import java.io.*;
import java.net.*;
import org.agentspace.*;

public class Controller extends Thread {

	Space space;
	private Scene scene;

	public Controller (Scene scene) {
		this.scene = scene;
		space = new LocalSpace("192.168.100.21");
		start();
	}
	
	public void run () {
		System.out.println("START");	
		for (;;) {
			if (scene.mouse) {
				scene.mouse = false;
				float x = scene.getPosX();
                float y = scene.getPosY();
                float z = scene.getPosZ();

				space.write("x",new Float(x));
				space.write("y",new Float(y));
				space.write("z",new Float(z));

				continue;
			}

			float x = scene.getPosX();
			float y = scene.getPosY();
			float z = scene.getPosZ();
			Float xrem = (Float) space.read("x");
			Float yrem = (Float) space.read("y");
			Float zrem = (Float) space.read("z");
            System.out.println("Controller reads x = " + ((xrem == null)? "null" : ""+xrem));
			System.out.println("Controller reads y = " + ((yrem == null)? "null" : ""+yrem));
			System.out.println("Controller reads z = " + ((zrem == null)? "null" : ""+zrem));
			if (xrem != null) x = xrem.floatValue();
			if (yrem != null) y = yrem.floatValue();
			if (zrem != null) z = zrem.floatValue();

			System.out.println("x = " + x);
			System.out.println("y = " + y);
			System.out.println("z = " + z);
			scene.setPos(x,y,z);
			try {
				Thread.sleep(100);
			} catch (InterruptedException ee) {
    	}
		}
	}

}
