package es.uvigo.mei.accidentes.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.accidentes.entidades.AccidenteVehiculo;

@Repository
public interface AccidenteVehiculoDAO extends JpaRepository<AccidenteVehiculo, Long>{
	List<AccidenteVehiculo> findByAccidente(long accidenteId);
	List<AccidenteVehiculo> findByVehiculo(long personaId);
	
	@Query("SELECT av FROM AccidenteVehiculo av WHERE av.vehiculoEstado LIKE %?1%")
	List<AccidenteVehiculo> findByVehiculoEstado(String patron_vehiculoEstado);
}