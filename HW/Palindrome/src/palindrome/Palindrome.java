package palindrome;

import java.util.Scanner;

/**
Assignment #6
Prompts the user for palindromes, and tells the user if they are indeed palindromes.
 @author Joey Bloom
 */
public class Palindrome
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Enter Q at any time to quit");
        do
        {
            System.out.print("Palindrome?: ");
            String maybePal = in.nextLine();
            if(maybePal.equalsIgnoreCase("Q")) break;
            StringBuilder builder = new StringBuilder();
            for(char c : maybePal.toCharArray())
            {
                if(Character.isLetterOrDigit(c))
                    builder.append(Character.toLowerCase(c));
            }
            if(builder.length() < 2) 
                System.out.println("NOT");
            else if(builder.toString().equals(builder.reverse().toString()))
                System.out.println("YEAH");
            else
                System.out.println("NOT");
            
        }while(true);
        System.out.println("BYE");
    }
}
/*
Output:

Welcome
Enter Q at any time to quit
Palindrome?: radar
YEAH
Palindrome?: Lewd did I live, & evil I did dwel.
YEAH
Palindrome?: I like Java
NOT
Palindrome?: J
NOT
Palindrome?: Straw? No, too stupid a fad, I put soot on warts.
YEAH
Palindrome?: @%^#$&&@$%^
NOT
Palindrome?: !@#$$#@!
NOT
Palindrome?: q
BYE
*/