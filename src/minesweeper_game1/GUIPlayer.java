/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import javafx.util.Pair;

public class GUIPlayer extends Player implements Serializable {

    public GUIPlayer(String name, int score, int color,int weak_shield,int strong_shield) {
        this.color = color;
        this.name = name;
        this.currentScore = score;
        this.strong_shield_num=strong_shield;
        this.weak_shield_num=weak_shield;
    }

    @Override
    public String get_name() {
        return name;
    }

    @Override
    public int get_currentScore() {
        return this.currentScore;
    }

    @Override
    public void set_current_score(int score) {
        this.currentScore += score;
    }

    public void set_color(int color) {
        this.color = color;
    }

    public void set_state(String state) {
        this.state.set_state(state);
    }

    public String get_state() {
        return state.get_state();
    }

    @Override
    public PlayerMove GetPlayerMove() {
        return new PlayerMove();
    }

    @Override
    public void set_name(String name) {
        this.name = name;
    }

    @Override
    public int get_color() {
        return this.color;
    }
 @Override
    public int getStrong_shield_num() {
     return this.strong_shield_num;
    }

    @Override
    public int getWeak_shield_num() {
     return this.weak_shield_num;
    }

    @Override
    public void change_number_of_shields(String kind_shield, int addition) {
      if(kind_shield=="strong")
        this.strong_shield_num+=addition;
        else
        this.weak_shield_num+=addition;
        }
    
}
