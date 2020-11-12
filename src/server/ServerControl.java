/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Message;
import model.User;

/**
 *
 * @author lamit
 */
public class ServerControl implements Runnable{
    private String serverHost;
    private int serverPort;
    private Socket clientSocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    ServerDao serverDao;
    public ServerControl(Socket socket) {
        this.serverDao = new ServerDao();
        this.clientSocket = socket;
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
            System.out.println("0");
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("1");
                Object o = ois.readObject();
                if(o instanceof Message){
                    Message mesReceive = (Message) o;
                    checkMesType(mesReceive);
//                    Message mesSend = createMessage();
//                    oos.writeObject(mesSend);
                }
            }
            Thread.sleep(100);
            clientSocket.close();
        } catch (Exception ex){
            try {
                ois.close();
                oos.close();
                clientSocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    private void checkMesType(Message mesReceive){
        switch(mesReceive.getMesType()){
            case LOGIN:{
                Account acc = (Account) mesReceive.getObject();
                System.out.println(acc);
                User user = serverDao.checkLogin((Account) mesReceive.getObject());
                System.out.println(user.getAccount().getUsername()+"-"+user.getAccount().getPassword());
                if(user == null){
                    try {
                        oos.writeObject(new Message(user, Message.MesType.LOGIN_FAIL));
                    } catch (IOException ex) {
                        Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    try {
                        oos.writeObject(new Message(user, Message.MesType.LOGIN_SUCCESS));
                    } catch (IOException ex) {
                        Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
            case REGISTER:{
                
            }
        }
    }
    private Message createMessage(){
        Message mes = new Message();
        return mes;
    }
    
}
