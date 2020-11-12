/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import model.Account;
import model.Message;
import ui.LoginFrm;

/**
 *
 * @author lamit
 */
public class LoginControl {
    private LoginFrm loginFrm;
    private ClientControl clientControl;
    public LoginControl(LoginFrm loginFrm){
        this.loginFrm = loginFrm;
        this.loginFrm.setVisible(true);
        clientControl = ClientControl.getInstance();
    }
    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginFrm.getUsername();
            String password = loginFrm.getPassword();
            Account account = new Account(username, password);
            Message mesSend = new Message(account, Message.MesType.LOGIN);
            clientControl.sendData(mesSend);
            Message mesRecei = clientControl.receiveData();
            if(mesRecei.getMesType() == Message.MesType.LOGIN_FAIL){
                loginFrm.showMessage("Login Fail");
            }else if(mesRecei.getMesType() == Message.MesType.LOGIN_SUCCESS){
                loginFrm.showMessage("Login Success");
            }
        }
        
    }
}

