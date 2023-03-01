import java.io.*;
import java.util.Date;

public class ScenePos implements Serializable {

  public static final long serialVersionUID = 0L;
  
	public String name;
	public float x;
	public float y;
	public float z;

	ScenePos (String name, float x, float y, float z) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String toString() {
		return(name + "["+x+","+y+","+z+"]");
	}

}
