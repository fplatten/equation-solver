package com.showyourwork.engine.trigonometry;

import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Rational;

public class CalculateDistance2D {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//San Fran
		//Double a1 = 37.616667D ;/// 57.29577951;
		//Double b1 = 122.366667D ;/// 57.29577951;
		
		//Paris
		//Double a2 = 48.733334D ;/// 57.29577951;
		//Double b2 = 2.383334D ;/// 57.29577951;
		
		//Double earthRad = 6378.1D;
		
		//Double x1 = earthRad * Math.cos(a1) * Math.cos(b1);
		//Double y1 = earthRad * Math.cos(a1) * Math.sin(b1);
		//Double zOrigin = earthRad * Math.sin(a1);
		
		//Double thetaOrigin = Math.atan2(y1, x1);
		
		//System.out.println(thetaOrigin);
		
		//Double x2 = earthRad * Math.cos(a2) * Math.cos(b2);
		//Double y2 = earthRad * Math.cos(a2) * Math.sin(b2);
		//Double zDestination = earthRad * Math.sin(a2);
		
		
		// Defines local variables.
        Variable.Local<Rational> varX1 = new Variable.Local<Rational>("x1");
        Variable.Local<Rational> varY1 = new Variable.Local<Rational>("y1");
        Variable.Local<Rational> varZ1 = new Variable.Local<Rational>("z1");
        
        // f(x, y) =  x² + x·y + 1;
        Polynomial<Rational> x1 = Polynomial.valueOf(Rational.ONE, varX1);
        Polynomial<Rational> y1 = Polynomial.valueOf(Rational.ONE, varY1);
        Polynomial<Rational> z1 = Polynomial.valueOf(Rational.ONE, varZ1);
        
     // Defines local variables.
        Variable.Local<Rational> varX2 = new Variable.Local<Rational>("x2");
        Variable.Local<Rational> varY2 = new Variable.Local<Rational>("y2");
        Variable.Local<Rational> varZ2 = new Variable.Local<Rational>("z2");
        
        // f(x, y) =  x² + x·y + 1;
        Polynomial<Rational> x2 = Polynomial.valueOf(Rational.ONE, varX2);
        Polynomial<Rational> y2 = Polynomial.valueOf(Rational.ONE, varY2);
        Polynomial<Rational> z2 = Polynomial.valueOf(Rational.ONE, varZ2);		
		
		// 
		
        Polynomial<Rational> xp = x2.minus(x1).pow(2);
        Polynomial<Rational> yp = y2.minus(y1).pow(2);
       // Polynomial<Rational> zp = z2.minus(z1).pow(2);
        
        Polynomial<Rational> equ = xp.plus(yp); 
        
        
        varX1.set(Rational.valueOf("4/1"));
        varY1.set(Rational.valueOf("5/1"));
        
        varX2.set(Rational.valueOf("20/1"));
        varY2.set(Rational.valueOf("35/1"));
        
        
        Rational result = equ.evaluate();
        
        Double answer = Math.sqrt(result.doubleValue());        
        
		
		System.out.println(answer);
		
		
		
		
		
		
		
		//Cos[a1] Cos[b1] Cos[a2] Cos[b2] + Cos[a1] Sin[b1] Cos[a2] Sin[b2] + Sin[a1] Sin[a2]  =  Cos[t]
		
		
		//Double t = Math.acos((Math.cos(a1) * Math.cos(b1) * Math.cos(a2) * Math.cos(b2)) + (Math.cos(a1) * Math.sin(b1) * Math.cos(a2) * Math.sin(b2)) + (Math.sin(a1) * Math.sin(a2))); 
		//Double t = Math.acos((Math.cos(x1) * Math.cos(y1) * Math.cos(x2) * Math.cos(y2)) + (Math.cos(x1) * Math.sin(y1) * Math.cos(x2) * Math.sin(y2)) + (Math.sin(x1) * Math.sin(x2)));
		
		
		//Double t = Math.acos((Math.cos(a1) * Math.cos(b1)) * (Math.cos(a2) * Math.cos(b2)) + ((Math.cos(a1) * Math.sin(b1)) * (Math.cos(a2) * Math.sin(b2))) + (Math.sin(a1) * Math.sin(a2)));
		
		//Arccos[Cos[a1] Cos[b1] Cos[a2] Cos[b2] + Cos[a1] Sin[b1] Cos[a2] Sin[b2]
		//                                                                     + Sin[a1] Sin[a2]] * r
		
		
		//System.out.println(t);

	}

}
