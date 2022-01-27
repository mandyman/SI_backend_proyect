package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.accidentes.daos.AccidenteVehiculoDAO;
import es.uvigo.mei.accidentes.entidades.AccidenteVehiculo;
import es.uvigo.mei.accidentes.entidades.AccidenteVehiculoId;
import es.uvigo.mei.accidentes.servicios.AccidenteVehiculoService;

@Service
public class AccidenteVehiculoServiceImpl implements AccidenteVehiculoService {

	@Autowired
	private AccidenteVehiculoDAO accidenteVehiculoDAO;

	@Override
	@Transactional
	public AccidenteVehiculo crear(AccidenteVehiculo accidenteVehiculo){
		return accidenteVehiculoDAO.save(accidenteVehiculo);
	}

	@Override
	@Transactional
	public AccidenteVehiculo modificar(AccidenteVehiculo accidenteVehiculo){
		return accidenteVehiculoDAO.save(accidenteVehiculo);
	}

	@Override
	@Transactional
	public void eliminar(AccidenteVehiculo accidenteVehiculo){
		accidenteVehiculoDAO.delete(accidenteVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<AccidenteVehiculo> buscarPorId(AccidenteVehiculoId accidenteVehiculoId ){
		return accidenteVehiculoDAO.findById(accidenteVehiculoId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidenteVehiculo> buscarTodos(){
		return accidenteVehiculoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidenteVehiculo> buscarPorVehiculoEstado(String vehiculoEstado){
		return accidenteVehiculoDAO.findByVehiculoEstado(vehiculoEstado);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidenteVehiculo> buscarPorAccidente(long accidenteId){
		return accidenteVehiculoDAO.findByAccidente(accidenteId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidenteVehiculo> buscarPorVehiculo(long vehiculoId){
		return accidenteVehiculoDAO.findByVehiculo(vehiculoId);
	}

}