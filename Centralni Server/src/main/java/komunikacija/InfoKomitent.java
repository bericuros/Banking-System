/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class InfoKomitent implements Serializable {
    
    Integer idK;
    String naziv;
    String adresa;
    Integer idM;

    public InfoKomitent(Integer idK, String naziv, String adresa, Integer idM) {
        this.idK = idK;
        this.naziv = naziv;
        this.adresa = adresa;
        this.idM = idM;
    }

    public Integer getIdK() {
        return idK;
    }

    public void setIdK(Integer idK) {
        this.idK = idK;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Integer getIdM() {
        return idM;
    }

    public void setIdM(Integer idM) {
        this.idM = idM;
    }
    
}
