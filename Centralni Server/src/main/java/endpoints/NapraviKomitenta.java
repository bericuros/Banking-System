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

@Path("napravikomitenta")
public class NapraviKomitenta {
    
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
    
    @POST
    public Response napraviKomitenta(@FormParam("Naziv") String naziv, @FormParam("Adresa") String adresa, @FormParam("Mesto") String mesto) {
        try {
            JMSContext context = connFactory.createContext();
            
            ObjectMessage sendMsgP1 = context.createObjectMessage();
            sendMsgP1.setStringProperty("naziv", naziv);
            sendMsgP1.setStringProperty("adresa", adresa);
            sendMsgP1.setStringProperty("mesto", mesto);
            sendMsgP1.setStringProperty("zahtev", "3");
            
            Komunikacija komP1 = new Komunikacija(context, qCSP1, qP1CS, sendMsgP1);
            komP1.start();
            
            synchronized (komP1) {
                while (komP1.isActive()) {
                    komP1.wait();
                }
            }
            
            Paket paketP1 = (Paket)komP1.receiveMsg().getObject();
            
            if (paketP1.getValueString().equals("Komitent je uspesno kreiran!")) {
                ObjectMessage sendMsgP2 = context.createObjectMessage();
                sendMsgP2.setStringProperty("naziv", naziv);
                sendMsgP2.setStringProperty("adresa", adresa);
                sendMsgP2.setStringProperty("mesto", mesto);
                sendMsgP2.setStringProperty("zahtev", "3");
                
                Komunikacija komP2 = new Komunikacija(context, qCSP2, qP2CS, sendMsgP2);
                komP2.start();
                
                synchronized (komP2) {
                    while (komP2.isActive()) {
                        komP2.wait();
                    }
                }
                
                Paket paketP2 = (Paket)komP2.receiveMsg().getObject();
                return Response.status(Response.Status.CREATED).entity(paketP2.getValueString()).build();
            }
            
            return Response.status(Response.Status.CREATED).entity(paketP1.getValueString()).build();
        } catch (JMSException | InterruptedException ex) {
            Logger.getLogger(NapraviKomitenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.OK).entity("Doslo je do greske!").build();
    }
    
}
