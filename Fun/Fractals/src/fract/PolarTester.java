package fract;

import java.awt.geom.Point2D;

/**
 * @author Joey
 *
 */
public class PolarTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        PolarPoint p1 = new PolarPoint(-1,90);
        PolarPoint p2 = new PolarPoint(1,90);
        PolarPoint p3 = new PolarPoint(-1,270);
        PolarPoint p4 = new PolarPoint(-1,450);

        PolarPoint p5 = new PolarPoint(1, 90);
        PolarPoint p6 = new PolarPoint(1, 91+360);

        assert p2.equals(p3);
        assert p2.compareTo(p3) == 0;
        assert !p3.equals(p1);
        assert p1.compareTo(p2) < 0;
        assert p1.equals(p4);
        assert p1.compareTo(p4) == 0;

        assert p6.compareTo(p5) > 0;

        PolarCoordinator c1 = new PolarCoordinator(0,0);
        PolarCoordinator c2 = new PolarCoordinator(1,1);

        System.out.println(c1);
        System.out.println("Rectangular (1,1) in polar is " + c1.toRect(new Point2D.Double(1,1)));
        System.out.println("Rectangular (-3,4) in polar is " + c1.toRect(new Point2D.Double(-3,4)));
        System.out.println("Rectangular (-1,-1) in polar is " + c1.toRect(new Point2D.Double(-1,-1)));


    }
}