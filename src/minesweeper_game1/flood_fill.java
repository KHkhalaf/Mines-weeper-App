/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import javafx.util.Pair;

public class flood_fill  implements Serializable {

    static private int tot = 0;
    static private int t = 0;
    static private int cnt1 = 0;
    static private int num_of_weak_shield=0,num_of_strong_shield=0;
    private static final int[][] vis = new int[1000][1000];
    private static final int[] neighbor_x = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static final int[] neighbor_y = {0, 0, 1, -1, 1, -1, 1, -1};

    public static void go(int rows, int cols, int x, int y, Square[][] squares, boolean ok) {
        if (ok) {
            vis[x][y] = 1;
        if(squares[x][y].getis_Shield()==true)
        {
           if("strong".equals(squares[x][y].getWeak_strong()))
               num_of_strong_shield++;
           else
               num_of_weak_shield++;
        }
            ok = false;
            if (squares[x][y].IsMine() == 0 && squares[x][y].get_cnt_mine() < 9 && squares[x][y].get_cnt_mine() > 0) {
                return;
            }
        }
        for (int i = 0; i < 8; i++) {
            int tx = x + neighbor_x[i];
            int ty = y + neighbor_y[i];
            if (tx >= 0 && tx < rows && ty >= 0 && ty < cols) {
            if (squares[tx][ty].IsMine() == 0 && squares[tx][ty].get_cnt_mine() == 0 && vis[tx][ty] == 0 && "Closed".equals(squares[tx][ty].getState().getStatus())) {
                    vis[tx][ty] = 1;
                    tot += t;
                    squares[tx][ty].getState().setstatus("OpenedEmpty");
                        if(squares[tx][ty].getis_Shield()==true)
                       {
                        if("strong".equals(squares[tx][ty].getWeak_strong()))
                           num_of_strong_shield++;
                       else
                            num_of_weak_shield++;
                       }
                  go(rows, cols, tx, ty, squares, false);
                } else if (squares[tx][ty].IsMine() == 0 && squares[tx][ty].get_cnt_mine() < 9 && vis[tx][ty] == 0 && "Closed".equals(squares[tx][ty].getState().getStatus())) {
                    vis[tx][ty] = 1;
                    tot += t;
                    squares[tx][ty].getState().setstatus("OpenedNumber");
                }
            }
        }
    }

    public static void set_cnt(int mount1, int mount2) {
        tot = 0;
        t = mount1;
        cnt1 = mount2;
    }
   public static void initnum_of_shields()
   {
   num_of_weak_shield =num_of_strong_shield=0;
   }
   
   public static Pair<Integer,Integer> cul_num_of_shields()
   {
   return new Pair<>( num_of_weak_shield,num_of_strong_shield);
    
   }
    public static int get_tot() {
        return tot + cnt1;
    }

    public static void inti_vis() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                vis[i][j] = 0;
            }
        }
    }
}
