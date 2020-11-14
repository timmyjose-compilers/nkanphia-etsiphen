package com.z0ltan.compilers.shuntingyard.evaluator;

import java.util.Queue;
import java.util.Stack;
import com.z0ltan.compilers.shuntingyard.parser.Parser;
import com.z0ltan.compilers.shuntingyard.ast.*;

public class Evaluator {
  private Parser parser;

  public Evaluator(Parser parser) {
    this.parser = parser;
  }

  void checkEmpty(Stack st, String message) {
    if (st.isEmpty()) {
      throw new RuntimeException(message);
    }
  }

  public double evaluate() {
    Queue<Item> q = this.parser.parse();
    Stack<Item> st = new Stack<>();

    while (!q.isEmpty()) {
      Item elem = q.remove();

      if (elem instanceof NumberItem) {
        st.push((NumberItem)elem);
      } else if (elem.isOperator()) {
        OperatorItem op = (OperatorItem)elem;

        checkEmpty(st, "Malformed expression - expected operand, foumnd none.");
        NumberItem n1 = (NumberItem)st.pop();

        checkEmpty(st, "malformed expression - expected another operand, found none.");
        NumberItem n2 = (NumberItem)st.pop();

        st.push(op.eval(n2, n1));
      } else {
        throw new RuntimeException("invalid operand or operator found");
      }
    }

    if (st.size() != 1 || !(st.peek() instanceof NumberItem)) {
      throw new RuntimeException("invalid expression");
    }

    return ((NumberItem)st.pop()).getValue();
  }
}
