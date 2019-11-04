/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

public class MoveType  implements Serializable {

    private String type;

    MoveType() {
        type = null;
    }

    public void set_type(String type) {
        this.type = type;
    }

    public String get_type() {
        return this.type;
    }
}
