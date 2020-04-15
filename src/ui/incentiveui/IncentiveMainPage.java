package ui.incentiveui;

import dto.Incentives;
import persist.IncentivesManagerImpl;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
// import javax.swing.JOptionPane;
//import com.toedter.calendar.JDateChooser;
//        import lombok.Data;

public class IncentiveMainPage extends JFrame {

    private JButton createButton, deleteButton, editButton;
    private int dealerID;
    private JScrollPane jScrollPane1;
    private JTabbedPane jTabbedPane1;
    private DefaultTableModel defaultTableModel;
    private JTable jTable1;
    private Collection<Incentives> incentivelist;
    private IncentivesManagerImpl incentivesManagerImpl;
    /**
     * Creates new form NewJFrame
     */
    public IncentiveMainPage(int dealerID) {
        setDealerID(dealerID);
        initComponents();
        addActionListener();
        refreshTableContents();
        IncentivesManagerImpl incentivesManagerImpl =new IncentivesManagerImpl();
        Collection<Incentives> incentivelist= incentivesManagerImpl.getListOfIncentives();

    }

    private void setDealerID(int dealerID) {
        this.dealerID = dealerID;
    }
    private void addActionListener() {
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new ui.incentiveui.CreatePage(dealerID,incentiveMainPage);

            }
        });
        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int rowIndex = jTable1.getSelectedRow();
                new ui.incentiveui.EditPage(dealerID,rowIndex,incentiveMainPage);
            }
        });
    }

    public IncentiveMainPage incentiveMainPage;
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        createButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        // pageTitle = new javax.swing.JLabel("Manage Incentives");
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

        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
//                CreatePage createPage=new CreatePage();
//                IncentivesMangerimpl incentivesMangerimpl=new IncentivesMangerimpl();
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
                                        .addComponent(editButton))
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
                        .addComponent(deleteButton).addComponent(editButton))
                .addContainerGap(52, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        defaultTableModel=(DefaultTableModel)jTable1.getModel();

        int rowIndex = jTable1.getSelectedRow();


        int ID = Integer.parseInt(jTable1.getValueAt(rowIndex, 4).toString()) ;
        System.out.println(ID);

        defaultTableModel.removeRow(rowIndex);

        IncentivesManagerImpl incentivesManagerImpl = new IncentivesManagerImpl();
        incentivesManagerImpl.deleteIncentive(ID);
        refreshTableContents();




        System.out.println("test selected row");
    }// GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
//        jTable1.editCellAt(ERROR, 0);
//        jTable1.editCellAt(ERROR, 1);
    }// GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])  {
//
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IncentiveMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IncentiveMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IncentiveMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IncentiveMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(new Runnable() {
//        IncentiveMainPage incentiveMainPage=new IncentiveMainPage();
        new IncentiveMainPage(6364).setVisible(true);;
//            public void run() {
//                new IncentiveMainPage().setVisible(true);
//            }

//         });
    }


    public void refreshTableContents(){

        DefaultTableModel tableModel=new DefaultTableModel();
        tableModel.addColumn("title");
        tableModel.addColumn("start date");
        tableModel.addColumn("end date");
        tableModel.addColumn("value");
        tableModel.addColumn("Id");

        IncentivesManagerImpl incentivesManagerImpl =new IncentivesManagerImpl();
        Collection<Incentives> incentivelist= incentivesManagerImpl.getListOfIncentives();
        for(Incentives i:incentivelist){
            tableModel.addRow(new String[]{i.getTitle(),i.getStartDate().toString(),
                    i.getEndDate().toString(), String.valueOf(i.getDiscountValue()),
                    String.valueOf(i.getIncentiveId())});
        }
        jTable1.setModel(tableModel);
        //jTable1.setEnabled(false);
        jTable1.isCellEditable(0,0);

    }

}
