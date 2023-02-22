import java.io.*;
import java.net.*;

public class Controller extends Thread {

    private DataInputStream in;
    private	DataOutputStream out;
    private Scene scene;
    public static final int PORT = 7171;

    public Controller (Scene scene) {
        this.scene = scene;
        start();
    }

    public void run () {
        System.out.println("START");
        try {
            InetAddress addr = InetAddress.getByName("localhost");
            System.out.println("addr = " + addr);
            Socket socket = new Socket(addr,PORT);
            System.out.println("socket = " + socket);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception ee) {
        }
        for (;;) {
            try {
                out.writeFloat(scene.getPosX());
                out.writeFloat(scene.getPosY());
                out.writeFloat(scene.getPosZ());
                float x = in.readFloat();
                float y = in.readFloat();
                float z = in.readFloat();
                scene.setPos(x,y,z);
            } catch (Exception ee) {
            }
            try {
                sleep(250);
            } catch (InterruptedException ee) {
            }
        }
    }

}
