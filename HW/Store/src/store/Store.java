package store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import sorting.Sorting;

/**
 Assignment #13
 A Store has many Items that can be sorted by ID number and printed.
 @author Joey Bloom
 */
public class Store
{
    private Item[] myStore;

    /**
     Constructs a Store with the Items described in the file
     at the location specified by filename
     <p/>
     @param fileName the name of the file with the items
     @throws IOException if the file cannot be found
     */
    public Store(String fileName) throws IOException
    {
        loadFile(fileName);
    }

    private void loadFile(String inFileName) throws IOException
    {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(Store.class.getResourceAsStream(inFileName))))
        {
            int numItems = Integer.parseInt(in.readLine());
            myStore = new Item[numItems];
            for(int i = 0; i < numItems; i++)
            {
                StringTokenizer tokenizer = new StringTokenizer(in.readLine());
                int id = Integer.parseInt(tokenizer.nextToken());
                int inv = Integer.parseInt(tokenizer.nextToken());
                myStore[i] = new Item(id, inv);
            }
        }
    }

    /**
     Prints the contents of the Store to the terminal
     */
    public void displayStore()
    {
        System.out.println("           Id       Inv");
        for(int i = 0; i < myStore.length; i++)
        {
            if(i % 10 == 0) System.out.println();

            StringBuilder line = new StringBuilder();

            line.append(Integer.toString(i + 1));
            while(line.length() < 3) line.insert(0, " ");

            line.append(myStore[i].getId());
            while(line.length() < 13) line.insert(3, " ");

            line.append(myStore[i].getInv());
            while(line.length() < 23) line.insert(13, " ");

            System.out.println(line);
        }
    }

    /**
     Sorts the Items in the Store using the generic quicksort algorithm
     that I wrote last year. I included the jar file containing this
     Sorting class in this project's lib folder. This project's doc folder
     also contains documentation for the sorting package, which contains one
     class of the name Sorting.
     */
    public void doSort()
    {
        ArrayList<Item> itemList = new ArrayList<>(Arrays.asList(myStore));
        Sorting.quickSort(itemList);
        for(int i = 0; i < myStore.length; i++)
        {
            myStore[i] = itemList.get(i);
        }
    }

    @Override
    public String toString()
    {
        return Arrays.toString(myStore);
    }
}
