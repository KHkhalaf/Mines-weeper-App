/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 2318887-NEW-LAPTOP-
 */
public class Data_of_ScoreBoard implements Serializable{
    private static final String path="D:\\Store\\Score_Board\\score_board.bin";
    private int id_of_game;
    private String name_winer_player;
    private int score;
    private int time_of_play;
    private int shields;
    private int mines;
    private String start_time;
    private String end_time;
    private int number_of_player;
    private String name_game;

    public Data_of_ScoreBoard(int id_of_game,String name_winer_player,int score, int time_of_play, int shields, int mines, String start_time, String end_time, int number_of_player,String name_game) {
        this.id_of_game=id_of_game;
        this.name_winer_player = name_winer_player;
        this.score = score;
        this.time_of_play = time_of_play;
        this.shields = shields;
        this.mines = mines;
        this.start_time = start_time;
        this.end_time = end_time;
        this.number_of_player = number_of_player;
        this.name_game=name_game;
    }
    
    public void OutPut_Data() {
        Thread thread_output_data = new Thread() {
            @Override
            public void run() {
            ObjectOutputStream output = null;ObjectInputStream input=null;
              try {FileOutputStream output1=null;
                    FileInputStream input1 = new FileInputStream(path);
                     input = new ObjectInputStream(input1);
                     ArrayList<Data_of_ScoreBoard> read_object=(ArrayList<Data_of_ScoreBoard> ) input.readObject();
                     id_of_game=1;
                     if(!read_object.isEmpty())
                        id_of_game= read_object.get(read_object.size()-1).getId_of_game()+1;
                     read_object.add(new Data_of_ScoreBoard(id_of_game, name_winer_player, score,  time_of_play,  shields,  mines,  start_time,  end_time,  number_of_player,name_game));
                    try {
                      if (input != null) {
                        input.close();
                       }
                      } catch (IOException ioException) {
                       System.err.println("Error closing file.");
                       System.exit(1);
                      }
                    output1 = new FileOutputStream(path);
                    output=new ObjectOutputStream(output1);
                    output.writeObject(read_object);
                    output1.close();
                } catch (IOException ioException) {
                    System.err.println("Error Creating file Now.");
                } catch (ClassNotFoundException ex) {
                  System.out.println("This File Not Found .....");
                }
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ioException) {
                    System.err.println("Error closing file.");
                    System.exit(1);
                }
            }
        };
        thread_output_data.start();
    }

    public ArrayList<Data_of_ScoreBoard> InPut_Data() {
        ArrayList<Data_of_ScoreBoard> read_object=null;
             ObjectInputStream input=null;
                try { 
                 try (FileInputStream input1 = new FileInputStream(path)) {
                     input = new ObjectInputStream(input1);
                     read_object=(ArrayList<Data_of_ScoreBoard>) input.readObject();
                 return read_object;
                 } catch (ClassNotFoundException ex) {
                     System.out.println("The class not found ....<<<<---");
        }
                } 
                catch (FileNotFoundException filenotfound){
                System.err.println("file not found ......?");
                }
                catch (EOFException endOfFileException) {
                    System.err.println("end of file was reached .....?"); 
                }  catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.err.println("Error opening file.....?");
                }
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException ioException) {
                    System.err.println("Error closing file.");
                    System.exit(1);
                }
                return null;
    }    

    public int getId_of_game() {
        return id_of_game;
    }

    public String getEnd_time() {
        return end_time;
    }

    public int getMines() {
        return mines;
    }

    public int getNumber_of_player() {
        return number_of_player;
    }

    public String getNameplayer() {
        return name_winer_player;
    }

    public int getScore() {
        return score;
    }

    public int getShields() {
        return shields;
    }

    public String getStart_time() {
        return start_time;
    }

    public int getTime_of_play() {
        return time_of_play;
    }

    public String getName_game() {
        return name_winer_player;
    }
    
    public String get_path_selected_game(){
        return name_game;
    }
   public void Create_File_if_Not_Found(){
       
   ObjectOutputStream output = null;
    try {FileOutputStream output1=null;
                    output1 = new FileOutputStream(path);
                    output=new ObjectOutputStream(output1);
                    ArrayList<Data_of_ScoreBoard> list=new ArrayList<>();
                    output.writeObject(list);
                    output1.close();
                } 
                  catch (IOException ioException) {
                    System.err.println("Error Creating file for data scoreboard.");
                }
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ioException) {
                    System.err.println("Error closing file.");
                    System.exit(1);
                }
   }
}
