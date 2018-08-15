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
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
public class Monthlywater extends javax.swing.JFrame {
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;
String completed = "Closed";
String p = " ";
String paid = "Unpaid";
String lastmonth = "";
String lastyear = "";
String nextmonth = "";
//DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    /**
     * Creates new form MonthLywater
     */
    public Monthlywater() {
        initComponents();
    }
public void closemonth(){
        String year = fd.format(date);
        for(int i=1;i<=12;i++){
            String month = "";
            String Nmonth = "";
            if(i==1){month = "January"; Nmonth = "February";}if(i==2){month = "February"; Nmonth = "March";}
            if(i==3){month = "March"; Nmonth = "April";}if(i==4){month = "April"; Nmonth = "May";}
            if(i==5){month = "May"; Nmonth = "June";}if(i==6){month = "June"; Nmonth = "July";}
            if(i==7){month = "July"; Nmonth = "August";}if(i==8){month = "August"; Nmonth = "September";}
            if(i==9){month = "September"; Nmonth = "October";}if(i==10){month = "October"; Nmonth = "November";}
            if(i==11){month = "November"; Nmonth = "December";}if(i==12){month = "December"; Nmonth = "January";}
          if(nextmonth.equals(month) && year.equals(lastyear)){
            int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to close "+nextmonth+"","Accept",JOptionPane.YES_NO_OPTION);
            if(p == 0){
                if("December".equals(month)){year = Integer.toString(Integer.parseInt(year)+ 1);}
            String sql = "UPDATE `lastmonth` SET `nextmonth` = '"+Nmonth+"',`lastmonth` = '"+month+"',`year` = '"+year+"' WHERE `lid` = 1";
            try{
                pst = conn.prepareStatement(sql);
                pst.execute(sql);
                JOptionPane.showMessageDialog(null, ""+nextmonth+" closed!");
                this.dispose();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            } 
        }          
        }
}
public void getlastmonth(){
    String sql = "SELECT * FROM `lastmonth`";
    try{
     pst = conn.prepareStatement(sql);
     rs = pst.executeQuery();
     while(rs.next())
     {
     lastmonth = rs.getString("lastmonth");
     nextmonth = rs.getString("nextmonth");
     lastyear = rs.getString("lastyear");
     }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Failed to retrieve information");
    }
}
public void saveblock(){
txtsum1.setText("0");
String sql = "INSERT INTO `chartblock`(`name`, `sum`, `month`, `monthn`, `year`) VALUES ('"+cboblock.getSelectedItem()+"','"+txtsum1.getText()+"','"+txtmonth.getText()+"','"+txtMN.getText()+"','"+txtyear.getText()+"')";
        try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
          }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
}
public void updateblock(){
String sql = "UPDATE `chartblock` SET`sum`='"+txtsum1.getText()+"' WHERE `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
        try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
          }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
}
public void saveentire(){
    txtsum.setText("0");
String sql = "INSERT INTO `chart`(`sum`, `month`, `monthN`,`year`) VALUES ('"+txtsum.getText()+"','"+txtmonth.getText()+"','"+txtMN.getText()+"','"+txtyear.getText()+"')";
        try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
          }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
}
public void updateentire(){
String sql = "UPDATE `chart` SET `sum`='"+txtsum.getText()+"' WHERE `month`='"+txtmonth.getText()+"'";
        try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
        }
}
public void sume(){
 String sql = "SELECT SUM(units) FROM `monthlywater` WHERE month = '"+txtmonth.getText()+"' and year = '"+txtyear.getText()+"'";
 try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtsum.setText(rs.getString("SUM(units)"));
 }
       }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
}
public void sumb(){
String sql = "SELECT SUM(units) FROM `monthlywater` WHERE `block` = '"+cboblock.getSelectedItem()+"' and month = '"+txtmonth.getText()+"' and year = '"+txtyear.getText()+"'";
 try{
 pst = conn.prepareStatement(sql);
 rs = pst.executeQuery();
 while(rs.next())
 {
 txtsum1.setText(rs.getString("SUM(units)"));
 }
       }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
}
public void invoice(){
 try{
            String para;
            para = txtno.getText();
            String para2 = txtmonth.getText();
            String para3 = txtyear.getText();
            FileInputStream fis = new FileInputStream("C:\\Users\\kamau\\Documents\\NetBeansProjects\\home water\\invoice2.jrxml");
            BufferedInputStream bis = new BufferedInputStream(fis);
            Map map = new HashMap();
            map.put("number", para);
            map.put("month", para2);
            map.put("year", para3);
            System.out.println(map);
            JasperReport jasperReport = (JasperReport)JasperCompileManager.compileReport(bis);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
            JasperViewer.viewReport(jasperPrint,false);
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
}
public void calc(){
txtcalc.setText(Integer.toString(Integer.parseInt(txt2.getText())/ Integer.parseInt(txthouses.getText())));
        String str = txtcalc.getText();
        int i = Integer.valueOf(str);
        jProgressBar2.setValue(i);
        if(i==0){
        saveentire();
        saveblock();
        }
}
public void progress(){
    String sql = "SELECT COUNT(block) from `monthlywater` WHERE `block` = '"+cboblock.getSelectedItem()+"' and `month` = '"+txtmonth.getText()+"' and `year`= '"+txtyear.getText()+"'";
    try{
     pst = conn.prepareStatement(sql);
     rs = pst.executeQuery();
     while(rs.next())
     {
     txt1.setText(rs.getString("COUNT(block)"));
     txt2.setText(Integer.toString(Integer.parseInt(txt1.getText())*100));
     calc();
     if ((Integer.parseInt(txt1.getText()) == Integer.parseInt(txthouses.getText()))){
     JOptionPane.showMessageDialog(null, "This block is done for month '"+txtmonth.getText()+"'");
     cmdSave.setEnabled(false);
     //water.setEnabled(false);
     }
     }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
}
public void house(){
String sql = "SELECT  `houses` FROM `block` WHERE `name` = '"+cboblock.getSelectedItem()+"'";
    try{
     pst = conn.prepareStatement(sql);
     rs = pst.executeQuery();
     while(rs.next())
     {
     txthouses.setText(rs.getString("houses"));
     txthouses.setText(Integer.toString(Integer.parseInt(txthouses.getText())));
     }
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
    }
}
public void water(){
    String sql = "Select block, houseno, meterno, tenantname, month, reading from monthlywater where `block` = '"+cboblock.getSelectedItem()+"' and `month` = '"+lastmonth+"' and `year` = '"+lastyear+"' order by houseno";
    try{
        cmdSave.setEnabled(true);
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        water.setModel(DbUtils.resultSetToTableModel(rs)); 
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}
public void clear(){
    txtname.setText("");
    txtamount.setText("");
    txtmeter.setText("");
    txtprevious.setText("");
    txtpreviousread.setText("");
    txtcurrentread.setText("");
    txtunits.setText("");
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
        txtmonth = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtyear = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtno = new javax.swing.JTextField();
        txtmeter = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtprevious = new javax.swing.JTextField();
        txtpreviousread = new javax.swing.JTextField();
        txtcurrentread = new javax.swing.JTextField();
        txtrate = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        water = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        txtunits = new javax.swing.JTextField();
        txtMN = new javax.swing.JTextField();
        txthouses = new javax.swing.JTextField();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel13 = new javax.swing.JLabel();
        txtcalc = new javax.swing.JTextField();
        txt1 = new javax.swing.JTextField();
        cboblock = new javax.swing.JComboBox();
        txtback = new javax.swing.JTextField();
        cmdSave = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txt2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtsum = new javax.swing.JTextField();
        txtsum1 = new javax.swing.JTextField();
        loading = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Monthly Water");

        jLabel2.setText("Block");

        txtmonth.setEditable(false);

        jLabel3.setText("Month");

        jLabel4.setText("Year");

        txtyear.setEditable(false);

        jLabel5.setText("House Number");

        jLabel6.setText("Meter Number");

        jLabel7.setText("Tenant Name");

        jLabel8.setText("Previous Month");

        jLabel9.setText("Previous Month Reading");

        jLabel11.setText("Current Month Reading");

        jLabel12.setText("Rate");

        txtno.setEditable(false);

        txtmeter.setEditable(false);
        txtmeter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmeterActionPerformed(evt);
            }
        });

        txtname.setEditable(false);

        txtprevious.setEditable(false);

        txtpreviousread.setEditable(false);

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

        water.setAutoCreateRowSorter(true);
        water.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        water.setRowHeight(25);
        water.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                waterMouseClicked(evt);
            }
        });
        water.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                waterMouseMoved(evt);
            }
        });
        water.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                waterAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        water.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                waterKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                waterKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(water);

        jLabel10.setText("Amount");

        txtamount.setEditable(false);

        txtunits.setEditable(false);
        txtunits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtunitsKeyTyped(evt);
            }
        });

        txthouses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthousesActionPerformed(evt);
            }
        });

        jProgressBar2.setForeground(new java.awt.Color(0, 0, 204));
        jProgressBar2.setStringPainted(true);

        jLabel13.setText("Completion");

        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });

        cboblock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboblockMouseClicked(evt);
            }
        });
        cboblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboblockActionPerformed(evt);
            }
        });

        cmdSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/water/save.PNG"))); // NOI18N
        cmdSave.setText("Save");
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });

        jLabel14.setText("Units");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/water/exit.PNG"))); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/water/clear.PNG"))); // NOI18N
        jButton1.setText("Issue Invoice");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Close Month");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(108, 108, 108)
                .addComponent(jButton1)
                .addGap(82, 82, 82)
                .addComponent(jButton2)
                .addGap(80, 80, 80))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/water/preloader.GIF"))); // NOI18N
        loading.setText("LOADING...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtno)
                            .addComponent(txtmeter)
                            .addComponent(txtname)
                            .addComponent(txtprevious)
                            .addComponent(txtpreviousread)
                            .addComponent(txtcurrentread)
                            .addComponent(txtrate)
                            .addComponent(txtamount, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txtunits, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboblock, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(loading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtback, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsum1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(cmdSave, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtcalc, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtsum, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(txthouses, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(txtmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboblock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtback, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loading))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtprevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtpreviousread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtcurrentread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtunits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdSave)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txthouses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtsum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtcalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    final DateFormat df = new SimpleDateFormat("MMMMMMMMM");
    final DateFormat fd  = new SimpleDateFormat("yyyy");
    final DateFormat N  = new SimpleDateFormat("MM");
    Calendar c = Calendar.getInstance();
    Date date = c.getTime();
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         conn = connectDB.ConnectDB();
         txtback.setVisible(false);
         txtMN.setVisible(false);
         txt1.setVisible(false);
         txt2.setVisible(false);
         txtcalc.setVisible(false);
         txthouses.setVisible(false);
         txtsum.setVisible(false);
         txtsum1.setVisible(false); 
         block();
         getlastmonth();
         Calendar now = Calendar.getInstance() ;  
         now.get(Calendar.MONTH);
         txtmonth.setText(nextmonth);
         txtyear.setText(fd.format(date));
         txtMN.setText(N.format(date));
         txtback.setText(Integer.toString(Integer.parseInt(txtMN.getText()) -1));
         
    }//GEN-LAST:event_formWindowOpened

    private void waterAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_waterAncestorMoved
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_waterAncestorMoved

    private void waterMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waterMouseMoved
        // TODO add your handling code here:
         
    }//GEN-LAST:event_waterMouseMoved

    private void waterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_waterKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_DOWN||evt.getKeyCode()==KeyEvent.VK_UP){
        int row = water.getSelectedRow();
        txtno.setText(water.getModel().getValueAt(row, 1).toString());
        txtmeter.setText(water.getModel().getValueAt(row, 2).toString());
        txtname.setText(water.getModel().getValueAt(row, 3).toString());
        txtprevious.setText(water.getModel().getValueAt(row, 4).toString());
        txtpreviousread.setText(water.getModel().getValueAt(row, 5).toString());
        }
    }//GEN-LAST:event_waterKeyPressed

    private void waterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waterMouseClicked
        // TODO add your handling code here:
        int row = water.getSelectedRow();
        txtno.setText(water.getModel().getValueAt(row, 1).toString());
        txtmeter.setText(water.getModel().getValueAt(row, 2).toString());
        txtname.setText(water.getModel().getValueAt(row, 3).toString());
        txtprevious.setText(water.getModel().getValueAt(row, 4).toString());
        txtpreviousread.setText(water.getModel().getValueAt(row, 5).toString());
    }//GEN-LAST:event_waterMouseClicked

    private void txtcurrentreadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcurrentreadKeyTyped
        // TODO add your handling code here:
      char c=evt.getKeyChar();
      if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)){evt.consume();}
    }//GEN-LAST:event_txtcurrentreadKeyTyped

    private void txtunitsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtunitsKeyTyped
        // TODO add your handling code here:
         
    }//GEN-LAST:event_txtunitsKeyTyped

    private void waterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_waterKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_waterKeyTyped

    private void txtcurrentreadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcurrentreadKeyReleased
        // TODO add your handling code here:
          txtunits.setText(Integer.toString(Integer.parseInt(txtcurrentread.getText()) - Integer.parseInt(txtpreviousread.getText())));
          txtamount.setText(Integer.toString(Integer.parseInt(txtunits.getText())* Integer.parseInt(txtrate.getText())));
    }//GEN-LAST:event_txtcurrentreadKeyReleased

    private void txtcurrentreadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcurrentreadKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtcurrentreadKeyPressed

    private void txtcurrentreadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcurrentreadFocusLost
        // TODO add your handling code here:
        int a = (Integer.parseInt(txtpreviousread.getText()));
        int b = (Integer.parseInt(txtcurrentread.getText()));
        if(a>b){
        JOptionPane.showMessageDialog(null, "Previous Reading can't be greater than current Reading");
       clear();
        }
    }//GEN-LAST:event_txtcurrentreadFocusLost

    private void txthousesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthousesActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txthousesActionPerformed

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1ActionPerformed

    private void cboblockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboblockMouseClicked
        // TODO add your handling code here:
       house();
       water();
       progress();
       sumb();
    }//GEN-LAST:event_cboblockMouseClicked

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Saving the current process will not be opened again"+txtamount.getText()+"'","Accept",JOptionPane.YES_NO_OPTION);
        if (p==0){
         String sql = "INSERT INTO `monthlywater`(`block`, `houseno`, `meterno`, `tenantname`, `month`, `year`, `monthN`, `reading`, `units`, `rate`, `amount`, `status`, `paid`) VALUES ('"+cboblock.getSelectedItem()+"','"+txtno.getText()+"','"+txtmeter.getText()+"','"+txtname.getText()+"','"+txtmonth.getText()+"','"+txtyear.getText()+"','"+txtMN.getText()+"','"+txtcurrentread.getText()+"','"+txtunits.getText()+"','"+txtrate.getText()+"','"+txtamount.getText()+"','"+completed+"','"+paid+"')";
        try{
           pst = conn.prepareStatement(sql);
           pst.execute(sql);
           JOptionPane.showMessageDialog(null, "Save Succesful");
           progress();
           sume();updateentire();
           sumb();updateblock();
           clear();
           int a =JOptionPane.showConfirmDialog(null, "Save succesful."+txtname.getText()+"'Do you want to print an invoice","Invoice",JOptionPane.YES_NO_OPTION);
           if(a==0){invoice();}else{}
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
        }
    }//GEN-LAST:event_cmdSaveActionPerformed

    private void txtrateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        invoice open = new invoice();
        open.setVisible(true);
        open.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboblockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboblockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboblockActionPerformed

    private void txtmeterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmeterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmeterActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        closemonth();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Monthlywater.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Monthlywater.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Monthlywater.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Monthlywater.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Monthlywater().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboblock;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel loading;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txtMN;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtback;
    private javax.swing.JTextField txtcalc;
    private javax.swing.JTextField txtcurrentread;
    private javax.swing.JTextField txthouses;
    private javax.swing.JTextField txtmeter;
    private javax.swing.JTextField txtmonth;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextField txtprevious;
    private javax.swing.JTextField txtpreviousread;
    private javax.swing.JTextField txtrate;
    private javax.swing.JTextField txtsum;
    private javax.swing.JTextField txtsum1;
    private javax.swing.JTextField txtunits;
    private javax.swing.JTextField txtyear;
    private javax.swing.JTable water;
    // End of variables declaration//GEN-END:variables
}
