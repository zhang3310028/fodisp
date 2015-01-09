package test;

import java.awt.Container;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.dyno.visual.swing.layouts.GroupLayout;

//VS4E -- DO NOT REMOVE THIS LINE!
public class RowSorterTest extends JFrame {

	private static final long serialVersionUID = 1L;

	public RowSorterTest() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		JPanel jPanel = new JPanel();
		JScrollPane jScrollPane = new JScrollPane();
		
		JTable dataGrid=new JTable ();
		dataGrid.setSize(30, 50);
		DefaultTableModel model = (DefaultTableModel) dataGrid.getModel();
		model.addColumn("a");
		model.addColumn("b");
		model.addColumn("c");
		model.addRow(new Object[]{1,2,3});
		model.addRow(new Object[]{5,2,4});
		jScrollPane.setViewportView(dataGrid);
		jPanel.add(jScrollPane);
		this.setContentPane(jPanel);
		TableRowSorter<TableModel> sorter=new TableRowSorter<TableModel>(model);
//		sorter.setSortable(0, false);
//		sorter.setComparator(0, new OrderNumberComparator());
//		sorter.setComparator(3, new OrderNumberComparator());
//		sorter.setComparator(4, new OrderNumberComparator());
//		sorter.setComparator(5, new OrderNumberComparator());
		dataGrid.setRowSorter(sorter);
		setSize(520, 240);
	}
	public static class OrderNumberComparator implements Comparator{

	    public int compare(Object o1, Object o2) {
	        int i = (Integer) o1 - (Integer) o2;
	            return i;
	    }

	}
}
