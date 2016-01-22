//package com.lab.codes.lucene;
//
//import java.io.IOException;
//
//import org.apache.lucene.index.LeafReaderContext;
//import org.apache.lucene.search.FieldDoc;
//import org.apache.lucene.search.FieldValueHitQueue;
//import org.apache.lucene.search.LeafCollector;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.Scorer;
//import org.apache.lucene.search.TopDocsCollector;
//import org.apache.lucene.util.PriorityQueue;
//
//public class CustomTopDocsCollector extends TopDocsCollector {
//
//  protected CustomTopDocsCollector(PriorityQueue pq) {
//    super(pq);
//  }
//
//  @Override
//  protected void populateResults(ScoreDoc[] results, int howMany) {
//    // avoid casting if unnecessary.
//    FieldValueHitQueue<FieldValueHitQueue.Entry> queue = (FieldValueHitQueue<FieldValueHitQueue.Entry>) pq;
//    for (int i = howMany - 1; i >= 0; i--) {
//      FieldDoc fieldDoc = queue.fillFields(queue.pop());
//      results[i] = fieldDoc;
//      results[i].score = Float.valueOf(String.valueOf(fieldDoc.fields[0])); //记录距离
//    }
//  }
//
//
//
//  @Override
//  public void setScorer(Scorer scorer) throws IOException {
//
//  }
//
//  @Override
//  public void collect(int doc) throws IOException {
//
//  }
//
//  @Override
//  public void setNextReader(LeafReaderContext context) throws IOException {
//
//  }
//
//  @Override
//  public boolean acceptsDocsOutOfOrder() {
//    return false;
//  }
//
//  @Override
//  public LeafCollector getLeafCollector(LeafReaderContext context) throws IOException {
//    return null;
//  }
//
//  @Override
//  public boolean needsScores() {
//    return false;
//  }
//}
