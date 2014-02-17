package irs;

import java.util.Scanner;

/**
 * Assignment #3
 * Tests the TaxFile class
 * @author Joey Bloom
 */
public class IRS 
{
    public static void main(String[] args)
    {
        System.out.print("Married? (y or n) : ");
        Scanner in = new Scanner(System.in);
        String mar = in.next();
        boolean married;
        if(mar.equalsIgnoreCase("Y"))
        {
            System.out.println("Married");
            married = true;
        }
        else
        {
            System.out.println("Not Married");
            married = false;
        }
        System.out.print("Taxable Income? : $");
        double inc = in.nextDouble();
        TaxFile test = new TaxFile(married, inc);
        System.out.println(test.getTax());
    }
}
/*
Output 1:
Married? (y or n) : n
Not Married
Taxable Income? : $15500
2325.0

Output 2:
Married? (y or n) : n
Not Married
Taxable Income? : $100000
25152.25

Output 3:
Married? (y or n) : y
Married
Taxable Income? : $50000
8100.0

Output 4:
Married? (y or n) : y
Married
Taxable Income? : $125000
29197.5

*/