package nl.evg.hourtracker.business;

import org.junit.Test;
import static org.junit.Assert.*;

public class TimeFormatterTest
{
	@Test public void seconds3661()
	{
		assertEquals(new TimeFormatter().format(3661),"1:01:01");
	}
	
	@Test public void seconds3600()
	{
		assertEquals(new TimeFormatter().format(3600),"1:00:00");
	}
	
	@Test public void seconds601()
	{
		assertEquals(new TimeFormatter().format(601),"0:10:01");
	}
	
	@Test public void seconds600()
	{
		assertEquals(new TimeFormatter().format(600),"0:10:00");
	}
	
	@Test public void seventyOneSeconds()
	{
		assertEquals(new TimeFormatter().format(71),"0:01:11");
	}
	
	@Test public void sixtyOneSeconds()
	{
		assertEquals(new TimeFormatter().format(61),"0:01:01");
	}

	@Test public void sixtySeconds()
	{
		assertEquals(new TimeFormatter().format(60),"0:01:00");
	}

	@Test public void eightSeconds()
	{
		assertEquals(new TimeFormatter().format(8),"0:00:08");
	}

	@Test public void twelveSeconds()
	{
		assertEquals(new TimeFormatter().format(12),"0:00:12");
	}

	@Test public void zeroSeconds()
	{
		assertEquals(new TimeFormatter().format(0),"0:00:00");
	}
}
