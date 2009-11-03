package nl.evg.hourtracker.helper;

public class Helper
{
	public static void pause(int seconds)
	{
		try
		{
			Thread.sleep(seconds*1000);
		} 
		catch (InterruptedException e)
		{
		}
	}

}
