package com.showyourwork.engine.trigonometry;

 

import java.math.BigDecimal;
import java.text.DecimalFormat; 

import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Rational; 

public class CalculateDistance3D {     

      public static final String TENTHS = "/10"; 

      /**
       * @param args
       */
      public static void main(String[] args) {
            // TODO Auto-generated method stub           

            //San Fran
            Double a1 = Math.toRadians(37.616667D - 90) ;/// 57.29577951;
            Double b1 = Math.toRadians(122.366667D) ;/// 57.29577951;           

            //New York
            Double a2 = Math.toRadians(40.7142D - 90) ;/// 57.29577951;
            Double b2 = Math.toRadians(74.0064D) ;/// 57.29577951;           

            //Paris
            //Double a2 = 48.733334D ;/// 57.29577951;
            //Double b2 = 2.383334D ;/// 57.29577951;          

            //Philadelphia
            //Double a2 = Math.toRadians(39.9522D - 90) ;/// 57.29577951;
            //Double b2 = Math.toRadians(-75.1642D) ;/// 57.29577951;           

            Double earthRadius = 6378.1D;           

            //x = radius_of_world * cos(longitude) * sin(90 - latitude)
            //y = radius_of_world * sin(longitude) * sin(90 - latitude)
            //z = radius_of_world * cos(90 - latitude)           

            //xPos = (app.radius) * Math.sin(latitude) * Math.cos(longitude);
            //zPos = (app.radius) * Math.sin(latitude) * Math.sin(longitude);
            //yPos = (app.radius) * Math.cos(latitude);           

            //xPos = (app.radius) * Math.cos(longitude) * Math.cos(latitude);
            //yPos = (app.radius) * Math.sin(longitude) * Math.cos(latitude);
            //zPos = (app.radius) * Math.sin(latitude);           

            //Double x1Orig = earthRadius * Math.cos(b1) * Math.cos(a1);
            //Double y1Orig = earthRadius * Math.sin(b1) * Math.cos(a1);
            //Double z1Origin = earthRadius * Math.sin(a1);           

            Double x1Orig = earthRadius * Math.sin(a1) * Math.cos(b1);
            Double y1Orig = earthRadius * Math.cos(a1);
            Double z1Origin = earthRadius * Math.sin(a1) * Math.sin(b1);           

            //Double x1Orig = earthRadius * Math.cos(a1) * Math.cos(b1);
            //Double y1Orig = earthRadius * Math.cos(a1) * Math.sin(b1);
            //Double z1Origin = earthRadius * Math.sin(a1);
           

            //Double x1Orig = earthRadius * Math.cos(b1) * Math.sin(Math.toRadians(90) - a1);
      //    Double y1Orig = earthRadius * Math.sin(b1) * Math.sin(Math.toRadians(90) - a1);
      //    Double z1Origin = earthRadius * Math.cos(90 - a1);           

      //    Double x2Dest = earthRadius * Math.cos(b2) * Math.sin(Math.toRadians(90) - a2);
            //Double y2Dest = earthRadius * Math.sin(b2) * Math.sin(Math.toRadians(90) - a2);
            //Double z2Dest = earthRadius * Math.cos(90 - a2);              

            Double x2Dest = earthRadius * Math.sin(a2) * Math.cos(b2);
            Double y2Dest = earthRadius * Math.cos(a2);
            Double z2Dest = earthRadius * Math.sin(a2) * Math.sin(b2);           

            //Double x2Dest = earthRadius * Math.cos(b2) * Math.cos(a2);
            //Double y2Dest = earthRadius * Math.sin(b2) * Math.cos(a2);
            //Double z2Dest = earthRadius * Math.sin(a2);           

            //Double x2Dest = earthRadius * Math.cos(a2) * Math.cos(b2);
            //Double y2Dest = earthRadius * Math.cos(a2) * Math.sin(b2);
            //Double z2Dest = earthRadius * Math.sin(a2);           

            //x1bd = x1bd.movePointRight(5);
            //DecimalFormat dc = new DecimalFormat("#0.000000");
            //Integer x1Int = x1bd.intValue();
            //System.out.println(x1Orig + " = " +x1bd.intValue());           

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

        Polynomial<Rational> xp = x2.minus(x1).pow(2);
        Polynomial<Rational> yp = y2.minus(y1).pow(2);
        Polynomial<Rational> zp = z2.minus(z1).pow(2);       

        Polynomial<Rational> equ = xp.plus(yp).plus(zp);

        varX1.set(convertDecimalToRational(x1Orig));
        varY1.set(convertDecimalToRational(y1Orig));
        varZ1.set(convertDecimalToRational(z1Origin));

        varX2.set(convertDecimalToRational(x2Dest));
        varY2.set(convertDecimalToRational(y2Dest));
        varZ2.set(convertDecimalToRational(z2Dest));       

        Rational result = equ.evaluate();
        Double cordLength = Math.sqrt(result.doubleValue());
        Double centralAngle = 2*Math.asin(cordLength/2/earthRadius);
       

        System.out.println("cordLength = " + cordLength);
        System.out.println("centralAngle = " + Math.toDegrees(centralAngle));
        System.out.println("earthRadius*centralAngle = " + earthRadius*centralAngle);           

            //Cos[a1] Cos[b1] Cos[a2] Cos[b2] + Cos[a1] Sin[b1] Cos[a2] Sin[b2] + Sin[a1] Sin[a2]  =  Cos[t]
            //Double t = Math.acos((Math.cos(a1) * Math.cos(b1) * Math.cos(a2) * Math.cos(b2)) + (Math.cos(a1) * Math.sin(b1) * Math.cos(a2) * Math.sin(b2)) + (Math.sin(a1) * Math.sin(a2)));
            //Double t = Math.acos((Math.cos(x1) * Math.cos(y1) * Math.cos(x2) * Math.cos(y2)) + (Math.cos(x1) * Math.sin(y1) * Math.cos(x2) * Math.sin(y2)) + (Math.sin(x1) * Math.sin(x2)));
        	//Double t = Math.acos((Math.cos(a1) * Math.cos(b1)) * (Math.cos(a2) * Math.cos(b2)) + ((Math.cos(a1) * Math.sin(b1)) * (Math.cos(a2) * Math.sin(b2))) + (Math.sin(a1) * Math.sin(a2)));
            //Arccos[Cos[a1] Cos[b1] Cos[a2] Cos[b2] + Cos[a1] Sin[b1] Cos[a2] Sin[b2]
            //+ Sin[a1] Sin[a2]] * r
            //System.out.println(t);

      }
      public static Rational convertDecimalToRational(Double convert){

            String roundDecimal = String.format("%4.6f", convert);
            String ivalue = roundDecimal.substring(0, roundDecimal.indexOf(".") ); 
            String mod = roundDecimal.substring(roundDecimal.indexOf(".") + 1, roundDecimal.length());

            Rational whole = Rational.valueOf( ivalue + "/1");
            Rational fraction = Rational.ZERO;

            for (int i = 0; i < mod.length(); ++i){

                  System.out.println(  mod.charAt(i));
                  System.out.println(  fraction);
                  fraction = fraction.plus(Rational.valueOf("" + mod.charAt(i) + TENTHS + addTrailingZeros(i)));
            }

            return whole.plus(fraction);
      }    

      public static String addTrailingZeros(int count){
    	  
            StringBuffer zeros = new StringBuffer("");

            for(int i = 0 ; i < count ; ++i){
                  zeros.append("0");
            }   
            return zeros.toString();
      }
}