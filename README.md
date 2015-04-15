## Equation Solver ##

Welcome to the Equation Solver.  Equation Solver is an equation parser and solver for [JScience](http://jscience.org/) and is the solution engine used for the [Equation Solver](http://equation-solver.appspot.com/) web site.

If you're just looking for a guide on how to build your own equation parser, I wrote this [wiki entry](http://code.google.com/p/equation-solver/wiki/HowToBuildAnEquationParser) on how to get started.

The Equation Solver uses OOD Principals.  Here's a sample unit test:
```
//test implied multiplication
String line = "(x-3)(x+5)-(x+2)(x-4) = 2x-5(x+4)";
		
EquationBuilder b = new EquationBuilder();
new EquationParser(b).parse(line);
		
Equation e = b.build();		
e.evaluateUsingNewtonsMethod();

assertTrue(e.getAnswers().contains(Rational.valueOf("-13/7")));
```
Currently in the process of developing a matrix parser and tools for differentiation (this is done in 0.0.2 and just needs testing), integration, and handling nested equations.  I'm no math expert, so please check the issues list and let me know if you'd like to contribute.

NOTE: To build and/or run the Equation Solver code you need to download [JScience version 4.3](http://jscience.org/jscience-4.3.1-bin.zip) and add it to your classpath.  Details located on the [Developer's Guide](http://code.google.com/p/equation-solver/wiki/DevelopersGuide)

ANOTHER NOTE:  If you're looking for a web site that solves algebraic equations, try [Equation Solver](http://equation-solver.appspot.com/).
