import java.util.*;
import java.net.*;
import java.io.*;
import org.agentspace.*;

public abstract class Agent extends Thread {
	Object monitor;
	Timer timer;
	Space space;

	public Agent () {
		monitor = new Object();
		space = new LocalSpace("localhost");
        System.out.println(space);
        System.out.println("initialized");
		timer = null;
		// start(); musi sa volat v odvodenej triede
	}

	public void setTimer(int period) {   // to be called from init()
		if (timer == null) timer = new Timer(true);
		timer.scheduleAtFixedRate(
			new TimerTask() {
				public void run() {
					trigger();
				}
			},period,period
		);
	}
	
	abstract void init(); // to be overriden

	abstract void sense_select_act();  // to be overriden

	private int receive() {
		int ret = 0;
		synchronized(monitor) {
			try {
				monitor.wait();
			}
			catch (InterruptedException e) {
				ret = -1;
			}
		}
		return (ret);
	}

	public void trigger() {
		synchronized (monitor) {
			monitor.notifyAll();
		}
	}

	void main () {
		init();
		for (;;) {
			receive();
			sense_select_act();
		}
	}

	public void run () {
		main();
	}

	public Object read (String name, Object def) {
		Object value = space.read(name);
        if (value == null) return def;
        return value;
	}


	public void write (String name, Object value) {
		space.write(name,(Serializable)value);
	}

} 
