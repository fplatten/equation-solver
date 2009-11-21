package com.showyourwork.engine;

import java.util.HashSet;
import java.util.Set;

import org.jscience.mathematics.number.Rational;

public class EquationConstants {
	
	
	public static final String[] mathoperations = { "PLUS", "MINUS", "DIVIDE", "MODULUS", "MULTIPLY", "EXPONENT" };
	public static final String[] literalmathoperations = { "+", "-", "/", "%", "*", "^" };
	public static final String[] srchstrings = { "([0-9]+)", "([A-Za-z])", "([^=<>!]=[^=])",
		"(\\+)", "(\\-)", "(/)", "(\\%)", "(\\*)", "(\\^)", "(\\()",
		"(\\))", "(\\[)","(\\])","(>[^=])", "(<[^=])", "(<=)", "(>=)", "(==)", "(!=)" };
	public static final String[] tokennames = { "NUMBER", "WORD", "EQUAL", "PLUS", "MINUS",
		"DIVIDE", "MODULUS", "MULTIPLY", "EXPONENT", "OPENPAREN",
		"CLOSEPAREN", "OPENBRAC","CLOSEBRAC","GREATERTHAN", "LESSTHAN", "LESSTHANEQUALTO",
		"GREATERTHANEQUALTO", "EQUALTO", "NOTEQUALTO" };
	public static final String[] reserved = { "rem", "input", "let", "print", "goto", "if", "end" };
	
	public static final Set<Rational> fractions = new HashSet<Rational>();
	
	static{
		Rational fraction = Rational.ZERO;

		for (int i = 1; i <= 25; ++i) {

			for (int j = 1; j < i; ++j) {

				fraction = Rational.valueOf("" + j + "/" + i);
				if (!fractions.contains(fraction)) {
					fractions.add(fraction);
				}
			}
		}
	}
	
	public static String transpose(String operation){		
		
		char opchar = operation.charAt(0);
		
		switch (opchar) {
		
			case '+' : return "-";
			case '-' : return "+";
			case '*' : return "/";
			case '/' : return "*";
		
		}
		
		return null;
	}

}
