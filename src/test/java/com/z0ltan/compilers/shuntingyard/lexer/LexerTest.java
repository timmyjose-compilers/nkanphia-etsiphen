package com.z0ltan.compilers.shuntingyard.lexer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.*;

import com.z0ltan.compilers.shuntingyard.lexer.Token;
import com.z0ltan.compilers.shuntingyard.lexer.TokenType;

public class LexerTest extends TestCase {
  static class LexerTestCase {
    TokenType expectedKind;
    String expectedSpelling;

    public LexerTestCase(TokenType kind, String spelling) {
      expectedKind = kind;
      expectedSpelling = spelling;
    }
  }

  public LexerTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(LexerTest.class);
  }

  public void testLexerInteger() {
    String input = "12345";
    LexerTestCase[] testCases = new LexerTestCase[] {
      new LexerTestCase(TokenType.NUMBER, "12345"),
          new LexerTestCase(TokenType.EOF, ""),
    };

    Lexer lexer = new Lexer(input);

    for (int i = 0; i < testCases.length; i++) {
      Token token = lexer.nextToken();
      assertEquals(testCases[i].expectedKind, token.getKind());
      assertEquals(testCases[i].expectedSpelling, token.getSpelling());
    }
  }

  public void testLexerFloat() {
    String input = "3.14159";
    LexerTestCase[] testCases = new LexerTestCase[] {
      new LexerTestCase(TokenType.NUMBER, "3"),
          new LexerTestCase(TokenType.PERIOD, "."),
          new LexerTestCase(TokenType.NUMBER, "14159"),
          new LexerTestCase(TokenType.EOF, ""),
    };

    Lexer lexer = new Lexer(input);

    for (int i = 0; i < testCases.length; i++) {
      Token token = lexer.nextToken();
      assertEquals(testCases[i].expectedKind, token.getKind());
      assertEquals(testCases[i].expectedSpelling, token.getSpelling());
    }
  }

  public void testLexerSymbol() {
    String input = "()+-~*/";
    LexerTestCase[] testCases = new LexerTestCase[] {
      new LexerTestCase(TokenType.LPAREN, "("),
          new LexerTestCase(TokenType.RPAREN, ")"),
          new LexerTestCase(TokenType.PLUS, "+"),
          new LexerTestCase(TokenType.MINUS, "-"),
          new LexerTestCase(TokenType.TILDE, "~"),
          new LexerTestCase(TokenType.ASTERISK, "*"),
          new LexerTestCase(TokenType.SLASH, "/"),
          new LexerTestCase(TokenType.EOF, ""),
    };

    Lexer lexer = new Lexer(input);
    for (int i = 0; i < testCases.length; i++) {
      Token token = lexer.nextToken();
      assertEquals(testCases[i].expectedKind, token.getKind());
      assertEquals(testCases[i].expectedSpelling, token.getSpelling());
    }
  }

  public void testLexerFunction() {
    String input = "sqrt(sin(cos(tan (199))))";
    LexerTestCase[] testCases = new LexerTestCase[] {
      new LexerTestCase(TokenType.FUNCTION, "sqrt"),
          new LexerTestCase(TokenType.LPAREN, "("),
          new LexerTestCase(TokenType.FUNCTION, "sin"),
          new LexerTestCase(TokenType.LPAREN, "("),
          new LexerTestCase(TokenType.FUNCTION, "cos"),
          new LexerTestCase(TokenType.LPAREN, "("),
          new LexerTestCase(TokenType.FUNCTION, "tan"),
          new LexerTestCase(TokenType.LPAREN, "("),
          new LexerTestCase(TokenType.NUMBER, "199"),
          new LexerTestCase(TokenType.RPAREN, ")"),
          new LexerTestCase(TokenType.RPAREN, ")"),
          new LexerTestCase(TokenType.RPAREN, ")"),
          new LexerTestCase(TokenType.RPAREN, ")"),
          new LexerTestCase(TokenType.EOF, ""),
    };

    Lexer lexer = new Lexer(input);
    for (int i = 0; i < testCases.length; i++) {
      Token token = lexer.nextToken();
      assertEquals(testCases[i].expectedKind, token.getKind());
      assertEquals(testCases[i].expectedSpelling, token.getSpelling());
    }
  }

  public void testLexerFull() {
    String input = "((11 ^ 2 + 2) * 3 - sqrt(24.19) / ~2.13)";
    LexerTestCase[] testCases = new LexerTestCase[] {
      new LexerTestCase(TokenType.LPAREN, "("),
      new LexerTestCase(TokenType.LPAREN, "("),
      new LexerTestCase(TokenType.NUMBER, "11"),
      new LexerTestCase(TokenType.CARET, "^"),
      new LexerTestCase(TokenType.NUMBER, "2"),
      new LexerTestCase(TokenType.PLUS, "+"),
      new LexerTestCase(TokenType.NUMBER, "2"),
      new LexerTestCase(TokenType.RPAREN, ")"),
      new LexerTestCase(TokenType.ASTERISK, "*"),
      new LexerTestCase(TokenType.NUMBER, "3"),
      new LexerTestCase(TokenType.MINUS, "-"),
      new LexerTestCase(TokenType.FUNCTION, "sqrt"),
      new LexerTestCase(TokenType.LPAREN, "("),
      new LexerTestCase(TokenType.NUMBER, "24"),
      new LexerTestCase(TokenType.PERIOD, "."),
      new LexerTestCase(TokenType.NUMBER, "19"),
      new LexerTestCase(TokenType.RPAREN, ")"),
      new LexerTestCase(TokenType.SLASH, "/"),
      new LexerTestCase(TokenType.TILDE, "~"),
      new LexerTestCase(TokenType.NUMBER, "2"),
      new LexerTestCase(TokenType.PERIOD, "."),
      new LexerTestCase(TokenType.NUMBER, "13"),
      new LexerTestCase(TokenType.RPAREN, ")"),
      new LexerTestCase(TokenType.EOF, ""),
    };

    Lexer lexer = new Lexer(input);
    for (int i = 0; i < testCases.length; i++) {
      Token token = lexer.nextToken();
      assertEquals(testCases[i].expectedKind, token.getKind());
      assertEquals(testCases[i].expectedSpelling, token.getSpelling());
    }
  }
}
