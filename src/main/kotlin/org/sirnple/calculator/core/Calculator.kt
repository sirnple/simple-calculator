package org.sirnple.calculator.core

import org.antlr.v4.runtime.tree.TerminalNode
import org.sirnple.calculator.antlr.CalculatorBaseVisitor
import org.sirnple.calculator.antlr.CalculatorLexer
import org.sirnple.calculator.antlr.CalculatorParser

/**
 *  Created by: sirnple
 *  Created in: 2020-11-26
 *  Description:
 */
class Calculator : CalculatorBaseVisitor<Double>() {
    override fun visitEquation(ctx: CalculatorParser.EquationContext?): Double {
        return super.visitEquation(ctx)
    }

    override fun visitExpression(ctx: CalculatorParser.ExpressionContext?): Double {
        var result = 0.0
        ctx?.children?.forEachIndexed { index, parseTree ->
            if (index % 2 == 0) {
                val multiplyingExpressionContext = parseTree as CalculatorParser.MultiplyingExpressionContext
                if (index == 0) {
                    result = visitMultiplyingExpression(multiplyingExpressionContext)
                } else {
                    val plusOrMinusOp = ctx.getChild(index - 1) as TerminalNode
                    if (plusOrMinusOp.symbol.type == CalculatorParser.PLUS) {
                        result += visitMultiplyingExpression(multiplyingExpressionContext)
                    } else {
                        result -= visitMultiplyingExpression(multiplyingExpressionContext)
                    }
                }
            }
        }
        return result
    }

    override fun visitMultiplyingExpression(ctx: CalculatorParser.MultiplyingExpressionContext?): Double {
        var result = 0.0
        ctx?.children?.forEachIndexed { index, parseTree ->
            if (index % 2 == 0) {
                val powExpressionContext = parseTree as CalculatorParser.PowExpressionContext
                if (index == 0) {
                    result = visitPowExpression(powExpressionContext)
                } else {
                    val terminalNode = ctx.getChild(index - 1) as TerminalNode
                    if (terminalNode.symbol.type == CalculatorParser.TIMES) {
                        result *= visitPowExpression(powExpressionContext)
                    } else {
                        result /= visitPowExpression(powExpressionContext)
                    }
                }

            }
        }
        return result
    }



    override fun visitScientific(ctx: CalculatorParser.ScientificContext?): Double {
        return ctx?.SCIENTIFIC_NUMBER()?.text?.toDouble()?:0.0
    }
}