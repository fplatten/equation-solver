package com.showyourwork.engine.trigonometry;

import org.jscience.mathematics.number.Rational;

public class RightTriangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Rational radiusVector = Rational.valueOf("10/1"); // r or hypotenuse
		Rational abscissa = Rational.valueOf("8/1"); // x or "adjacent angle A"
		Rational ordinate = Rational.valueOf("6/1"); // y or opposite angle A
		
		Rational sinA = ordinate.divide(radiusVector); // sine A
		Rational cosA = abscissa.divide(radiusVector); // co-sine A
		Rational tanA = ordinate.divide(abscissa); // tangent A
		Rational cotA = abscissa.divide(ordinate); // co-tangent A
		Rational secA = radiusVector.divide(abscissa); // see-cant A
		Rational cscA = radiusVector.divide(ordinate); // co-secant A
		
		System.out.println("sin A = " + sinA + " = " + sinA.doubleValue());
		System.out.println("cos A = " + cosA + " = " + cosA.doubleValue());
		System.out.println("tan A = " + tanA + " = " + tanA.doubleValue());
		System.out.println("cot A = " + cotA + " = " + cotA.doubleValue());
		System.out.println("sec A = " + secA + " = " + secA.doubleValue());
		System.out.println("csc A = " + cscA + " = " + cscA.doubleValue());
		
		Double Arad = Math.asin(sinA.doubleValue());
		
		Double arcsin = Math.asin(sinA.doubleValue());// * 57.2957795;
		Double arccos = Math.acos(cosA.doubleValue());// * 57.2957795;
		Double arctan = Math.atan(tanA.doubleValue());// * 57.2957795;		
		
		//System.out.println("Angle in Radians = " + Arad);
		System.out.println("arcsin = " + Math.toDegrees(arcsin));
		System.out.println("arccos = " + Math.toDegrees(arccos));
		System.out.println("arctan = " + Math.toDegrees(arctan));
		
		System.out.println(Math.sqrt(40D));

	}

}
