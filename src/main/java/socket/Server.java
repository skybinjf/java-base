package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socker 服务类
 *
 * @author Sky
 * @date 2021-01-17 10:47.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器启动，等待客户端连接……");
        Socket socket = null;
        int count = 0;
        while (true) {
            socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket);
            // 启动线程
            serverThread.start();
            count ++;
            System.out.println("客户端数量：" + count);
            InetAddress address = socket.getInetAddress();
            System.out.println("当前客户端 IP：" + address.getHostAddress());
        }

        //InputStream inputStream = accept.getInputStream();
        //InputStreamReader isr = new InputStreamReader(inputStream);
        //
        //BufferedReader bufferedReader = new BufferedReader(isr);
        //String info;
        //while ((info = bufferedReader.readLine()) != null) {
        //    System.out.println("我是服务器，客户端说：" + info);
        //}
        //accept.shutdownInput();
        //
        //OutputStream os = accept.getOutputStream();
        //PrintWriter pw = new PrintWriter(os);
        //pw.write("欢迎您");
        //pw.flush();
        //
        //pw.close();
        //os.close();
        //bufferedReader.close();
        //isr.close();
        //inputStream.close();
        //accept.close();
        //serverSocket.close();
    }

}
