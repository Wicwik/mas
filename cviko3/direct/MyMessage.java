import java.io.*;
import java.util.Date;

public class MyMessage implements Serializable {

	String content;

	MyMessage (String content) {
		this.content = content;
	}

	public String toString() {
		return(content);
	}
  
}
