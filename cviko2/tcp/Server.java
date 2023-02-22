import java.io.*;
import java.net.*;

public class Server extends Thread {
    static int i=0;

    private int j;
    private Socket socket;

    public Server (Socket socket) {
        j = ++i;
        this.socket = socket;
        System.out.println("thread "+j+" started");
        start();
    }

    public void run() {
        try {
            System.out.println("connection "+j+" accepted: " + socket);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("connected");
            for (;;) {
                float x = in.readFloat();
                float y = in.readFloat();
                float z = in.readFloat();
                x += 0.1f;
                System.out.println("TICK "+x);
                out.writeFloat(x);
                out.writeFloat(y);
                out.writeFloat(z);
                if (1 != 1) break; //dummy
            }
            System.out.println("closing connection "+j);
            socket.close();
        } catch (Exception ee) {
            System.out.println(ee);
        }
        System.out.println("thread "+j+" finished");
    }

    public static void main(String[] args) throws Exception {
        int port = 7171;
        try {
            ServerSocket s = new ServerSocket(port);
            System.out.println("Started listening on port "+port+" : " + s);
//			for (;;) {
            try {
                Socket socket = s.accept();
                new Server(socket);
            } catch (Exception ee) {
                System.out.println(ee);
//	  			break;
            }
//	  	}
            s.close();
        } catch (Exception ee) {
            System.out.println(ee);
        }
    }

}
