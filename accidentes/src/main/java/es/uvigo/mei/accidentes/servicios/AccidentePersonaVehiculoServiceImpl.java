package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.accidentes.daos.AccidentePersonaVehiculoDAO;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaVehiculo;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaVehiculoId;
import es.uvigo.mei.accidentes.servicios.AccidentePersonaVehiculoService;

@Service
public class AccidentePersonaVehiculoServiceImpl implements AccidentePersonaVehiculoService {

	@Autowired
	private AccidentePersonaVehiculoDAO accidentePersonaVehiculoDAO;

	@Override
	@Transactional
	public AccidentePersonaVehiculo crear(AccidentePersonaVehiculo accidentePersonaVehiculo){
		return accidentePersonaVehiculoDAO.save(accidentePersonaVehiculo);
	}

	@Override
	@Transactional
	public AccidentePersonaVehiculo modificar(AccidentePersonaVehiculo accidentePersonaVehiculo){
		return accidentePersonaVehiculoDAO.save(accidentePersonaVehiculo);
	}

	@Override
	@Transactional
	public void eliminar(AccidentePersonaVehiculo accidentePersonaVehiculo){
		accidentePersonaVehiculoDAO.delete(accidentePersonaVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<AccidentePersonaVehiculo> buscarPorId(AccidentePersonaVehiculoId accidentePersonaVehiculoId ){
		return accidentePersonaVehiculoDAO.findById(accidentePersonaVehiculoId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersonaVehiculo> buscarTodos(){
		return accidentePersonaVehiculoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersonaVehiculo> buscarPorRol(String rol){
		return accidentePersonaVehiculoDAO.findByRol(rol);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersonaVehiculo> buscarPorAccidente(long accidenteId){
		return accidentePersonaVehiculoDAO.findByAccidente(accidenteId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersonaVehiculo> buscarPorPersona(long personaId){
		return accidentePersonaVehiculoDAO.findByPersona(personaId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersonaVehiculo> buscarPorVehiculo(long vehiculoId){
		return accidentePersonaVehiculoDAO.findByVehiculo(vehiculoId);
	}

}