/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import java.util.List;

public class Custom extends Game.GameRules  implements Serializable {

    Custom(int numbered_btn1, int empty_btn1, int empty_or_numbered_flood1, int mine_flag1, int non_mine_flag1, int discount1, int last_addition1) {
        new Game().super();
        numbered_btn = numbered_btn1;
        empty_btn = empty_btn1;
        this.empty_or_numbered_flood = empty_or_numbered_flood1;
        mine_flag = mine_flag1;
        non_mine_flag = non_mine_flag1;
        discount = discount1;
        last_addition = last_addition1;
    }

    public void set_numbered_btn(Square square) {
        numbered_btn = square.getState().getnumber();
    }

    public int get_empty_btn() {
        return empty_btn;
    }

    public int get_empty_or_numbered_flood() {
        return empty_or_numbered_flood;
    }

    public int get_mine_flag() {
        return mine_flag;
    }

    public int get_non_mine_flag() {
        return non_mine_flag;
    }

    public int get_discount() {
        return discount;
    }

    public int get_last_addition() {
        return last_addition;
    }

    @Override
    public int GetScoreChange(PlayerMove move) {
        return move.get_result().get_scorechange();
    }

    @Override
    public boolean DecideNextPlayer(PlayerMove move) {
        if (move.get_square().getState().equals("Marked")) {
            return true;
        }
        return false;
    }

}
