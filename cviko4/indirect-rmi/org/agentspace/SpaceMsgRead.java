package org.agentspace;

import java.io.*;

public class SpaceMsgRead extends SpaceMsg {
	static public final long serialVersionUID = 1000001;
	Serializable key;

	public SpaceMsgRead() {
	}
	
	public SpaceMsgRead (Serializable key) {
		this.key = key;
	}
	
}
