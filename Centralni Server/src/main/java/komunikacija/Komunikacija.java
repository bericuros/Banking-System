/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 *
 * @author pc
 */
public class Komunikacija extends Thread {
    
    private JMSContext context;
    private JMSProducer producer;
    private JMSConsumer consumer;
    private Queue cs_p;
    private Queue p_cs;
    private ObjectMessage sendMsg;
    private ObjectMessage receiveMsg;
    private Boolean isActive;
    
    public Komunikacija(JMSContext context, Queue cs_p, Queue p_cs, ObjectMessage sendMsg) {
        this.context = context;
        this.cs_p = cs_p;
        this.p_cs = p_cs;
        this.sendMsg = sendMsg;
        this.producer = context.createProducer();
        this.consumer = context.createConsumer(p_cs);
        this.isActive = true;
    }

    @Override
    public void run() {
        while (consumer.receiveNoWait() != null) {}
        producer.send(cs_p, sendMsg);
        receiveMsg = (ObjectMessage) consumer.receive();
        synchronized (this) {
            isActive = false;
            notify();
        }
    }
    
    public Boolean isActive() {
        return isActive;
    }
    
    public ObjectMessage receiveMsg() {
        return receiveMsg;
    }
    
}
