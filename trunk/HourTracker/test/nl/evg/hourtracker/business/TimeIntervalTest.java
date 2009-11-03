package nl.evg.hourtracker.business;

import org.junit.Test;
import static org.junit.Assert.*;

import nl.evg.hourtracker.business.TimeInterval;
import nl.evg.hourtracker.business.Timestamp;
import nl.evg.hourtracker.helper.Helper;



public class TimeIntervalTest
{
	@Test public void equalTimestamps()
	{
		Timestamp quarterPastEight = new Timestamp(8,15);
		TimeInterval interval = new TimeInterval(quarterPastEight,quarterPastEight);
		assertEquals(interval.getSecondsTaken(),0);
	}
	
	@Test public void oneHourLater()
	{
		Timestamp quarterPastEight = new Timestamp(8,15);
		Timestamp quarterPastNine = new Timestamp(9,15);
		TimeInterval interval = new TimeInterval(quarterPastEight,quarterPastNine);
		assertEquals(interval.getSecondsTaken(),60*60);
	}
	
	@Test public void fifteenSecondsLater()
	{
		Timestamp quarterPastEight = new Timestamp(8,15);
		Timestamp quarterPastEightAndFiveSeconds = new Timestamp(8,15,5);
		TimeInterval interval = new TimeInterval(quarterPastEight,quarterPastEightAndFiveSeconds);
		assertEquals(interval.getSecondsTaken(),5);
	}
	
	@Test public void timeUpTilNow()
	{
		TimeInterval interval = new TimeInterval(Timestamp.getNow(),Timestamp.getNow());
		Helper.pause(3);
		assertEquals(interval.getSecondsTakenUpTilNow(),3);
		
	}
}
