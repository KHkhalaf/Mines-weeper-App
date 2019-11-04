/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.*;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author 2318887-NEW-LAPTOP-
 */
public class File_Input_Output_Streams implements Serializable {
    private static final String file_path="D:\\Store\\";
    private int repetition;
    private String play_state=null;
    private ArrayList<String> names_of_player;
    private ArrayList<Player> list_of_player;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private File tempfile=null;
    private Pair<Square[][],ArrayList<PlayerMove>> p1;
    private Pair<ArrayList<Integer>,ArrayList<Integer>> p2;
    
    public void set_init_Data(Pair<Square[][],ArrayList<PlayerMove>> p1,Pair<ArrayList<Integer>,ArrayList<Integer>> p2,int repetition, String play_state,ArrayList<String> names_of_player,ArrayList<Player>list_of_player) {
        this.repetition = repetition;
        this.play_state = play_state;
        this.list_of_player=list_of_player;
        this.names_of_player=names_of_player;
        this.p1=new Pair<>(p1.getKey(),p1.getValue());
        this.p2=new Pair<>(p2.getKey(),p2.getValue());
    }
  
    public String OutPut_Data(boolean isDone) {
            String Total_Path=null;
              try {FileOutputStream output1=null;
                 if(!isDone)  {
                    Total_Path=getNewFileName("not_Game_Complete\\"+play_state);
                    output1 = new FileOutputStream(Total_Path);
                        }
                else   {
                    Total_Path=getNewFileName("Game_Complete\\"+play_state);
                    output1 = new FileOutputStream(Total_Path);
                            }
                    output=new ObjectOutputStream(output1);
                    File_Input_Output_Streams obj=new File_Input_Output_Streams();
                    obj.set_init_Data(p1, p2, repetition, play_state, names_of_player, list_of_player);
                    output.writeObject(obj);
                    output1.close();
                } catch (IOException ioException) {
                    System.err.println("Error Creating file.");
                }
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ioException) {
                    System.err.println("Error closing file.");
                    System.exit(1);
                }
           return Total_Path;
    }
    
    public void OutPut_Data_to_tempfile() throws IOException{
        if(tempfile==null)
        tempfile=File.createTempFile("Temp_File", ".temp",new File(file_path));
            FileOutputStream output1=null;
            output1 = new FileOutputStream(tempfile.getCanonicalPath());
            output=new ObjectOutputStream(output1);
            File_Input_Output_Streams obj=new File_Input_Output_Streams();
            obj.set_init_Data(p1, p2, repetition, play_state, names_of_player, list_of_player);
            output.writeObject(obj);
            output1.close();
             try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ioException) {
                    System.err.println("Error closing file.");
                    System.exit(1);
                } 
        tempfile.deleteOnExit();
    }
    
    public void InPut_Data(String file_path) {
                try { 
                  FileInputStream input1 = new FileInputStream(file_path);
                  input = new ObjectInputStream(input1);
                  File_Input_Output_Streams read_object=(File_Input_Output_Streams) input.readObject();
                  repetition=read_object.getRepetition();
                  play_state=read_object.getPlay_state();
                  names_of_player=read_object.getList_of_names();
                  list_of_player=read_object.getList_of_player();
                  p1=read_object.getP1();
                  p2=read_object.getP2();
                      input1.close();
                } 
                catch (FileNotFoundException filenotfound){
                System.err.println("file not found ......?");
                }
                catch (EOFException endOfFileException) {
                    System.err.println("end of file was reached .....?"); 
                }  catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.err.println("Error opening file.....?");
                } catch (ClassNotFoundException ex) { 
                    System.err.println("File Not Found  .....?");
            }
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException ioException) {
                    System.err.println("Error closing file.");
                    System.exit(1);
                }
    }
    
    public void InPut_Data_to_tempfile() {
        if(tempfile==null) return;
                try { 
                  FileInputStream input1 = new FileInputStream(tempfile);
                  input = new ObjectInputStream(input1);
                  File_Input_Output_Streams read_object=(File_Input_Output_Streams) input.readObject();
                  repetition=read_object.getRepetition();
                  play_state=read_object.getPlay_state();
                  names_of_player=read_object.getList_of_names();
                  list_of_player=read_object.getList_of_player();
                  p1=read_object.getP1();
                  p2=read_object.getP2();
                      input1.close();
                } 
                catch (FileNotFoundException filenotfound){
                System.err.println("file not found ......?");
                }
                catch (EOFException endOfFileException) {
                    System.err.println("end of file was reached .....?"); 
                }  catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.err.println("Error opening file.....?");
                } catch (ClassNotFoundException ex) { 
                    System.err.println("File Not Found  .....?");
            }
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException ioException) {
                    System.err.println("Error closing file.");
                    System.exit(1);
                }
    }
    
    public static String getNewFileName(String type_game)throws IOException{  
    File aFile=new File(file_path+type_game+"(1).bin");
    int file_number=1;
    String newFileName="";
    if(aFile.exists())
    {
    while(aFile.exists()){
    file_number++;
    aFile=new File(file_path+type_game+"("+file_number+").bin");
    newFileName=file_path+type_game+"("+file_number+").bin";
    }
    }
    else {
        newFileName=file_path+type_game+"(1).bin";
    }
     return newFileName;
}
    
    public ArrayList<String> getList_of_names() {
        return names_of_player;
    }

    public int getRepetition() {
        return repetition;
    }

    public String getPlay_state() {
        return play_state;
    }

    public ArrayList<Player> getList_of_player() {
        return list_of_player;
    }

    public Pair<Square[][], ArrayList<PlayerMove>> getP1() {
        return p1;
    }

    public Pair<ArrayList<Integer>, ArrayList<Integer>> getP2() {
        return p2;
    }
    public void delete_file(){
       if(tempfile!=null)
        tempfile.delete();
    }
}
