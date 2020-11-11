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
            System.out.println("1");
            String username = loginFrm.getName();
            String password = loginFrm.getPassword();
            Account account = new Account(username,password);
            Message mesSend = new Message(account, Message.MesType.LOGIN);
            System.out.println("2");
            control.sendData(mesSend);
            System.out.println("3");
            Message mesRecei = control.receiveData();
            System.out.println("4");
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
