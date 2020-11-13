package com.z0ltan.compilers.shuntingyard.ast;

public class SubItem extends OperatorItem {
  public SubItem(int precedence, Associativity associativity) {
    super(precedence, associativity);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof SubItem)) {
      return false;
    }

    SubItem otherItem = (SubItem)other;

    return otherItem.precedence == this.precedence && otherItem.associativity == this.associativity;
  }
}

