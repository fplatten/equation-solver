package com.showyourwork.engine.calculus;

import org.jscience.mathematics.number.Rational;

import com.showyourwork.engine.Equation;
import com.showyourwork.engine.EquationBuilder;
import com.showyourwork.engine.EquationParser;


public class Integrator {
	
	
	public static Rational trapezoidal(Equation e, Rational start,
			Rational end, double tolerance, String var) {
		
		Rational dx = end.minus(start);
		
		Rational oldValue = Rational.ZERO;
		int maxIter = 20;
		Rational oneHalf = Rational.valueOf("1/2");
		Rational sum = Rational.ZERO;
		Rational scale = Rational.ZERO;
		Rational one = Rational.ONE;
		Rational two = Rational.valueOf("2/1");
		
		e.getVariables().get(var).set(start);
		Rational s1 = e.getSumLeft().evaluate();
		
		e.getVariables().get(var).set(end);
		Rational e1 = e.getSumLeft().evaluate();		
		
		Rational value = oneHalf.times(dx).times(s1.plus(e1));
		
		System.out.println(value.doubleValue());
		
		for (int i = 0; i < maxIter; ++i) {
			sum = Rational.ZERO;
			scale = oneHalf.pow(i + 1);
			for (int j = 0; j < Math.pow(2, i); ++j) {				
				
				e.getVariables().get(var).set(start.plus((scale).times(two.times(Rational.valueOf(j + "/1")).plus(one))).times(dx));
				sum = sum.plus(e.getSumLeft().evaluate());
				
			}

			oldValue = value;
			value = (oneHalf.times(value).plus(scale).times(dx).times(sum));

			if (value.minus(oldValue).abs().doubleValue() <= tolerance * oldValue.abs().doubleValue()) {
				return value;
			}
		}		
		
		return null;
	}
	public static void main(String[] args){
		
		String line = "u^2 + 1";
		
		EquationBuilder b = new EquationBuilder();
		new EquationParser(b).parse(line);
		Equation e = b.build();
		Rational three = Rational.valueOf("3/1");		
		
		Rational result = Integrator.trapezoidal(e, Rational.ZERO, three, 1.0e-4, "u");
		
		System.out.println(result.doubleValue());
		
	}

}
