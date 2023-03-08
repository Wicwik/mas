import java.util.Properties;

public class Agent2 extends Agent {

    private int i = 0;
    private WebService read;
    private WebService write;

    private float atof (String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception ee) {
            return 0.0f;
        }
    }

    public Agent2 () {
        start();
    }

    void init () {
        setTimer(250);
    }

    void sense_select_act() {
        try {
            read = new WebService("http://www.agentspace.org:8080/read?x_GM_tmp&y_GM_tmp&z_GM_tmp");
            read.open();
            Properties p = read.call();
            float x = atof(p.getProperty("x_GM_tmp"));
            float y = atof(p.getProperty("y_GM_tmp"));
            float z = atof(p.getProperty("z_GM_tmp"));
            System.out.println(x+" "+y+" "+z);
            Main.scene.setPos(x,y,z);
            read.close();
            return;
        }	catch (Exception ee) {
            //ee.printStackTrace();
        }
    }

}
