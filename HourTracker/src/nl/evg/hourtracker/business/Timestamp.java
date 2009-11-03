package nl.evg.hourtracker.business;

import java.util.Calendar;

public class Timestamp
{
	public static Timestamp getNow()
	{
		Calendar now = Calendar.getInstance();
		return new Timestamp(now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),now.get(Calendar.SECOND));
	}
	
	public Timestamp(int hours, int minutes)
	{
		this(hours,minutes,0);
	}
	
	public Timestamp(int hours, int minutes, int seconds)
	{
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public int minus(Timestamp other)
	{
		return this.inSeconds() - other.inSeconds();
	}

	private int inSeconds()
	{
		return hours*60*60+minutes*60+seconds;
	}

	private int hours;
	private int minutes;
	private int seconds;
}
