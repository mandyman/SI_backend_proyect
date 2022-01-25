package es.uvigo.mei.accidentes.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.accidentes.entidades.Vehiculo;

@Repository
public interface VehiculoDAO extends JpaRepository<Vehiculo, Long>{
	List<Vehiculo> findByModelo(String modelo);
    List<Vehiculo> findByMatricula(String matricula);

	@Query("SELECT v FROM Vehiculo AS v WHERE v.conductor.id = :conductorId")
	List<Vehiculo> findByConductor(Long conductorId);
}