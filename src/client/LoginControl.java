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
    ClientControl control;
    public LoginControl(LoginFrm loginFrm) {
        this.loginFrm = loginFrm;
        control = new ClientControl();
        loginFrm.setAction(new ButtonListener());
    }
    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            String username = loginFrm.getName();
            String password = loginFrm.getPassword();
            Account account = new Account(username,password);
            Message mesSend = new Message(account, Message.MesType.LOGIN);
            control.sendData(mesSend);
            Message mesRecei = control.receiveData();
            if(mesRecei!=null){
                switch(mesRecei.getMesType()){
                    case LOGIN_FAIL:{
                        loginFrm.showMessage("Login Fail");
                        break;
                    }
                    case LOGIN_SUCCESS:{
                        loginFrm.showMessage("Login Success");
                        break;
                    }
                    default: break;
                }
            }
        }
        
    }
}
