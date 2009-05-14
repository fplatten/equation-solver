/*
 * Created on Jan 11, 2005
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
 * @author fplatten
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Subtraction extends BasicEquation {
	
	private List numbers = new ArrayList();
	private Number ans = new Number();
	private Number head = new Number();
	private Number origianlsubtop = new Number();	
	
	public void addNumberToList(String number) {

		Number n = new Number(number);
		n.setType(Number.EQUATION);
		int rnum = getRowNum();
		n.setPosition(rnum);
		numbers.add(n);
		if(rnum == 0){
			origianlsubtop = new Number(number);
			origianlsubtop.setType(Number.EQUATION);
			origianlsubtop.setPosition(getRowNum());
			Collections.reverse(origianlsubtop.getDigits());
		}

	}
	
	public List evaluate(){
		
		numbers = reverseNumbers(numbers);
		ans = setAnswer(getRowNum());
		int count = getLongestNumberCount(numbers);
		head = setHeader( count, getRowNum());
		numbers.add(head);
		int sum = 0;
		
		for(int i = 0; i < head.getDigits().size(); ++i){
			
			sum = subtractBase(i+1);						
			ans.addDigit( "" + sum );									
		}		
		
		numbers.add(ans);
		numbers.add(origianlsubtop);
		reverseNumbers(numbers);
		Collections.sort(numbers, Number.typeCmp);		
		
		return numbers;			
	}
	private int subtractBase(int base){		
		
		int subtop = 0;
		int subbottom = 0;
		int header = 0;

		Iterator it = numbers.iterator();
		while(it.hasNext()){
			Number n = (Number) it.next();
			int type = n.getType();			
			if( n.getDigits().size() >= base ){
				if(n.getType() == Number.EQUATION){
					if(n.getPosition() == 0)
						subtop = ((Digit)n.getDigits().get(base-1)).getInteger();					
					else
						subbottom = ((Digit)n.getDigits().get(base-1)).getInteger();				
				}
				else if(n.getType() == Number.HEADER){
					header = ((Digit)n.getDigits().get(base-1)).getInteger();
					if(header == 1)
						header = header * 10;
				}									
			}			
		}
		
		if( (subtop + header) < subbottom ){
			processCarryRight(base);
			header = ((Digit)head.getDigits().get(base-1)).getInteger();
			if(header == 1)
				header = header * 10;
		}		
		return (subtop + header) - subbottom;		
	}
	private void processCarryRight(int base){
		
		int subtopnext = ((Digit)((Number) numbers.get(0)).getDigits().get(base)).getInteger();
		((Digit)((Number) numbers.get(0)).getDigits().get(base)).setStrikeout(true);
		((Digit)origianlsubtop.getDigits().get(base)).setStrikeout(true);
		int header = ((Digit) head.getDigits().get(base-1)).getInteger();
		
		if(subtopnext > 0 & header == 0){				
			head.getDigits().set(base-1, new Digit("1") );			
			((Number) numbers.get(0)).getDigits().set(base, new Digit("" + (subtopnext - 1)));
			((Digit)((Number) numbers.get(0)).getDigits().get(base)).setStrikeout(true);
			((Digit)origianlsubtop.getDigits().get(base)).setStrikeout(true);
		}
		else if(subtopnext > 0 & header > 0){				
			((Number) numbers.get(0)).getDigits().set(base, new Digit("" + (subtopnext - 1)));
			((Digit)((Number) numbers.get(0)).getDigits().get(base)).setStrikeout(true);
			((Digit)origianlsubtop.getDigits().get(base)).setStrikeout(true);
		}
		else if(subtopnext == 0 & header == 0){
			head.getDigits().set(base-1, new Digit("1") );	
			head.getDigits().set(base, new Digit("9") );
			processCarryRight(base+ 1);		
		}
		else if(subtopnext == 0 & header == 9){
			head.getDigits().set(base, new Digit("9") );
			processCarryRight(base+ 1);			
		}
	}
	public Document toXml(){
		
		return null;
	}
	
	
}
