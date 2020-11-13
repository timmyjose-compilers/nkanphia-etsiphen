package com.z0ltan.compilers.shuntingyard.ast;

public abstract class OperatorItem extends Item {
  protected int precedence;
  protected Associativity associativity;

  public OperatorItem(int precedence, Associativity associativity) {
    this.precedence = precedence;
    this.associativity = associativity;
  }

  public int getPrecedence() {
    return this.precedence;
  }

  public Associativity getAssociativity() {
    return this.associativity;
  }

  @Override
  public boolean isOperator() {
    return true;
  }
}
