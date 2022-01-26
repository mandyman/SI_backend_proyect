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

@Entity
public class Accidente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String descripcion;
   
    private Localizacion localizacion;
    
    @OneToMany(mappedBy = "accidente", cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Multa> multas;
    
    public Accidente() {
    }

    public Accidente(Localizacion localizacion, String descripcion) {
    	this.localizacion = localizacion;
    	this.descripcion = descripcion;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Localizacion getLocalizacion() {
        return localizacion;
    }
    
    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }
    
    public List<Multa> getMultas() {
        return multas;
    }
    
    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }
    
    
    public void anadirMulta(Multa multanueva) {
        if (multas == null) {
            this.multas = new ArrayList<Multa>();
        }
        if (!this.multas.contains(multanueva)) {
            this.multas.add(multanueva);
        }
    }
    
    public void eliminarLineaPedidoInterno(Multa multanueva) {
        if (multas != null) {
            this.multas.remove(multanueva);
        }
    }
    

    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        } else {
            int hash = Objects.hashCode(this.descripcion);
            return hash;
        }
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
        final Accidente other = (Accidente) obj;
        if (this.id !=null) {
            return this.descripcion.equals(other.descripcion);
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
}