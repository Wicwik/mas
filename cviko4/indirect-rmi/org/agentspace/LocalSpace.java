package org.agentspace;

import java.net.*;
import java.io.*;

public class LocalSpace implements Space {

	static private int port = 7171;

	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	Socket socket = null;
	
	public LocalSpace (String ip) {
		try {
			InetAddress addr = InetAddress.getByName(ip);
			System.out.println("addr = " + addr);
			socket = new Socket(addr,port);
			System.out.println("socket = " + socket);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write (Serializable key, Serializable value) {
		try {
			out.writeObject(new SpaceMsgWrite(key,value));
			SpaceMsgResult res = (SpaceMsgResult) in.readObject();
			if (!res.success) System.out.println("unsuccessful write");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Serializable read (Serializable key) {    
		try {
			out.writeObject(new SpaceMsgRead(key));
			SpaceMsgResult res = (SpaceMsgResult) in.readObject();
			if (!res.success) {
				System.out.println("unsuccessful read");
				return null;
			}
			else return res.value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void delete (Serializable key) {
		try {
			out.writeObject(new SpaceMsgDelete(key));
			SpaceMsgResult res = (SpaceMsgResult) in.readObject();
			if (!res.success) System.out.println("unsuccessful delete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
		}
	}
	
	public static void main (String[] args) {
		LocalSpace space = new LocalSpace("localhost");
		space.write("a",new Float(123));
		System.out.println(space.read("a"));
	}
	
}