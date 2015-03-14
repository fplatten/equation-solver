### Equation Solver 0.0.4 Release Notes ###

Added an evaluate method using Newton's Method to the Equation class which required fixing up the methods in the Differentiator class.  This really speeds up the time to find a solution when solving for x.

Added three methods to the Integrator class trapezoidal using Equal and Unequal spacing and an Average Ordinate method.

Fixed a bug in the parser to pick up on a urinary operator after the equals sign when the next value is a number.  It was working correctly only if a -x followed the equals symbol.