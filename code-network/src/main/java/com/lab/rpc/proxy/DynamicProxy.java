package com.lab.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

public class DynamicProxy {

  private <T> T getProxyService(T impl) {
    T realService = impl;
    ClassLoader classLoader = ThriftService.Processor.class.getClassLoader();
    InvocationHandler proxyHandler = new MetricProxy(impl, realService, ThriftService.ServiceIface.class);
    impl = (T) Proxy.newProxyInstance(classLoader, new Class[] {ThriftService.ServiceIface.class}, proxyHandler);
    return impl;
  }

  public void serve() {
    server = Thrift.serveIface(new InetSocketAddress(port), getProxyService(impl));
  }
}
