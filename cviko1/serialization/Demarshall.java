import java.io.*;

public class Demarshall {

    public static void main (String[] args) throws Exception {
        byte[] marshalledObject = {-84,-19,0,5,115,114,0,5,71,117,108,107,97,0,0,0,0,6,-81,1,92,2,0,5,70,0,2,100,12,0,70,0,2,100,121,70,0,6,114,97,100,105,117,115,70,0,1,120,70,0,1,121,120,112,0,0,0,0,0,0,0,0,63,-128,0,0,0,0,0,0,0,0,0,0};
        ByteArrayInputStream byteStream = new ByteArrayInputStream(marshalledObject);
        ObjectInputStream demarshaller = new ObjectInputStream(byteStream);
        Gulka g = (Gulka) demarshaller.readObject();
        System.out.println(g); // vypise 2.0[0.0,0.0]
    }

}