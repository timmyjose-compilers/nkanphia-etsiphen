package com.z0ltan.compilers.shuntingyard.ast;

public abstract class OperatorItem extends Item {
  public abstract NumberItem eval(NumberItem n1, NumberItem n2);

  @Override
  public boolean isOperator() {
    return true;
  }
}
