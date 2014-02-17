package exp;


import java.util.*;

/**
 Runs a simple user interface from the terminal that allows the user
 to enter expressions and parses them.
 Checkpoint 3
 @author Joey Bloom
 */
public class ExpressionParserTerminal
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Expression Parser");
        loop: do
        {
            System.out.println("Select an option: ");
            System.out.println("E: evaluate a "
                + (Operator.prefix ? "prefix" : "postfix") + " expression");
            System.out.println("D: define a new operator");
            System.out.println("O: view currently defined operators");
            System.out.println("P: toggle prefix/postfix");
            System.out.println("Q: quit");
            switch(in.nextLine())
            {
                case "e":
                case "E":
                    System.out.println("Enter a math expression in "
                        + (Operator.prefix ? "prefix" : "postfix") + " notation");
                    String expression = in.nextLine();
                    System.out.print("Result: ");
                    if(Operator.prefix)
                        System.out.println(evaluatePrefix(expression));
                    else
                        System.out.println(evaluatePostfix(expression));
                    break;
                case "d":
                case "D":
                    System.out.print("What is the operator's symbol? ");
                    String symbol = in.next();
                    in.nextLine();
                    System.out.print("How many args does it take? ");
                    int numArgs = in.nextInt();
                    in.nextLine();
                    System.out.println("What is the operation that the operator will perform/the syntax of the operator? ");
                    System.out.println("Use the lowercase letters of the alphabet starting at \"a\" to represent the args.");
                    System.out.println("Example: the syntax for the average of two numbers would be \"" + (Operator.prefix ? "/ + a b 2" : "a b + 2 /")+ "\".");
                    String operation = in.nextLine();
                    Operator.defineOperator(symbol,numArgs,operation);
                    break;
                case "o":
                case "O":
                    Set<String> operators = Operator.symbolMap.keySet();
                    for(String str : operators)
                    {
                        System.out.print(str + " ");
                    }
                    System.out.println();
                    break;
                case "p":
                case "P":
                    Operator.prefix = !Operator.prefix;
                    System.out.println("You are now using " + (Operator.prefix ? "prefix" : "postfix") + " notation.");
                    break;
                case "q":
                case "Q":
                    break loop;
                default:
                    System.out.println("Ha thats not an option silly. try again.");
                    break;
            }
        }
        while(true);
        System.out.println("KTHXBYE");
    }

    /**
    Overloaded method; calls evaluatePrefix(StringTokenizer)
    @param expression
        This is used to instantiate the StringTokenizer
        that is passed to evaluatePrefix(StringTokenizer)
    @return the number that is the expression evaluated as a
    prefix notation mathematical expression
    */
    private static double evaluatePrefix(String expression)
    {
        return evaluatePrefix(new StringTokenizer(expression));
    }
    /**
    Evaluates the expression in prefix notation; each token
    in the tokenizer is a number or operator.
    @param tokenizer tokens of the prefix expression
    @return the number that is obtained by evaluating the
    sequence of tokens as a prefix expression.
    */
    private static double evaluatePrefix(StringTokenizer tokenizer)
    {
        //get first token
        //if number
            //return number
        //if operator
            //return evaluate operator for evaluatePrefix of args
        String str = tokenizer.nextToken();
        if(str.matches(Operator.numberRegex))
            return Double.parseDouble(str);
        else
        {
            Operator operator = Operator.symbolMap.get(str);
            int numArgs = operator.getNumArgs();
            double[] args = new double[numArgs];
            for(int i = 0; i < numArgs; i++)
            {
                args[i] = evaluatePrefix(tokenizer);
            }
            return operator.eval(args);
        }
    }

    /**
    Evaluates the expression in postfix notation.
    @param expression a well-formed postfix notation expression
    @return the number that is obtained by evaluating the sequence of tokens as
    a postfix expression
    */
    private static double evaluatePostfix(String expression)
    {
        Stack<Double> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while(tokenizer.hasMoreTokens())
        {
            String str = tokenizer.nextToken();
            if(str.matches(Operator.numberRegex))//if it's a number
            {
                stack.push(Double.parseDouble(str));
            }
            else
            {
                Operator operator = Operator.symbolMap.get(str);
                int numArgs = operator.getNumArgs();
                double[] args = new double[numArgs];
                for(int i = numArgs - 1; i >= 0; i--)
                //traverse backwards intentionally to preserve
                //the order of arguments for noncommutative operations,
                //such as subraction or division.
                {
                    args[i] = stack.pop();
                }
                stack.push(operator.eval(args));
            }
        }
        return stack.pop();
    }
}
/*
This one shows the program's attention to the order of the arguments.
Output 1:
Welcome to Expression Parser
Select an option:
E: evaluate a prefix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
e
Enter a math expression in prefix notation
- 4 3
Result: 1.0
Select an option:
E: evaluate a prefix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
p
You are now using postfix notation.
Select an option:
E: evaluate a postfix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
e
Enter a math expression in postfix notation
4 3 -
Result: 1.0
Select an option:
E: evaluate a postfix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
q
KTHXBYE

This one shows the user-defined operator.
Output 2:
Welcome to Expression Parser
Select an option:
E: evaluate a prefix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
o
sqrt * + ^ / -
Select an option:
E: evaluate a prefix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
d
What is the operator's symbol? average
How many args does it take? 2
What is the operation that the operator will perform/the syntax of the operator?
Use the lowercase letters of the alphabet starting at "a" to represent the args.
Example: the syntax for the average of two numbers would be "/ + a b 2".
/ + a b 2
Select an option:
E: evaluate a prefix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
e
Enter a math expression in prefix notation
average 1 2
Result: 1.5
Select an option:
E: evaluate a prefix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
p
You are now using postfix notation.
Select an option:
E: evaluate a postfix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
e
Enter a math expression in postfix notation
.01 .02 average
Result: 0.015
Select an option:
E: evaluate a postfix expression
D: define a new operator
O: view currently defined operators
P: toggle prefix/postfix
Q: quit
q
KTHXBYE
*/
