package source;

import javax.swing.*;

public class Node extends JLabel {
	JButton deleteButton;
	Panel panel;

	Node(String text, Panel panel, final int UNIT_SIZE, int nodeNum){
		setLayout(null);
		setText(text);
	}
}
