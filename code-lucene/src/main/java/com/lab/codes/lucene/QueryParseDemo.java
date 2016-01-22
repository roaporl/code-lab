package com.lab.codes.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;

public class QueryParseDemo {

  public static void main(String[] args) throws ParseException {

    StandardAnalyzer analyzer = new StandardAnalyzer();
    QueryParser queryParser = new QueryParser(" ", analyzer);
    queryParser.setDefaultOperator(QueryParser.Operator.OR);
    Query query = queryParser.parse("+subject:(图铺)^2 +message:(图铺)");
    System.out.println(query);
  }

}
