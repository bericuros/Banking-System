/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pc
 */
public class InfoRacun implements Serializable {
    
    Integer idR;
    Integer idK;
    Integer idM;
    String status;
    double stanje;
    double dozvoljenMinus;
    Integer brojTransakcija;
    Date datumVreme;

    public InfoRacun(Integer idR, Integer idK, Integer idM, String status, double stanje, double dozvoljenMinus, Integer brojTransakcija, Date datumVreme) {
        this.idR = idR;
        this.idK = idK;
        this.idM = idM;
        this.status = status;
        this.stanje = stanje;
        this.dozvoljenMinus = dozvoljenMinus;
        this.brojTransakcija = brojTransakcija;
        this.datumVreme = datumVreme;
    }

    public Integer getIdR() {
        return idR;
    }

    public void setIdR(Integer idR) {
        this.idR = idR;
    }

    public Integer getIdK() {
        return idK;
    }

    public void setIdK(Integer idK) {
        this.idK = idK;
    }

    public Integer getIdM() {
        return idM;
    }

    public void setIdM(Integer idM) {
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

    public Integer getBrojTransakcija() {
        return brojTransakcija;
    }

    public void setBrojTransakcija(Integer brojTransakcija) {
        this.brojTransakcija = brojTransakcija;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }
    
}
