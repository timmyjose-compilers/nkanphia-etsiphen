package com.z0ltan.compilers.shuntingyard.parser;

import java.util.Queue;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.*;

import com.z0ltan.compilers.shuntingyard.lexer.Lexer;
import com.z0ltan.compilers.shuntingyard.ast.*;

public class ParserTest extends TestCase {
  static class ParserTestCase {
    Item expectedItem;

    public ParserTestCase(Item expectedItem) {
      this.expectedItem = expectedItem;
    }
  }

  public ParserTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(ParserTest.class);
  }

  public void testParseInteger() {
    String input = "12345";
    ParserTestCase[] testCases = new ParserTestCase[] {
      new ParserTestCase(new NumberItem(12345))
    };

    Parser parser = new Parser(new Lexer(input));
    Queue<Item> ast = parser.parse();

    for (int i = 0; i < testCases.length; i++) {
      assertEquals(testCases[i].expectedItem, ast.poll());
    }
  }

  public void testParseExpression1() {
    String input = "1.09 + 2.23";
    ParserTestCase[] testCases = new ParserTestCase[] {
      new ParserTestCase(new NumberItem(1.09)),
          new ParserTestCase(new NumberItem(2.23)),
          new ParserTestCase(new AddItem(4, Associativity.LEFT)),
    };

    Parser parser = new Parser(new Lexer(input));
    Queue<Item> ast = parser.parse();
    for (int i = 0; i < testCases.length; i++) {
      assertEquals(testCases[i].expectedItem, ast.poll());
    }
  }

  public void testParseExpression2() {
    String input = "1 + 3 * 9 / (2 + 4)";
    ParserTestCase[] testCases = new ParserTestCase[] {
      new ParserTestCase(new NumberItem(1)),
          new ParserTestCase(new NumberItem(3)),
          new ParserTestCase(new NumberItem(9)),
          new ParserTestCase(new MulItem(7, Associativity.LEFT)),
          new ParserTestCase(new NumberItem(2)),
          new ParserTestCase(new NumberItem(4)),
          new ParserTestCase(new AddItem(4, Associativity.LEFT)),
          new ParserTestCase(new DivItem(7, Associativity.LEFT)),
          new ParserTestCase(new AddItem(4, Associativity.LEFT)),
    };

    Parser parser = new Parser(new Lexer(input));
    Queue<Item> ast = parser.parse();

    for (int i = 0; i < testCases.length; i++) {
      assertEquals(testCases[i].expectedItem, ast.poll());
    }
  }

  public void testParseExpression3() {
    String input = "3 + 4 *2 / (    1 - 5   ) ^ 2 ^ 3";
    ParserTestCase[] testCases = new ParserTestCase[] {
      new ParserTestCase(new NumberItem(3)),
      new ParserTestCase(new NumberItem(4)),
      new ParserTestCase(new NumberItem(2)),
      new ParserTestCase(new MulItem(7, Associativity.LEFT)),
      new ParserTestCase(new NumberItem(1)),
      new ParserTestCase(new NumberItem(5)),
      new ParserTestCase(new SubItem(4, Associativity.LEFT)),
      new ParserTestCase(new NumberItem(2)),
      new ParserTestCase(new NumberItem(3)),
      new ParserTestCase(new PowItem(10, Associativity.RIGHT)),
      new ParserTestCase(new PowItem(10, Associativity.RIGHT)),
      new ParserTestCase(new DivItem(7, Associativity.LEFT)),
      new ParserTestCase(new AddItem(4, Associativity.LEFT)),
    };

    Parser parser = new Parser(new Lexer(input));
    Queue<Item> ast = parser.parse();

    for (int i = 0; i < testCases.length; i++) {
      assertEquals(testCases[i].expectedItem, ast.poll());
    }
  }
}
