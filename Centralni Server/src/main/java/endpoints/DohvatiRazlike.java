/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import komunikacija.InfoFilijala;
import komunikacija.InfoKomitent;
import komunikacija.InfoMesto;
import komunikacija.InfoRacun;
import komunikacija.InfoTransakcija;
import komunikacija.Komunikacija;
import komunikacija.Paket;

/**
 *
 * @author pc
 */

@Path("dohvatirazlike")
public class DohvatiRazlike {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connFactory;
    
    @Resource(lookup = "QP1CS_7")
    private Queue qP1CS;
    
    @Resource(lookup = "QCSP1_7")
    private Queue qCSP1;
    
    @Resource(lookup = "QP2CS_7")
    private Queue qP2CS;
    
    @Resource(lookup = "QCSP2_7")
    private Queue qCSP2;
    
    @Resource(lookup = "QP3CS_7")
    private Queue qP3CS;
    
    @Resource(lookup = "QCSP3_7")
    private Queue qCSP3;
    
    @GET
    public Response dohvatiRazlike() {
        JMSContext context = connFactory.createContext();
            
        List<InfoMesto> listaInfoMestoP1 = dohvatiInformacije(context, qCSP1, qP1CS, "16m");
        List<InfoFilijala> listaInfoFilijalaP1 = dohvatiInformacije(context, qCSP1, qP1CS, "16f");
        List<InfoKomitent> listaInfoKomitentP1 = dohvatiInformacije(context, qCSP1, qP1CS, "16k");
        List<InfoRacun> listaInfoRacunP2 = dohvatiInformacije(context, qCSP2, qP2CS, "16r");
        List<InfoTransakcija> listaInfoTransakcijaP2 = dohvatiInformacije(context, qCSP2, qP2CS, "16t");
        
        List<InfoMesto> listaInfoMestoP3 = dohvatiInformacije(context, qCSP3, qP3CS, "16m");
        List<InfoFilijala> listaInfoFilijalaP3 = dohvatiInformacije(context, qCSP3, qP3CS, "16f");
        List<InfoKomitent> listaInfoKomitentP3 = dohvatiInformacije(context, qCSP3, qP3CS, "16k");
        List<InfoRacun> listaInfoRacunP3 = dohvatiInformacije(context, qCSP3, qP3CS, "16r");
        List<InfoTransakcija> listaInfoTransakcijaP3 = dohvatiInformacije(context, qCSP3, qP3CS, "16t");
        
        List<InfoMesto> notEqualMesto = new ArrayList<>();
        List<InfoFilijala> notEqualFilijala = new ArrayList<>();
        List<InfoKomitent> notEqualKomitent = new ArrayList<>();
        List<InfoRacun> notEqualRacun = new ArrayList<>();
        List<InfoTransakcija> notEqualTransakcija = new ArrayList<>();
        
        for (int i = 0; i < listaInfoMestoP3.size(); i++) {
            InfoMesto imP1 = listaInfoMestoP1.get(i);
            InfoMesto imP3 = listaInfoMestoP3.get(i);
            Boolean isEqual = imP1.getIdM().equals(imP3.getIdM()) && imP1.getPb().equals(imP3.getPb()) && imP1.getNaziv().equals(imP3.getNaziv());
            if (isEqual == false) notEqualMesto.add(imP1);
        }
        for (int i = listaInfoMestoP3.size(); i < listaInfoMestoP1.size(); i++) {
            notEqualMesto.add(listaInfoMestoP1.get(i));
        }
        
        for (int i = 0; i < listaInfoFilijalaP3.size(); i++) {
            InfoFilijala ifP1 = listaInfoFilijalaP1.get(i);
            InfoFilijala ifP3 = listaInfoFilijalaP3.get(i);
            Boolean isEqual = ifP1.getIdF().equals(ifP3.getIdF()) && ifP1.getNaziv().equals(ifP3.getNaziv())
                    && ifP1.getAdresa().equals(ifP3.getAdresa()) && ifP1.getIdM().equals(ifP3.getIdM());
            if (isEqual == false) notEqualFilijala.add(ifP1);
        }
        for (int i = listaInfoFilijalaP3.size(); i < listaInfoFilijalaP1.size(); i++) {
            notEqualFilijala.add(listaInfoFilijalaP1.get(i));
        }
        
        for (int i = 0; i < listaInfoKomitentP3.size(); i++) {
            InfoKomitent ikP1 = listaInfoKomitentP1.get(i);
            InfoKomitent ikP3 = listaInfoKomitentP3.get(i);
            Boolean isEqual = ikP1.getIdK().equals(ikP3.getIdK()) && ikP1.getNaziv().equals(ikP3.getNaziv())
                    && ikP1.getAdresa().equals(ikP3.getAdresa()) && ikP1.getIdM().equals(ikP3.getIdM());
            if (isEqual == false) notEqualKomitent.add(ikP1);
        }
        for (int i = listaInfoKomitentP3.size(); i < listaInfoKomitentP1.size(); i++) {
            notEqualKomitent.add(listaInfoKomitentP1.get(i));
        }
            
        for (int i = 0; i < listaInfoRacunP3.size(); i++) {
            InfoRacun irP2 = listaInfoRacunP2.get(i);
            InfoRacun irP3 = listaInfoRacunP3.get(i);
            Boolean isEqual = irP2.getIdR().equals(irP3.getIdR()) && irP2.getIdK().equals(irP3.getIdK())
                    && irP2.getIdM().equals(irP3.getIdM()) && irP2.getStatus().equals(irP3.getStatus())
                    && irP2.getStanje() == irP3.getStanje() && irP2.getDozvoljenMinus() == irP3.getDozvoljenMinus()
                    && irP2.getBrojTransakcija().equals(irP3.getBrojTransakcija()) && irP2.getDatumVreme().equals(irP3.getDatumVreme());
            if (isEqual == false) notEqualRacun.add(irP2);
        }
        for (int i = listaInfoRacunP3.size(); i < listaInfoRacunP2.size(); i++) {
            notEqualRacun.add(listaInfoRacunP2.get(i));
        }
        
        for (int i = 0; i < listaInfoTransakcijaP3.size(); i++) {
            InfoTransakcija itP2 = listaInfoTransakcijaP2.get(i);
            InfoTransakcija itP3 = listaInfoTransakcijaP3.get(i);
            Boolean isEqual = itP2.getIdT().equals(itP3.getIdT()) && itP2.getIznos() == itP3.getIznos()
                    && itP2.getDatumVreme().equals(itP3.getDatumVreme()) && itP2.getSvrha().equals(itP3.getSvrha())
                    && itP2.getTip().equals(itP3.getTip());
            if (isEqual == false) notEqualTransakcija.add(itP2);
        }
        for (int i = listaInfoTransakcijaP3.size(); i < listaInfoTransakcijaP2.size(); i++) {
            notEqualTransakcija.add(listaInfoTransakcijaP2.get(i));
        }
        
        StringBuilder sb = new StringBuilder();
        
        if (notEqualMesto.size() > 0) {
            sb.append("========== MESTO ==========\n");
            sb.append("IdM\tPB\tNaziv\n");
        }
        for (InfoMesto im : notEqualMesto) {
            sb.append(im.getIdM());
            sb.append("\t");
            sb.append(im.getPb());
            sb.append("\t");
            sb.append(im.getNaziv());
            sb.append("\n");
        }
        
        if (notEqualFilijala.size() > 0) {
            sb.append("\n========== FILIJALA ==========\n");
            sb.append("IdF\tNaziv\tAdresa\tIdM\n");
        }
        for (InfoFilijala iff : notEqualFilijala) {
            sb.append(iff.getIdF());
            sb.append("\t");
            sb.append(iff.getNaziv());
            sb.append("\t");
            sb.append(iff.getAdresa());
            sb.append("\t");
            sb.append(iff.getIdM());
            sb.append("\n");
        }
        
        if (notEqualKomitent.size() > 0) {
            sb.append("\n========== KOMITENT ==========\n");
            sb.append("IdK\tNaziv\tAdresa\tIdM\n");
        }
        for (InfoKomitent ik : notEqualKomitent) {
            sb.append(ik.getIdK());
            sb.append("\t");
            sb.append(ik.getNaziv());
            sb.append("\t");
            sb.append(ik.getAdresa());
            sb.append("\t");
            sb.append(ik.getIdM());
            sb.append("\n");
        }
        
        if (notEqualRacun.size() > 0) {
            sb.append("\n========== RACUN ==========\n");
            sb.append("IdR\tIdK\tIdM\tStatus\tStanje\tDozvoljenMinus\tBrojTransakcija\tDatumVreme\n");
        }
        for (InfoRacun ir : notEqualRacun) {
            sb.append(ir.getIdR());
            sb.append("\t");
            sb.append(ir.getIdK());
            sb.append("\t");
            sb.append(ir.getIdM());
            sb.append("\t");
            sb.append(ir.getStatus());
            sb.append("\t");
            sb.append(ir.getStanje());
            sb.append("\t");
            sb.append(ir.getDozvoljenMinus());
            sb.append("\t");
            sb.append(ir.getBrojTransakcija());
            sb.append("\t");
            sb.append(ir.getDatumVreme());
            sb.append("\n");
        }
        
        if (notEqualTransakcija.size() > 0) {
            sb.append("\n========== TRANSAKCIJA ==========\n");
            sb.append("IdT\tIdRSa\tIdRNa\tIdF\tTip\tIznos\tSvrha\tRedBrRSa\tRedBrRNa\tDatumVreme\n");
        }
        for (InfoTransakcija it : notEqualTransakcija) {
            sb.append(it.getIdT());
            sb.append("\t");
            sb.append(it.getIdRSa());
            sb.append("\t");
            sb.append(it.getIdRNa());
            sb.append("\t");
            sb.append(it.getIdF());
            sb.append("\t");
            sb.append(it.getTip());
            sb.append("\t");
            sb.append(it.getIznos());
            sb.append("\t");
            sb.append(it.getSvrha());
            sb.append("\t");
            sb.append(it.getRedniBrojRacunSa());
            sb.append("\t");
            sb.append(it.getRedniBrojRacunNa());
            sb.append("\t");
            sb.append(it.getDatumVreme());
            sb.append("\n");
        }
        
        if (notEqualMesto.size() == 0 && notEqualFilijala.size() == 0 && notEqualKomitent.size() == 0
                && notEqualRacun.size() == 0 && notEqualTransakcija.size() == 0) {
            sb.append("Original i kopija su jednaki!");
        }
        
        return Response.status(Response.Status.OK).entity(sb.toString()).build();
    }
    
    public static List dohvatiInformacije(JMSContext context, Queue qCSP, Queue qPCS, String type) {
        try {
            ObjectMessage sendMsg = context.createObjectMessage();
            sendMsg.setStringProperty("zahtev", type);
            
            Komunikacija kom = new Komunikacija(context, qCSP, qPCS, sendMsg);
            kom.start();
            
            synchronized (kom) {
                while (kom.isActive()) {
                    kom.wait();
                }
            }
            
            Paket paket = (Paket)kom.receiveMsg().getObject();
            return paket.getValueList();
        } catch (JMSException | InterruptedException ex) {
            Logger.getLogger(DohvatiRazlike.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
