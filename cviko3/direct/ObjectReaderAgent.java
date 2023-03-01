import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.domain.DFService;

import java.io.*;

public class ObjectReaderAgent extends Agent {

    protected void setup() {

        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("ObjectReaderAgent");
        sd.setName(getName());
//  sd.setOwnership("Reader");
        dfd.addServices(sd);
        dfd.setName(getAID());
//  dfd.addOntologies("Test_Example");

        /*
          try {
            DFService.deregister(this,dfd);
          } catch (FIPAException e) {
          }
        */
        try {
            DFService.register(this,dfd);
        } catch (FIPAException e) {
            System.err.println(getLocalName()+" registration with DF unsucceeded. Reason: "+e.getMessage());
            doDelete();
        }
        System.out.println(getLocalName()+ " succeeded in registration with DF");

        for (;;) {
            try {

                System.out.println(getLocalName()+" is waiting for a message");
                ACLMessage msg = blockingReceive();
                System.out.println(getLocalName()+ " rx msg"+msg);

                if ("JavaSerialization".equals(msg.getLanguage())) {
                    MyMessage p = (MyMessage)msg.getContentObject();
                    System.out.println(getLocalName()+ " read Java Object " + p.getClass().getName() + p.toString());
                }
                else if ("StringLanguage".equals(msg.getLanguage())) {
                    System.out.println(getLocalName()+ " read Java String " + msg.getContent());
                }
                else {
                    System.out.println(getLocalName()+ " read other content " + msg.getContent());
                }

            } catch(UnreadableException e3) {
                System.err.println(getLocalName()+ " catched exception "+e3.getMessage());
            }
        }
    }

    public void takeDown() {
        try {
            DFService.deregister(this);
        }
        catch (FIPAException e) {
            System.err.println(getLocalName()+" deregistration with DF unsucceeded. Reason: "+e.getMessage());
        }
    }


}
