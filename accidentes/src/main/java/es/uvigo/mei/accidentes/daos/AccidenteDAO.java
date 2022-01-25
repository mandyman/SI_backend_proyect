package es.uvigo.mei.accidentes.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.accidentes.entidades.Accidente;

@Repository
public interface AccidenteDAO extends JpaRepository<Accidente, Long>{
	List<Accidente> findByLocalizacionProvincia(String provincia);
    List<Accidente> findByLocalizacionCodigoPostal(String codigoPostal);

    @Query("SELECT a FROM Accidente a WHERE a.descripcion LIKE %?1%")
    List<Accidente> findByDescripcion(String patron_description);
}