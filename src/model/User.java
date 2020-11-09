/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author lamit
 */
public class User implements Serializable{
    private static final long serialVersionUID = 6529685098267757693L;
    private int id;
    private Account account;
    private int point;
    private boolean status;

    public User(int id, Account account, int point, boolean status) {
        this.id = id;
        this.account = account;
        this.point = point;
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
