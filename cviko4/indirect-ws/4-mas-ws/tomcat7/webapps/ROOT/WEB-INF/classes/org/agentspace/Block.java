package org.agentspace;

import java.util.*;

@SuppressWarnings("unchecked")
public class Block {
	
//	static private Log log = new Log("space.txt");
	
	private String name;
	private Object value;
	private double validity;
	private float priority;
	private Vector<Triggerable> objects;
//	private Cloner cloner = new Cloner();
	
	private void assign (Object value) {
		this.value = value;
//		log.log(" "+name+":="+value);
//		this.value = cloner.deepClone(value);
	}
	
	public Block (String name, Object value, double validity, float priority) {
		this.name = name;
		assign(value);
		this.validity = validity;
		this.priority = priority;
		this.objects = new Vector<Triggerable>();
	}

	public Block (String name, Object value, double validity) {
		this.name = name;
		assign(value);
		this.validity = validity;
		this.priority = 1.0f;
		this.objects = new Vector<Triggerable>();
	}

	public Block (String name, Object value) {
		this.name = name;
		assign(value);
		this.validity = 0.0;
		this.priority = 1.0f;
		this.objects = new Vector<Triggerable>();
	}
	
	public String getName() {
		return name;
	}

	public Object getValue() {
//		return cloner.deepClone(value);
//		log.log(" "+name+"->"+value);
		return value;
	}

	public double getValidity() {
		return validity;
	}
	
	public double getPriority() {
		return priority;
	}
	
	public void setValue1 (Object value) {
		assign(value);
		for (int t=0; t < objects.size(); t++) 
			objects.get(t).trigger();
	}

	public void setValue (Object value) {
		this.validity = 0.0;
		this.priority = 1.0f;
		setValue1(value);
	}

	public void setValue (Object value, double validity) {
		this.validity = validity;
		this.priority = 1.0f;
		setValue1(value);
	}

	public void setValue (Object value, double validity, float priority) {
		this.validity = validity;
		this.priority = priority;
		setValue1(value);
	}

	public boolean matchName (String mask) {
		return name.equals(mask);
	}
	
	public void addTriggerable (Triggerable obj) {
		if (!objects.contains(obj)) objects.add(obj);
	}

}
