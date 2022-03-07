/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import entiteti.Komitent;
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
import komunikacija.Komunikacija;
import komunikacija.Paket;

/**
 *
 * @author pc
 */

@Path("dohvatikomitente")
public class DohvatiKomitente {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connFactory;
    
    @Resource(lookup = "QP1CS_7")
    private Queue qP1CS;
    
    @Resource(lookup = "QCSP1_7")
    private Queue qCSP1;
    
    @GET
    public Response dohvatiKomitente() {
        try {
            JMSContext context = connFactory.createContext();
            ObjectMessage sendMsg = context.createObjectMessage();
            sendMsg.setStringProperty("zahtev", "12");
            
            Komunikacija kom = new Komunikacija(context, qCSP1, qP1CS, sendMsg);
            kom.start();
            
            synchronized (kom) {
                while (kom.isActive()) {
                    kom.wait();
                }
            }
            
            Paket paket = (Paket)kom.receiveMsg().getObject();
            List<Komitent> listaKomitenata = paket.getValueList();
            
            String returnString = "IdK\tNaziv\tAdresa\tIdM\n";
            for (Komitent k : listaKomitenata) {
                returnString += k.getIdK() + "\t" + k.getNaziv() + "\t" + k.getAdresa() + "\t" + k.getIdM().getIdM() + "\n";
            }
            
            return Response.status(Response.Status.OK).entity(returnString).build();
        } catch (JMSException | InterruptedException ex) {
            Logger.getLogger(DohvatiKomitente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Doslo je do greske!").build();
    }
    
}
