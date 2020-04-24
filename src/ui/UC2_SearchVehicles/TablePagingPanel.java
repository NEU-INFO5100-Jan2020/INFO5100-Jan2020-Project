package ui.UC2_SearchVehicles;

import dto.Vehicle;
import ui.guiforcase3.CarDetailGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class TablePagingPanel extends JPanel {
    private List<Vehicle> vehicles;

    private int pageSize;
    private JTable jTable;
    private TableModel tableModel;

    private int lastPageNum;
    private int currPageNum;
    private JLabel countLabel, sizeLabel;
    private JButton first, prev, next, last, middle;

    public TablePagingPanel(JTable jTable, int pageSize, List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.pageSize = pageSize;
        this.jTable = jTable;
        this.tableModel = jTable.getModel();
        this.lastPageNum = tableModel.getRowCount() / pageSize
                + (tableModel.getRowCount() % pageSize != 0 ? 1 : 0);
        this.currPageNum = 1;
        setLayout(new BorderLayout());

        JPanel countPanel = new JPanel();
        countLabel = new JLabel();
        countPanel.add(countLabel, BorderLayout.CENTER);
        sizeLabel = new JLabel();
        countPanel.add(sizeLabel, BorderLayout.CENTER);
        add(countPanel, BorderLayout.NORTH);
        add(new JScrollPane(jTable), BorderLayout.CENTER);
        add(createControls(), BorderLayout.SOUTH);
        updatePage();
        repaint();
    }



    private JPanel createControls() {
        first = new JButton(new AbstractAction("<<") {
            @Override
            public void actionPerformed(ActionEvent e) {
                currPageNum = 1;
                updatePage();
            }
        });

        prev = new JButton(new AbstractAction("<") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (--currPageNum <= 0)
                    currPageNum = 1;
                updatePage();
            }
        });

        next = new JButton(new AbstractAction(">") {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                if (++currPageNum > lastPageNum)
                    currPageNum = lastPageNum;
                updatePage();

            }
        });

        last = new JButton(new AbstractAction(">>") {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                currPageNum = lastPageNum;
                updatePage();
            }
        });

        middle = new JButton(new AbstractAction("View Details") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTable.getSelectedRow() >= 0) {
                    int selectIndex = jTable.getSelectedRow();
                    Vehicle selectedVehicle = vehicles.get(selectIndex);
                    ArrayList<Vehicle> parameters = new ArrayList<>();
                    parameters.add(selectedVehicle);
                    new CarDetailGUI(parameters);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please Select A Vehicle", "Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JPanel bar = new JPanel(new GridLayout(1, 4));
        bar.add(first);
        bar.add(prev);
        bar.add(middle);
        bar.add(next);
        bar.add(last);
        return bar;
    }

    private void updatePage() {
        // replace the original model with a new model
        // containing only the entries in the current page.
        // Sorting and Paging has to be done here
        DefaultTableModel page = new DefaultTableModel() {
            Class[] types = {String.class, String.class, Float.class, Integer.class, Integer.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        // add Column
        int count = tableModel.getColumnCount();
        for (int i = 0; i < count; i++) {
            page.addColumn(tableModel.getColumnName(i));
        }

        int start = (currPageNum - 1) * pageSize;
        int end = start + pageSize;
        if (end >= tableModel.getRowCount()) {
            end = tableModel.getRowCount();
        }
        for (int rowNum = start; rowNum < end; rowNum++) {
            int columnNum = tableModel.getColumnCount();
            Object[] rowData = new Object[columnNum];
            for (int j = 0; j < columnNum; j++) {
                rowData[j] = tableModel.getValueAt(rowNum, j);
            }
            page.addRow(rowData);
        }
        jTable.setModel(page);
        jTable.setRowSorter(new TableRowSorter<TableModel>(page));

        // update the label
        countLabel.setText("Page " + currPageNum + "/" + lastPageNum);
        sizeLabel.setText(" |  " + pageSize +" per page");

        // update buttons
        final boolean canGoBack = currPageNum != 1;
        final boolean canGoFwd = currPageNum != lastPageNum;
        first.setEnabled(canGoBack);
        prev.setEnabled(canGoBack);
        next.setEnabled(canGoFwd);
        last.setEnabled(canGoFwd);
    }

    public void refreshTable(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        this.lastPageNum = tableModel.getRowCount() / pageSize
                + (tableModel.getRowCount() % pageSize != 0 ? 1 : 0);
        this.currPageNum = 1;
        updatePage();
    }
}