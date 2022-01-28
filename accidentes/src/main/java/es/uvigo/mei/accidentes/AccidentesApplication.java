package es.uvigo.mei.accidentes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.uvigo.mei.accidentes.daos.AccidenteDAO;
import es.uvigo.mei.accidentes.daos.MultaDAO;
import es.uvigo.mei.accidentes.daos.PersonaDAO;
import es.uvigo.mei.accidentes.daos.VehiculoDAO;
import es.uvigo.mei.accidentes.daos.AccidentePersonaVehiculoDAO;
import es.uvigo.mei.accidentes.daos.AccidentePersonaDAO;
import es.uvigo.mei.accidentes.daos.AccidenteVehiculoDAO;

import es.uvigo.mei.accidentes.entidades.Accidente;
import es.uvigo.mei.accidentes.entidades.Multa;
import es.uvigo.mei.accidentes.entidades.Localizacion;
import es.uvigo.mei.accidentes.entidades.Persona;
import es.uvigo.mei.accidentes.entidades.Vehiculo;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaVehiculo;
import es.uvigo.mei.accidentes.entidades.AccidentePersona;
import es.uvigo.mei.accidentes.entidades.AccidenteVehiculo;


@SpringBootApplication
public class AccidentesApplication implements CommandLineRunner {

    @Autowired
    AccidenteDAO accidenteDAO;

    @Autowired
    MultaDAO multaDAO;

    @Autowired
    PersonaDAO personaDAO;

    @Autowired
    VehiculoDAO vehiculoDAO;

    @Autowired
    AccidentePersonaVehiculoDAO accidentePersonaVehiculoDAO;

    @Autowired
    AccidentePersonaDAO accidentePersonaDAO;

    @Autowired
    AccidenteVehiculoDAO accidenteVehiculoDAO;

	public static void main(String[] args) {
		SpringApplication.run(AccidentesApplication.class, args);
	}

    @Override
	public void run(String... args) throws Exception {
        Localizacion loc = new Localizacion("domicilio","localidad","codigopostal","provincia");
        Accidente ac1 = new Accidente(loc,"description1 description1 description1");                          
        Multa mu1 = new Multa("tipo1", "sancion1", ac1);
        Multa mu2 = new Multa("tipo2", "sancion2", ac1);

        Accidente ac2 = new Accidente(loc,"description2 description2 description2");                          
        Multa mu3 = new Multa("tipo3", "sancion3", ac2);
        Multa mu4 = new Multa("tipo4", "sancion4", ac2);

        Persona p1 = new Persona("name1","apellidos1 apellidos1","99999999B", loc);

        Persona p2 = new Persona("name2","apellidos2 apellidos2","99999990B", loc);

        Vehiculo v1 = new Vehiculo("moto1", "asdasd1", p1);
        Vehiculo v2 = new Vehiculo("moto2", "asdasd2", p2);

        //ac1.anadirMulta(mu1);
        //ac1.anadirMulta(mu2);

        /*AccidentePersonaVehiculo apv1 = new AccidentePersonaVehiculo(ac1, p1, v1);

        AccidentePersona ap1 = new AccidentePersona("gravedad2", ac1, p1);
        AccidenteVehiculo av1 = new AccidenteVehiculo("estado de vehiculo2", ac1, v1);*/

        accidenteDAO.save(ac1);
        multaDAO.save(mu1);
        multaDAO.save(mu2);
        accidenteDAO.save(ac2);
        multaDAO.save(mu3);
        multaDAO.save(mu4);
        personaDAO.save(p1);
        vehiculoDAO.save(v1);
        personaDAO.save(p2);
        vehiculoDAO.save(v2);
        
        /*accidentePersonaVehiculoDAO.save(apv1);
        accidentePersonaDAO.save(ap1);
        accidenteVehiculoDAO.save(av1);*/
    }
}
