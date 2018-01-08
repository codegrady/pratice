package collection.list;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Grady on 2017/8/3.
 */
public class ArrayListPractice {
    static ArrayList<Integer> list = new ArrayList<>();
    static List<Entity> eList = new ArrayList<>();
    public static void main(String[] args) {
        sortEntity();
    }

    static void listFor(){
        for (Integer v:list)
            System.out.println("v = " + v);
    }
    static void listLambda(){
        list.forEach(item-> System.out.println("v = " + item));
        list.forEach(System.out::println);
    }

    //===========================List 操作 ===========================//
    /**
     * list 排序
     */
    static void sortList(){
        Collections.sort(list);
        list.forEach(v->{
            System.out.println(v);
        });
    }
    static void sortEntity(){
        getList(5);
        eList.forEach(v->{
            System.out.println(v.getName()+"   "+v.getAge());
        });
        System.out.println("------------------------------------------------------");
        //排序 1compareto2 生序
//        Collections.sort(eList, new Comparator<Entity>() {
//            @Override
//            public int compare(Entity o1, Entity o2) {
//                return o2.getAge().compareTo(o1.getAge());
//            }
//        });
        //stream
//        eList.sort(Comparator.comparing(Entity::getAge).reversed());
        eList = eList.stream().sorted(Comparator.comparing(Entity::getAge)).collect(Collectors.toList());
        eList.forEach(v->{
            System.out.println(v.getName()+"   "+v.getAge());
        });
    }
    static void remove(){

        list.subList(2,4).clear();
        System.out.println(list.toString());
    }


    static void getList(){
        list.add(1);
        list.add(2);
        list.add(6);
        list.add(9);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(7);
    }

    static void getList(int size){
        int max = 40;
        Random random = new Random();
        for(int i = 1;i<= size;i++){
            eList.add(new Entity("entity_"+i, random.nextInt(max)));
        }
    }

    static class Entity{
        public Entity(String name,Integer age){
            this.age=age;
            this.name=name;
        }
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
