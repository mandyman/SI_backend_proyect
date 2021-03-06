package es.uvigo.mei.accidentes.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.accidentes.entidades.AccidentePersonaVehiculo;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaVehiculoId;

@Repository
public interface AccidentePersonaVehiculoDAO extends JpaRepository<AccidentePersonaVehiculo, AccidentePersonaVehiculoId>{
	List<AccidentePersonaVehiculo> findByAccidente(long accidenteId);
    List<AccidentePersonaVehiculo> findByPersona(long personaId);
	List<AccidentePersonaVehiculo> findByVehiculo(long vehiculoId);

	List<AccidentePersonaVehiculo> findByRol(String rol);
}