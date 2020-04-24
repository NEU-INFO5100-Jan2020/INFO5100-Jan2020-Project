package ui.guiforcase5;

import dto.Dealer;
import dto.Incentives;
import persist.DealerManager;
import persist.IncentivesManager;
import persist.IncentivesManagerImpl;
import persist.DealerManagerImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class IncentiveMainPage extends JFrame {
    private JButton createButton, editButton, deleteButton, backButton;
    private JScrollPane jScrollPane1;
    private JTabbedPane jTabbedPane1;
    private JTable jTable1;
    private int dealerID;
    private String dealerName;
    private IncentiveMainPage thisPage;
    ui.guiforcase5.CreatePage cp;
    ui.guiforcase5.EditPage ep;

    public IncentiveMainPage(int dealerID, String dealerName) {
        thisPage = this;
        setDealer(dealerID, dealerName);
        initComponents();
        addActionListener();
        refreshTableContents();
    }

    private void setDealer(int dealerID, String dealerName) {
        this.dealerID = dealerID;
        this.dealerName = dealerName;
    }
    private void addActionListener() {
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cp = new CreatePage(dealerID,dealerName, thisPage);
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    int rowIndex = jTable1.getSelectedRow();
                    if(rowIndex==-1){
                        throw new Exception();
                    }
                    Incentives incentives=extract(rowIndex);
                    ep = new ui.guiforcase5.EditPage(dealerID,dealerName, incentives, thisPage);
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,"plesae select a row");
                }
            }
        });
    }

    private Incentives extract(int rowIndex){
        rowIndex = jTable1.getSelectedRow();
        int ID = Integer.parseInt(jTable1.getValueAt(rowIndex, 0).toString()) ;
        IncentivesManagerImpl incentivesManagerImpl =new IncentivesManagerImpl();
        Collection<Incentives> incentivelist= incentivesManagerImpl.getListOfIncentives();
        for(Incentives i:incentivelist){
            if(i.getIncentiveId()==ID){
                return i;
            }
        }
        return null;
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column)
            {
                return false;}//Table is not editable
        };
        createButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        editButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setBackground(new java.awt.Color(255, 153, 102));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] { { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null } },
                new String[] { "IncentiveID", "Title", "Discription", "Disclaimer", "StartDate", "EndDate",
                        "discountValue", "DiscountType", "" }) {
            Class[] types = new Class[] { java.lang.Integer.class, java.lang.String.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        jTable1.setRowHeight(20);
        jTable1.setSelectionForeground(new java.awt.Color(255, 51, 102));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);

            }
        });

        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);

            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        editButton.setText("Edit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addGap(216, 216, 216).addComponent(createButton)
                                        .addGap(117, 117, 117).addComponent(deleteButton).addGap(144, 144, 144)
                                        .addComponent(editButton).addGap(100, 100, 100).addComponent(backButton))
                                .addGroup(layout.createSequentialGroup().addContainerGap()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1064,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup().addGap(131, 131, 131).addComponent(jTabbedPane1,
                                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(createButton)
                        .addComponent(deleteButton).addComponent(editButton).addComponent(backButton))
                .addContainerGap(52, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        //get select row or rows
        int[] rows = jTable1.getSelectedRows();
        if (rows.length == 0) {
            JOptionPane.showMessageDialog(null, "Please select row(s) to delete.");
            return;
        }

        IncentivesManager im = new IncentivesManagerImpl();
        String success = "";
        String fail = "";
        for (int i = rows.length - 1; i >= 0; i--) {
            String incentiveID = (String)jTable1.getValueAt(rows[i], 0);
            boolean deleted = im.deleteIncentive(Integer.parseInt(incentiveID));

            if (deleted) {
                success += " " + incentiveID + " ";
                tableModel.removeRow(rows[i]);
            }else {
                fail += " " + incentiveID + " ";
            }
        }
        String message = "";
        if (!success.isEmpty()) {
            message += "Successful deleted IncentiveID: " + success + '\n';
        }
        if (!fail.isEmpty()) {
            message += "Failed deleted IncentiveID: " + fail;
        }
        JOptionPane.showMessageDialog(null, message);
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (cp != null) {
            cp.disposePage();
        }
        if (ep != null) {
            ep.disposePage();
        }
        thisPage.dispose();
        DealerManagerImpl dealerManager = new DealerManagerImpl();
        Dealer curDealer = dealerManager.getDealer(dealerID);
        new ui.guiforcase4.OperationOptions(dealerID, curDealer.getDealerName());
    }

    protected void refreshTableContents(){
        DefaultTableModel tableModel=new DefaultTableModel();
        tableModel.addColumn("Incentive ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Start Date");
        tableModel.addColumn("End Date");
        tableModel.addColumn("Type");
        tableModel.addColumn("Value");
        tableModel.addColumn("Description");
        tableModel.addColumn("Disclaimer");

        IncentivesManagerImpl  incentivesManagerimpl=new IncentivesManagerImpl ();
        Collection<Incentives> incentivelist= incentivesManagerimpl.getListOfIncentives();
        for(Incentives i:incentivelist){
            if(i.getDealerId() != this.dealerID) continue;
            if(i.getIsDeleted() != false) continue;
            tableModel.addRow(new String[]{
                    String.valueOf(i.getIncentiveId()),
                    i.getTitle(), i.getStartDate().toString(),
                    i.getEndDate().toString(),
                    i.getDiscountType(),
                    String.valueOf(i.getDiscountValue()),
                    i.getDescription(),
                    i.getDisclaimer()});
        }
        jTable1.setModel(tableModel);
    }

//    public static void main(String args[])  {
//        new IncentiveMainPage(1).setVisible(true);
//    }

}
