package com.lab.codes.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.google.common.base.Throwables;

public class DynamicProxyDemo {

  interface Obj {
    void print();
    void setNum(int x);
  }

  class RealObj implements Obj {
    protected int a = 0;

    @Override
    public void print() {
      System.out.println("real print");
    }

    public void setNum(int x) {
      a = x;
    }
  }

  class ProxyObj implements Obj {
    RealObj realObj;

    public ProxyObj(RealObj real) {
      realObj = real;
    }

    @Override
    public void print() {
      realObj.print();
    }

    @Override
    public void setNum(int x) {
      realObj.setNum(x);
    }
  }

  class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
      this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("**** proxy: ****\n" + proxy.getClass()
                         + "\nmethod: " + method + "\nargs: " + args);
      if (args != null)
        for (Object arg : args) System.out.println("  " + arg);
      return method.invoke(proxied, args);
    }
  }


  public static void main(String[] args) {
    DynamicProxyDemo demo = new DynamicProxyDemo();
    demo.doMain();
  }

  private void doMain() {
    try {
      RealObj real = null; // new RealObj();
//    real.print();

      Obj proxy = (Obj) Proxy.newProxyInstance(Obj.class.getClassLoader(),
                                               new Class[]{Obj.class},
                                               new DynamicProxyHandler(real));
      proxy.print();
      proxy.setNum(4);
      System.out.println(real.a);
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
    }
  }

}
