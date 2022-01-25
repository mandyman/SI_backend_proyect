package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.accidentes.daos.AccidenteDAO;
import es.uvigo.mei.accidentes.entidades.Accidente;
import es.uvigo.mei.accidentes.servicios.AccidenteService;

@Service
public class AccidenteServiceImpl implements AccidenteService {

	@Autowired
	private AccidenteDAO accidenteDAO;

	@Override
	@Transactional
	public Accidente crear(Accidente accidente){
		return accidenteDAO.save(accidente);
	}

	@Override
	@Transactional
	public Accidente modificar(Accidente accidente){
		return accidenteDAO.save(accidente);
	}

	@Override
	@Transactional
	public void eliminar(Accidente accidente){
		accidenteDAO.delete(accidente);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Accidente> buscarPorId(Long id){
		return accidenteDAO.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accidente> buscarTodos(){
		return accidenteDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accidente> buscarPorProvincia(String provincia){
		return accidenteDAO.findByLocalizacionProvincia(provincia);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accidente> buscarPorCodigoPostal(String codigoPostal){
		return accidenteDAO.findByLocalizacionCodigoPostal(codigoPostal);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accidente> buscarPorDescripcion(String descripcion){
		return accidenteDAO.findByDescripcion(descripcion);
	}

}