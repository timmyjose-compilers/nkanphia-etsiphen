package com.z0ltan.compilers.shuntingyard.lexer;

public enum Precedence {
  LEVEL_TEN(10),
  LEVEL_SEVEN(7),
  LEVEL_FOUR(4),
  LEVEL_ZERO(0);

  private int level;

  private Precedence(int level) {
    this.level = level;
  }

  public int getLevel() {
    return this.level;
  }
}
