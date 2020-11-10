/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_socket;

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
public class ClientReceiveData implements Runnable{
    private Socket clientSocket;
    private String serverHost = "";
    private int serverPort = 4567;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ClientFrm clientFrm;
    public ClientReceiveData(ClientFrm clientFrm) {
        this.clientFrm = clientFrm;
        try {
            clientSocket = new Socket(serverHost,serverPort);
        } catch (IOException ex) {
            Logger.getLogger(ClientReceiveData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            Object o = ois.readObject();
            if(o instanceof Message){
                Message mesReceive = (Message) o;
                if(mesReceive.getMesType() == Message.MesType.LOGIN_SUCCESS){
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientReceiveData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientReceiveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
