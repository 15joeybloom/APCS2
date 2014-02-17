package fract;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;

/**
 *
 * @author Joey
 */
public class Util
{
    public static void drawPolygon(Graphics2D g, ArrayList<? extends Point2D> points)
    {
        for(int i = 0; i < points.size() - 1; i++)
        {
            g.draw(new Line2D.Double(points.get(i),points.get(i+1)));
        }
        g.draw(new Line2D.Double(points.get(points.size()-1),points.get(0)));
    }

    public static <T extends Point2D> T midPoint(T p1, T p2)
    {
        T returnMe = (T) p1.clone();
        returnMe.setLocation((p1.getX() + p2.getX())/2,(p1.getY() + p2.getY())/2);
        return returnMe;
    }
}