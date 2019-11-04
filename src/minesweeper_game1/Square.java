/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import java.util.*;

public class Square  implements Serializable {

    private int x, y, cnt_mine, color;
    private Mine mine;
    private List<Player> playersMoves;
    private SquareStatus state;

    private Shield shield;
    private boolean is_shield;
    private String weak_strong;
    
    public Square() {
        
        x = y = cnt_mine = 0;
        mine=new Mine();
        List<Player>playersMoves=new  ArrayList<Player>();
        state=new SquareStatus();
        is_shield=false;
        shield=null;
    }
    public void setWeak_strong(String weak_strong) {
        this.weak_strong = weak_strong;
    }

    public String getWeak_strong() {
        return weak_strong;
    }
       
    public void setShield(Shield shield) {
        
        this.shield = shield;
    }

    
    public Shield getShield() {
        return shield;
    }
    
    public boolean getis_Shield()
    {
    return this.is_shield;
    }
    
    public void setis_Shield(boolean shield_ok) {
        this.is_shield= shield_ok;
    }
   
    public void set_mine(String ID) {
        this.mine.set_ID(ID);
    }

    public void set_cnt_mine(int cnt_mine) {
        this.cnt_mine = cnt_mine;
    }

    public void set_x(int x) {
        this.x = x;
    }

    public void set_y(int y) {
        this.y = y;
    }

    public int get_x() {
        return this.x;
    }

    public int get_y() {
        return this.y;
    }

    public int get_color() {
        return this.color;
    }

    public void set_color(int color) {
        this.color = color;
    }

    public int get_cnt_mine() {
        return this.cnt_mine;
    }

    public SquareStatus getState() {

        return this.state;
    }

    public void set_state(String state) {
        this.state.setstatus(state);
    }

    public int IsMine() {
        if ("mine".equals(mine.get_ID())) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setColored(boolean colored) {
        this.state.setColored(colored);
    }

    public boolean IsColored() {
        return this.state.getColored();
    }
}
