/*
 * Created on Jan 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.showyourwork.engine.basic;

import java.util.Collections;
import java.util.Iterator;

/**
 * @author fplatten
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.util.List;

import org.w3c.dom.Document;

public abstract class BasicEquation {
	
	protected int rows = 0;
	
	public int getLongestNumberCount(List numbers){
		
		Iterator it = numbers.iterator();
		Number n1 = (Number) numbers.get(0);
		int count = n1.getDigits().size();	
		
		while(it.hasNext()){
			Number n = (Number) it.next();			
			if( n.getDigits().size() >  n1.getDigits().size() ){
				n1 = n;
				count = n.getDigits().size();			
			}			
		}
		
		return count;		
	}
	protected Iterator getShortestIterator(List numbers){
		
		Iterator it = numbers.iterator();
		Iterator it2 = numbers.iterator();
		Number n1 = (Number) numbers.get(0);			
		
		while(it.hasNext()){
			Number n = (Number) it.next();			
			if( n.getDigits().size() >  n1.getDigits().size() ){
				n1 = n;
				it2 = n.getDigits().iterator();							
			}			
		}
		
		return it2;		
	}
	protected int getShortestNumberCount(List numbers){
		
		Iterator it = numbers.iterator();
		Number n1 = (Number) numbers.get(0);
		int count = n1.getDigits().size();	
		
		while(it.hasNext()){
			Number n = (Number) it.next();			
			if( n.getDigits().size() <  n1.getDigits().size() ){
				n1 = n;
				count = n.getDigits().size();				
			}
			
		}
		
		return count;		
	}
	protected List reverseNumbers(List numbers){
		
		Iterator it = numbers.iterator();
		while(it.hasNext()){
			Number n = (Number) it.next();
			Collections.reverse(n.getDigits());			
		}
		
		return numbers;
		
	}
	protected Number setSolution(int rownum){
		
		Number sol = new Number();
		sol.setType(Number.SOLUTION);
		sol.setPosition(rownum);
		
		return sol;		
	}
	protected Number setAnswer(int rownum){
		
		Number sol = new Number();
		sol.setType(Number.ANSWER);
		sol.setPosition(rownum);
		
		return sol;		
	}
	protected Number setHeader(int count, int rownum){
		
		Number head = new Number();
		head.setType(Number.HEADER);
		head.setPosition(rownum);
		for(int i = 0; i <= count+1; ++i){
			head.addDigit( "0" );
		}
		
		return head;		
	}
	protected int getRowNum(){
		int current = rows;
		++rows;
		return current;
	}
	public abstract void addNumberToList(String number);
	
	public abstract List evaluate();
	
	public abstract Document toXml();

}