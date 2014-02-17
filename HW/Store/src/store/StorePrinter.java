package store;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 Assignment #13
 Tests the Store class
 @author Joey Bloom
 */
public class StorePrinter
{
    public static void main(String[] args)
    {
//        JOptionPane.showMessageDialog(null,"hi");
        try
        {
            Store store = new Store("file50.txt");
            System.out.println("Unsorted: ");
            store.displayStore();
            System.out.println("\n-----------------------\n");
            store.doSort();
            System.out.println("Sorted: ");
            store.displayStore();
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
//        JOptionPane.showMessageDialog(null, "Done!");
    }
}
