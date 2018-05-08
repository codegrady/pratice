package annotation;

import reflect.ReflectUtil;

/**
 * @Description:
 * @Author: Grady
 * @Date: Created in 15:07 2018/4/14
 */
@Desc(desc = "AnnotationTest")
public class AnnotationTest {
    @Test(id = 12,desc = "outInfo")
    static void outInfo(){
        System.out.println("outInfo");
    }

    @Test(id= 234)
    static void info(){
        System.out.println("info");
    }

    public static void main(String[] args) {
        ReflectUtil.getClassInfo(AnnotationTest.class);
    }
}
