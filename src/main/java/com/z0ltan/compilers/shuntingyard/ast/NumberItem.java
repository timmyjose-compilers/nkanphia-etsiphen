package com.z0ltan.compilers.shuntingyard.ast;

import java.util.Objects;

public class NumberItem extends Item {
  private double value;

  public NumberItem(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "NumberItem { value = " + value + " }";
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof NumberItem)) {
      return false;
    }

    NumberItem otherItem = (NumberItem) other;

    return otherItem.value == this.value;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }
}
