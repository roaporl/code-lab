package com.lab.codes.base.hash.balence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import com.lab.codes.base.hash.HashAlgorithms;

public class RehashBucketDemo {

  private static PrintWriter out;

  public static void main(String[] args) throws FileNotFoundException {

//    int x = Integer.toString("0".hashCode()).hashCode();
//    System.out.println((x & Integer.MAX_VALUE) % 10);
//    out = new PrintWriter(new File("/home/lab/logs/rehash/rehash-random-toString.report"));
    out = new PrintWriter(new File("/home/lab/logs/rehash/rehash-murmurHash.report"));
    int maxShard = 10;
    for (int thread = 3; thread <= 11; thread++) {
      for (int shards = 1; shards <= maxShard; shards++) {
        int max = shards * thread * tens(4);
        new RehashBucketDemo().run(shards, thread, max);
      }
      out.println();
    }
    out.close();
  }

  private void run(int shards, int thread, int dataMax) {
    int[] buckets = new int[shards * thread];
//    out.println(buckets.length);
    for (int i = 1; i <= dataMax; ++i) {
//      for (int s = 0; s < shards; s++) {
        int id = new Random().nextInt();
//      int id = i;
      int acceptShard = acceptBy(id, shards);
      String key = Integer.toString(HashAlgorithms.murmurHash32(String.valueOf(id)));
      int hashKey = (HashAlgorithms.murmurHash32(key) & Integer.MAX_VALUE) % thread;
      buckets[acceptShard * thread + hashKey]++;
    }
    out.printf("data=%d, shards=%d, threadPerShard=%d, standardVariance=%.4f\n", dataMax, shards, thread, Math.sqrt(variance(buckets)));
//    out.printf("(shards=%2s, buckets=%2s) > %s\n", shards, buckets.length, bucketsToString(buckets, 4));
    for (int i = 0; i < shards; ++i) {
//      out.printf("(shard=%2s, bucket=%2s) > %s\n", i, thread, bucketsToString(buckets, i, thread, 4));
    }
  }

  private static int tens(int n) {
    int cx = 1;
    for (int i = 0; i < n; i++) {
      cx *= 10;
    }
    return cx;
  }

  private String bucketsToString(int[] buckets, int shardId, int thread, int n) {
    StringBuilder builder = new StringBuilder();
    String formatter = String.format("%%%dd", n);
    for (int i = shardId * thread, j = 0; j < thread && i < buckets.length; i++, j++) {
      if (j != 0) {
        builder.append(", ").append(String.format(formatter, buckets[i]));
      } else {
        builder.append(buckets[i]);
      }
    }
    return builder.toString();
  }


  private String bucketsToString(int[] buckets, int n) {
    StringBuilder builder = new StringBuilder();
    String formatter = String.format("%%%dd", n);
    for (int i = 0; i < buckets.length; i++) {
      if (i != 0) {
        builder.append(", ").append(String.format(formatter, buckets[i]));
      } else {
        builder.append(buckets[i]);
      }
    }
    return builder.toString();
  }

  private boolean accept(int s, int id, int shard) {
    return Math.abs(id) % shard == s;
//    return (id & Integer.MAX_VALUE) % shard == 0;

  }


  private int acceptBy(int id, int shard) {
    return Math.abs(id) % shard;
//    return (id & Integer.MAX_VALUE) % shard == 0;
  }


  public double variance(int[] list) {
    double avg = average(list);
    double var = 0;
    for (int a : list) {
      var += Math.pow(a - avg, 2);
    }
    var /= list.length - 1;
    return var;
  }

  private double average(int[] list) {
    double avg = 0;
    for (int e : list) {
      avg += e;
    }
    avg /= list.length;
    return avg;
  }
}
