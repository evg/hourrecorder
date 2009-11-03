package nl.evg.hourtracker.helper;

import nl.evg.hourtracker.business.Task;
import nl.evg.hourtracker.guimvc.View;



public class MockView implements View
{
	public MockView(Log logger)
	{
		this.logger = logger;
	}
	
//	@Override
	public void addtoTodaysTaskList(Task task)
	{
		logger.log("addToTodaysTasksList: "+task);
	}

//	@Override
	public void showAsCurrentTask(Task task)
	{
		logger.log("showAsCurrentTask: "+task);
	}

//	@Override
	public void addToTaskList(Task task)
	{
		logger.log("addToTaskList: "+task);
	}
	
	public String getLastRequest()
	{
		return logger.getLastLine();
	}
	
	public boolean hasReceivedRequest(String request)
	{
		return logger.containsLine(request);
	}
	
	public int nrTimesReceived(String request)
	{
		return logger.nrTimesContained(request);
	}
	
	private Log logger;
}
