package nl.evg.hourtracker.guimvc;

import nl.evg.hourtracker.business.Task;

public interface View
{
	public void addToTaskList(Task task);
	public void showAsCurrentTask(Task task);
	public void addtoTodaysTaskList(Task task);
}

