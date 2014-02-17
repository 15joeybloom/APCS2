/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rolling;

import java.util.Random;

/**
 Assignment #12
 Counts how many times 3 hexahedral dice need to be rolled until the values
 showing are all different
 <p/>
 @author Joey Bloom
 */
public class Rolling
{
    public static void main(String[] args)
    {
        Random rand = new Random();
        int die1;
        int die2;
        int die3;
        int counter = 0;
        do
        {
            die1 = rand.nextInt(6) + 1;
            die2 = rand.nextInt(6) + 1;
            die3 = rand.nextInt(6) + 1;
            counter++;
            System.out.println(die1 + " " + die2 + " " + die3);
        }
        while (die1 == die2 || die2 == die3 || die3 == die1);
        //the condition is the same as !(die1 != die2 && die2 != die3 && die3 != die1)
        //which will evaluate to true if any of the dice are equal

        System.out.println("count = " + counter);
    }
}
/*
 Output 1: 
 1 1 6
 1 1 4
 6 3 6
 1 3 6
 count = 4

 Output 2:
 3 2 2
 1 4 6
 count = 2

 Output 3:
 3 5 4
 count = 1
 */
