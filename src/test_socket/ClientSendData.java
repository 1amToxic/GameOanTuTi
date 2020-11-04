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
public class ClientSendData implements Runnable{
    private int serverPort = 4567;
    private String serverHost = "";
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket clientSocket;
    private Message message;
    public ClientSendData(Message message) {
        this.message = message;
        try {
            clientSocket = new Socket(serverHost,serverPort);
            ois = new ObjectInputStream(ois);
            oos = new ObjectOutputStream(oos);
        } catch (IOException ex) {
            Logger.getLogger(ClientSendData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void run() {
        try {
            oos.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ClientSendData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
