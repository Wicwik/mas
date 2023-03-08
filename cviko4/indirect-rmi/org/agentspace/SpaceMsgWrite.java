package org.agentspace;

import java.io.*;

public class SpaceMsgWrite extends SpaceMsg {
	static public final long serialVersionUID = 1000002;
	Serializable key;
	Serializable value;
	
	public SpaceMsgWrite() {
	}
	
	public SpaceMsgWrite (Serializable key, Serializable value) {
		this.key = key;
		this.value = value;
	}
	
}
