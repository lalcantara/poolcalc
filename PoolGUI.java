/******************************************************************************
 * Program Name: Pool GUI
 * Programmers Name: Lamburt Alcantara
 * Program Description: Pool GUI for calculating volume.
 ******************************************************************************/

import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Container;
import java.awt.*;

public class PoolGUI extends JFrame
{
    private JFrame mainFrame;
    private JButton calculateButton;
    private JButton exitButton;
    private JTextField num1Field;
    private JTextField num2Field;
    private JTextField num3Field;
    private JTextField sumField;
    private JLabel num1Label;
    private JLabel num2Label;
    private JLabel num3Label;
    private JLabel sumLabel;
    
    public PoolGUI()  // a constructor
    {
        mainFrame = new JFrame("Swimming Pool Volume Calculator");
        
        // create all components
        calculateButton = new JButton("Calculate volume");
        exitButton = new JButton("Exit");
        num1Label = new JLabel("Enter the length of the swimming pool:");
        num2Label = new JLabel("Enter the width of the swimming pool:");
        num3Label = new JLabel("Enter the average depth of the swimming pool:");
        sumLabel = new JLabel("The swimming pool volume is =");
        num1Field = new JTextField(5);
        num2Field = new JTextField(5);
        num3Field = new JTextField(5);
        sumField = new JTextField(5);
        
        // get the content pane
        Container c = mainFrame.getContentPane();
        // set the layout manager
        c.setLayout(new FlowLayout());
        
        // add the components to the ContentPane
        c.add(num1Label);
        c.add(num1Field);
        c.add(num2Label);
        c.add(num2Field);
        c.add(num3Label);
        c.add(num3Field);
        c.add(sumLabel);
        c.add(sumField);
        c.add(calculateButton);
        c.add(exitButton);
        
        // create accelerator keys
        calculateButton.setMnemonic('C');
        exitButton.setMnemonic('x');
        
        mainFrame.setSize(350, 200);
        
        // define and register window event handler
        mainFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        
        // create and register the button event handlers
        // instantiate a handler
        CalculateButtonHandler chandler = new CalculateButtonHandler();
        calculateButton.addActionListener(chandler);  // register the handler
        
        ExitButtonHandler ehandler = new ExitButtonHandler();  // instantiate a handler
        exitButton.addActionListener(ehandler);  // register the handler
        
        // create and register the new focus handler
        FocusHandler fhandler = new FocusHandler();
        num1Field.addFocusListener(fhandler);
        num2Field.addFocusListener(fhandler);
        num3Field.addFocusListener(fhandler);
        sumField.addFocusListener(fhandler);
        
        mainFrame.show();
    }
    
    // inner focus handling class
    class FocusHandler implements FocusListener
    {
        public void focusGained(FocusEvent e)
        {
            if(e.getSource() == num1Field)
            {
                num1Field.setText("");  // blank imput textfield
                sumField.setText("");  // blank textfield
            }
            else if(e.getSource() == num2Field)
            {
                num2Field.setText("");  // blank imput textfield
                sumField.setText("");  // blank textfield
            }
            else if(e.getSource() == num3Field)
            {
                num3Field.setText("");
                sumField.setText("");
            }
            else if(e.getSource() == sumField)
            {
                sumField.setNextFocusableComponent(num1Field);
                num1Field.grabFocus();
            }
        }
        public void focusLost(FocusEvent e)
        {
            if(e.getSource() == num1Field)
            {
                num1Field.setNextFocusableComponent(num2Field);
            }
            if(e.getSource() == num2Field)
            {
                num2Field.setNextFocusableComponent(num3Field);
            }
            if(e.getSource() == num3Field)
            {
                num3Field.setNextFocusableComponent(calculateButton);
            }
        }
    }  // end of focus listener class
    
    // new inner class for the command buttons
    class CalculateButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            DecimalFormat num = new DecimalFormat(",###.##");
            String instring;
            double invalue1, invalue2, invalue3, outvalue;
            
            sumField.setEnabled(true);  // enable area text field
            instring = num1Field.getText();  // read the input value
            // prevent entry of an empty string
            if (instring.equals(""))
            {
                instring = "0";
                num1Field.setText("0");
            }
            invalue1 = Double.parseDouble(instring);  // convert to a double
            instring = num2Field.getText();  // read the input value
            // prevent entry of an empty string
            if (instring.equals("0"))
            {
                instring = "0";
                num2Field.setText("0");
            }
            invalue2 = Double.parseDouble(instring);  // convert to a double
            instring = num3Field.getText();  // read the input value
            // prevent entry of an empty string
            if (instring.equals("0"))
            {
                instring = "0";
                num3Field.setText("0");
            }
            invalue3 = Double.parseDouble(instring);  // convert to a double
            outvalue = invalue1 * invalue2 * invalue3;
            sumField.setText(num.format(outvalue));
        }
    }
    
    class ExitButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    public static void main(String args[])
    {
        new PoolGUI();  // instantiate a GUI object
    }
}  // end of class