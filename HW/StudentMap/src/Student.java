
/**
 Assignment #19
 A Student has a first name, a last name, and a unique integer ID.
 <p/>
 @author Joey Bloom
 */
public class Student implements Comparable<Student>
{
    private final String first;
    private final String last;
    private final int id;
    private static int idCounter = 0;

    /**
     Constructs a Student with a name. Each Student is assigned
     a unique integer ID number upon construction.
     <p/>
     @param firstName Student's first name
     @param lastName  Student's last name
     */
    public Student(String firstName, String lastName)
    {
        first = firstName;
        last = lastName;
        id = idCounter++;
    }

    /**
    Returns the first name
    @return first name
    */
    public String getFirst()
    {
        return first;
    }

    /**
    Returns the last name
    @return last name
    */
    public String getLast()
    {
        return last;
    }

    /**
    Returns the full name of the Student.
    @return last+ ", " + first
    */
    public String getName()
    {
        return last + ", " + first;
    }

    /**
    Return's the student's ID number
    @return unique integer ID
    */
    public int getId()
    {
        return id;
    }

    /**
    Students are equal if their ID numbers are equal
    @param other
    @return
    */
    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Student)
            return id == ((Student)other).getId();
        return false;
    }

    /**
    A Student's ID number is its hash code
    @return
    */
    @Override
    public int hashCode()
    {
        return id;
    }


    /**
    Compares Students by last name, then first name, then unique ID number
    @param other
    @return
    */
    @Override
    public int compareTo(Student other)
    {
        int lastCompare = last.compareTo(other.getLast());
        if(lastCompare != 0) return lastCompare;

        int firstCompare = first.compareTo(other.getFirst());
        if(firstCompare != 0) return firstCompare;

        return id - other.getId();
    }

    @Override
    public String toString()
    {
        return getName() + " " + id;
    }
}
