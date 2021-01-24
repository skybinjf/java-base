package socket;

import java.io.*;
import java.net.Socket;

/**
 * 服务端线程处理类，实现一个服务器和多个客户端通信
 *
 * @author Sky
 * @date 2021-01-17 11:12.
 */
public class ServerThread extends Thread {

    // 和本线程相关的 socket
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    // 线程执行操作，响应客户端请求
    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            BufferedReader br = new BufferedReader(isr);
            String info;
            while ((info = br.readLine()) != null) {
                System.out.println("我是服务器，客户端说：" + info);
            }
            socket.shutdownInput();

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("欢迎您");
            pw.flush();

            pw.close();
            os.close();
            br.close();
            isr.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //pw.close();
            //os.close();
            //br.close();
            //isr.close();
            //is.close();
            //socket.close();
        }
    }
}
