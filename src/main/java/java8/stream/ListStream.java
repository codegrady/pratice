package java8.stream;

import java.util.Arrays;

/**
 * @Description:
 * @Author: Grady
 * @Date: Created in 下午2:53 2018/1/26
 */
public class ListStream {
    public static void main(String[] args) {
//        List<Integer> list = Lists.newArrayList();
//        list.add(null);
//        int sum;
//        sum = list.stream().mapToInt(value -> value).sum();
//        System.out.println("sum = " + sum);

        int[] nums = {1,2,3,4,5,6,6,7,88,5,453,3};
        //allMatch：是不是Stream中的所有元素都满足给定的匹配条件
        boolean b = Arrays.stream(nums).allMatch(v -> v == 88);
        System.out.println("b = " + b);
        //anyMatch：Stream中是否存在任何一个元素满足匹配条件
        boolean bb = Arrays.stream(nums).anyMatch(v -> v == 5);
        System.out.println("bb = " + bb);
    }

//    static List<Integer>
}
