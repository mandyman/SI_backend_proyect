package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.accidentes.daos.AccidentePersonaDAO;
import es.uvigo.mei.accidentes.entidades.AccidentePersona;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaId;
import es.uvigo.mei.accidentes.servicios.AccidentePersonaService;

@Service
public class AccidentePersonaServiceImpl implements AccidentePersonaService {

	@Autowired
	private AccidentePersonaDAO accidentePersonaDAO;

	@Override
	@Transactional
	public AccidentePersona crear(AccidentePersona accidentePersona){
		return accidentePersonaDAO.save(accidentePersona);
	}

	@Override
	@Transactional
	public AccidentePersona modificar(AccidentePersona accidentePersona){
		return accidentePersonaDAO.save(accidentePersona);
	}

	@Override
	@Transactional
	public void eliminar(AccidentePersona accidentePersona){
		accidentePersonaDAO.delete(accidentePersona);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<AccidentePersona> buscarPorId(AccidentePersonaId accidentePersonaId ){
		return accidentePersonaDAO.findById(accidentePersonaId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersona> buscarTodos(){
		return accidentePersonaDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersona> buscarPorTipo(String tipo){
		return accidentePersonaDAO.findByTipo(tipo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersona> buscarPorGravedad(String gravedad){
		return accidentePersonaDAO.findByGravedad(gravedad);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersona> buscarPorAccidente(long accidenteId){
		return accidentePersonaDAO.findByAccidente(accidenteId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccidentePersona> buscarPorPersona(long personaId){
		return accidentePersonaDAO.findByPersona(personaId);
	}

}