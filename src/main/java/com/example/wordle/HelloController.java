package com.example.wordle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public Label notEnoughLettersText;


    @FXML
    public void initialize() {
        ArrayList<ArrayList<TextField>> allRows = new ArrayList<>();

        ArrayList<TextField> row1 = new ArrayList<>();
        row1.add(one1); row1.add(one2);row1.add(one3);row1.add(one4);row1.add(one5);

        ArrayList<TextField> row2 = new ArrayList<>();
        row2.add(two1); row2.add(two2);row2.add(two3);row2.add(two4);row2.add(two5);

        allRows.add(row1);allRows.add(row2);

        disableRow(allRows);

        setOneLetterOnly(allRows);

        for(int i = 0; i < row1.size();i++){
            int x = i;
            // "BACKSPACE" PRESSED
            row1.get(x).addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.BACK_SPACE) {
                    notEnoughLettersText.setText("");
                    if(x!=0){
                        row1.get(x).setText("");
                        row1.get(x).setEditable(false);
                        row1.get(x-1).setEditable(true);
                        row1.get(x-1).requestFocus();
                    } else{
                        row1.get(x).setText("");
                        row1.get(x).requestFocus();
                    }
                }
            });

            // "ENTER" PRESSED
            row1.get(x).addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.ENTER) {
                    if(allFieldsFull(row1)){
                        System.out.println("true");
                    } else{
                        notEnoughLettersText.setText("Not Enough Letters");
                        System.out.println("false");
                    }
                }
            });

            // WORD PRESSED
            row1.get(x).textProperty().addListener((observable, oldValue, newValue) -> {
                notEnoughLettersText.setText("");
                if(newValue.matches("[a-zA-Z]+")){
                    if(x!=4){
                        row1.get(x+1).setEditable(true);
                        row1.get(x).setEditable(false);
                        row1.get(x+1).requestFocus();

                    }
                } else{
                    row1.get(x).setText("");
                }
            });
        }
    }



    //CHECKS IF ALL FIELDS ARE FULL
    private boolean allFieldsFull(ArrayList<TextField> row1) {
        if(!row1.get(4).getText().trim().isEmpty()){
            return true;
        } else{
            if(!notEnoughLettersText.equals("")){
                notEnoughLettersText.setText("");
            }
            return false;
        }
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