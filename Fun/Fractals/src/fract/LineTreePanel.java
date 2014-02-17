package fract;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * @author Joey
 *
 */
public class LineTreePanel extends Fractal
{
    public LineTreePanel()
    {
    }
    
    public void lineTree(Graphics2D g, double x1, double y1, double x2, double y2)
    {
        double d = Point.distance(x1, y1, x2, y2);
        double midx =  (x1 + x2)/2
    }

    @Override
    public void renderFractal() 
    {
        lineTree((Graphics2D)getGraphics(), 0, getHeight()/2, getWidth(), getHeight()/2);
    }
}
