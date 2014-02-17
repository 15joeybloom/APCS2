package funloops;

/**
Assignment #4

 @author Joey Bloom
 */
public class FunLoops
{
    public static void main(String[] args)
    {
        printMagicSquares(10);
        System.out.println();
        System.out.println("Reversed Numbers: ");
//        System.out.println("123 --> " + numberReverse(123));
//        System.out.println("1005 --> " + numberReverse(1005));
//        System.out.println("2500 --> " + numberReverse(2500));
        System.out.println("12345 --> " + numberReverse(12345));
        System.out.println("10001 --> " + numberReverse(10001));
        System.out.println("1200 --> " + numberReverse(1200));
        System.out.println("5 --> " + numberReverse(5));
        System.out.println();
        System.out.println("LCMs");
        System.out.println("LCM (15,18) = " + lcm(15,18));
        System.out.println("LCM (40,12) = " + lcm(40,12));
        System.out.println("LCM (2,7) = " + lcm(2,7));
        System.out.println("LCM (100,5) = " + lcm(100,5));
    }

    /**
    Prints the first n magic squares to the terminal
    @param n how many magic squares to print.
    */
    public static void printMagicSquares(int n)
    {
        System.out.println("First " + n + " Magic Squares");

//        long counter = 1;
//        while(n > 0)
//        {
//            double sqrt = Math.floor(Math.sqrt(counter));
//            if(sqrt * sqrt == counter)
//            {
//                int i = 0;
//                int sum = 0;
//                while(sum < counter)
//                {
//                    sum += i;
//                    if(sum == counter)
//                    {
//                        System.out.println(counter);
//                        n--;
//                    }
//                    i++;
//                }
//            }
//            counter++;
//        }

        long counter = 0;
        int i = 0;
        while(n > 0)
        {
            counter += i;
            i++;
            //take sqrt, round down to integer.
            double sqrt = Math.floor(Math.sqrt(counter));
            if(sqrt * sqrt == counter)
            {
                System.out.println(counter);
                n--;
            }
        }
    }

    /**
    Returns the reverse of the parameter.
    Examples:
    numberReverse(123) == 321
    numberReverse(1005) == 5001
    numberReverse(2500) == 52
    @param n number to be reversed
    @return reversed number
     */
    public static long numberReverse(long n)
    {
        long reversed = 0;
        while(n > 0)
        {
            reversed *= 10;
            reversed += n % 10;
            n /= 10;
        }
        return reversed;
    }

    /**
    Returns the least common multiple of the two parameters
    @param one
    @param two
    @return
    */
    public static int lcm(int one, int two)
    {
        int product = one * two;
        int counter = Math.min(one,two);
        while(counter > 0)
        {
            int testOne = one / counter;
            int testTwo = two / counter;
            if(testOne * counter == one && testTwo * counter == two)
                return product / counter;
            counter--;
        }
        return product;
    }
}

/*
First 10 Magic Squares
0
1
36
1225
41616
1413721
48024900
1631432881
55420693056
1882672131025

Reversed Numbers:
12345 --> 54321
10001 --> 10001
1200 --> 21
5 --> 5

LCMs
LCM (15,18) = 90
LCM (40,12) = 120
LCM (2,7) = 14
LCM (100,5) = 100
*/
