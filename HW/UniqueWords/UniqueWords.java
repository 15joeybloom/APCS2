import java.io.*;
import java.util.*;
/**
Assignment #14
Prints all of the unique words from standard input
@author Joey Bloom
 */
public class UniqueWords
{
    public static void main(String[] args)
    {
        System.out.println("Type some words plz");
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in)))
        {
            Set<String> set = new TreeSet<>();
            String line;
            while((line = in.readLine()) != null && !line.equals(""))
            {
                StringTokenizer tokens = new StringTokenizer(line);
                while(tokens.hasMoreTokens()) set.add(tokens.nextToken());
                System.out.println("Type more words plz");
            }
            for(String word : set)
            {
                System.out.println(word);
            }
            System.out.println("Size: " + set.size());
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
/*
Output:

Type some words plz
one
Type more words plz
one two
Type more words plz
one two three
Type more words plz
one two three four
Type more words plz
one two three four five
Type more words plz
One
Type more words plz

One
five
four
one
three
two
Size: 6

 */