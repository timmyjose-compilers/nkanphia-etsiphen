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

### Conversion of string infix input to RPN (postfix)

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
  
## Evaluation of RPN expression

The result of parsing the string input is a queue of `Item` objects, where each item pertains to an arithmetic entity - a number or an operator.
The basic algorithm of evaluation of a queue of such entities is straightforward:

Setup:

Initialise an empty stack, S, for carrying out the actual computation.

While the queue, Q, is not empty:
  Poll the first element of the Q. 
  If the element is a number, push onto S.
  If the element is an operator
    // If we don't have two elements at the top of
    // S, it is an invalid expression
    Pop off the top element from S, E1
    Pop off the top element from S, E2
    If the operator is left-associative
      Evaluate E1 <operator> E2 and push result onto S.
    If the operator is right-associative
      Evaluate E2 <operator> E1 and push result onto S.
  If the element is some other type, error.
// if S is not empty at this stage, the expression is malformed/invalid
After Q is empty, S should contain only one element, the result.

      
  