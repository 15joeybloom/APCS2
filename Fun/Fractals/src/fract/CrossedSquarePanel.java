package fract;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * @author Joey
 *
 */
public class CrossedSquarePanel extends Fractal
{    
    @Override
    public void renderFractal()
    {
        super.renderFractal();
        Graphics2D g2 = (Graphics2D) getGraphics();
        g2.drawRect(0, 0, getWidth(), getHeight());
        crossedSquare(g2,0,0,getWidth(),getHeight(),10);
    }

    private void crossedSquare(Graphics2D g, int x, int y, int w, int h, int depth)
    {
        if(depth == 0)
        {
            return;
        }

        int j = w/2;
        int k = h/2;
        int l = x + j;
        g.drawLine(l, y, l, y + h);
        int m = y + k;
        g.drawLine(x, m, x + w, m);

        int d = depth-1;
        crossedSquare(g,x,y,j,k,d);//topleft
        crossedSquare(g,l,m,j,k,d);//bottomright
        crossedSquare(g,x,m,j,k,d);//bottomleft
    }
}