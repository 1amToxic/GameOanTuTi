/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoantuti;

import login.ServerThreadMain;

/**
 *
 * @author lamit
 */
public class GameOanTuTi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Thread thread = new Thread(new ServerThreadMain());
        thread.start();
    }
    
}
