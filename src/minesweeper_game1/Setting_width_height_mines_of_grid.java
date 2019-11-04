/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

public class Setting_width_height_mines_of_grid  implements Serializable {

    private int width, height, numberofmines;

    public Setting_width_height_mines_of_grid() {
        width = 10;
        height = 10;
        numberofmines = 10;
    }

    public int get_width() {
        return width;
    }

    public int get_height() {
        return height;
    }

    public int get_mines() {
        return numberofmines;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setNumberofmines(int numberofmines) {
        this.numberofmines = numberofmines;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
