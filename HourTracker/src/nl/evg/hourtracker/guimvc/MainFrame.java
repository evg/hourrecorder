package nl.evg.hourtracker.guimvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import nl.evg.hourtracker.business.Task;
import nl.evg.hourtracker.business.TimeFormatter;

public class MainFrame extends JFrame implements View
{
	// View stuff ----------------------------------------

	@Override
	public void showAsCurrentTask(Task task)
	{
		setTitle(task.getName());
		showSelected(task);
	}
	
	@Override
	public void addToTaskList(Task task)
	{
		taskListModel.addElement(task);
	}

	@Override
	public void addtoTodaysTaskList(Task task)
	{
		Vector<Object> rowData = new Vector<Object>();
		rowData.add(task);
		rowData.add("running");
		todaysTaskListModel.addRow(rowData);
	}

	// Controller stuff ---------------------------------------
	

	public void newTaskButtonClicked(String taskName)
	{
		controller.handleNewTaskRequest(taskName);
	}

	private void handleDoubleClickInTaskListOnItemWithIndex(int index)
	{
		controller.handleActivateTask(getTask(index));
	}

	private void handleDoubleClickInTodaysTaskListOnTask(Task task)
	{
		controller.handleActivateTask(task);
	}

	// standard stuff ---------------------------------------

	private void layoutControls()
	{
		// south
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(newTaskButton);
		panel.add(newTaskNameField);
		getContentPane().add(panel, BorderLayout.SOUTH);

		// west
		JScrollPane taskScrollPane = new JScrollPane(taskList);
		getContentPane().add(taskScrollPane, BorderLayout.WEST);
		
		// center
		JPanel todayPanel = new JPanel(new BorderLayout());
		JScrollPane todaysTasksScrollPane = new JScrollPane(todaysTaskList);
		todayPanel.add(new JLabel("Todays tasks"),BorderLayout.NORTH);
		todayPanel.add(todaysTasksScrollPane, BorderLayout.CENTER);
		getContentPane().add(todayPanel, BorderLayout.CENTER);
	}

	public MainFrame()
	{
		super("Hourtracker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		// Display the window.
		setPreferredSize(new Dimension(600, 400));
		pack();
		setVisible(true);
		controller = new Controller(new Model(), this);
		layoutControls();
		mapListenerEventsToDomainEvents();
	}

	public static void main(String[] args)
	{
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainFrame();
			}
		});
	}

	private Task getTask(int index)
	{
		return (Task) (taskList.getModel().getElementAt(index));
	}

	private void showSelected(Task task)
	{
		for(int row=0; row<todaysTaskListModel.getRowCount(); row++)
		{
			Task taskInRow = (Task)todaysTaskListModel.getValueAt(row, 0);
			String timeString = getDisplayTimeFor(taskInRow);
			if (taskInRow.equals(task))
			{
				todaysTaskList.getSelectionModel().setSelectionInterval(row, row);
				todaysTaskList.setValueAt(timeString+" Running", row, 1);
			}
			else
				todaysTaskList.setValueAt(timeString, row, 1);
		}
	}
	
	private String getDisplayTimeFor(Task task)
	{
		return timeFormatter.format(task.getSecondsTaken());
	}
	
	private TimeFormatter timeFormatter = new TimeFormatter();

	private void mapListenerEventsToDomainEvents()
	{
		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					if (e.getSource().equals(taskList))
					{
						int index = taskList.locationToIndex(e.getPoint());
						handleDoubleClickInTaskListOnItemWithIndex(index);
					}
					if (e.getSource().equals(todaysTaskList))
					{
						int index = todaysTaskList.getSelectedRow();
						handleDoubleClickInTodaysTaskListOnTask((Task)todaysTaskListModel.getValueAt(index, 0));
					}
				}
			}
		};
		taskList.addMouseListener(mouseListener);

		ActionListener newTaskButtonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getSource().equals(newTaskButton))
					newTaskButtonClicked(newTaskNameField.getText().trim());
			}
		};
		newTaskButton.addActionListener(newTaskButtonListener);
	}
	
	private Controller controller;
	private DefaultListModel taskListModel = new DefaultListModel();
	private DefaultTableModel todaysTaskListModel = new DefaultTableModel(getColumnNames(),0);
	private JList taskList = new JList(taskListModel);
	private JTable todaysTaskList = new TodaysTaskTable(todaysTaskListModel);
	private JTextField newTaskNameField = new JTextField(30);
	private JButton newTaskButton = new JButton("Add task");

	private static Vector<String> getColumnNames()
	{
		Vector<String> result = new Vector<String>();
		result.add("Name");
		result.add("Time");
		return result;
	}
	
	static final long serialVersionUID = 1L;
}
