package com.lamp.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {

    public static void main(String[] args) {
        Talk talk = new ChinaPerson();

        FreeManProxyChinaPersion proxy = new FreeManProxyChinaPersion();
        proxy.setTalk(talk);
        Talk o = (Talk) Proxy.newProxyInstance(talk.getClass().getClassLoader(), talk.getClass().getInterfaces(), proxy);
        o.sayHello();


    }

}


interface Talk {
    public void sayHello();


}

class ChinaPerson implements Talk {
    public void sayHello() {
        System.out.println("你好啊");
    }
}

class FreeManProxyChinaPersion implements InvocationHandler {

    Talk talk;

    public Talk getTalk() {
        return talk;
    }

    public void setTalk(Talk talk) {
        this.talk = talk;
    }




    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始打印日志");

        Object object = method.invoke(talk, args);

        System.out.println("打印结束");
        return object;
    }

    public  Talk  getProxy(){
      return    (Talk)Proxy.newProxyInstance(talk.getClass().getClassLoader(),talk.getClass().getInterfaces(),this);
    }
}
