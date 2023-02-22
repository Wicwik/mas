import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

public class Agent {

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "158.195.28.155");
            Registry registry = LocateRegistry.getRegistry("158.195.28.155"); // on port 1099
            System.out.println("registry found");
            Space space = (Space) registry.lookup("//158.195.28.155:7171/SPACE"); // on port 7171
            System.out.println("stub created");
            System.out.println(space.read("a"));
            space.write("a","aaaa");
            System.out.println(space.read("a"));

            space.write("vx", 0.1f);
            space.write("vy", 0.0f);
            space.write("vz", 0.0f);
 
        } catch(Exception e) {
            System.out.println("probably space not present");
            System.out.println(e);
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
            e.printStackTrace();
        }
    }

}