package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.accidentes.daos.VehiculoDAO;
import es.uvigo.mei.accidentes.entidades.Vehiculo;
import es.uvigo.mei.accidentes.servicios.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoDAO vehiculoDAO;

	@Override
	@Transactional
	public Vehiculo crear(Vehiculo accidente){
		return vehiculoDAO.save(accidente);
	}

	@Override
	@Transactional
	public Vehiculo modificar(Vehiculo accidente){
		return vehiculoDAO.save(accidente);
	}

	@Override
	@Transactional
	public void eliminar(Vehiculo accidente){
		vehiculoDAO.delete(accidente);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Vehiculo> buscarPorId(Long id){
		return vehiculoDAO.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> buscarTodos(){
		return vehiculoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> buscarPorModelo(String modelo){
		return vehiculoDAO.findByModelo(modelo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> buscarPorMatricula(String matricula){
		return vehiculoDAO.findByMatricula(matricula);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> buscarPorConductorId(Long conductorId){
		return vehiculoDAO.findByConductor(conductorId);
	}

}