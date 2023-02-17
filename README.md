# Boolean Expression Evaluater

This project is a basic boolean expression evaluator that uses a lexer and an interpreter to evaluate the input. The lexer reads in a string of boolean expressions and generates a stream of tokens that the interpreter then reads and evaluates.

## Syntax and Operator Precedence Rules
The boolean expression syntax includes the following operators:

  * `^` for AND
  * `v` for OR
  * `¬` for NOT
  * `()` for grouping
  
 ## Operator precedence is as follows:
  * NOT
  * AND
  * OR
  
 ## Examples
  ```
  fun main(args: Array<String>) {
    val mine = "¬T"
    val lexer = Lexer("$mine ^ (T ^ F)")
    val interpreter = Interpreter(lexer)
    val result = interpreter.eval()
    println(result)
}
```
This will evaluate the expression `"¬T ^ (T ^ F)"`, which should output `false`.
If it encounters an invalid character, it will throw an exception.
