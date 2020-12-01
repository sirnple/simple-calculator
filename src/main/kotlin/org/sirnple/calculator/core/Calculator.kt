package org.sirnple.calculator.core

import org.antlr.v4.runtime.tree.TerminalNode
import org.sirnple.calculator.antlr.CalculatorBaseVisitor
import org.sirnple.calculator.antlr.CalculatorParser
import java.lang.UnsupportedOperationException
import java.math.BigDecimal
import kotlin.math.pow

/**
 *  Created by: sirnple
 *  Created in: 2020-11-26
 *  Description:
 */
object Calculator : CalculatorBaseVisitor<Double>() {
    private val variableTable = hashMapOf<String, Double>()
    override fun visitStart(ctx: CalculatorParser.StartContext?): Double {
        if (ctx == null) {
            return Double.NaN
        }
        return if (ctx.EQ() != null) {
            variableTable[ctx.VARIABLE().text] = visitExpression(ctx.expression())
            Double.NaN
        } else {
            visitExpression(ctx.expression())
        }
    }

    override fun visitExpression(ctx: CalculatorParser.ExpressionContext?): Double {
        if (ctx == null) {
            return Double.NaN
        }
        var result = 0.0
        ctx.children.forEachIndexed { index, parseTree ->
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
        if (ctx == null) {
            return Double.NaN
        }
        var result = 0.0
        ctx.children.forEachIndexed { index, parseTree ->
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

    override fun visitPowExpression(ctx: CalculatorParser.PowExpressionContext?): Double {
        if (ctx == null) {
            return Double.NaN
        }
        val signedAtoms = ctx.signedAtom()
        return if (signedAtoms.size > 1) {
            var exponent = 0.0
            for (i in signedAtoms.lastIndex downTo 1) {
                exponent = if (i == signedAtoms.lastIndex) {
                    visitSignedAtom(signedAtoms[i])
                } else {
                    visitSignedAtom(signedAtoms[i]).pow(exponent)
                }
            }
            visitSignedAtom(signedAtoms[0]).pow(exponent)
        } else {
            visitSignedAtom(signedAtoms[0])
        }
    }

    override fun visitSignedAtom(ctx: CalculatorParser.SignedAtomContext?): Double {
        if (ctx == null) {
            return Double.NaN
        }
        return if (ctx.signedAtom() != null) {
            if (ctx.PLUS() != null) {
                -visitSignedAtom(ctx.signedAtom())
            } else {
                visitSignedAtom(ctx.signedAtom())
            }
        } else if (ctx.func() != null) {
            visitFunc(ctx.func())
        } else {
            visitAtom(ctx.atom())
        }
    }

    override fun visitAtom(ctx: CalculatorParser.AtomContext?): Double {
        if (ctx == null) {
            return Double.NaN
        }
        return when {
            ctx.scientific() != null -> {
                visitScientific(ctx.scientific())
            }
            ctx.variable() != null -> {
                visitVariable(ctx.variable())
            }
            ctx.constant() != null -> {
                visitConstant(ctx.constant())
            }
            else -> {
                visitExpression(ctx.expression())
            }
        }
    }

    override fun visitScientific(ctx: CalculatorParser.ScientificContext?): Double {
        if (ctx == null) {
            return Double.NaN
        }
        return BigDecimal(ctx.SCIENTIFIC_NUMBER().text).toDouble()
    }

    override fun visitConstant(ctx: CalculatorParser.ConstantContext?): Double {
        if (ctx == null) {
            return Double.NaN
        }
        return when {
            ctx.PI() != null -> {
                Math.PI
            }
            ctx.EULER() != null -> {
                Math.E
            }
            else -> {
                throw UnsupportedOperationException("Complex is not supported")
            }
        }
    }

    override fun visitVariable(ctx: CalculatorParser.VariableContext?): Double {
        if (ctx == null) {
            return Double.NaN
        }
        return if (variableTable[ctx.text] != null) {
            variableTable[ctx.text]!!
        } else {
            println("No such variable [${ctx.text}]")
            Double.NaN
        }
    }

    fun clear() {
        variableTable.clear()
    }
}