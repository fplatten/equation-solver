/*
 * Created on Jan 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.showyourwork.engine.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

import org.w3c.dom.Document;

/**
 * @author fplatten
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Multiplication extends BasicEquation {

	private List numbers = new ArrayList();
	private List headers = new ArrayList();
	private List solutions = new ArrayList();
	private Number sol = new Number();
	private Number head = new Number();
	private Number ans = new Number();

	public void addNumberToList(String number) {

		Number n = new Number(number);
		n.setType(Number.EQUATION);
		n.setPosition(getRowNum());
		numbers.add(n);
	}

	public List evaluate() {

		numbers = reverseNumbers(numbers);
		int longcount = getLongestNumberCount(numbers);
		int shortcount = getLongestNumberCount(numbers);
		int sum = 0;

		Iterator iterator = ((Number) numbers.get(1)).getDigits().iterator();
		int x = 0;
		while (iterator.hasNext()) {
			int multiplier = ((Digit) iterator.next()).getInteger();
			multiplyBase(x, multiplier);
			++x;
		}
		
		solutions = reverseNumbers(solutions);
		Addition a = new Addition(solutions);
		List answer = a.evaluate();	
		
		numbers.addAll(headers);
		numbers = reverseNumbers(numbers);
		Collections.sort(numbers, Number.typeCmp);
		numbers.addAll(answer);
		return numbers;
	}

	private void multiplyBase(int count, int multiplier) {
		
		Number n = (Number) numbers.get(numbers.size()-1);
		n.setPlacementLast(true);

		int sum = 0;
		sol = setSolution(count);
		sol = addTrailingZeros(sol, count);
		head = setHeader(6, (0 - count));
		int total = 0;
		Iterator iterator = ((Number) numbers.get(0)).getDigits().iterator();
		int x = 0;
		while (iterator.hasNext()) {
			int multiplican = ((Digit) iterator.next()).getInteger();
			
			int h = ((Digit)head.getDigits().get(x)).getInteger();
			total = (multiplier * multiplican)+h;
			if(total > 9)
				processCarryLeft(total, x+1);			
			else{				
				sol.addDigit( "" + total );			
			}
			++x;			
		}
		sol.addDigit(((Digit)head.getDigits().get(x)).getDigit());

		solutions.add(sol);
		headers.add(head);	
		
	}
	private Number addTrailingZeros(Number n, int i) {

		while (n.getDigits().size() < i) {
			n.addDigit("0");
		}

		return n;
	}
	private void processCarryLeft(int sum, int base){
		
		//TODO: make this recursive!
		if(sum > 9 & sum < 99 ){
			int h = ((Digit)head.getDigits().get(base)).getInteger();			
			head.getDigits().set(base, new Digit("" + ((sum/10)+ h)));				
			sol.addDigit( "" + (sum%10) );				
		}
		else if(sum > 99){
			int h1 = ((Digit)head.getDigits().get(base)).getInteger();
			head.getDigits().set(base, new Digit("" + (((sum/10)%10)+h1)));
			sol.addDigit( "" + ((sum%100)%10) );
			int h2 = ((Digit)head.getDigits().get(base+1)).getInteger();
			head.getDigits().set(base+1, new Digit("" + ((sum/100)+h2)));			
		}		
	}
	public Document toXml(){
		
		return null;
	}
}