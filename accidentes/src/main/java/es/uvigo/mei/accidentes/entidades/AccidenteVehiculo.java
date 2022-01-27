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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import es.uvigo.mei.accidentes.entidades.Localizacion;
import es.uvigo.mei.accidentes.entidades.Multa;
import es.uvigo.mei.accidentes.entidades.AccidenteVehiculoId;


@Entity
@IdClass(AccidenteVehiculoId.class)
public class AccidenteVehiculo implements Serializable {

    @Id
    @ManyToOne
    @JsonIgnoreProperties("multas")
    private Accidente accidente;
    
    @Id
    @ManyToOne
    @JsonIgnoreProperties("conductor")
    private Vehiculo vehiculo;
	
    private String vehiculoEstado; //Estado en que queda el vehiculo despues del accidente
    
    public AccidenteVehiculo() {
    }

    public AccidenteVehiculo(String vehiculoEstado, Accidente accidente, Vehiculo vehiculo) {
    	this.vehiculoEstado = vehiculoEstado;
    	this.accidente = accidente;
    	this.vehiculo = vehiculo;
    }
    
    public String getVehiculoEstado() {
        return vehiculoEstado;
    }

    public void setVehiculoEstado(String vehiculoEstado) {
        this.vehiculoEstado = vehiculoEstado;
    }
    
    public Accidente getAccidente() {
        return accidente;
    }
    
    public void setAccidente(Accidente accidente) {
        this.accidente = accidente;
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
        final AccidenteVehiculo other = (AccidenteVehiculo) obj;
        if ( !Objects.equals(this.accidente, other.accidente)) {
            return false ;
        }
        if ( !Objects.equals(this.vehiculo, other.vehiculo)) {
            return false ;
        }

        return true;
    }
}