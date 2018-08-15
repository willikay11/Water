/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.water;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;
/*import org.smslib.GatewayException;
import org.smslib.InboundMessage;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
*/
/**
 *
 * @author kamau
 */
public class Extract extends javax.swing.JFrame implements ActionListener,PropertyChangeListener {
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;
private Task task;
    int lid;
    int lidd;
    int id;
    int idd;
    Object g;
    String house;
    String Tenantname;
    String previousreading;
    String previousmonth;
    String reply;
    String h;
    String PhoneNumber;
    String b;
    String p;
    String monthp;
    String yearp;
    String year1;
    String specification;
    Timer timer;
    String Send;
    SerialModemGateway gateway;
    double textRecepient = 0;
    /**
     * Creates new form Extract
     */
    public Extract() {
        initComponents();
        gateway = Initializer.setUp(getComPort());
    }
    private String getComPort(){
        String number = modem.modemComPort;
        return number;
    }
    public class Task extends SwingWorker<Void, Void> {
             @Override
        public Void doInBackground() {  
            Random random = new Random();
            float progress = 0;
            //Initialize progress property.
            setProgress(0);
            //First primary Number
            if(BlockSend.getSelectedItem().equals("All")){
                String getlid = "SELECT `lid` FROM `savedtexts` WHERE `month` ='"+MonthSend.getSelectedItem()+"' and `year` = '"+txtyear.getText()+"'";
                try{
                pst = conn.prepareStatement(getlid);
                rs = pst.executeQuery();
                while (rs.first())
                {
                    lid=rs.getInt("lid");
                    System.out.println(lid);
                break;
                }
                  }catch(Exception e){
                  JOptionPane.showMessageDialog(null, e);
                  }
            //Last primary Number
                String getlidd = "SELECT `lid` FROM `savedtexts` WHERE `month` ='"+MonthSend.getSelectedItem()+"' and `year` = '"+txtyear.getText()+"'";
                try{
                pst = conn.prepareStatement(getlidd);
                rs = pst.executeQuery();
                while (rs.last())
                {
                    lidd=rs.getInt("lid");
                    lidd =lidd+1;
                    System.out.println(lidd);
                break;
                }
                  }catch(Exception e){
                  JOptionPane.showMessageDialog(null, e);
                  }
            }else{
                String getlid = "SELECT `lid` FROM `savedtexts` WHERE `block` = '"+BlockSend.getSelectedItem()+"' and `month` ='"+MonthSend.getSelectedItem()+"' and `year` = '"+txtyear.getText()+"'";
                try{
                pst = conn.prepareStatement(getlid);
                rs = pst.executeQuery();
                while (rs.first())
                {
                    lid=rs.getInt("lid");
                    System.out.println(lid);
                break;
                }
                  }catch(Exception e){
                  JOptionPane.showMessageDialog(null, e);
                  }
            //Last primary Number
                String getlidd = "SELECT `lid` FROM `savedtexts` WHERE `block` = '"+BlockSend.getSelectedItem()+"' and `month` ='"+MonthSend.getSelectedItem()+"' and `year` = '"+txtyear.getText()+"'";
                try{
                pst = conn.prepareStatement(getlidd);
                rs = pst.executeQuery();
                while (rs.last())
                {
                    lidd=rs.getInt("lid");
                    lidd =lidd+1;
                    System.out.println(lidd);
                break;
                }
                  }catch(Exception e){
                  JOptionPane.showMessageDialog(null, e);
                  }
            }
                float receipients = lidd-lid;
                float counter=0;
            for (int j=0;j<receipients;j++) {
                counter++;
                System.out.println("counter " + counter);
                        if(BlockSend.getSelectedItem().equals("All")){
                            Send="SELECT `sms`,`phonenumber`,`houseno`,`name` FROM `savedtexts` WHERE `month` ='"+MonthSend.getSelectedItem()+"' and `year` = '"+YearSend.getSelectedItem()+"'and `sent` = 'Unsent' order by houseno";
                        }else{
                            Send="SELECT `sms`,`phonenumber`,`houseno`,`name` FROM `savedtexts` WHERE `block` = '"+BlockSend.getSelectedItem()+"' and `month` ='"+MonthSend.getSelectedItem()+"' and `year` = '"+YearSend.getSelectedItem()+"'and `sent` = 'Unsent' order by houseno";
                        }
                        try{
                        rs = conn.prepareStatement(Send).executeQuery();
                        if(rs.next()){
                        reply=rs.getString("sms");
                        house=rs.getString("houseno");
                        Tenantname=rs.getString("name");
                        //System.out.println("reply: "+rs.getString("houseno"));
                        PhoneNumber=rs.getString("phonenumber");
                       // PhoneNumber = "0706020966";
                        System.out.println("Modem Information:");
                        System.out.println("  Manufacturer: " + gateway.getManufacturer());
                        System.out.println("  Model: " + gateway.getModel());
                        System.out.println("  Serial No: " + gateway.getSerialNo());
                        System.out.println("  SIM IMSI: " + gateway.getImsi());
                        System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
                        System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
                        System.out.println("  Sent to:" +rs.getString("houseno"));
                        OutboundMessage msg = new OutboundMessage(PhoneNumber, reply);
                        //Service.getInstance().sendMessage(msg);
                        Thread.sleep(random.nextInt(3000));
                        progress = ((float)(counter/receipients)*100);
                        setProgress(Math.min((int) progress, 100));
                        jLabelStatus.setText("Sending to: " + Tenantname);
                        String Update = "UPDATE `savedtexts` SET `sent`='Sent' WHERE `houseno` = '"+house+"' ";
                        try{
                            pst = conn.prepareStatement(Update);
                            pst.execute(Update);
                            table();
                        }catch(Exception e){JOptionPane.showMessageDialog(null, e);}
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e);
                    }   
                        
            }
            return null;
        }
            @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            cmdsend.setEnabled(true);
            jLabelStatus.setText("Finished");
            setCursor(null); //turn off the wait cursor
        }
 }
    public class Texts extends SwingWorker<Void, Void>{

        @Override
        public Void doInBackground() throws InterruptedException{
            Random random = new Random();
            int progress = 0;
            //Initialize progress property.
            setProgress(0);
            int counter=0;
            retrieve();
if("block".equals(specification)){
        String sqll = "SELECT `lid` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `block` ='"+cboblock.getSelectedItem()+"' and `year` = '"+txtyear.getText()+"'";
            try{
                    pst = conn.prepareStatement(sqll);
                    rs = pst.executeQuery();
                    while (rs.first())
                    {
                        lid=rs.getInt("lid");
                    break;
                    }
            }catch(Exception e){ 
            JOptionPane.showConfirmDialog(null, e);
            }
    String sql1 = "SELECT `lid` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `block` ='"+cboblock.getSelectedItem()+"' and `year` = '"+txtyear.getText()+"'";
     try{
            pst = conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            while (rs.last())
            {
                lidd=rs.getInt("lid");
                lidd++;
            break;
            }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Records not found");
    }
}
if(specification=="all"){
        String sqll = "SELECT `lid` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
    try{
            pst = conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            while (rs.first())
            {
                lid=rs.getInt("lid");
            break;
            }
    }catch(Exception e){    }
    String sql1 = "SELECT `lid` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
     try{
            pst = conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            while (rs.last())
            {
                lidd=rs.getInt("lid");
                lidd++;
            break;
            }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Records not found");
    }
    }
    float extracts = lidd-lid;
    for(int i=lid;i<lidd;i++){
        counter++;
        try{
            String Load = "SELECT `lid`, `block`, `houseno`, `meterno`, `tenantname`, `month`, `year`,  `reading`, `units`, `rate`, `amount` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"' and `lid` = '"+i+"'";
            rs = conn.prepareStatement(Load).executeQuery();
            
        if(rs.next()){
            String Amount=rs.getString("amount");
            String houseno = rs.getString("houseno");
            h=houseno;
            
            String block = rs.getString("block");
            b=block;
            String reading = rs.getString("reading");
            String month = rs.getString("month");
            String year = rs.getString("year");
            String rate = rs.getString("rate");
            String name = rs.getString("tenantname");
            Tenantname=name;
            String meterno = rs.getString("meterno");
            ///////////////////month
                    String January = "January";
        String February = "February";
        String March = "March";
        String April = "April";
        String May= "May";
        String June = "June";
        String July = "July";
        String August = "August";
        String September = "September";
        String October = "October";
        String November = "November";
        String December = "December";
        if(txtmonth.getText().equalsIgnoreCase(January)){p=December;}
        if(txtmonth.getText().equalsIgnoreCase(February)){p=January;}
        if(txtmonth.getText().equalsIgnoreCase(March)){p=February;}
        if(txtmonth.getText().equalsIgnoreCase(April)){p=March;}
        if(txtmonth.getText().equalsIgnoreCase(May)){p=April;}
        if(txtmonth.getText().equalsIgnoreCase(June)){p=May;}
        if(txtmonth.getText().equalsIgnoreCase(July)){p=June;}
        if(txtmonth.getText().equalsIgnoreCase(August)){p=July;}
        if(txtmonth.getText().equalsIgnoreCase(September)){p=August;}
        if(txtmonth.getText().equalsIgnoreCase(October)){p=September;}
        if(txtmonth.getText().equalsIgnoreCase(November)){p=October;}
        if(txtmonth.getText().equalsIgnoreCase(December)){p=November;}
            ///////////////////get
            String units = rs.getString("units");
                String sql="SELECT  `month`, `year`, `reading` FROM `monthlywater` WHERE `month`='"+p+"'and `year`='"+txtyear.getText()+"' and `houseno` ='"+h+"'";
            try {
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();
         while(rs.next())
          {
              String pmonth=rs.getString("month");
              previousmonth=pmonth;
              String preading = rs.getString("reading");
              previousreading=preading;
          }
            } catch (SQLException ex) {
            }
            ////////////////////reply
            reply="Block: "+block+" \nHouse No: "+houseno+" \nName: "+name+"  \nReading: "+reading+"  \nPrevious Reading was "+previousreading+" \nMonth :"+month+" \nPrevious Month of "+previousmonth+" \nYear: "+year+" \nUnits: "+units+" \nRate: "+rate+" \nAmount:Ksh "+Amount+". Please pay for for the bill via M-pesa to 0725524548. For feedback or queries please contact this number 0706020966.Thanks";
        /////////////////phonenumber
        String phone = "SELECT `phoneNumber` FROM `houses` WHERE `houseno` = '"+h+"'";
        try{
            rs = conn.prepareStatement(phone).executeQuery();
            if(rs.next()){
                PhoneNumber = rs.getString("phoneNumber");
            } 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        ///////////////////save
        String save ="INSERT INTO `savedtexts`(`sms`, `houseno`,`block`,`name`,`phonenumber`, `month`, `year`, `sent`) VALUES ('"+reply+"','"+h+"','"+b+"','"+Tenantname+"','"+PhoneNumber+"','"+txtmonth.getText()+"','"+txtyear.getText()+"','Unsent')";
          try{
             pst = conn.prepareStatement(save);
           pst.execute(save);
        }catch(Exception e){
              System.out.println(e);
        }
          
            } 
        }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        }
              
        Thread.sleep(random.nextInt(5000));
        progress = (int) ((float)(counter/extracts)*100);
        setProgress(Math.min((int) progress, 100));
        ExtractStatus.setText("Extracting Bill for: "+Tenantname+" "+counter+ "/"+extracts );
        ExtractStatus.setForeground(Color.GREEN);}
            return null;
        }
          @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            ExtractStatus.setText("Finished Extracting");
            ExtractStatus.setForeground(Color.GREEN);
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
         BlockSend.addItem(rs.getString("name"));
         }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    public void text(){
        for(int j=id;j<idd;j++){
            String sql="SELECT `sms`,`phonenumber` FROM `savedtexts` WHERE `lid` = '"+j+"' and `sent` = 'Unsent'";
        try{
            rs = conn.prepareStatement(sql).executeQuery();
        if(rs.next()){
        reply=rs.getString("sms");
        PhoneNumber=rs.getString("phonenumber");
        //PhoneNumber = "0706020966";
        }
        }catch(Exception e){}
        }
}

    final DateFormat df = new SimpleDateFormat("MMMMMMMMM");
    final DateFormat fd  = new SimpleDateFormat("yyyy");
    final DateFormat N  = new SimpleDateFormat("MM");
    Calendar c = Calendar.getInstance();
    java.util.Date date = c.getTime();
    public void take(){
String sql ="SELECT `lid` FROM `savedtexts` WHERE `month` ='"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.first())
            {
                id=rs.getInt("lid");
            break;
            }
String sqll = "SELECT `lid` FROM `savedtexts` WHERE `month` ='"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
try{
            pst = conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            while (rs.last())
            {
                idd=rs.getInt("lid");
                idd++;
            break;
            }
}catch(Exception e){}
}catch(Exception e){}

}
    public void calc(){
if(specification=="block"){
String sql = "SELECT COUNT(houseno) FROM `houses` WHERE `block` = '"+cboblock.getSelectedItem()+"'";
try{
    rs = conn.prepareStatement(sql).executeQuery();
if(rs.next()){
    txthouses.setText(rs.getString("COUNT(houseno)"));}

}catch(Exception e){
    System.out.println(e);
}
String sqll = "SELECT COUNT(houseno) FROM `monthlywater` WHERE `block` = '"+cboblock.getSelectedItem()+"' and `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
try{
    rs = conn.prepareStatement(sqll).executeQuery();
if(rs.next()){
    txt2.setText(rs.getString("COUNT(houseno)"));}
    txt2.setText(Integer.toString(Integer.parseInt(txt2.getText())*100));
}catch(Exception e){
    System.out.println(e);
}
txtcalc.setText(Integer.toString(Integer.parseInt(txt2.getText())/ Integer.parseInt(txthouses.getText())));
        String str = txtcalc.getText();
        int i = Integer.valueOf(str);
        progress.setValue(i);
}
if(specification=="all"){
String sql = "SELECT COUNT(houseno) FROM `houses` WHERE `block` = '"+cboblock.getSelectedItem()+"'";
try{
    rs = conn.prepareStatement(sql).executeQuery();
if(rs.next()){
    txthouses.setText(rs.getString("COUNT(houseno)"));}

}catch(Exception e){
    System.out.println(e);
}
String sqll = "SELECT COUNT(houseno) FROM `savedtexts` WHERE `block` = '"+cboblock.getSelectedItem()+"' and `month` = '"+txtmonth.getText()+"' and `sent` = 'Sent'";
try{
    rs = conn.prepareStatement(sqll).executeQuery();
if(rs.next()){
    txt2.setText(rs.getString("COUNT(houseno)"));}
    txt2.setText(Integer.toString(Integer.parseInt(txt2.getText())*100));
}catch(Exception e){
    System.out.println(e);
}
txtcalc.setText(Integer.toString(Integer.parseInt(txt2.getText())/ Integer.parseInt(txthouses.getText())));
        String str = txtcalc.getText();
        int i = Integer.valueOf(str);
        progress.setValue(i);
}
}
    public void retrieve(){
        if("block".equals(specification)){
          String sql = "SELECT  `sms`, `block`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` WHERE `block` = '"+cboblock.getSelectedItem()+"' and month ='"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
                try{
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    sms.setModel(DbUtils.resultSetToTableModel(rs));    
                }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                }  
        }
        if("all".equals(specification)){
          String sql = "SELECT  `sms`, `block`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` WHERE  month ='"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
                try{
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    sms.setModel(DbUtils.resultSetToTableModel(rs));    
                }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                }
        }
    }
    public void number(){
if("block".equals(specification)){
        String sqll = "SELECT `lid` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `block` ='"+cboblock.getSelectedItem()+"' and `year` = '"+txtyear.getText()+"'";
            try{
                    pst = conn.prepareStatement(sqll);
                    rs = pst.executeQuery();
                    while (rs.first())
                    {
                        lid=rs.getInt("lid");
                    break;
                    }
            }catch(Exception e){ 
            JOptionPane.showConfirmDialog(null, e);
            }
    String sql1 = "SELECT `lid` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `block` ='"+cboblock.getSelectedItem()+"' and `year` = '"+txtyear.getText()+"'";
     try{
            pst = conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            while (rs.last())
            {
                lidd=rs.getInt("lid");
                lidd++;
            break;
            }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Records not found");
    }
}
if(specification=="all"){
        String sqll = "SELECT `lid` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
    try{
            pst = conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            while (rs.first())
            {
                lid=rs.getInt("lid");
            break;
            }
    }catch(Exception e){    }
    String sql1 = "SELECT `lid` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"'";
     try{
            pst = conn.prepareStatement(sqll);
            rs = pst.executeQuery();
            while (rs.last())
            {
                lidd=rs.getInt("lid");
                lidd++;
            break;
            }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Records not found");
    }
}
    for(int i=lid;i<lidd;i++){
try{
    String sql = "SELECT `lid`, `block`, `houseno`, `meterno`, `tenantname`, `month`, `year`,  `reading`, `units`, `rate`, `amount` FROM `monthlywater` WHERE `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"' and `lid` = '"+i+"'";
    rs = conn.prepareStatement(sql).executeQuery();
if(rs.next()){
    String Amount=rs.getString("amount");
    String houseno = rs.getString("houseno");
    h=houseno;
    String block = rs.getString("block");
    b=block;
    String reading = rs.getString("reading");
    String month = rs.getString("month");
    String year = rs.getString("year");
    String rate = rs.getString("rate");
    String name = rs.getString("tenantname");
    Tenantname=name;
    String meterno = rs.getString("meterno");
    String units = rs.getString("units");
    get();
    reply="Block: "+block+" \nHouse No: "+houseno+" \nName: "+name+"  \nReading: "+reading+"  \nPrevious Reading was "+previousreading+" \nMonth :"+month+" \nPrevious Month of "+previousmonth+" \nYear: "+year+" \nUnits: "+units+" \nRate: "+rate+" \nAmount:Ksh "+Amount+". Please pay via M-pesa to 0725524548. For feedback or queries please contact this number 0706020966.Thanks";
   // calc();
    phonenumber();
    save();
} 
}catch(Exception e){}
    }
    }
    public void phonenumber(){
        conn = connectDB.ConnectDB();
        String sql = "SELECT `phoneNumber` FROM `houses` WHERE `houseno` = '"+h+"'";
        try{
            rs = conn.prepareStatement(sql).executeQuery();
            if(rs.next()){
                PhoneNumber = rs.getString("phoneNumber");
            } 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void save(){
      conn = connectDB.ConnectDB();
  String sql ="INSERT INTO `savedtexts`(`sms`, `houseno`,`block`,`name`,`phonenumber`, `month`, `year`, `sent`) VALUES ('"+reply+"','"+h+"','"+b+"','"+Tenantname+"','"+PhoneNumber+"','"+txtmonth.getText()+"','"+txtyear.getText()+"','Unsent')";
          try{
             pst = conn.prepareStatement(sql);
           pst.execute(sql);
        }catch(Exception e){
              System.out.println(e);
        }
  }
    public void get(){
      month();
    String sql="SELECT  `month`, `year`, `reading` FROM `monthlywater` WHERE `month`='"+p+"'and `year`='"+txtyear.getText()+"' and `houseno` ='"+h+"'";
            try {
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();
         while(rs.next())
          {
              String pmonth=rs.getString("month");
              previousmonth=pmonth;
              String preading = rs.getString("reading");
              previousreading=preading;
          }
            } catch (SQLException ex) {
            }
        }
    public void month(){
        String January = "January";
        String February = "February";
        String March = "March";
        String April = "April";
        String May= "May";
        String June = "June";
        String July = "July";
        String August = "August";
        String September = "September";
        String October = "October";
        String November = "November";
        String December = "December";
        if(txtmonth.getText().equalsIgnoreCase(January)){p=December;}
        if(txtmonth.getText().equalsIgnoreCase(February)){p=January;}
        if(txtmonth.getText().equalsIgnoreCase(March)){p=February;}
        if(txtmonth.getText().equalsIgnoreCase(April)){p=March;}
        if(txtmonth.getText().equalsIgnoreCase(May)){p=April;}
        if(txtmonth.getText().equalsIgnoreCase(June)){p=May;}
        if(txtmonth.getText().equalsIgnoreCase(July)){p=June;}
        if(txtmonth.getText().equalsIgnoreCase(August)){p=July;}
        if(txtmonth.getText().equalsIgnoreCase(September)){p=August;}
        if(txtmonth.getText().equalsIgnoreCase(October)){p=September;}
        if(txtmonth.getText().equalsIgnoreCase(November)){p=October;}
        if(txtmonth.getText().equalsIgnoreCase(December)){p=November;}
        }
    public void select(){
        if(BlockSend.getSelectedItem().equals("All")){
        String sql = "SELECT `sms`, `block`, `name`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` WHERE `sent` = 'Unsent'" ;
        try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            sms.setModel(DbUtils.resultSetToTableModel(rs));
            if(sms.getRowCount()>=1){
                cmdsend.setEnabled(true);
            }else{
                cmdsend.setEnabled(false);
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
        }else{
        String sql = "SELECT `sms`, `block`, `name`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` where `block` = '"+BlockSend.getSelectedItem()+"' and `sent` = 'Unsent'";
        try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            sms.setModel(DbUtils.resultSetToTableModel(rs));  
            if(sms.getRowCount()>=1){
                cmdsend.setEnabled(true);
            }else{
                cmdsend.setEnabled(false);
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
        }
    }   
    public void monthSend(){
if(BlockSend.getSelectedItem().equals("All")){
String sql = "SELECT `sms`, `block`, `name`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` where `month` = '"+MonthSend.getSelectedItem()+"' and `sent` = 'Unsent'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    sms.setModel(DbUtils.resultSetToTableModel(rs));  
            if(sms.getRowCount()>=1){
                cmdsend.setEnabled(true);
            }else{
                cmdsend.setEnabled(false);
            }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "SELECT `sms`, `block`, `name`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` where `block` = '"+BlockSend.getSelectedItem()+"' and `month` = '"+MonthSend.getSelectedItem()+"' and `sent` = 'Unsent'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    sms.setModel(DbUtils.resultSetToTableModel(rs));   
            if(sms.getRowCount()>=1){
                cmdsend.setEnabled(true);
            }else{
                cmdsend.setEnabled(false);
            }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
    public void yearSend(){
if(BlockSend.getSelectedItem().equals("All")){
String sql = "Select block, houseno, meterno, tenantname, month, year, reading, units, rate, amount, paid  from monthlywater where `month` = '"+MonthSend.getSelectedItem()+"' and `year` = '"+YearSend.getSelectedItem()+"'";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    sms.setModel(DbUtils.resultSetToTableModel(rs)); 
            if(sms.getRowCount()>=1){
                cmdsend.setEnabled(true);
            }else{
                cmdsend.setEnabled(false);
            }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}else{
String sql = "SELECT `sms`, `block`, `name`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` where `block` = '"+BlockSend.getSelectedItem()+"' and `month` = '"+MonthSend.getSelectedItem()+"' and `year` = '"+YearSend.getSelectedItem()+"' ";
try{
    pst = conn.prepareStatement(sql);
    rs = pst.executeQuery();
    sms.setModel(DbUtils.resultSetToTableModel(rs));
            if(sms.getRowCount()>=1){
                cmdsend.setEnabled(true);
            }else{
                cmdsend.setEnabled(false);
            }
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}
}
    public void table(){
        String sql = "SELECT `sms`, `block`, `name`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` where `block` = '"+BlockSend.getSelectedItem()+"' and `month` = '"+MonthSend.getSelectedItem()+"' and `year` = '"+YearSend.getSelectedItem()+"'and `sent` = 'Unsent' ";
            try{
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sms.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
    public void UpdateTable(){
                    ////////////Table
        String Loadtable;

        if( "All".equalsIgnoreCase(specification)){
        Loadtable = "SELECT `sms`, `block`, `name`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` WHERE `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"' "; 
            try{
                    pst = conn.prepareStatement(Loadtable);
                    rs = pst.executeQuery();
                    sms.setModel(DbUtils.resultSetToTableModel(rs));
                    MonthSend.setSelectedItem(txtmonth.getText());
                    YearSend.setSelectedItem(txtyear.getText());
                    BlockSend.setSelectedItem("All");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        if("block".equals(specification)){
        Loadtable = "SELECT `sms`, `block`, `name`, `houseno`, `phonenumber`, `month`, `year`, `sent` FROM `savedtexts` WHERE `block` = '"+cboblock.getSelectedItem()+"' and `month` = '"+txtmonth.getText()+"' and `year` = '"+txtyear.getText()+"' ";
            try{
                    pst = conn.prepareStatement(Loadtable);
                    rs = pst.executeQuery();
                    sms.setModel(DbUtils.resultSetToTableModel(rs));
                    MonthSend.setSelectedItem(txtmonth.getText());
                    YearSend.setSelectedItem(txtyear.getText());
                    BlockSend.setSelectedItem(cboblock.getSelectedItem());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtcalc = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txthouses = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnestate = new javax.swing.JRadioButton();
        cboblock = new javax.swing.JComboBox();
        btnblock = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        cmdExtract = new javax.swing.JButton();
        progress = new javax.swing.JProgressBar();
        lbextract = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboMonth = new javax.swing.JComboBox();
        chkmonth = new javax.swing.JCheckBox();
        txtmonth = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        chkyear = new javax.swing.JCheckBox();
        txtyear = new javax.swing.JTextField();
        ExtractStatus = new javax.swing.JLabel();
        cmdclose = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sms = new javax.swing.JTable();
        TextProgress = new javax.swing.JProgressBar();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        cmdsend = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        BlockSend = new javax.swing.JComboBox();
        MonthSend = new javax.swing.JComboBox();
        YearSend = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Criteria"));

        buttonGroup1.add(btnestate);
        btnestate.setText("Entire Estate");
        btnestate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnestateActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnblock);
        btnblock.setText("Block");
        btnblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnblockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnestate, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboblock, 0, 185, Short.MAX_VALUE)
                    .addComponent(btnblock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnblock)
                .addGap(6, 6, 6)
                .addComponent(cboblock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnestate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Date"));

        cmdExtract.setText("Extract");
        cmdExtract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExtractActionPerformed(evt);
            }
        });

        cboYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018" }));
        cboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearActionPerformed(evt);
            }
        });

        jLabel4.setText("Year");

        jLabel3.setText("Month");

        cboMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "May", "April", "June", "July", "August", "September", "October", "November", "December" }));
        cboMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonthActionPerformed(evt);
            }
        });

        chkmonth.setText("Select Month");
        chkmonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkmonthActionPerformed(evt);
            }
        });

        txtmonth.setFocusable(false);

        chkyear.setText("Select Year");
        chkyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkyearActionPerformed(evt);
            }
        });

        txtyear.setFocusable(false);

        ExtractStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        ExtractStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ExtractStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(chkmonth)
                                    .addGap(18, 18, 18)
                                    .addComponent(cboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(lbextract, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(chkyear))
                        .addContainerGap(33, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdExtract)
                        .addGap(58, 58, 58))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboMonth, cboYear, txtmonth, txtyear});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkmonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkyear)
                    .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(ExtractStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbextract, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(cmdExtract)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboMonth, cboYear, txtmonth, txtyear});

        cmdclose.setText("Close");
        cmdclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcloseActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Send Bills"));

        sms.setModel(new javax.swing.table.DefaultTableModel(
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
        sms.setRowHeight(25);
        jScrollPane1.setViewportView(sms);

        TextProgress.setForeground(new java.awt.Color(0, 0, 255));
        TextProgress.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TextProgressPropertyChange(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 0));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("<<CLICK TO SEND INDIVIDUAL SMS>>");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jLabelStatus.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(255, 0, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cmdsend.setText("Send");
        cmdsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsendActionPerformed(evt);
            }
        });

        BlockSend.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All" }));
        BlockSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockSendActionPerformed(evt);
            }
        });

        MonthSend.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "May", "April", "June", "July", "August", "September", "October", "November", "December" }));
        MonthSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthSendActionPerformed(evt);
            }
        });

        YearSend.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018" }));
        YearSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearSendActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Block");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Month");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Year");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(TextProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdsend))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BlockSend, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(MonthSend, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(YearSend, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BlockSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MonthSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YearSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(TextProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdsend)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(101, 87, 15));
        jLabel8.setText("SEND BILLS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtcalc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txthouses, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdclose)
                        .addGap(20, 20, 20))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtcalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txthouses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdclose))
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnestateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnestateActionPerformed
        // TODO add your handling code here:
        specification="all";
    }//GEN-LAST:event_btnestateActionPerformed

    private void btnblockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnblockActionPerformed
        // TODO add your handling code here:
        cboblock.setEnabled(true);
        specification="block";
    }//GEN-LAST:event_btnblockActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        conn = connectDB.ConnectDB();
        txt2.setVisible(false);
        txtcalc.setVisible(false);
        txthouses.setVisible(false);
        cmdsend.setEnabled(false);
        block();
        cboblock.setEnabled(false);
        cboMonth.setEnabled(false);
        cboYear.setEnabled(false);
        jLabelStatus.setText("Status: Idle");
        jLabelStatus.setForeground(Color.BLUE);
        ExtractStatus.setText("Status: Idle");
        ExtractStatus.setForeground(Color.BLUE);
        txtmonth.setText(df.format(date));
        txtyear.setText(fd.format(date));
    }//GEN-LAST:event_formWindowOpened

    private void cboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearActionPerformed
        // TODO add your handling code here:
        txtyear.setText(cboYear.getSelectedItem().toString());
        cmdExtract.setEnabled(true);
    }//GEN-LAST:event_cboYearActionPerformed

    private void cmdExtractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExtractActionPerformed
        // TODO add your handling code here:
            int p = JOptionPane.showConfirmDialog(null, "Extracting Bills for "+txtmonth.getText()+", "+txtyear.getText(),"Accept",JOptionPane.YES_NO_OPTION);
            if(p == 0){
            ext = new Texts();
            ext.addPropertyChangeListener(
                    new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent e) {
                            if (e.getPropertyName().equals("progress")) {
                                int newValue = (Integer) e.getNewValue();
                                progress.setValue(newValue);
                            } // end if
                        } // end method propertyChange
                    } // end anonymous inner class
            ); // end call to addPropertyChangeListener
            ext.execute();             
            }
    }//GEN-LAST:event_cmdExtractActionPerformed

    private void chkyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkyearActionPerformed
        // TODO add your handling code here:
        if(chkyear.isSelected()){
        cboYear.setEnabled(true);
        }else{
        cboYear.setEnabled(false);
        }
    }//GEN-LAST:event_chkyearActionPerformed

    private void chkmonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkmonthActionPerformed
        // TODO add your handling code here:
        if(chkmonth.isSelected()){
        cboMonth.setEnabled(true);
        }else{
        cboMonth.setEnabled(false);
        }
    }//GEN-LAST:event_chkmonthActionPerformed

    private void cboMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonthActionPerformed
        // TODO add your handling code here:
        txtmonth.setText(cboMonth.getSelectedItem().toString());
    }//GEN-LAST:event_cboMonthActionPerformed

    private void cmdcloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cmdcloseActionPerformed
    Task sender;
    Texts ext;
    private void cmdsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsendActionPerformed
        Object Decision = BlockSend.getSelectedItem();
        Object Month = MonthSend.getSelectedItem();
        Object Year = YearSend.getSelectedItem();
        int p = JOptionPane.showConfirmDialog(null, "Sending bills for "+Decision+" , "+Month+" and "+Year+"","Accept",JOptionPane.YES_NO_OPTION);
        if(p == 0){
        cmdsend.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        sender = new Task();
        sender.addPropertyChangeListener(this);
        sender.execute();        
        }
    }//GEN-LAST:event_cmdsendActionPerformed

    private void TextProgressPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TextProgressPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_TextProgressPropertyChange

    private void BlockSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlockSendActionPerformed
        // TODO add your handling code here:
        select();
    }//GEN-LAST:event_BlockSendActionPerformed

    private void MonthSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthSendActionPerformed
        // TODO add your handling code here:
        monthSend();
    }//GEN-LAST:event_MonthSendActionPerformed

    private void YearSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearSendActionPerformed
        // TODO add your handling code here:
        yearSend();
    }//GEN-LAST:event_YearSendActionPerformed

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
            java.util.logging.Logger.getLogger(Extract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Extract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Extract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Extract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Extract().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox BlockSend;
    private javax.swing.JLabel ExtractStatus;
    private javax.swing.JComboBox MonthSend;
    private javax.swing.JProgressBar TextProgress;
    private javax.swing.JComboBox YearSend;
    private javax.swing.JRadioButton btnblock;
    private javax.swing.JRadioButton btnestate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboMonth;
    private javax.swing.JComboBox cboYear;
    private javax.swing.JComboBox cboblock;
    private javax.swing.JCheckBox chkmonth;
    private javax.swing.JCheckBox chkyear;
    private javax.swing.JButton cmdExtract;
    private javax.swing.JButton cmdclose;
    private javax.swing.JButton cmdsend;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbextract;
    private javax.swing.JProgressBar progress;
    private javax.swing.JTable sms;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txtcalc;
    private javax.swing.JTextField txthouses;
    private javax.swing.JTextField txtmonth;
    private javax.swing.JTextField txtyear;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {

    }
        @Override
    public void propertyChange(PropertyChangeEvent evt) {
            if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            TextProgress.setValue(progress);
            //TextArea.append(String.format("Completed %d%% of task.\n", task.getProgress()));
        } 
    }
}
