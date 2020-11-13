package com.z0ltan.compilers.shuntingyard.ast;

public class PowItem extends OperatorItem {
  public PowItem(int precedence, Associativity associativity) {
    super(precedence, associativity);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof PowItem)) {
      return false;
    }

    PowItem otherItem = (PowItem)other;

    return otherItem.precedence == this.precedence && otherItem.associativity == this.associativity;
  }
}
