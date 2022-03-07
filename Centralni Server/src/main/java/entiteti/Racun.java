/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "racun")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Racun.findAll", query = "SELECT r FROM Racun r"),
    @NamedQuery(name = "Racun.findByIdR", query = "SELECT r FROM Racun r WHERE r.idR = :idR"),
    @NamedQuery(name = "Racun.findByIdM", query = "SELECT r FROM Racun r WHERE r.idM = :idM"),
    @NamedQuery(name = "Racun.findByStatus", query = "SELECT r FROM Racun r WHERE r.status = :status"),
    @NamedQuery(name = "Racun.findByStanje", query = "SELECT r FROM Racun r WHERE r.stanje = :stanje"),
    @NamedQuery(name = "Racun.findByDozvoljenMinus", query = "SELECT r FROM Racun r WHERE r.dozvoljenMinus = :dozvoljenMinus"),
    @NamedQuery(name = "Racun.findByDatumVreme", query = "SELECT r FROM Racun r WHERE r.datumVreme = :datumVreme"),
    @NamedQuery(name = "Racun.findByBrojTransakcija", query = "SELECT r FROM Racun r WHERE r.brojTransakcija = :brojTransakcija")})
public class Racun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdR")
    private Integer idR;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdM")
    private int idM;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Stanje")
    private double stanje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DozvoljenMinus")
    private double dozvoljenMinus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatumVreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVreme;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BrojTransakcija")
    private int brojTransakcija;
    @OneToMany(mappedBy = "idRNa")
    private List<Transakcija> transakcijaList;
    @OneToMany(mappedBy = "idRSa")
    private List<Transakcija> transakcijaList1;
    @JoinColumn(name = "IdK", referencedColumnName = "IdK")
    @ManyToOne(optional = false)
    private Komitent idK;

    public Racun() {
    }

    public Racun(Integer idR) {
        this.idR = idR;
    }

    public Racun(Integer idR, int idM, String status, double stanje, double dozvoljenMinus, Date datumVreme, int brojTransakcija) {
        this.idR = idR;
        this.idM = idM;
        this.status = status;
        this.stanje = stanje;
        this.dozvoljenMinus = dozvoljenMinus;
        this.datumVreme = datumVreme;
        this.brojTransakcija = brojTransakcija;
    }

    public Integer getIdR() {
        return idR;
    }

    public void setIdR(Integer idR) {
        this.idR = idR;
    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getStanje() {
        return stanje;
    }

    public void setStanje(double stanje) {
        this.stanje = stanje;
    }

    public double getDozvoljenMinus() {
        return dozvoljenMinus;
    }

    public void setDozvoljenMinus(double dozvoljenMinus) {
        this.dozvoljenMinus = dozvoljenMinus;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public int getBrojTransakcija() {
        return brojTransakcija;
    }

    public void setBrojTransakcija(int brojTransakcija) {
        this.brojTransakcija = brojTransakcija;
    }

    @XmlTransient
    public List<Transakcija> getTransakcijaList() {
        return transakcijaList;
    }

    public void setTransakcijaList(List<Transakcija> transakcijaList) {
        this.transakcijaList = transakcijaList;
    }

    @XmlTransient
    public List<Transakcija> getTransakcijaList1() {
        return transakcijaList1;
    }

    public void setTransakcijaList1(List<Transakcija> transakcijaList1) {
        this.transakcijaList1 = transakcijaList1;
    }

    public Komitent getIdK() {
        return idK;
    }

    public void setIdK(Komitent idK) {
        this.idK = idK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idR != null ? idR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Racun)) {
            return false;
        }
        Racun other = (Racun) object;
        if ((this.idR == null && other.idR != null) || (this.idR != null && !this.idR.equals(other.idR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Racun[ idR=" + idR + " ]";
    }
    
}
