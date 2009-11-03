package nl.evg.hourtracker.guitda;

import java.awt.Dimension;

import javax.swing.JFrame;


public class MainFrame extends JFrame {
	public void run()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(400, 300));
		setVisible(true);
		pack();

	}
	public static void main(String[] args)
	{
		new MainFrame().run();
	}
}
