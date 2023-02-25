import java.io.*;

public class Gulka extends Thread implements Serializable {

	public static final long serialVersionUID = 112132444L;
	public int desc;
    public float v;

    public Gulka (int desc, float v) {
        this.v = v;
		this.desc = desc;
		start();
    }
	
	public void run() {
        for (int t=0; t<100; t++) {
			float myPos = Scene.getPosX(desc);
			float yourPos = Scene.getPosX(3-desc);
			
			if (Math.abs(yourPos - myPos) < 0.9) {
				v = -v;
			}
			
            Scene.setPos(desc,Scene.getPosX(desc)+v,Scene.getPosY(desc),Scene.getPosZ(desc));

			Scene.delay(100);
        }
	}
}
