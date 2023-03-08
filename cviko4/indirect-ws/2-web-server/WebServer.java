import java.io.*;
import java.net.*;

public class WebServer extends Thread {

    private Socket socket;

    public WebServer (Socket socket) {
        this.socket = socket;
        start();
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("connected");
            for (;;) {
                String str = in.readLine();
                if ("".equals(str)) {
                    String htmlcode="<html><body>Ahoj!</body></html>";
                    out.println("HTTP/1.1 200 OK");
                    out.println("Date: Mon, 05 Mar 2018 21:33:01 GMT");
                    out.println("Server: Apache/2.4.25 (Debian)");
                    out.println("Last-Modified: Mon, 19 Sep 2016 15:36:08 GMT");
                    out.println("ETag: \"29cd-53cde122cb597\"");
                    out.println("Accept-Ranges: bytes");
                    out.println("Content-Length: "+htmlcode.length());
                    out.println("Content-Type: text/html");
                    out.println();
                    out.println(htmlcode);
                    out.println();
                }
                if (1 != 1) break; //dummy
            }
            System.out.println("closing connection");
            socket.close();
        } catch (Exception ee) {
            System.out.println(ee);
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 80;
        try {
            ServerSocket s = new ServerSocket(port);
            System.out.println("Started listening on port "+port+" : " + s);
            for (;;) {
                try {
                    Socket socket = s.accept();
                    new WebServer(socket);
                } catch (Exception ee) {
                    System.out.println(ee);
                    break;
                }
            }
            s.close();
        } catch (Exception ee) {
            System.out.println(ee);
        }
    }

}
