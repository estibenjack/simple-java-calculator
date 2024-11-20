import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

  // declare ui components
	JFrame frame;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[9];
	JButton addButton, subButton, mulButton, divButton;
	JButton decButton, equButton, delButton, clrButton, negButton;
	JPanel panel;

  // font for buttons and textfield
	Font myFont = new Font("Arial", Font.BOLD, 30);

  // vars for storing numbers and operators
	double num1 = 0, num2 = 0, result = 0;
	char operator;

  // constructor to set up the calculator ui and event listeners
	Calculator() {

    // set up main frame
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setLayout(null);

    // create the textfield
		textfield = new JTextField();
		textfield.setBounds(50, 25, 300, 50);
		textfield.setFont(myFont);
		textfield.setEditable(false);

    // initialise operator buttons
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Del");
		clrButton = new JButton("AC");
		negButton = new JButton("(-)");

    // assign operator buttons to function buttons array
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;

    // set up event listeners and appearance for function buttons
		for (int i = 0; i < 9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}

    // initialise number buttons (0-9)
		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}

    // set positions of last three function buttons (negative number, delete, clear)
		negButton.setBounds(50, 430, 100, 50);
		delButton.setBounds(150, 430, 100, 50);
		clrButton.setBounds(250, 430, 100, 50);

    // create panel that holds number buttons in a grid layout
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 10, 10));

    // add number buttons and operators to panel
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);

    // add panel and buttons to frame
		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textfield);

    // make frame visible
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		new Calculator();

	}

  // handle button clicks
	@Override
	public void actionPerformed(ActionEvent e) {

    // handle button clicks (0-9)
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}

    // handle decimal button click
		if (e.getSource() == decButton) {
			if (!textfield.getText().contains(".")) {
				textfield.setText(textfield.getText().concat("."));
			}
		}

    // handle arithmetic operations
		if (e.getSource() == addButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '+';
			textfield.setText("");
		}
		if (e.getSource() == subButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '-';
			textfield.setText("");
		}
		if (e.getSource() == mulButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '*';
			textfield.setText("");
		}
		if (e.getSource() == divButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '/';
			textfield.setText("");
		}
    
    // handle equals button (perform calculations)
		if (e.getSource() == equButton) {
			num2 = Double.parseDouble(textfield.getText());
			switch (operator) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				if (num2 == 0) {
					textfield.setText("Error: Div by 0");
					return;
				} else {
					result = num1 / num2;
				}
				break;
			}
      
      // display result 
			if (result % 1 == 0) {
				textfield.setText(String.format("%d", (int) result));
			} else {
				textfield.setText(String.valueOf(result));
			}
			num1 = result;
		}

    // handle clear button click (clear textfield)
		if (e.getSource() == clrButton) {
			textfield.setText("");
		}

    //handle delete button click (remove last char)
		if (e.getSource() == delButton) {
			String string = textfield.getText();
			textfield.setText("");
			for (int i = 0; i < string.length() - 1; i++) {
				textfield.setText(textfield.getText() + string.charAt(i));
			}
		}

    // handle negative number button click (change sign of the number)
		if (e.getSource() == negButton) {
			double temp = Double.parseDouble(textfield.getText());
			temp *= -1;
			textfield.setText(String.valueOf(temp));
		}
	}

}
