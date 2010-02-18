package com.showyourwork.engine.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.showyourwork.engine.Token;

public class InfixToPostfixConverter {

	StringBuffer infix = new StringBuffer();
	//StringBuffer postfix = new StringBuffer();
	Stack<Token> stack = new Stack<Token>();
	List<Token> tokens = new ArrayList<Token>();
	List<Token> postfix = new ArrayList<Token>();
	//String[] operations = { "%", "^", "/", "*", "-", "+" };
	String[] operations = { "+", "-", "/", "%", "*", "^" };
	int currentprecedence = -1;
	
	public InfixToPostfixConverter(){}
	
	public InfixToPostfixConverter(List<Token> tokens){
		this.tokens = tokens;
	}
	
	public List<Token> convertToPostFix() {
		
		Token etoken = new Token();
		etoken.setLocation(999999);
		etoken.setName("OPENPAREN");
		etoken.setValue("(");
		stack.push(etoken);
		
		int precedence = -1;
		
		Iterator iterator = tokens.iterator();
		
		while (iterator.hasNext()) {

			Token t = (Token) iterator.next();			
			
				//System.out.println("convertToPostFix token = " + t.getValue());
				if(t.getName().equals("NUMBER") || t.getName().equals("FRACTION"))
					postfix.add(t);
				
				if(t.getName().equals("VARIABLE"))
					postfix.add(t);	
				
				if (isOperator(t.getValue())) {
					precedence = getPrecedence(t.getValue());
					processOperator(t, precedence);
					/*
					 * pop ops from stack with equal or higher precedence 
					 * and append to postfix
					 */

				}

				if (t.getName().equals("OPENPAREN"))
					stack.push(t);

				if (t.getName().equals("CLOSEPAREN"))
					processRightParen();		

		}
		
		processRightParen();
		
		return postfix;

	}
	
	public boolean isDigit(String str) {

		boolean digit = false;

		try {
			int v = Integer.parseInt(str);
			digit = true;
		} catch (NumberFormatException e) {
		}

		return digit;
	}
	public boolean isOperator(String str) {

		boolean operator = false;

		for (int i = 0; i < operations.length; ++i) {

			if (str.equals(operations[i])) {

				operator = true;
			}

		}

		return operator;

	}
	public boolean isVariable(String str){
		
		boolean letter = false;		
		
		if( Character.isLetter(str.charAt(0)) )
			letter = true;		
		
		return letter;
	}
	public int getPrecedence(String str) {

		int p = -1;

		for (int i = 0; i < operations.length; ++i) {

			if (str.equals(operations[i])) {

				p = i;
			}

		}

		return p;

	}
	public void processOperator(Token t, int p) {

		boolean cont = true;

		while (cont) {

			Token top = (Token) stack.peek();
			if (isOperator(top.getValue())) {
				int tpresedence = getPrecedence(top.getValue());
				if (tpresedence >= p) {
					postfix.add( (Token) stack.pop());
				} else {
					stack.push(t);
					cont = false;
				}
			} else {
				stack.push(t);
				cont = false;

			}

		}

	}
	public void processRightParen() {

		boolean cont = true;

		while (cont) {

			Token top = (Token) stack.peek();

			if (top.getName().equals("OPENPAREN")) {
				stack.pop();
				cont = false;

			} else{
				//if (isOperator(top))
				//	postfix.append(" ");								
				postfix.add( (Token) stack.pop());				
			}
				

		}

	}

	/**
	 * @return Returns the tokens.
	 */
	public List getTokens() {
		return tokens;
	}
	/**
	 * @param tokens The tokens to set.
	 */
	public void setTokens(List tokens) {
		this.tokens = tokens;
	}
	public List<Token> getPostfix() {
		return postfix;
	}
	public void setPostfix(List<Token> postfix) {
		this.postfix = postfix;
	}
	
}
