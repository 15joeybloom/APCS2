package turtle;

import TurtleGraphics.Pen;
import TurtleGraphics.StandardPen;
import java.awt.Color;
import java.util.*;

/**
 <p>
 @author Joey Bloom
 */
public class Dragon
{
    private static final ArrayList<Boolean> turns = new ArrayList<>(); //true = right, false = left

//    static
//    {
//        turns.add(Boolean.TRUE);
//    }

    private static void expandTurns()
    {
        ArrayList<Boolean> copy = new ArrayList<>(turns);
        Collections.reverse(copy);
        for(int i = 0; i < copy.size(); i++)
        {
            copy.set(i, !copy.get(i).booleanValue());
        }
        turns.add(Boolean.TRUE);
        turns.addAll(copy);
    }

    private static final Color[] cs;
    static
    {
        cs = new Color[256];
        int c = 0;
        for(int i = 0; i < 256; i++)
        {
            c = (c + 73) % 256;
            cs[i] = new Color(c,c,255-c);
        }
    }

    public static void main(String[] args)
    {
//        Pen p = new StandardPen();
        double sideLength = 300;
        final double RT2 = Math.sqrt(2);
        int itr = 15;
        for(int i = 0; i < itr; i++)
        {
//            p.setColor(cs[i]);
//            p.home(); p.turn(90);
//            p.turn(-45 * i);
//            for(Boolean b : turns)
//            {
//                p.move(sideLength);
//                p.turn(b.booleanValue() ? 90 : -90);
//            }
//            p.move(sideLength);
            expandTurns();
            sideLength /= RT2;
        }
        Pen p2 = new StandardPen();
        p2.setWidth(1);
        p2.setColor(cs[itr]);
        p2.home(); p2.turn(90);
        p2.turn(-45 * itr);
        for(Boolean b : turns)
        {
            p2.move(sideLength);
            p2.turn(b.booleanValue() ? 90 : -90);
        }
        p2.move(sideLength);
    }
}
