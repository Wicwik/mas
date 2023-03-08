public class Agent1 extends Agent {

    private float f=0.0f;
    
	public Agent1 () {
		start();
	}

	void init () {
		setTimer(2000);
	}

	void sense_select_act() {
        f+=0.1f;
		write("a",new Float(f));
		System.out.println("Agent1 writes a = "+f);
	}
    
    public static void main (String[] args) {
        new Agent1();
    }

}
