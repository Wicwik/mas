import jade.lang.acl.ACLMessage;
import jade.core.Agent;
import jade.core.AID;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jamr.jadeacl.xml.*;
import jade.content.lang.sl.*;
import jade.content.lang.xml.*;
import jade.content.onto.*;

import java.util.*;
import java.io.*;

public class ObjectWriterAgent extends Agent {

protected void setup() {

  String txt = "\nAhoj svet!";
  //Object[] args = getArguments();
  //if (args.length > 0) txt = args[0].toString();
  
  AID reader = new AID();
  DFAgentDescription dfd = new DFAgentDescription();  
  ServiceDescription sd = new ServiceDescription();
  sd.setType("ObjectReaderAgent"); 
  dfd.addServices(sd);
  try {
    while (true) {
      System.out.println(getLocalName()+ " waiting for an ObjectReaderAgent registering with the DF");
//      SearchConstraints c = new SearchConstraints();
//      c.setMaxDepth(new Long(3));
      DFAgentDescription[] result = DFService.search(this,dfd/*,c*/);
      if ((result != null) && (result.length > 0)) {
				dfd = result[0]; 
				reader = dfd.getName();
				break;
    	}
      Thread.sleep(1000);
    }
  } catch (Exception fe) {
      fe.printStackTrace();
      System.err.println(getLocalName()+" search with DF is not succeeded because of " + fe.getMessage());
      doDelete();
  }

  System.out.println(getLocalName()+" agent sends ACLMessages whose content is a Java object");

   try {
      MyMessage p = new MyMessage(txt);

      ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
      msg.addReceiver(reader);

      msg.setContentObject(p);
      msg.setLanguage("JavaSerialization");
      send(msg);
      System.out.println(getLocalName()+" sent msg "+msg);

/*
      msg.setDefaultEnvelope();
      msg.setLanguage("StringLanguage");
      msg.setContent(p.toString());
      send(msg);
      System.out.println(getLocalName()+" sent msg with string codec "+msg);
*/

/*
			XMLCodec codec = new XMLCodec();
			Ontology onto = FIPAManagementOntology.getInstance();
      Float f = 3.1415927f; 
      msg.setLanguage(XMLCodec.NAME);
      msg.setOntology(onto.getName());
	    msg.setContent(codec.encodeObject(onto, f, true));
      send(msg);
      System.out.println(getLocalName()+" sent xml "+msg);
*/

  } catch (Exception e ) {
    e.printStackTrace();
  }
   //doDelete(); // kill itself because it has completed its task.
  }
}
