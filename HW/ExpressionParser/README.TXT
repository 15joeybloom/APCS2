EXPRESSION PARSER
by Joey Bloom
11/26/13

-----------------------
TABLE OF CONTENTS
1. Launching the program
2. Writing an expression
3. Defining an operator
4. Highlights
5. Bugs
-----------------------

-----------------------
1. Launching the program
-----------------------
Double-click ExpressionParser.jar inside the "ExpressionParser" folder.



------------------------
2. Writing an expression
------------------------
Enter your expression in the text field at the top of the 
program window.

Expressions use prefix or postfix notation. To change between
the two, right-click anywhere in the program. The 
mundane expression "2 + 2" is written in
prefix notation as "+ 2 2" or in postfix notation as "2 2 +".
For more help with prefix/postfix, visit 
http://www.cs.man.ac.uk/~pjj/cs2121/fix.html 



------------------------
3. Defining an operator
------------------------
When the program is first launched,
several basic operators are already defined, and they
are listed at the bottom-left of the program window.

You can define your own operators using the ones already provided!
When the program is launched, the form for defining an operator
is already completed for the "average" operator. This operator
computes the arithmetic mean of its two arguments.

Each operator has a symbol, a number of arguments and a syntax.
The symbol is the sequence of characters used to indicate the operator
in expressions. The number of arguments is self explanatory.
The syntax is the operation that the operator will perform.
The syntax looks just like an expression, except that it uses letters
to represent the arguments. "a" represents the first arguments, "b"
the second, etc.

-----------------------
4. Highlights
-----------------------
My favorite aspect of this program is its use of Groovy.
Essentially, Java classes in the Groovy library are used
to turn Strings into code. This is accomplished with the
GroovyShell.parse(String) method. This method returns a Script
which acts just like a method, except that it was defined at runtime,
which is difficult or impossible using only Java.

The data structure used in this program is a Map. It maps the operator
symbols to their Operator object.


----------------------
5. Bugs
----------------------
No known bugs other than typical swing quirks.
