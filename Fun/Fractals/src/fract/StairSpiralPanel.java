package fract;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;

/**
 * 
 * @author Joey Bloom
 *
 */
public class StairSpiralPanel extends Fractal
{
    private ArrayList<Point2D.Double> points;
    
    public StairSpiralPanel(ArrayList<Point2D.Double> ps)
    {
        points = ps;
    }

    @Override
    public void renderFractal() 
    {
        if(points.isEmpty())
        {
            return;
        }
        //set up clone of points
        ArrayList<Point2D.Double> pts = new ModularArrayList<>(points);
        for(int i = 0; i < pts.size(); i++)
        {
            pts.set(i, (Point2D.Double)pts.get(i).clone());
        }

        Graphics2D g2 = (Graphics2D) getGraphics();
        Util.drawPolygon(g2, points);
        Point2D.Double p1 = Util.midPoint(pts.get(0), pts.get(1));
        Point2D.Double p2 = Util.midPoint(pts.get(2), pts.get(3));
        for(int i = 3; i < 100 * points.size(); i++)
        {
            pts.set(i-2, p1);
            pts.set(i-1, p2);
            g2.draw(new Line2D.Double(p1,p2));
            p1 = Util.midPoint(p1, p2);
            p2 = Util.midPoint(pts.get(i), pts.get(i+1));
        }
    }
}