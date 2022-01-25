package es.uvigo.mei.accidentes.controladores;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uvigo.mei.accidentes.entidades.Accidente;
import es.uvigo.mei.accidentes.servicios.AccidenteService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/accidente", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidenteController {
	@Autowired
	AccidenteService accidenteService;


	@Operation(summary = "Recuperar el listado de Accidentes")
	@GetMapping()
	public ResponseEntity<List<EntityModel<Accidente>>> buscarTodos(
			@RequestParam(name = "provincia", required = false) String provincia,
			@RequestParam(name = "codigoPostal", required = false) String codigoPostal,
			@RequestParam(name = "descripcion", required = false) String descripcion) {
		try {
			List<Accidente> resultado = new ArrayList<>();

			if (provincia != null) {
				resultado = accidenteService.buscarPorProvincia(provincia);
			} else if (codigoPostal != null) {
				resultado = accidenteService.buscarPorCodigoPostal(codigoPostal);
			} else if (descripcion != null) {
				resultado = accidenteService.buscarPorDescripcion(descripcion);
			} 
			else {
				resultado = accidenteService.buscarTodos();
			}

			if (resultado.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<EntityModel<Accidente>> resultadoDTO = new ArrayList<>();
			resultado.forEach(a -> resultadoDTO.add(crearDTOAccidente(a)));

			return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Recuperar datos de un Accidente")
	@GetMapping(path = "{id}")
	public ResponseEntity<EntityModel<Accidente>> buscarPorId(@PathVariable("id") Long id) {
		Optional<Accidente> accidente = accidenteService.buscarPorId(id);

		if (accidente.isPresent()) {
			EntityModel<Accidente> dto = crearDTOAccidente(accidente.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Eliminar un Accidente")
	@DeleteMapping(path = "{id}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
		try {
			Optional<Accidente> accidente = accidenteService.buscarPorId(id);
			if (accidente.isPresent()) {
				accidenteService.eliminar(accidente.get());
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@Operation(summary = "Actualizar un Accidente")
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Accidente>> modificar(@PathVariable("id") Long id,
			@Valid @RequestBody Accidente accidente) {
		Optional<Accidente> accidenteOptional = accidenteService.buscarPorId(id);

		if (accidenteOptional.isPresent()) {
			Accidente nuevaAccidente = accidenteService.modificar(accidente);
			EntityModel<Accidente> dto = crearDTOAccidente(nuevaAccidente);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Crear un nuevo Accidente")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Accidente>> crear(@Valid @RequestBody Accidente accidente) {
		try {
			Accidente nuevaAccidente = accidenteService.crear(accidente);
			EntityModel<Accidente> dto = crearDTOAccidente(nuevaAccidente);
			URI uri = crearURIAccidente(nuevaAccidente);

			return ResponseEntity.created(uri).body(dto);
		} catch (

		Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Crear los DTO con enlaces HATEOAS
	private EntityModel<Accidente> crearDTOAccidente(Accidente accidente) {
		Long id = accidente.getId();
		EntityModel<Accidente> dto = EntityModel.of(accidente);
		Link link = linkTo(methodOn(AccidenteController.class).buscarPorId(id)).withSelfRel();
		dto.add(link);
		return dto;
	}

	
	// Construye la URI del nuevo recurso creado con POST
	private URI crearURIAccidente(Accidente accidente) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accidente.getId()).toUri();
	}

}
