import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPANames;
import java.io.*;
import java.util.*;

public class BallAgent extends Agent {
  
  private AID aid;
  private SceneLauncher scene;
  private ScenePos last = null;
  
  protected void setup() {
    scene = new SceneLauncher();
    delay(1500);
    aid = getAID();
    registerBall();
    addBehaviour( 
      new TickerBehaviour(this,200) {
        public void onTick () {
          System.out.println("tick 1");
          if (!scene.available()) return;
          ScenePos pos = new ScenePos(aid.getName(),scene.getPosX(),scene.getPosY(),scene.getPosZ());
          if (last == null) {
            last = pos;
            return;
          }
          if (last.x == pos.x && last.y == pos.y && last.z == pos.z) return;
          for (AID agent : findBalls()) {
            if (aid.compareTo(agent) == 0) continue;
            sendMessage(agent,pos);
          }
          last = pos;
        }  
      }
    );
    addBehaviour( 
      new TickerBehaviour (this,200) {
        public void onTick () {
          System.out.println("tick 2");
          if (!scene.available()) return;
          ScenePos pos = receiveMessage();
          if (pos != null) {
            scene.setPos(pos.x,pos.y,pos.z);
            last = pos;
          }
        }  
      }
    );    
  }
  DFAgentDescription dfd;
  void registerBall() {
    dfd = new DFAgentDescription();    
    ServiceDescription sd = new ServiceDescription();
    sd.setType("Ball"); 
    sd.setName(getName());
    //sd.setOwnership(getLocalName());
    dfd.addServices(sd);
    dfd.setName(aid);
    //dfd.addOntologies("Ball_Moving");
    try {
      DFService.register(this,dfd);
    } catch (FIPAException e) {
      System.err.println(getLocalName()+" registration with DF unsucceeded. Reason: "+e.getMessage());
    }
    System.out.println(getLocalName()+ " succeeded in registration with DF");
  }
  
  ArrayList<AID> findBalls() {
    DFAgentDescription dfd = new DFAgentDescription();  
    ServiceDescription sd = new ServiceDescription();
    sd.setType("Ball"); 
    dfd.addServices(sd);
    //SearchConstraints c = new SearchConstraints();
    //c.setMaxDepth(new Long(3));    
    ArrayList<AID> list = new ArrayList<AID>();
    try {
      DFAgentDescription[] result = DFService.search(this,dfd/*,c*/);
      if ((result != null) && (result.length > 0))
        for (int i=0; i<result.length; i++)
        	list.add(result[i].getName()); 
      System.out.println(getLocalName()+ " succeeded in search, "+result.length+" found");
    } catch(FIPAException ee){
    	System.err.println(getLocalName()+ " search failed "+ee.getMessage());
    }
    return list;
  }
  void sendMessage (AID recipient, ScenePos pos) {
    try {
      ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
      msg.addReceiver(recipient);
      msg.setContentObject(pos);
      msg.setLanguage("JavaSerialization");
      send(msg);
      System.out.println(getLocalName()+" sent message "+pos);

      //msg.setDefaultEnvelope();
      //msg.getEnvelope().setAclRepresentation(FIPANames.ACLCodec.BITEFFICIENT);
      //send(msg);
      //System.out.println(getLocalName()+" sent 1st msg with bit-efficient aclCodec "+msg);
      /*
      p = new MyMessage("hoja Jelka!");
      msg.setContent(p.toString());
      msg.setLanguage("StringLanguage");
      msg.setDefaultEnvelope(); //reset the envelope to default ACLCodec
      send(msg);
      System.out.println(getLocalName()+" sent 2nd msg "+msg);

      //msg.getEnvelope().setAclRepresentation(FIPANames.ACLCodec.XML); 
      //send(msg);
      //System.out.println(getLocalName()+" sent 2nd msg with xml aclCodec "+msg);
      */
    } catch (Exception ee) {
      ee.printStackTrace();
    } 
  }
  
  ScenePos receiveMessage () {
    try {
      //System.out.println(getLocalName()+" is waiting for a message");
      ACLMessage msg = receive(); 
      if (msg == null) return null;
      System.out.println(getLocalName()+ " rx msg"+msg); 
      
      if ("JavaSerialization".equals(msg.getLanguage())) {
  			ScenePos pos = (ScenePos) msg.getContentObject();
  			System.out.println(getLocalName()+ " read Java Object " + pos.getClass().getName() + pos);
  			return pos;
      } 
      else {
  	    System.out.println(getLocalName()+ " read Java String " + msg.getContent()); 
      }
  
    } catch(UnreadableException ee){
    	System.err.println(getLocalName()+ " catched exception "+ee.getMessage());
    }
    return null;
  }
  
  public static void delay (int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ee) {
    }
  }
  
    // ukončenie agenta
    protected void takeDown() {
      System.out.println("Agent " + getLocalName() + " sa ukončuje.");          
    try {
      DFService.deregister(this,dfd);
      doDelete();
    } catch (Exception e) {
      System.err.println(getLocalName()+" deregistration with DF unsucceeded. Reason: "+e.getMessage());
    }
  }
}
