package es.uvigo.mei.accidentes.entidades;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import es.uvigo.mei.accidentes.entidades.Localizacion;
import es.uvigo.mei.accidentes.entidades.Multa;
import es.uvigo.mei.accidentes.entidades.AccidentePersonaId;


@Entity
@IdClass(AccidentePersonaVehiculoId.class)
public class AccidentePersonaVehiculo implements Serializable {

    @Id
    @ManyToOne
    private Accidente accidente;
    
    @Id
    @ManyToOne
    private Persona persona;
    
    @Id
    @ManyToOne
    private Vehiculo vehiculo;
	
    @Enumerated(EnumType.STRING)
    private RolAccidente rol;
    
    public AccidentePersonaVehiculo() {
    }

    public AccidentePersonaVehiculo(Accidente accidente, Persona persona, Vehiculo vehiculo) {
    	this.rol = RolAccidente.NO_DETERMINADO;
    	this.accidente = accidente;
    	this.persona = persona;
        this.vehiculo = vehiculo;
    }

    public AccidentePersonaVehiculo(RolAccidente rol, Accidente accidente, Persona persona, Vehiculo vehiculo) {
    	this.rol = rol;
    	this.accidente = accidente;
    	this.persona = persona;
        this.vehiculo = vehiculo;
    }
    
    public RolAccidente getRol() {
        return rol;
    }

    public void setRol(RolAccidente rol) {
        this.rol = rol;
    }
    
    
    public Accidente getAccidente() {
        return accidente;
    }
    
    public void setAccidente(Accidente accidente) {
        this.accidente = accidente;
    }
    
    public Persona getPersona() {
        return persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
	
    @Override
    public int hashCode() {
    	int hash = 2;
        hash = 53 * hash + Objects.hashCode(this.accidente);
        hash = 53 * hash + Objects.hashCode(this.persona);
        hash = 53 * hash + Objects.hashCode(this.vehiculo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccidentePersonaVehiculo other = (AccidentePersonaVehiculo) obj;
        if ( !Objects.equals(this.accidente, other.accidente)) {
            return false ;
        }
        if ( !Objects.equals(this.persona, other.persona)) {
            return false ;
        }

        if ( !Objects.equals(this.vehiculo, other.vehiculo)) {
            return false ;
        }
        return true;
    }
}