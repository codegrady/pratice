package string;


/**
 * @Description:
 * @Todo: Created by Grady on 2017/10/25.
 */
public class StringInternTest {
    public static void main(String[] args) {

        String a = "abc".intern();
        String b = "abc";
        String c = new String("abc");
        System.out.println(a == a.intern());
        System.out.println(a == b);
        System.out.println(a.intern() == b);
        System.out.println(a.intern() ==c);
        System.out.println(a.intern() == c.intern());

    }
}
