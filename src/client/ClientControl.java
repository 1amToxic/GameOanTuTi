/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import model.Message;
import ui.LoginFrm;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ClientControl{
    private Socket clientSocket;
    private String serverHost;
    private int serverPort;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    public ClientControl(){
        serverHost = Usage.serverHost;
        serverPort = Usage.port;
        try {
            clientSocket = new Socket(serverHost,serverPort);
            ois = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendData(Message mesSend){
        try {
            oos.writeObject(mesSend);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Message receiveData(){
        try {
            Object o = ois.readObject();
            if(o instanceof Message){
                Message mesReceive =(Message) o;
                return mesReceive;
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private void checkMesReceiveType(Message.MesType mesType){
        switch(mesType){
            case LOGIN_FAIL:{
            }
            case LOGIN_SUCCESS:{
                
            }
        }
    }
}
