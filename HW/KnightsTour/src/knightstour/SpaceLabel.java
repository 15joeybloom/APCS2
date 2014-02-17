package knightstour;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
Assignment #9
Acts as a JLabel, but also stores the space's location on
the chessboard and the correct background color to produce
the black-white alternating pattern typical of chessboards.
@author Joey Bloom
 */
public class SpaceLabel extends JLabel
{
    private final int r,c;
    private final Color background;
    private final Color backgroundHighlight = Color.blue;
    private final Color foreground;
    private final Color foregroundHighlight = Color.yellow;

    /**
    Constructs a SpaceLabel, designating the text to display
    on the label and the location of the space on the chessboard.
    @param str  initial text to display
    @param r    row of the space
    @param c    column of the space
    */
    public SpaceLabel(String str, int r, int c)
    {
        super(str);
        this.r = r;
        this.c = c;
        if((this.r + this.c) % 2 == 0)
        {
            background = Color.black;
            foreground = Color.white;
        }
        else
        {
            background = Color.white;
            foreground = Color.black;
        }
        setHorizontalAlignment(JLabel.CENTER);
        setFont(new Font("Arial", Font.PLAIN, 20));
        unHighlight();
        setOpaque(true);

    }

    /**
    Constructs a SpaceLabel, designating the location of the space
    on the chessboard.
    @param r    row of the space
    @param c    column of the space
    */
    public SpaceLabel(int r, int c)
    {
        this("",r,c);
    }

    /**
    Returns the row of the label
    @return
    */
    public int getR()
    {
        return r;
    }

    /**
    Returns the column of the label
    @return
    */
    public int getC()
    {
        return c;
    }

    /**
    Highlights the SpaceLabel by changing the background color
    */
    public void highlight()
    {
        setBackground(backgroundHighlight);
        setForeground(foregroundHighlight);
    }

    /**
    Sets the background back to the normal, chessboard color.
    */
    public void unHighlight()
    {
        setBackground(background);
        setForeground(foreground);
    }
}
