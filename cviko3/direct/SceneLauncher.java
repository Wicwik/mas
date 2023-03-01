public class SceneLauncher extends Thread {

  private Scene scene;

  public SceneLauncher ()
  {
    scene = null;
    start();
  }

  public void run () {
    try {
      scene = new Scene();
	  	System.out.println("scene started");
  	} catch (Exception ee) {
	  	System.out.println("scene failed");
  	}
    scene.begin();
  }

  public void setPos (float x, float y, float z) {
  	if (scene == null) return;
		scene.setPos(x,y,z);
	}
	
	public boolean available() {
  	return (scene != null);
	}
	
	public float getPosX () {
  	if (scene == null) return 0.0f;
		return scene.getPosX();
	}
	
	public float getPosY () {
  	if (scene == null) return 0.0f;
		return scene.getPosY();
	}
	
	public float getPosZ () {
  	if (scene == null) return 0.0f;
		return scene.getPosZ();
	}
}