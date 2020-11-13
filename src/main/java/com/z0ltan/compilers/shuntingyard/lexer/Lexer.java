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

  String readInteger() {
    int startPosition = position;
    while (Character.isDigit(currentChar)) {
      readChar();
    }
    return source.substring(startPosition, position);
  }

  public Token nextToken() {
    Token token = null;

    skipWhitespace();

    switch (currentChar) {
      case '\u0000':
        token = new Token(TokenType.EOF, ""); break;
      case '.':
        token = new Token(TokenType.PERIOD, "."); break;
      case '^':
        token = new Token(TokenType.CARET, "^"); break;
      case '+':
        token = new Token(TokenType.PLUS, "+"); break;
      case '-':
        token = new Token(TokenType.MINUS, "-"); break;
      case '*':
        token = new Token(TokenType.ASTERISK, "*"); break;
      case '/':
        token = new Token(TokenType.SLASH, "/"); break;
      case '~':
        token = new Token(TokenType.TILDE, "~"); break;
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
          String spelling = readInteger();
          token = new Token(kind, spelling);
          return token;
        }

      case 'a':
      case 'b':
      case 'c':
      case 'd':
      case 'e':
      case 'f':
      case 'g':
      case 'h':
      case 'i':
      case 'j':
      case 'k':
      case 'l':
      case 'm':
      case 'n':
      case 'o':
      case 'p':
      case 'q':
      case 's':
      case 't':
      case 'u':
      case 'v':
      case 'w':
      case 'x':
      case 'y':
      case 'z':
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      case 'G':
      case 'H':
      case 'I':
      case 'J':
      case 'K':
      case 'L':
      case 'M':
      case 'N':
      case 'O':
      case 'P':
      case 'Q':
      case 'S':
      case 'T':
      case 'U':
      case 'V':
      case 'W':
      case 'X':
      case 'Y':
      case 'Z': 
        {
          TokenType kind = TokenType.FUNCTION;
          String spelling = readFunctionName();
          token  = new Token(kind, spelling);
          return token;
        }

      default:
        token = new Token(TokenType.ILLEGAL, "");
    }

    readChar();
    return token;
  }
}
