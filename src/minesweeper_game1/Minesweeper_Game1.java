/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper_game1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

public class Minesweeper_Game1 extends Application {

    private static final int buttons_padding = 1;
    private File_Input_Output_Streams input_and_output_data;
    private Data_of_ScoreBoard output_data_score;
    private ArrayList<Integer> setting_game, setting_points;
    private Pair<Square[][], ArrayList<PlayerMove>> p1;
    private ArrayList<PlayerMove> moves;
    private Pair<ArrayList<Integer>, ArrayList<Integer>> p2;
    private ArrayList<Object> Data_of_winer_player;
    private ArrayList<Player> list_of_players;
    private ArrayList<String> list_of_names;
    private int width, height, mines, x_clicked_btn, y_clicked_btn, numberofmultiplayer, number_of_weak_sheilds = 0, number_of_strong_sheilds = 0, iterator = 0;
    private int numbered_btn, empty_btn, empty_or_numbered_flood, mine_flag, non_mine_flag, discount, last_addition, t;
    private Square[][] squares = null;
    private Stage window_primary;
    private String level = "", play_state, start_time_of_play, end_time_of_play;
    private GridPane grid_;
    private File file1;
    private Game.GameRules obj_Custom;
    private int i, j, cnt, repetition = 0, prev_rep, x_random_computer, y_random_of_computer, number_of_time = 10;
    private Grid gui;
    private Setting_width_height_mines_of_grid dimension;
    private ComboBox<String> combobox_type, combobox_setting;
    private Button[][] grid_button;
    private Button set_setting, cancel_setting, set_setting_1, cancel_setting_1, about, load_game, save_game, view_scoreBoard, Quick_save;
    private TextField[] tf, tf1;
    private BorderPane root;
    private HBox hbox, hbox1;
    private VBox vbox, vb_setting, vb_vbox;
    private AnchorPane anchor;
    private Label current_player, number_of_mines, name_current_player, weak_Shields, num_weak_Shields, strong_Shields, num_strong_Shields, score, result_score, number_mines, label_text, label_text1, time_of_play, time;
    private Scene scene_window1, scene_window2;
    private boolean ok = true, pause_time, ok1 = false, review = false, single_player_state, play_with_computer_state, multiplayer_state, init_squares = false, removed_comuter = false;
    private TimerTask timertask;
    private Timer timer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        list_of_players = new ArrayList<>();
        list_of_names = new ArrayList<>();
        input_and_output_data = new File_Input_Output_Streams();
        window_primary = primaryStage;
        window_primary.initStyle(StageStyle.UNDECORATED);
        root = new BorderPane();
        anchor = new AnchorPane();
        scene_window1 = new Scene(anchor, 1373, 735);
        anchor.setId("anchorpane");
        scene_window1.getStylesheets().addAll(this.getClass().getResource("style1.css").toExternalForm());
        scene_window2 = new Scene(root, 1373, 725);
        Desgin_window1();
        grid_ = new GridPane();
    }

    private void Desgin_window1() {
        Design_customize_points_setting();
        Design_customize_game_setting();
        init_parameters();
        init_values();
        about = new Button("About");
        about.setLayoutX(1250);
        about.setLayoutY(650);
        about.setFont(new Font("Tahoma", 20));
        about.setOnAction(e -> {
            click_about();
        });
        Label mines_titel = new Label("Mines Weeper");
        mines_titel.setLayoutX(500);
        mines_titel.setLayoutY(10);
        mines_titel.setFont(new Font("italic", 70));
        mines_titel.setStyle("-fx-text-fill:white");
        Button start_btn = new Button("Start");
        start_btn.setLayoutX(200);
        start_btn.setLayoutY(200);
        start_btn.setId("start");
        start_btn.setMinSize(200, 35);
        start_btn.setFont(new Font("Tahoma", 20));
        combobox_type = new ComboBox<>();
        combobox_type.setLayoutX(200);
        combobox_type.setLayoutY(280);
        start_btn.setOnAction(e -> {
            review = false;
            list_of_names.clear();
            init_window1(combobox_type.getValue());
        });
        combobox_setting = new ComboBox<>();
        combobox_setting.setMinSize(200, 35);
        combobox_type.setMinSize(200, 35);
        combobox_setting.setLayoutX(200);
        combobox_setting.setLayoutY(360);
        List listofitems = new ArrayList<String>();
        listofitems.add("Single Player");
        listofitems.add("Play With Computer");
        listofitems.add("Severl Players");
        ObservableList<String> observable = FXCollections.observableArrayList(listofitems);
        combobox_type.getItems().addAll(listofitems);
        combobox_type.setValue("Single Player");
        combobox_type.setOnAction(e -> {
            if ("Play With Computer".equals(combobox_type.getValue())) {
                level_computer();
            }
        });
        observable.clear();
        listofitems.clear();
        listofitems.add("Custome Points");
        listofitems.add("Custome Game");
        observable.addAll(listofitems);
        combobox_setting.getItems().addAll(observable);
        observable.clear();
        listofitems.clear();
        listofitems.add("Dump");
        listofitems.add("Smart");
        observable.addAll(listofitems);
        combobox_setting.setPromptText("Customize");
        combobox_setting.setOnAction(e -> {
            setting_customize(combobox_setting.getValue());
            combobox_setting.setPromptText("Customize");
        });
        load_game = new Button("Load Game");
        load_game.setLayoutX(200);
        load_game.setLayoutY(440);
        load_game.setMinSize(200, 35);
        load_game.setFont(new Font("Tahoma", 20));
        load_game.setOnAction(e -> {
            window_loading();
        });
        view_scoreBoard = new Button("ScoreBoard");
        view_scoreBoard.setLayoutX(200);
        view_scoreBoard.setLayoutY(520);
        view_scoreBoard.setMinSize(200, 35);
        view_scoreBoard.setFont(new Font("Tahoma", 20));
        view_scoreBoard.setOnAction(e -> {
            window_score_board();
        });
        Button exit_btn = new Button("Exit");
        exit_btn.setLayoutX(200);
        exit_btn.setLayoutY(600);
        exit_btn.setMinSize(200, 35);
        exit_btn.setFont(new Font("Tahoma", 20));
        exit_btn.setOnAction(e -> {
            click_exit();
        });
        anchor.getChildren().addAll(mines_titel, start_btn, combobox_type, combobox_setting, load_game, view_scoreBoard, exit_btn, about);
        window_primary.setTitle("Mines Weeper");
        window_primary.setScene(scene_window1);
        window_primary.show();
    }

    private void Desgin_window2() {
        Button back_btn = new Button("Back");
        back_btn.setLayoutX(20);
        back_btn.setLayoutY(15);
        back_btn.setFont(new Font("Tahoma", 20));
        back_btn.setId("back");
        back_btn.setOnAction(e -> {
            if (list_of_players.isEmpty()) {
                if (!single_player_state && timer != null && timertask != null) {
                    timertask.cancel();
                    timer.cancel();
                    time = new Label(String.valueOf(number_of_time));
                }
                window_primary.setScene(scene_window1);
                window_primary.show();
            } else {
                click_back();
            }
        });
        Button new_game_btn = new Button("New Game");
        new_game_btn.setFont(new Font("Tahoma", 20));
        new_game_btn.setLayoutX(40);
        new_game_btn.setLayoutY(15);
        new_game_btn.setId("newgame");
        new_game_btn.setOnAction(e -> {
            review = false;
            if (!list_of_players.isEmpty()) {
                new_game();
            } else {
                if (!single_player_state && timer != null && timertask != null) {
                    timertask.cancel();
                    timer.cancel();
                }
                init_new_game();
            }
        });
        save_game = new Button("Save Game");
        save_game.setFont(new Font("Tahoma", 20));
        save_game.setLayoutX(40);
        save_game.setLayoutY(15);
        save_game.setId("savegame");
        save_game.setOnAction(e -> {
            fill_Data();
            Thread thread_output_data = new Thread() {
                @Override
                public void run() {
                    if (list_of_players.isEmpty()) {
                        for (i = 0; i < list_of_names.size(); i++) {
                            if (!list_of_names.get(i).equals("computer")) {
                                list_of_players.add(new GUIPlayer(list_of_names.get(i), 15, i + 1, number_of_weak_sheilds, number_of_strong_sheilds));
                            } else {
                                list_of_players.add(new Computer_Player("computer", 15, i + 1, number_of_weak_sheilds, number_of_strong_sheilds));
                            }
                        }
                        input_and_output_data.set_init_Data(p1, p2, repetition, play_state, list_of_names, list_of_players);
                        String path_score_game = input_and_output_data.OutPut_Data(true); ///name game
                        String file_path = "D:\\Store\\Score_Board\\";
                        output_data_score = new Data_of_ScoreBoard(1, String.valueOf(Data_of_winer_player.get(0)), (int) Data_of_winer_player.get(1), (int) Data_of_winer_player.get(2), (int) Data_of_winer_player.get(3) + (int) Data_of_winer_player.get(4), Integer.valueOf(number_mines.getText()), start_time_of_play, end_time_of_play, list_of_names.size(), path_score_game);
                        File file = new File(file_path);
                        if ((file.list()).length == 0) {
                            output_data_score.Create_File_if_Not_Found();
                        }
                        output_data_score.OutPut_Data();
                    } else {
                        input_and_output_data.set_init_Data(p1, p2, repetition, play_state, list_of_names, list_of_players);
                        input_and_output_data.OutPut_Data(false);
                    }
                }
            };
            thread_output_data.start();
        });

        Quick_save = new Button("Quick Save");
        Quick_save.setFont(new Font("Tahoma", 20));
        Quick_save.setLayoutX(40);
        Quick_save.setLayoutY(15);
        Quick_save.setId("savegame");
        Quick_save.setOnAction(e -> {
            quick_save();
        });
        Button Next_move = new Button("Next Move");
        Next_move.setFont(new Font("Tahoma", 20));
        Next_move.setLayoutX(40);
        Next_move.setLayoutY(15);
        Next_move.setOnAction(e -> {
            int x = moves.get(iterator).get_square().get_x();
            int y = moves.get(iterator).get_square().get_y();
            boolean type_move = moves.get(iterator).get_type();
            check_cordinate(x, y, type_move);
            iterator++;
            if (iterator >= moves.size()) {
                iterator = 0;
                Disable_all_buttons(false);
                Next_move.setDisable(true);
                Clear_list_of_players();
            }
        });
        hbox1 = new HBox(40);
        hbox1.setPadding(new Insets(10, 10, 0, 10));
        hbox1.getChildren().addAll(back_btn, new_game_btn, save_game, Quick_save);
        if (review) {
            hbox1.getChildren().add(Next_move);
            save_game.setDisable(true);
            Quick_save.setDisable(true);
            new_game_btn.setDisable(true);
        }
        hbox = new HBox(30);
        hbox.setPadding(new Insets(0, 0, 0, 10));
        vbox = new VBox();
        current_player = new Label("current player :");
        current_player.setFont(new Font("Tahoma", 20));
        name_current_player = new Label();
        name_current_player.setFont(new Font("Tahoma", 20));
        score = new Label("Current Score :");
        score.setAlignment(Pos.CENTER);
        score.setFont(new Font("Tahoma", 20));
        result_score = new Label();
        result_score.setAlignment(Pos.CENTER);
        if (!review) {
            result_score.setStyle("-fx-text-fill: #ac4400");
        }
        result_score.setFont(new Font("Tahoma", 20));
        number_mines = new Label(String.valueOf(mines));
        number_mines.setFont(new Font("Tahoma", 20));
        number_mines.setAlignment(Pos.CENTER_RIGHT);
        weak_Shields = new Label("Weak Shields : ");
        weak_Shields.setAlignment(Pos.CENTER);
        weak_Shields.setFont(new Font("Tahoma", 20));
        strong_Shields = new Label("Strong Shields : ");
        strong_Shields.setAlignment(Pos.CENTER);
        strong_Shields.setFont(new Font("Tahoma", 20));
        num_weak_Shields = new Label(String.valueOf(number_of_weak_sheilds));
        num_weak_Shields.setFont(new Font("Tahoma", 20));
        num_weak_Shields.setAlignment(Pos.CENTER_RIGHT);
        number_of_mines = new Label("Mines :");
        number_of_mines.setFont(new Font("Tahoma", 20));
        num_strong_Shields = new Label(String.valueOf(number_of_strong_sheilds));
        num_strong_Shields.setFont(new Font("Tahoma", 20));
        num_strong_Shields.setAlignment(Pos.CENTER_RIGHT);
        time_of_play = new Label("Time : ");
        time_of_play.setFont(new Font("Tahoma", 20));
        time = new Label(String.valueOf(number_of_time));
        time.setFont(new Font("Tahoma", 20));
        hbox.getChildren().addAll(current_player, name_current_player, score, result_score, number_of_mines, number_mines, weak_Shields, num_weak_Shields, strong_Shields, num_strong_Shields, time_of_play, time);
        vbox.getChildren().add(grid_);
        vbox.setAlignment(Pos.CENTER);
        grid_.setPadding(new Insets(buttons_padding));
        grid_.setHgap(buttons_padding);
        grid_.setVgap(buttons_padding);
        grid_.setAlignment(Pos.CENTER);
        VBox vbox1 = new VBox(20);
        vbox1.getChildren().addAll(hbox1, hbox);
        vbox1.setAlignment(Pos.CENTER);
        root.setTop(vbox1);
        root.setCenter(vbox);
        root.setStyle("-fx-background-color:#c8e7f8");
        window_primary.setTitle("Mines Weeper");
        window_primary.setScene(scene_window2);
        window_primary.show();
    }

    private void init_window1(String value) {
        switch (value) {
            case "Single Player":
                list_of_players.clear();
                click_single_Player();
                break;
            case "Play With Computer":
                list_of_players.clear();
                click_play_with_computer();

                break;
            case "Severl Players":
                list_of_players.clear();
                window_get_number_of_multi_Player();
                break;
        }
    }

    private void click_new_game() {
        obj_Custom = new Custom(numbered_btn, empty_btn, empty_or_numbered_flood, mine_flag, non_mine_flag, discount, last_addition);
        gui = new Grid(numbered_btn, empty_btn, empty_btn, empty_btn, empty_btn, empty_btn, empty_btn);
        gui.set_List_of_Players(list_of_players);
        cnt = 0;
        ok = true;
        init_squares = false;
        dimension = new Setting_width_height_mines_of_grid();
        dimension.setHeight(height);
        dimension.setNumberofmines(mines);
        dimension.setWidth(width);
        height = dimension.get_height();
        width = dimension.get_width();
        mines = dimension.get_mines();
        grid_button = new Button[width][height];
        gui.initGrid(width, height, mines);
    }

    private void grid_button() {
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                cnt++;
                grid_button[i][j] = new Button();
                grid_button[i][j].setMinHeight(27);
                grid_button[i][j].setMinWidth(35);
                grid_button[i][j].setId("id" + cnt);
                if (!review) {
                    grid_button[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override

                        public void handle(MouseEvent event) {
                            MouseButton button = event.getButton();
                            String id = ((Control) event.getSource()).getId();
                            for (int m = 0; m < width; m++) {
                                for (int n = 0; n < height; n++) {
                                    if (id == grid_button[m][n].getId()) {
                                        x_clicked_btn = m;
                                        y_clicked_btn = n;
                                    }
                                }
                            }
                            if (button == MouseButton.PRIMARY) {
                                if (!init_squares) {
                                    squares = gui.init_array_of_squares(x_clicked_btn, y_clicked_btn);
                                    init_squares = true;
                                }
                                check_cordinate(x_clicked_btn, y_clicked_btn, true);
                            } else if (button == MouseButton.SECONDARY) {
                                if (init_squares) {
                                    check_cordinate(x_clicked_btn, y_clicked_btn, false);
                                }
                            }
                        }
                    });
                }
                grid_.add(grid_button[i][j], j, i);
            }
        }
    }

    private void init_grid(Square squares[][]) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!squares[i][j].IsColored() && ("OpenedEmpty".equals(squares[i][j].getState().getStatus())
                        || "OpenedNumber".equals(squares[i][j].getState().getStatus()))) {
                    String color = Color.return_color(list_of_players.get(repetition).get_color());
                    grid_button[i][j].setStyle("-fx-background-color:#" + color);
                    grid_button[i][j].setDisable(true);
                    squares[i][j].set_color(repetition + 1);
                    squares[i][j].setColored(true);
                }
                if (squares[i][j].IsMine() == 0 && squares[i][j].get_cnt_mine() < 9 && "OpenedNumber".equals(squares[i][j].getState().getStatus())) {
                    grid_button[i][j].setText(String.valueOf(squares[i][j].get_cnt_mine()));
                }
                if (squares[i][j].getis_Shield() && "OpenedEmpty".equals(squares[i][j].getState().getStatus())) {
                    if ("weak".equals(squares[i][j].getWeak_strong())) {
                        Image image = new Image(getClass().getResourceAsStream("weak_shield.png"));
                        grid_button[i][j].setGraphic(new ImageView(image));

                    } else {
                        Image image = new Image(getClass().getResourceAsStream("strong_shield.png"));
                        grid_button[i][j].setGraphic(new ImageView(image));
                    }
                }
            }
        }
    }

    private void check_cordinate(int x, int y, boolean btn_pri_or_sec) {
        if (!review) {
            gui.add_new_move(repetition, btn_pri_or_sec, squares[x][y]);
        }
        if (btn_pri_or_sec) {
            if (squares[x][y].getState().getStatus().equals("Closed")) {
                if (squares[x][y].IsMine() == 1) {
                    pause_time = true;
                    clicked_mine(x, y);
                    pause_time = false;
                    if (!list_of_players.isEmpty()) {
                        show_score_if_he_down();
                    } else {
                        return;
                    }
                    if (!list_of_players.get(repetition).get_name().equals("computer")) {
                        num_weak_Shields.setText(String.valueOf(gui.get_numbers_of_shields(repetition).getKey()));
                        num_strong_Shields.setText(String.valueOf(gui.get_numbers_of_shields(repetition).getValue()));
                    } else {
                        num_weak_Shields.setText("");
                        num_strong_Shields.setText("");
                    }
                    update_GUI_during_review();
                    if (!list_of_players.get(repetition).get_name().equals("computer")) {  // throw for computer play after mine
                        return;
                    }
                } else if (ok == true) {
                    if (!single_player_state && !review) {
                        Timer_ON();
                    }
                    get_time(true);
                    gui.AcceptMove(obj_Custom, x, y, repetition, level);
                    init_grid(gui.get_Square());
                    ok = false;
                } else {
                    gui.call_flood(obj_Custom, x, y, repetition, level);
                }
                gui.addTime_play(number_of_time - t, repetition);
                init_grid(gui.get_Square());
                if (single_player_state) {
                    gui.singleplayer_and_update_score();
                    collector_of_methods();
                } else if (play_with_computer_state) {
                    if (repetition >= list_of_players.size()) {
                        repetition = 0;
                    }

                    gui.get_update_score_current_player_with_computer(repetition);
                    repetition++;
                    t = number_of_time + 1;
                    if (repetition >= list_of_players.size()) {
                        repetition = 0;
                    }
                    update_GUI_during_review();
                    if (!gui.get_state_removed_computer() && "computer".equals(list_of_players.get(repetition).get_name()) && !review) {
                        move_computer();
                    } else {
                        collector_of_methods();
                    }
                    if (repetition >= list_of_players.size()) {
                        repetition = 0;
                    }
                } else if (multiplayer_state) {
                    if (repetition >= list_of_players.size()) {
                        repetition = 0;
                    }
                    gui.multiplayer_and_update_score(repetition);
                    repetition++;
                    if (repetition >= list_of_players.size()) {
                        repetition = 0;
                    }
                    collector_of_methods();
                }
            }
        } else {
            if (squares[x][y].getState().getStatus().equals("Closed")) {
                Image image = new Image(getClass().getResourceAsStream("flag.png"));
                grid_button[x][y].setGraphic(new ImageView(image));
                squares[x][y].set_state("ClosedMarked");
                gui.change_a_squareState(x, y, "ClosedMarked");
                if (squares[x][y].IsMine() == 1) {
                    gui.set_change_score(mine_flag);
                } else {
                    gui.set_change_score(non_mine_flag);
                }
                number_mines.setText(String.valueOf(Integer.valueOf(number_mines.getText()) - 1));
            } else if (squares[x][y].getState().getStatus().equals("ClosedMarked")) {
                grid_button[x][y].setGraphic(null);
                squares[x][y].set_state("Closed");
                gui.change_a_squareState(x, y, "Closed");
                if (squares[x][y].IsMine() == 1) {
                    gui.set_change_score(-1 * mine_flag);
                } else {
                    gui.set_change_score(-1 * non_mine_flag);
                }
                number_mines.setText(String.valueOf(Integer.valueOf(number_mines.getText()) + 1));
            }
            if (single_player_state) {
                gui.singleplayer_and_update_score();
                collector_of_methods();
            } else if (play_with_computer_state) {
                if (repetition >= list_of_players.size()) {
                    repetition = 0;
                }
                gui.get_update_score_current_player_with_computer(repetition);
                repetition++;
                t = number_of_time + 1;
                if (repetition >= list_of_players.size()) {
                    repetition = 0;
                }
                if (!gui.get_state_removed_computer() && "computer".equals(list_of_players.get(repetition).get_name()) && !review) {
                    move_computer();
                }
                if (repetition >= list_of_players.size()) {
                    repetition = 0;
                }
            } else if (multiplayer_state) {
                if (repetition >= list_of_players.size()) {
                    repetition = 0;
                }
                gui.multiplayer_and_update_score(repetition);
                repetition++;
                if (repetition >= list_of_players.size()) {
                    repetition = 0;
                }
                collector_of_methods();
            }
        }

    }

    private void show_score_if_he_down() {
        if (!review) {
            result_score.setText("");
        }
        if (single_player_state) {
            repetition = 0;
        }
        name_current_player.setText(list_of_players.get(repetition).get_name());

        if (list_of_players.get(repetition).get_currentScore() < 10) {
            result_score.setText(String.valueOf(list_of_players.get(repetition).get_currentScore()));
        }
    }

    private void End_Of_Game() {
        int count_of_reminder_square = 0, count_of_mines_non_flag_or_mineexplosive = 0;
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                if ("Closed".equals(squares[i][j].getState().getStatus()) || "MineExplosive".equals(squares[i][j].getState().getStatus()) || "ClosedMarked".equals(squares[i][j].getState().getStatus())) {
                    count_of_reminder_square++;
                }
                if ("ClosedMarked".equals(squares[i][j].getState().getStatus()) || "MineExplosive".equals(squares[i][j].getState().getStatus())) {
                    count_of_mines_non_flag_or_mineexplosive++;
                }
            }
        }
        if (count_of_reminder_square == mines) {
            gui.set_change_score((mines - count_of_mines_non_flag_or_mineexplosive) * last_addition);
            if (single_player_state) {
                gui.singleplayer_and_update_score();
            } else if (play_with_computer_state) {
                gui.get_update_score_current_player_with_computer(repetition);
            } else if (multiplayer_state) {
                if (repetition == 0) {
                    repetition = list_of_players.size() - 1;
                } else {
                    repetition--;
                }
                gui.multiplayer_and_update_score(repetition);
            }
            Window_Won();
            list_of_players.remove(repetition);
            if (!list_of_players.isEmpty()) {
                Window_of_results_players();
            }
            Clear_list_of_players();
        }
    }

    private void Window_of_results_players() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(250);
        window.setHeight(220);
        repetition = 0;
        VBox vb = new VBox(15);
        Label[] name_and_result = new Label[list_of_players.size() + 1];
        Button btn = new Button("موافق");
        btn.setOnAction(e -> {
            window.close();
        });
        Label lb = new Label("تهانينا");
        lb.setFont(new Font("Tahoma", 15));
        vb.getChildren().add(lb);
        for (i = 1; i < list_of_players.size() + 1; i++) {
            name_and_result[i] = new Label();
            Pair<Integer, Integer> p = gui.get_numbers_of_shields(repetition);
            int sum = (p.getKey() + p.getValue()) * 50 + list_of_players.get(i - 1).get_currentScore();
            name_and_result[i].setText(list_of_players.get(i - 1).get_name() + "      : " + sum);
            name_and_result[i].setFont(new Font("Tahoma", 15));
            vb.getChildren().add(name_and_result[i]);
        }
        vb.getChildren().add(btn);
        vb.setAlignment(Pos.CENTER);
        scene = new Scene(vb);
        window.setScene(scene);
        window.show();
    }

    private void move_computer() {
        Thread thread_move_computer = new Thread() {
            @Override
            public void run() {
                if ("Dump".equals(level)) {
                    get_move_of_player_Random();
                } else {
                    get_move_of_player_Smart();
                }
                Platform.runLater(() -> {
                    check_cordinate(x_random_computer, y_random_of_computer, true);
                    collector_of_methods();
                    init_grid(gui.get_Square());
                });
            }
        };
        thread_move_computer.start();
    }

    private void get_move_of_player_Random() {
        Pair<Integer, Integer> p = gui.get_coordinate(true);
        x_random_computer = p.getKey();
        y_random_of_computer = p.getValue();
    }

    private void get_move_of_player_Smart() {
        Pair<Integer, Integer> p = gui.get_coordinate(false);
        x_random_computer = p.getKey();
        y_random_of_computer = p.getValue();
    }

    private void window_get_number_of_multi_Player() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);

        Label lb = new Label("كم عدد اللاعبين"), lb1 = new Label();
        lb1.setStyle("-fx-text-color:#f00513");
        TextField numberplayers = new TextField();
        lb.setFont(new Font(20));
        Button btn = new Button("تحديد");
        Button btn1 = new Button("إلغاء");
        btn.setOnAction(e -> {
            try {
                int num = Integer.parseInt(numberplayers.getText());
            } catch (NumberFormatException ex) {
                lb1.setFont(new Font(15));
                lb1.setText("   هناك خطأ في الإدخال");
                return;
            }
            numberofmultiplayer = Integer.valueOf(numberplayers.getText());
            if (numberofmultiplayer > 8) {
                numberofmultiplayer = 8;
            }
            window.close();
            click_multi_Player();
        });
        btn1.setOnAction(e -> {
            window.close();
        });
        VBox vbox = new VBox(20);
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(btn, btn1);
        vbox.getChildren().addAll(lb1, lb, numberplayers, hbox);
        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 300, 200);
        window.setScene(scene);
        window.showAndWait();

    }

    private void click_single_Player() {
        Stage window;
        Scene scene;
        Label lb = new Label("ادخل اسمك ");
        lb.setFont(new Font(15));
        TextField tf = new TextField();
        tf.setPromptText("  ادخل اسمك");
        Label lb1 = new Label();
        lb1.setStyle("-fx-text-color:red");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        Button btn = new Button("تم");
        Button btn1 = new Button("إلغاء");
        btn.setOnAction(e -> {
            if (tf.getText().isEmpty()) {
                lb1.setFont(new Font(15));
                lb1.setText("ادخل اسم صحيح   ");
                return;
            }
            list_of_players.add(new GUIPlayer(tf.getText(), 15, 1, number_of_weak_sheilds, number_of_strong_sheilds));
            list_of_names.add(list_of_players.get(0).get_name());
            single_player_state = true;
            play_with_computer_state = false;
            multiplayer_state = false;
            init_new_game();
            window.close();
        });
        btn1.setOnAction(e -> {
            window.close();
        });
        VBox vbox = new VBox(20);
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(btn, btn1);
        vbox.getChildren().addAll(lb, lb1, tf, hbox);
        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 250, 250);
        window.setScene(scene);
        window.showAndWait();

    }

    private void click_play_with_computer() {

        Stage window;
        Scene scene;
        Label lb = new Label("ادخل اسمك ");
        lb.setFont(new Font(15));
        TextField tf = new TextField();
        tf.setPromptText("  ادخل اسمك");
        Label lb1 = new Label();
        lb1.setStyle("-fx-text-color:#f00513");
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        Button btn = new Button("تم");
        Button btn1 = new Button("إلغاء");
        btn.setOnAction(e -> {
            if (tf.getText().isEmpty()) {
                lb1.setFont(new Font(15));
                lb1.setText("ادخل اسم صحيح   ");
                return;
            }
            list_of_players.add(new GUIPlayer(tf.getText(), 15, 1, number_of_weak_sheilds, number_of_strong_sheilds));
            list_of_players.add(new Computer_Player("computer", 15, 2, number_of_weak_sheilds, number_of_strong_sheilds));
            for (Player item : list_of_players) {
                list_of_names.add(item.get_name());
            }
            play_with_computer_state = true;
            single_player_state = false;
            multiplayer_state = false;
            init_new_game();
            window.close();
        });
        btn1.setOnAction(e -> {
            window.close();
        });
        VBox vbox = new VBox(20);
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(btn, btn1);
        vbox.getChildren().addAll(lb, lb1, tf, hbox);
        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 250, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    private void click_multi_Player() {
        Stage window;
        Scene scene;
        TextField[] tf = new TextField[numberofmultiplayer];
        VBox vb = new VBox(10);
        ScrollPane sp = new ScrollPane();
        int cnt = 1;
        Label text = new Label();
        text.setStyle("-fx-text-color:#f00513");
        for (i = 0; i < numberofmultiplayer; i++) {
            tf[i] = new TextField();
            tf[i].setPromptText("اسم اللاعب " + (i + 1));
            vb.getChildren().add(tf[i]);
        }
        sp.setContent(vb);
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        Button btn = new Button("تم");
        Button btn1 = new Button("إلغاء");
        btn.setOnAction(e -> {
            for (i = 0; i < numberofmultiplayer; i++) {
                if (tf[i].getText().isEmpty() || tf[i].getText().equals("computer")) {
                    text.setText("   هناك خطأ في الإدخال");
                    return;
                }
            }
            multiplayer_state = true;
            single_player_state = false;
            play_with_computer_state = false;
            for (int i = 0; i < numberofmultiplayer; i++) {
                list_of_players.add(new GUIPlayer(tf[i].getText(), 15, i + 1, number_of_weak_sheilds, number_of_strong_sheilds));
                list_of_names.add(tf[i].getText());
            }
            init_new_game();
            window.close();
        });
        btn1.setOnAction(e -> {
            window.close();
        });
        VBox vbox = new VBox(20);
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(btn, btn1);
        vbox.getChildren().addAll(text, sp, hbox);
        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 300, 200);
        window.setScene(scene);
        window.show();

    }

    private void init_new_game() {
        ok = true;
        repetition = 0;
        removed_comuter = false;
        root = new BorderPane();
        grid_ = new GridPane();
        timer = new Timer();
        scene_window2 = new Scene(root, 1370, 735);
        fill_list_of_players();
        click_new_game();
        grid_button();
        Desgin_window2();
        gui.init_visited();
        name_current_player.setText(list_of_players.get(repetition).get_name());
    }

    private void new_game() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(250);
        window.setHeight(220);
        Label lb1 = new Label("ستتم إعادة اللعبة من جديد");
        lb1.setFont(new Font("Tahoma", 20));
        Label lb2 = new Label("هل أنت متأكد");
        lb2.setFont(new Font("Tahoma", 20));
        Button btn = new Button("موافق");
        Button btn1 = new Button("إلغاء");
        btn1.setOnAction(e -> window.close());
        btn.setOnAction(e -> {
            if (!single_player_state && timertask != null && timertask != null) {
                timertask.cancel();
                timer.cancel();
                time = new Label(String.valueOf(number_of_time));
            }
            init_new_game();
            window.close();
        });
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(btn, btn1);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(lb1, lb2, hbox);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.show();
    }

    private void click_back() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(250);
        window.setHeight(220);
        Label lb1 = new Label("ستتم إعادة اللعبة من جديد");
        lb1.setFont(new Font("Tahoma", 20));
        Label lb2 = new Label("هل أنت متأكد");
        lb2.setFont(new Font("Tahoma", 20));
        Button btn = new Button("موافق");
        Button btn1 = new Button("إلغاء");
        btn1.setOnAction(e -> window.close());
        btn.setOnAction(e -> {
            if (!single_player_state && timer != null && timertask != null) {
                timertask.cancel();
                timer.cancel();
            }
            window_primary.setScene(scene_window1);
            window_primary.show();
            window.close();
        });
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(btn, btn1);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(lb1, lb2, hbox);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

    private void Window_Won() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(500);
        Pair<Integer, Integer> p = gui.get_numbers_of_shields(repetition);
        int sum = (p.getKey() + p.getValue()) * 50 + gui.get_score_detected_player(repetition);
        window.setHeight(220);
        Label lb = new Label("لقد فاز اللاعب " + list_of_players.get(repetition).get_name() + " الذي يملك النتيجة ");
        lb.setFont(new Font(20));
        Label lb1 = new Label(String.valueOf(sum));
        lb1.setFont(new Font(20));
        Button btn = new Button("موافق");
        btn.setOnAction(e -> {
            Data_of_winer_player = new ArrayList<>();
            Data_of_winer_player.add(list_of_players.get(repetition).get_name());
            Data_of_winer_player.add(sum);
            Data_of_winer_player.add(gui.getTime_play(repetition));
            Data_of_winer_player.add(list_of_players.get(repetition).getWeak_shield_num());
            Data_of_winer_player.add(list_of_players.get(repetition).getStrong_shield_num());
            get_time(false);
            Disable_all_buttons(true);
            window.close();
        });
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(lb, lb1, btn);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

    private void click_exit() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(250);
        window.setHeight(220);
        Label lb = new Label("هل أنت متأكد من الخروج ؟");
        lb.setFont(new Font(20));
        Button btn = new Button("خروج");
        Button btn1 = new Button("إلغاء");
        btn.setOnAction(e -> {
            window.close();
            window_primary.close();
            if (!single_player_state && timer != null) {
                timertask.cancel();
                timer.cancel();
            }
            input_and_output_data.delete_file();
            Platform.exit();
        });
        btn1.setOnAction(e -> {
            window.close();
        });
        VBox vbox = new VBox(20);
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(btn, btn1);
        vbox.getChildren().addAll(lb, hbox);
        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

    private void click_about() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setTitle("About");
        window.setWidth(500);
        window.setHeight(220);
        Label lb = new Label("أهلا وسهلا بك في لعبة كانسة الألغام");
        lb.setFont(new Font(20));
        Label lb1 = new Label("تم إنشاء التطبيق  من قبل خليل خلف " + "\n" + "Email : Bestmind11111@gmail.com");
        lb1.setFont(new Font(20));
        Button btn = new Button("موافق");
        btn.setMinSize(45, 10);
        btn.setOnAction(e -> {
            window.close();
        });
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(lb, lb1, btn);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

    private void check_shield() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(500);
        window.setHeight(220);
        Label lb1 = new Label("هل تريد الخصم من عدد الدروع ؟");
        lb1.setFont(new Font(20));
        HBox hb = new HBox(20);
        Button btn1 = new Button("لا");
        btn1.setOnAction(e -> {
            window.close();
        });
        btn1.setMinSize(45, 10);
        Button btn2 = new Button("نعم");
        btn2.setMinSize(45, 10);
        btn2.setOnAction(e -> {
            Edit_numbers_of_shields();
            window.close();
        });
        hb.getChildren().addAll(btn2, btn1);
        hb.setPadding(new Insets(0,0,0,175));
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(lb1, hb);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

    private void Edit_numbers_of_shields() {
        if (list_of_players.get(repetition).getStrong_shield_num() > 0) {
            gui.change_number_of_shields(repetition, "strong");
            gui.set_clicked_mine_of_move(1);
            gui.set_change_score(0);
        } else {
            gui.change_number_of_shields(repetition, "weak");
            gui.set_change_score(new weak_shield().decrease());
            gui.set_clicked_mine_of_move(1);
        }
        if (single_player_state) {
            gui.singleplayer_and_update_score();
        } else if (play_with_computer_state) {
            gui.get_update_score_current_player_with_computer(repetition);
        } else if (multiplayer_state) {
            gui.multiplayer_and_update_score(repetition);
        }
        repetition++;
        if (repetition == list_of_players.size()) {
            repetition = 0;
        }
        ok1 = true;
    }

    private void clicked_mine(int x, int y) {
        squares[x][y].set_state("MineExplosive");
        grid_button[x][y].setDisable(true);
        String color = Color.return_color(list_of_players.get(repetition).get_color());
        grid_button[x][y].setStyle("-fx-background-color:#" + color);
        Image image = new Image(getClass().getResourceAsStream("mine_explosive.png"));
        grid_button[x][y].setGraphic(new ImageView(image));
        number_mines.setText(String.valueOf(Integer.valueOf(number_mines.getText()) - 1));
        if (review) {
            if (iterator >= moves.size() - 1 || (list_of_players.get(repetition).get_name().equals("computer") && !level.equals("Smart"))) {
                result_score.setText(String.valueOf(gui.get_score_detected_player(repetition)));
                gui.delete_an_player(repetition);
                list_of_players.remove(repetition);
                repetition++;
                if (repetition >= list_of_players.size()) {
                    repetition = 0;
                }
                if (list_of_players.isEmpty()) {
                    Disable_all_buttons(false);
                    Clear_list_of_players();
                }
            } else if (moves.get(iterator).isClicked_mine() == 1) {
                Edit_numbers_of_shields();
            } else if (moves.get(iterator).isClicked_mine() == 2) {
                gui.set_change_score(-1 * discount);
                if (single_player_state) {
                    gui.singleplayer_and_update_score();
                } else if (play_with_computer_state) {
                    gui.get_update_score_current_player_with_computer(repetition);
                } else if (multiplayer_state) {
                    gui.multiplayer_and_update_score(repetition);
                    repetition++;
                    if (repetition == list_of_players.size()) {
                        repetition = 0;
                    }
                }
            }
            return;
        }
        if (level.equals("Smart") || !list_of_players.get(repetition).get_name().equals("computer")) {
            if (list_of_players.get(repetition).getStrong_shield_num() > 0 || list_of_players.get(repetition).getWeak_shield_num() > 0) {
                ok1 = false;
                if (!list_of_players.get(repetition).get_name().equals("computer")) {
                    check_shield();
                } else {
                    Edit_numbers_of_shields();
                }
                if (ok1) {
                    return;
                }
            }
        }
        if (play_with_computer_state && "computer".equals(list_of_players.get(repetition).get_name())) {
            if (list_of_players.get(repetition).get_currentScore() >= discount) {
                gui.set_change_score(-1 * discount);
                gui.set_clicked_mine_of_move(2);
            } else {
                if (list_of_players.size() == 1) {
                    Data_of_winer_player = new ArrayList<>();
                    Pair<Integer, Integer> p = gui.get_numbers_of_shields(repetition);
                    int sum = (p.getKey() + p.getValue()) * 50 + gui.get_score_detected_player(repetition);
                    Data_of_winer_player.add(list_of_players.get(0).get_name());
                    Data_of_winer_player.add(sum);
                    Data_of_winer_player.add(gui.getTime_play(repetition));
                    Data_of_winer_player.add(list_of_players.get(0).getWeak_shield_num());
                    Data_of_winer_player.add(list_of_players.get(0).getStrong_shield_num());
                }
                gui.removed_computer();
                removed_comuter = true;
                Display_score(repetition);
                gui.delete_an_player(repetition);
                list_of_players.remove(repetition);
                if (list_of_players.isEmpty()) {
                    get_time(false);
                    Disable_all_buttons(false);
                    get_time(false);
                    Clear_list_of_players();
                }
                if (list_of_players.size() == repetition) {
                    repetition = 0;
                } else {
                    repetition++;
                }
            }
            return;
        }
        Scene scene1;
        Stage window;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(450);
        window.setHeight(220);
        Label lb = new Label("لقد خسرت اللعبة");
        Label lb1 = new Label("إن أردت تكملة اللعبة فسيتم خصم");
        Label lb2 = new Label("  من الربح " + discount);
        lb.setFont(new Font(20));
        lb1.setFont(new Font(20));
        lb2.setFont(new Font(20));
        Button btn = new Button("موافق");
        Button btn1 = new Button("إلغاء");
        btn1.setOnAction(e -> {
            if (list_of_players.size() == 1) {
                Data_of_winer_player = new ArrayList<>();
                Pair<Integer, Integer> p = gui.get_numbers_of_shields(repetition);
                int sum = (p.getKey() + p.getValue()) * 50 + gui.get_score_detected_player(repetition);
                Data_of_winer_player.add(list_of_players.get(0).get_name());
                Data_of_winer_player.add(sum);
                Data_of_winer_player.add(gui.getTime_play(repetition));
                Data_of_winer_player.add(list_of_players.get(0).getWeak_shield_num());
                Data_of_winer_player.add(list_of_players.get(0).getStrong_shield_num());
            }
            gui.set_clicked_mine_of_move(0);
            Display_score(repetition);
            list_of_players.remove(repetition);
            repetition++;
            if (repetition >= list_of_players.size()) {
                repetition = 0;
            }
            if (list_of_players.isEmpty()) {
                get_time(false);
                Disable_all_buttons(false);
                Clear_list_of_players();
            }
            window.close();
            return;
        });
        btn.setOnAction(e -> {
            if (gui.get_score_detected_player(repetition) >= discount) {
                gui.set_change_score(-1 * discount);
                gui.set_clicked_mine_of_move(2);
                number_mines.setText(String.valueOf(Integer.valueOf(number_mines.getText()) - 1));

                if (single_player_state) {
                    gui.singleplayer_and_update_score();
                } else if (play_with_computer_state) {
                    gui.get_update_score_current_player_with_computer(repetition);
                } else if (multiplayer_state) {
                    gui.multiplayer_and_update_score(repetition);
                    repetition++;
                    if (repetition == list_of_players.size()) {
                        repetition = 0;
                    }
                }
                window.close();
            } else {
                Scene scene2;
                Label label = new Label("لا تملك ربحا كافيا");
                Label label1 = new Label("هل تود اللعب بقيمة سالبة ؟");
                label.setFont(new Font(20));
                label1.setFont(new Font(20));
                Button btn2 = new Button("موافق");
                Button cancel = new Button("إلغاء");
                VBox vbox = new VBox(20);
                HBox hb = new HBox(15);
                hb.setAlignment(Pos.CENTER);
                hb.getChildren().addAll(btn2, cancel);
                vbox.getChildren().addAll(label, label1, hb);
                vbox.setAlignment(Pos.CENTER);
                scene2 = new Scene(vbox);
                window.setScene(scene2);
                cancel.setOnAction(ev -> {
                    if (list_of_players.size() == 1) {
                        Data_of_winer_player = new ArrayList<>();
                        Pair<Integer, Integer> p = gui.get_numbers_of_shields(repetition);
                        int sum = (p.getKey() + p.getValue()) * 50 + gui.get_score_detected_player(repetition);
                        Data_of_winer_player.add(list_of_players.get(0).get_name());
                        Data_of_winer_player.add(sum);
                        Data_of_winer_player.add(gui.getTime_play(repetition));
                        Data_of_winer_player.add(list_of_players.get(0).getWeak_shield_num());
                        Data_of_winer_player.add(list_of_players.get(0).getStrong_shield_num());
                    }
                    gui.set_clicked_mine_of_move(0);
                    Display_score(repetition);
                    gui.delete_an_player(repetition);
                    list_of_players.remove(repetition);
                    if (repetition == list_of_players.size()) {
                        repetition = 0;
                    }
                    if (list_of_players.size() == 0) {
                        get_time(false);
                        Disable_all_buttons(false);
                        get_time(false);
                        Clear_list_of_players();
                    }
                    window.close();
                });
                btn2.setOnAction(e1 -> {
                    gui.set_change_score(-1 * discount);
                    gui.set_clicked_mine_of_move(2);
                    if (single_player_state) {
                        gui.singleplayer_and_update_score();
                    }
                    if (play_with_computer_state) {
                        gui.get_update_score_current_player_with_computer(repetition);
                    } else if (multiplayer_state) {
                        gui.multiplayer_and_update_score(repetition);
                        repetition++;
                        if (repetition == list_of_players.size()) {
                            repetition = 0;
                        }
                    }
                    window.close();
                    return;
                });
            }
        });
        VBox vbox = new VBox(20);
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(btn, btn1);
        vbox.getChildren().addAll(lb, lb1, lb2, hbox);
        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        scene1 = new Scene(vbox);
        window.setScene(scene1);
        window.showAndWait();
    }

    private void Display_score(int index) {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(350);
        window.setHeight(220);
        Pair<Integer, Integer> p = gui.get_numbers_of_shields(repetition);
        int sum = (p.getKey() + p.getValue()) * 50 + gui.get_score_detected_player(repetition);
        Label lb1 = new Label("النتيجة النهائية للاعب " + list_of_players.get(index).get_name());
        Label lb2 = new Label(String.valueOf(sum));
        lb2.setFont(new Font(20));
        lb1.setFont(new Font(20));
        Button btn = new Button("موافق");
        btn.setOnAction(e -> {
            window.close();
        });
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(lb1, lb2, btn);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

    private void Disable_all_buttons(boolean IsWon) {
        if (IsWon) {
            for (i = 0; i < width; i++) {
                for (j = 0; j < height; j++) {
                    grid_button[i][j].setDisable(true);
                    if (squares[i][j].IsMine() == 1 && "Closed".equals(squares[i][j].getState().getStatus())) {
                        Image image = new Image(getClass().getResourceAsStream("flag.png"));
                        grid_button[i][j].setText("");
                        grid_button[i][j].setGraphic(new ImageView(image));
                    }
                }
            }
        } else {
            for (i = 0; i < width; i++) {
                for (j = 0; j < height; j++) {
                    grid_button[i][j].setDisable(true);
                    if (squares[i][j].IsMine() == 1 && "Closed".equals(squares[i][j].getState().getStatus())) {
                        Image image = new Image(getClass().getResourceAsStream("mine_non_explosive.png"));
                        grid_button[i][j].setText("");
                        grid_button[i][j].setGraphic(new ImageView(image));
                    }
                }
            }
        }
        if (review) {
            int sum = (Integer.valueOf(num_weak_Shields.getText()) + Integer.valueOf(num_strong_Shields.getText())) * 50 + Integer.valueOf(result_score.getText());
            result_score.setText(String.valueOf(sum));
        }
        Quick_save.setDisable(true);
    }

    private void Clear_list_of_players() {
        list_of_players.clear();
    }

    private void setting_customize(String choice) {
        if ("Custome Points".equals(choice)) {
            setting_customize_points();
        } else {
            setting_customize_game();
        }
    }

    private void setting_customize_points() {
        Design_customize_points_setting();
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setWidth(300);
        window.setHeight(500);
        set_setting.setOnAction(e -> {
            if (!tf[0].getText().equals("مبهمة")) {
                label_text.setFont(new Font(15));
                label_text.setText("   هناك خطأ في الإدخال");
                return;
            }
            for (i = 1; i < 7; i++) {
                try {
                    int num = Integer.parseInt(tf[i].getText());
                } catch (NumberFormatException ex) {
                    label_text.setFont(new Font(15));
                    label_text.setText("   هناك خطأ في الإدخال");
                    return;
                }
            }

            init_parameters();
            window.close();
        });
        cancel_setting.setOnAction(e -> {
            window.close();
        });
        scene = new Scene(vb_setting);
        window.setScene(scene);
        window.showAndWait();

    }

    private void setting_customize_game() {
        Design_customize_game_setting();
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setWidth(250);
        window.setHeight(420);
        set_setting_1.setOnAction(e -> {
            for (i = 0; i < 6; i++) {
                try {
                    int num = Integer.parseInt(tf1[i].getText());
                } catch (NumberFormatException ex) {
                    label_text1.setFont(new Font(15));
                    label_text1.setText("   هناك خطأ في الإدخال");
                    return;
                }
            }
            init_values();
            window.close();
        });
        cancel_setting_1.setOnAction(e -> {
            window.close();
        });
        scene = new Scene(vb_vbox);
        window.setScene(scene);
        window.showAndWait();

    }

    private void Design_customize_points_setting() {

        vb_setting = new VBox(50);
        label_text = new Label();
        label_text.setStyle("-fx-text-color:#f00513");
        Label[] points = new Label[7];
        tf = new TextField[7];
        VBox vb1 = new VBox(15), vb2 = new VBox(20);
        HBox hb1 = new HBox(40), hb2 = new HBox(20);
        set_setting = new Button("إعداد");
        cancel_setting = new Button("إلغاء");
        for (i = 0; i < 7; i++) {
            points[i] = new Label();
            tf[i] = new TextField();
            tf[i].setMaxWidth(50);
            points[i].setFont(new Font("Tahoma", 15));
            vb1.getChildren().add(tf[i]);
            vb2.getChildren().add(points[i]);
        }
        tf[0].setText("مبهمة");
        tf[1].setText("10");
        tf[2].setText("1");
        tf[3].setText("5");
        tf[4].setText("-1");
        tf[5].setText("250");
        tf[6].setText("100");
        points[0].setText("مربع مرقم بجوار لغم " + ":");
        points[1].setText("مربع فارغ " + ":");
        points[2].setText("مربع فارغ أو مرقم أثناء البحث " + ":");
        points[3].setText("تعليم لغم " + ":");
        points[4].setText("تعليم مربع فارغ " + ":");
        points[5].setText("الخصم أثناء فتح لغم" + ":");
        points[6].setText("الإضافة لكل لغم غير معلم " + ":");
        hb1.getChildren().addAll(set_setting, cancel_setting);
        hb2.getChildren().addAll(vb1, vb2);
        vb_setting.getChildren().addAll(label_text, hb2, hb1);
        vb1.setAlignment(Pos.CENTER);
        hb1.setAlignment(Pos.CENTER);
        vb2.setAlignment(Pos.CENTER_RIGHT);
        hb2.setAlignment(Pos.CENTER);
        vb_setting.setAlignment(Pos.CENTER);
    }

    private void Design_customize_game_setting() {
        vb_setting = new VBox(50);
        label_text1 = new Label();
        label_text1.setStyle("-fx-text-color:#f00513");
        Label[] Title = new Label[6];
        tf1 = new TextField[6];
        VBox vb1 = new VBox(10), vb2 = new VBox(20);
        vb_vbox = new VBox(50);
        HBox hb1 = new HBox(40), hb2 = new HBox(20);
        set_setting_1 = new Button("إعداد");
        cancel_setting_1 = new Button("إلغاء");
        for (i = 0; i < 6; i++) {
            Title[i] = new Label();
            tf1[i] = new TextField();
            tf1[i].setMaxWidth(40);
            Title[i].setFont(new Font("Tahoma", 15));
            vb1.getChildren().add(tf1[i]);
            vb2.getChildren().add(Title[i]);
        }
        Title[0].setText("عدد الأسطر " + ":");
        Title[1].setText("عدد الأعمدة " + ":");
        Title[2].setText("عدد الألغام " + ":");
        Title[3].setText("عددالدروع الضعيفة " + ":");
        Title[4].setText("عدد الدروع القوية " + ":");
        Title[5].setText("عدد وقت اللعب " + ":");
        tf1[0].setText("20");
        tf1[1].setText("20");
        tf1[2].setText("50");
        tf1[3].setText("1");
        tf1[4].setText("2");
        tf1[5].setText("10");
        hb1.getChildren().addAll(set_setting_1, cancel_setting_1);
        hb2.getChildren().addAll(vb1, vb2);
        vb_vbox.getChildren().addAll(label_text1, hb2, hb1);
        vb1.setAlignment(Pos.CENTER);
        hb1.setAlignment(Pos.CENTER);
        vb2.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        vb_vbox.setAlignment(Pos.CENTER);
    }

    private void fill_list_of_players() {
        list_of_players.clear();
        int color = 1;
        for (String item : list_of_names) {
            if ("computer".equals(item)) {
                list_of_players.add(new Computer_Player(item, 15, color, number_of_weak_sheilds, number_of_strong_sheilds));
            } else {
                list_of_players.add(new GUIPlayer(item, 15, color, number_of_weak_sheilds, number_of_strong_sheilds));
            }
            color++;
        }
    }

    private void init_parameters() {
        if ("مبهمة".equals(tf[0].getText())) {
            numbered_btn = -1;
        } else {
            numbered_btn = Integer.valueOf(tf[0].getText());
        }
        empty_btn = Integer.valueOf(tf[1].getText());
        empty_or_numbered_flood = Integer.valueOf(tf[2].getText());
        mine_flag = Integer.valueOf(tf[3].getText());
        non_mine_flag = Integer.valueOf(tf[4].getText());
        discount = Integer.valueOf(tf[5].getText());
        last_addition = Integer.valueOf(tf[6].getText());
    }

    private void init_values() {
        width = Integer.valueOf(tf1[0].getText());
        if (width > 25) {
            width = 25;
        }
        height = Integer.valueOf(tf1[1].getText());
        if (height > 45) {
            width = 45;
        }
        mines = Integer.valueOf(tf1[2].getText());
        if (mines > 589) {
            mines = 589;
        }
        number_of_weak_sheilds = Integer.parseInt(tf1[3].getText());
        number_of_strong_sheilds = Integer.parseInt(tf1[4].getText());
        number_of_time = Integer.parseInt(tf1[5].getText());
    }

    private void level_computer() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(200);
        window.setHeight(250);
        Label lb = new Label();
        Button btn = new Button("موافق");
        Button btn1 = new Button("إلغاء");
        RadioButton level_dump = new RadioButton("Dump");
        level_dump.setAlignment(Pos.CENTER_LEFT);
        level_dump.setPadding(new Insets(0, 0, 0, 15));
        RadioButton level_random = new RadioButton("Smart");
        level_random.setAlignment(Pos.CENTER_LEFT);
        level_random.setPadding(new Insets(0, 0, 0, 15));
        level_dump.setSelected(true);
        btn1.setOnAction(e -> window.close());
        btn.setOnAction(e -> {
            if (level_dump.isSelected() && !level_random.isSelected()) {
                level = level_dump.getText();
            } else if (level_random.isSelected() && !level_dump.isSelected()) {
                level = level_random.getText();
            } else {
                lb.setStyle("-fx-text-color:#f00513");
                lb.setFont(new Font(15));
                lb.setText("اختر احدى المستويات      ");
                return;
            }
            window.close();
        });
        VBox vbox1 = new VBox(20);
        vbox1.getChildren().addAll(lb, level_dump, level_random);
        vbox1.setAlignment(Pos.CENTER_LEFT);
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(btn, btn1);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(vbox1, hbox);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.show();
    }

    private void collector_of_methods() {
        if (!list_of_players.isEmpty()) {
            if (!list_of_players.get(repetition).get_name().equals("computer")) {
                num_weak_Shields.setText(String.valueOf(gui.get_numbers_of_shields(repetition).getKey()));
                num_strong_Shields.setText(String.valueOf(gui.get_numbers_of_shields(repetition).getValue()));
            } else {
                num_weak_Shields.setText("");
                num_strong_Shields.setText("");
            }
            update_GUI_during_review();
        }
        if (!list_of_players.isEmpty()) {
            if (!"computer".equals(list_of_players.get(repetition).get_name())) {
                show_score_if_he_down();
            }
        }
        End_Of_Game();
    }

    private void Timer_ON() {
        prev_rep = repetition;
        t = number_of_time;
        timertask = new TimerTask() {
            @Override
            public void run() {
                if (prev_rep == repetition && !pause_time) {
                    t--;
                    if (t == -1) {
                        t = number_of_time;
                        repetition++; // do some thing
                        if (repetition >= list_of_players.size()) {
                            repetition = 0;
                        }
                        if (!list_of_players.isEmpty()) {
                            if (list_of_players.get(repetition).get_name().equals("computer")) {
                                Platform.runLater(() -> {
                                    move_computer();
                                });
                            } else {
                                Platform.runLater(() -> {
                                    collector_of_methods();
                                });
                            }
                        }
                    }
                } else {
                    t = number_of_time;
                    prev_rep = repetition;
                }
                Platform.runLater(() -> {
                    if (list_of_players.size() <= 1) {
                        t = number_of_time;
                    }
                    time.setText(String.valueOf(t));
                });
            }
        };
        timer.scheduleAtFixedRate(timertask, 1000, 1000);
    }

    private void fill_Data() {
        setting_game = new ArrayList<>();
        setting_points = new ArrayList<>();
        ArrayList<PlayerMove> moves;
        moves = gui.get_PlayerMove_List();
        p1 = new Pair<>(squares, moves);
        if (tf[0].getText().equals("مبهمة")) {
            setting_points.add(-1);
        } else {
            setting_points.add(Integer.valueOf(tf[0].getText()));
        }
        for (i = 0; i < 6; i++) {
            setting_points.add(Integer.valueOf(tf[i + 1].getText()));
        }
        setting_game.add(width);
        setting_game.add(height);
        setting_game.add(number_of_time);
        setting_game.add(Integer.valueOf(number_mines.getText()));
        setting_game.add(number_of_strong_sheilds);
        setting_game.add(number_of_weak_sheilds);
        if (level.equals("Dump")) {
            setting_game.add(0);
        } else {
            setting_game.add(1);
        }
        if (removed_comuter) {
            setting_game.add(1);
        } else {
            setting_game.add(0);
        }
        setting_game.add(mines);
        p2 = new Pair<>(setting_points, setting_game);
        if (single_player_state) {
            play_state = "Single_Player_State";
        } else if (play_with_computer_state) {
            play_state = "Play_With_Computer_State";
        } else {
            play_state = "Multiplayer_State";
        }
    }

    private void set_game() {
        ok = false;
        init_squares = true;
        String state = input_and_output_data.getPlay_state();
        if (null == state) {
            multiplayer_state = true;
            single_player_state = play_with_computer_state = false;
        } else {
            switch (state) {
                case "Single_Player_State":
                    single_player_state = true;
                    play_with_computer_state = multiplayer_state = false;
                    break;
                case "Play_With_Computer_State":
                    play_with_computer_state = true;
                    single_player_state = multiplayer_state = false;
                    break;
                default:
                    multiplayer_state = true;
                    single_player_state = play_with_computer_state = false;
                    break;
            }
        }
        list_of_names = input_and_output_data.getList_of_names();
        list_of_players = input_and_output_data.getList_of_player();
        squares = input_and_output_data.getP1().getKey();
        ArrayList<Integer> list_ = input_and_output_data.getP2().getValue();
        width = list_.get(0);
        height = list_.get(1);
        number_of_time = list_.get(2);
        number_mines = new Label(String.valueOf(list_.get(3)));
        dimension = new Setting_width_height_mines_of_grid();
        dimension.setHeight(height);
        dimension.setNumberofmines(mines);
        dimension.setWidth(width);
        number_of_strong_sheilds = list_.get(4);
        number_of_weak_sheilds = list_.get(5);
        moves = input_and_output_data.getP1().getValue();
        if (list_.get(6) == 0) {
            level = "Dump";
        } else {
            level = "Smart";
        }
        if (list_.get(7) == 1) {
            removed_comuter = true;
        } else {
            removed_comuter = false;
        }
        mines = list_.get(8);
        list_ = input_and_output_data.getP2().getKey();
        numbered_btn = list_.get(0);
        empty_btn = list_.get(1);
        empty_or_numbered_flood = list_.get(2);
        mine_flag = list_.get(3);
        non_mine_flag = list_.get(4);
        discount = list_.get(5);
        last_addition = list_.get(6);
    }

    private void init_grid_when_load_game_not_complete(Square squares[][]) {
        root = new BorderPane();
        grid_ = new GridPane();
        timer = new Timer();
        click_new_game();
        if (removed_comuter) {
            gui.removed_computer();
        }
        init_squares = true;
        gui.set_PlayerMove_List(input_and_output_data.getP1().getValue());
        gui.setSquares(squares);
        grid_button();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (squares[i][j].IsColored()) {
                    String color = Color.return_color(squares[i][j].get_color());
                    grid_button[i][j].setStyle("-fx-background-color:#" + color);
                    grid_button[i][j].setDisable(true);
                }
                if ("OpenedNumber".equals(squares[i][j].getState().getStatus())) {
                    grid_button[i][j].setText(String.valueOf(squares[i][j].get_cnt_mine()));
                }
                if (squares[i][j].getis_Shield() && "OpenedEmpty".equals(squares[i][j].getState().getStatus())) {
                    if ("weak".equals(squares[i][j].getWeak_strong())) {
                        Image image = new Image(getClass().getResourceAsStream("weak_shield.png"));
                        grid_button[i][j].setGraphic(new ImageView(image));

                    } else {
                        Image image = new Image(getClass().getResourceAsStream("strong_shield.png"));
                        grid_button[i][j].setGraphic(new ImageView(image));
                    }
                }
                if ("MineExplosive".equals(squares[i][j].getState().getStatus())) {
                    Image image = new Image(getClass().getResourceAsStream("mine_explosive.png"));
                    grid_button[i][j].setGraphic(new ImageView(image));
                }
                if ("ClosedMarked".equals(squares[i][j].getState().getStatus())) {
                    Image image = new Image(getClass().getResourceAsStream("flag.png"));
                    grid_button[i][j].setGraphic(new ImageView(image));
                }
            }
        }
        scene_window2 = new Scene(root, 1370, 725);
        Desgin_window2();
        gui.init_visited();
        name_current_player.setText(list_of_players.get(repetition).get_name());
        collector_of_methods();
    }

    private void init_grid_when_load_game_complete(Square squares[][]) {
        repetition = 0;
        removed_comuter = false;
        review = true;
        root = new BorderPane();
        grid_ = new GridPane();
        timer = new Timer();
        click_new_game();
        init_squares = true;
        gui.set_PlayerMove_List(input_and_output_data.getP1().getValue());
        gui.setSquares(squares);
        grid_button();
        for (i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid_button[i][j].setGraphic(null);
                squares[i][j].set_state("Closed");
                if (squares[i][j].IsMine() == 1) {
                    Image image = new Image(getClass().getResourceAsStream("mine_non_explosive.png"));
                    grid_button[i][j].setGraphic(new ImageView(image));
                }
            }
        }
        scene_window2 = new Scene(root, 1370, 725);
        Desgin_window2();
        gui.set_color_squares();
        gui.init_visited();
        name_current_player.setText(list_of_players.get(repetition).get_name());
        collector_of_methods();
    }

    private void window_loading() {
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setWidth(500);
        window.setHeight(220);
        Label lb1 = new Label("هل تريد استعراض لعبة منتهية أم استئناف لعبة سابقة ؟");
        lb1.setFont(new Font("Tahoma", 20));
        Button btn1 = new Button("إلغاء");
        Button btn2 = new Button("استئناف");
        Button btn3 = new Button("استئناف لعبة مؤقتة");
        Button btn4 = new Button("استعراض");
        btn1.setOnAction(e -> window.close());
        btn2.setOnAction(e -> {
            String file_path = "D:\\Store\\Not_Game_Complete";
            File file = new File(file_path);
            if ((file.list()).length == 0) {
                Stage window1;
                Scene scene1;
                window1 = new Stage();
                window1.initModality(Modality.APPLICATION_MODAL);
                window1.initStyle(StageStyle.UNDECORATED);
                window1.setWidth(550);
                window1.setHeight(220);
                Label lb = new Label("لا توجد ألعاب محفوظة من أجل استئنافها  ؟؟؟");
                lb.setFont(new Font("Tahoma", 20));
                Button btn = new Button("موافق");
                btn.setOnAction(lamda -> {
                    window1.close();
                });
                VBox vbox = new VBox(20);
                vbox.getChildren().addAll(lb, btn);
                vbox.setAlignment(Pos.CENTER);
                scene1 = new Scene(vbox);
                window1.setScene(scene1);
                window1.showAndWait();
            } else {
                FileChooser chooser = new FileChooser();
                file1 = new File(file_path);
                chooser.setInitialDirectory(file1);
                file1 = chooser.showOpenDialog(null);
                if (file1 != null) {
                    p1 = new Pair<>(null, null);
                    p2 = new Pair<>(null, null);
                    Thread thread_input_data = new Thread() {
                        @Override
                        public void run() {
                            input_and_output_data.set_init_Data(p1, p2, repetition, play_state, list_of_names, list_of_players);
                            input_and_output_data.InPut_Data(file1.getAbsolutePath());
                            Platform.runLater(() -> {
                                set_game();
                                init_grid_when_load_game_not_complete(squares);
                            });
                        }
                    };
                    thread_input_data.start();  
                    window.close();
                }
            }
        });
        btn3.setOnAction(e -> {
            String file_path = "D:\\Store";
            File file = new File(file_path);
            if ((file.list()).length <= 3) {
                Stage window1;
                Scene scene1;
                window1 = new Stage();
                window1.initModality(Modality.APPLICATION_MODAL);
                window1.initStyle(StageStyle.UNDECORATED);
                window1.setWidth(550);
                window1.setHeight(220);
                Label lb = new Label("لا توجد ألعاب مؤقتة من أجل استئنافها  ؟؟؟");
                lb.setFont(new Font("Tahoma", 20));
                Button btn = new Button("موافق");
                btn.setOnAction(lamda -> {
                    window1.close();
                });
                VBox vbox = new VBox(20);
                vbox.getChildren().addAll(lb, btn);
                vbox.setAlignment(Pos.CENTER);
                scene1 = new Scene(vbox);
                window1.setScene(scene1);
                window1.showAndWait();
            } else {
                p1 = new Pair<>(null, null);
                p2 = new Pair<>(null, null);
                Thread thread_input_data = new Thread() {
                    @Override
                    public void run() {
                        input_and_output_data.set_init_Data(p1, p2, repetition, play_state, list_of_names, list_of_players);
                        input_and_output_data.InPut_Data_to_tempfile();
                        Platform.runLater(() -> {
                            set_game();
                            init_grid_when_load_game_not_complete(squares);
                        });
                    }
                };
                thread_input_data.start();
                window.close();
            }
        });
        btn4.setOnAction(e -> {
            String file_path = "D:\\Store\\Game_Complete";
            File file = new File(file_path);
            if ((file.list()).length == 0) {
                Stage window1;
                Scene scene1;
                window1 = new Stage();
                window1.initModality(Modality.APPLICATION_MODAL);
                window1.initStyle(StageStyle.UNDECORATED);
                window1.setWidth(550);
                window1.setHeight(220);
                Label lb = new Label("لا توجد ألعاب منتهية من أجل استعراضها  ؟؟؟");
                lb.setFont(new Font("Tahoma", 20));
                Button btn = new Button("موافق");
                btn.setOnAction(lamda -> {
                    window1.close();
                });
                VBox vbox = new VBox(20);
                vbox.getChildren().addAll(lb, btn);
                vbox.setAlignment(Pos.CENTER);
                scene1 = new Scene(vbox);
                window1.setScene(scene1);
                window1.showAndWait();
            } else {
                FileChooser chooser = new FileChooser();
                chooser.setInitialDirectory(new File(file_path));
                file1 = new File(file_path);
                file1 = chooser.showOpenDialog(null);
                if (file1 != null) {
                    p1 = new Pair<>(null, null);
                    p2 = new Pair<>(null, null);

                    Thread thread_input_data = new Thread() {
                        @Override
                        public void run() {
                            input_and_output_data.set_init_Data(p1, p2, repetition, play_state, list_of_names, list_of_players);
                            input_and_output_data.InPut_Data(file1.toString());
                            Platform.runLater(() -> {
                                set_game();
                                review = true;
                                init_grid_when_load_game_complete(squares);
                            });
                        }
                    };
                    thread_input_data.start();
                    window.close();
                }
            }
        });
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(btn1, btn2, btn3, btn4);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(lb1, hbox);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

    private void quick_save() {
        fill_Data();
        Thread thread_output_data = new Thread() {
            @Override
            public void run() {
                input_and_output_data.set_init_Data(p1, p2, repetition, play_state, list_of_names, list_of_players);
                try {
                    input_and_output_data.OutPut_Data_to_tempfile();
                } catch (IOException ex) {
                    System.out.println(" IOException at create temp file");
                }
            }
        };
        thread_output_data.start();
    }

    private void window_score_board() {
        String file_path = "D:\\Store\\Score_Board\\";
        File file = new File(file_path);
        if ((file.list()).length == 0) {
            Stage window;
            Scene scene;
            window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.UNDECORATED);
            window.setWidth(500);
            window.setHeight(220);
            Label lb = new Label("لا توجد ألعاب منتهية من أجل عرض النتائج !!!");
            lb.setFont(new Font(20));
            Button btn = new Button("موافق");
            btn.setMinSize(45, 10);
            btn.setOnAction(e -> {
                window.close();
            });
            VBox vbox = new VBox(20);
            vbox.getChildren().addAll(lb, btn);
            vbox.setAlignment(Pos.CENTER);
            scene = new Scene(vbox);
            window.setScene(scene);
            window.showAndWait();
            return;
        }
        TableColumn id_of_game = new TableColumn("Id");
        id_of_game.setMinWidth(30);
        id_of_game.setCellValueFactory(new PropertyValueFactory("id_of_game"));
        TableColumn<Data_of_ScoreBoard, String> namewinerplayer = new TableColumn("Name Winer Player");
        namewinerplayer.setMinWidth(145);
        namewinerplayer.setCellValueFactory(new PropertyValueFactory("name_game"));
        TableColumn score = new TableColumn("Score");
        score.setMinWidth(100);
        score.setCellValueFactory(new PropertyValueFactory("score"));
        TableColumn time_of_play = new TableColumn("Time of play");
        time_of_play.setMinWidth(50);
        time_of_play.setCellValueFactory(new PropertyValueFactory<>("time_of_play"));
        TableColumn shields = new TableColumn("Shields");
        shields.setMinWidth(100);
        shields.setCellValueFactory(new PropertyValueFactory("shields"));
        TableColumn mines = new TableColumn("Mines");
        mines.setMinWidth(100);
        mines.setCellValueFactory(new PropertyValueFactory("mines"));
        TableColumn<Data_of_ScoreBoard, String> start_time = new TableColumn("Start Time");
        start_time.setMinWidth(145);
        start_time.setCellValueFactory(new PropertyValueFactory("start_time"));
        TableColumn end_time = new TableColumn("End Time");
        end_time.setMinWidth(145);
        end_time.setCellValueFactory(new PropertyValueFactory("end_time"));
        TableColumn number_of_player = new TableColumn("Number of Player");
        number_of_player.setMinWidth(130);
        number_of_player.setCellValueFactory(new PropertyValueFactory("number_of_player"));
        output_data_score = new Data_of_ScoreBoard(0, null, 0, 0, 0, 0, null, null, 0, null);
        TableView<Data_of_ScoreBoard> table = new TableView<>();
        ArrayList<Data_of_ScoreBoard> data_score = output_data_score.InPut_Data();
        ObservableList<Data_of_ScoreBoard> observable = FXCollections.observableArrayList();
        for (i = 0; i < data_score.size(); i++) {
            observable.add(data_score.get(i));
        }
        table.setItems(observable);
        table.getColumns().addAll(id_of_game, namewinerplayer, score, time_of_play, shields, mines, start_time, end_time, number_of_player);
        Stage window;
        Scene scene;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setTitle("About");
        window.setWidth(1000);
        window.setHeight(400);
        Button view_selected_item = new Button("View Game"), Cancel = new Button("Cancel");
        view_selected_item.setFont(new Font("Tahoma", 20));
        Cancel.setFont(new Font("Tahoma", 20));
        Cancel.setOnAction(e -> {
            window.close();
        });
        view_selected_item.setOnAction(e -> {
            ObservableList<Data_of_ScoreBoard> selected_items;
            selected_items = table.getSelectionModel().getSelectedItems();
            if (selected_items.isEmpty()) {
                return;
            }
            window.close();
            p1 = new Pair<>(null, null);
            p2 = new Pair<>(null, null);
            Thread thread_input_data = new Thread() {
                @Override
                public void run() {
                    input_and_output_data.set_init_Data(p1, p2, repetition, play_state, list_of_names, list_of_players);
                    input_and_output_data.InPut_Data(selected_items.get(0).get_path_selected_game());
                    Platform.runLater(() -> {
                        set_game();
                        review = true;
                        init_grid_when_load_game_complete(squares);
                    });
                }
            };
            thread_input_data.start();
        });
        HBox hbox = new HBox(50);
        hbox.setPadding(new Insets(0, 10, 10, 0));
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().addAll(view_selected_item, Cancel);
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(2, 2, 0, 2));
        vbox.getChildren().addAll(table, hbox);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

    private void get_time(boolean start_or_end_playing) {

        String time = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa").format(new Date());
        char[] time_ = new char[time.length()];
        time.getChars(0, time.length(), time_, 0);
        if (time_[0] == '0') {
            time_[0] = ' ';
        }
        if (time_[3] == '0') {
            time_[3] = ' ';
        }
        if (time_[11] == '0') {
            time_[11] = ' ';
        }
        if (start_or_end_playing) {
            start_time_of_play = String.valueOf(time_);
        } else {
            end_time_of_play = String.valueOf(time_);
        }
    }

    private void update_GUI_during_review() {

        if (review) {
            System.out.println("rep=" + repetition + "   name=" + list_of_players.get(repetition).get_name() + "   score=" + gui.get_score_detected_player(repetition) + "    weak=" + gui.get_numbers_of_shields(repetition).getKey() + "    strong=" + gui.get_numbers_of_shields(repetition).getValue());
            name_current_player.setText(list_of_players.get(repetition).get_name());
            result_score.setText(String.valueOf(gui.get_score_detected_player(repetition)));
            num_weak_Shields.setText(String.valueOf(gui.get_numbers_of_shields(repetition).getKey()));
            num_strong_Shields.setText(String.valueOf(gui.get_numbers_of_shields(repetition).getValue()));
            if (level.equals("Dump") && list_of_players.get(repetition).get_name().equals("computer")) {
                num_weak_Shields.setText("0");
                num_strong_Shields.setText("0");
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
