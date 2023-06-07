package source;

import javax.swing.*;

public class Frame extends JFrame{
	
	final int WIDTH = 1300;
	final int HEIGHT = 1000;
	
	Frame(){
		setLayout(null);
		setResizable(false);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Panel());
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Frame();
	}
}
