/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import entiteti.Mesto;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
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

@Path("napravimesto")
public class NapraviMesto {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    public ConnectionFactory connFactory;
    
    @Resource(lookup = "QP1CS_7")
    public Queue qP1CS;
    
    @Resource(lookup = "QCSP1_7")
    public Queue qCSP1;
    
    @POST
    public Response napraviMesto(@FormParam("PB") String pb, @FormParam("Naziv") String naziv) {
        try {
            Mesto mesto = new Mesto();
            mesto.setPb(pb);
            mesto.setNaziv(naziv);
            
            JMSContext context = connFactory.createContext();
            ObjectMessage sendMsg = context.createObjectMessage(mesto);
            sendMsg.setStringProperty("zahtev", "1");
            
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
            Logger.getLogger(NapraviMesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(Response.Status.OK).entity("ipak nista").build();
    }
    
}
