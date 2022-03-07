/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "transakcija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transakcija.findAll", query = "SELECT t FROM Transakcija t"),
    @NamedQuery(name = "Transakcija.findByIdT", query = "SELECT t FROM Transakcija t WHERE t.idT = :idT"),
    @NamedQuery(name = "Transakcija.findByIdF", query = "SELECT t FROM Transakcija t WHERE t.idF = :idF"),
    @NamedQuery(name = "Transakcija.findByTip", query = "SELECT t FROM Transakcija t WHERE t.tip = :tip"),
    @NamedQuery(name = "Transakcija.findByRedniBroj", query = "SELECT t FROM Transakcija t WHERE t.redniBroj = :redniBroj"),
    @NamedQuery(name = "Transakcija.findBySvrha", query = "SELECT t FROM Transakcija t WHERE t.svrha = :svrha"),
    @NamedQuery(name = "Transakcija.findByIznos", query = "SELECT t FROM Transakcija t WHERE t.iznos = :iznos"),
    @NamedQuery(name = "Transakcija.findByDatumVreme", query = "SELECT t FROM Transakcija t WHERE t.datumVreme = :datumVreme")})
public class Transakcija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdT")
    private Integer idT;
    @Column(name = "IdF")
    private Integer idF;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Tip")
    private String tip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RedniBroj")
    private int redniBroj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Svrha")
    private String svrha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Iznos")
    private double iznos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatumVreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVreme;
    @JoinColumn(name = "IdRNa", referencedColumnName = "IdR")
    @ManyToOne
    private Racun idRNa;
    @JoinColumn(name = "IdRSa", referencedColumnName = "IdR")
    @ManyToOne
    private Racun idRSa;

    public Transakcija() {
    }

    public Transakcija(Integer idT) {
        this.idT = idT;
    }

    public Transakcija(Integer idT, String tip, int redniBroj, String svrha, double iznos, Date datumVreme) {
        this.idT = idT;
        this.tip = tip;
        this.redniBroj = redniBroj;
        this.svrha = svrha;
        this.iznos = iznos;
        this.datumVreme = datumVreme;
    }

    public Integer getIdT() {
        return idT;
    }

    public void setIdT(Integer idT) {
        this.idT = idT;
    }

    public Integer getIdF() {
        return idF;
    }

    public void setIdF(Integer idF) {
        this.idF = idF;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Racun getIdRNa() {
        return idRNa;
    }

    public void setIdRNa(Racun idRNa) {
        this.idRNa = idRNa;
    }

    public Racun getIdRSa() {
        return idRSa;
    }

    public void setIdRSa(Racun idRSa) {
        this.idRSa = idRSa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idT != null ? idT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transakcija)) {
            return false;
        }
        Transakcija other = (Transakcija) object;
        if ((this.idT == null && other.idT != null) || (this.idT != null && !this.idT.equals(other.idT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Transakcija[ idT=" + idT + " ]";
    }
    
}
