/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

public class console_player extends Player implements Serializable {

    public console_player(String name) {
        this.name = name;
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
    public String get_name() {
        return this.name;
    }

    public void set_state(String state) {
        this.state.set_state(state);
    }

    @Override
    public String get_state() {
        return state.get_state();
    }

    @Override
    public int get_currentScore() {
        return 1;
    }

    @Override
    public int get_color() {
        return this.color;
    }

    @Override
    public void set_color(int id) {
        this.color = id;
    }

    @Override
    public void set_current_score(int score) {
        this.currentScore = score;
    }

    @Override
    public void change_number_of_shields(String kind_shield, int addition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getStrong_shield_num() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getWeak_shield_num() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
