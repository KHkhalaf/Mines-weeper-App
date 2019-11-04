/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleGame extends Game implements Serializable {

    static int count_of_mines;
    private Setting_width_height_mines_of_grid dofg;

    public ConsoleGame(int count_of_miness) {
        players = new ArrayList<>();
        currentPlayer = null;
        dofg = new Setting_width_height_mines_of_grid();
        dofg.setHeight(30);dofg.setNumberofmines(count_of_mines);dofg.setWidth(30);
        currentRules = new DefaulltRules();
        this.count_of_mines = count_of_miness;
    }

    public void build_console_game(Grid gui) {
        int r = dofg.get_width(), c = dofg.get_height();
        count_of_mines = dofg.get_mines();
        gui.initGrid(r, c, count_of_mines);
        String[][] show_array = new String[c + 1][r + 1];
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to Minesweeper\nEnter your name : ");
        String console_player_name;
        console_player_name = sc.nextLine();
        players.add(new console_player(console_player_name));
        display_show_array(show_array, c, r, gui, players.get(0).get_name());
        Cordinate_of_square cd = new Cordinate_of_square(enter());
        while (!cd.ok) {
            if (gui.getoneSquare(cd.getX(), cd.getY()).getState().getStatus() == "Closed") {
                gui.getoneSquare(cd.getX(), cd.getY()).getState().setstatus("Marked");
                count_of_mines--;
            } else if (gui.getoneSquare(cd.getX(), cd.getY()).getState().getStatus() == "Marked") {
                gui.getoneSquare(cd.getX(), cd.getY()).getState().setstatus("Closed");
                count_of_mines++;
            }
            display_show_array(show_array, c, r, gui, players.get(0).get_name());
            cd = enter();
        }
        edit(gui.getoneSquare(cd.getX(), cd.getY()), cd.getX(), cd.getY(), cd.getOk(), true, gui.get_rows(), gui.get_cols(), gui);
        display_show_array(show_array, c, r, gui, players.get(0).get_name());
        while (true) {
            if (number_of_closed__and_marked_cell(r, c, gui) == dofg.get_mines()) {
                System.out.println("congratulations " + console_player_name + " .\n you have won .");
                players.remove(0);
                return;
            }
            cd = enter();
            if (gui.getoneSquare(cd.getX(), cd.getY()).IsMine()==1 && cd.ok) {
                System.out.println("Game is Over\nYou Lost .");
                players.remove(0);
                return;
            }
            edit(gui.getoneSquare(cd.getX(), cd.getY()), cd.getX(), cd.getY(), cd.getOk(), false, gui.get_rows(), gui.get_cols(), gui);
            display_show_array(show_array, c, r, gui, players.get(0).get_name());
        }
    }

    class Cordinate_of_square {

        private int x, y;
        private boolean ok;

        Cordinate_of_square(int x, int y, boolean ok) {
            this.ok = ok;
            this.x = x;
            this.y = y;
        }

        Cordinate_of_square(Cordinate_of_square cd) {
            this.ok = cd.ok;
            this.x = cd.x;
            this.y = cd.y;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public boolean getOk() {
            return ok;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }

    }

    public Cordinate_of_square enter() {
        String coords;
        do {
            System.out.print("Enter the coords : ");
            Scanner sc = new Scanner(System.in);
            coords = sc.next();
        } while (coords.length() > 3 || coords.isEmpty());
        boolean ok;
        int entered_x, entered_y;
        if (coords.charAt(0) == '_') {
            ok = false;
            if ((coords.charAt(2) >= 'A' && coords.charAt(2) <= 'Z') || (coords.charAt(2) >= 'a' && coords.charAt(2) <= 'z')) {
                entered_x = coords.charAt(1) - '0';
                if (coords.charAt(2) >= 'A' && coords.charAt(2) <= 'Z') {
                    entered_y = coords.charAt(2) - 'A';
                } else {
                    entered_y = coords.charAt(2) - 'a' + 26;
                }
            } else {
                entered_x = (coords.charAt(2) - '0') + 10 * (coords.charAt(1) - '0');
                if (coords.charAt(3) >= 'A' && coords.charAt(3) <= 'Z') {
                    entered_y = coords.charAt(3) - 'A';
                } else {
                    entered_y = coords.charAt(3) - 'a' + 26;
                }
            }
        } else {
            ok = true;
            if ((coords.charAt(1) >= 'A' && coords.charAt(1) <= 'Z') || (coords.charAt(1) >= 'a' && coords.charAt(1) <= 'z')) {
                entered_x = coords.charAt(0) - '0';
                if (coords.charAt(1) >= 'A' && coords.charAt(1) <= 'Z') {
                    entered_y = coords.charAt(1) - 'A';
                } else {
                    entered_y = coords.charAt(1) - 'a' + 26;
                }
            } else {
                entered_x = (coords.charAt(1) - '0') + 10 * (coords.charAt(0) - '0');
                if (coords.charAt(2) >= 'A' && coords.charAt(2) <= 'Z') {
                    entered_y = coords.charAt(2) - 'A';
                } else {
                    entered_y = coords.charAt(2) - 'a' + 26;
                }
            }
        }
        Cordinate_of_square cd = new Cordinate_of_square(entered_x, entered_y, ok);
        return cd;
    }

    public void edit(Square t, int x1, int y1, boolean notflagmove, boolean first_click, int rows, int cols, Grid x) {
        if (notflagmove) {
            if (t.getState().getStatus() == "Closed") {
                if (first_click) {
                    x.set_squares(x.init_array_of_squares(x1, y1));
                    if (t.get_cnt_mine() == 0) {
                        t.getState().setstatus("OpenedEmpty");
                        flood_fill.inti_vis();
                        flood_fill.go(rows, cols, x1, y1, x.get_Square(), true);
                    } else {
                        t.getState().setstatus("OpenedNumber");
                    }
                } else {
                    if (t.IsMine()==1) {
                        t.getState().setstatus("OpenedMine");
                    } else {
                        if (t.get_cnt_mine() == 0) {
                            t.getState().setstatus("OpenedEmpty");
                            flood_fill.go(rows, cols, x1, y1, x.get_Square(), true);
                        } else {
                            t.getState().setstatus("OpenedNumber");
                        }
                    }
                }
            } else {
                System.out.println("This cell has been discovered before :::: => Choice another cell .");
                return;
            }
        } else {
            if (t.getState().getStatus() == "Marked") {
                t.getState().setstatus("Closed");
                count_of_mines++;
            } else if (t.getState().getStatus() == "Closed") {
                t.getState().setstatus("Marked");
                count_of_mines--;
            } else {
                System.out.println("This cell has been discovered before :::: => Choice another cell .");
                return;
            }

        }
    }

    public void display_show_array(String[][] show_array, int c, int r, Grid x, String player_name) {
        char t = 'A';
        int t1 = 0;
        boolean ok = true;
        System.out.println("Player's Name :" + player_name + "            Mines :" + count_of_mines);
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                if (i == 0 && j == 0) {
                    show_array[i][j] = "   ";
                } else if (i == 0 && j != 0) {
                    show_array[i][j] = String.valueOf(t++) + "|";
                } else if (i != 0 && j == 0) {
                    show_array[i][j] = String.valueOf(t1++) + "| ";
                } else if (j != 0 && i != 0 && x.getoneSquare(i - 1, j - 1).getState().getStatus() == "Closed") {
                    show_array[i][j] = "O|";
                } else if (j != 0 && i != 0 && x.getoneSquare(i - 1, j - 1).getState().getStatus() == "Marked") {
                    show_array[i][j] = "P|";
                } else if (j != 0 && i != 0 && x.getoneSquare(i - 1, j - 1).getState().getStatus() == "OpenedMine") {
                    show_array[i][j] = "B|";
                } else if (j != 0 && i != 0 && x.getoneSquare(i - 1, j - 1).getState().getStatus() == "OpenedNumber") {
                    show_array[i][j] = String.valueOf(x.getoneSquare(i - 1, j - 1).get_cnt_mine()) + "|";
                } else if (j != 0 && i != 0 && x.getoneSquare(i - 1, j - 1).getState().getStatus() == "OpenedEmpty") {
                    show_array[i][j] = " |";
                }
                System.out.print(show_array[i][j]);
                if (t > 'Z' && ok) {
                    t = 'a';
                    ok = false;
                }
            }
            System.out.println();
            for (int k = 0; k <= 2 * c + 3; k++) {
                System.out.print("_");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int number_of_closed__and_marked_cell(int r, int c, Grid x) {
        int number = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (x.getoneSquare(i, j).getState().getStatus() == "Closed" || x.getoneSquare(i, j).getState().getStatus() == "Marked") {
                    number++;
                }
            }
        }
        return number;
    }
}
