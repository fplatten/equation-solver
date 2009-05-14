/*
 * Created on Feb 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.showyourwork.engine.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;

/**
 * @author Frederick Platten
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Division extends BasicEquation {

	private List numbers = new ArrayList();
	private List solutions = new ArrayList();
	private Number quotient = new Number();
	
	public void addNumberToList(String number) {

		Number n = new Number(number);
		n.setType(Number.EQUATION);
		n.setPosition(getRowNum());
		numbers.add(n);
	}	
	
	public List evaluate(){		
		
		Number nDividend = (Number) numbers.get(0);
		String sDividend = Number.getNumberAsString(nDividend.getDigits());
		int dividend = new Integer(sDividend).intValue();
		Number sol = new Number();
		Iterator iterator = ((Number) numbers.get(0)).getDigits().iterator();
		int x = 0;
		Number nDivisor = (Number) numbers.get(1);
		String sDivisor = Number.getNumberAsString(nDivisor.getDigits());
		int divisor = new Integer(sDivisor).intValue();
		String subdividend = "";
		int remainder = -1;
		int i = 0;
		while (iterator.hasNext()) {
			subdividend = subdividend + ((Digit) iterator.next()).getDigit();
			int qdigit = 0;
			int div = new Integer(subdividend).intValue();
			if(divisor <= div){
				qdigit = findQuotient(divisor, div);
				quotient.addDigits("" + qdigit);
				
				//change to use the Subtraction class
				remainder = div - ( divisor * qdigit );
				//remainder = qdigit - div;
				
				solutions.add(new Number(div, ++i, Number.SOLUTION));
				solutions.add(new Number((divisor * qdigit), ++i, Number.SOLUTION));
				solutions.add(new Number(remainder, ++i, Number.SOLUTION ));			
				
				if(remainder > -1 )					
					subdividend = "" + remainder;				
				
			}					
								
			++x;
		}		
		
		numbers.addAll(solutions);		
		quotient.setType(Number.HEADER);
		quotient.setPosition(-2);
		numbers.add(quotient);
		numbers.add(new Number(remainder, -1, Number.HEADER));
		
		nDividend.setPosition(1);
		nDivisor.setPosition(0);
		
		Collections.sort(numbers, Number.typeCmp);
		
		//the first number in the list is the dividend
		//the second number in the list is the divisor
		//the answer is the quotient
		//anything left is the remainder or mod.
		//example divide 29508 by 47
		//29508 is the dividend
		//47 is the divisor
		
		//get the length of the divisor 47
		
		//loop through the digits of the dividend 
		//in blocks of the length of the divisor
		
		//if the resulting number from digits that match the length
		//of the divisor is less than the divisor then get an additional digit.
		//so 29 is less than 47 so add the next digit 5 to the result.
				
		//divide the divisor into the result
		//47 is divided into 295 6 times.  6 x 47 = 282
		
		//subtract the result 295 from 282 which = 13
		
		//13 is carried to the top of the loop.
		
		//the process is continued until the there are no more digits in the dividend
		
				
		
		//end loop
		
		
	
		return numbers;
	}
	public int findQuotient(int lower, int higher){
		
		if(lower == higher) return 1;
		
		int quotient = 0;
		while( (quotient * lower) < higher   ){
			++quotient;			
		}
		
		return quotient-1;		
	}
	public Document toXml(){
		
		return null;
	}
	
}
