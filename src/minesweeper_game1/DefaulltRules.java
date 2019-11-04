/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import java.util.List;

public class DefaulltRules extends Game.GameRules  implements Serializable {

    DefaulltRules() {
        new Game().super();
        empty_btn = 10;
        empty_or_numbered_flood = 1;
        mine_flag = 5;
        non_mine_flag = -1;
        discount = 250;
        last_addition = 100;
    }

    public void set_numbered_btn(Square square) {
        numbered_btn = square.getState().getnumber();
    }

    @Override
    public int GetScoreChange(PlayerMove move) {
        return move.get_result().get_scorechange();
    }

    @Override
    public boolean DecideNextPlayer(PlayerMove move) {
        return false;
    }

}
