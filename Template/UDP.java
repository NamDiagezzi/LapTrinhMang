package Template;

import java.io.*;
import java.util.*;
import java.net.*;

public class UDP {

    public static void main(String args[]) throws IOException{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sa = InetAddress.getByName("36.50.135.242");
        int sp = 2207;
        
        String msv = ";B23DCCN575;vflWbaup";
        DatagramPacket guiMa = new DatagramPacket(msv.getBytes(), msv.length(), sa, sp);
        socket.send(guiMa);
        
        byte[] buffer = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(nhan);
        
        String inp = new String(nhan.getData());
        String[] str = inp.trim().replace(';', ' ').replace(',', ' ').split("\\s+");
        
        String rId = str[0];
        int n = Integer.parseInt(str[1]);
        HashSet<Integer> hs = new HashSet<>();
        for (int i=2; i<str.length; i++) hs.add(Integer.parseInt(str[i]));
        
        String ans = rId + ";";
        for (int i=1; i<=n; i++){
            if (!hs.contains(i)){
                ans += i;
                if (i!=n) ans += ",";
            }
        }
        
        DatagramPacket guiKQ = new DatagramPacket(ans.getBytes(), ans.length(), sa, sp);
        socket.send(guiKQ);
        
        socket.close();
    }
}
