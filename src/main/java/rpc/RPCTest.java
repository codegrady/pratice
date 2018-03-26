package rpc;

/**
 * @Description:
 * @Author: Grady
 * @Date: Created in 16:37 2018/3/14
 */
public class RPCTest {
    public static void main(String[] args) {
        HelloWorldService helloWorldService = (HelloWorldService) RPCProxyClient.getProxy(HelloWorldService.class);
        helloWorldService.sayHello("Grady");
    }
}
