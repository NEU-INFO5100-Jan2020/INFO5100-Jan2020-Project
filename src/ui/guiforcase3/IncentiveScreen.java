package ui.guiforcase3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import dto.Incentives;
import persist.IncentivesManagerImpl;

public class IncentiveScreen {

	public IncentiveScreen(int vechileId) {		
		IncentivesManagerImpl incImpl = new IncentivesManagerImpl();
		Collection<Incentives> result = incImpl.checkIncentives(vechileId);
		if (result.size()==0) {
			showMessageDialog();
		}
		else {
		JFrame frame = new JFrame("Incentive Details");		
		frame.setSize(1000, 300);
		String[] columns = {"Incentive ID","Title", "Type", "Value", "End Date"};
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		
		for (Incentives inc : result) {
			Vector<String> row = new Vector<>();
			row.add(inc.getIncentiveId()+"");
			row.add(inc.getTitle());
			row.add(inc.getDiscountType());
			row.add(inc.getDiscountValue() + "");						
			row.add(inc.getEndDate() + "");
			model.addRow(row);
		}

		JTable table = new JTable(model) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// this is to have alternative color in the table row
				Component returnComp = super.prepareRenderer(renderer, row, column);
				Color darkShade = new Color(0, 52, 94);
				Color lightShade = new Color(0, 75, 134);
				if (!returnComp.getBackground().equals(getSelectionBackground())) {
					Color bg = (row % 2 == 0 ? darkShade : lightShade);
					returnComp.setBackground(bg);
					bg = null;
				}
				return returnComp;
			}
		};

		table.setPreferredSize(new Dimension(700, 500));
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(222, 249, 250));
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setRowHeight(table.getRowHeight() + 20); // set row height
		table.setDefaultEditor(Object.class, null); // to stop the editing of table cell on double click of mouse

		// to make text Center Align
		DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
		rendar.setHorizontalAlignment(JLabel.CENTER);
		for (int x = 0; x < table.getColumnCount(); x++) {
			table.getColumnModel().getColumn(x).setCellRenderer(rendar);
		}

		// Table Header
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Dialog", Font.BOLD, 18));
		header.setBackground(new Color(0, 30, 54));
		header.setForeground(Color.WHITE);
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

			// set header size
		table.getTableHeader().setPreferredSize(new Dimension(400, table.getRowHeight())

		);
		setJTableColumnsWidth(table, 400, 10, 30, 30, 20, 10);
		JScrollPane jScrollPane = new JScrollPane(table);
		frame.add(jScrollPane);
		frame.getContentPane().setLayout(new BorderLayout());	 
       
		frame.getContentPane().add(jScrollPane,BorderLayout.CENTER);
		frame.setVisible(true);
	}
	}
	
	//Show message dialog when there is no incentive
	public static void showMessageDialog() {
		String message= "Oops, there is no valid incentive available on this vehicle";
		JOptionPane.showMessageDialog(null, message,"Incentive",JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Set table column Width
	public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
		double total = 0;
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			total += percentages[i];
		}

		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
		}
	}

}
