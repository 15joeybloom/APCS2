
/**
 Assignment #17

 @author Joey Bloom
 */
public class IntSet
{
    private Node root;

    public IntSet()
    {

    }

    /**
    Adds the parameter to the set. If the parameter
    is already in the set, no action is taken.
    @param x int to add
    */
    public void add(int x)
    {
        if(root == null)
        {
            root = new Node(x);
        }
        root.add(x);
    }

    /**
    Removes the parameter from the set if it is present.
    If the parameter is not present, not action is taken.
    @param x int to remove
     */
    public void remove(int x)
    {
        Node parent = null;
        Node toRemove = root;
        boolean isLeftOfParent = true;
        loop:
        while(true)
        {
            if(toRemove == null) return;

            if(toRemove.dat < x)
            {
                parent = toRemove;
                toRemove = toRemove.right;
                isLeftOfParent = false;
            }
            else if(toRemove.dat > x)
            {
                parent = toRemove;
                toRemove = toRemove.left;
                isLeftOfParent = true;
            }
            else
            {
                break loop;
            }
        }
        //x is present in the set, so let's remove it
        //x is in the toRemove Node
        boolean leftNull = toRemove.left == null;
        boolean rightNull = toRemove.right == null;
        if(leftNull || rightNull)
        {
            Node toReplace;
            //if both children null, then set parent link to null
            if(leftNull && rightNull)
            {
                toReplace = null;
            }
            //if left child is null, then set parent link to right child
            else if(leftNull)
            {
                toReplace = toRemove.right;
            }
            //if right child is null, then set parent link to left child
            else
            {
                toReplace = toRemove.left;
            }

            //if replacing root node
            if(parent == null)
                root = toReplace;
            else if(isLeftOfParent)
                parent.left = toReplace;
            else
                parent.right = toReplace;
        }
        //if both children non-null
        else
        {
            //look for smallest node in right subtree
            Node smallestParent = toRemove;
            Node smallest = toRemove.right;
            while(smallest.left != null)
            {
                smallestParent = smallest;
                smallest = smallest.left;
            }

            //since smallest's left child is null,
            //give smallest's right child to the parent
            if(smallestParent == toRemove)
                smallestParent.right = smallest.right;
            else
                smallestParent.left = smallest.right;

            //replace toRemove with smallest
            smallest.left = toRemove.left;
            smallest.right = toRemove.right;
            if(parent == null)
                root = smallest;
            else if(isLeftOfParent)
                parent.left = smallest;
            else
                parent.right = smallest;
        }
    }

    /**
    Prints the set in sorted order
    */
    public void print()
    {
        if(root == null) return;
        root.printTree();
        System.out.println();
    }

    /**
    Returns true iff the set contains the parameter
    @param x the int to search for
    @return true iff the set contains x
    */
    public boolean contains(int x)
    {
        Node current = root;
        do
        {
            if(current == null) return false;
            int val = current.dat;
            if(x == val) return true;
            if(x < val) current = current.left;
            else current = current.right;
        }
        while(true);
    }

    /**
    For my testing. Prints a diagram of the tree.
    */
    void printDiagram()
    {
        System.out.println("Root: " + root.childrenDiagram(""));
    }

    private class Node
    {
        Node left;
        Node right;
        int dat;

        Node(int d)
        {
            dat = d;
        }

        void add(int x)
        {
            if(dat < x)
            {
                if(right == null)
                {
                    right = new Node(x);
                }
                else
                {
                    right.add(x);
                }
            }
            else if(dat > x)
            {
                if(left == null)
                {
                    left = new Node(x);
                }
                else
                {
                    left.add(x);
                }
            }
        }

        /**
        Prints the left subtree, this Node, and the right subtree.
        */
        void printTree()
        {
            if(left != null) left.printTree();
            System.out.print(dat + " ");
            if(right != null) right.printTree();
        }

        @Override
        public String toString()
        {
            return Integer.toString(dat);
        }

        /**
        For my testing. displays the set in a tree-like shape.
        @param sps A String of ' ' chars, that give the tree its shape
        @return
        */
        String childrenDiagram(String sps)
        {
            sps += "  "; // 2 spaces each level
            String returnMe = dat + "\n";
            returnMe +=
                (left == null ? "": sps + "Left: " + left.childrenDiagram(sps)) +
                (right == null ? "": sps + "Right: " + right.childrenDiagram(sps));

            return returnMe;
        }
    }

    public static void main(String[] args)
    {
        IntSet set = new IntSet();
        for(int i = 5; i != 2; i = (i+3)%20)
        {
            set.add(i);
        }
        set.add(2);

        System.out.println("Full: ");
        set.print();
//        set.printDiagram();

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

/*
Output:

Full:
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
Remove -1:
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
Add 3:
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
Contains 1: true
Contains 27: false

Remove 0:
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
Remove 7:
1 2 3 4 5 6 8 9 10 11 12 13 14 15 16 17 18 19
Remove 14:
1 2 3 4 5 6 8 9 10 11 12 13 15 16 17 18 19
Remove 1:
2 3 4 5 6 8 9 10 11 12 13 15 16 17 18 19
Remove 8:
2 3 4 5 6 9 10 11 12 13 15 16 17 18 19
Remove 15:
2 3 4 5 6 9 10 11 12 13 16 17 18 19
Remove 2:
3 4 5 6 9 10 11 12 13 16 17 18 19
Remove 9:
3 4 5 6 10 11 12 13 16 17 18 19
Remove 16:
3 4 5 6 10 11 12 13 17 18 19
Remove 3:
4 5 6 10 11 12 13 17 18 19
Remove 10:
4 5 6 11 12 13 17 18 19
Remove 17:
4 5 6 11 12 13 18 19
Remove 4:
5 6 11 12 13 18 19
Remove 11:
5 6 12 13 18 19
Remove 18:
5 6 12 13 19
Remove 5:
6 12 13 19
Remove 12:
6 13 19
Remove 19:
6 13
Remove 6:
13
*/