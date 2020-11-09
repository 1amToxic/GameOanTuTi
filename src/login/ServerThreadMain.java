/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class ServerThreadMain implements Runnable {

    private ServerSocket myServer;
    private int port = 4567;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    Socket clientSocket;

    public ServerThreadMain() {
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                try {
                    myServer = new ServerSocket(port);
                } catch (IOException ex) {
                    Logger.getLogger(ServerThreadMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("1");
                        clientSocket = myServer.accept();
                        System.out.println("2");
                        System.out.println(clientSocket.getRemoteSocketAddress());
                        ServerControl sc = new ServerControl(clientSocket);
                        new Thread(sc).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Thread.sleep(100);
                clientSocket.close();
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerThreadMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServerThreadMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
