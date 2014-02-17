package exp;


import java.util.*;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

/**
 An Operator takes a fixed number of numeric arguments and calculates a
 numeric results.
 <p>
 Each Operator has a syntax; Operators are defined in terms of other Operators.
 This syntax can look like "+ a 1" in prefix notation. The Operator defined by
 this syntax would simply increment its one argument.
 <p>
 All constructors are private; Operators must be defined through the
 {@link #defineOperator(java.lang.String, int, java.lang.String) defineOperator}
 method.
 Checkpoint 3
 <p>
 @author Joey Bloom
 */
public class Operator
{
    /**
     This value is true iff prefix notation is currently being used.
     */
    static boolean prefix = true;
    private final Script script;
    private final int numArgs;

    private static final GroovyShell shell = new GroovyShell();
    public static Map<String, Operator> symbolMap = new HashMap<>();

    static
    {
        new Operator("+", 2, shell.parse("a + b;"));
        new Operator("-", 2, shell.parse("a - b;"));
        new Operator("*", 2, shell.parse("a * b;"));
        new Operator("/", 2, shell.parse("a / b;"));
        new Operator("^", 2, shell.parse("Math.pow(a,b);"));
        new Operator("sqrt", 1, shell.parse("Math.sqrt(a)"));
        new Operator("pi",0, shell.parse("Math.PI"));
        new Operator("e",0,shell.parse("Math.E"));
        new Operator("ln",1,shell.parse("Math.log(a)"));
    }
    /**
     This is simply the lowercase alphabet. These chars are the vars
     used by the user to represent the arguments for their user defined
     operation.
     */
    public static final String[] vars =
    {
        "a", "b", "c", "d", "e",
        "f", "g", "h", "i", "j", "k",
        "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u",
        "v", "w", "x", "y", "z"
    };

    /**
     This regular expression is used to filter numbers from operators
     */
    public static final String numberRegex = "\\d*\\.?\\d*";

    /**
     Constructs an Operator which performs a user-defined operation
     using the script and is represented by the symbol. This constructor
     is only used directly for standard Operators like +, -, *, / that are
     predefined
     in the application, not user-defined.
     <p>
     @param symbol  represents this Operator
     @param numArgs the number of arguments taken by this Operator
     @param script  the script that this Operator performs
     */
    private Operator(String symbol, int numArgs, Script script)
    {
        this.script = script;
        this.numArgs = numArgs;
        symbolMap.put(symbol, this);
    }

    /**
     Constructs an Operator which performs a user-defined operation
     and is represented by a symbol.
     <p>
     @param symbol    used to represent the operation in expressions
     @param numArgs   number of arguments taken by this Operator
     @param operation the computation performed when this operator is specified.
     */
    private Operator(String symbol, int numArgs, String operation)
    {
        script = shell.parse(toCode(new StringTokenizer(operation)) + ";");
        this.numArgs = numArgs;
    }

    /**
     Defines a new Operator, and puts the new Operator in the symbolMap.
     <p>
     @param symbol    The symbol used to represent the Operator
     @param numArgs   The number of arguments that this Operator takes
     @param operation The syntax of this Operator
     @return The newly defined Operator
     */
    public static Operator defineOperator(String symbol, int numArgs, String operation)
    {
        Operator newOperator = new Operator(symbol, numArgs, operation);
        symbolMap.put(symbol, newOperator);
        return newOperator;
    }

    /**
     Returns the tokens in the tokenizer into code. The tokenizer
     should be all or part of a prefix or postfix notation sequence
     depending on the value of <code>prefix</code>.
     <p>
     @param tokenizer
     @return
     */
    private String toCode(StringTokenizer tokenizer)
    {
        if(prefix)
        {
            if(!tokenizer.hasMoreTokens()) return "";
            String str = tokenizer.nextToken();
            if(str.matches(numberRegex) || str.matches("[a-z]"))//if it's a number or variable
            {
                return str;
            }
            else //if it's an operator
            {
                int n = symbolMap.get(str).getNumArgs();
                String returnMe = "map.get(\"" + str + "\").eval(";
                if(n-- > 0)
                {
                    returnMe += toCode(tokenizer);
                    while(n-- > 0)
                    {
                        returnMe += "," + toCode(tokenizer);
                    }
                }
                returnMe += ")";
                return returnMe;
            }
        }
        else //if postfix
        {
            Stack<String> stack = new Stack<>();
            while(tokenizer.hasMoreTokens())
            {
                String str = tokenizer.nextToken();
                if(str.matches(Operator.numberRegex) || str.matches("[a-z]"))//if it's a number or variable
                {
                    stack.push(str);
                }
                else
                {
                    Operator operator = Operator.symbolMap.get(str);
                    int n = operator.getNumArgs();
                    String[] args = new String[n];
                    for(int i = n - 1; i >= 0; i--)//go in backwards order intentionally
                    {
                        args[i] = stack.pop();
                    }
                    String pushMe = "map.get(\"" + str + "\").eval(";
                    if(n-- > 0)
                    {
                        pushMe += args[0];
                        for(int i = 1; n-- > 0; i++)
                        {
                            pushMe += "," + args[i];
                        }
                    }
                    pushMe += ")";
                    stack.push(pushMe);
                }
            }
            return stack.pop();
        }
    }

    /**
     Returns the number of arguments this operation takes.
     <p>
     @return the number of arguments accepted by this operator.
     */
    public int getNumArgs()
    {
        return numArgs;
    }

    /**
     Calculates this operation with the given arguments.
     <p>
     @param args The arguments for this operation.
     @return the result of calculating this operation.
     @throws RuntimeException if args.length != getNumArgs
     */
    public double eval(double... args)
    {
        if(args.length == numArgs)
        {
            Binding binding = new Binding();
            for(int i = 0; i < args.length; i++)
            {
                binding.setVariable(vars[i], args[i]);
            }
            binding.setVariable("map", symbolMap);
            script.setBinding(binding);
            return (Double) script.run();
        }
        else throw new RuntimeException("incorrect number of arguments");
    }
}
