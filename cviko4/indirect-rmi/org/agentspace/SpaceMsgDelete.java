package org.agentspace;

import java.io.*;

public class SpaceMsgDelete extends SpaceMsg {
	static public final long serialVersionUID = 1000003;
	Serializable key;

	public SpaceMsgDelete() {
	}
	
	public SpaceMsgDelete (Serializable key) {
		this.key = key;
	}
	
}
