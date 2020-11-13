package com.z0ltan.compilers.shuntingyard.ast;

public class AddItem extends OperatorItem {
  public AddItem(int precedence, Associativity associativity) {
    super(precedence, associativity);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof AddItem)) {
      return false;
    }

    AddItem otherItem = (AddItem)other;

    return otherItem.precedence == this.precedence && otherItem.associativity == this.associativity;
  }
}
