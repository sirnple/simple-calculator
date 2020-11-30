package org.sirnple.calculator.core

import org.antlr.v4.runtime.tree.TerminalNode
import org.apache.commons.math3.complex.Complex
import org.sirnple.calculator.antlr.CalculatorBaseVisitor
import org.sirnple.calculator.antlr.CalculatorLexer
import org.sirnple.calculator.antlr.CalculatorParser
import org.sirnple.calculator.util.ASTUtils
import java.lang.UnsupportedOperationException
import kotlin.math.pow

/**
 *  Created by: sirnple
 *  Created in: 2020-11-26
 *  Description:
 */
class Calculator : CalculatorBaseVisitor<Double>() {
    private val variableTable = hashMapOf<String, Double>()
    override fun visitEquation(ctx: CalculatorParser.EquationContext?): Double {
        if (ctx?.relop() != null && ctx.relop().EQ() != null && ASTUtils.isExprVariable(ctx.expression(0))) {
            variableTable[ctx.text] = visitExpression(ctx.expression(1))
            println("New var ${ctx.text} has been init")
        }
        return Double.NaN
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

    override fun visitPowExpression(ctx: CalculatorParser.PowExpressionContext?): Double {
        val signedAtoms = ctx?.signedAtom()
        val baseNumb = visitSignedAtom(signedAtoms?.get(0))
        val exponentNumb = signedAtoms?.size?:0.0
        return baseNumb.pow(exponentNumb.toDouble())
    }

    override fun visitSignedAtom(ctx: CalculatorParser.SignedAtomContext?): Double {
        return if (ctx?.signedAtom() != null) {
            if (ctx.PLUS() != null) {
                -visitSignedAtom(ctx.signedAtom())
            } else {
                visitSignedAtom(ctx.signedAtom())
            }
        } else if (ctx?.func() != null) {
            visitFunc(ctx.func())
        } else {
            visitAtom(ctx?.atom())
        }
    }

    override fun visitAtom(ctx: CalculatorParser.AtomContext?): Double {
        return when {
            ctx?.scientific() != null -> {
                visitScientific(ctx.scientific())
            }
            ctx?.variable() != null -> {
                visitVariable(ctx.variable())
            }
            ctx?.constant() != null -> {
                visitConstant(ctx.constant())
            }
            else -> {
                visitExpression(ctx?.expression())
            }
        }
    }

    override fun visitScientific(ctx: CalculatorParser.ScientificContext?): Double {
        return ctx?.SCIENTIFIC_NUMBER()?.text?.toDouble()?:0.0
    }

    override fun visitConstant(ctx: CalculatorParser.ConstantContext?): Double {
        return when {
            ctx?.PI() != null -> {
                Math.PI
            }
            ctx?.EULER() != null -> {
                Math.E
            }
            else -> {
                throw UnsupportedOperationException("Complex is not supported")
            }
        }
    }

    override fun visitVariable(ctx: CalculatorParser.VariableContext?): Double {
        return if (variableTable[ctx?.text] != null) {
            variableTable[ctx?.text]!!
        } else {
            println("No such variable [${ctx?.text}]")
            Double.NaN
        }
    }
}