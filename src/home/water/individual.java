/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package home.water;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author kamau
 */
public class individual extends javax.swing.JFrame {
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;
DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
String Code;
String Year;
String Month;
    /**
     * Creates new form individual
     */
    public individual() {
        initComponents();
    }
 public void one(){
 		JTable table = new JTable(){
                        public Component prepareRenderer(TableCellRenderer renderer,int Index_row, int Index_col) {
				Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
				//even index, selected or not selected
				if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
					comp.setBackground(Color.lightGray);
				} 
				else {
					comp.setBackground(Color.red);
				}
				return comp;
			}
		};
 }
public void invoice(){
try{
            String para = Code;
            String para2 = Month;
            String para3 = Year;
            FileInputStream fis = new FileInputStream("C:\\Users\\kamau\\Documents\\NetBeansProjects\\home water\\individual.jrxml");
            BufferedInputStream bis = new BufferedInputStream(fis);
            Map map = new HashMap();
            map.put("HouseNo", para);
            map.put("Month", para2);
            map.put("Year", para3);
            JasperReport jasperReport = (JasperReport)JasperCompileManager.compileReport(bis);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
            JasperViewer.viewReport(jasperPrint,false);
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
}
public void block(){
String sql = "SELECT  `name` FROM `block`";
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 cboblock.addItem(rs.getString("name"));
 }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}}
public void select(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount from monthlywater ";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    invoice.setModel(DbUtils.resultSetToTableModel(rs));    
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount from monthlywater where `block` = '"+cboblock.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    invoice.setModel(DbUtils.resultSetToTableModel(rs));    
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
public void month(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount from monthlywater where `month` = '"+cbomonth.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    invoice.setModel(DbUtils.resultSetToTableModel(rs));    
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount from monthlywater where `block` = '"+cboblock.getSelectedItem()+"' and `month` = '"+cbomonth.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    invoice.setModel(DbUtils.resultSetToTableModel(rs));    
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
public void year(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount from monthlywater where `month` = '"+cbomonth.getSelectedItem()+"' and `year` = '"+cboyear.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    invoice.setModel(DbUtils.resultSetToTableModel(rs));    
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount from monthlywater where `block` = '"+cboblock.getSelectedItem()+"' and `month` = '"+cbomonth.getSelectedItem()+"' and `year` = '"+cboyear.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    invoice.setModel(DbUtils.resultSetToTableModel(rs));    
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboblock = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        cbomonth = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        invoice = new javax.swing.JTable();
        cboyear = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmdClose = new javax.swing.JButton();
        txthouse = new javax.swing.JTextField();
        txtmonth = new javax.swing.JTextField();
        txtyear = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Individual Invoices");

        jLabel3.setText("Month");

        cboblock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cboblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboblockActionPerformed(evt);
            }
        });

        jLabel2.setText("Block");

        jLabel4.setText("Year");

        cbomonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March ", "May", "April", "June ", "July", "August", "September", "October", "November", "December" }));
        cbomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomonthActionPerformed(evt);
            }
        });

        invoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        invoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invoiceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(invoice);

        cboyear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015" }));
        cboyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboyearActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 0));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel5.setText("<<CLICK TO PRINT INVOICE FOR INDIVIDUAL TENENT>>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/water/exit.PNG"))); // NOI18N
        cmdClose.setText("Close");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdClose)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdClose)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txthouse, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(228, 228, 228)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator2)
                        .addComponent(jSeparator1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cboblock, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40)
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbomonth, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(42, 42, 42)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 424, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txthouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cboblock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(cbomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(107, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboblockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboblockActionPerformed
        // TODO add your handling code here:
        select();
    }//GEN-LAST:event_cboblockActionPerformed

    private void cbomonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomonthActionPerformed
        // TODO add your handling code here:
        month();
    }//GEN-LAST:event_cbomonthActionPerformed

    private void cboyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboyearActionPerformed
        // TODO add your handling code here:
        year();
    }//GEN-LAST:event_cboyearActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         conn = connectDB.ConnectDB();
        block();
        one();
        txthouse.setVisible(false);
        txtmonth.setVisible(false);
        txtyear.setVisible(false);     
    }//GEN-LAST:event_formWindowOpened

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void invoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoiceMouseClicked
        // TODO add your handling code here:
        try{
         int row = invoice.getSelectedRow();
        txthouse.setText(invoice.getModel().getValueAt(row, 1).toString());
        Code=txthouse.getText();
        txtmonth.setText(invoice.getModel().getValueAt(row, 4).toString());
        Month=txtmonth.getText();
        txtyear.setText(invoice.getModel().getValueAt(row, 5).toString());
        Year=txtyear.getText();
        int p = JOptionPane.showConfirmDialog(null, "Invoice for house number "+txthouse.getText()+"","Accept",JOptionPane.YES_NO_OPTION);
        if (p==0){
        invoice();
        }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "You have selected an invalid option");
        }
    }//GEN-LAST:event_invoiceMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new individual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboblock;
    private javax.swing.JComboBox cbomonth;
    private javax.swing.JComboBox cboyear;
    private javax.swing.JButton cmdClose;
    private javax.swing.JTable invoice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txthouse;
    private javax.swing.JTextField txtmonth;
    private javax.swing.JTextField txtyear;
    // End of variables declaration//GEN-END:variables
}
