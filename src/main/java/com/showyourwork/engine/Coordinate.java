package com.showyourwork.engine;

import org.jscience.mathematics.number.Rational;

public class Coordinate {
	
	Rational x;
	Rational y;
	
	public Coordinate(){}
	
	public Coordinate(Rational x, Rational y){
		
		this.x = x;
		this.y = y;		
	}	
	
	public Rational getX() {
		return x;
	}
	public void setX(Rational x) {
		this.x = x;
	}
	public Rational getY() {
		return y;
	}
	public void setY(Rational y) {
		this.y = y;
	}

}
