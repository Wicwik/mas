import java.io.*;
import java.net.*;
import org.agentspace.*;

public class Controller extends Thread {

	Space space;
	private Scene scene;

	public Controller (Scene scene) {
		this.scene = scene;
		space = new LocalSpace("localhost");
		start();
	}
	
	public void run () {
		System.out.println("START");	
		for (;;) {
			float x = scene.getPosX();
			float y = scene.getPosY();
			float z = scene.getPosZ();
			Float f = (Float) space.read("a");
            System.out.println("Controller reads a = " + ((f == null)? "null" : ""+f));
			if (f != null) x = f.floatValue();
			scene.setPos(x,y,z);
			try {
				Thread.sleep(100);
			} catch (InterruptedException ee) {
    	}
		}
	}

}
