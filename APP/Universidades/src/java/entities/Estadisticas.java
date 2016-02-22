/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "estadisticas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadisticas.findAll", query = "SELECT e FROM Estadisticas e"),
    @NamedQuery(name = "Estadisticas.findByIdDato", query = "SELECT e FROM Estadisticas e WHERE e.idDato = :idDato"),
    @NamedQuery(name = "Estadisticas.findByAnio", query = "SELECT e FROM Estadisticas e WHERE e.anio = :anio"),
    @NamedQuery(name = "Estadisticas.findByIbe", query = "SELECT e FROM Estadisticas e WHERE e.ibe = :ibe"),
    @NamedQuery(name = "Estadisticas.findByLac", query = "SELECT e FROM Estadisticas e WHERE e.lac = :lac"),
    @NamedQuery(name = "Estadisticas.findByCo", query = "SELECT e FROM Estadisticas e WHERE e.co = :co"),
    @NamedQuery(name = "Estadisticas.findByO", query = "SELECT e FROM Estadisticas e WHERE e.o = :o"),
    @NamedQuery(name = "Estadisticas.findByIc", query = "SELECT e FROM Estadisticas e WHERE e.ic = :ic"),
    @NamedQuery(name = "Estadisticas.findByNi", query = "SELECT e FROM Estadisticas e WHERE e.ni = :ni"),
    @NamedQuery(name = "Estadisticas.findByQ1", query = "SELECT e FROM Estadisticas e WHERE e.q1 = :q1"),
    @NamedQuery(name = "Estadisticas.findBySpec", query = "SELECT e FROM Estadisticas e WHERE e.spec = :spec"),
    @NamedQuery(name = "Estadisticas.findByExc", query = "SELECT e FROM Estadisticas e WHERE e.exc = :exc"),
    @NamedQuery(name = "Estadisticas.findByLead", query = "SELECT e FROM Estadisticas e WHERE e.lead = :lead"),
    @NamedQuery(name = "Estadisticas.findByEwl", query = "SELECT e FROM Estadisticas e WHERE e.ewl = :ewl")})
public class Estadisticas implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "IC")
    private Float ic;
    @Column(name = "NI")
    private Float ni;
    @Column(name = "Q1")
    private Float q1;
    @Column(name = "SPEC")
    private Float spec;
    @Column(name = "EXC")
    private Float exc;
    @Column(name = "LEAD")
    private Float lead;
    @Column(name = "EWL")
    private Float ewl;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DATO")
    private Integer idDato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ANIO")
    private String anio;
    @Column(name = "IBE")
    private Integer ibe;
    @Column(name = "LAC")
    private Integer lac;
    @Column(name = "CO")
    private Integer co;
    @Column(name = "O")
    private Integer o;
    @JoinColumn(name = "ID_UNIVERSIDAD", referencedColumnName = "ID_UNIVERSIDAD")
    @ManyToOne
    private Universidades idUniversidad;

    public Estadisticas() {
    }

    public Estadisticas(Integer idDato) {
        this.idDato = idDato;
    }

    public Estadisticas(Integer idDato, String anio) {
        this.idDato = idDato;
        this.anio = anio;
    }

    public Integer getIdDato() {
        return idDato;
    }

    public void setIdDato(Integer idDato) {
        this.idDato = idDato;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Integer getIbe() {
        return ibe;
    }

    public void setIbe(Integer ibe) {
        this.ibe = ibe;
    }

    public Integer getLac() {
        return lac;
    }

    public void setLac(Integer lac) {
        this.lac = lac;
    }

    public Integer getCo() {
        return co;
    }

    public void setCo(Integer co) {
        this.co = co;
    }

    public Integer getO() {
        return o;
    }

    public void setO(Integer o) {
        this.o = o;
    }


    public Universidades getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Universidades idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDato != null ? idDato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadisticas)) {
            return false;
        }
        Estadisticas other = (Estadisticas) object;
        if ((this.idDato == null && other.idDato != null) || (this.idDato != null && !this.idDato.equals(other.idDato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Estadisticas[ idDato=" + idDato + " ]";
    }

    public Float getIc() {
        return ic;
    }

    public void setIc(Float ic) {
        this.ic = ic;
    }

    public Float getNi() {
        return ni;
    }

    public void setNi(Float ni) {
        this.ni = ni;
    }

    public Float getQ1() {
        return q1;
    }

    public void setQ1(Float q1) {
        this.q1 = q1;
    }

    public Float getSpec() {
        return spec;
    }

    public void setSpec(Float spec) {
        this.spec = spec;
    }

    public Float getExc() {
        return exc;
    }

    public void setExc(Float exc) {
        this.exc = exc;
    }

    public Float getLead() {
        return lead;
    }

    public void setLead(Float lead) {
        this.lead = lead;
    }

    public Float getEwl() {
        return ewl;
    }

    public void setEwl(Float ewl) {
        this.ewl = ewl;
    }
    
}
