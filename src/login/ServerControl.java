/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Message;

/**
 *
 * @author lamit
 */
public class ServerControl implements Runnable{
    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Message mesSend;
    public ServerControl(Socket clientSocket) {
        
        this.clientSocket = clientSocket;
        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()){
                Object o = ois.readObject();
                if(o instanceof Message){
                    Message mesRecei = (Message) o;
                    if(mesRecei.getMesType() == Message.MesType.LOGIN){
                        Account acc = (Account) mesRecei.getContent();
                        if(acc.getUsername().equals("1") && acc.getPassword().equals("1")){
                            mesSend = new Message(acc, Message.MesType.LOGIN_SUCCESS);
                        }else{
                            mesSend = new Message(acc, Message.MesType.LOGIN_FAIL);
                        }
                    }
                    oos.writeObject(mesSend);
                }
            }
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            try {
                ois.close();
                oos.close();
                clientSocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
