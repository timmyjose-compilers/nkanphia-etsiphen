package com.z0ltan.compilers.shuntingyard.evaluator;

import java.util.Queue;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import static org.junit.Assert.*;

import com.z0ltan.compilers.shuntingyard.lexer.Lexer;
import com.z0ltan.compilers.shuntingyard.parser.Parser;

public class EvaluatorTest extends TestCase {
  public EvaluatorTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(EvaluatorTest.class);
  }

  public void testEvaluateNumber1() {
    String input = "1";
    double expectedValue = 1.0;

    Evaluator evaluator = new Evaluator(new Parser(new Lexer(input)));
    double res = evaluator.evaluate();
    assertEquals(expectedValue, res);
  }

  public void testEvaluateNumber2() {
    String input = "2.71828";
    double expectedValue = 2.71828;

    Evaluator evaluator = new Evaluator(new Parser(new Lexer(input)));
    double res = evaluator.evaluate();
    assertEquals(expectedValue, res);
  }

  public void testEvaluateExpression1() {
    String input = "1 + 2 * 3";
    double expectedValue = 7.0;

    Evaluator evaluator = new Evaluator(new Parser(new Lexer(input)));
    double res = evaluator.evaluate();
    assertEquals(expectedValue, res);
  }

  public void testEvaluateExpression2() {
    String input = "1 * 2 + 3";
    double expectedValue = 5.0;

    Evaluator evaluator = new Evaluator(new Parser(new Lexer(input)));
    double res = evaluator.evaluate();
    assertEquals(expectedValue, res);
  }

  public void testEvaluateExpression3() {
    String input = " 3 + 4 * 2 / (1 - 5) ^ 2 ^ 3";
    double expectedValue = 3.0001220703125;
    Evaluator evaluator = new Evaluator(new Parser(new Lexer(input)));
    double res = evaluator.evaluate();
    assertEquals(expectedValue, res);
  }

  public void testEvaluateExpression4() {
    String input = "2 ^ 3";
    double expectedValue = 8.0;

    Evaluator evaluator = new Evaluator(new Parser(new Lexer(input)));
    double res = evaluator.evaluate();
    assertEquals(expectedValue, res);
  }
}
