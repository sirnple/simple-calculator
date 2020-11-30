package org.sirnple.calculator.core

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.sirnple.calculator.antlr.CalculatorLexer
import org.sirnple.calculator.antlr.CalculatorParser
import java.nio.file.Path

/**
 *  Created by: sirnple
 *  Created in: 2020-11-26
 *  Description:
 */
fun main() {
    while (true) {
        val readLine = readLine()
        readLine?.let {
            val evalResult = evaluate(it)
            println(evalResult)
        }
    }
}

fun evaluate(line: String): Double {
    val lexer = CalculatorLexer(CharStreams.fromString(line))
    val tokenStream = CommonTokenStream(lexer)
    val parser = CalculatorParser(tokenStream)
    return parser.equation().accept(Calculator())
}

fun String.getResourceAsCharStream(): CharStream {
    val `is` = String.javaClass.getResourceAsStream(this)
    return CharStreams.fromStream(`is`)
}

