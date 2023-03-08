package org.agentspace;

import java.io.*;

public interface Space {
	void write (Serializable key, Serializable value);
	Serializable read (Serializable key);
	void delete (Serializable key);
}
