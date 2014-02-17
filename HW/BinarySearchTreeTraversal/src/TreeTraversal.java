/**
 Assignment #19
 Prints the tree using inorder, preorder, and postorder traversal
 @author Joey Bloom
 */
public class TreeTraversal
{
    public static void main(String[] args)
    {
        BinarySearchTree tree = new BinarySearchTree();
        tree.add(10);
        tree.add(15);
        tree.add(13);
        tree.add(14);
        tree.add(11);
        tree.add(12);
        tree.add(18);
        tree.add(16);
        tree.add(17);
        tree.add(19);
        tree.add(20);
        tree.add(5);
        tree.add(3);
        tree.add(4);
        tree.add(2);
        tree.add(1);
        tree.add(8);
        tree.add(9);
        tree.add(6);
        tree.add(7);
        
        System.out.print("Inorder: "); tree.print();
        System.out.println();
        
        System.out.print("PreOrder: "); tree.printPreOrder();
        System.out.println();
        
        System.out.print("PostOrder: "); tree.printPostOrder();
        System.out.println();
    }
}

/*
Output: 

Inorder: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 

PreOrder: 1 2 4 3 7 6 9 8 5 12 11 14 13 17 16 20 19 18 15 10 

PostOrder: 10 5 3 2 1 4 8 6 7 9 15 13 11 12 14 18 16 17 19 20 

*/
