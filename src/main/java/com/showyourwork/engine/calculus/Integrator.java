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

		for (int i = 0; i < maxIter; ++i) {
			sum = Rational.ZERO;
			scale = oneHalf.pow(i + 1);

			System.out.println("Math.pow(2, i) = " + Math.pow(2, i));

			for (int j = 0; j < 16; ++j) {

				e.getVariables().get(var).set(
						start.plus(
								(scale).times(two.times(
										Rational.valueOf(j + "/1")).plus(one)))
								.times(dx));
				sum = sum.plus(e.getSumLeft().evaluate());

				// System.out.println("sum = " + sum);

			}

			oldValue = value;
			value = (oneHalf.times(value).plus(scale).times(dx).times(sum));

			if (value.minus(oldValue).abs().doubleValue() <= tolerance
					* oldValue.abs().doubleValue()) {
				return value;
			}
		}

		return null;
	}

	public static Rational simpson(Equation e, Rational start, Rational end,
			double tolerance, String var) {

		return null;
	}
	public static Rational simpson(Rational lo, Rational hi, Rational n,
			Equation e, String var) {
		// Check data.
		// Integrate the function between lo and hi with n slices.
		if (hi.floatValue() <= lo.floatValue() || n.intValue() <= 0
				|| e == null)
			return Rational.ZERO;

		Rational dx = (hi.minus(lo)).divide(n);
		Rational two = Rational.valueOf("2/1");
		Rational three = Rational.valueOf("3/1");
		Rational four = Rational.valueOf("4/1");
		dx = dx.divide(two); // Use half widths for Simpson rule
		Rational area = Rational.ZERO;

		Rational x_lo = lo;
		Rational x_md = lo.plus(dx);
		Rational x_hi = lo.plus(two).times(dx);

		for (int i = 0; i < n.intValue(); i++) {

			e.getVariables().get(var).set(x_lo);
			Rational left_side = e.getSumLeft().evaluate();

			e.getVariables().get(var).set(x_md);
			Rational middle = e.getSumLeft().evaluate();

			e.getVariables().get(var).set(x_hi);
			Rational rite_side = e.getSumLeft().evaluate();

			// area += dx * (left_side + 4.0 * middle + rite_side) / 3.0;
			area = area.plus(dx.times(((left_side.plus(four).times(middle)
					.plus(rite_side))).divide(three)));

			x_lo = lo.plus(two).times(Rational.valueOf("" + i + "/1"))
					.times(dx);
			x_md = x_lo.plus(dx);
			x_hi = x_lo.plus(two).times(dx);

		}

		return area;
	} // integrate

	

	public static Rational averageOrdinateMethod(Equation e, Rational lo,
			Rational hi, Rational n, String var) {

		Rational dx = (hi.minus(lo)).divide(n);
		Rational sum = Rational.ZERO;
		Rational step = lo;

		for (int i = 0; i <= n.intValue(); i++) {

			e.getVariables().get(var).set(step);
			Rational ordPoint = e.getSumLeft().evaluate();

			sum = sum.plus(ordPoint);
			step = step.plus(dx);

		}

		return (sum.divide(n)).times(hi.minus(lo));

	}

	public static Rational trapezoidalEqualSpacingMethod(Equation e,
			Rational lo, Rational hi, Rational n, String var) {

		Rational dx = (hi.minus(lo)).divide(n);
		Rational oneHalf = Rational.valueOf("1/2");
		Rational sum = Rational.ZERO;
		Rational step = lo;
		Rational yZero = Rational.ZERO;
		Rational yN = Rational.ZERO;

		for (int i = 0; i <= n.intValue(); i++) {

			e.getVariables().get(var).set(step);
			yN = e.getSumLeft().evaluate();
			System.out.println("i = " + i + "  -- " + yN.floatValue());

			if (i == 0)
				yZero = yN;
			else {
				sum = sum.plus(yN);
			}

			step = step.plus(dx);

		}

		sum = sum.minus(yN);

		Rational area = oneHalf.times(yZero.plus(yN)).plus(sum);

		return area;
	}

	public static Rational trapezoidalUnequalSpacingMethod(Equation e,
			Rational lo, Rational hi, Rational n, String var) {

		Rational dx = (hi.minus(lo)).divide(n);
		Rational oneHalf = Rational.valueOf("1/2");
		Rational sum = Rational.ZERO;
		Rational step = lo;
		Rational yZero = Rational.ZERO;
		Rational yN = Rational.ZERO;
		Rational yPrev = Rational.ZERO;
		Rational stepPrev = null;

		for (int i = 0; i <= n.intValue(); i++) {

			e.getVariables().get(var).set(step);
			yN = e.getSumLeft().evaluate();
			System.out.println("i = " + i + "  -- " + yN.floatValue());

			if (i == 0)
				yZero = yN;
			else {

				System.out.println("x1 - x0 = " + step.minus(stepPrev));
				System.out.println("y1 - y0 = " + yN.plus(yPrev));

				System.out.println(oneHalf.times(step.minus(stepPrev)).times(
						yN.plus(yPrev)));

				sum = sum.plus(oneHalf.times(step.minus(stepPrev)).times(
						yN.plus(yPrev)));

			}

			stepPrev = step;
			yPrev = yN;
			step = step.plus(dx);

		}

		// sum = sum.minus(yN);

		// Rational area = oneHalf.times(yZero.plus(yN)).plus(sum);

		return sum;

	}

}
