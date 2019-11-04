/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

public class PlayerStatus  implements Serializable {

    private String state = null;

    public void set_state(String state) {
        this.state = state;
    }

    public String get_state() {
        return state;
    }
}
