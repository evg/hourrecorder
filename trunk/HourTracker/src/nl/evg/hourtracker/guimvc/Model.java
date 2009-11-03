package nl.evg.hourtracker.guimvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import nl.evg.hourtracker.business.Task;

public class Model
{
	public void createTask(String name)
	{
		tasks.push(new Task(name));
	}
	
	public Task getRunningTask()
	{
		return runningTask;
	}
	
	public int nrTasks()
	{
		return tasks.size();
	}
	
	public Task lastTaskCreated()
	{
		return tasks.peek();
	}
	
	public boolean todaysTasksContain(Task task)
	{
		return todaysTasks.contains(task);
	}

	public void startCounting(Task task)
	{
		runningTask = task;
		runningTask.startNow();
	}
	
	public void stopCounting()
	{
		if (runningTask!=null)
			runningTask.stopNow();
	}
	
	public void addToTodaysTaskList(Task task)
	{
		todaysTasks.add(task);
	}
	
	private Task runningTask = null;
	private List<Task> todaysTasks = new ArrayList<Task>();
	private Stack<Task> tasks = new Stack<Task>();
}
