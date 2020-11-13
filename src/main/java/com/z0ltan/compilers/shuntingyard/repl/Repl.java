package com.z0ltan.compilers.shuntingyard.repl;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Repl {
  public void run() {
    System.out.println("Welcome to a demonstration of the Shunting Yard Algorithm.");
    System.out.println("Enter arithmetic expressions to be evaluated. Press Ctrl+C to quit");

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
      while (true) {
        String input = reader.readLine();
        System.out.println("You entered " + input);
      }
    } catch (IOException ex) {
      System.err.println("An IO error occurred - " + ex.getLocalizedMessage());
    } catch (Throwable err) {
      System.err.println("Something else went wrong - " + err.getLocalizedMessage());
    }
  }
}
