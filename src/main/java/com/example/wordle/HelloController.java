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
import org.w3c.dom.Text;

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


        for (int a = 0; a < allRows.size(); a++) {
            //System.out.println(a);
            //System.out.println(allRows.get(a).toString());
            allRows.get(a).get(0).setEditable(true);
            ArrayList<TextField> currentRow = allRows.get(a);

            for (int i = 0; i < currentRow.size(); i++) {
                int x = i;
                // "BACKSPACE" PRESSED
                currentRow.get(x).addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                    if (key.getCode() == KeyCode.BACK_SPACE) {
                        notEnoughLettersText.setText("");
                        if (x != 0) {
                            currentRow.get(x).setText("");
                            currentRow.get(x).setEditable(false);
                            currentRow.get(x - 1).setEditable(true);
                            currentRow.get(x - 1).requestFocus();
                        } else {
                            currentRow.get(x).setText("");
                            currentRow.get(x).requestFocus();
                        }
                    }
                });

                // "ENTER" PRESSED
                currentRow.get(x).addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                    if (key.getCode() == KeyCode.ENTER) {
                        if (allFieldsFull(currentRow)) {
                            System.out.println("true");
                        } else {
                            notEnoughLettersText.setText("Not Enough Letters");
                            System.out.println("false");
                        }
                    }
                });

                // WORD PRESSED
                currentRow.get(x).textProperty().addListener((observable, oldValue, newValue) -> {
                    notEnoughLettersText.setText("");
                    if (newValue.matches("[a-zA-Z]+")) {
                        if (x != 4) {
                            currentRow.get(x + 1).setEditable(true);
                            currentRow.get(x).setEditable(false);
                            currentRow.get(x + 1).requestFocus();

                        }
                    } else {
                        currentRow.get(x).setText("");
                    }
                });
            }
        }

    }

    /*
    private void enterPressed(ArrayList<TextField> theRow) {
        for(int i = 0; i< theRow.size(); i++){
            theRow.get(i).setStyle("-fx-background-color: black");
        }
        currentRow = allRows.get(1);
    }

     */



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
    }

}