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
    val path = "/example/multiply"
    val lexer = CalculatorLexer(path.getResourceAsCharStream())
    val tokenStream = CommonTokenStream(lexer)
    val parser = CalculatorParser(tokenStream)
    println(Calculator().visitExpression(parser.expression()))
}

fun String.getResourceAsCharStream(): CharStream {
    val `is` = String.javaClass.getResourceAsStream(this)
    return CharStreams.fromStream(`is`)
}