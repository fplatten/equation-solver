/*
 * Created on Jan 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.showyourwork.engine.basic;

/**
 * @author fplatten
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Number {
	
	private List digits = new ArrayList();
	private int position;
	private int type;
	private boolean answer;
	private boolean equation;
	private boolean solution;
	private boolean header;
	private boolean placementLast;
	
	public static final int HEADER = 1;
	public static final int EQUATION = 2;	
	public static final int SOLUTION = 3;
	public static final int ANSWER = 4;
	
	public Number(){}
	
	public Number(String str){
		
		addDigits(str);
		
	}
	public Number(int number, int position, int type){
		addDigits( ""+ number);
		setPosition(position);
		setType(type);
	}

	
	public void addDigitsD(String str){
		
		for(int i = 0; i < str.length(); ++i){			
			String digit = str.substring(i,i+1);
			digits.add(digit);			
		}		
	
	}
	public void addDigitD(String str){		
		digits.add(str);		
	}
	public void addDigits(String str){
		
		for(int i = 0; i < str.length(); ++i){			
			Digit d = new Digit(str.substring(i,i+1), (str.length() - 1) - i );
			digits.add(d);			
		}		
	
	}
	public void addDigit(String str){		
		digits.add(new Digit(str));			
	}	
	/**
	 * @return Returns the answer.
	 */
	public boolean isAnswer() {
		return answer;
	}
	/**
	 * @param answer The answer to set.
	 */
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	/**
	 * @return Returns the digits.
	 */
	public List getDigits() {
		return digits;
	}
	/**
	 * @param digits The digits to set.
	 */
	public void setDigits(List digits) {
		this.digits = digits;
	}
	/**
	 * @return Returns the equation.
	 */
	public boolean isEquation() {
		return equation;
	}
	/**
	 * @param equation The equation to set.
	 */
	public void setEquation(boolean equation) {
		this.equation = equation;
	}
	/**
	 * @return Returns the etype.
	 */
	public int getType() {
		return type;
	}
	public String getTypeString() {
		
		switch (type) {
		
		case HEADER:
			return "header";
		case EQUATION:
			return "equation";
		case SOLUTION:
			return "solution";
		case ANSWER:
			return "answer";		
		}
		
		return null;
	}
	
	/**
	 * @param etype The etype to set.
	 */
	public void setType(int etype) {
		this.type = etype;
	}
	/**
	 * @return Returns the header.
	 */
	public boolean isHeader() {
		return header;
	}
	/**
	 * @param header The header to set.
	 */
	public void setHeader(boolean header) {
		this.header = header;
	}
	/**
	 * @return Returns the position.
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @param position The position to set.
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * @return Returns the solution.
	 */
	public boolean isSolution() {
		return solution;
	}
	/**
	 * @param solution The solution to set.
	 */
	public void setSolution(boolean solution) {
		this.solution = solution;
	}	
	
	public boolean isPlacementLast() {
		return placementLast;
	}

	public void setPlacementLast(boolean placement) {
		this.placementLast = placement;
	}

	public static String getNumberAsStringD(List digits){
		
		Iterator iterator = digits.iterator();		
		String number = "";
		while (iterator.hasNext()) {
			number = number + (String) iterator.next();			
		}
		
		return number;
	}
	public static Number convertToNumber(int n){
		
		Number converted = new Number();
		
		converted.addDigits("" + n);
				
		
		return converted;
	}
	public static String getNumberAsString(List digits){
		
		Iterator iterator = digits.iterator();		
		String number = "";
		while (iterator.hasNext()) {
			number = number + ((Digit) iterator.next()).getDigit();			
		}
		
		return number;
	}
	
	static final Comparator typeCmp = new Comparator() {
		public int compare(Object o1, Object o2) {

			Number n1 = (Number) o1;
			Number n2 = (Number) o2;			

			int e = n1.getType() - n2.getType();				

			if (e == 0)
				e = n1.getPosition() - n2.getPosition();
			
			return e;
		}
	};
}
