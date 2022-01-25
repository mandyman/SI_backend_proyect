package es.uvigo.mei.accidentes.entidades;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import es.uvigo.mei.accidentes.entidades.Localizacion;
import es.uvigo.mei.accidentes.entidades.Multa;


public class AccidentePersonaId implements Serializable {

    private Long accidente;
    private Long persona;
    
    public AccidentePersonaId() {
    	super();
    }

    public AccidentePersonaId( Long accidente, Long persona) {
    	super();
    	this.accidente = accidente;
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
        final AccidentePersonaId other = (AccidentePersonaId) obj;
        if ( !Objects.equals(this.accidente, other.accidente)) {
            return false ;
        }
        if ( !Objects.equals(this.persona, other.persona)) {
            return false ;
        }

        return true;
    }
}