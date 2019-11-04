/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import java.util.ArrayList;

public class Game  implements Serializable {

    protected ArrayList<Player> players;
    protected Player currentPlayer;
    protected ArrayList<PlayerMove> Moves;
    protected Game.GameRules currentRules;
    protected Grid grid;
    protected boolean removed_computer;

    abstract class GameRules {

        protected int numbered_btn, empty_btn, empty_or_numbered_flood, mine_flag, non_mine_flag, discount, last_addition;

        public int GetScoreChange(PlayerMove move) {
            return 1;
        }

        public abstract boolean DecideNextPlayer(PlayerMove move);
    }

    public void inti_Game(ArrayList<Player> list_of_names) {
    }

    public boolean Accept_Move(Square move) {
        return true;
    }

    public void ApplyPlayerMove(PlayerMove move, Custom obj) {
    }

    public void Delete_an_player_of_list(int index) {
    }

}
