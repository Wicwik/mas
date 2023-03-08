import java.io.*;

public class MySystem {
	
	public static int i=0;
	
	public static int calculate() {
		return i++;
	}
	
	public static void main(String[] args) {
		//start tomcat
		String[] params = {};
		org.apache.catalina.startup.Bootstrap.main(params);
		System.out.println(calculate());
		System.out.println(calculate());
		System.out.println(calculate());
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			br.readLine();
		} catch (IOException ee) {
		}
	}	
}