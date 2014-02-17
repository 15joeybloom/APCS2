package quadratic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Assignment #1
 *
 * @author Joey Bloom
 */
public class QuadraticFrame extends JFrame
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                new QuadraticFrame();
            }
        });
    }

    private JPanel northPanel;
        private JTextField aField;
        private JTextField bField;
        private JTextField cField;

    private JPanel southPanel;
        private JTextField output1;
        private JTextField output2;

    public QuadraticFrame()
    {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,100);
        setTitle("Quadratic");

        northPanel = new JPanel();
        add(northPanel,BorderLayout.NORTH);

        aField = new JTextField("1",10);
        aField.addActionListener(new MyListener());
        northPanel.add(aField);
        northPanel.add(new JLabel("x\u00b2   +"));

        bField = new JTextField("-1",10);
        bField.addActionListener(new MyListener());
        northPanel.add(bField);
        northPanel.add(new JLabel("x   +"));

        cField = new JTextField("-6",10);
        cField.addActionListener(new MyListener());
        northPanel.add(cField);
        northPanel.add(new JLabel("= 0"));

        southPanel = new JPanel();
        add(southPanel,BorderLayout.SOUTH);

        southPanel.add(new JLabel("x  ="));
        output1 = new JTextField(10);
        output1.setEditable(false);
        southPanel.add(output1);

        southPanel.add(new JLabel("  x  ="));
        output2 = new JTextField(10);
        output2.setEditable(false);
        southPanel.add(output2);

        setVisible(true);
    }

    public class MyListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            double a,b,c;
            try
            {
                a = Double.parseDouble(aField.getText());
                b = Double.parseDouble(bField.getText());
                c = Double.parseDouble(cField.getText());
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(QuadraticFrame.this,"NaN\n"+ex);
                return;
            }
            double part = Math.sqrt(b*b - 4*a*c);
            String sol1 = String.valueOf((-b + part) / (2*a));
            String sol2 = String.valueOf((-b - part) / (2*a));
            
            //truncate decimal
            sol1 = sol1.substring(0,Math.min(sol1.indexOf(".") + 5,sol1.length()));
            sol2 = sol2.substring(0,Math.min(sol2.indexOf(".") + 5, sol2.length()));
            
            output1.setText(sol1);
            output2.setText(sol2);
        }
    }
}
