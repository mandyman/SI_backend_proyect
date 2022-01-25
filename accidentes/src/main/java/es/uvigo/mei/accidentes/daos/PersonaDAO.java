package es.uvigo.mei.accidentes.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.accidentes.entidades.Persona;

@Repository
public interface PersonaDAO extends JpaRepository<Persona, Long>{
	List<Persona> findByDireccionProvincia(String provincia);
    List<Persona> findByDireccionCodigoPostal(String codigoPostal);

    List<Persona> findByNombre(String nombre);
    List<Persona> findByApellidos(String apellidos);
    List<Persona> findByDNI(String dni);
}