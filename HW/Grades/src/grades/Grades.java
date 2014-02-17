package grades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
Assignment #5

@author Joey Bloom
 */
public class Grades
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(new File("in.txt")));
        
        String row = in.readLine();
        loop:
        while(row != null)
        {
            StringTokenizer grades = new StringTokenizer(row);
            int numGrades = 0;
            double average = 0;
            boolean hasF = false;
            while(grades.hasMoreTokens())
            {
                numGrades++;
                String grade = grades.nextToken();
                System.out.print(grade.toUpperCase() + " ");
                switch(grade)
                {
                    case "A":
                    case "a":
                        average += 4;
                        break;
                    case "B":
                    case "b":
                        average += 3;
                        break;
                    case "C":
                    case "c":
                        average += 2;
                        break;
                    case "D":
                    case "d":
                        average += 1;
                        break;
                    case "F":
                    case "f":
                        average += 0;
                        hasF = true;
                        break;
                    default:
                        System.out.println("Illegal grade String: " + grade);
                        System.out.println();
                        row = in.readLine();
                        continue loop;
                }                
            }
            average /= numGrades;                
            if(numGrades < 4)
            {
                System.out.println("Ineligible, taking less than 4 classes.");
            }
            else if(hasF)
            {
                if(average < 2)
                {
                    System.out.println("Ineligible, gpa below 2.0 and has F grade");
                }
                else
                {
                    System.out.println("Ineligible, gpa above 2.0 but has F grade");
                }
            }
            else if(average < 2)
            {
                System.out.println("Ineligible, gpa below 2.0");
            }
            else
            {
                System.out.println("Eligible");
            }
            row = in.readLine();
            System.out.println();
        }
    }
}
/*
Output:

B B C B F Ineligible, gpa above 2.0 but has F grade

C D C Ineligible, taking less than 4 classes.

A B A Ineligible, taking less than 4 classes.

7 Illegal grade String: 7

C B D D D C Ineligible, gpa below 2.0

A A B A A B A Eligible

D C F F D Ineligible, gpa below 2.0 and has F grade

*/