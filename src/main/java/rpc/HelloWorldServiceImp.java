package rpc;

/**
 * @Description:
 * @Author: Grady
 * @Date: Created in 16:36 2018/3/14
 */
public class HelloWorldServiceImp implements HelloWorldService{
    @Override
    public String sayHello(String name) {
        return "HelloWorld :  "+ name+ " !....";
    }
}
