/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.water;

import javax.swing.JOptionPane;
import org.smslib.Library;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class Initializer {
static SerialModemGateway gateway;
    public static SerialModemGateway setUp(String portNumber)
    {
        System.out.println(Library.getLibraryDescription());
        System.out.println("Version: " + Library.getLibraryVersion());

        gateway = new SerialModemGateway("modem.com" + portNumber, "COM" + portNumber, 115200, "", "");
      

        gateway.setInbound(true);
        gateway.setOutbound(true);
        gateway.setFrom("Burundi");
                try {
          //Service.getInstance().addGateway(gateway);
          //Service.getInstance().startService();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, portNumber);
        }
                return gateway;
    }
}