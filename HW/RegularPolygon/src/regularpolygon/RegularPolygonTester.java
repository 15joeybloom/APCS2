package regularpolygon;

/**
 * Assignment #2
 * Tests the RegularPolygon class
 * @author Joey Bloom
 */
public class RegularPolygonTester
{
    public static void main(String[] args)
    {
        RegularPolygon polygon = new RegularPolygon();

        RegularPolygon polygon2 = new RegularPolygon(4,10);
        RegularPolygon polygon3 = new RegularPolygon(8,5.75);
        RegularPolygon polygon4 = new RegularPolygon(19, 2);
        RegularPolygon polygon5 = new RegularPolygon(91, 0.5);

        printRegularPolygonInfo(polygon);
        polygon.draw();

        printRegularPolygonInfo(polygon2);
        polygon2.draw();

        printRegularPolygonInfo(polygon3);
        polygon3.draw();

        printRegularPolygonInfo(polygon4);
        polygon4.draw();

        printRegularPolygonInfo(polygon5);
        polygon5.draw();
    }

    private static void printRegularPolygonInfo(RegularPolygon polygon)
    {
        System.out.println(polygon);
        System.out.format("r = %.2f%n", polygon.getr());
        System.out.format("R = %.2f%n", polygon.getR());
        System.out.format("Vertex Angle: %.2f%n", polygon.vertexAngle());
        System.out.format("Perimeter = %.2f%n", polygon.Perimeter());
        System.out.format("Area = %.2f%n", polygon.Area());
        System.out.println();
    }
}

/*
Output:
Polygon
Number of Sides: 3
Side Length: 1.0
r = 0.29
R = 0.58
Vertex Angle: 60.00
Perimeter = 3.00
Area = 0.43

Polygon
Number of Sides: 4
Side Length: 10.0
r = 5.00
R = 7.07
Vertex Angle: 90.00
Perimeter = 40.00
Area = 100.00

Polygon
Number of Sides: 8
Side Length: 5.75
r = 6.94
R = 7.51
Vertex Angle: 135.00
Perimeter = 46.00
Area = 159.64

Polygon
Number of Sides: 19
Side Length: 2.0
r = 5.99
R = 6.08
Vertex Angle: 161.05
Perimeter = 38.00
Area = 113.86

Polygon
Number of Sides: 91
Side Length: 0.5
r = 7.24
R = 7.24
Vertex Angle: 176.04
Perimeter = 45.50
Area = 164.68
 */