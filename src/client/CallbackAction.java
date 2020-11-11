/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import model.Message;

/**
 *
 * @author lamit
 */
public interface CallbackAction {
    void callbackMessage(Message mes);
}
