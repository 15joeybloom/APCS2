package piglatinator;

import java.util.Scanner;

/**
Assignment #7
Acts as an english to pig latin translator
 @author Joey Bloom
 */
public class Piglatinator
{
    public static void main(String[] args)
    {
        System.out.print("I can translate English sentences and phrases into Pig Latin.");
        Scanner in = new Scanner(System.in);

        do
        {
            in.nextLine();
            System.out.println("Please type an English sentence or phrase and then press <Enter>");
            System.out.print("> ");
            StringBuilder english = new StringBuilder(in.nextLine());
            StringBuilder pigLatin = new StringBuilder();
            while(english.length() != 0)
            {
                if(Character.isLetter(english.charAt(0)))
                {
                    int firstNonLetter = 1;
                    for(;
                        firstNonLetter < english.length() &&
                        Character.isLetter(english.charAt(firstNonLetter));
                        firstNonLetter++);
                    pigLatin.append(toPigLatin(english.substring(0,firstNonLetter)));
                    english.delete(0,firstNonLetter);
                }
                else
                {
                    pigLatin.append(english.charAt(0));
                    english.delete(0,1);
                }
            }
            System.out.println("In Pig Latin that would be: ");
            System.out.println("> " + pigLatin);
            System.out.println();
            System.out.print("Would you like to translate another phrase?");
        }while(in.next().equalsIgnoreCase("y"));

    }

    /**
    Translates an english word into pig latin
    @param english word to be translated
            Precondition: english contains only lowercase and uppercase letters
    @return pig latin
    */
    public static String toPigLatin(String english)
    {
        int indexOfFirstVowel = -1;
        findVowel:
        for(int i = 0; i < english.length(); i++)
        {
            for(char c : new char[]{'a','e','i','o','u','A','E','I','O','U'})
            {
                if(english.charAt(i) == c)
                {
                    indexOfFirstVowel = i;
                    break findVowel;
                }
            }
        }
        if(indexOfFirstVowel < 0)
        {
            return english + "ay";
        }
        if(indexOfFirstVowel == 0)
        {
            return english + "yay";
        }
        String start = english.substring(0, indexOfFirstVowel);
        String end = english.substring(indexOfFirstVowel);
        if(Character.isUpperCase(start.charAt(0)))
        {
            start = start.toLowerCase();
            end = Character.toUpperCase(end.charAt(0)) + end.substring(1);
        }
        return end + start + "ay";
    }
}
/*
Output:

I can translate English sentences and phrases into Pig Latin.
Please type an English sentence or phrase and then press <Enter>
> Hasta la vista baby. - the Terminator
In Pig Latin that would be:
> Astahay alay istavay abybay. - ethay Erminatortay

Would you like to translate another phrase?y
Please type an English sentence or phrase and then press <Enter>
> System.out.println("I love Java");
In Pig Latin that would be:
> Emsystay.outyay.intlnpray("Iyay ovelay Avajay");

Would you like to translate another phrase?y
Please type an English sentence or phrase and then press <Enter>
> I have never let my schooling interfere with my education. - Mark Twain
In Pig Latin that would be:
> Iyay avehay evernay etlay myay oolingschay interfereyay ithway myay educationyay. - Arkmay Aintway

Would you like to translate another phrase?no way jose
*/