

fun main(args: Array<String>) {
    val mine = "¬T ^ F"
    val input = mine
    val lexer = Lexer(input)
    val interpreter = Interpreter(lexer)
    val result = interpreter.eval()
    println(result)
}


enum class Token {
    True, False, And, Or, Not, LeftParen, RightParen
}

class Lexer(private val input: String) {
    private var current = 0

    fun nextToken(): Token {
        while (current < input.length && input[current].isWhitespace()) {
            current++
        }

        if (current == input.length) {
            return Token.True
        }

        when (input[current]) {
            'T' -> {
                current++
                return Token.True
            }
            'F' -> {
                current++
                return Token.False
            }
            '^' -> {
                current++
                return Token.And
            }
            'v' -> {
                current++
                return Token.Or
            }
            '¬' -> {
                current++
                return Token.Not
            }
            '(' -> {
                current++
                return Token.LeftParen
            }
            ')' -> {
                current++
                return Token.RightParen
            }
        }
        return Token.True
    }
}

class Interpreter(private val lexer: Lexer) {
    fun eval(): Boolean {
        val token = lexer.nextToken()
        when (token) {
            Token.True -> return true
            Token.False -> return false
            Token.Not -> return !eval()
            Token.LeftParen -> {
                val result = eval()
                val rightParen = lexer.nextToken()
                if (rightParen != Token.RightParen) {
                    throw Exception("Expected RightParen")
                }
                return result
            }
            else -> {
                val left = eval()
                val op = token
                val right = eval()

                return when (op) {
                    Token.And -> left && right
                    Token.Or -> left || right
                    else -> throw Exception("Unexpected operator")
                }
            }
        }
    }
}