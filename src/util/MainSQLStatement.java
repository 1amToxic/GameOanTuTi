/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author lamit
 */
public interface MainSQLStatement {
    String checkLogin = "select id from tblaccount where username = ? and password = ?";
    String insertAccount = "insert into tblaccount(username,password) values(?,?)";
    String insertUser = "insert into tbluser(accountid,point,status) values(?,?,?)";
    String updateUserOnline = "select * from tbluser where status = ?";
    String createGame = "insert into tblgame(userid,time) values(?,?) ";
    String updateGameChoice = "update into tblgame(choice) values(?) where id = ?";
    
}
