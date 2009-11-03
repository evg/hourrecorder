package nl.evg.hourtracker.business;

import java.util.ArrayList;
import java.util.List;

public class Task
{
	public Task(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
	
	public boolean equals(Object other)
	{
		if (other==null)
			return false;
		if (!(other instanceof Task))
			return false;
		
		Task otherTask = (Task)other;
		return otherTask.name.equals(name);
	}
	
	public int hashCode()
	{
		return name.hashCode();
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getSecondsTaken()
	{
		int result = 0;
		for(TimeInterval interval: intervals)
			result += interval.getSecondsTaken();
		if (runningInterval!=null)
			result += runningInterval.getSecondsTakenUpTilNow();
		return result;
	}
	
	public void startNow()
	{
		Timestamp now = Timestamp.getNow();
		startAt(now);
		runningInterval = new TimeInterval(now,now);
	}
	
	public void startAt(Timestamp time)
	{
		start = time;
	}
	
	public void stopNow()
	{
		stopAt(Timestamp.getNow());
		runningInterval = null;
	}
	
	public void stopAt(Timestamp time)
	{
		intervals.add(new TimeInterval(start,time));
	}
	
	private TimeInterval runningInterval;
	private String name;
	private Timestamp start;
	private List<TimeInterval> intervals = new ArrayList<TimeInterval>();
}
