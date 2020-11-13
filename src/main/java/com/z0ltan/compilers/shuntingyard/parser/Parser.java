package com.z0ltan.compilers.shuntingyard.parser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import com.z0ltan.compilers.shuntingyard.lexer.Lexer;
import com.z0ltan.compilers.shuntingyard.lexer.Token;
import com.z0ltan.compilers.shuntingyard.lexer.TokenType;
import com.z0ltan.compilers.shuntingyard.ast.*;

public class Parser {
  private Lexer lexer;
  private Token currentToken;
  private Queue<Item> ast;

  public Parser(Lexer lexer) {
    this.lexer = lexer;
    this.ast = new LinkedList();
    acceptIt();
  }

  void acceptIt() {
    currentToken = lexer.nextToken();
  }

  void accept(TokenType kind) {
    if (currentToken.getKind() != kind) {
      throw new RuntimeException("expected to accept token of kind " + kind + ", got token of kind " + currentToken.getKind());
    }
    currentToken = lexer.nextToken();
  }

  public Queue<Item> parse() {
    executeShuntingYard();
    accept(TokenType.EOF);
    return ast;
  }

  void executeShuntingYard() {
    Stack<Item> st = new Stack<>();

    while (currentToken.getKind() != TokenType.EOF) {
      switch (currentToken.getKind()) {
        case NUMBER:
          {
            ast.add(new NumberItem(Double.parseDouble(currentToken.getSpelling())));
            acceptIt();
          }
          break;

        case LEFT_OPERATOR:
          {
            OperatorItem op = null;
            switch (currentToken.getSpelling()){
              case "+" : op = new AddItem(4, Associativity.LEFT); break;
              case "-": op = new SubItem(4, Associativity.LEFT); break;
              case "*": op = new MulItem(7, Associativity.LEFT); break;
              case "/": op = new DivItem(7, Associativity.LEFT); break;
              default: throw new RuntimeException("unknown left associative operator - " + currentToken.getSpelling());
            }
            while (!st.isEmpty() && st.peek().isOperator() && ((OperatorItem)st.peek()).getPrecedence() >= op.getPrecedence()) {
              ast.add(st.pop());
            }

            st.push(op);
            acceptIt();
          }
          break;

        case RIGHT_OPERATOR:
          {
            OperatorItem op = null;
            switch (currentToken.getSpelling()) {
              case "^": op = new PowItem(10, Associativity.RIGHT); break;
              default:
                        throw new RuntimeException("unknown right-associative operator - " + currentToken.getSpelling());

            }

            while (!st.isEmpty() && st.peek().isOperator() && ((OperatorItem)st.peek()).getPrecedence() > op.getPrecedence()) {
              ast.add(st.pop());
            }
            st.push(op);
            acceptIt();
          }
          break;

        case LPAREN:
          {
            Item leftParen = new LParenItem();
            st.push(leftParen);
            acceptIt();
          }
          break;

        case RPAREN:
          {
            while (!st.isEmpty() && !(st.peek() instanceof LParenItem)) {
              ast.add(st.pop());
            }

            if (st.isEmpty()) {
              throw new RuntimeException("imbalanced parentheses - could not find matching left parenthesis");
            }

            st.pop();
            acceptIt();
          }
      }

    }
    while (!st.isEmpty()) {
      if (st.peek() instanceof LParenItem) {
        throw new RuntimeException("imbalanced parentheses - extra left parenthesis left over");
      }
      ast.add(st.pop());
    }
  }
}
