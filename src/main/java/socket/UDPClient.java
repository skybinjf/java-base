package socket;

import java.io.IOException;
import java.net.*;

/**
 * UDP 客户端
 *
 * @author Sky
 * @date 2021-01-17 13:41.
 */
public class UDPClient {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 8888;
        byte[] data = "用户名：admin; 密码：123".getBytes();

        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);

        // 接收服务器响应的数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        socket.receive(packet2);
        String reply = new String(data2, 0, data2.length);
        System.out.println("我是客户端，服务器说：" + reply);

        socket.close();
    }

}
