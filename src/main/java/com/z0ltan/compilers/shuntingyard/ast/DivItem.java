package com.z0ltan.compilers.shuntingyard.ast;

public class DivItem extends OperatorItem {
  public DivItem(int precedence, Associativity associativity) {
    super(precedence, associativity);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof DivItem)) {
      return false;
    }

    DivItem otherItem = (DivItem)other;

    return otherItem.precedence == this.precedence && otherItem.associativity == this.associativity;
  }
}
