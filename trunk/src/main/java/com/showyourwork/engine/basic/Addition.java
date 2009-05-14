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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Addition extends BasicEquation {

	private List numbers = new ArrayList();
	private Number ans = new Number();
	private Number head = new Number();	
	
	public Addition(){		
	}
	public Addition(List l){		
		this.numbers = l;			
	}
	public void addNumberToList(String number) {

		Number n = new Number(number);
		n.setType(Number.EQUATION);
		n.setPosition(getRowNum());
		numbers.add(n);
	}	
	public List evaluate() {
		
		Number n = (Number) numbers.get(numbers.size()-1);
		n.setPlacementLast(true);
		
		numbers = reverseNumbers(numbers);
		ans = setAnswer(getRowNum());
		int count = getLongestNumberCount(numbers);
		head = setHeader( count, getRowNum());		
		numbers.add(head);		
		int sum = 0;
		
		for(int i = 0; i < head.getDigits().size(); ++i){
			
			sum = sumBase(i+1);			
			
			if(sum > 9)
				processCarryLeft(sum, i+1);			
			else{				
				ans.addDigit( "" + sum );			
			}					
		}		
		
		numbers.add(ans);		
		reverseNumbers(numbers);		
		Collections.sort(numbers, Number.typeCmp);
		
		return numbers;
	}
	private int sumBase(int base){		
		
		int sum = 0;
		
		Iterator it = numbers.iterator();
		while(it.hasNext()){
			Number n = (Number) it.next();
			if( n.getDigits().size() >= base ){
				int c = ((Digit)n.getDigits().get(base-1)).getInteger();
				sum = sum + c;				
			}			
		}		
		return sum;		
	}		
	private void processCarryLeft(int sum, int base){
		
		//TODO: make this recursive!
		if(sum > 9 & sum < 99 ){
			int h = ((Digit)head.getDigits().get(base)).getInteger();
			head.getDigits().set(base, new Digit("" + ((sum/10)+ h)));				
			ans.addDigit( "" + (sum%10) );				
		}
		else if(sum > 99){
			int h1 = ((Digit)head.getDigits().get(base)).getInteger();
			head.getDigits().set(base, new Digit("" + (((sum/10)%10)+h1)));
			ans.addDigit( "" + ((sum%100)%10) );
			int h2 = ((Digit)head.getDigits().get(base+1)).getInteger();
			head.getDigits().set(base+1, new Digit("" + ((sum/100)+h2)));			
		}		
	}
	public Document toXml(){
		
		Document doc = null;
		
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = builder.newDocument();
			
			Element e = doc.createElement("equation");
			
			e.setAttribute("type", "addition");
			e.setAttribute("lpadding", "615" );
			e.setAttribute("rpadding", "267"); 
			e.setAttribute("length", "8");
			
			
			
			
			
			doc.appendChild(e);
			
			
			
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return null;
	}
}