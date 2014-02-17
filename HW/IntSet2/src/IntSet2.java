
import java.util.*;


/**
 Assignment #18
 Implements a Set of ints using a TreeSet<Integer>
 @author Joey Bloom
 */
public class IntSet2
{
    private Set<Integer> set;

    public IntSet2()
    {
        set = new TreeSet<>();
    }

    /**
    Adds the parameter to the set. If the parameter
    is already in the set, no action is taken.
    @param x int to add
    */
    public void add(int x)
    {
        set.add(x);
    }

    /**
    Removes the parameter from the set if it is present.
    If the parameter is not present, not action is taken.
    @param x int to remove
     */
    public void remove(int x)
    {
        set.remove(x);
    }

    /**
    Prints the set in sorted order
    */
    public void print()
    {
        IntSetItr itr = iterator();
        while(itr.hasNext())
        {
            int x = itr.next();
            System.out.print(x + " ");
        }
        System.out.println();
    }

    /**
    Returns the iterator for the set.
    */
    public IntSetItr iterator()
    {
        return new IntSetItr();
    }

    public class IntSetItr
    {
        private Iterator itr;

        public IntSetItr()
        {
            itr = set.iterator();
        }

        public boolean hasNext()
        {
            return itr.hasNext();
        }

        public int next()
        {
            return (int)itr.next();
        }
    }

    /**
    Returns true iff the set contains the parameter
    @param x the int to search for
    @return true iff the set contains x
    */
    public boolean contains(int x)
    {
        return set.contains(x);
    }

    public static void main(String[] args)
    {
        IntSet2 set = new IntSet2();
        for(int i = 5; i != 2; i = (i+3)%20)
        {
            set.add(i);
        }
        set.add(2);

        System.out.println("Full: ");
        set.print();

        System.out.println("Remove -1: ");
        set.remove(-1);
        set.print();

        System.out.println("Add 3: ");
        set.add(3);
        set.print();

        System.out.println("Contains 1: " + set.contains(1));
        System.out.println("Contains 27: " + set.contains(27));

        System.out.println();

        for(int i = 0; i != 13; i = (i + 7)%20)
        {
            System.out.println("Remove " + i + ": ");
            set.remove(i);
            set.print();
        }
    }
}