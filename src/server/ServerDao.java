/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Choice;
import model.Game;
import model.User;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ServerDao{

    private Connection conn;
    public ServerDao() {
        conn = ConnectDatabase.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public User checkLogin(Account acc) {
        User user = new User();
        try {
            PreparedStatement pre = conn.prepareStatement(Usage.findAccount);
            pre.setString(1, acc.getUsername());
            pre.setString(2, acc.getPassword());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                user.setAccount(acc);
                user.setStatus(true);
            }
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return user;
    }

    public boolean register(Account acc) {
        boolean isSuccess = false;
        try {
            PreparedStatement pre = conn.prepareStatement(Usage.insertAccount, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, acc.getUsername());
            pre.setString(2, acc.getPassword());
            int lastRowId = pre.executeUpdate();
            PreparedStatement pre1 = conn.prepareStatement(Usage.insertUser);
            User user = new User(0, false);
            pre1.setInt(1, lastRowId);
            pre1.setInt(2, 0);
            pre1.setBoolean(3, false);
            isSuccess = true;
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return isSuccess;
    }

    public Game insertGame() {
        Game game = new Game();
        try {
            PreparedStatement pre = conn.prepareStatement(Usage.insertGame, Statement.RETURN_GENERATED_KEYS);
            Date date = new Date();
            pre.setObject(1, date.toInstant().atZone(ZoneId.of("Vietnam")).toLocalDate());
            int idGame = pre.executeUpdate();
            game.setTimeCreated(date);
            game.setId(idGame);
            conn.commit();
            return game;
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return game;
    }

    public void insertChoice(Choice choice) {
        try {
            PreparedStatement pre = conn.prepareStatement(Usage.insertChoice);
            pre.setInt(0, 0);
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> listUser = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareStatement(Usage.getAllUser);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                //u._point,u._status,a._username
                int point = rs.getInt("u._point");
                boolean status = rs.getBoolean("u._status");
                String username = rs.getString("a._username");
                User user = new User(new Account(username), point, status);
                listUser.add(user);
            }
            Collections.sort(listUser,new CompareUser());
            int rank = 1;
            for(User user : listUser){
                user.setRank(rank++);
            }
            conn.commit();
            return listUser;
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return listUser;
    }

    class CompareUser implements Comparator<User> {

        @Override
        public int compare(User t, User t1) {
            return t.getPoint() > t1.getPoint() ? 1 : -1;
        }

    }
}
