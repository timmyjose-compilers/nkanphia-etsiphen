package com.z0ltan.compilers.shuntingyard.lexer;

import com.z0ltan.compilers.shuntingyard.lexer.TokenType;

public class Token {
  private TokenType kind;
  private String spelling;

  public Token(final TokenType kind, final String spelling) {
    this.kind = kind;
    this.spelling = spelling;
  }

  public TokenType getKind() {
    return this.kind;
  }

  public String getSpelling() {
    return this.spelling;
  }

  @Override
  public String toString() {
    return "<" + kind.toString() + ", " + spelling + ">";
  }
}

