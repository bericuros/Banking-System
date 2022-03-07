/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import entiteti.Mesto;
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

@Path("dohvatimesta")
public class DohvatiMesta {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connFactory;
    
    @Resource(lookup = "QP1CS_7")
    private Queue qP1CS;
    
    @Resource(lookup = "QCSP1_7")
    private Queue qCSP1;
    
    @GET
    public Response dohvatiMesta() {
        try {
            JMSContext context = connFactory.createContext();
            ObjectMessage sendMsg = context.createObjectMessage();
            sendMsg.setStringProperty("zahtev", "10");
            
            Komunikacija kom = new Komunikacija(context, qCSP1, qP1CS, sendMsg);
            kom.start();
            
            synchronized (kom) {
                while (kom.isActive()) {
                    kom.wait();
                }
            }
            
            Paket paket = (Paket)kom.receiveMsg().getObject();
            List<Mesto> listaMesta = paket.getValueList();
            
            String returnString = "IdM\tPostanski broj\tNaziv\n";
            for (Mesto m : listaMesta) {
                returnString += m.getIdM().toString() + "\t" + m.getPb() + "\t" + m.getNaziv() + "\n";
            }
            
            return Response.status(Response.Status.OK).entity(returnString).build();
        } catch (JMSException | InterruptedException ex) {
            Logger.getLogger(DohvatiMesta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(Response.Status.BAD_REQUEST).entity("Doslo je do greske!").build();
    }
}
