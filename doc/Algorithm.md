## Setup

### Precedence

Operator | Precedence | Assocativity
____________________________________
  ~      | 10         |   Right (negation) 
  ^      | 7          |   Right
  *      | 4          |   Left
  /      | 4          |   Left
  +      | 1          |   Left
  -      | 1          |   Left

We consider only these primary arithmetic infix operators as well as parentheses.

As for numbers, only `int`S are supported.

The following mathematical functions are supported (as found in the `java.math` package):

```
 	abs(double a) 	
 	abs(int a) 	
 	acos(double a) 	
 	asin(double a) 	
 	atan(double a) 	
 	cbrt(double a) 	
 	ceil(double a) 	
 	cos(double a) 	
 	cos(double x) 	
 	decrementExact(int a) 	
 	exp(double a) 	
 	expm1(double x) 	
 	floor(double a) 	
 	getExponent(double d) 	
 	incrementExact(int a) 	
 	log(double a) 	
 	log10(double a) 	
 	log1p(double x) 	
 	negateExact(int a) 	
 	nextDown(double d) 	
 	nextUp(double d) 	
 	random() 	
 	rint(double a) 	
 	round(double a) 	
 	signum(double d) 	
 	sin(double a) 	
 	sinh(double x) 	
 	sqrt(double a) 	
 	tan(double a) 	
 	tanh(double x) 	
 	toDegrees(double angrad) 	
 	toRadians(double angdeg) 	
 	ulp(double d) 	
```

## Algorithm

Here is the adapted algorithm from [Andrew Wei's blogpost](https://www.andr.mu/logs/the-shunting-yard-algorithm/):

Setup:

Initialise an output queue (Q) which will contain the final RPN (postfix) expression.
Initialise a stack (S) for the operators,

Input:

A string of arithmetic operators, parentheses, and numbers (integers).

Procedure:

While there are tokens (characters) left
  If the token is a number, insert it at the end of Q.
  If the token is a unary postfix operator, insert it at the end of Q.
  If the token is a unary prefix operator, push it onto S.
  If the token is a function, push it onto S.
  If the token is an operator
    If the operator is left-associative
      while S contains an operator of equal/higher precedence
        Pop S and insert at the end of Q.
        Push the token onto S.
    If the operator is right-associative
      while S contains an operator of higher precedence
        Pop S and insert as the end of Q.
        Push the token onto S.
  If the token is a parenthesis
    If the token is a left parenthesis
      Push the token onto S.
    If the token is a right parenthesis
      // if the stack becomes empty without finding a left parenthesis, then the parentheses
      // are mismatched - error
      while the top of the Stack is not a left parenthesis
        Pop item from S and insert at the end of Q.
      Pop the left parenthesis off of S.
      If the top of the stack is now a function, pop it and insert at the end of Q.
  // if there is any left parenthesis in the stack at this stage, then it is a case of
  // mismatched parentheses again - error
  Now pop each item off of S and insert at the end of Q.
  