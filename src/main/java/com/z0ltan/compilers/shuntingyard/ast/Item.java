package com.z0ltan.compilers.shuntingyard.ast;

public abstract class Item {
  public boolean isOperator() {
    return false;
  }
}
