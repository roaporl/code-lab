package com.lab.codes.base.tree;

import com.google.common.base.Joiner;

import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharSequenceNodeFactory;
import com.googlecode.concurrenttrees.radixinverted.ConcurrentInvertedRadixTree;
import com.googlecode.concurrenttrees.radixinverted.InvertedRadixTree;

public class TrieUtil {

  public static void main(String[] args) {

    // GET:/apm/search
    // GET:/apm/app/package/a.b.c
    // GET:/apm/download
    // GET:/apm/updateinfo
    // GET:/apm/updateinfo/v2
    // GET:/apm/featured
    // GET:/apm/search

    RadixTree<Integer> radixTree = new ConcurrentRadixTree<Integer>(new DefaultCharSequenceNodeFactory());
    radixTree.put("GET:/apm/search", 1);
    radixTree.put("GET:/apm/app/package", 1);
    radixTree.put("GET:/apm/search/suggest", 1);
    radixTree.put("GET:/apm/download", 1);
    radixTree.put("GET:/apm/updateinfo", 1);
    radixTree.put("GET:/apm/updateinfo/v2", 1);
    radixTree.put("GET:/apm/featured", 1);
    System.out.println(radixTree.getValueForExactKey("GET:/apm/search"));
    System.out.println(radixTree.getValueForExactKey("GET:/apm/updateinfo"));
//    System.out.println(radixTree.getValueForExactKey("GET:/apm/updateinfo"));

    // startWith
    Iterable<CharSequence> values  = radixTree.getKeysStartingWith("GET:/apm/search");
    System.out.println(Joiner.on(", ").join(values));
    System.out.println(Joiner.on(", ").join(radixTree.getKeysStartingWith("GET:/apm/updateinfo")));
    System.out.println(Joiner.on(", ").join(radixTree.getKeysStartingWith("GET:/apm/app/package")));



    InvertedRadixTree<Integer>
        tree = new ConcurrentInvertedRadixTree<Integer>(new DefaultCharSequenceNodeFactory());
    tree.put("GET:/apm/search", 1);
    tree.put("GET:/apm/app/package", 1);
    tree.put("GET:/apm/search/suggest", 1);
    tree.put("GET:/apm/search/suggest/a", 1);
    tree.put("GET:/apm/search/suggest/e", 1);
    tree.put("GET:/apm/download", 1);
    tree.put("GET:/apm/updateinfo", 1);
    tree.put("GET:/apm/updateinfo/v2", 1);
    tree.put("GET:/apm/featured", 1);

    System.out.println("Prefixing\t" + Joiner.on(", ").join(tree.getKeysPrefixing("GET:/apm/app/package/a.b.c")));
    System.out.println("Prefixing\t" + Joiner.on(", ").join(tree.getKeysPrefixing("GET:/apm/updateinfo/a.b.c")));
    System.out.println("Prefixing\t"+Joiner.on(", ").join(tree.getKeysPrefixing("HGET:/apm/search/suggest")));
    System.out.println("ContainedIn\t"+Joiner.on(", ").join(tree.getKeysContainedIn("HGET:/apm/search/suggest")));
    System.out.println("Closest\t"+Joiner.on(", ").join(tree.getClosestKeys("GET:/apm/search/suggest/d")));
    System.out.println("Closest\t"+Joiner.on(", ").join(tree.getClosestKeys("GET:/apm/toplist/d")));

    System.out.println(tree.getKeysPrefixing("HGET:/apm/search/suggest").iterator().hasNext());


  }

}
