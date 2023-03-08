import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Properties;

public class WebService {

    private Socket socket;
    private InputStream in;
    private	PrintWriter out;
    private String url;
    private String ip;
    private int port;
    private String filename;
    private String userinfo;

    public WebService () {
        ip = "";
        socket = null;
    }

    public WebService (String url) throws MalformedURLException {
        set(url);
        socket = null;
    }

    public void open() throws IOException {
        if ("".equals(ip)) throw new IOException("no url specified");
        InetAddress addr = InetAddress.getByName(ip);
        socket = new Socket(addr,port);
        in = socket.getInputStream();
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }

    public void close() throws IOException {
        if (socket == null) throw new IOException("no socket opened");
        socket.close();
    }

    public void set (String url) throws MalformedURLException {
        this.url = url;
        URL u = new URL(url);
        ip = u.getHost();
        port = u.getPort();
        if (port == -1) port = 80;
        filename = u.getFile();
        userinfo = u.getUserInfo();
        System.out.println(ip+" "+port+" "+filename+" "+userinfo);
    }

    public Properties call () throws IOException {
        out.println("GET "+filename+" HTTP/1.1");
        if (userinfo != null) {
            out.print("Authorization: Basic ");
            out.print(Base64.getEncoder().encodeToString(userinfo.getBytes("utf-8")));
            out.println();
        }
        out.println("User-Agent: mas/1.0");
        out.println("Host: "+ip);
        out.println("Accept: */*");
        out.println();
        out.println();
        StringBuffer sb = new StringBuffer();
        int len = 0;
        int i = 0;
        for (;;) {
            int charRead = in.read();
            //System.out.print((char)charRead);
            if ((char) charRead == '\r') continue;
            if ((char) charRead == '\n') {
                //System.out.println();
                if (i == 0) break;
                i = 0;
                String line = sb.toString();
                if (line.length() >= 16 && "Content-Length:".equals(line.substring(0,15)))
                    len = Integer.parseInt(line.substring(16));
                sb = new StringBuffer();
                continue;
            }
            i++;
            if (i > 2048) {
                i = 0;
                sb = new StringBuffer();
            }
            else sb.append((char) charRead);

        }
        sb = new StringBuffer();
        //System.out.println("--- len: "+len);
        if (len <= 2048)
            for (i=0; i<len; i++) {
                int charRead = in.read();
                sb.append((char) charRead);
            }
        //System.out.println(sb.toString());
        StringReader sr = new StringReader(sb.toString().replace("<BR>",""));
        Properties properties = new Properties();
        properties.load(sr);
        for (String name : properties.stringPropertyNames()) {
            if ("null".equals(properties.getProperty(name)))
                properties.setProperty(name,"");
            //System.out.println(name+"..."+properties.getProperty(name));
        }
        return properties;
    }

    /*
    	public static void main (String[] args) throws Exception {
      	WebService ws = new WebService("http://www.agentspace.org:8080/read?x1&y1&z1");
      	//WebService ws = new WebService("http://www.agentspace.org:8080/write?x1=1&y1=1&z1=1");
      	ws.open();
      	System.out.println(ws.call());
      	System.out.println(ws.call());
      	ws.close();
    	}
    */
}
