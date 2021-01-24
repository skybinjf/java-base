package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解处理类
 *
 * @author Sky
 * @date 2021-01-24 09:16.
 */
public class ParseAnnotation {

    public static void main(String[] args) {
        // 1，使用类加载器加载类
        try {
            Class c = Class.forName("annotation.Child");
            // 2，找到类上面的注解
            boolean isClassExist = c.isAnnotationPresent(Description.class);
            if (isClassExist) {
                // 3，拿到注解实例
                Description description = (Description) c.getAnnotation(Description.class);
                System.out.println(description.value());
            }

            // 4，找到方法上的注解
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                boolean isMethodExist = method.isAnnotationPresent(Description.class);
                if (isMethodExist) {
                    Description d = method.getAnnotation(Description.class);
                    System.out.println(d.value());
                }
            }

            // 另外一种解析方法
            for (Method method : methods) {
                Annotation[] as = method.getAnnotations();
                for (Annotation a : as) {
                    if (a instanceof Description) {
                        System.out.println(((Description) a).value());
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
