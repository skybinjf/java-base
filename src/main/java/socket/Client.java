package socket;

import java.io.*;
import java.net.Socket;

/**
 * Socket 客户端类
 *
 * @author Sky
 * @date 2021-01-17 10:48.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);

        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("用户名：admin; 密码：123456");
        //pw.write("用户名：Sky; 密码：123456");
        pw.flush();
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info;
        while ((info = br.readLine()) != null) {
            System.out.println("我是客户端，服务端说：" + info);
        }

        is.close();
        br.close();

        os.close();;
        pw.close();
        socket.close();

    }

}
