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

import es.uvigo.mei.accidentes.entidades.Vehiculo;
import es.uvigo.mei.accidentes.servicios.VehiculoService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/vehiculo", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehiculoController {
	@Autowired
	VehiculoService vehiculoService;


	@Operation(summary = "Recuperar el listado de Vehiculo")
	@GetMapping()
	public ResponseEntity<List<EntityModel<Vehiculo>>> buscarTodos(
			@RequestParam(name = "modelo", required = false) String modelo,
			@RequestParam(name = "matricula", required = false) String matricula,
			@RequestParam(name = "conductorId", required = false) Long conductorId) {
		try {
			List<Vehiculo> resultado = new ArrayList<>();

			if (modelo != null) {
				resultado = vehiculoService.buscarPorModelo(modelo);
			} else if (matricula != null) {
				resultado = vehiculoService.buscarPorMatricula(matricula);
			} else if (conductorId != null) {
				resultado = vehiculoService.buscarPorConductorId(conductorId);
			} 
			else {
				resultado = vehiculoService.buscarTodos();
			}

			if (resultado.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<EntityModel<Vehiculo>> resultadoDTO = new ArrayList<>();
			resultado.forEach(a -> resultadoDTO.add(crearDTOVehiculo(a)));

			return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Recuperar datos de un Vehiculo")
	@GetMapping(path = "{id}")
	public ResponseEntity<EntityModel<Vehiculo>> buscarPorId(@PathVariable("id") Long id) {
		Optional<Vehiculo> vehiculo = vehiculoService.buscarPorId(id);

		if (vehiculo.isPresent()) {
			EntityModel<Vehiculo> dto = crearDTOVehiculo(vehiculo.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Eliminar un Vehiculo")
	@DeleteMapping(path = "{id}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
		try {
			Optional<Vehiculo> vehiculo = vehiculoService.buscarPorId(id);
			if (vehiculo.isPresent()) {
				vehiculoService.eliminar(vehiculo.get());
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@Operation(summary = "Actualizar un Vehiculo")
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Vehiculo>> modificar(@PathVariable("id") Long id,
			@Valid @RequestBody Vehiculo vehiculo) {
		Optional<Vehiculo> vehiculoOptional = vehiculoService.buscarPorId(id);

		if (vehiculoOptional.isPresent()) {
			Vehiculo nuevaVehiculo = vehiculoService.modificar(vehiculo);
			EntityModel<Vehiculo> dto = crearDTOVehiculo(nuevaVehiculo);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Crear un nuevo Vehiculo")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Vehiculo>> crear(@Valid @RequestBody Vehiculo vehiculo) {
		try {
			Vehiculo nuevaVehiculo = vehiculoService.crear(vehiculo);
			EntityModel<Vehiculo> dto = crearDTOVehiculo(nuevaVehiculo);
			URI uri = crearURIVehiculo(nuevaVehiculo);

			return ResponseEntity.created(uri).body(dto);
		} catch (

		Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Crear los DTO con enlaces HATEOAS
	private EntityModel<Vehiculo> crearDTOVehiculo(Vehiculo vehiculo) {
		Long idVehiculo = vehiculo.getId();
		Long idConductor = vehiculo.getConductor().getId();
		EntityModel<Vehiculo> dto = EntityModel.of(vehiculo);
		Link link = linkTo(methodOn(VehiculoController.class).buscarPorId(idVehiculo)).withSelfRel();
		dto.add(link);
		//dto.add(linkTo(methodOn(VehiculoController.class).buscarPorId(idVehiculo)).withRel("vehiculo"));
		dto.add(linkTo(methodOn(PersonaController.class).buscarPorId(idConductor)).withRel("persona"));
		return dto;
	}

	
	// Construye la URI del nuevo recurso creado con POST
	private URI crearURIVehiculo(Vehiculo vehiculo) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vehiculo.getId()).toUri();
	}

}
