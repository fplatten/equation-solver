package com.showyourwork.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.jscience.mathematics.function.Function;
import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Rational;

import com.showyourwork.engine.util.InfixToPostfixConverter;

public class SolveForXEquationBuilder  {
	
	Stack<BinaryTree<String>> stack = new Stack<BinaryTree<String>>();
	private List<Token> tokens;
	private Map<String, Variable.Local<Rational>> variables = new HashMap<String, Variable.Local<Rational>>();
	private Map<String, Polynomial<Rational>> values = new HashMap<String, Polynomial<Rational>>();	
	
	public Equation build(){
		
		Equation e = parseEquation(tokens);
		
		
		InfixToPostfixConverter itpLeft = new InfixToPostfixConverter(e.getLeftSide());
		BinaryTree<String> btLeft = convertListToTree(itpLeft.convertToPostFix());
		
		Function<Rational, Rational> sumLeft = build(btLeft.getRoot());
		
		Function<Rational, Rational> sumRight = null;
		if(e.getRightSide().size() == 1){
			e.setRightSide(appendEquation(e.getRightSide()));
		}
		
		if(e.getRightSide().size() > 1){
			InfixToPostfixConverter itpRight = new InfixToPostfixConverter(e.getRightSide());
			BinaryTree<String> btRight = convertListToTree(itpRight.convertToPostFix());			
			sumRight = build(btRight.getRoot());
		}
		
		e.setValues(values);
		e.setVariables(variables);
		e.setSumLeft(sumLeft);
		e.setSumRight(sumRight);		
		
		return e;
	}
	public BinaryTree<String> convertListToTree(List<Token> tokens) {

		Iterator<Token> it = tokens.iterator();
		BinaryTree<String> prev = null;
		while (it.hasNext()) {

			Token t = it.next();

			if (t.getName().equals("NUMBER") || t.getName().equals("FRACTION")) {
				populateTableWithTerm(t);
				getStack().push(populateBinaryTree(t));
			}

			else if (t.getName().equals("VARIABLE")) {
				populateTableWithVariable(t);
				getStack().push(populateBinaryTree(t));
			} else if (t.isOperator()) {
				prev = mergeTopElements(t.getValue());
			}
		}

		return prev;

	}
	public BinaryTree<String> mergeTopElements(String operation) {

		BinaryTree<String> right = stack.pop();
		BinaryTree<String> left = stack.pop();
		BinaryTree<String> op = new BinaryTree<String>();
		op.merge(operation, right, left);
		stack.push(op);

		op.printInOrder();

		return op;
	}

	public BinaryTree<String> populateBinaryTree(Token t) {
		return new BinaryTree<String>(t.getValue());
	}
	public Stack<BinaryTree<String>> getStack() {
		return stack;
	}

	public void setStack(Stack<BinaryTree<String>> stack) {
		this.stack = stack;
	}
	public Equation parseEquation(List<Token> tokens) {
		
		Equation e = new Equation();

		boolean right = false;
		for (Token t : tokens) {

			if (t.getName().equals("EQUAL")){
				right = true;
			}
			if (right && !t.getName().equals("EQUAL")){
				e.getRightSide().add(t);
			}				
			else {
				if (!t.getName().equals("EQUAL")){
					e.getLeftSide().add(t);
				}					
			}
		}

		return e;
	}

	public void populateTableWithVariable(Token t) {
		
		if(values.get(t.getValue()) == null){
			Variable.Local<Rational> varT1 = new Variable.Local<Rational>(t.getValue());
			Polynomial<Rational> t1 = Polynomial.valueOf(Rational.ONE, varT1);
			values.put(t.getValue(), t1);
			variables.put(t.getValue(), varT1);			
		}
	}
	public void populateTableWithTerm(Token t) {
		
		if(values.get(t.getValue()) == null){
			Variable.Local<Rational> varT1 = new Variable.Local<Rational>(t.getValue());
			Polynomial<Rational> t1 = Polynomial.valueOf(Rational.ONE, varT1);
			
			if(t.getName().equals("NUMBER")){
				long number = Long.parseLong(t.getValue());
				varT1.set(Rational.valueOf(number, 1));				
			}
			else if(t.getName().equals("FRACTION")){
				varT1.set(Rational.valueOf(t.getValue()));
			}			
			
			values.put(t.getValue(), t1);
			variables.put(t.getValue(), varT1);			
		}
	}

	public Function<Rational, Rational> build(BinaryNode<String> root) {

		if (isLiteralOperator(root.getElement().toString())) {			
			Function<Rational, Rational> left = build(root.getLeft());
			Function<Rational, Rational> right = build(root.getRight());
			String op = root.getElement().toString();
			if (op.equals("+"))	return right.plus(left);
			if (op.equals("-")) return right.minus(left);
			if (op.equals("*")) return right.times(left);
			if (op.equals("^")) return right.pow(left.evaluate().intValue());
			if (op.equals("/")) return right.divide(left);
			System.out.println("we're returning a null");
			return null;
		}
		else{
			return (Function<Rational, Rational>) values.get(root.getElement().toString());
		}
	}
	public Function<Rational, Rational> build2(BinaryNode<String> root) {

		if (isLiteralOperator(root.getElement().toString())) {			
			Function<Rational, Rational> left = build(root.getLeft());
			Function<Rational, Rational> right = build(root.getRight());
			String op = root.getElement().toString();
			if (op.equals("+"))	return right.plus(left);
			if (op.equals("-")) return right.minus(left);
			if (op.equals("*")) return right.times(left);
			if (op.equals("^")) return right.pow(left.evaluate().intValue());
			if (op.equals("/")) return right.divide(left);
			System.out.println("we're returning a null");
			return null;
		}
		else{
			return (Function<Rational, Rational>) values.get(root.getElement().toString());
		}
	}

	public static boolean isNumber(String str) {

		boolean digit = false;

		try {
			Integer.parseInt(str);
			digit = true;
		} catch (NumberFormatException e) {
		}

		return digit;
	}
	public boolean isLiteralOperator(String str) {

		boolean operator = false;

		for (int i = 0; i < EquationConstants.literalmathoperations.length; ++i) {

			if (str.equals(EquationConstants.literalmathoperations[i])) {
				return true;
			}
		}
		return operator;
	}
	public List<Token> appendEquation(List<Token> list) {
		
		List<Token> basicEquation = new ArrayList<Token>();
		
		basicEquation.add(list.get(0));		
		
		Token nt = new Token();
		nt.setValue("+");
		nt.setName("PLUS");
		basicEquation.add(nt);
		
		Token nt2 = new Token();
		nt2.setValue("0");
		nt2.setName("NUMBER");
		basicEquation.add(nt2);		
		
		return basicEquation;
	}
	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
}
