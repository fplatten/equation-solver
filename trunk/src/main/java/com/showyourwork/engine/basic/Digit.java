/*
 * Created on Jul 11, 2005
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
public class Digit {
	
	private String s_digit;
	private boolean strikeout;
	private int place;
	
	public static final int PLACE_ONE = 0;
	public static final int PLACE_TEN = 2;
	public static final int PLACE_HUNDRED = 3;
	public static final int PLACE_THOUSAND = 4;
	public static final int PLACE_TEN_THOUSAND = 5;
	public static final int PLACE_HUNDRED_THOUSAND = 6;
	public static final int PLACE_MILLION = 7;
	public static final int PLACE_TEN_MILLION = 8;
	public static final int PLACE_HUNDRED_MILLION = 9;
	public static final int PLACE_BILLION = 10;
	public static final int PLACE_TEN_BILLION = 11;
	public static final int PLACE_HUNDRED_BILLION = 12;
	
	
	public Digit(){}
	
	public Digit(String str){
		s_digit = str;		
	}
	public Digit(String digit, int place){
		s_digit = digit;
		this.place = place;
	}
	/**
	 * @return Returns the snumber.
	 */
	public String getDigit() {
		return s_digit;
	}
	/**
	 * @param snumber The snumber to set.
	 */
	public void setDigit(String str) {
		s_digit = str;
	}
	/**
	 * @return Returns the strikeout.
	 */
	public boolean isStrikeout() {
		return strikeout;
	}
	/**
	 * @param strikeout The strikeout to set.
	 */
	public void setStrikeout(boolean strikeout) {
		this.strikeout = strikeout;
	}
	public int getInteger(){		
		return (new Integer(s_digit).intValue());
	}
	public String toString(){
		return s_digit;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
	
}
