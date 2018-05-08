# 注解

 **注解** （也被称为 _元数据_ ）注解不直接影响你的代码执行，但也有一些类型的注解实际上可以用于这一目的。Java 注解是从 Java5 开始添加到 Java 的。
 
 与接口一样，注解也会被编译成class文件
 
 ### 优点
    1、完整地描述程序所需的信息，而这些信息市无法用Java来表达的。
    2、注解可以用来生成描述符文件，甚至或是新的类定义，并且有助于减轻编写“样板”代码的负担。
    3、更加干净易读的代码以及编译期来行检查等。
 
 ### 常用的注解
 在 java.lang中的标准注解
 ```
 @Override 表示当前方法覆盖父类中的方法。
 @Deprecated 如果程序员使用了注解为它的元素，那么编译器会发出警告信息。
 @SuppressWarnings 关闭不当的编辑器警告信息。
 ```
 
 Java提供了四种注解，专门负责新注解的创建 -- 元注解。
 
 ## 元注解 （meta-annotation）
 
 #### @Target
 表示该注解可以用在什么地方
 
 ElementType 参数
 ```
 CONSTRUCTOR：构造器的声明
 FIELD：域声明（包括 enum 实例）
 LOCAL_VARIBLE:局部变量声明
 METHOD:方法声明
 PACKAGE:包声明
 PARAMETER:参数声明
 TYPE:类、接口(包括注解类型） 或enum声明   
 ```
 #### @Retention
 表示需要在什么级别保存改注解信息
 
 RetentionPolicy 参数
 ``` 
 SOURCE:注解将被编译器丢弃。
 CLASS:注解在class文件中可用，但会被VM丢弃。
 RUNTIME:VM将在运行期也保留注解，因此可以用过反射机制读取注解的信息。
 ```
 #### @Documented
 将此注解包含在JavaDoc中
 #### @Inherited
 允许子类继承父类中的注解。
 
 ## 注解元素
 注解可用的类型：
 ```
 1、所有基本了类型（int，float，boolean等）
 2、String
 3、Class
 4、enum
 5、Annotation
 以上类型的数组
 ```
 使用了除上述以为的类型，编译器就会报错。
 
 ### 默认值限制
 首先，元素不能有不确定的值。
 其次，对于非基本类型的元素，无论是再源代码中声明是，或是在注解接口中定于默认值时，都不能以null作为其值。
 
 ### 生成外部文件
 