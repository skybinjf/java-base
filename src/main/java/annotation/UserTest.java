package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 测试实体注解类，模拟数据库映射
 *
 * @author Sky
 * @date 2021-01-24 11:17.
 */
public class UserTest {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(10);

        User user2 = new User();
        user2.setUserName("Sky");
        user2.setAge(20);

        User user3 = new User();
        user3.setEmail("sky@163.com,sky@qq.com,sky@gmail.com");

        String sql1 = query(user1);
        String sql2 = query(user2);
        String sql3 = query(user3);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);

    }

    private static String query(User user) {
        StringBuilder sb = new StringBuilder();
        // 1,获取到 class
        Class userClass = user.getClass();
        // 2，获取 table 的名字
        boolean isExist = userClass.isAnnotationPresent(Table.class);
        if (!isExist) {
            return null;
        }
        Table table = (Table) userClass.getAnnotation(Table.class);
        String tableName = table.value();
        sb.append("SELECT * FROM ").append(tableName).append(" WHERE 1=1 ");
        // 3，遍历所有的字段
        Field[] fArray = userClass.getDeclaredFields();
        for (Field field : fArray) {
            // 4，处理每个字段对应的 sql
            // 4.1，拿到字段名
            boolean fExist = field.isAnnotationPresent(Column.class);
            if (!fExist) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            // 4.2，拿到字段的值
            String filedName = field.getName();
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
            Object fieldValue = null;
            try {
                Method getMethod = userClass.getMethod(getMethodName);
                fieldValue = getMethod.invoke(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 4.3，拼装 sql
            if (fieldValue == null || (fieldValue instanceof Integer && (Integer) fieldValue == 0)) {
                continue;
            }
            sb.append(" AND ").append(columnName);
            if (fieldValue instanceof String) {
                if (((String) fieldValue).contains(",")) {
                    String[] values = ((String) fieldValue).split(",");
                    sb.append(" in(");
                    for (String value : values) {
                        sb.append("'").append(value).append("'").append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                } else {
                    sb.append("=").append("'").append(fieldValue).append("'");
                }
            } else if (fieldValue instanceof Integer) {
                sb.append("=").append(fieldValue);
            }

        }
        return sb.toString();
    }

}
