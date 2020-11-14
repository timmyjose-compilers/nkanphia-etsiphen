package com.z0ltan.compilers.shuntingyard.ast;

public class SubItem extends OperatorItem {
  @Override
  public NumberItem eval(NumberItem n1, NumberItem n2) {
    return new NumberItem(n1.getValue() - n2.getValue());
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof SubItem)) {
      return false;
    }
    return true;
  }
}

