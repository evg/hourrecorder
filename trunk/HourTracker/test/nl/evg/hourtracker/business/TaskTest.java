package nl.evg.hourtracker.business;

import nl.evg.hourtracker.business.Task;
import nl.evg.hourtracker.business.Timestamp;

import org.junit.Test;
import static org.junit.Assert.*;

public class TaskTest
{
	@Test public void equals()
	{
		Task t1 = new Task("My task");
		Task t2 = new Task("My task");
		Task t3 = new Task("Your task");
		
		assertEquals(t1,t1);
		assertEquals(t1,t2);
		assertFalse(t1.equals(t3));
		assertFalse(t1.equals(null));
	}
	
	@Test public void sumIntervals()
	{
		Task task = new Task("My task");
		task.startAt(new Timestamp(8,15));
		task.stopAt(new Timestamp(8,15,5));
		
		task.startAt(new Timestamp(9,0));
		task.stopAt(new Timestamp(9,0,7));
		
		assertEquals(task.getSecondsTaken(),12);
	}
}
