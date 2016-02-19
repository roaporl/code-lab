package com.lab.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetricProxy implements InvocationHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(MetricProxy.class);
  private Set<Method> validMethods = null;
  private Object serviceImpl;
  private Set<Class<?>> ignoreExceptions = new HashSet<Class<?>>();
  private Map<Method, Set<Class<?>>> declaredExceptions = new HashMap<Method, Set<Class<?>>>();

  public MetricProxy(Object serviceImpl, Object realServiceImpl, Class<?> ifaceClass) {
    this.serviceImpl = serviceImpl;
    this.validMethods = new HashSet<>(Arrays.asList(ifaceClass.getDeclaredMethods()));
    CounterIgnoreException ignored = realServiceImpl.getClass().getAnnotation(CounterIgnoreException.class);
    if (ignored != null) {
      this.ignoreExceptions.addAll(Arrays.asList(ignored.value()));
    }
  }

  @Override
  public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
    boolean isValidMethod = validMethods.contains(method);

    try {
      String parentTag = null;
      final String tag;
      switch (method.getName()) {
        case "search": {
          assert args.length == 1;
          Req request = (Req) args[0];
          tag = request.getDomain() + "|" + request.getCluster() + "|search";
          break;
        }
        case "searchService": {
          assert args.length == 1;
          SReq request = (SReq) args[0];
          String cluster = request.getCluster();
          if (cluster == null) {
            cluster = serviceKeeperImpl.getCluster(request);
//            System.out.println(cluster);
          }
          parentTag = request.getDomain() + "|" + cluster + "|search";
          tag = request.getDomain() + "|" + cluster + "|searchService|" + request.getName();
          break;
        }
        case "index": {
          IReq request = (IReq) args[0];
          tag = request.getDomain() + "|" + request.getCluster() + "|index";
          break;
        }
        case "batchIndex": {
          IReq request = (IReq) args[0];
          tag = request.getDomain() + "|" + request.getCluster() + "|index";
          break;
        }
        case "handelBqlRequest": {
          assert args.length == 3;
          tag = args[0] + "|" + args[1] + "|search";
          break;
        }
        case "handelTemplatedBqlRequest": {
          assert args.length == 4;
          parentTag = args[0] + "|" + args[1] + "|search";
          tag = args[0] + "|" + args[1] + "|searchService|" + args[2];
          break;
        }
        case "handelIndexRequest": {
          assert args.length == 3;
          tag = args[0] + "|" + args[1] + "|index";
          break;
        }
        default:
          break;
      }
//          if (parentTag != null) {
//            return oakbayCounter.add(new Callable<Object>() {
//              @Override
//              public Object call() throws Exception {
//                return oakbayCounter.add(actualInvokeCall, tag);
//              }
//            }, parentTag);
//          } else {
//            return oakbayCounter.add(actualInvokeCall, tag);
//          }
//        }
//      };

      return method.invoke(serviceImpl, args);
//      return this.oakbayCounter.add(new Callable<Object>() {
//        @Override
//        public Object call() throws Exception {
//          return methodCall;
//        }
//      }, "oakbay_domain|oakbay_cluster|method/" + method.getName());

    } catch (InvocationTargetException ite) {
      Throwable t = (ite.getCause() != null) ? ite.getCause() : ite;
      logException(t, method, isValidMethod, true);
      throw t;
    } catch (Throwable r) {
      logException(r, method, isValidMethod, true);
      throw r;
    } finally {
      if (isValidMethod) {
//        this.oakbayCounter.add(null, method.getName());
      }
    }
  }

  private void logException(Throwable t, Method method, boolean incrementCounter, boolean isFirstException) {
    if (isFirstException && isUserDeclaredException(method, t)) {
      LOGGER.info("User defined exception was thrown.", t);
    }

    //report all the failures
//    counter.increment(ALL_THRIFT_METHODS_FAIL);
    if (isFirstException && this.ignoreExceptions.contains(t.getClass())) {
      return;
    }

    //report unexpected failures.
//    counter.increment(ALL_THRIFT_METHODS_UNEXPECTED_FAIL);

    if (this.oakbayCounter != null && t != null) {
//      counter.increment(EXCEPTION_COUNTER_PREFIX + t.getClass().getName());
      if (incrementCounter) {
//        counter.increment(METHOD_COUNTER_PREFIX + method.getName() + "_fail");
      }
      logException(t.getCause(), method, false, false);
    }
  }

  private boolean isUserDeclaredException(Method method, Throwable t) {
    synchronized (method) {
      Set<Class<?>> exceptions = declaredExceptions.get(method);
      if (exceptions == null) {
        exceptions = new HashSet<Class<?>>(Arrays.asList(method.getExceptionTypes()));
        exceptions.remove(TException.class);
        declaredExceptions.put(method, exceptions);
      }
      return exceptions.contains(t.getClass());
    }
  }

}
