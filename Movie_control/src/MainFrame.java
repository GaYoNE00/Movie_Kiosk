import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public MainFrame(String title) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());		
		
		
		setVisible(true);
	}
	public void left() {
		setLayout(new GridLayout());

	}
	public void center() {
		
	}

}
