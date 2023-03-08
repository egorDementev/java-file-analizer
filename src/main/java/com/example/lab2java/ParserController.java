package com.example.lab2java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ParserController {
    public Button parseButton;
    public Label header;
    public Label text1;
    public Label text2;
    public TextField inputFileNameField;
    public TextField outputFileNameField;
    public AnchorPane mainPane;
    public Label errorText;

    /**
     * clicking on the button starts the process of opening a file, reading data, and writing to a new file
     * @throws RuntimeException || IOException and set error message
     */
    @FXML
    protected void onParseButtonClicked() {
        try {
            Parser parser = new Parser(inputFileNameField.getText());
            parser.stringHandler();
            parser.writeInFile(outputFileNameField.getText());
            errorText.setText("Success!");
        }
        catch (RuntimeException | IOException e) {
            errorText.setText(e.getMessage());
        }
    }
}