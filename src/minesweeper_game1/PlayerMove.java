/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;

public class PlayerMove  implements Serializable {

    //instance variable
    private int player;
    private Square square;
    private boolean type;
    private int clicked_mine;
    private MoveResult result;

    PlayerMove() {
        player = 0;
        square = new Square();
        result = new MoveResult();
        type=false;
        clicked_mine=0;
    }

    //1)set
    public void set_player(int player) {
        this.player = player;
    }

    //2)set
    public void setsquare(Square square) {
        this.square = square;
    }

    //3)set
    public void settype(boolean type) {
        this.type=type;
    }

    //4)set
    public void setresult(int result) {
        this.result.set_scorechange(result);
    }

    //1)get
    public int getplayer() {
        return this.player;
    }

    public int isClicked_mine() {
        return clicked_mine;
    }

    public void setClicked_mine(int clicked_mine) {
        this.clicked_mine = clicked_mine;
    }
    

    //2)get        
    public Square get_square() {
        return this.square;
    }

    //3)get
    public boolean get_type() {
        return this.type;
    }

    //4)get        
    public MoveResult get_result() {
        return this.result;
    }

}
