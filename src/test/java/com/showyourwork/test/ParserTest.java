package com.showyourwork.test;

import junit.framework.TestCase;

import org.jscience.mathematics.number.Rational;

import com.showyourwork.engine.Coordinate;
import com.showyourwork.engine.Derivative;
import com.showyourwork.engine.Equation;
import com.showyourwork.engine.EquationBuilder;
import com.showyourwork.engine.EquationParser;

public class ParserTest extends TestCase{
	
	
	public void testParens()
    {		
		System.out.println("testParens");
		String line = "(x-3)(x+5)-(x+2)(x-4) = 2x-5(x+4)";
		
		EquationBuilder b = new EquationBuilder();
		new EquationParser(b).parse(line);
		
		Equation e = b.build();		
		e.evaluate();
		
        assertTrue(e.getAnswers().contains(Rational.valueOf("-13/7")));
    }
	public void testDivision()
    {		
		System.out.println("testDivision");
		String line = "((4x + 3) / 5) - ((x + 4) / 3) - ((2x - 1) / 15) = 2";
		
		EquationBuilder b = new EquationBuilder();
		new EquationParser(b).parse(line);
		
		Equation e = b.build();		
		e.evaluate();
		
        assertTrue(e.getAnswers().contains(Rational.valueOf("8/1")));
    }
	public void testLongFormula()
    {		
		System.out.println("testLongFormula");
		String line = "4x - 3(2x - 5) - 9 + (3x + 4) = 12 - (x-5) - 2(7 - x) - 3x";
		
		EquationBuilder b = new EquationBuilder();
		new EquationParser(b).parse(line);
		
		Equation e = b.build();		
		e.evaluate();
		
        assertTrue(e.getAnswers().contains(Rational.valueOf("-7/3")));
    }
	public void testFractions()
    {		
		
		System.out.println("testFractions");
		String line = "[-1/2] - [1/2]";
		
		EquationBuilder b = new EquationBuilder();
		new EquationParser(b).parse(line);
		
		Equation e = b.build();		
		e.evaluate();
		
        assertTrue(e.getAnswers().contains(Rational.valueOf("-1/1")));
    }
	public void testQuadraticEquation()
    {		
		
		System.out.println("testQuadraticEquation");
		String line = "3x ^2 - 5x = 0";
		
		EquationBuilder b = new EquationBuilder();
		new EquationParser(b).parse(line);
		
		Equation e = b.build();		
		e.evaluate();
		
        assertTrue(e.getAnswers().contains(Rational.valueOf("5/3")));
        assertTrue(e.getAnswers().contains(Rational.valueOf("0/1")));
    }
	public void testFindSlope(){
		
		//(-5,2) (-1,-2)
		System.out.println("testFindSlope");
		Coordinate sub1 = new Coordinate(Rational.valueOf("-5/1"),Rational.valueOf("2/1"));
		Coordinate sub2 = new Coordinate(Rational.valueOf("-1/1"),Rational.valueOf("-2/1"));
		
		
		Rational slope = Derivative.findSlope(sub1, sub2);
		
		assertTrue(slope.equals(Rational.valueOf("-1/1")));
		
	}
	public void testFindYIntercept(){
		
		System.out.println("testFindYIntercept");
		//Rational m  = Rational.valueOf("2/9");
		//Rational y  = Rational.valueOf("-2/1");
		
		Rational m  = Rational.valueOf("2/1");
		Rational y  = Rational.valueOf("4/1");
		Rational x  = Rational.valueOf("-3/1");
		
		
		Rational b = Derivative.findYIntercept( x, y, m);
		
		//assertTrue(b.equals(Rational.valueOf("-20/9")));
		assertTrue(b.equals(Rational.valueOf("10/1")));
		
	}
	public void testFindXIntercept(){
		System.out.println("testFindXIntercept");
		//Rational m  = Rational.valueOf("2/9");
		//Rational b  = Rational.valueOf("-20/9");
		
		Rational m  = Rational.valueOf("2/1");
		Rational b  = Rational.valueOf("10/1");
		
		Rational x = Derivative.findXIntercept( b, m);
		
		//assertTrue(x.equals(Rational.valueOf("10/1")));
		assertTrue(x.equals(Rational.valueOf("-5/1")));
		
	}
}
