package org.agentspace;

import java.io.*;

public class SpaceMsgResult extends SpaceMsg {
	static public final long serialVersionUID = 1000004;
	boolean success;
	Serializable value;

	public SpaceMsgResult() {
	}
	
	public SpaceMsgResult (boolean success) {
		this.success = success;
	}

	public SpaceMsgResult (Serializable value) {
		this.success = true;
		this.value = value;
	}
	
}
