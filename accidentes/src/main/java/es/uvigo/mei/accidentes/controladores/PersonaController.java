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

import es.uvigo.mei.accidentes.entidades.Persona;
import es.uvigo.mei.accidentes.servicios.PersonaService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/persona", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonaController {
	@Autowired
	PersonaService personaService;


	@Operation(summary = "Recuperar el listado de Personas")
	@GetMapping()
	public ResponseEntity<List<EntityModel<Persona>>> buscarTodos(
			@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "apellidos", required = false) String apellidos,
			@RequestParam(name = "dni", required = false) String dni,
			@RequestParam(name = "provincia", required = false) String provincia,
			@RequestParam(name = "codigoPostal", required = false) String codigoPostal
			) {
		try {
			List<Persona> resultado = new ArrayList<>();

			if (nombre != null) {
				resultado = personaService.buscarPorNombre(nombre);
			} else if (apellidos != null) {
				resultado = personaService.buscarPorApellidos(apellidos);
			} else if (dni != null) {
				resultado = personaService.buscarPorDNI(dni);
			} else if (provincia != null) {
				resultado = personaService.buscarPorProvincia(provincia);
			} else if (codigoPostal != null) {
				resultado = personaService.buscarPorCodigoPostal(codigoPostal);
			} 
			else {
				resultado = personaService.buscarTodos();
			}

			if (resultado.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<EntityModel<Persona>> resultadoDTO = new ArrayList<>();
			resultado.forEach(a -> resultadoDTO.add(crearDTOPersona(a)));

			return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Recuperar datos de una Persona")
	@GetMapping(path = "{id}")
	public ResponseEntity<EntityModel<Persona>> buscarPorId(@PathVariable("id") Long id) {
		Optional<Persona> persona = personaService.buscarPorId(id);

		if (persona.isPresent()) {
			EntityModel<Persona> dto = crearDTOPersona(persona.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Eliminar una Persona")
	@DeleteMapping(path = "{id}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
		try {
			Optional<Persona> persona = personaService.buscarPorId(id);
			if (persona.isPresent()) {
				personaService.eliminar(persona.get());
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@Operation(summary = "Actualizar una Persona")
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Persona>> modificar(@PathVariable("id") Long id,
			@Valid @RequestBody Persona persona) {
		Optional<Persona> personaOptional = personaService.buscarPorId(id);

		if (personaOptional.isPresent()) {
			Persona nuevaPersona = personaService.modificar(persona);
			EntityModel<Persona> dto = crearDTOPersona(nuevaPersona);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Crear una nueva Persona")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Persona>> crear(@Valid @RequestBody Persona persona) {
		try {
			Persona nuevaPersona = personaService.crear(persona);
			EntityModel<Persona> dto = crearDTOPersona(nuevaPersona);
			URI uri = crearURIPersona(nuevaPersona);

			return ResponseEntity.created(uri).body(dto);
		} catch (

		Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Crear los DTO con enlaces HATEOAS
	private EntityModel<Persona> crearDTOPersona(Persona persona) {
		Long id = persona.getId();
		EntityModel<Persona> dto = EntityModel.of(persona);
		Link link = linkTo(methodOn(PersonaController.class).buscarPorId(id)).withSelfRel();
		dto.add(link);
		return dto;
	}

	
	// Construye la URI del nuevo recurso creado con POST
	private URI crearURIPersona(Persona persona) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(persona.getId()).toUri();
	}

}
