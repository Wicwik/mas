import java.rmi.*;
import java.io.*;

public interface Space extends Remote {
    void write (Serializable key, Serializable value) throws RemoteException;
    Serializable read (Serializable key) throws RemoteException;
    void delete (Serializable key) throws RemoteException;
}