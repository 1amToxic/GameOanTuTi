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
public class ClientContorller {
    private String serverHost = "";
    private int serverPort =  4567;
    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ClientFrm clientFrm;
    public ClientContorller(ClientFrm clientFrm) {
        this.clientFrm = clientFrm;
        try {
            clientSocket = new Socket(serverHost,serverPort);
            ois = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientContorller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendData(Account acc){
        Message mes = new Message(acc, Message.MesType.LOGIN);
        try {
            oos.writeObject(mes);
        } catch (IOException ex) {
            Logger.getLogger(ClientContorller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void receiveData(){
        try {
            Object o = ois.readObject();
            if(o instanceof Message){
                Message mes = (Message) o;
                if(mes.getMesType() == Message.MesType.LOGIN_SUCCESS){
                    clientFrm.showMessage("Login Success");
                }else{
                    clientFrm.showMessage("Login Failed");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientContorller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientContorller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
