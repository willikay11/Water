/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package home.water;

/**
 *
 * @author kamau
 */
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
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
public class invoice extends javax.swing.JFrame {
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;
DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    /**
     * Creates new form invoice
     */
    public invoice() {
        initComponents();
    }
public void invoice(){
try{
    Object obj = cboblock.getSelectedItem();
    Object object = cbomonth.getSelectedItem();
    Object object1 = cboyear.getSelectedItem();
            String para = obj.toString();
            String para2 = object.toString();
            String para3 = object1.toString();
            FileInputStream fis = new FileInputStream("C:\\Users\\kamau\\Documents\\NetBeansProjects\\home water\\invoice.jrxml");
            BufferedInputStream bis = new BufferedInputStream(fis);
            Map map = new HashMap();
            map.put("block", para);
            map.put("month", para2);
            map.put("year", para3);
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cboblock = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbomonth = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cboyear = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        invoice = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cmdClose = new javax.swing.JButton();
        issue = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Invoices");

        jLabel2.setText("Block");

        cboblock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cboblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboblockActionPerformed(evt);
            }
        });

        jLabel3.setText("Month");

        cbomonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March ", "May", "April", "June ", "July", "August", "September", "October", "November", "December" }));
        cbomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomonthActionPerformed(evt);
            }
        });

        jLabel4.setText("Year");

        cboyear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015" }));
        cboyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboyearActionPerformed(evt);
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
        jScrollPane1.setViewportView(invoice);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/water/exit.PNG"))); // NOI18N
        cmdClose.setText("Close");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        issue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/water/clear.PNG"))); // NOI18N
        issue.setText("Issue Invoices");
        issue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(issue)
                .addGap(27, 27, 27)
                .addComponent(cmdClose)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdClose)
                    .addComponent(issue))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboblock, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cbomonth, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 314, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jLabel4)
                    .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        conn = connectDB.ConnectDB();
        block();
    }//GEN-LAST:event_formWindowOpened

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

    private void issueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueActionPerformed
        // TODO add your handling code here:
        invoice();
    }//GEN-LAST:event_issueActionPerformed

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

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
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new invoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboblock;
    private javax.swing.JComboBox cbomonth;
    private javax.swing.JComboBox cboyear;
    private javax.swing.JButton cmdClose;
    private javax.swing.JTable invoice;
    private javax.swing.JButton issue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
