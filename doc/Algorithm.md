## Setup

### Precedence

Operator | Precedence | Assocativity
____________________________________
  ^      | 10          |   Right
  *      | 7           |   Left
  /      | 7           |   Left
  +      | 4           |   Left
  -      | 4           |   Left

We consider only these primary arithmetic infix operators as well as parentheses.

All numbers, whether integers or otherwise are treated as `double`S.


## Algorithm

Setup:

Initialise an output queue (Q) which will contain the final RPN (postfix) expression.
Initialise a stack (S) for the operators,

Input:

A string of arithmetic operators, parentheses, and numbers (integers).

Procedure:

While there are tokens (characters) left
  If the token is a number, insert it at the end of Q.
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
  // if there is any left parenthesis in the stack at this stage, then it is a case of
  // mismatched parentheses again - error
  Now pop each item off of S and insert at the end of Q.
  