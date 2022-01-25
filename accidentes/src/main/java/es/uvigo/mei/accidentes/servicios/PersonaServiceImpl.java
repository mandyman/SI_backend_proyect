package es.uvigo.mei.accidentes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.accidentes.daos.PersonaDAO;
import es.uvigo.mei.accidentes.entidades.Persona;
import es.uvigo.mei.accidentes.servicios.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaDAO personaDAO;

	@Override
	@Transactional
	public Persona crear(Persona persona){
		return personaDAO.save(persona);
	}

	@Override
	@Transactional
	public Persona modificar(Persona persona){
		return personaDAO.save(persona);
	}

	@Override
	@Transactional
	public void eliminar(Persona persona){
		personaDAO.delete(persona);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> buscarPorId(Long id){
		return personaDAO.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscarTodos(){
		return personaDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscarPorNombre(String nombre){
		return personaDAO.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscarPorApellidos(String apellidos){
		return personaDAO.findByApellidos(apellidos);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscarPorDNI(String dni){
		return personaDAO.findByDNI(dni);
	}

}