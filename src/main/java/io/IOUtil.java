package io;

import java.io.*;

/**
 * IO 工具类
 *
 * @author Sky
 * @date 2021-01-16 10:02.
 */
public class IOUtil {

    public static void main(String[] args) throws IOException {
        //IOUtil.printHex("E:\\workspace\\pom.xml");
        //IOUtil.printHexByByteArray("E:\\workspace\\pom.xml");
        //copyFile(new File("E:\\workspace\\pom.xml"), new File("E:\\workspace\\pom1.xml"));
        copyFileByBuffer(new File("E:\\workspace\\pom.xml"), new File("E:\\workspace\\pom2.xml"));
    }

    public static void printHex(String fileName) throws IOException {
        FileInputStream in = new FileInputStream(fileName);
        int b;
        int i = 1;
        while ((b = in.read()) != -1) {
            if (b <= 0xf) {
                System.out.print("0");
            }

            System.out.print(Integer.toHexString(b) + " ");
            if (i++ % 10 == 0) {
                System.out.println();
            }
        }
        in.close();

    }

    public static void printHexByByteArray(String fileName) throws IOException {
        FileInputStream in = new FileInputStream(fileName);
        byte[] buf = new byte[20 * 1024];
        int bytes = in.read(buf, 0, buf.length);
        int j = 1;
        for (int i = 0; i < bytes; i++) {
            if (buf[i] <= 0xf) {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(buf[i]) + " ");
            if (j++ % 10 == 0) {
                System.out.println();
            }

        }
    }

    /**
     * 拷贝文件
     * @param srcFile
     * @param desFile
     */
    public static void copyFile(File srcFile, File desFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("源文件" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件");
        }
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(desFile);

        byte[] buf = new byte[8 * 1024];
        int b;
        while ((b = in.read(buf, 0, buf.length)) != -1) {
            out.write(buf, 0, b);
            out.flush();
        }
        in.close();
        out.close();
    }

    /**
     * 使用缓冲拷贝文件
     * @param srcFile
     * @param destFile
     */
    public static void copyFileByBuffer(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("源文件" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件");
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));

        int c;
        while ((c = bis.read()) != -1) {
            bos.write(c);
            bos.flush();
        }
        bis.close();
        bos.close();

    }


}
