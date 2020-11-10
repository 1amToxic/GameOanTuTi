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
import model.Message;

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

    public ServerControl(Socket socket) {
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
            while(!Thread.currentThread().isInterrupted()){
                Object o = ois.readObject();
                if(o instanceof Message){
                    Message mesReceive = (Message) o;
                    checkMesType(mesReceive.getMesType());
                    Message mesSend = createMessage();
                    oos.writeObject(mesSend);
                }
            }
            Thread.sleep(100);
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
    private void checkMesType(Message.MesType mesType){
        switch(mesType){
            case LOGIN:{
                
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
