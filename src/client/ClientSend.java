/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;

/**
 *
 * @author lamit
 */
public class ClientSend implements Runnable{
    ObjectOutputStream oos;
    Message mesSend;
    public ClientSend(ObjectOutputStream oos,Message mesSend) {
        this.oos = oos;
        this.mesSend = mesSend;
    }
    
    @Override
    public void run() {
        try {
            oos.writeObject(mesSend);
        } catch (IOException ex) {
            Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
