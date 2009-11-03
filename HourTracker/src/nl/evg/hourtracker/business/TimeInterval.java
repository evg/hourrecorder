package nl.evg.hourtracker.business;

public class TimeInterval
{
	public TimeInterval(Timestamp time1, Timestamp time2)
	{
		this.time1 = time1;
		this.time2 = time2;
	}
	
	public int getSecondsTaken()
	{
		return time2.minus(time1);
	}
	
	public int getSecondsTakenUpTilNow()
	{
		return Timestamp.getNow().minus(time1);
	}
	
	private Timestamp time1;
	private Timestamp time2;
}
