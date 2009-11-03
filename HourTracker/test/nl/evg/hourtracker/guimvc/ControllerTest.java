package nl.evg.hourtracker.guimvc;


import org.junit.Test;
import static org.junit.Assert.*;

import nl.evg.hourtracker.business.Task;
import nl.evg.hourtracker.guimvc.Controller;
import nl.evg.hourtracker.guimvc.Model;
import nl.evg.hourtracker.helper.Helper;
import nl.evg.hourtracker.helper.Log;
import nl.evg.hourtracker.helper.MockView;


public class ControllerTest
{
	@Test public void onNewTaskEvent_CreateTaskInModel()
	{
		Log logger = new Log();
		Model model = new Model();
		int oldNrTasks = model.nrTasks();
		new Controller(model,new MockView(logger)).handleNewTaskRequest("My task");
		assertEquals(model.nrTasks(),oldNrTasks+1);
		assertEquals(model.lastTaskCreated(),new Task("My task"));
	}

	@Test public void onNewTaskEvent_ListWithTasksIsExpandedWithNewTask()
	{
		Log logger = new Log();
		MockView view = new MockView(logger);
		new Controller(new Model(),view).handleNewTaskRequest("My task");
		assertEquals(view.getLastRequest(),"addToTaskList: My task");
	}
	
	@Test public void onActivateTask_Check_AddToTodayListIfNotOnTodayListYet()
	{
		Log logger = new Log();
		MockView view = new MockView(logger);
		Model model = new Model();
		assertFalse(model.todaysTasksContain(new Task("My task")));
		new Controller(model,view).handleActivateTask(new Task("My task"));
		assertEquals(view.nrTimesReceived("addToTodaysTasksList: My task"),1);
		assertTrue(model.todaysTasksContain(new Task("My task")));
		
		new Controller(model,view).handleActivateTask(new Task("My task"));
		assertEquals("Only once on today's task list", view.nrTimesReceived("addToTodaysTasksList: My task"),1);
	}
	
	@Test public void onActivateTask_Check_ShowAsCurrentTaskRequest_SentToView()
	{
		Log logger = new Log();
		MockView view = new MockView(logger);
		new Controller(new Model(),view).handleActivateTask(new Task("My task"));
		assertTrue(view.hasReceivedRequest("showAsCurrentTask: My task"));
	}
	
	@Test public void onActivateTask_TaskIsCounting()
	{
		Log logger = new Log();
		MockView view = new MockView(logger);
		Model model = new Model();
		new Controller(model,view).handleActivateTask(new Task("My task"));
		assertEquals(model.getRunningTask(),new Task("My task"));
		Helper.pause(3);
		assertTrue(model.getRunningTask().getSecondsTaken()>1);
	}

	@Test public void onActivateTask_OldTaskStopsCounting()
	{
		Log logger = new Log();
		MockView view = new MockView(logger);
		Model model = new Model();
		new Controller(model,view).handleActivateTask(new Task("My task"));
		assertEquals(model.getRunningTask(),new Task("My task"));
		Helper.pause(3);
		Task oldTask = model.getRunningTask();
		int oldSeconds = oldTask.getSecondsTaken();
		assertTrue(oldTask.getSecondsTaken()>0);

		new Controller(model,view).handleActivateTask(new Task("Other task"));
		Helper.pause(3);
		assertEquals(oldTask.getSecondsTaken(),oldSeconds);
	}
	
	@Test public void whenReactivatingTaskCountIt()
	{
		Log logger = new Log();
		MockView view = new MockView(logger);
		Model model = new Model();
		Task task1 = new Task("Task1");
		Task task2 = new Task("Task2");
		new Controller(model,view).handleActivateTask(task1);
		Helper.pause(1);
		assertEquals(task1.getSecondsTaken(),1);

		new Controller(model,view).handleActivateTask(task2);
		Helper.pause(1);
		assertEquals(task1.getSecondsTaken(),1);
		
		new Controller(model,view).handleActivateTask(task1);
		Helper.pause(1);
		assertEquals(task1.getSecondsTaken(),2);
	}
}
