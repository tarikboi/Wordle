package com.example.wordle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloController {
    @FXML
    public TextField one1; @FXML public TextField one2; @FXML public TextField one3; @FXML public TextField one4; @FXML public TextField one5;
    @FXML
    public TextField two1; @FXML public TextField two2; @FXML public TextField two3; @FXML public TextField two4; @FXML public TextField two5;

    @FXML
    public Button start;
    private KeyEvent keyEvent;


    public void start() {
        ArrayList<ArrayList<TextField>> allRows = new ArrayList<>();

        ArrayList<TextField> row1 = new ArrayList<>();
        row1.add(one1); row1.add(one2);row1.add(one3);row1.add(one4);row1.add(one5);

        ArrayList<TextField> row2 = new ArrayList<>();
        row2.add(two1); row2.add(two2);row2.add(two3);row2.add(two4);row2.add(two5);

        allRows.add(row1);allRows.add(row2);

        setOneLetterOnly(allRows);

        //disableRow(allRows);




        for(int i = 0; i< row1.size();i++){
            int x = i;

            row1.get(x).textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    char letter = newValue.charAt(0);
                    if(x!=4){
                        row1.get(x+1).requestFocus();
                    }
                }
            });

            

        }

/*
        for(int i = 0; i < row1.size(); i++){
            int k = i + 1; int x = i;
            if(i<4){
                row1.get(i).addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                    if(key.getCode().equals(KeyCode.BACK_SPACE)){
                        System.out.println("back");
                    } else if(key.getCode().isLetterKey()){
                        row1.get(x).textProperty().addListener(new ChangeListener<String>() {
                            @Override
                            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                char letter = newValue.charAt(0);
                                if(Character.isLetter(letter)){
                                    previousTextField = row1.get(x);
                                    row1.get(x).setEditable(false);
                                    row1.get(k).requestFocus();
                                }
                            }
                        });
                    }
                });
            } else{
                row1.get(i).textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        char letter = newValue.charAt(0);
                    }
                });
            }
        }

 */


    }

    //SETS 1 CHAR LIMIT FOR ALL TEXTFIELDS
    private void setOneLetterOnly(ArrayList<ArrayList<TextField>> allRows) {
        for (int i = 0; i < allRows.size(); i++) {
            for (int j = 0; j < allRows.get(i).size(); j++) {
                addTextLimiter(allRows.get(i).get(j),1);
            }
        }
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

    //DISABLES ALL ROWS (NOT USED YET)
    private void disableRow(ArrayList<ArrayList<TextField>> allRows) {
        for (int i = 0; i < allRows.size(); i++) {
            for (int j = 0; j < allRows.get(i).size(); j++) {
                allRows.get(i).get(j).setEditable(false);
            }
        }
        allRows.get(0).get(0).setEditable(true);

    }

}