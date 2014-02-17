
import java.util.*;


/**
 Assignment #19

 @author Joey Bloom
 */
public class StudentMap
{
    private static Map<Student,String> grades = new TreeMap<>();
    private static Map<Integer,Student> idMap = new TreeMap<>();
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("HAI");

        System.out.println("Welcome to your gradebook!!!!!!!!! :D :P >D XP");
        String str;

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
    Prompts the user for thisKid and grade of the new student, then
    adds the student to the Set.
     */
    private static void promptAdd()
    {
        System.out.println("What is student's first name?");
        String first = in.nextLine();
        System.out.println("What is student's last name?");
        String last = in.nextLine();
        Student thisKid = new Student(first, last);
        idMap.put(thisKid.getId(),thisKid);
        System.out.println(thisKid.getName() + "\'s ID is " + thisKid.getId());
        System.out.println("What is " + thisKid.getName() + "\'s grade?");
        String grade = in.nextLine();
        String oldGrade = grades.put(thisKid,grade);
        if(oldGrade != null)
        {
            System.out.println(thisKid.getName() + " was already in your gradebook.");
            System.out.println(thisKid.getName() + "\'s grade was " + oldGrade);
            System.out.println(thisKid.getName() + "\'s grade was changed to " + grade);
            in.nextLine();
        }
    }

    /**
    Prompts the user for the thisKid of student to modify, and their
    new grade, then modifies the Set accordingly
     */
    private static void promptModify()
    {
        Student thisKid = promptId();
        if(thisKid != null)
        {
            String oldGrade = grades.get(thisKid);
            System.out.println(thisKid.getName() + "\'s grade is currently " + oldGrade);
            System.out.print("Enter new grade: ");
            String grade = in.nextLine();
            grades.put(thisKid,grade);
            System.out.println(thisKid.getName() + "\'s grade is now " + grade);
        }
        else
        {
            System.out.println("Student is not in the gradebook.");
            System.out.println("Cannot be modified.");
        }
        in.nextLine();
    }

    /**
    Prompts the user for the thisKid of the student to remove, then
    prints their thisKid and grade.
     */
    private static void promptRemove()
    {
        Student thisKid = promptId();
        if(thisKid != null)
        {
            String grade = grades.remove(thisKid);
            System.out.println(thisKid.getName() + " has been removed.");
            System.out.println(thisKid.getName() + " led a fulfilling life, with a grade of " + grade + ".");
            System.out.println(thisKid.getName() + " will be missed.");
        }
        else
        {
            System.out.println("Student is not in the gradebook.");
            System.out.println("Cannot be removed.");
        }
        in.nextLine();
    }

    /**
    Prints the Set, with the students and their grades.
     */
    private static void printGrades()
    {
        for(Student kid : grades.keySet())
        {
            System.out.println(kid + ": " + grades.get(kid));
        }
    }

    /**
    Prompts the user for a student's ID.
    @return the Student with ID number entered by the user,
            or null if no Student with that ID number exists
     */
    private static Student promptId()
    {
        try
        {
            System.out.println("What is the student's ID number?");
            Student id = idMap.get(in.nextInt());
            in.nextLine();
            return id;
        }
        catch(NumberFormatException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
}

/*
Output:

run:
HAI
Welcome to your gradebook!!!!!!!!! :D :P >D XP
Would you like to:
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is student's first name?
Joey
What is student's last name?
Bloom
Bloom, Joey's ID is 0
What is Bloom, Joey's grade?
A+

Would you like to:
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
m
What is the student's ID number?
0
Bloom, Joey's grade is currently A+
Enter new grade: A-
Bloom, Joey's grade is now A-


Would you like to:
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is student's first name?
Billy
What is student's last name?
Anderson
Anderson, Billy's ID is 1
What is Anderson, Billy's grade?
B

Would you like to:
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is student's first name?
Ronald
What is student's last name?
Velleuer
Velleuer, Ronald's ID is 2
What is Velleuer, Ronald's grade?
A

Would you like to:
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
m
What is the student's ID number?
2
Velleuer, Ronald's grade is currently A
Enter new grade: B
Velleuer, Ronald's grade is now B
a

Would you like to:
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
a
What is student's first name?
Tom
What is student's last name?
Kirby
Kirby, Tom's ID is 3
What is Kirby, Tom's grade?
Q

Would you like to:
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
r
What is the student's ID number?
3
Kirby, Tom has been removed.
Kirby, Tom led a fulfilling life, with a grade of Q.
Kirby, Tom will be missed.


Would you like to:
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
p
Anderson, Billy 1: B
Bloom, Joey 0: A-
Velleuer, Ronald 2: B

Would you like to:
(a) Add a student?
(m) Modify a grade?
(r) Remove a student?
(p) Print grades?
(q) Quit?
q
BIBI
BUILD SUCCESSFUL (total time: 2 minutes 53 seconds)

*/