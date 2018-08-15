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
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.TableCellRenderer;
import net.proteanit.sql.DbUtils;
public class payments extends javax.swing.JFrame {
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;
DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
String N = "";
    /**
     * Creates new form payments
     */
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
public void get(){
 for (int i = 0; i < payments.getRowCount(); i++) {
txtpaid.setText(payments.getModel().getValueAt(i, 10).toString());
txtunpaid.setText(payments.getModel().getValueAt(i, 10).toString());
//if(txtpaid.getText().equals("Paid")){
//payments.setBackground(Color.red);
//}else{
////if(txtunpaid.getText().equals("Unpaid")){
////payments.setBackground(Color.blue);
////}
//}
 }}
public void go(){
Object obj = cboblock.getSelectedItem();
Object object = cbomonth.getSelectedItem();
 txtblock.setText(obj.toString());
 txtblock.setForeground(Color.blue);
 txtmonth.setText(object.toString());
 txtmonth.setForeground(Color.blue);
}
public void sum(){
txtTotal.setText(Double.toString(Double.parseDouble(txtTP.getText()) + Double.parseDouble(txtUP.getText())));
txtTotal.setForeground(Color.green);
}
public void total(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Paid'" ;
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtTP.setText(rs.getString("SUM(`amount`)"));
 go();
 sum();
 if(txtTP.getText().equals(N)){
 txtTP.setText("0");
go();
sum();
 }else{}
 }
}catch(Exception e){
//JOptionPane.showMessageDialog(null, e);
    txtTP.setText("0");
}
}else{
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Paid' and `block` = '"+cboblock.getSelectedItem()+"'";
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtTP.setText(rs.getString("SUM(`amount`)"));
 go();
 sum();
 if(txtTP.getText().equals(N)){
 txtTP.setText("0");
go();
sum();
 }else{}
 }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
public void totalmonth(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Paid' and `month` = '"+cbomonth.getSelectedItem()+"'" ;
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtTP.setText(rs.getString("SUM(`amount`)"));
go();
sum();
 if(txtTP.getText().equals(N)){
 txtTP.setText("0");
 go();
 sum();
 }else{}
 }
}catch(Exception e){
//JOptionPane.showMessageDialog(null, e);
    txtTP.setText("0");
}
}else{
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Paid' and `block` = '"+cboblock.getSelectedItem()+"'and `month` = '"+cbomonth.getSelectedItem()+"'";
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtTP.setText(rs.getString("SUM(`amount`)"));
 go();
 sum();
 if(txtTP.getText().equals(N)){
 txtTP.setText("0");
 go();
 sum();
 }else{}
 }
}catch(Exception e){
//JOptionPane.showMessageDialog(null, e);
    txtTP.setText("0");
}
}
}
public void totalyear(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Paid' and `month` = '"+cbomonth.getSelectedItem()+"' and `year` = '"+cboyear.getSelectedItem()+"'" ;
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtTP.setText(rs.getString("SUM(`amount`)"));
 go();
 sum();
 if(txtTP.getText().equals(N)){
 txtTP.setText("0");
go();
sum();
 }else{}
 }
}catch(Exception e){
//JOptionPane.showMessageDialog(null, e);
    txtTP.setText("0");
}
}else{
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Paid' and `block` = '"+cboblock.getSelectedItem()+"'and `month` = '"+cbomonth.getSelectedItem()+"' and `year` = '"+cboyear.getSelectedItem()+"'";
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtTP.setText(rs.getString("SUM(`amount`)"));
 go();
 sum();
 if(txtTP.getText().equals(N)){
 txtTP.setText("0");
go();
sum();
 }else{}
 }
}catch(Exception e){
//JOptionPane.showMessageDialog(null, e);
    txtTP.setText("0");
}
}
}
public void select(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount, paid from monthlywater" ;
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    payments.setModel(DbUtils.resultSetToTableModel(rs));
    get();
    total();
    total1();
   // payments.setBackground(Color.red);
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount, paid from monthlywater where `block` = '"+cboblock.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    payments.setModel(DbUtils.resultSetToTableModel(rs));   
    total();
    total1();
    get();
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
public void month(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount , paid  from monthlywater where `month` = '"+cbomonth.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    payments.setModel(DbUtils.resultSetToTableModel(rs));  
    totalmonth();
    totalmonth1();
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount, paid  from monthlywater where `block` = '"+cboblock.getSelectedItem()+"' and `month` = '"+cbomonth.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    payments.setModel(DbUtils.resultSetToTableModel(rs));    
    totalmonth();
    totalmonth1();
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
public void year(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount, paid  from monthlywater where `month` = '"+cbomonth.getSelectedItem()+"' and `year` = '"+cboyear.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    payments.setModel(DbUtils.resultSetToTableModel(rs)); 
    totalyear();
    totalyear1();
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount, paid  from monthlywater where `block` = '"+cboblock.getSelectedItem()+"' and `month` = '"+cbomonth.getSelectedItem()+"' and `year` = '"+cboyear.getSelectedItem()+"' ";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    payments.setModel(DbUtils.resultSetToTableModel(rs)); 
    totalyear();
    totalyear1();
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
public void total1(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Unpaid'" ;
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtUP.setText(rs.getString("SUM(`amount`)"));
if(txtUP.getText().equals(N)){
 txtUP.setText("0");
 }else{}
 }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Unpaid' and `block` = '"+cboblock.getSelectedItem()+"'";
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtUP.setText(rs.getString("SUM(`amount`)"));
 if(txtUP.getText().equals(N)){
 txtUP.setText("0");
 }else{}
 }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e); 
}
}
}
public void totalmonth1(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Unpaid' and `month` = '"+cbomonth.getSelectedItem()+"'" ;
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtUP.setText(rs.getString("SUM(`amount`)"));
 if(txtUP.getText().equals(N)){
 txtUP.setText("0");
 }else{}
 }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Unpaid' and `block` = '"+cboblock.getSelectedItem()+"'and `month` = '"+cbomonth.getSelectedItem()+"'";
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtUP.setText(rs.getString("SUM(`amount`)"));
 if(txtUP.getText().equals(N)){
 txtUP.setText("0");
 }else{}
 }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
public void totalyear1(){
if(cboblock.getSelectedItem().equals("All")){
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Unpaid' and `month` = '"+cbomonth.getSelectedItem()+"' and `year` = '"+cboyear.getSelectedItem()+"'" ;
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtUP.setText(rs.getString("SUM(`amount`)"));
 if(txtUP.getText().equals(N)){
 txtUP.setText("0");
 }else{}
 }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "SELECT SUM(`amount`) FROM `monthlywater` WHERE `paid` = 'Unpaid' and `block` = '"+cboblock.getSelectedItem()+"'and `month` = '"+cbomonth.getSelectedItem()+"' and `year` = '"+cboyear.getSelectedItem()+"'";
try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtUP.setText(rs.getString("SUM(`amount`)"));
 if(txtUP.getText().equals(N)){
 txtUP.setText("0");
 }else{}
 }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
    public payments() {
        initComponents();
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
        cboblock = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbomonth = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cboyear = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        payments = new javax.swing.JTable();
        txtpaid = new javax.swing.JTextField();
        txtunpaid = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtUP = new javax.swing.JTextField();
        txtTP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtblock = new javax.swing.JTextField();
        txtmonth = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        cmdClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Payments");

        cboblock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        cboblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboblockActionPerformed(evt);
            }
        });

        jLabel2.setText("Block");

        cbomonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March ", "May", "April", "June ", "July", "August", "September", "October", "November", "December" }));
        cbomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomonthActionPerformed(evt);
            }
        });

        jLabel3.setText("Month");

        cboyear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015" }));
        cboyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboyearActionPerformed(evt);
            }
        });

        jLabel4.setText("Year");

        payments.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(payments);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 51, 255)));

        jLabel5.setText("Total(Paid)");

        txtTotal.setEditable(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel7.setText("Total Due");

        txtUP.setEditable(false);

        txtTP.setEditable(false);

        jLabel6.setText("Total(Unpaid)");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("For:");

        txtblock.setEditable(false);

        txtmonth.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtTP, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtUP, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmonth)
                    .addComponent(txtblock))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtUP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtblock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/water/exit.PNG"))); // NOI18N
        cmdClose.setText("Close");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtunpaid, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtpaid, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdClose)
                .addGap(28, 28, 28))
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
                    .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtunpaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        txtpaid.setVisible(false);
        txtunpaid.setVisible(false);
        block();
    }//GEN-LAST:event_formWindowOpened

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

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
            java.util.logging.Logger.getLogger(payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new payments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboblock;
    private javax.swing.JComboBox cbomonth;
    private javax.swing.JComboBox cboyear;
    private javax.swing.JButton cmdClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable payments;
    private javax.swing.JTextField txtTP;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUP;
    private javax.swing.JTextField txtblock;
    private javax.swing.JTextField txtmonth;
    private javax.swing.JTextField txtpaid;
    private javax.swing.JTextField txtunpaid;
    // End of variables declaration//GEN-END:variables
}
