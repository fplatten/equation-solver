package com.showyourwork.engine.util;

import org.jscience.mathematics.number.Rational;

import com.showyourwork.engine.EquationConstants;

public class NumberUtils {

	public static final String TENTHS = "/10";

	public static Rational convertDecimalToRational(Float convert) {
		
		//System.out.println("convert = " + convert);
		String roundDecimal = String.format("%4.6f", convert);
		String ivalue = roundDecimal.substring(0, roundDecimal.indexOf("."));
		String mod = roundDecimal.substring(roundDecimal.indexOf(".") + 1,
				roundDecimal.length());

		Rational whole = Rational.valueOf(ivalue + "/1");
		Rational fraction = Rational.ZERO;

		for (int i = 0; i < mod.length(); ++i) {

			fraction = fraction.plus(Rational.valueOf("" + mod.charAt(i)
					+ TENTHS + addTrailingZeros(i)));
		}

		return whole.plus(fraction);
	}

	public static String addTrailingZeros(int count) {

		StringBuffer zeros = new StringBuffer("");

		for (int i = 0; i < count; ++i) {
			zeros.append("0");
		}
		return zeros.toString();
	}

	public static boolean equals(Float a, Float b, Float eps) {

		return Math.abs(a - b) < eps;
	}
	public static Rational findShorterFraction(Rational r, Float eps){
		
		int wholeNumber = r.intValue();
		
		
		for (Rational f : EquationConstants.fractions) {			
			
			if(equals(Rational.valueOf( wholeNumber + "/1").plus(f).floatValue(), r.floatValue(), eps) ){
				return Rational.valueOf( wholeNumber + "/1").plus(f);
			}			
		}		
		
		return r;
		
	}

}
