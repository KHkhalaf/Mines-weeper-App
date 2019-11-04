/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

/**
 *
 * @author 2318887-NEW-LAPTOP-
 */
public class Color  implements Serializable {

    public static String return_color(int index) {

        switch (index) {
            case 1:
                return "eea1bd";
            case 2:
                return "9f86eb";
            case 3:
                return "ffff33";
            case 4:
                return "00cc00";
            case 5:
                return "878382";
            case 6:
                return "ffffff";
            case 7:
                return "ffab06";
            case 8:
                return "db732e";
            default:
                return "eea1bd";
        }
    }
}
