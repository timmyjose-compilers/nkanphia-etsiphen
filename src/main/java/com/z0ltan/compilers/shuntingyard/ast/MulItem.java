package com.z0ltan.compilers.shuntingyard.ast;

public class MulItem extends OperatorItem {
  public MulItem(int precedence, Associativity associativity) {
    super(precedence, associativity);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof MulItem)) {
      return false;
    }

    MulItem otherItem = (MulItem)other;

    return otherItem.precedence == this.precedence && otherItem.associativity == this.associativity;
  }
}
