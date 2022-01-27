package es.uvigo.mei.accidentes.controladores;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

import es.uvigo.mei.accidentes.entidades.AccidentePersonaVehiculo;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaVehiculoId;
import es.uvigo.mei.accidentes.servicios.AccidentePersonaVehiculoService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/accidentePersonaVehiculo", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidentePersonaVehiculoController {
	@Autowired
	AccidentePersonaVehiculoService accidentePersonaVehiculoService;


	@Operation(summary = "Recuperar el listado de AccidentePersonaVehiculo")
	@GetMapping()
	public ResponseEntity<List<EntityModel<AccidentePersonaVehiculo>>> buscarTodos(
			@RequestParam(name = "rol", required = false) String rol) {
		try {
			List<AccidentePersonaVehiculo> resultado = new ArrayList<>();

			if (rol != null) {
				resultado = accidentePersonaVehiculoService.buscarPorRol(rol);
			}	
			else {
				resultado = accidentePersonaVehiculoService.buscarTodos();
			}

			if (resultado.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<EntityModel<AccidentePersonaVehiculo>> resultadoDTO = new ArrayList<>();
			resultado.forEach(a -> resultadoDTO.add(crearDTOAccidentePersonaVehiculo(a)));

			return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Recuperar datos de un AccidentePersonaVehiculo")
	@GetMapping("{idAccidente}&{idPersona}&{idVehiculo}")
	public ResponseEntity<EntityModel<AccidentePersonaVehiculo>> buscarPorId(
		@PathVariable("idAccidente") Long idAccidente,
		@PathVariable("idPersona") Long idPersona,
		@PathVariable("idVehiculo") Long idVehiculo
	) {

		AccidentePersonaVehiculoId id = new AccidentePersonaVehiculoId(idAccidente, idPersona, idVehiculo);

		Optional<AccidentePersonaVehiculo> accidentePersonaVehiculo = accidentePersonaVehiculoService.buscarPorId(id);

		if (accidentePersonaVehiculo.isPresent()) {
			EntityModel<AccidentePersonaVehiculo> dto = crearDTOAccidentePersonaVehiculo(accidentePersonaVehiculo.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Eliminar un AccidentePersonaVehiculo")
	@DeleteMapping("{idAccidente}&{idPersona}&{idVehiculo}")
	public ResponseEntity<HttpStatus> eliminar(
		@PathVariable("idAccidente") Long idAccidente,
		@PathVariable("idPersona") Long idPersona,
		@PathVariable("idVehiculo") Long idVehiculo
	) {
		try {
			AccidentePersonaVehiculoId id = new AccidentePersonaVehiculoId(idAccidente, idPersona, idVehiculo);

			Optional<AccidentePersonaVehiculo> accidentePersonaVehiculo = accidentePersonaVehiculoService.buscarPorId(id);
			if (accidentePersonaVehiculo.isPresent()) {
				accidentePersonaVehiculoService.eliminar(accidentePersonaVehiculo.get());
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@Operation(summary = "Actualizar un AccidentePersonaVehiculo")
	@PutMapping("{idAccidente}&{idPersona}&{idVehiculo}")
	public ResponseEntity<EntityModel<AccidentePersonaVehiculo>> modificar(		
			@PathVariable("idAccidente") Long idAccidente,
			@PathVariable("idPersona") Long idPersona,
			@PathVariable("idVehiculo") Long idVehiculo,
			@Valid @RequestBody AccidentePersonaVehiculo accidentePersonaVehiculo) {
				AccidentePersonaVehiculoId id = new AccidentePersonaVehiculoId(idAccidente, idPersona, idVehiculo);
		
		Optional<AccidentePersonaVehiculo> accidentePersonaVehiculoOptional = accidentePersonaVehiculoService.buscarPorId(id);

		if (accidentePersonaVehiculoOptional.isPresent()) {
			AccidentePersonaVehiculo nuevaAccidentePersonaVehiculo = accidentePersonaVehiculoService.modificar(accidentePersonaVehiculo);
			EntityModel<AccidentePersonaVehiculo> dto = crearDTOAccidentePersonaVehiculo(nuevaAccidentePersonaVehiculo);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Crear un nuevo AccidentePersonaVehiculo")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<AccidentePersonaVehiculo>> crear(
		@Valid @RequestBody AccidentePersonaVehiculo accidentePersonaVehiculo) {
		try {
			AccidentePersonaVehiculo nuevaAccidentePersonaVehiculo = accidentePersonaVehiculoService.crear(accidentePersonaVehiculo);
			EntityModel<AccidentePersonaVehiculo> dto = crearDTOAccidentePersonaVehiculo(nuevaAccidentePersonaVehiculo);
			URI uri = crearURIAccidentePersonaVehiculo(nuevaAccidentePersonaVehiculo);

			return ResponseEntity.created(uri).body(dto);
		} catch (

		Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Crear los DTO con enlaces HATEOAS
	private EntityModel<AccidentePersonaVehiculo> crearDTOAccidentePersonaVehiculo(AccidentePersonaVehiculo accidentePersonaVehiculo) {
		Long idAccidente = accidentePersonaVehiculo.getAccidente().getId();
		Long idPersona = accidentePersonaVehiculo.getPersona().getId();
		Long idVehiculo = accidentePersonaVehiculo.getVehiculo().getId();
		EntityModel<AccidentePersonaVehiculo> dto = EntityModel.of(accidentePersonaVehiculo);
		Link link = linkTo(methodOn(AccidentePersonaVehiculoController.class).buscarPorId(idAccidente, idPersona, idVehiculo)).withSelfRel();
		dto.add(linkTo(methodOn(AccidenteController.class).buscarPorId(idAccidente)).withRel("accidente"));
		dto.add(linkTo(methodOn(PersonaController.class).buscarPorId(idPersona)).withRel("persona"));
		dto.add(linkTo(methodOn(VehiculoController.class).buscarPorId(idVehiculo)).withRel("vehiculo"));
		dto.add(link);
		return dto;
	}

	//{idAccidente}&{idPersona}&{idVehiculo}
	// Construye la URI del nuevo recurso creado con POST
	private URI crearURIAccidentePersonaVehiculo(AccidentePersonaVehiculo accidentePersonaVehiculo) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{idAccidente}&{idPersona}&{idVehiculo}").buildAndExpand(
					accidentePersonaVehiculo.getAccidente().getId(),
					accidentePersonaVehiculo.getPersona().getId(),
					accidentePersonaVehiculo.getVehiculo().getId()
				).toUri();
	}

}
