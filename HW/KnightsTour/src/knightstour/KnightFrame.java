package knightstour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.swing.*;

/**
Assignment #9
<p/>
GUI shows a chessboard on which one attempts the knight's tour.
@author Joey Bloom
 */
public class KnightFrame extends JFrame
{
    private JPanel chessBoard;
    private SpaceLabel[][] grid;
    private JPanel buttonPanel;
    private JButton step;
    private JButton finish;
    private JButton clear;
    private JButton undo;

    private JMenuBar menubar;
    private JMenu stepMode;
    private ButtonGroup buttonGroup;
    private JRadioButtonMenuItem randomMode;
    private JRadioButtonMenuItem accessibilityMode;

    /**
     Constructs a new KnightFrame.
     */
    public KnightFrame()
    {
        setSize(500, 500);
        setTitle("Knight's Tour");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        layout.setHgap(0);
        layout.setVgap(0);
        setLayout(layout);

        setUpChessBoard();
        setUpGrid();
        setUpButtons();
        setUpMenuBar();

        setVisible(true);
        clear.doClick();
    }

    /**
     Sets up the JPanel that holds the spaces on the board.
     */
    private void setUpChessBoard()
    {
        chessBoard = new JPanel();
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setOpaque(false);

        add(chessBoard, BorderLayout.CENTER);
    }

    /**
     Sets up the grid of SpaceLabels.
     */
    private void setUpGrid()
    {
        grid = new SpaceLabel[8][];
        for(int i = 0; i < 8; i++)
        {
            grid[i] = new SpaceLabel[8];
            for(int j = 0; j < 8; j++)
            {
                grid[i][j] = new SpaceLabel(i, j);
                grid[i][j].addMouseListener(new SpaceListener());
                chessBoard.add(grid[i][j]);
            }
        }
    }

    /**
     Sets up the buttons on the bottom
     */
    private void setUpButtons()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        add(buttonPanel, BorderLayout.SOUTH);

        Color red = new Color(194, 0, 0);
        finish = new JButton("Finish");
        finish.setBackground(red);
        finish.setForeground(Color.yellow);
        finish.addActionListener(new FinishListener());
        buttonPanel.add(finish);

        step = new JButton("Step");
        step.setBackground(red);
        step.setForeground(Color.yellow);
        step.addActionListener(new StepListener());
        buttonPanel.add(step);

        clear = new JButton("Clear");
        clear.setBackground(red);
        clear.setForeground(Color.yellow);
        clear.addActionListener(new ClearListener());
        buttonPanel.add(clear);

        undo = new JButton("Undo");
        undo.setBackground(red);
        undo.setForeground(Color.yellow);
        undo.addActionListener(new UndoListener());
        buttonPanel.add(undo);
    }

    /**
    Sets up the menu bar at the top of the frame
    */
    private void setUpMenuBar()
    {
        menubar = new JMenuBar();
        setJMenuBar(menubar);

        stepMode = new JMenu("Step Mode");
        menubar.add(stepMode);

        buttonGroup = new ButtonGroup();

        randomMode = new JRadioButtonMenuItem("Random");
        randomMode.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                mode = RANDOM_MODE;
            }
        });
        stepMode.add(randomMode);
        buttonGroup.add(randomMode);

        accessibilityMode = new JRadioButtonMenuItem("Accessibility");
        accessibilityMode.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                mode = ACCESSIBILITY_MODE;
            }
        });
        stepMode.add(accessibilityMode);
        buttonGroup.add(accessibilityMode);

        randomMode.doClick();
    }

    private Random rand = new Random();
    private static final int[] horz =
    {
        -2, -1, 1, 2, 2, 1, -1, -2
    };
    private static final int[] vert =
    {
        1, 2, 2, 1, -1, -2, -2, -1
    };
    private int[][] access = setUpAccess();
    private SpaceLabel knightSpace;
    private int counter;
    private boolean done;
    private ArrayList<SpaceLabel> nextChoices = new ArrayList<>();
    private Stack<SpaceLabel> path = new Stack<>();

        private static final byte RANDOM_MODE = 0;
        private static final byte ACCESSIBILITY_MODE = 1;
    private byte mode = RANDOM_MODE;

    /**
    Initializes the field <code>access</code>
    */
    private int[][] setUpAccess()
    {
        int[][] returnMe = new int[8][];
        try(BufferedReader in = new BufferedReader(new InputStreamReader(KnightFrame.class.getResourceAsStream("access.txt"))))
        {
            for(int i = 0; i < 8; i++)
            {
                String str = in.readLine();
                StringTokenizer tokens = new StringTokenizer(str);
                returnMe[i] = new int[8];
                for(int j = 0; j < 8; j++)
                {
                    returnMe[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,ex);
            System.exit(1);
        }
        return returnMe;
    }

    /**
     Sets the text of all of the spaces to "0"
     Resets counter
     Puts the knight back at 1,1
     */
    private void clearBoard()
    {
        for(SpaceLabel[] xs : grid)
        {
            for(SpaceLabel x : xs)
            {
                x.setText("");
                x.unHighlight();
            }
        }
        done = false;
        knightSpace = grid[0][0];
        counter = 0;
        path.clear();
        access = setUpAccess();
        knightSpace.setText(Integer.toString(++counter));
        knightSpace.setForeground(Color.blue);
        assembleChoices();

        //TODO omit the following:
        printAccessibility();
    }

    /**
     Fills nextChoices with available spaces based
     on the current location of the knight as stored
     in knightSpace
     */
    private void assembleChoices()
    {
        nextChoices = new ArrayList<>();
        for(int i = 0; i < 8; i++)
        {
            try
            {
                SpaceLabel x = grid[knightSpace.getR() + vert[i]][knightSpace.getC() + horz[i]];
                if(x.getText().equals(""))
                {
                    nextChoices.add(x);
                    x.highlight();
                }
            }
            catch(ArrayIndexOutOfBoundsException ex){}
        }
    }

    /**
    Increments the accessibility for each SpaceLabel
    in nextChoices. This method is called shortly before
    undoing a move, to restore the accessibility values
    to what they were before the move.
    */
    private void incrementAccessibility()
    {
        for(SpaceLabel x : nextChoices)
        {
            access[x.getR()][x.getC()]++;
        }
    }

    /**
    Decrements the accessibility for each SpaceLabel
    in nextChoices. This method is called shortly after a move
    is made and the choices are assembled for that
    move.
    */
    private void decrementAccessibility()
    {
        for(SpaceLabel x : nextChoices)
            {
                access[x.getR()][x.getC()]--;
            }
    }

    private class UndoListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(path.isEmpty()) return;
            done = false;

            //unhighlight choices
            for(SpaceLabel x : nextChoices)
            {
                x.unHighlight();
            }

            //make current space blank
            knightSpace.setText(""); counter--;
            knightSpace.unHighlight(); //restores the space to black and white

            //increment accessiblity for each of the choices
            //from the current space (the space before undo)
            incrementAccessibility();

            //move back
            knightSpace = path.pop();
            knightSpace.setForeground(Color.blue);

            //assemble and highlight choices
            assembleChoices();
            for(SpaceLabel x : nextChoices)
            {
                x.highlight();
            }
        }
    }

    private class SpaceListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if(nextChoices.contains((SpaceLabel)e.getSource()))
            {
                //TODO omit the following:
                System.out.println("Before: ");
                printAccessibility();

                //old space is the space we move from
                //new space is the space we move to

                //unhighlight the nextChoices from the old space
                for(SpaceLabel x : nextChoices)
                {
                    x.unHighlight();
                }

                //restore old space's text color
                knightSpace.setForeground((knightSpace.getR() + knightSpace.getC()) % 2 == 0 ? Color.white : Color.black);

                //decrement the accessibility for the nextChoices
                //of the old space.
                decrementAccessibility();

                //move to the space which was clicked, which is
                //the new space.
                path.push(knightSpace);
                knightSpace = (SpaceLabel)e.getSource();
                knightSpace.setText(Integer.toString(++counter));

                //make the text blue of the new space
                knightSpace.setForeground(Color.blue);

                //assemble the available spaces to move to for next move
                assembleChoices();

                //highlight the choices for next move
                for(SpaceLabel x : nextChoices)
                {
                    x.highlight();
                }

                //TODO omit the following:
                System.out.println("After: ");
                printAccessibility();
            }
        }
        @Override public void mousePressed(MouseEvent e){}
        @Override public void mouseReleased(MouseEvent e){}
        @Override public void mouseEntered(MouseEvent e){}
        @Override public void mouseExited(MouseEvent e){}
    }

    private class ClearListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            clearBoard();
        }
    }

    private class StepListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(done)
            {
                JOptionPane.showMessageDialog(KnightFrame.this, "Touched " + counter + " spaces");
                return;
            }

            //old space is the space we move from
            //new space is the space we move to

            //unhighlight the nextChoices from old space
            for(SpaceLabel x : nextChoices)
            {
                x.unHighlight();
            }

            //if no available moves, show results.
            if(nextChoices.isEmpty())
            {
                JOptionPane.showMessageDialog(KnightFrame.this, "Touched " + counter + " spaces");
                done = true;
                return;
            }

            //restore old space's text color
            knightSpace.setForeground((knightSpace.getR() + knightSpace.getC()) % 2 == 0 ? Color.white : Color.black);

            //decrement the accessibility for the nextChoices
            //of the old space.
            decrementAccessibility();

            //since there are available spaces, choose
            //one and move to it. This space
            //is now the new space
            path.push(knightSpace);
            switch(mode)
            {
                case RANDOM_MODE:
                    knightSpace = nextChoices.get(rand.nextInt(nextChoices.size()));
                    break;
                case ACCESSIBILITY_MODE:
                    //find the minimum accessibility value
                    SpaceLabel minLabel = nextChoices.get(0);
                    int minAccessibility = access[minLabel.getR()][minLabel.getC()];
                    for(int i = 1; i < nextChoices.size(); i++)
                    {
                        SpaceLabel x = nextChoices.get(i);
                        int xAccessibility = access[x.getR()][x.getC()];
                        if(xAccessibility < minAccessibility)
                        {
                            minAccessibility = xAccessibility;
                            minLabel = x;
                        }
                    }
                    knightSpace = minLabel;
                    break;
            }
            knightSpace.setText(Integer.toString(++counter));

            //make the text blue of the new space
            knightSpace.setForeground(Color.blue);

            //assemble the available spaces to move to for next move
            assembleChoices();

            //highlight the available spaces for next move
            for(SpaceLabel x : nextChoices)
            {
                x.highlight();
            }

            //TODO omit the following:
            printAccessibility();
        }
    }

    private class FinishListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //using a SwingWorker allows the clicking of step to run in
            //the background, so that the user can actually see
            //the clicking occur and the user can see
            //the knight moving.
            new SwingWorker<Void, Void>()
            {
                @Override
                public Void doInBackground() throws InterruptedException
                {
                    do
                    {
                        //finish the tour by clicking step repeatedly
                        step.doClick();
                        try
                        {
                            //slow down the clicking of step
                            Thread.sleep(200);
                        }
                        catch(InterruptedException ex){}
                    }
                    while(!done);
                    return null;
                }
            }.execute();
        }
    }

    public static void main(String[] args) throws InterruptedException, InvocationTargetException
    {
        SwingUtilities.invokeAndWait(new Runnable()
        {
            @Override
            public void run()
            {
                KnightFrame frame = new KnightFrame();
            }
        });
    }

    //testing method
    private void printAccessibility()
    {
        for(int[] xs : access)
        {
            for(int x : xs)
            {
                System.out.print(x + "  ");
            }
            System.out.println();
        }
    }
}
