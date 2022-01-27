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

import es.uvigo.mei.accidentes.entidades.AccidenteVehiculo;
import es.uvigo.mei.accidentes.entidades.AccidenteVehiculoId;
import es.uvigo.mei.accidentes.servicios.AccidenteVehiculoService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/accidenteVehiculo", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccidenteVehiculoController {
	@Autowired
	AccidenteVehiculoService accidenteVehiculoService;


	@Operation(summary = "Recuperar el listado de AccidenteVehiculo")
	@GetMapping()
	public ResponseEntity<List<EntityModel<AccidenteVehiculo>>> buscarTodos(
			@RequestParam(name = "vehiculoEstado", required = false) String vehiculoEstado) {
		try {
			List<AccidenteVehiculo> resultado = new ArrayList<>();

			if (vehiculoEstado != null) {
				resultado = accidenteVehiculoService.buscarPorVehiculoEstado(vehiculoEstado);
			}	
			else {
				resultado = accidenteVehiculoService.buscarTodos();
			}

			if (resultado.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<EntityModel<AccidenteVehiculo>> resultadoDTO = new ArrayList<>();
			resultado.forEach(a -> resultadoDTO.add(crearDTOAccidenteVehiculo(a)));

			return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Recuperar datos de un AccidenteVehiculo")
	@GetMapping("{idAccidente}&{idVehiculo}")
	public ResponseEntity<EntityModel<AccidenteVehiculo>> buscarPorId(
		@PathVariable("idAccidente") Long idAccidente,
		@PathVariable("idVehiculo") Long idVehiculo
	) {

		AccidenteVehiculoId id = new AccidenteVehiculoId(idAccidente, idVehiculo);

		Optional<AccidenteVehiculo> accidenteVehiculo = accidenteVehiculoService.buscarPorId(id);

		if (accidenteVehiculo.isPresent()) {
			EntityModel<AccidenteVehiculo> dto = crearDTOAccidenteVehiculo(accidenteVehiculo.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Eliminar un AccidenteVehiculo")
	@DeleteMapping("{idAccidente}&{idVehiculo}")
	public ResponseEntity<HttpStatus> eliminar(
		@PathVariable("idAccidente") Long idAccidente,
		@PathVariable("idVehiculo") Long idVehiculo
	) {
		try {
			AccidenteVehiculoId id = new AccidenteVehiculoId(idAccidente, idVehiculo);

			Optional<AccidenteVehiculo> accidenteVehiculo = accidenteVehiculoService.buscarPorId(id);
			if (accidenteVehiculo.isPresent()) {
				accidenteVehiculoService.eliminar(accidenteVehiculo.get());
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@Operation(summary = "Actualizar un AccidenteVehiculo")
	@PutMapping("{idAccidente}&{idVehiculo}")
	public ResponseEntity<EntityModel<AccidenteVehiculo>> modificar(		
			@PathVariable("idAccidente") Long idAccidente,
			@PathVariable("idVehiculo") Long idVehiculo,
			@Valid @RequestBody AccidenteVehiculo accidenteVehiculo) {
			AccidenteVehiculoId id = new AccidenteVehiculoId(idAccidente, idVehiculo);
		
		Optional<AccidenteVehiculo> accidenteVehiculoOptional = accidenteVehiculoService.buscarPorId(id);

		if (accidenteVehiculoOptional.isPresent()) {
			AccidenteVehiculo nuevaAccidenteVehiculo = accidenteVehiculoService.modificar(accidenteVehiculo);
			EntityModel<AccidenteVehiculo> dto = crearDTOAccidenteVehiculo(nuevaAccidenteVehiculo);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Crear un nuevo AccidenteVehiculo")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<AccidenteVehiculo>> crear(
		@Valid @RequestBody AccidenteVehiculo accidenteVehiculo) {
		try {
			AccidenteVehiculo nuevaAccidenteVehiculo = accidenteVehiculoService.crear(accidenteVehiculo);
			EntityModel<AccidenteVehiculo> dto = crearDTOAccidenteVehiculo(nuevaAccidenteVehiculo);
			URI uri = crearURIAccidenteVehiculo(nuevaAccidenteVehiculo);

			return ResponseEntity.created(uri).body(dto);
		} catch (

		Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Crear los DTO con enlaces HATEOAS
	private EntityModel<AccidenteVehiculo> crearDTOAccidenteVehiculo(AccidenteVehiculo accidenteVehiculo) {
		Long idAccidente = accidenteVehiculo.getAccidente().getId();
		Long idVehiculo = accidenteVehiculo.getVehiculo().getId();
		EntityModel<AccidenteVehiculo> dto = EntityModel.of(accidenteVehiculo);
		Link link = linkTo(methodOn(AccidenteVehiculoController.class).buscarPorId(idAccidente, idVehiculo)).withSelfRel();
		dto.add(linkTo(methodOn(AccidenteController.class).buscarPorId(idAccidente)).withRel("accidente"));
		dto.add(linkTo(methodOn(VehiculoController.class).buscarPorId(idVehiculo)).withRel("vehiculo"));
		dto.add(link);
		return dto;
	}

	//{idAccidente}&{idVehiculo}
	// Construye la URI del nuevo recurso creado con POST
	private URI crearURIAccidenteVehiculo(AccidenteVehiculo accidenteVehiculo) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{idAccidente}&{idVehiculo}").buildAndExpand(
				accidenteVehiculo.getAccidente().getId(),
				accidenteVehiculo.getVehiculo().getId()
				).toUri();
	}

}
