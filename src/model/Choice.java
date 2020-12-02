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
public class Choice implements Serializable{
    private static final long serialVersionUID = 6529685098267757691L;
    private User user;
    private Game game;
    private ChoiceType choice;
    private int result;

    public Choice(User user, Game game, ChoiceType choice, int result) {
        this.user = user;
        this.game = game;
        this.choice = choice;
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ChoiceType getChoice() {
        return choice;
    }

    public void setChoice(ChoiceType choice) {
        this.choice = choice;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    
}
enum ChoiceType{
    BUA,
    KEO,
    BAO
}