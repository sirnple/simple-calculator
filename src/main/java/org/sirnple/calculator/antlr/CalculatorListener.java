// Generated from /Users/apple/IdeaProjects/SirnpleCalculator/src/main/resources/antlr/Calculator.g4 by ANTLR 4.8
package org.sirnple.calculator.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculatorParser}.
 */
public interface CalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#equation}.
	 * @param ctx the parse tree
	 */
	void enterEquation(CalculatorParser.EquationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#equation}.
	 * @param ctx the parse tree
	 */
	void exitEquation(CalculatorParser.EquationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CalculatorParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CalculatorParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyingExpression(CalculatorParser.MultiplyingExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyingExpression(CalculatorParser.MultiplyingExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#powExpression}.
	 * @param ctx the parse tree
	 */
	void enterPowExpression(CalculatorParser.PowExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#powExpression}.
	 * @param ctx the parse tree
	 */
	void exitPowExpression(CalculatorParser.PowExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#signedAtom}.
	 * @param ctx the parse tree
	 */
	void enterSignedAtom(CalculatorParser.SignedAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#signedAtom}.
	 * @param ctx the parse tree
	 */
	void exitSignedAtom(CalculatorParser.SignedAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CalculatorParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CalculatorParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#scientific}.
	 * @param ctx the parse tree
	 */
	void enterScientific(CalculatorParser.ScientificContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#scientific}.
	 * @param ctx the parse tree
	 */
	void exitScientific(CalculatorParser.ScientificContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(CalculatorParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(CalculatorParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(CalculatorParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(CalculatorParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(CalculatorParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(CalculatorParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#funcname}.
	 * @param ctx the parse tree
	 */
	void enterFuncname(CalculatorParser.FuncnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#funcname}.
	 * @param ctx the parse tree
	 */
	void exitFuncname(CalculatorParser.FuncnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#relop}.
	 * @param ctx the parse tree
	 */
	void enterRelop(CalculatorParser.RelopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#relop}.
	 * @param ctx the parse tree
	 */
	void exitRelop(CalculatorParser.RelopContext ctx);
}