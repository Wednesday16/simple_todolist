package source;

import javax.swing.*;
import java.util.Stack;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {
	JTextField input;
	Graphics g;
	
	final int WIDTH = 1300;
	final int HEIGHT = 1000;
	final int UNIT_SIZE = 20;
	
	final int HORIZONTAL_CENTER = WIDTH/2;
	final int VERTICAL_CENTER = HEIGHT/2;
	final int MAX_TEXT_LENGTH = 50;
	
	// Size constraints
	final int LABEL_WIDTH = 15;
	final int LABEL_HEIGHT = 1;
	final int INPUT_FIELD_WIDTH = 30;
	final int INPUT_FIELD_HEIGHT = 2;
	final int SUBMIT_BUTTON_WIDTH = 4;
	final int SUBMIT_BUTTON_HEIGHT = 2;

	// Offset constraints
	final int LABEL_VERTICAL_OFFSET = 5;
	final int INPUT_FIELD_VERTICAL_OFFSET = 10;
	final int SUBMIT_BUTTON_VERTICAL_OFFSET = 10;
	final int OFFSET_VALUE = 230;  // vertical offset value of the first node
	final int NODE_WIDTH = 30;
	final int NODE_HEIGHT = 2;
	final int BUTTON_WIDTH = 2;
	final int BUTTON_HEIGHT = 2;
	final int INTERLINING = 3;
	
	Stack nodes = new Stack();
	Stack deleteButtons = new Stack();
	int nodeNum = 0;

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint();
	}
	
	Panel(){
		setLayout(null);
		setSize(1300, 1000);
		JLabel l1 = new JLabel("Input the actions you want to add below:");
		input = new JTextField();
		JButton submit = new JButton("Create");
		
		l1.setBounds((int)(HORIZONTAL_CENTER - UNIT_SIZE*(LABEL_WIDTH/2)), UNIT_SIZE*LABEL_VERTICAL_OFFSET, UNIT_SIZE*LABEL_WIDTH, UNIT_SIZE*LABEL_HEIGHT);
		input.setBounds((int)(HORIZONTAL_CENTER - UNIT_SIZE*(INPUT_FIELD_WIDTH/2)), UNIT_SIZE*INPUT_FIELD_VERTICAL_OFFSET, UNIT_SIZE*INPUT_FIELD_WIDTH, UNIT_SIZE*INPUT_FIELD_HEIGHT);
		submit.setBounds((int)(HORIZONTAL_CENTER + UNIT_SIZE*(INPUT_FIELD_WIDTH/2+1)), UNIT_SIZE*SUBMIT_BUTTON_VERTICAL_OFFSET, UNIT_SIZE*SUBMIT_BUTTON_WIDTH, UNIT_SIZE*SUBMIT_BUTTON_HEIGHT);
		submit.addActionListener(this);
		
		add(l1); add(input); add(submit);
	}
	
	public void addNode(String nodeText, Graphics g) {
		
		if (!(nodeNum >= 10) && !(nodeText.length() > MAX_TEXT_LENGTH)) {
			input.setText(null);
			Node currentNode = new Node(nodeText, this, UNIT_SIZE, nodeNum);
			JButton deleteButton = new JButton(new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					deleteNode();
				}
			});
			nodes.push(currentNode);
			deleteButtons.push(deleteButton);
			currentNode.setText(nodeText);
			nodeNum++;
			currentNode.setBounds((int)(HORIZONTAL_CENTER - UNIT_SIZE*(NODE_WIDTH/2)), UNIT_SIZE*INTERLINING*(nodeNum+1)+OFFSET_VALUE, UNIT_SIZE*NODE_WIDTH, UNIT_SIZE*NODE_HEIGHT);
			deleteButton.setBounds((int)(HORIZONTAL_CENTER + UNIT_SIZE*(NODE_WIDTH/2+1)), UNIT_SIZE*INTERLINING*(nodeNum+1)+OFFSET_VALUE, UNIT_SIZE*BUTTON_WIDTH, UNIT_SIZE*BUTTON_HEIGHT);
			currentNode.setBorder(BorderFactory.createLineBorder(Color.black));
			add(currentNode); add(deleteButton);
		}
		
		else if (nodeText.length() > MAX_TEXT_LENGTH) {
			JOptionPane.showMessageDialog(this, 
					"The input text is too big\n"
					+ "Try to split or reduce it");
		}
			
		else {
			JOptionPane.showMessageDialog(this, 
					"You already have too many nodes\n"
					+ "Please remove some to continue");
		}
	}		

	public void deleteNode() {
		if (!(nodeNum <= 0)) {
			Node currentNode = (Node) nodes.pop();
			JButton currentButton = (JButton) deleteButtons.pop();
			remove(currentNode);
			remove(currentButton);
			repaint();
			nodeNum--;
		}

		else {
			JOptionPane.showMessageDialog(this, "You don't have nodes to delete");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nodeText = input.getText();
		
		addNode(nodeText, g);
	}
}
