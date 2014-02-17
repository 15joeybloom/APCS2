package kochcurve;

import TurtleGraphics.Pen;
import TurtleGraphics.SketchPadWindow;
import TurtleGraphics.StandardPen;

/**
 Assignment #11<br/>
 Draws a Koch Snowflake using TurtleGraphics.
 <p/>
 @author Joey Bloom
 */
public class KochCurve
{

    public static void main(String[] args)
    {
        SketchPadWindow pad = new SketchPadWindow(1000,800);
        Pen p = new StandardPen(pad);
        p.setWidth(1);
        p.up();
        p.move(-300, 200);
        p.setDirection(0);
        p.down();
        drawKochCurve(p, 8, 600);
        p.turn(-120);
        drawKochCurve(p, 8, 600);
        p.turn(-120);
        drawKochCurve(p, 8, 600);
        p.turn(-120);
        
    }

    private static void drawKochCurve(Pen p, int level, double length)
    {
        if (level < 1)
        {
            p.move(length);
        }
        else
        {
            level--;
            length /= 3;
            drawKochCurve(p, level, length);
            p.turn(60);
            drawKochCurve(p, level, length);
            p.turn(-120);
            drawKochCurve(p, level, length);
            p.turn(60);
            drawKochCurve(p, level, length);
        }
    }
}
