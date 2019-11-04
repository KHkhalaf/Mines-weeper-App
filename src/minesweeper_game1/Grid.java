/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javafx.util.Pair;
import java.util.*;

public class Grid  implements Serializable {

    private int rows, cols, num_mines,num_shield;
    private Square[][] squares;
    private Mine[] mines;
    private GUIGame currentGame;
    private int[] neighbor_x = {-1, 1, 0, 0, -1, -1, 1, 1};
    private int[] neighbor_y = {0, 0, 1, -1, 1, -1, 1, -1};
    private ConsoleGame currentGame1;

    Grid(int numbered_btn1, int empty_btn1, int empty_or_numbered_flood1, int mine_flag1, int non_mine_flag1, int discount1, int last_addition1) {
        rows = cols = 20;
        num_shield=10;
        num_mines = 30;
        currentGame = new GUIGame(numbered_btn1, empty_btn1, empty_or_numbered_flood1, mine_flag1, non_mine_flag1, discount1, last_addition1);
        mines = new Mine[num_mines];
        for (int i = 0; i < num_mines; i++) {
            mines[i] = new Mine();
        }
    }

    Grid() {
        num_shield=10;
        rows = cols = 20;
        num_mines = 30;
        currentGame = new GUIGame(-1, 10, 1, 5, -1, 250, 100);
        currentGame1 = new ConsoleGame(30);
        mines = new Mine[num_mines];
        for (int i = 0; i < num_mines; i++) {
            mines[i] = new Mine();
        }
    }

    public int get_rows() {
        return rows;
    }

    public int get_cols() {
        return cols;
    }

    public void add_new_move(int player, boolean move_type, Square square) {
        currentGame.add_new_move(player, move_type, square);
    }
    
    public void set_clicked_mine_of_move(int clicked_mine){
     currentGame.set_clicked_mine_of_move(clicked_mine);
    }
    
    public ArrayList<PlayerMove> get_PlayerMove_List() {
        return currentGame.get_PlayerMove_List();
    }
    public void set_PlayerMove_List(ArrayList<PlayerMove> moves){
    currentGame.set_Moves(moves);}
    public PlayerMove get_current_move() {
        return currentGame.get_current_move();
    }

    public int getNum_mines() {
        return num_mines;
    }

    public ConsoleGame getCurrentGame1() {
        return currentGame1;
    }

    public Square[][] get_Square() {
        return squares;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public void initGrid(int rows, int cols, int num_mines) {
        this.rows = rows;
        this.cols = cols;
        this.num_mines = num_mines;
        squares = new Square[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                squares[i][j] = new Square();squares[i][j].set_x(i);squares[i][j].set_y(j);
            }
        }
    }

    public Square[][] init_array_of_squares(int first_btn_x, int first_btn_y) {
        Set< Pair<Integer, Integer>> set = new HashSet<>();
        while (set.size() < num_mines) {
            Random x = new Random();
            Random y = new Random();
            int x1 = x.nextInt(rows);
            int y1 = y.nextInt(cols);
            if (x1 != first_btn_x && y1 != first_btn_y) {
                try {
                    set.add(new Pair<>(x1, y1));
                } catch (Exception e) {
                    System.err.println("Can't set Grid with mines random");
                }
            }
        }
        set.stream().map((it) -> {
            squares[it.getKey()][it.getValue()].set_mine("mine");
            return it;
        }).forEachOrdered((it) -> {
            squares[it.getKey()][it.getValue()].set_cnt_mine(-1);
        });
        set.forEach((it) -> {
            for (int i = 0; i < 8; i++) {
                int x1 = it.getKey() + neighbor_x[i], y1 = it.getValue() + neighbor_y[i];
                if (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < cols) {
                    if (squares[x1][y1].IsMine() == 0) {
                        squares[x1][y1].set_cnt_mine(squares[x1][y1].get_cnt_mine() + 1);
                    }
                }
            }
        });
         set.clear();
        
         while (set.size() < num_shield) {
            Random x = new Random();
            Random y = new Random();
            int x1 = x.nextInt(rows);
            int y1 = y.nextInt(cols);
       if( squares[x1][y1].IsMine()==0 && squares[x1][y1].get_cnt_mine()==0){
                try {
                    
                    set.add(new Pair<>(x1, y1));
                   
                }
                catch (Exception e) {
                    System.err.println("Can't set Grid with shield random");
                }
         }   
       
        }
        int index=0;
        for (Pair<Integer, Integer> it : set) {
            squares[it.getKey()][it.getValue()].setis_Shield(true);
            if(index<num_shield/2)
                 squares[it.getKey()][it.getValue()].setWeak_strong("weak");
            else if(index>=num_shield/2&&index<num_shield)
                squares[it.getKey()][it.getValue()].setWeak_strong("strong");
            index++;
        }
        return squares;
    }

    public void AcceptMove(Game.GameRules obj, int first_btn_x, int first_btn_y,int index,String level) {
        if (squares[first_btn_x][first_btn_y].IsMine() == 0 && squares[first_btn_x][first_btn_y].get_cnt_mine() == 0) {
            squares[first_btn_x][first_btn_y].getState().setstatus("OpenedEmpty");
            flood_fill.set_cnt(((Custom) obj).get_empty_or_numbered_flood(), ((Custom) obj).get_empty_btn());
            flood_fill.initnum_of_shields();
            flood_fill.go(rows, cols, first_btn_x, first_btn_y, squares, true);
           Pair<Integer,Integer> p=flood_fill.cul_num_of_shields();
           if(!level.equals("Dump")||!"computer".equals(currentGame.get_name_detected_player(index)))
           {currentGame.edit_num_shields(index, "weak", p.getKey());
            currentGame.edit_num_shields(index, "strong", p.getValue());
            }
            int total_Profit = flood_fill.get_tot();
            currentGame.get_Moves().set_scorechange(total_Profit);
        } else if (squares[first_btn_x][first_btn_y].IsMine() == 0 && squares[first_btn_x][first_btn_y].get_cnt_mine() < 9) {
            squares[first_btn_x][first_btn_y].getState().setstatus("OpenedNumber");
            if (obj.numbered_btn != -1) {
                currentGame.get_Moves().set_scorechange(obj.numbered_btn);
            } else {
                currentGame.get_Moves().set_scorechange(squares[first_btn_x][first_btn_y].get_cnt_mine());
            }
        }
    }

    public boolean Accept_Move(Square move_type) {
        return currentGame.Accept_Move(move_type);
    }

    public void call_flood(Game.GameRules obj, int x, int y,int index,String level) {
        flood_fill.set_cnt(((Custom) obj).get_empty_or_numbered_flood(), ((Custom) obj).get_empty_btn());
        if (squares[x][y].IsMine() == 0 && squares[x][y].get_cnt_mine() == 0) {
            flood_fill.initnum_of_shields();
            flood_fill.go(rows, cols, x, y, squares, true);
            Pair<Integer,Integer> p=flood_fill.cul_num_of_shields();
             if(!level.equals("Dump")||!"computer".equals(currentGame.get_name_detected_player(index)))
           {
            currentGame.edit_num_shields(index, "weak", p.getKey());
            currentGame.edit_num_shields(index, "strong", p.getValue());
           }
            int total_Profit = flood_fill.get_tot();
            currentGame.get_Moves().set_scorechange(total_Profit);
            squares[x][y].getState().setstatus("OpenedEmpty");
        } else if (squares[x][y].IsMine() == 0 && squares[x][y].get_cnt_mine() < 9) {
            squares[x][y].getState().setstatus("OpenedNumber");
            if (obj.numbered_btn != -1) {
                 currentGame.get_Moves().set_scorechange(obj.numbered_btn);
            } else {
                 currentGame.get_Moves().set_scorechange(squares[x][y].get_cnt_mine());
            }
        }
    }

    public void init_visited() {
        flood_fill.inti_vis();
    }

    public void set_change_score(int score) {
        currentGame.get_Moves().set_scorechange(score);
    }

    public void singleplayer_and_update_score() {
        currentGame.update_player(currentGame.get_Moves().get_scorechange());
    }

    public void get_update_score_current_player_with_computer(int current_player) {
        if (!"computer".equals(currentGame.players.get(current_player).get_name())) {
            currentGame.update_player(currentGame.get_Moves().get_scorechange(), current_player, true);
        } else {
            currentGame.update_player(currentGame.get_Moves().get_scorechange(), current_player, true);
        }
    }

    public void multiplayer_and_update_score(int current_player) {
        Player p = currentGame.update_player(currentGame.get_Moves().get_scorechange(), current_player);
    }

    public void set_List_of_Players(ArrayList<Player> list_players) {
        currentGame.inti_Game(list_players);
    }

    public void set_squares(Square[][] squares) {
        this.squares = squares;
    }

 

    public Pair<Integer, Integer> get_coordinate(boolean IsRandom) {
        return currentGame.get_coordinate(squares, rows, cols, IsRandom);
    }

    public void delete_an_player(int index) {
        currentGame.Delete_an_player_of_list(index);
    }

    public int get_score_detected_player(int index) {
        return currentGame.get_detected_score(index);
    }

    public void setoneSquare(int x, int y, Square square) {
        this.squares[x][y] = square;
    }

    public Square getoneSquare(int x, int y) {
        return squares[x][y];
    }

    public boolean get_state_removed_computer() {
        return currentGame.removed_computer;
    }

    public void removed_computer() {
        currentGame.removed_computer = true;
    }
    
    public void change_a_squareState(int x, int y, String state) {
        squares[x][y].set_state(state);
    }
    
    public void change_number_of_shields(int index_of_player,String kind) {
    currentGame.edit_num_shields(index_of_player, kind,-1);
    }
    
    public Pair<Integer,Integer> get_numbers_of_shields(int index){
       return currentGame.get_numbers_of_shields(index);
    }
    
    public void set_color_squares(){
    for(int i=0;i<rows;i++)
        for(int j=0;j<cols;j++)
          squares[i][j].setColored(false);
    }
    
    public int getTime_play(int index) {
       return currentGame.players.get(index).time_play;
    }
    
    public void addTime_play(int time_a_move,int index){
        if(currentGame.players.get(index).get_name().equals("computer")||currentGame.players.size()==1)
            time_a_move=1;
            currentGame.players.get(index).time_play+=time_a_move;
    }
}
