/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "universidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Universidades.findAll", query = "SELECT u FROM Universidades u"),
    @NamedQuery(name = "Universidades.findByIdUniversidad", query = "SELECT u FROM Universidades u WHERE u.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "Universidades.findByUniversidad", query = "SELECT u FROM Universidades u WHERE u.universidad = :universidad"),
    @NamedQuery(name = "Universidades.findByLatitud", query = "SELECT u FROM Universidades u WHERE u.latitud = :latitud"),
    @NamedQuery(name = "Universidades.findByLongitud", query = "SELECT u FROM Universidades u WHERE u.longitud = :longitud")})
public class Universidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "ID_UNIVERSIDAD")
    private String idUniversidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "UNIVERSIDAD")
    private String universidad;
    @Size(max = 25)
    @Column(name = "LATITUD")
    private String latitud;
    @Size(max = 25)
    @Column(name = "LONGITUD")
    private String longitud;
    @OneToMany(mappedBy = "idUniversidad")
    private Collection<Estadisticas> estadisticasCollection;
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS")
    @ManyToOne
    private Paises idPais;

    public Universidades() {
    }

    public Universidades(String idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public Universidades(String idUniversidad, String universidad) {
        this.idUniversidad = idUniversidad;
        this.universidad = universidad;
    }

    public String getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(String idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @XmlTransient
    public Collection<Estadisticas> getEstadisticasCollection() {
        return estadisticasCollection;
    }

    public void setEstadisticasCollection(Collection<Estadisticas> estadisticasCollection) {
        this.estadisticasCollection = estadisticasCollection;
    }

    public Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(Paises idPais) {
        this.idPais = idPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUniversidad != null ? idUniversidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universidades)) {
            return false;
        }
        Universidades other = (Universidades) object;
        if ((this.idUniversidad == null && other.idUniversidad != null) || (this.idUniversidad != null && !this.idUniversidad.equals(other.idUniversidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Universidades[ idUniversidad=" + idUniversidad + " ]";
    }
    
}
