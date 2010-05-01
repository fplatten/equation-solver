package com.showyourwork.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jscience.mathematics.function.Function;
import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Rational;

import com.showyourwork.engine.calculus.Differentiator;

public class Equation implements Cloneable{
	
	private Map<String, Variable.Local<Rational>> variables = new HashMap<String, Variable.Local<Rational>>();
	private Map<String, Polynomial<Rational>> values = new HashMap<String, Polynomial<Rational>>();
	private List<Token> leftSide = new ArrayList<Token>();
	private List<Token> rightSide = new ArrayList<Token>();
	Function<Rational, Rational> sumRight;
	Function<Rational, Rational> sumLeft;
	Function<Rational, Rational> leftRightTransposed;
	Set<Rational> answers = new HashSet<Rational>();
	
	public Function<Rational, Rational> getSumRight() {
		return sumRight;
	}
	public void setSumRight(Function<Rational, Rational> sumRight) {
		this.sumRight = sumRight;
	}
	public Function<Rational, Rational> getSumLeft() {
		return sumLeft;
	}
	public void setSumLeft(Function<Rational, Rational> sumLeft) {
		this.sumLeft = sumLeft;
	}	
	public Function<Rational, Rational> getLeftRightTransposed() {
		return leftRightTransposed;
	}
	public void setLeftRightTransposed(
			Function<Rational, Rational> leftRightTransposed) {
		this.leftRightTransposed = leftRightTransposed;
	}
	public List<Token> getLeftSide() {
		return leftSide;
	}
	public void setLeftSide(List<Token> leftSide) {
		this.leftSide = leftSide;
	}
	public List<Token> getRightSide() {
		return rightSide;
	}
	public void setRightSide(List<Token> rightSide) {
		this.rightSide = rightSide;
	}
	public Map<String, Variable.Local<Rational>> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Variable.Local<Rational>> variables) {
		this.variables = variables;
	}
	public Map<String, Polynomial<Rational>> getValues() {
		return values;
	}
	public void setValues(Map<String, Polynomial<Rational>> values) {
		this.values = values;
	}
	
	public Set<Rational> getAnswers() {
		return answers;
	}
	
	public void setAnswers(Set<Rational> answers) {
		this.answers = answers;
	}
	public void evaluate(){
		
		if(getVariables().get("x") != null){
			for (int i = 25; i >= -25; --i) {
				try {
					getVariables().get("x").set(Rational.valueOf(i, 1));
					//System.out.println("x = " + i +    "  sumLeft = " + sumLeft.evaluate().doubleValue() + "  sumRight = " + sumRight.evaluate().doubleValue());
					
					if (sumLeft.evaluate().equals(sumRight.evaluate())){
						//System.out.println("int x = " + i);
						answers.add(Rational.valueOf(i + "/1"));
					}
						
					
					for (Rational f : EquationConstants.fractions) {				
						//System.out.println("x = " + Rational.valueOf(i + "/1").plus(f));
						getVariables().get("x").set(Rational.valueOf(i + "/1").plus(f));
//						if(Rational.valueOf(i + "/1").plus(f).equals(Rational.valueOf("-1/4"))){
//							System.out.println(sumLeft);
//							System.out.println(sumRight);							
//							System.out.println("x = " + Rational.valueOf(i + "/1").plus(f) +    "  sumLeft = " + sumLeft.evaluate() + "  sumRight = " + sumRight.evaluate());
//						}
							
						
						if (sumLeft.evaluate().equals(sumRight.evaluate())){
							//System.out.println("x = " + Rational.valueOf(i + "/1").plus(f));
							answers.add(Rational.valueOf(i + "/1").plus(f));
						}
							
					}
				} catch (Exception e) {
					// catching the divide by zero exception when looking to solve: 20 / x = 5
					//e.printStackTrace();
				}
				
				
			}
			
		}
		else{
			System.out.println("answer = " + sumLeft.evaluate());
			answers.add(sumLeft.evaluate());
		}
		
	}
	public Rational evaluateUsingNewtonsMethod(Rational x){
			
		
		getVariables().get("x").set(x);
		Rational y = getSumLeft().evaluate();
		
		Rational m = Differentiator.getFirstDerivative(this , x, "x");
		
		Rational b = Differentiator.findYIntercept(x,y,m);
		Rational xint = Differentiator.findXIntercept(b, m);
		
		return xint;
		
	}	
	
	public void evaluateUsingNewtonsMethod(){
		
		//starting point
		Rational max = Rational.valueOf("100/1");
		Rational min = Rational.valueOf("-100/1");		
		int maxIter = 20;
		setSumLeft(getSumLeft().minus(getSumRight()));
		Rational result = max;
		Rational tolerance = Rational.valueOf("1/1"); 
		
		for (int i = 0; i < maxIter; ++i) {
			
			result = evaluateUsingNewtonsMethod(result);			
			System.out.println(result);			
		}
		
		
		
	}
	public void evaluate(String var){
		
		if(getVariables().get(var) != null){
			for (int i = 470; i >= -470; --i) {
				getVariables().get(var).set(Rational.valueOf(i, 1));
				//System.out.println("x = " + i +    "  sumLeft = " + sumLeft.evaluate().doubleValue() + "  sumRight = " + sumRight.evaluate().doubleValue());
				
				if (sumLeft.evaluate().equals(sumRight.evaluate())){
					System.out.println("int " + var + " = " + i);
					answers.add(Rational.valueOf(i + "/1"));
				}
					
				
				for (Rational f : EquationConstants.fractions) {				
					//System.out.println("x = " + Rational.valueOf(i + "/1").plus(f));
					getVariables().get(var).set(Rational.valueOf(i + "/1").plus(f));
					//System.out.println("x = " + Rational.valueOf(i + "/1").plus(f) +    "  sumLeft = " + sumLeft.evaluate().doubleValue() + "  sumRight = " + sumRight.evaluate().doubleValue());
					
					if (sumLeft.evaluate().equals(sumRight.evaluate())){
						//System.out.println("x = " + Rational.valueOf(i + "/1").plus(f));
						answers.add(Rational.valueOf(i + "/1").plus(f));
					}
						
				}
				
				
			}
			
		}
		else{
			System.out.println("answer = " + sumLeft.evaluate());
			answers.add(sumLeft.evaluate());
		}
		
	}
	public Object clone(){
		
		try {
			super.clone();
			
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return this;
		
	}
	
	
	
}