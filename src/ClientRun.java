
import client.ClientControl;
import client.LoginControl;
import ui.LoginFrm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lamit
 */
public class ClientRun {
    public static void main(String[] args) {
        LoginFrm loginFrm = new LoginFrm();
        System.out.println("1");
        ClientControl clientControl = new ClientControl();
        System.out.println("");
        clientControl.openConnection();
        System.out.println("2");
        LoginControl loginControl
                 = new LoginControl(loginFrm,clientControl);
        
    }
}
