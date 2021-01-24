package annotation;

/**
 * 孩子类
 *
 * @author Sky
 * @date 2021-01-20 21:37.
 */
@Description("I am class annotation.")
public class Child implements Person {

    @Override
    @Description("I am method annotation.")
    public String name() {
        return "Sky";
    }

}
