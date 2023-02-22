import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;

public class Controller extends Thread {

    private DataInputStream in;
    private	DataOutputStream out;
    private Scene scene;

    public Controller (Scene scene) {
        this.scene = scene;
        start();
    }

    public void run () {
        System.out.println("START");
        try {
            //System.setProperty("java.rmi.server.hostname", "158.195.28.151");
            Registry registry = LocateRegistry.getRegistry("localhost"); // on 1099
            Space space = (Space) registry.lookup("SPACE"); // any port
            for (;;) {
                Float vx = (Float) space.read("vx");
                Float vy = (Float) space.read("vy");
                Float vz = (Float) space.read("vz");
                float x = scene.getPosX();
                float y = scene.getPosY();
                float z = scene.getPosZ();
                if (vx != null) x += vx.floatValue();
                if (vy != null) y += vy.floatValue();
                if (vz != null) z += vz.floatValue();
                scene.setPos(x,y,z);
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException ee) {
                }
            }
        } catch(Exception e) {
            System.out.println("probably space not present");
        }
    }

}
