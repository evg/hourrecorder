package nl.evg.hourtracker.guimvc;

import nl.evg.hourtracker.business.Task;

public class Controller
{
	public Controller(Model model,View view)
	{
		this.model = model;
		this.view = view;
	}
	
	public void handleNewTaskRequest(String taskName)
	{
		model.createTask(taskName);
		view.addToTaskList(model.lastTaskCreated());
//		model.addToTodaysTaskList(model.lastTaskCreated());
//		view.addtoTodaysTaskList(model.lastTaskCreated());
	}
	
	public void handleActivateTask(Task task)
	{
		if (task.equals(model.getRunningTask()))
			return;
		
		model.stopCounting();
		if (!model.todaysTasksContain(task))
		{
			model.addToTodaysTaskList(task);
			view.addtoTodaysTaskList(task);
		}
		model.startCounting(task);
		view.showAsCurrentTask(task);
	}
	
	private Model model;
	private View view;
}
