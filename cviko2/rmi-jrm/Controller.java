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
            System.setProperty("java.rmi.server.hostname", "192.168.100.21");
            Registry registry = LocateRegistry.getRegistry("192.168.100.21"); // on 1099
            System.out.println("REGISTRY FOUND");
            Space space = (Space) registry.lookup("//192.168.100.21:7171/SPACE");  // any port
            System.out.println("SPACE FOUND");

            System.out.println("blocking read");

            boolean run = (Boolean) space.blockingRead("run");

            while (run) {
                Float vx = (Float) space.read("vx");
                System.out.println("vx");
                Float vy = (Float) space.read("vy");
                System.out.println("vy");
                Float vz = (Float) space.read("vz");
                System.out.println("vz");
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
