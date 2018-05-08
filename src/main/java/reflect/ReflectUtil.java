package reflect;

import annotation.Desc;
import annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: Grady
 * @Date: Created in 14:49 2018/4/14
 */
public class ReflectUtil {
    public  static void getClassInfo(Class<?> cl){
        Annotation[] arra = cl.getAnnotations();
        System.out.println("arra.size  = "+ arra.length);
        for (Annotation a :arra) {
            System.out.println("a = " + a.toString());
        }
        for(Method m : cl.getDeclaredMethods()){
            System.out.println("method name : "+m.getName());
            Test t = m.getAnnotation(Test.class);
            if(t != null)
                System.out.println("id = " + t.id()+"  desc = " + t.desc());
        }
    }
}
