package controllers;

import models.ModelMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Azaelmglw
 */

public class ControllerMain implements Initializable {
    
    private final ModelMain model_main;
    
    public ControllerMain(ModelMain model_main){
        this.model_main = model_main;
    }
         
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        first_name_tfield.focusedProperty().addListener((observable) -> {
            if(first_name_tfield.getTextFormatter() == null){
                System.out.println("First Name container doesn't have a TextFormatter currently.");
                FormatInputFields();
            }
        });
    }
    
    @FXML
    private TextField first_name_tfield;
    
    @FXML
    private TextField middle_name_tfield;
    
    @FXML
    private TextField last_name_tfield;
    
    @FXML
    private DatePicker birth_date_dpicker;
    
    @FXML
    private Label rfc_result_lbl;
    
    @FXML
    private void GenerateRfc(ActionEvent event) {
        SetValues();
        model_main.GenerateRfc();
        rfc_result_lbl.setText(model_main.getResultRfc());
        model_main.setResultRfc("");
    }
    
    @FXML
    private void ClearInputFields(ActionEvent event) {
        first_name_tfield.setText(" ");
        middle_name_tfield.setText(" ");
        last_name_tfield.setText(" ");
        birth_date_dpicker.getEditor().clear();
    }
    
    private void SetValues(){
        model_main.setFirstName(first_name_tfield.getText());
        model_main.setMiddleName(middle_name_tfield.getText());
        model_main.setLastName(last_name_tfield.getText());
        model_main.setBirthDate("" + birth_date_dpicker.getValue());
    }
    
    private void FormatInputFields(){
        first_name_tfield.setTextFormatter(model_main.getTextFormatter(0));
        middle_name_tfield.setTextFormatter(model_main.getTextFormatter(1));
        last_name_tfield.setTextFormatter(model_main.getTextFormatter(2));
    }
}