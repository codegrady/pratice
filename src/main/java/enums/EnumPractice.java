package enums;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class EnumPractice {
    public static void main(String[] args) {
//        System.out.println(getCodeType(3).getDescribe());
//        types().forEach(type->{
//            System.out.println(type);
//        });
        testEnumMap();
    }

    static CodeTypeEnum getCodeType(int code){
        for(CodeTypeEnum cte: CodeTypeEnum.values()){
            if(cte.getCode().equals(code)) {
                return cte;
            }
        }
        return null;
    }

    static List<CodeTypeEnum> types(){
        return Arrays.asList(CodeTypeEnum.values());
    }

    static void testEnumSet(){
        EnumSet<CodeTypeEnum> codeTypeEnums = EnumSet.allOf(CodeTypeEnum.class);
        codeTypeEnums.forEach(System.out::println);
    }
    static void testEnumMap(){
        EnumMap<CodeTypeEnum,String> map = new EnumMap(CodeTypeEnum.class);
        map.put(CodeTypeEnum.JAVA,"java === java");
        map.put(CodeTypeEnum.C,"C === C");
        map.put(CodeTypeEnum.GO,"go === go");
        map.forEach((k,v)->{
            System.out.println(k+": "+v);
        });
    }
}
