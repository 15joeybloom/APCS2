package exp;


import java.awt.Desktop;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 Shows a Graphical User Interface for parsing mathematical expressions.
 <p>
 @author Joey Bloom
 */
public class ExpressionParserGUI extends JFrame
{
    private JLabel expression;
    private JTextField expressionField;
    private JButton evaluateButton;
    private JTextField result;

    private JSeparator separator;

    private JLabel operators;
    private JLabel symbol;
    private JTextField symbolField;
    private JList<String> operatorList;
    private DefaultListModel<String> operatorListModel;
    private JLabel noArguments;
    private JSpinner noArgumentsSpinner;
    private JLabel syntax;
    private JTextField syntaxField;
    private JButton defineButton;

    private JPopupMenu popupMenu;
    private JRadioButtonMenuItem prefixButton;
    private JRadioButtonMenuItem postfixButton;
    private JMenuItem prefixPostfixHelp;
    private JMenuItem expressionParserHelp;
    private MouseListener popupListener;

    public ExpressionParserGUI()
    {
        setSize(610, 377); //fibonacci numbers
        setTitle("Expression Parser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setUpComponents();
        setUpLayout();

        setVisible(true);

        JOptionPane.showMessageDialog(
            this,
            "Right Click to change between prefix and postfix notations,"
            + "\nand for help with prefix and postfix notations");
    }

    private void setUpComponents()
    {
        expression = new JLabel("Expression:");

        expressionField = new JTextField(15);

        evaluateButton = new JButton(" = ");
        evaluateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String exp = expressionField.getText();
                result.setText(
                    Double.toString(
                        Operator.prefix
                        ? evaluatePrefix(exp)
                        : evaluatePostfix(exp)));
            }
        });

        result = new JTextField(1);
        result.setEditable(false);

        separator = new JSeparator();

        operators = new JLabel("Operators");

        operatorListModel = new DefaultListModel<>();
        for(String str : Operator.symbolMap.keySet())
        {
            operatorListModel.addElement(str);
        }
        operatorList = new JList<>(operatorListModel);
        operatorList.setLayoutOrientation(JList.VERTICAL_WRAP);
        operatorList.setBorder(expressionField.getBorder());
        operatorList.setVisibleRowCount(0);

        symbol = new JLabel("Symbol:");
        symbolField = new JTextField("average", 0);

        noArguments = new JLabel("No. Arguments:");
        noArgumentsSpinner = new JSpinner(new SpinnerNumberModel(2, 0, 26, 1));//start,min,max,step

        syntax = new JLabel("Syntax:");
        syntaxField = new JTextField("/ + a b 2", 0);

        defineButton = new JButton("Define");
        defineButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                Operator.defineOperator(
                    symbolField.getText(),
                    (Integer) noArgumentsSpinner.getValue(),
                    syntaxField.getText()
                );
                operatorListModel.addElement(symbolField.getText());
            }
        });

        popupMenu = new JPopupMenu();
        prefixButton = new JRadioButtonMenuItem("Prefix Notation");
        prefixButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                Operator.prefix = true;
            }
        });
        postfixButton = new JRadioButtonMenuItem("Postfix Notation");
        postfixButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                Operator.prefix = false;
            }
        });
        prefixButton.doClick();
        ButtonGroup group = new ButtonGroup();
        group.add(prefixButton);
        group.add(postfixButton);
        popupMenu.add(prefixButton);
        popupMenu.add(postfixButton);

        prefixPostfixHelp = new JMenuItem("Help with Prefix and Postfix Notations");
        prefixPostfixHelp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                try
                {
                    openWebpage(new URL("http://www.cs.man.ac.uk/~pjj/cs2121/fix.html"));
                }
                catch(MalformedURLException ex)
                {
                }
            }

            /**
             This and the following method taken from
             <a href="http://bit.ly/1bY6tjf">http://bit.ly/1bY6tjf</a>
             <p>
             @param uri
             */
            public void openWebpage(URI uri)
            {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
                {
                    try
                    {
                        desktop.browse(uri);
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            public void openWebpage(URL url)
            {
                try
                {
                    openWebpage(url.toURI());
                }
                catch(URISyntaxException e)
                {
                    e.printStackTrace();
                }
            }
        });
        popupMenu.add(prefixPostfixHelp);

        expressionParserHelp = new JMenuItem("Help with this program");
        expressionParserHelp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if(Desktop.isDesktopSupported())
                {
                    try
                    {
                        Desktop.getDesktop().edit(new File("README.txt"));
                    }
                    catch(IOException ex){}
                }
                else
                {
                    JOptionPane.showMessageDialog(ExpressionParserGUI.this, "Desktop not supported");
                }
            }
        });
        popupMenu.add(expressionParserHelp);

        popupListener = new PopupListener();
        addMouseListener(popupListener);
    }

    private void setUpLayout()
    {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

//        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(expression, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(expressionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(evaluateButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(result, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                             GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
            .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                             GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
            .addGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(operators, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                     GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addComponent(operatorList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(symbol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(symbolField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                     GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(noArguments, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(noArgumentsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                     GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(syntax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(syntaxField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(defineButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );
        layout.setHorizontalGroup(
            layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addComponent(expression, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                 GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(expressionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                 GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(evaluateButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                 GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(result, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
            .addComponent(separator)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(operators, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(operatorList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                 GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(symbol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                         GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(symbolField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(noArguments, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                         GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(noArgumentsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(syntax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                         GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(syntaxField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(defineButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );
    }

    /**
     Overloaded method; calls evaluatePrefix(StringTokenizer)
     <p>
     @param expression
                       This is used to instantiate the StringTokenizer
                       that is passed to evaluatePrefix(StringTokenizer)
     @return the number that is the expression evaluated as a
             prefix notation mathematical expression
     */
    private static double evaluatePrefix(String expression)
    {
        return evaluatePrefix(new StringTokenizer(expression));
    }

    /**
     Evaluates the expression in prefix notation; each token
     in the tokenizer is a number or operator.
     <p>
     @param tokenizer tokens of the prefix expression
     @return the number that is obtained by evaluating the
             sequence of tokens as a prefix expression.
     */
    private static double evaluatePrefix(StringTokenizer tokenizer)
    {
        //get first token
        //if number
        //return number
        //if operator
        //return evaluate operator for evaluatePrefix of args
        String str = tokenizer.nextToken();
        if(str.matches(Operator.numberRegex))
            return Double.parseDouble(str);
        else
        {
            Operator operator = Operator.symbolMap.get(str);
            int numArgs = operator.getNumArgs();
            double[] args = new double[numArgs];
            for(int i = 0; i < numArgs; i++)
            {
                args[i] = evaluatePrefix(tokenizer);
            }
            return operator.eval(args);
        }
    }

    /**
     Evaluates the expression in postfix notation.
     <p>
     @param expression a well-formed postfix notation expression
     @return the number that is obtained by evaluating the sequence of tokens as
             a postfix expression
     */
    private static double evaluatePostfix(String expression)
    {
        Stack<Double> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while(tokenizer.hasMoreTokens())
        {
            String str = tokenizer.nextToken();
            if(str.matches(Operator.numberRegex))//if it's a number
            {
                stack.push(Double.parseDouble(str));
            }
            else
            {
                Operator operator = Operator.symbolMap.get(str);
                int numArgs = operator.getNumArgs();
                double[] args = new double[numArgs];
                for(int i = numArgs - 1; i >= 0; i--)
                //traverse backwards intentionally to preserve
                //the order of arguments for noncommutative operations,
                //such as subraction or division.
                {
                    args[i] = stack.pop();
                }
                stack.push(operator.eval(args));
            }
        }
        return stack.pop();
    }

    /**
     Copied from
     <a href="http://docs.oracle.com/javase/tutorial/uiswing/components/menu.html#popup">
     The Java Tutorials</a> with modifications
     */
    private class PopupListener extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            maybeShowPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e)
        {
            if(e.isPopupTrigger())
            {
                popupMenu.show(e.getComponent(),
                               e.getX(), e.getY());
            }
        }
    }

    public static void main(String[] args)
    {
        new ExpressionParserGUI();
    }
}
