package enums;

import lombok.*;

@AllArgsConstructor
//@ToString
public enum CodeTypeEnum {
    JAVA(0,"java","server,web"),
    CSHARP(1,"C#","server,web"),
    C(2,"C","server"),
    CPP(3,"C++","server"),
    JAVASCRIPT(4,"js","server,web"),
    RUBY(5,"ruby","server"),
    SQL(6,"sql","database"),
    SCALA(7,"scala","server"),
    GO(8,"go","server");


    @Getter @Setter private Integer code;
    @Getter @Setter private String name;
    @Getter @Setter private String describe;

}
