/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.util.Pair;

public class GUIGame extends Game implements Serializable {

    public GUIGame(int numbered_btn1, int empty_btn1, int empty_or_numbered_flood1, int mine_flag1, int non_mine_flag1, int discount1, int last_addition1) {
        players = new ArrayList<>();
        Moves= new ArrayList<>();
        currentPlayer = null;
        removed_computer = false;
        currentRules = new Custom(numbered_btn1, empty_btn1, empty_or_numbered_flood1, mine_flag1, non_mine_flag1, discount1, last_addition1);
    }

    @Override
    public void inti_Game(ArrayList<Player> list_of_Players) {
        int id = 0;
        for (Player item : list_of_Players) {
            players.add(item);
        }
        currentPlayer = players.get(0);
    }

    @Override
    public boolean Accept_Move(Square move_type) {

        if (move_type.getState().getStatus().equals("Closed")) {
            return true;
        }
        return false;
    }

    public void add_new_move(int player, boolean move_type, Square square) {
        Moves.add(new PlayerMove());
        Moves.get(Moves.size()-1).set_player(player);
        Moves.get(Moves.size()-1).setsquare(square);
        Moves.get(Moves.size()-1).settype(move_type);
     }
    
    public void set_clicked_mine_of_move(int clicked_mine){
     Moves.get(Moves.size()-1).setClicked_mine(clicked_mine);
    }
            
    public PlayerMove get_current_move() {
        return Moves.get(Moves.size()-1);
    }
    
    public boolean get_typy_move(int index){
        return Moves.get(index).get_type();
    }
    
    public MoveResult get_Moves() {
        return Moves.get(Moves.size()-1).get_result();
    }
    
    public ArrayList<PlayerMove> get_PlayerMove_List() {
        return Moves;
    }
    public void set_Moves(ArrayList<PlayerMove> moves){
    Moves=moves;
    }
    public ArrayList<Player> get_Player_List() {
        return players;
    }

    public void update_player(int score) {
        ((GUIPlayer) players.get(0)).set_current_score(score);
    }

    public void update_player(int score, int current_player, boolean rank) {

        if (!"computer".equals(players.get(current_player).get_name())) {
            ((GUIPlayer) players.get(current_player)).set_current_score(score);
        } else {
            ((Computer_Player) players.get(current_player)).set_current_score(score);
        }
    }

    public Player update_player(int score, int current_player) {
        ((GUIPlayer) players.get(current_player)).set_current_score(score);
        if (current_player == (players.size() - 1)) {
            current_player = -1;
        }
        return (GUIPlayer) players.get(++current_player);
    }

    public Pair<Integer, Integer> get_coordinate(Square squares[][], int rows, int cols, boolean IsRandom) {
       currentPlayer = new Computer_Player("computer", 15, 2,3,3);
        if (IsRandom) {
            return ((Computer_Player) currentPlayer).generate_random_coords(squares, rows, cols);
        } else { 
            return AIPlayer.generate_smart_coords(squares, rows, cols,3,3);
        }
    }

    @Override
    public void Delete_an_player_of_list(int index) {
        players.remove(index);
    }

    public int get_detected_score(int index) {
        return players.get(index).get_currentScore();
    }
    
    public void edit_num_shields(int index,String kind,int addition)
    {
        if(players.get(index).get_name().equals("computer"))
            ((Computer_Player)players.get(index)).change_number_of_shields(kind,addition);
        else 
             ((GUIPlayer)players.get(index)).change_number_of_shields(kind,addition);
    }
    public Pair<Integer,Integer> get_numbers_of_shields(int index)
     {if(!players.get(index).get_name().equals("computer"))
        return new Pair<Integer,Integer>(((GUIPlayer)players.get(index)).getWeak_shield_num(),((GUIPlayer)players.get(index)).getStrong_shield_num());
       else
         return new Pair<Integer,Integer>(((Computer_Player)players.get(index)).getWeak_shield_num(),((Computer_Player)players.get(index)).getStrong_shield_num());
     }
    public String get_name_detected_player(int index){return players.get(index).get_name();}
 
}
