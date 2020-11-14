package com.z0ltan.compilers.shuntingyard.parser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import com.z0ltan.compilers.shuntingyard.lexer.Lexer;
import com.z0ltan.compilers.shuntingyard.lexer.Token;
import com.z0ltan.compilers.shuntingyard.lexer.TokenType;
import com.z0ltan.compilers.shuntingyard.lexer.Associativity;
import com.z0ltan.compilers.shuntingyard.lexer.Precedence;
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

  Item makeItem(Token token) {
    switch (token.getKind()) {
      case OPERATOR:
        switch (token.getSpelling()) {
          case "+": return new AddItem();
          case "-": return new SubItem();
          case "*": return new MulItem();
          case "/": return new DivItem();
          case "^": return new PowItem();
          default: throw new RuntimeException("unknown operator - " + token.getSpelling());
        }
      case NUMBER: return new NumberItem(Double.parseDouble(token.getSpelling()));
      default: throw new RuntimeException("unexpected token kind - " + token.getKind());
    }
  }

  void executeShuntingYard() {
    Stack<Token> st = new Stack<>();

    while (currentToken.getKind() != TokenType.EOF) {
      switch (currentToken.getKind()) {
        case NUMBER:
          {
            ast.add(makeItem(currentToken));
            acceptIt();
          }
          break;

        case OPERATOR:
          {
            OperatorItem op = (OperatorItem)makeItem(currentToken);

            while (!st.isEmpty() && 
                ((currentToken.getAssociativity() == Associativity.LEFT && st.peek().getPrecedence().getLevel() >= currentToken.getPrecedence().getLevel()) ||
                 (currentToken.getAssociativity() == Associativity.RIGHT && st.peek().getPrecedence().getLevel() > currentToken.getPrecedence().getLevel()))) {
              ast.add(makeItem(st.pop()));
                 }

            st.push(currentToken);
            acceptIt();
          }
          break;

        case LPAREN:
          {
            st.push(currentToken);
            acceptIt();
          }
          break;

        case RPAREN:
          {
            while (!st.isEmpty() && !st.peek().getSpelling().equals("(")) {
              ast.add(makeItem(st.pop()));
            }

            if (st.isEmpty()) {
              throw new RuntimeException("imbalanced parentheses - could not find matching left parenthesis");
            }

            st.pop();
            acceptIt();
          }
          break;

        case ILLEGAL: throw new RuntimeException("got an illegal token");
      }
    }

    while (!st.isEmpty()) {
      if (st.peek().getSpelling().equals("(")) {
        throw new RuntimeException("imbalanced parentheses - extra left parenthesis left over");
      }

      ast.add(makeItem(st.pop()));
    }
  }
}
