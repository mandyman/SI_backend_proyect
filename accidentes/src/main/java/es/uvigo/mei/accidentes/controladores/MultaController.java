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

import es.uvigo.mei.accidentes.entidades.Multa;
import es.uvigo.mei.accidentes.servicios.MultaService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/multa", produces = MediaType.APPLICATION_JSON_VALUE)
public class MultaController {
	@Autowired
	MultaService multaService;


	@Operation(summary = "Recuperar el listado de Multas")
	@GetMapping()
	public ResponseEntity<List<EntityModel<Multa>>> buscarTodos(
			@RequestParam(name = "tipo", required = false) String tipo,
			@RequestParam(name = "sancion", required = false) String sancion,
			@RequestParam(name = "accidenteId", required = false) Long accidenteId) {
		try {
			List<Multa> resultado = new ArrayList<>();

			if (tipo != null) {
				resultado = multaService.buscarPorTipo(tipo);
			} else if (sancion != null) {
				resultado = multaService.buscarPorSancion(sancion);
			} else if (accidenteId != null) {
				resultado = multaService.buscarPorAccidenteId(accidenteId);
			} 
			else {
				resultado = multaService.buscarTodos();
			}

			if (resultado.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<EntityModel<Multa>> resultadoDTO = new ArrayList<>();
			resultado.forEach(a -> resultadoDTO.add(crearDTOMulta(a)));

			return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Recuperar datos de una Multa")
	@GetMapping(path = "{id}")
	public ResponseEntity<EntityModel<Multa>> buscarPorId(@PathVariable("id") Long id) {
		Optional<Multa> multa = multaService.buscarPorId(id);

		if (multa.isPresent()) {
			EntityModel<Multa> dto = crearDTOMulta(multa.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Eliminar una Multa")
	@DeleteMapping(path = "{id}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
		try {
			Optional<Multa> multa = multaService.buscarPorId(id);
			if (multa.isPresent()) {
				multaService.eliminar(multa.get());
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@Operation(summary = "Actualizar un Multa")
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Multa>> modificar(@PathVariable("id") Long id,
			@Valid @RequestBody Multa multa) {
		Optional<Multa> multaOptional = multaService.buscarPorId(id);

		if (multaOptional.isPresent()) {
			Multa nuevaMulta = multaService.modificar(multa);
			EntityModel<Multa> dto = crearDTOMulta(nuevaMulta);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Crear una nueva Multa")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Multa>> crear(@Valid @RequestBody Multa multa) {
		try {
			Multa nuevaMulta = multaService.crear(multa);
			EntityModel<Multa> dto = crearDTOMulta(nuevaMulta);
			URI uri = crearURIMulta(nuevaMulta);

			return ResponseEntity.created(uri).body(dto);
		} catch (

		Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Crear los DTO con enlaces HATEOAS
	private EntityModel<Multa> crearDTOMulta(Multa multa) {
		Long idMulta = multa.getId();
		Long idAccidente = multa.getAccidente().getId();
		EntityModel<Multa> dto = EntityModel.of(multa);
		Link link = linkTo(methodOn(MultaController.class).buscarPorId(idMulta)).withSelfRel();
		dto.add(link);
		//dto.add(linkTo(methodOn(VehiculoController.class).buscarPorId(idVehiculo)).withRel("vehiculo"));
		dto.add(linkTo(methodOn(AccidenteController.class).buscarPorId(idAccidente)).withRel("accidente"));
		return dto;
	}

	
	// Construye la URI del nuevo recurso creado con POST
	private URI crearURIMulta(Multa multa) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(multa.getId()).toUri();
	}

}
