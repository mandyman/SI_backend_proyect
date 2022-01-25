package es.uvigo.mei.accidentes.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.accidentes.entidades.Multa;

@Repository
public interface MultaDAO extends JpaRepository<Multa, Long>{
	List<Multa> findByTipo(String tipo);
	List<Multa> findBySancion(String sancion);
	
	@Query("SELECT m FROM Multa AS m WHERE m.accidente.id = :accidenteId")
	List<Multa> findByAccidente(Long accidenteId);
}