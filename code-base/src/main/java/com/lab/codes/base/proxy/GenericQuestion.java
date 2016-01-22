package com.lab.codes.base.proxy;

import java.lang.Integer;import java.lang.Override;import java.lang.String;import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericQuestion {
    interface Function<F, R> {
      R apply(F data);
    }
  public static class Fruit {
    int id;
    String name;
    Fruit(int id, String name) {
      this.id = id; this.name = name;
    }

    @Override
    public String toString() {
      return this.getClass().getName() + "{" + id + ", " + name + "}";
    }
  }

  public static class Apple extends Fruit {
    Apple(int id, String type) {
      super(id, type);
    }
  }

  public static class Pear extends Fruit {
    Pear(int id, String type) {
      super(id, type);
    }
  }

  public static void main(String[] args) {
    List<Apple> apples = Arrays.asList(
        new Apple(1, "Green"), new Apple(2, "Red")
    );
    List<Pear> pears = Arrays.asList(
        new Pear(1,"Green"), new Pear(2,"Red")
    );

    Function fruitID = new Function<Fruit, Integer>() {
      @Override
      public Integer apply(Fruit data) {
        return data.id;
      }
    };

    Map appleMap = mapValues(apples, fruitID);
    for (Object key : appleMap.keySet()) {
      System.out.println(key + "\t" + appleMap.get(key));
    }
    Map<Integer, Pear> pearMap = mapValues(pears, fruitID);
  }

  @SuppressWarnings("uncheck")
  public static <K, V> Map<K, V> mapValues(List<V> values, Function<? super V, K> function) {
    Map<K, V> map = new HashMap<K, V>();
    for (V val : values) {
      map.put(function.apply(val), val);
    }
    return map;
  }
}
