import java.util.*;
/**
Assignment #16

 */
public class GradesMap
{
    private static Map<String,String> grades = new TreeMap<>();
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("HAI");

        System.out.println("Welcome to your gradebook!!!!!!!!! :D :P >D XP");
        String str = "";
        
        loop:
        do
        {
            System.out.println("Would you like to: ");
            System.out.println("(a) Add a student?");
            System.out.println("(m) Modify a grade?");
            System.out.println("(r) Remove a student?");
            System.out.println("(p) Print grades?");
            System.out.println("(q) Quit?");
            str = in.next();
            in.nextLine();
            switch(str)
            {
                case "a":
                case "A":
                    promptAdd();
                    break;
                    
                case "m":
                case "M":
                    promptModify();
                    break;
                    
                case "r":
                case "R":
                    promptRemove();
                    break;
                    
                case "p":
                case "P":
                    printGrades();
                    break;
                    
                case "q":
                case "Q":                
                    break loop;
                    
                default:
                    System.out.println("JOO ARE WRONG");
                    break;
            }
            System.out.println();
        }
        while(true);
        System.out.println("BIBI");
    }
    
    /**
    Prompts the user for name and grade of the new student, then
    adds the student to the Set.
     */
    private static void promptAdd()
    {
        String name = promptName();
        System.out.println("What is " + name + "\'s grade?");
        String grade = in.nextLine();
        String oldGrade = grades.put(name,grade);
        if(oldGrade != null)
        {
            System.out.println(name + " was already in your gradebook.");
            System.out.println(name + "\'s grade was " + oldGrade);
            System.out.println(name + "\'s grade was changed to " + grade);
            in.nextLine();
        }
    }
    
    /**
    Prompts the user for the name of student to modify, and their
    new grade, then modifies the Set accordingly
     */
    private static void promptModify()
    {
        String name = promptName(); 
        if(grades.containsKey(name))
        {
            String oldGrade = grades.get(name);
            System.out.println(name + "\'s grade is currently " + oldGrade);
            System.out.print("Enter new grade: ");
            String grade = in.nextLine();
            grades.put(name,grade);
            System.out.println(name + "\'s grade is now " + grade);
        }
        else
        {
            System.out.println(name + " is not in the gradebook.");
            System.out.println("Cannot be modified.");
        }
        in.nextLine();
    }
    
    /**
    Prompts the user for the name of the student to remove, then
    prints their name and grade.
     */
    private static void promptRemove()
    {
        String name = promptName();
        if(grades.containsKey(name))
        {
            String grade = grades.remove(name);
            System.out.println(name + " has been removed.");
            System.out.println(name + " led a fulfilling life, with a grade of " + grade + ".");
            System.out.println(name + " will be missed.");
        }
        else
        {
            System.out.println(name + " is not in the gradebook.");
            System.out.println("Cannot be removed.");
        }
        in.nextLine();
    }
    
    /**
    Prints the Set, with the students and their grades.
     */
    private static void printGrades()
    {
        for(String name : grades.keySet())
        {
            System.out.println(name + ": " + grades.get(name));
        }
    }
    
    /**
    Prompts the user for a student's name.
    @return the next line of user input
     */
    private static String promptName()
    {
        System.out.println("What is name of student?");
        return in.nextLine();
    }
}

/*
Output 1:

HAI
Welcome to your gradebook!!!!!!!!! :D :P >D XP
Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is name of student?
Joey
What is Joey's grade?
A++++

Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is name of student?
X
What is X's grade?
X

Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is name of student?
Travis
What is Travis's grade?
F#

Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
m
What is name of student?
X
X's grade is currently X
Enter new grade: Y
X's grade is now Y


Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is name of student?
Jenny
What is Jenny's grade?
C

Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
r
What is name of student?
Travis
Travis has been removed.
Travis led a fulfilling life, with a grade of F#.
Travis will be missed.


Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
p
Jenny: C
Joey: A++++
X: Y

Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
q
BIBI

Output 2:
HAI
Welcome to your gradebook!!!!!!!!! :D :P >D XP
Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
m
What is name of student?

 is not in the gradebook.
Cannot be modified.


Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
r
What is name of student?
7
7 is not in the gradebook.
Cannot be removed.


Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
p

Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is name of student?
joe
What is joe's grade?
8

Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is name of student?
Joe
What is Joe's grade?
8

Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
m
What is name of student?
JOE
JOE is not in the gradebook.
Cannot be modified.


Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
r
What is name of student?
JOe
JOe is not in the gradebook.
Cannot be removed.


Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
p
Joe: 8
joe: 8

Would you like to: 
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
q
BIBI

 */