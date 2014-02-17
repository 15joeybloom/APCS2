package fract;

import java.awt.geom.Point2D;

/**
 * @author Joey
 *
 * Degrees are used for all angle measures
 */
public class PolarCoordinator
{
    private double polex;
    private double poley;

    public PolarCoordinator(Point2D.Double pole)
    {
        this(pole.getX(),pole.getY());
    }
    public PolarCoordinator(double x,double y)
    {
        polex = x;
        poley = y;
    }
    public PolarCoordinator()
    {
        this(0,0);
    }

    public PolarPoint toRect(Point2D.Double pt)
    {
        double x = pt.getX() + polex;
        double y = pt.getY() + poley;

        double r = Math.sqrt(x * x + y * y);
        double theta = Math.toDegrees(Math.atan2(y, x));

        return new PolarPoint(r,theta);
    }

    @Override
    public String toString()
    {
        return "Pole: " + "(" + polex + ", " + poley + ")";
    }
}
