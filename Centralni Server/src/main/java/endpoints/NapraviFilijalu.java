/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import entiteti.Filijala;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import komunikacija.Komunikacija;
import komunikacija.Paket;

/**
 *
 * @author pc
 */

@Path("napravifilijalu")
public class NapraviFilijalu {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connFactory;
    
    @Resource(lookup = "QP1CS_7")
    private Queue qP1CS;
    
    @Resource(lookup = "QCSP1_7")
    private Queue qCSP1;
    
    @POST
    public Response napraviFilijalu(@FormParam("Naziv") String naziv, @FormParam("Adresa") String adresa, @FormParam("Mesto") String mesto) {
        try {
            Filijala filijala = new Filijala();
            filijala.setNaziv(naziv);
            filijala.setAdresa(adresa);
            
            JMSContext context = connFactory.createContext();
            ObjectMessage sendMsg = context.createObjectMessage(filijala);
            sendMsg.setStringProperty("mesto", mesto);
            sendMsg.setStringProperty("zahtev", "2");
            
            Komunikacija kom = new Komunikacija(context, qCSP1, qP1CS, sendMsg);
            kom.start();
            
            synchronized (kom) {
                while (kom.isActive()) {
                    kom.wait();
                }
            }
            
            Paket paket = (Paket)kom.receiveMsg().getObject();
            return Response.status(Response.Status.CREATED).entity(paket.getValueString()).build();
        } catch (JMSException | InterruptedException ex) {
            Logger.getLogger(NapraviFilijalu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(Response.Status.BAD_REQUEST).entity("Doslo je do greske!").build();
    }
    
}
