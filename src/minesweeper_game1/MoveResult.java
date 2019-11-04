/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

public class MoveResult  implements Serializable {

    private int scorechange;

    MoveResult() {
        scorechange = 0;
    }

    public void set_scorechange(int scorechange) {
        this.scorechange = scorechange;
    }

    public int get_scorechange() {
        return this.scorechange;
    }
}
