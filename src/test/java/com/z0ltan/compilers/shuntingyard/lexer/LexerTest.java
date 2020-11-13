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
          new LexerTestCase(TokenType.NUMBER, "3.14159"),
    };

    Lexer lexer = new Lexer(input);

    for (int i = 0; i < testCases.length; i++) {
      Token token = lexer.nextToken();
      assertEquals(testCases[i].expectedKind, token.getKind());
      assertEquals(testCases[i].expectedSpelling, token.getSpelling());
    }
  }

  public void testLexerSymbol() {
    String input = "()+-*/";
    LexerTestCase[] testCases = new LexerTestCase[] {
      new LexerTestCase(TokenType.LPAREN, "("),
          new LexerTestCase(TokenType.RPAREN, ")"),
          new LexerTestCase(TokenType.LEFT_OPERATOR, "+"),
          new LexerTestCase(TokenType.LEFT_OPERATOR, "-"),
          new LexerTestCase(TokenType.LEFT_OPERATOR, "*"),
          new LexerTestCase(TokenType.LEFT_OPERATOR, "/"),
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
    String input = "((11 ^ 2 + 2) * 3 - (24.19) / -2.13)";
    LexerTestCase[] testCases = new LexerTestCase[] {
      new LexerTestCase(TokenType.LPAREN, "("),
      new LexerTestCase(TokenType.LPAREN, "("),
      new LexerTestCase(TokenType.NUMBER, "11"),
      new LexerTestCase(TokenType.RIGHT_OPERATOR, "^"),
      new LexerTestCase(TokenType.NUMBER, "2"),
      new LexerTestCase(TokenType.LEFT_OPERATOR, "+"),
      new LexerTestCase(TokenType.NUMBER, "2"),
      new LexerTestCase(TokenType.RPAREN, ")"),
      new LexerTestCase(TokenType.LEFT_OPERATOR, "*"),
      new LexerTestCase(TokenType.NUMBER, "3"),
      new LexerTestCase(TokenType.LEFT_OPERATOR, "-"),
      new LexerTestCase(TokenType.LPAREN, "("),
      new LexerTestCase(TokenType.NUMBER, "24.19"),
      new LexerTestCase(TokenType.RPAREN, ")"),
      new LexerTestCase(TokenType.LEFT_OPERATOR, "/"),
      new LexerTestCase(TokenType.LEFT_OPERATOR, "-"),
      new LexerTestCase(TokenType.NUMBER, "2.13"),
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
