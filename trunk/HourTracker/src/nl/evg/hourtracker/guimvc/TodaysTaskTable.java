package nl.evg.hourtracker.guimvc;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class TodaysTaskTable extends JTable
{
	public TodaysTaskTable(TableModel dm)
	{
		super(dm);
	}

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

	public static final long serialVersionUID = 1L;
}
