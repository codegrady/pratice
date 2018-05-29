package google.guava;

import com.google.common.base.Optional;

/**
 * @Description:
 * Optional用于包含非空对象的不可变对象。
 * Optional对象，用于不存在值表示null。
 * 这个类有各种实用的方法，以方便代码来处理为可用或不可用，而不是检查null值。
 * @Todo:
 * @Author Grady
 * Created on 2017/11/21.
 */
public class GuavaOptional {
    public static void main(String[] args) {



    }

    static Integer sum(Optional<Integer> a , Optional<Integer> b){
        //Optional.isPresent - checks the value is present or not
        System.out.println("first param is present : "+a.isPresent());

        System.out.println("second param is present : "+b.isPresent());

        a.or(10);
        return a.get() + b.get();
    }
}
