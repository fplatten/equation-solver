package com.showyourwork.engine.calculus;

import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Rational;

import com.showyourwork.engine.Coordinate;
import com.showyourwork.engine.Equation;
import com.showyourwork.engine.EquationBuilder;
import com.showyourwork.engine.EquationParser;

public class Differentiator {
	
	static final Rational h = Rational.valueOf("1/1");
	static final Rational two = Rational.valueOf("2/1");

	public void forward() {
	}

	public static Rational getFirstDerivative(Equation e, Rational x, String var) {

		e.getVariables().get(var).set(x.plus(h.divide(two)));

		Rational r1 = e.getSumLeft().evaluate();

		e.getVariables().get(var).set(x.minus(h.divide(two)));
		Rational r2 = e.getSumLeft().evaluate();

		return r1.minus(r2).divide(h);

	}
	public static Rational getSecondDerivative(Equation e, Rational x, String var) {

		e.getVariables().get(var).set(x);
		Rational r1 = e.getSumLeft().evaluate();

		e.getVariables().get(var).set(x.plus(h));
		Rational r2 = e.getSumLeft().evaluate();

		e.getVariables().get(var).set(x.minus(h));
		Rational r3 = e.getSumLeft().evaluate();

		Rational result = ((r2.minus(two.times(r1))).plus(r3)).divide(h).divide(h);
		
		return result;
	}

	public static Rational findSlope(Coordinate sub1, Coordinate sub2) {

		Variable.Local<Rational> varX1 = new Variable.Local<Rational>("x1");
		Variable.Local<Rational> varY1 = new Variable.Local<Rational>("y1");

		Polynomial<Rational> x1 = Polynomial.valueOf(Rational.ONE, varX1);
		Polynomial<Rational> y1 = Polynomial.valueOf(Rational.ONE, varY1);

		Variable.Local<Rational> varX2 = new Variable.Local<Rational>("x2");
		Variable.Local<Rational> varY2 = new Variable.Local<Rational>("y2");

		Polynomial<Rational> x2 = Polynomial.valueOf(Rational.ONE, varX2);
		Polynomial<Rational> y2 = Polynomial.valueOf(Rational.ONE, varY2);

		varX1.set(sub1.getX());
		varY1.set(sub1.getY());

		varX2.set(sub2.getX());
		varY2.set(sub2.getY());

		Rational m = y2.minus(y1).evaluate().divide(x2.minus(x1).evaluate());

		return m;
	}
	public static Rational findYIntercept(Coordinate sub1, Rational m) {

		String line = "mx + b = 0 + y";

		EquationBuilder b = new EquationBuilder();
		new EquationParser(b).parse(line);

		Equation e = b.build();

		e.getVariables().get("x").set(sub1.getX());
		e.getVariables().get("y").set(sub1.getY());
		e.getVariables().get("m").set(m);

		e.evaluate("b");

		return e.getAnswers().iterator().next();

	}

	public static Rational findYIntercept(Rational x, Rational y, Rational m) {

		String line = "mx + b = 0 + y";

		EquationBuilder b = new EquationBuilder();
		new EquationParser(b).parse(line);

		Equation e = b.build();

		e.getVariables().get("x").set(x);
		e.getVariables().get("y").set(y);
		e.getVariables().get("m").set(m);

		e.evaluate("b");

		return e.getAnswers().iterator().next();

	}

	public static Rational findXIntercept(Rational b, Rational m) {

		String line = "mx + b = 0 + 0";

		EquationBuilder eb = new EquationBuilder();
		new EquationParser(eb).parse(line);

		Equation e = eb.build();

		e.getVariables().get("b").set(b);
		e.getVariables().get("m").set(m);

		e.evaluate();

		return e.getAnswers().iterator().next();
	}	
}
