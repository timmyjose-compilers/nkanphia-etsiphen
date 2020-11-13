package com.z0ltan.compilers.shuntingyard.lexer;

import com.z0ltan.compilers.shuntingyard.lexer.TokenType;
import com.z0ltan.compilers.shuntingyard.lexer.Token;

public class Lexer {
  private String source;
  private int position;
  private int readPosition;
  private char currentChar;

  public Lexer(final String source) {
    this.source = source;
    this.position = 0;
    this.readPosition = 0;
    this.currentChar = '\u0000';
    readChar();
  }

  void readChar() {
    if (readPosition >= source.length()) {
      currentChar = '\u0000';
    } else {
      currentChar = source.charAt(readPosition);
    }

    position = readPosition;
    readPosition++;
  }

  char peekChar() {
    if (readPosition >= source.length()) {
      return '\u0000';
    }
    return source.charAt(readPosition);
  }

  void skipWhitespace() {
    while (Character.isWhitespace(currentChar)) {
      readChar();
    }
  }

  String readFunctionName() {
    int startPosition = position;
    while (Character.isLetter(currentChar)) {
      readChar();
    }

    return source.substring(startPosition, position);
  }

  String readNumber() {
    int startPosition = position;

    if (currentChar == '-') {
      readChar();
    }

    while (Character.isDigit(currentChar)) {
      readChar();
    }

    if (currentChar == '.') {
      readChar();
      while (Character.isDigit(currentChar)) {
        readChar();
      }
    }
    return source.substring(startPosition, position);
  }

  public Token nextToken() {
    Token token = null;

    skipWhitespace();

    switch (currentChar) {
      case '\u0000':
        token = new Token(TokenType.EOF, ""); break;
      case '^':
        token = new Token(TokenType.RIGHT_OPERATOR, "^"); break;
      case '+':
        token = new Token(TokenType.LEFT_OPERATOR, "+"); break;
      case '-':
        token = new Token(TokenType.LEFT_OPERATOR, "-"); break;
      case '*':
        token = new Token(TokenType.LEFT_OPERATOR, "*"); break;
      case '/':
        token = new Token(TokenType.LEFT_OPERATOR, "/"); break;
      case '(':
        token = new Token(TokenType.LPAREN, "("); break;
      case ')':
        token = new Token(TokenType.RPAREN, ")"); break;
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9': 
        {
          TokenType kind = TokenType.NUMBER;
          String spelling = readNumber();
          token = new Token(kind, spelling);
          return token;
        }

      default:
        token = new Token(TokenType.ILLEGAL, "");
    }

    readChar();
    return token;
  }
}
