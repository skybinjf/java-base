package io;

import java.io.*;

/**
 * 对象序列化
 *
 * @author Sky
 * @date 2021-01-16 16:26.
 */
public class ObjectSeriaDemo1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = "ojb.dat";
        // 1, 对象的序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        Student stu = new Student("10001", "张三", 20);
        oos.writeObject(stu);

        oos.flush();
        oos.close();

        // 2, 对象的反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Student student = (Student) ois.readObject();
        System.out.println(student.toString());
        ois.close();
    }

}
