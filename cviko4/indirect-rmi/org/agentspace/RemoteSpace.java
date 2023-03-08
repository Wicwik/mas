package org.agentspace;

import java.net.*;
import java.util.*;
import java.io.*;

public class RemoteSpace extends Thread implements Space {

	static private int port = 7171;
	
	static final Map h = Collections.synchronizedMap(new TreeMap());

	public void write (Serializable key, Serializable value) {
		h.put(key,value);
		System.out.println("write  " + key + " = " + value/* + this*/);
	}

	public Serializable read (Serializable key) {    
		Serializable obj = (Serializable) h.get(key);
		System.out.println("read   " + key + " = " + obj/* + this*/);
		return obj;
	}

	public void delete (Serializable key) {    
		h.remove(key);
		System.out.println("delete " + key/* + this*/);
	}
	
	public String toString() {
		String str = new String("\nSpace contains:\n");
		Iterator it = ((h.entrySet()).iterator());
		if (!it.hasNext())str += "empty";
		else while (it.hasNext()) str += (it.next()).toString() + "\n";
		return str;
	}
  
	private Socket socket;

	public RemoteSpace (Socket socket) {
		this.socket = socket;
		start();
	}

	public void run() {
		ObjectInputStream in;
		ObjectOutputStream out;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			return;
		}
		for (;;) {
			try {
				SpaceMsg msg = (SpaceMsg) in.readObject();
				SpaceMsg msg2 = codelet(msg);
				out.writeObject(msg2);
			} catch (Exception e) {
				break;
			}
		}
		System.out.println("client finished "+socket);
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
		} 
	}
	
	SpaceMsg codelet (SpaceMsg request) {
		if (request instanceof SpaceMsgWrite) {
			SpaceMsgWrite req = (SpaceMsgWrite) request;
			write(req.key,req.value);
			return new SpaceMsgResult(true);
		}
		else if (request instanceof SpaceMsgRead) {
			SpaceMsgRead req = (SpaceMsgRead) request;
			return new SpaceMsgResult(read(req.key));
		}
		else if (request instanceof SpaceMsgDelete) {
			SpaceMsgDelete req = (SpaceMsgDelete) request;
			delete(req.key);
			return new SpaceMsgResult(true);
		}
		else return new SpaceMsgResult(false);
	}

	public static void main(String[] args) {
		ServerSocket s;
		try {
			s = new ServerSocket(port);
		} catch (Exception e) {
			return;
		} finally {
			System.out.println("Started");
		}

		for (;;) {
			try {
				Socket socket = s.accept();
				System.out.println("new client " + socket);
				new RemoteSpace(socket);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}

		try {
			s.close();
		} catch (Exception e) {
		} 
		System.out.println("Finished");
	}
  
}