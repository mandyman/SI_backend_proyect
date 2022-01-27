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

import es.uvigo.mei.accidentes.entidades.AccidentePersona;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaId;
import es.uvigo.mei.accidentes.servicios.AccidentePersonaService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/accidentePersona", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidentePersonaController {
	@Autowired
	AccidentePersonaService accidentePersonaService;


	@Operation(summary = "Recuperar el listado de AccidentePersona")
	@GetMapping()
	public ResponseEntity<List<EntityModel<AccidentePersona>>> buscarTodos(
			@RequestParam(name = "modelo", required = false) String tipo,
			@RequestParam(name = "matricula", required = false) String gravedad) {
		try {
			List<AccidentePersona> resultado = new ArrayList<>();

			if (tipo != null) {
				resultado = accidentePersonaService.buscarPorTipo(tipo);
			} else if (gravedad != null) {
				resultado = accidentePersonaService.buscarPorGravedad(gravedad);
			}		
			else {
				resultado = accidentePersonaService.buscarTodos();
			}

			if (resultado.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<EntityModel<AccidentePersona>> resultadoDTO = new ArrayList<>();
			resultado.forEach(a -> resultadoDTO.add(crearDTOAccidentePersona(a)));

			return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Recuperar datos de un AccidentePersona")
	@GetMapping("{idAccidente}&{idPersona}")
	public ResponseEntity<EntityModel<AccidentePersona>> buscarPorId(
		@PathVariable("idAccidente") Long idAccidente,
		@PathVariable("idPersona") Long idPersona
	) {

		AccidentePersonaId id = new AccidentePersonaId(idAccidente, idPersona);

		Optional<AccidentePersona> accidentePersona = accidentePersonaService.buscarPorId(id);

		if (accidentePersona.isPresent()) {
			EntityModel<AccidentePersona> dto = crearDTOAccidentePersona(accidentePersona.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Eliminar un AccidentePersona")
	@DeleteMapping("{idAccidente}&{idPersona}")
	public ResponseEntity<HttpStatus> eliminar(
		@PathVariable("idAccidente") Long idAccidente,
		@PathVariable("idPersona") Long idPersona
	) {
		try {
			AccidentePersonaId id = new AccidentePersonaId(idAccidente, idPersona);

			Optional<AccidentePersona> accidentePersona = accidentePersonaService.buscarPorId(id);
			if (accidentePersona.isPresent()) {
				accidentePersonaService.eliminar(accidentePersona.get());
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@Operation(summary = "Actualizar un AccidentePersona")
	@PutMapping("{idAccidente}&{idPersona}")
	public ResponseEntity<EntityModel<AccidentePersona>> modificar(		
			@PathVariable("idAccidente") Long idAccidente,
			@PathVariable("idPersona") Long idPersona,
			@Valid @RequestBody AccidentePersona accidentePersona) {
		AccidentePersonaId id = new AccidentePersonaId(idAccidente, idPersona);
		
		Optional<AccidentePersona> accidentePersonaOptional = accidentePersonaService.buscarPorId(id);

		if (accidentePersonaOptional.isPresent()) {
			AccidentePersona nuevaAccidentePersona = accidentePersonaService.modificar(accidentePersona);
			EntityModel<AccidentePersona> dto = crearDTOAccidentePersona(nuevaAccidentePersona);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Crear un nuevo AccidentePersona")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<AccidentePersona>> crear(@Valid @RequestBody AccidentePersona accidentePersona) {
		try {
			AccidentePersona nuevaAccidentePersona = accidentePersonaService.crear(accidentePersona);
			EntityModel<AccidentePersona> dto = crearDTOAccidentePersona(nuevaAccidentePersona);
			URI uri = crearURIAccidentePersona(nuevaAccidentePersona);

			return ResponseEntity.created(uri).body(dto);
		} catch (

		Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Crear los DTO con enlaces HATEOAS
	private EntityModel<AccidentePersona> crearDTOAccidentePersona(AccidentePersona accidentePersona) {
		Long idAccidente = accidentePersona.getAccidente().getId();
		Long idPersona = accidentePersona.getPersona().getId();
		EntityModel<AccidentePersona> dto = EntityModel.of(accidentePersona);
		Link link = linkTo(methodOn(AccidentePersonaController.class).buscarPorId(idAccidente, idPersona)).withSelfRel();
		dto.add(linkTo(methodOn(AccidenteController.class).buscarPorId(idAccidente)).withRel("accidente"));
		dto.add(linkTo(methodOn(PersonaController.class).buscarPorId(idPersona)).withRel("persona"));
		dto.add(link);
		return dto;
	}

	//{idAccidente}&{idPersona}
	// Construye la URI del nuevo recurso creado con POST
	private URI crearURIAccidentePersona(AccidentePersona accidentePersona) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{idAccidente}&{idPersona}").buildAndExpand(
					accidentePersona.getAccidente().getId(),
					accidentePersona.getPersona().getId()		
				).toUri();
	}

}
