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
@IdClass(AccidentePersonaId.class)
public class AccidentePersona implements Serializable {

    @Id
    @ManyToOne
    private Accidente accidente;
    
    @Id
    @ManyToOne
    private Persona persona;
	
    @Enumerated(EnumType.STRING)
    private TipoPersona tipo; //VICTIMA o CAUSANTE
    private String gravedad;
    
    public AccidentePersona() {
    }

    public AccidentePersona(String gravedad, Accidente accidente, Persona persona) {
    	this.tipo = TipoPersona.NO_DETERMINADO;
    	this.gravedad = gravedad;
    	this.accidente = accidente;
    	this.persona = persona;
    }

    public AccidentePersona(TipoPersona tipo, String gravedad, Accidente accidente, Persona persona) {
    	this.tipo = tipo;
    	this.gravedad = gravedad;
    	this.accidente = accidente;
    	this.persona = persona;
    }
    
    public TipoPersona getTipo() {
        return tipo;
    }

    public void setTipo(TipoPersona tipo) {
        this.tipo = tipo;
    }
    
    public String getGravedad() {
        return gravedad;
    }
    
    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
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
    
	
    @Override
    public int hashCode() {
    	int hash = 2;
        hash = 53 * hash + Objects.hashCode(this.accidente);
        hash = 53 * hash + Objects.hashCode(this.persona);
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
        final AccidentePersona other = (AccidentePersona) obj;
        if ( !Objects.equals(this.accidente, other.accidente)) {
            return false ;
        }
        if ( !Objects.equals(this.persona, other.persona)) {
            return false ;
        }

        return true;
    }
}