/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

public class SquareStatus  implements Serializable {

    private String status;
    private int number;
    private boolean colored;

    public SquareStatus() {
        status = "Closed";
        number = 0;
        colored = false;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setnumber(int number) {
        this.number = number;
    }

    public int getnumber() {
        return this.number;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }

    public boolean getColored() {
        return colored;
    }
}
