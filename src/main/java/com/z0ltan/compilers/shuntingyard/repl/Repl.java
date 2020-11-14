package com.z0ltan.compilers.shuntingyard.repl;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import com.z0ltan.compilers.shuntingyard.lexer.Lexer;
import com.z0ltan.compilers.shuntingyard.parser.Parser;
import com.z0ltan.compilers.shuntingyard.evaluator.Evaluator;

public class Repl {
  private static final String PROMPT = ">> ";

  public void run() {
    System.out.println("\nWelcome to a demonstration of the Shunting Yard Algorithm.");
    System.out.println("Enter arithmetic expressions to be evaluated. Press Ctrl+C to quit\n");

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
      while (true) {
        System.out.print(PROMPT);
        System.out.flush();
        String input = reader.readLine();
        Evaluator evaluator = new Evaluator(new Parser(new Lexer(input)));
        System.out.println(evaluator.evaluate());
      }
    } catch (IOException ex) {
      System.err.println("An IO error occurred - " + ex.getLocalizedMessage());
    } catch (Throwable err) {
      System.err.println("Something else went wrong - " + err.getLocalizedMessage());
    }
  }
}
