package nl.evg.hourtracker.business;

public class TimeFormatter
{

	public String format(int seconds)
	{
		int hours = seconds/3600;
		int secondsRemaining = seconds - 3600*hours;
		int minutes = secondsRemaining/60;
		secondsRemaining = secondsRemaining - 60*minutes;

		String secondsPrint = ""+secondsRemaining;
		String minutesPrint = ""+minutes;
		if (secondsRemaining<10)
			secondsPrint = "0"+secondsRemaining;
		if (minutes<10)
			minutesPrint = "0"+minutes;
		return hours+":"+minutesPrint+":"+secondsPrint;
	}
}
