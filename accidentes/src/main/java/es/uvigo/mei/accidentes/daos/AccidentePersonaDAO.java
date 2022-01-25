package es.uvigo.mei.accidentes.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.accidentes.entidades.AccidentePersona;

@Repository
public interface AccidentePersonaDAO extends JpaRepository<AccidentePersona, Long>{
	List<AccidentePersona> findByAccidente(long accidenteId);
	List<AccidentePersona> findByPersona(long personaId);
	
	List<AccidentePersona> findByTipo(String tipo);
	List<AccidentePersona> findByGravedad(String gravedad);
}