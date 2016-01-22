package com.lab.codes.bql.test;

import com.google.common.base.Stopwatch;
import com.lab.codes.bql.BQLLexer;
import com.lab.codes.bql.BQLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class Demo {

  public static void main(String[] args) {

//    CharStream stream = new ANTLRFileStream("test.expr");
//    ExprLexer lexer = new ExprLexer(stream);
//    CommonTokenStream tokens = new CommonTokenStream(lexer);
//    ExprParser parser = new ExprParser(tokens);

    String bql = "not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n"
                 + "    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n"
                 + "not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n"
                 + "not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n"
                 + "not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n"
                 + "not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n"
                 + "not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n"
                 + "not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n"
                 + "not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n"
                 + "not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n"
                 + "not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n"
                 + "not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n"
                 + "not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12";

    System.out.println("Start parsing");
    Stopwatch sw = Stopwatch.createStarted();

    compile(bql);

    System.out.println("End parsing: " + sw.stop());
  }

  public static void compile(String bqlStmt) {
    // Lexer splits input into tokens
    ANTLRInputStream input = new ANTLRInputStream(bqlStmt);
    BQLLexer lexer = new BQLLexer(input);
    lexer.removeErrorListeners();
    TokenStream tokens = new CommonTokenStream(lexer);

    // Parser generates abstract syntax tree
    BQLParser parser = new BQLParser(tokens);
    parser.removeErrorListeners();
    parser.setErrorHandler(new BailErrorStrategy());
    try {
      BQLParser.ProgramContext ret = parser.program();
//      BQLCompilerAnalyzer analyzer = new BQLCompilerAnalyzer(parser);
//      ParseTreeWalker.DEFAULT.walk(analyzer, ret);
//      return analyzer.getLindenRequest(ret);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
