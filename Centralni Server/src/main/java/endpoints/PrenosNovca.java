/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

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

@Path("prenosnovca")
public class PrenosNovca {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connFactory;
    
    @Resource(lookup = "QP2CS_7")
    private Queue qP2CS;
    
    @Resource(lookup = "QCSP2_7")
    private Queue qCSP2;
    
    @POST
    public Response prenosNovca(@FormParam("RacunSa") String racunSa, @FormParam("RacunNa") String racunNa,
            @FormParam("Iznos") String iznos, @FormParam("Svrha") String svrha) {
        try {
            JMSContext context = connFactory.createContext();
            
            ObjectMessage sendMsg = context.createObjectMessage();
            sendMsg.setStringProperty("racunsa", racunSa);
            sendMsg.setStringProperty("racunna", racunNa);
            sendMsg.setStringProperty("iznos", iznos);
            sendMsg.setStringProperty("svrha", svrha);
            sendMsg.setStringProperty("zahtev", "7");
            
            Komunikacija kom = new Komunikacija(context, qCSP2, qP2CS, sendMsg);
            kom.start();
            
            synchronized (kom) {
                while (kom.isActive()) {
                    kom.wait();
                }
            }
            
            Paket paket = (Paket)kom.receiveMsg().getObject();
            return Response.status(Response.Status.OK).entity(paket.getValueString()).build();
        } catch (JMSException | InterruptedException ex) {
            Logger.getLogger(PrenosNovca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Doslo je do greske!").build();
    }
    
}
