/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

/**
 *
 * @author Mohammed ALmasry
 */
public class weak_shield extends Shield implements Serializable 
{

    @Override
    public int decrease() {
        return -50;
    }
    
}
