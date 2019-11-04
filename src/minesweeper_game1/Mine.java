/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

public class Mine  implements Serializable {

    private String ID;

    Mine() {
        ID = null;
    }

    public void set_ID(String ID) {
        this.ID = ID;
    }

    public String get_ID() {
        return this.ID;
    }
}
