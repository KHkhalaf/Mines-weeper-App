/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import javafx.util.Pair;

public abstract class Player  implements Serializable {

    protected String name;
    protected int time_play=0;
    protected int currentScore = 0;
    protected PlayerStatus state = null;
    protected PlayerMove move = null;
    protected int color = 0;
    protected int weak_shield_num,strong_shield_num;

    abstract public PlayerMove GetPlayerMove();

    abstract public String get_name();

    abstract public int get_color();

    abstract public void set_color(int id);

    abstract public void set_name(String name);

    abstract public void set_current_score(int score);

    abstract public String get_state();

    abstract public int get_currentScore();
    
    public void setStrong_shield_num(int strong_shield_num) {
        this.strong_shield_num = strong_shield_num;
    }

    public void setWeak_shield_num(int weak_shield_num) {
        this.weak_shield_num = weak_shield_num;
    }
   
   abstract  public void change_number_of_shields(String kind_shield,int addition);
  
   abstract public int getStrong_shield_num();

   abstract public int getWeak_shield_num();
}
