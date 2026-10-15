package Template;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPObject {

    public static void main(String args[]) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("36.50.135.242");
        int p = 2209;
        
        String msv = ";B23DCCN575;ioIZS6iD";
        DatagramPacket guiMa = new DatagramPacket(msv.getBytes(), msv.length(), ip, p);
        socket.send(guiMa);
        
        byte[] buff = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buff, buff.length);
        socket.receive(nhan);
        
        String reqId = new String(nhan.getData(), 0, 8);
        ByteArrayInputStream bais = new ByteArrayInputStream(nhan.getData(), 8, nhan.getLength()-8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        UDPObject x = (UDPObject)ois.readObject();
        
        //Xu ly java
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(x);
        oos.flush();
        
        byte[] buff1 = new byte[8+baos.size()];
        System.arraycopy(reqId.getBytes(), 0, buff1, 0, 8);
        System.arraycopy(baos.toByteArray(), 0, buff1, 8, baos.size());
        DatagramPacket gui = new DatagramPacket(buff1, buff1.length, ip, p);
        socket.send(gui);
        
        socket.close();
    }
}
