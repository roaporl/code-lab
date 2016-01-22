package com.lab.codes.lucene;

import com.google.common.base.Stopwatch;
import org.apache.commons.math3.util.FastMath;

public class MathDemo {

  public static void main(String[] args) {

    Stopwatch sw = Stopwatch.createStarted();
    for (long i = 0; i < 10E7; i++) {
//      FastMath.pow(2.4, 3);
//      Math.pow(2.4, 3);
//      Math.log(2.34);
//      FastMath.log(2.34);
//      Math.sqrt(2.34);
//      FastMath.sqrt(2.34);

//      Math.sin(2.34);
//      FastMath.sin(2.34);
//      Math.cos(2.34);
//      FastMath.cos(2.34);
//      Math.abs(1.2);
//      FastMath.abs(1.2);
//      Math.ulp(1.34);
//      FastMath.ulp(1.34);
    }
    System.out.println(sw.stop().toString());
    FastMath.ulp(2);
    FastMath.abs(1);
    Math.toRadians(2);
    FastMath.toRadians(2);
  }
}
