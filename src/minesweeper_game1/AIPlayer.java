/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author 2318887-NEW-LAPTOP-
 */
public class AIPlayer  implements Serializable {
        private int strong_shield_num;
        private int weak_shield_num;
        public AIPlayer(int weak_shield,int strong_shield) {
        this.strong_shield_num=strong_shield;
        this.weak_shield_num=weak_shield;
    }
    
  
    static public Pair<Integer, Integer> generate_smart_coords(Square[][] squares, int rows, int cols,int weak_shield,int strong_shield) {
       
        Random r = new Random();
        int x1 = r.nextInt(rows), y1 = r.nextInt(cols);
        while (true) { 
            if ("Closed".equals(squares[x1][y1].getState().getStatus()) && (squares[x1][y1].get_cnt_mine() > 0 || squares[x1][y1].IsMine()==1)) {
                return new Pair<>(x1, y1);
            }
            x1 = r.nextInt(rows);
            y1 = r.nextInt(cols);
        }
    }
    public int getStrong_shield_num() {
        return this.strong_shield_num;
    }

    
    public int getWeak_shield_num() {
       return weak_shield_num;
    }

    
    public void change_number_of_shields(String kind_shield, int addition) {
      if("strong".equals(kind_shield))
        this.strong_shield_num+=addition;
        else
        this.weak_shield_num+=addition;
    
    }
}
