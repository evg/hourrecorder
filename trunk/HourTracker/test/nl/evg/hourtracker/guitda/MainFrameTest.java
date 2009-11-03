package nl.evg.hourtracker.guitda;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainFrameTest {

	@Test public void mainFrameIsVisible()
	{
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
		assertTrue(mainFrame.isVisible());
	}

	@Test public void mainFrameRightSize()
	{
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
		assertEquals(new Dimension(400,300),mainFrame.getPreferredSize());
	}
	
	@Test public void mainFrameExitsOnClose()
	{
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
		assertEquals(JFrame.EXIT_ON_CLOSE, mainFrame.getDefaultCloseOperation());
	}
	
	@Test public void mainFrameHasTaskList()
	{
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
		assertEquals(0,mainFrame.getComponentCount());
	}
}
