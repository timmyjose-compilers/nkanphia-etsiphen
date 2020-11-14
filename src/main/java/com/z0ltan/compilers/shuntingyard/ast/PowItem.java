package com.z0ltan.compilers.shuntingyard.ast;

public class PowItem extends OperatorItem {
  @Override
  public NumberItem eval(NumberItem n1, NumberItem n2) {
    return new NumberItem(Math.pow(n1.getValue(), n2.getValue()));
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof PowItem)) {
      return false;
    }

    return true;
  }
}
