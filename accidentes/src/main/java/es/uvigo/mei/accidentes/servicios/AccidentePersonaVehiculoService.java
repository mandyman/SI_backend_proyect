package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaVehiculo;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaVehiculoId;

public interface AccidentePersonaVehiculoService {
	public AccidentePersonaVehiculo crear(AccidentePersonaVehiculo accidentePersonaVehiculo);
	public AccidentePersonaVehiculo modificar(AccidentePersonaVehiculo accidentePersonaVehiculo);
	public void eliminar(AccidentePersonaVehiculo accidentePersonaVehiculo);
	public Optional<AccidentePersonaVehiculo> buscarPorId(AccidentePersonaVehiculoId AccidentePersonaVehiculoId);
	public List<AccidentePersonaVehiculo> buscarTodos();

	public List<AccidentePersonaVehiculo> buscarPorRol(String rol);
	
	public List<AccidentePersonaVehiculo> buscarPorAccidente(long accidenteId);
	public List<AccidentePersonaVehiculo> buscarPorPersona(long personaId);
	public List<AccidentePersonaVehiculo> buscarPorVehiculo(long vehiculoId);
}