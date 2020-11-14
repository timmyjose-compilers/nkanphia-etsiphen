package com.z0ltan.compilers.shuntingyard.lexer;

import com.z0ltan.compilers.shuntingyard.lexer.TokenType;
import com.z0ltan.compilers.shuntingyard.lexer.Associativity;
import com.z0ltan.compilers.shuntingyard.lexer.Precedence;

public class Token {
  private TokenType kind;
  private String spelling;
  private Associativity associativity;
  private Precedence precedence;

  public Token(final TokenType kind, final String spelling) {
    this.kind = kind;
    this.spelling = spelling;
    this.associativity = Associativity.NONE;
    this.precedence = Precedence.LEVEL_ZERO;
  }

  public Token(final TokenType kind, final String spelling, Precedence precedence, Associativity associativity) {
    this.kind = kind;
    this.spelling = spelling;
    this.precedence = precedence;
    this.associativity = associativity;
  }

  public TokenType getKind() {
    return this.kind;
  }

  public String getSpelling() {
    return this.spelling;
  }

  public Precedence getPrecedence() {
    return this.precedence;
  }

  public Associativity getAssociativity() {
    return this.associativity;
  }

  @Override
  public String toString() {
    return "<" + kind.toString() + ", " + spelling + ">";
  }
}

