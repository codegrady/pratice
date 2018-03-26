package rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: Rpc 代理类
 * @Author: Grady
 * @Date: Created in 16:27 2018/3/14
 */
public class RPCProxyClient implements InvocationHandler{
    private Object obj;

    public RPCProxyClient(Object obj) {
        this.obj = obj;
    }

    /**
     * 得到被代理对象
     * @param obj
     * @return
     */
    public static Object getProxy(Object obj){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),new RPCProxyClient(obj));
    }

    /**
     * 调用此方法执行
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //结果参数;
        System.out.println("invoking ... ");
        Object result = new Object();

        return result;
    }
}
