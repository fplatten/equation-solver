package com.showyourwork.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquationParser {
	
	EquationBuilder b;
	
	public EquationParser(EquationBuilder b){
		this.b = b;
	}
	
	public List<Token> parse(String line){
		
		List<Token> tokens = parseLine(line);
		tokens = convertFractionsToNumbers(tokens);
		tokens = insertMultipliers(tokens);
		tokens = convertUrnaryNumbers(tokens);
		
		b.setTokens(tokens);
		
		return tokens;
		
	}
	public List<Token> parseLine(String line) {

		List<Token> tokens = new ArrayList<Token>();
		Pattern pat;

		for (int i = 0; i < EquationConstants.srchstrings.length; ++i) {

			pat = Pattern.compile(EquationConstants.srchstrings[i], Pattern.CASE_INSENSITIVE);
			Matcher matcher = pat.matcher(line);
			boolean result = matcher.find();

			while (result) {

				Token t = new Token();

				if (EquationConstants.tokennames[i].equals("EQUALS"))
					t.setValue("=");
				else if (EquationConstants.tokennames[i].equals("GREATERTHAN"))
					t.setValue(">");
				else if (EquationConstants.tokennames[i].equals("LESSTHAN"))
					t.setValue("<");
				else
					t.setValue(matcher.group(0));

				if (EquationConstants.tokennames[i].equals("WORD")) {
					if (isReserved(t.getValue()))
						t.setName("COMMAND");
					else{						
						t.setName("VARIABLE");
					}
				} else
					t.setName(EquationConstants.tokennames[i]);

				t.setLocation(matcher.start());
				tokens.add(t);
				result = matcher.find();
			}

		}

		Collections.sort(tokens, Token.locationCmp);

		return tokens;

	}
	
	public List<Token> convertFractionsToNumbers(List<Token> tokens) {
		
		List<Token> withFractions = new ArrayList<Token>();
		Iterator<Token> it = tokens.iterator();
		
		while (it.hasNext()) {
			
			Token t = it.next();
			boolean addt = true;
			
			if (t.getName().equals("OPENBRAC")) {
				Token nt = new Token();
				nt.setName("FRACTION");
				
				StringBuffer fraction = new StringBuffer("");
				boolean cont = true;
				while(cont){
					
					Token tf = it.next();
					if(!tf.getName().equals("CLOSEBRAC")){
						fraction.append(tf.getValue());
					}
					else{
						nt.setValue(fraction.toString());
						withFractions.add(nt);
						addt = false;
						cont = false;
					}
				}
			}
			
			if(addt){
				withFractions.add(t);				
			}			
		}
		
		return withFractions;
	}
public List<Token> insertMultipliers(List<Token> list) {

	List<Token> withMultipliers = new ArrayList<Token>();

	Token prev = null;
	for (Token t : list) {

		if (t.getName().equals("VARIABLE")) {
			if (prev != null && !prev.getName().equals("OPENPAREN") && !prev.isOperator() && !prev.getName().equals("EQUAL")) {
				Token nt = new Token();
				nt.setValue("*");
				nt.setName("MULTIPLY");
				withMultipliers.add(nt);
			}

		}
		if (t.getName().equals("OPENPAREN")) {
			if (prev != null && (prev.getName().equals("NUMBER") || prev.getName().equals("VARIABLE"))) {
				Token nt = new Token();
				nt.setValue("*");
				nt.setName("MULTIPLY");
				withMultipliers.add(nt);
			}
			else if(prev != null && prev.isOperator()){
				Token ntn = new Token();
				ntn.setValue("1");
				ntn.setName("NUMBER");
				withMultipliers.add(ntn);
				Token ntm = new Token();
				ntm.setValue("*");
				ntm.setName("MULTIPLY");
				withMultipliers.add(ntm);					
			}
			else if(prev != null && prev.getName().equals("CLOSEPAREN")){					
				Token ntm = new Token();
				ntm.setValue("*");
				ntm.setName("MULTIPLY");
				withMultipliers.add(ntm);
			}
		}		

		prev = t;
		withMultipliers.add(t);
	}

	return withMultipliers;
	}
	public boolean isReserved(String str) {

	boolean result = false;

	for (int i = 0; i < EquationConstants.reserved.length; ++i) {

		if (str.equals(EquationConstants.reserved[i]))
			return true;
	}

	return result;
	}
	public List<Token> convertUrnaryNumbers(List<Token> tokens) {

		List<Token> withUrnaries = new ArrayList<Token>();
		Iterator<Token> it = tokens.iterator();
		Token prev = null;
		while (it.hasNext()) {
			
			Token t = it.next();
			
			if (t.getName().equals("MINUS")) {
				
				if(prev == null){
					
					Token zt = new Token();
					zt.setValue("0");
					zt.setName("NUMBER");					
					withUrnaries.add(zt);
					
				}				
				else if(prev.isOperator() || prev.getName().equals("OPENPAREN") || prev.getName().equals("EQUAL") ){
					t = it.next();
					t.setValue("-" + t.getValue());
				}
				
			}
			prev = t;
			withUrnaries.add(t);
		}

		return withUrnaries;
	}
}