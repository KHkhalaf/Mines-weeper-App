/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import java.util.Random;
import javafx.util.Pair;

public class Computer_Player extends Player implements Serializable {

    public Computer_Player(String name, int score, int color,int weak_shield,int strong_shield) {
        this.name = name;
        this.currentScore = score;
        this.color=color;
        this.strong_shield_num=strong_shield;
        this.weak_shield_num=weak_shield;
    }

    public void set_currentScore(int edited_score) {
        currentScore += edited_score;
    }

    public void set_name(String name) {
        this.name = name;
    }

    @Override
    public String get_name() {
        return name;
    }

    @Override
    public void set_current_score(int score) {
        currentScore += score;
    }

    @Override
    public int get_currentScore() {
        return this.currentScore;
    }

    public void set_state(String state) {
        this.state.set_state(state);
    }

    public String get_state() {
        return state.get_state();
    }

    public void set_color(int id) {
        color = id;
    }

    @Override
    public PlayerMove GetPlayerMove() {
        return new PlayerMove();
    }

    public Pair<Integer, Integer> generate_random_coords(Square[][] squares, int rows, int cols) {
        Random r = new Random();
        int x1 = r.nextInt(rows), y1 = r.nextInt(cols);
        while (true) {
            if ("Closed".equals(squares[x1][y1].getState().getStatus())) {
                return new Pair<>(x1, y1);
            }
            x1 = r.nextInt(rows);
            y1 = r.nextInt(cols);
        }
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
       return weak_shield_num;
    }

    @Override
    public void change_number_of_shields(String kind_shield, int addition) {
      if("strong".equals(kind_shield))
        this.strong_shield_num+=addition;
        else
        this.weak_shield_num+=addition;
    
    }

}
