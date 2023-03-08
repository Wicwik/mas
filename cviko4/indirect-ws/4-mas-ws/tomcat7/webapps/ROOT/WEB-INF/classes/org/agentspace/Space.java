package org.agentspace;

public class Space {

	final static int MAX = 100;
	Block[] block = new Block[MAX];
	int count = 0;
	final static Object EMPTY = new Object();
	final static Space instance = new Space();
	
	private Space() {
	}

	public static Space getInstance() {	
 	  return instance;
	}
	
	public static double getTimestamp() {
		return (double) System.currentTimeMillis();
	}
	
	public int contains (String name) {
		for (int i=0; i<count; i++)
			if (block[i] != null) {
				String blockname = block[i].getName();
				if (blockname != null && blockname.equals(name)) return i;
			}
		return -1;
	}
	
	synchronized public void write (String name, Object value) {
		int i = contains(name);
		if (i != -1) {
			if ((block[i].getPriority() == 1.0) || !valid(i,getTimestamp()))
				block[i].setValue(value);
		}
		else block[count++] = new Block(name,value);
	}

	synchronized public void write (String name, Object value, float priority) {
		int i = contains(name);
		if (i != -1) {
			if ((block[i].getPriority() <= priority) || !valid(i,getTimestamp()))
				block[i].setValue(value);
		}
		else block[count++] = new Block(name,value);
	}

	synchronized public void write (String name, Object value, double validity) {
		int i = contains(name);
		if (i != -1) {
			if ((block[i].getPriority() == 1.0) || !valid(i,getTimestamp()))
				block[i].setValue(value,getTimestamp()+validity);
		}
		else block[count++] = new Block(name,value,getTimestamp()+validity);
	}

	synchronized public void write (String name, Object value, double validity, float priority) {
		int i = contains(name);
		if (i != -1) {
			if ((block[i].getPriority() <= priority) || !valid(i,getTimestamp()))
				block[i].setValue(value,getTimestamp()+validity,priority);
		}
		else block[count++] = new Block(name,value,getTimestamp()+validity,priority);
	}
	
	synchronized public Object read (String name) {
		int i = contains(name);
		if (i == -1) return null;
		else if (!valid(i,getTimestamp())) return null;
		else return block[i].getValue();
	}

	public boolean valid (int i, double timestamp) {
		if (i == -1) return false;
		else if (block[i].getValidity() == 0.0) return true;
		else if (block[i].getValidity() < timestamp) return false;
		else {
//			System.out.println("still valid " + block[i].getValidity() + " - " + timestamp + " = " + (block[i].getValidity() - timestamp));
			return true;
		}
	}
	
	public void registerTrigger (String name, Triggerable obj) {
		int i = contains(name);
		if (i == -1) {
			i = count;
			block[count++] = new Block(name,EMPTY,-1);
		}
		block[i].addTriggerable(obj);
	}

/*	public Block[] readAll (String mask) {
		Block[] ret;
		int length = 0;
		int i;
		for (i=0; i<count; i++)
			if (block[i].matchName(mask)) length++;
		ret = new Block[length];
		for (i=0; i<count; i++)
			if (block[i].matchName(mask)) 
				ret[i] = block[i];
		return ret;
	}
*/

/*
	public static void delay (int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ee) {
		}
	}
		
	public static void main(String[] args) {
		Space space1 = new Space();
		Space space2 = new Space();
		space1.write("ahoj","hoja");
		space2.write("leto","otel");
		System.out.println(space2.read("ahoj"));
		System.out.println(space1.read("leto"));
		space1.write("ahoj","hoja2");
		space2.write("leto","otel2");
		System.out.println(space1.read("ahoj"));
		System.out.println(space2.read("leto"));
//		Block[] all = space2.readAll("*");
//		int i;
//		for (i=0; i<all.length; i++) {
//			String s = (String) all[i].getValue();
//			System.out.print(s + ",");
//		}
//		System.out.println(); 
		System.out.println("----");
		space1.write("a","1",200);
		System.out.println((String) space1.read("a"));
		delay(100);
		System.out.println((String) space1.read("a"));
		delay(100);
		System.out.println((String) space1.read("a"));
		System.out.println("----");
		space1.write("a","x",200,2f);
		System.out.println((String) space1.read("a"));
		delay(100);
		space1.write("a","y",0,1f);
		System.out.println((String) space1.read("a"));
		delay(110);
		space1.write("a","y",0,1f);
		System.out.println((String) space1.read("a"));	
	}
*/

}
