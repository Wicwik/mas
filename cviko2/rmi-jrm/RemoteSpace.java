import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class RemoteSpace implements Space {

    static final Map<Serializable,Serializable> h = Collections.synchronizedMap(new TreeMap<Serializable,Serializable>());
    static public int verbose = 0;

    public void write (Serializable key, Serializable value) throws RemoteException {
        h.put(key,value);
        System.out.println("\nwrite  " + key + " = " + value + this);
    }

    public Serializable read (Serializable key) throws RemoteException {
        Serializable obj = h.get(key);
        System.out.println("\nread   " + key + " = " + obj + this);
        return obj;
    }

    public Serializable blockingRead (Serializable key) throws RemoteException {
        for (;;) {
            if (h.containsKey(key)) {
                Serializable obj = h.get(key);
                System.out.println("\nblockingRead   " + key + " = " + obj + this);
                return obj;
            }
            else {
                try {
                    System.out.println("\nblockingRead sleep...");
                    Thread.sleep(100);
                } catch (InterruptedException ee) {
                }
            }
        }
    }


    public void delete (Serializable key) throws RemoteException {
        h.remove(key);
        System.out.println("\ndelete " + key + this);
    }

    public String toString() {
        String str = new String("\nSpace contains:\n");
        for (Map.Entry<Serializable,Serializable> item : h.entrySet())
            str += item.getKey().toString() + " = " + item.getValue().toString() + "\n";
        return str;
    }

    public RemoteSpace() throws RemoteException {
    }

    public static void main(String args[]) {
		System.setProperty("java.rmi.server.hostname", "192.168.100.21");
		System.out.println("preferred server ip set");
        try {
            RemoteSpace obj = new RemoteSpace();
            System.out.println("distributed object created");

            // Space stub = (Space) UnicastRemoteObject.exportObject(obj, 0);  // on any free port
			Space stub = (Space) UnicastRemoteObject.exportObject(obj, 7171); // on port 7171
            System.out.println("skeleton created");

            Registry registry = LocateRegistry.getRegistry();
            System.out.println("registry created");

            // registry.rebind("SPACE", stub);
			registry.rebind("//192.168.100.21:7171/SPACE", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server failed");
        }
    }

}

