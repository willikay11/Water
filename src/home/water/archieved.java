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
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class archieved extends javax.swing.JFrame {
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;
String Completed = "Closed";
String January = "01";
String February = "02";
String March = "03";
String April = "04";
String May = "05";
String June = "06";
String July = "07";
String August = "08";
String September = "09";
String October = "10";
String November = "11";
String December = "12";
public void month(){
        if(cbomonth.getSelectedItem().equals("January")){
        txtMN.setText(January);
        }else{
        if(cbomonth.getSelectedItem().equals("February")){
        txtMN.setText(February);
        }else{
        if(cbomonth.getSelectedItem().equals("March")){
        txtMN.setText(March);
        }else{
        if(cbomonth.getSelectedItem().equals("April")){
        txtMN.setText(April);
        }else{
        if(cbomonth.getSelectedItem().equals("May")){
        txtMN.setText(May);
        }else{
        if(cbomonth.getSelectedItem().equals("June")){
        txtMN.setText(June);
        }else{
        if(cbomonth.getSelectedItem().equals("July")){
        txtMN.setText(July);
        }else{
        if(cbomonth.getSelectedItem().equals("August")){
        txtMN.setText(August);
        }else{
        if(cbomonth.getSelectedItem().equals("October")){
        txtMN.setText(October);
        }else{
        if(cbomonth.getSelectedItem().equals("November")){
        txtMN.setText(November);
        }else{
        if(cbomonth.getSelectedItem().equals("December")){
        txtMN.setText(December);
        }else{
        if(cbomonth.getSelectedItem().equals("September")){
        txtMN.setText(September);
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
          }
        }
}
public void clear(){
txtname.setText("");
txtno.setText("");
txtmeter.setText("");
txtp.setText("");
txtcurrentread.setText("");
rate.setText("");
txtamount.setText("");
}
    /**
     * Creates new form archieved
     */
    public archieved() {
        initComponents();
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
public void archieved(){
String sql = "SELECT  `block`, `houseno`, `tenantname`, `meterno` FROM `houses` where `block` = '"+cboblock.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    archieved.setModel(DbUtils.resultSetToTableModel(rs)); 
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        archieved = new javax.swing.JTable();
        txtmeter = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtcurrentread = new javax.swing.JTextField();
        txtrate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        rate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboblock = new javax.swing.JComboBox();
        year = new com.toedter.calendar.JYearChooser();
        jLabel3 = new javax.swing.JLabel();
        txtp = new javax.swing.JTextField();
        txtMN = new javax.swing.JTextField();
        cbomonth = new javax.swing.JComboBox();
        cmdSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Archived Records");

        archieved.setModel(new javax.swing.table.DefaultTableModel(
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
        archieved.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archievedMouseClicked(evt);
            }
        });
        archieved.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                archievedKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                archievedKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(archieved);

        txtmeter.setEditable(false);

        jLabel12.setText("Rate");

        txtno.setEditable(false);

        jLabel9.setText("Year");

        jLabel11.setText("Reading");

        jLabel14.setText("Units");

        jLabel7.setText("Tenant Name");

        jLabel8.setText("Month");

        jLabel5.setText("House Number");

        jLabel6.setText("Meter Number");

        txtname.setEditable(false);

        txtcurrentread.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcurrentreadFocusLost(evt);
            }
        });
        txtcurrentread.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcurrentreadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcurrentreadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcurrentreadKeyTyped(evt);
            }
        });

        txtrate.setEditable(false);
        txtrate.setText("100");
        txtrate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrateActionPerformed(evt);
            }
        });

        jLabel10.setText("Amount");

        txtamount.setEditable(false);

        rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rateKeyTyped(evt);
            }
        });

        jLabel2.setText("Block");

        cboblock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboblockMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboblockMousePressed(evt);
            }
        });
        cboblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboblockActionPerformed(evt);
            }
        });
        cboblock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboblockKeyPressed(evt);
            }
        });

        jLabel3.setText("Previous Reading");

        cbomonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cbomonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbomonthMouseClicked(evt);
            }
        });
        cbomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomonthActionPerformed(evt);
            }
        });

        cmdSave.setText("Save");
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtno)
                                            .addComponent(txtmeter)
                                            .addComponent(txtname)
                                            .addComponent(txtcurrentread)
                                            .addComponent(txtrate)
                                            .addComponent(txtamount)
                                            .addComponent(rate, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                            .addComponent(cboblock, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbomonth, 0, 115, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtp)))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtMN, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(cmdSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel14, jLabel2, jLabel3, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cboblock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtmeter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtcurrentread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(cmdSave)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcurrentreadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcurrentreadFocusLost
        // TODO add your handling code here:
        int a = (Integer.parseInt(txtp.getText()));
        int b = (Integer.parseInt(txtcurrentread.getText()));
        if(a>b){
        JOptionPane.showMessageDialog(null, "Previous Reading can't be greater than current Reading");
        txtcurrentread.setText("");
        txtp.setText("");
        rate.setText("");
        txtamount.setText("");
        }
    }//GEN-LAST:event_txtcurrentreadFocusLost

    private void txtcurrentreadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcurrentreadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcurrentreadKeyPressed

    private void txtcurrentreadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcurrentreadKeyReleased
        // TODO add your handling code here:
        rate.setText(Integer.toString(Integer.parseInt(txtcurrentread.getText()) - Integer.parseInt(txtp.getText())));
        txtamount.setText(Integer.toString(Integer.parseInt(rate.getText())* Integer.parseInt(txtrate.getText())));
    }//GEN-LAST:event_txtcurrentreadKeyReleased

    private void txtcurrentreadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcurrentreadKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtcurrentreadKeyTyped

    private void txtrateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrateActionPerformed

    private void rateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rateKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_rateKeyTyped
    final DateFormat df = new SimpleDateFormat("MMMMMMMMM");
    final DateFormat fd  = new SimpleDateFormat("yyyy");
    final DateFormat N  = new SimpleDateFormat("MM");
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        conn = connectDB.ConnectDB();
        block();
    }//GEN-LAST:event_formWindowOpened

    private void cboblockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboblockActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboblockActionPerformed

    private void cboblockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboblockMouseClicked
        // TODO add your handling code here:
        archieved();
    }//GEN-LAST:event_cboblockMouseClicked

    private void archievedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archievedMouseClicked
        // TODO add your handling code here:
         int row = archieved.getSelectedRow();
        txtno.setText(archieved.getModel().getValueAt(row, 1).toString());
        txtmeter.setText(archieved.getModel().getValueAt(row, 3).toString());
        txtname.setText(archieved.getModel().getValueAt(row, 2).toString());
    }//GEN-LAST:event_archievedMouseClicked

    private void cboblockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboblockKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboblockKeyPressed

    private void cboblockMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboblockMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboblockMousePressed

    private void cbomonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomonthActionPerformed
        // TODO add your handling code here:
        month();
    }//GEN-LAST:event_cbomonthActionPerformed

    private void cbomonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbomonthMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbomonthMouseClicked

    private void archievedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_archievedKeyTyped
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_archievedKeyTyped

    private void archievedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_archievedKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_archievedKeyPressed

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Saving the current process will not be opened again"+txtamount.getText()+"'","Accept",JOptionPane.YES_NO_OPTION);
        if (p==0){
         String sql = "INSERT INTO `monthlywater`(`block`, `houseno`, `meterno`, `tenantname`, `month`, `year`, `monthN`, `reading`, `units`, `rate`, `amount`, `status`) VALUES ('"+cboblock.getSelectedItem()+"','"+txtno.getText()+"','"+txtmeter.getText()+"','"+txtname.getText()+"','"+cbomonth.getSelectedItem()+"','"+year.getValue()+"','"+txtMN.getText()+"','"+txtcurrentread.getText()+"','"+rate.getText()+"','"+txtrate.getText()+"','"+txtamount.getText()+"','"+Completed+"')";
        try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
           JOptionPane.showMessageDialog(null, "'"+txtname.getText()+"' Saved Successfully");
           clear();
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
        }
    }//GEN-LAST:event_cmdSaveActionPerformed

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
            java.util.logging.Logger.getLogger(archieved.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(archieved.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(archieved.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(archieved.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new archieved().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable archieved;
    private javax.swing.JComboBox cboblock;
    private javax.swing.JComboBox cbomonth;
    private javax.swing.JButton cmdSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField rate;
    private javax.swing.JTextField txtMN;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtcurrentread;
    private javax.swing.JTextField txtmeter;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextField txtp;
    private javax.swing.JTextField txtrate;
    private com.toedter.calendar.JYearChooser year;
    // End of variables declaration//GEN-END:variables
}
