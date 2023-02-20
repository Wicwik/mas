import java.io.*;

public class Marshall {

    public static void main (String[] args) throws Exception {
        Gulka g = new Gulka(1.0f,0.0f,0.0f,0.0f,0.0f);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream marshaller = new ObjectOutputStream(byteStream);
        marshaller.writeObject(g);
        byte[] bytes = byteStream.toByteArray();
        System.out.println(bytes.length);
        for (int i=0; i<bytes.length; i++) System.out.print(bytes[i]+" ");
        System.out.println();
    }

}