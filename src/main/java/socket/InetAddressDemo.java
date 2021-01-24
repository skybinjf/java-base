package socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddressDemo
 *
 * @author Sky
 * @date 2021-01-16 17:05.
 */
public class InetAddressDemo {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostName());
        System.out.println(address.getHostAddress());
        System.out.println(address);
    }
}
