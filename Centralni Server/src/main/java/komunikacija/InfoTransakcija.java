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
public class InfoTransakcija implements Serializable {
    
    Integer idT;
    Integer idRSa;
    Integer idRNa;
    Integer idF;
    String tip;
    double iznos;
    String svrha;
    Integer redniBrojRacunSa;
    Integer redniBrojRacunNa;
    Date datumVreme;

    public InfoTransakcija(Integer idT, Integer idRSa, Integer idRNa, Integer idF, String tip, double iznos, String svrha, Integer redniBrojRacunSa, Integer redniBrojRacunNa, Date datumVreme) {
        this.idT = idT;
        this.idRSa = idRSa;
        this.idRNa = idRNa;
        this.idF = idF;
        this.tip = tip;
        this.iznos = iznos;
        this.svrha = svrha;
        this.redniBrojRacunSa = redniBrojRacunSa;
        this.redniBrojRacunNa = redniBrojRacunNa;
        this.datumVreme = datumVreme;
    }

    public Integer getIdT() {
        return idT;
    }

    public void setIdT(Integer idT) {
        this.idT = idT;
    }

    public Integer getIdRSa() {
        return idRSa;
    }

    public void setIdRSa(Integer idRSa) {
        this.idRSa = idRSa;
    }

    public Integer getIdRNa() {
        return idRNa;
    }

    public void setIdRNa(Integer idRNa) {
        this.idRNa = idRNa;
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

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public Integer getRedniBrojRacunSa() {
        return redniBrojRacunSa;
    }

    public void setRedniBrojRacunSa(Integer redniBrojRacunSa) {
        this.redniBrojRacunSa = redniBrojRacunSa;
    }

    public Integer getRedniBrojRacunNa() {
        return redniBrojRacunNa;
    }

    public void setRedniBrojRacunNa(Integer redniBrojRacunNa) {
        this.redniBrojRacunNa = redniBrojRacunNa;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }
    
}
