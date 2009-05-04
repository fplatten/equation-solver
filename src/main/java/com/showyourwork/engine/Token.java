package com.showyourwork.engine;

import java.util.Comparator;


public class Token {
	
	int location;
	String name;
	String value;
	
	/**
	 * @return Returns the location.
	 */
	public int getLocation() {
		return location;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(int location) {
		this.location = location;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isOperator() {

		boolean operator = false;

		for (int i = 0; i < EquationConstants.mathoperations.length; ++i) {

			if (name.equals(EquationConstants.mathoperations[i])) {
				return true;
			}
		}
		return operator;
	}
	public boolean isLiteralOperator() {

		boolean operator = false;

		for (int i = 0; i < EquationConstants.literalmathoperations.length; ++i) {

			if (value.equals(EquationConstants.literalmathoperations[i])) {
				return true;
			}
		}
		return operator;
	}
	static final Comparator<Token> locationCmp = new Comparator<Token>(){
		public int compare(Token t1, Token t2){
			
			return t1.getLocation() - t2.getLocation();			
			
		} 
		
	};
}
