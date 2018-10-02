package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 *
 * @author Azaelmglw
 */

public class ModelMain {
    /*  Parents array list position:
    [0] -> main    |
    */
    
    /*  Text Formatters array list position:
    [0] -> first_name_formatter |   [1] -> middle_name_formatter   |    
    [2] -> last_name_formatter  |  
    */
    
    /* Date Formatters array list position:
    [0] -> date_formatter_mx    |   [1] -> date_formatter_rfc   |
    */
    
    /*  Alerts array list position:
    [0] -> error_alert  |
    */
    
    /*  User input array list position:
    [0] -> first_name   |   [1] -> middle_name  |   [2] -> last_name    |
    [3] -> birth_date   |
    */
    
    /*  Application output array list position:
    [0] -> result_rfc  |
    */
    
    private final Stage primaryStage;
    private List<Parent> parents = new ArrayList<>(5);
    private List<TextFormatter> text_formatters = new ArrayList<>(5);
    private List<SimpleDateFormat> date_formatters = new ArrayList<>(5);
    private List<Alert> alerts = new ArrayList<>(5);
    private List<String> user_input = new ArrayList<>(5);
    private List<String> app_output = new ArrayList<>(5);
    
    private String rfc_verification_aux = "";
    
    public ModelMain(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void GenerateRfc(){
        /*Primera letra y primera vocal interna del primer apellido.
        Primera letra del segundo apellido.
        Primera letra del primer nombre del contribuyente.
        Fecha de nacimiento en formato aa/mm/dd.*/
        
        
        //  Primera Letra del Primer Apellido.
        if(VerifyUserInput(user_input.get(1), "Middle Name")){
            
            //  Primera Vocal Interna del primer apellido.
            for(int x = 0; x < getMiddleName().length(); x ++){
                if(getMiddleName().charAt(x) == 'A' || getMiddleName().charAt(x) == 'E' || 
                getMiddleName().charAt(x) == 'I' || getMiddleName().charAt(x) == 'O' ||
                getMiddleName().charAt(x) == 'U'){
                    rfc_verification_aux += getMiddleName().charAt(x);
                    x = getMiddleName().length();
                    
                    //  Primera letra del segundo apellido.
                    if(VerifyUserInput(user_input.get(2), "Last Name")){
                        
                        //  Primera letra del primer nombre.
                        if(VerifyUserInput(user_input.get(0), "First Name")){
                            
                        // Fecha de nacimiento formato aa/mm/dd.
                            try{
                                Date date = getDateFormatter(1).parse(user_input.get(3));
                                rfc_verification_aux += "" + getDateFormatter(1).format(date);
                                setResultRfc(rfc_verification_aux.replaceAll("[-]", ""));
                                rfc_verification_aux = "";
                            }
                            catch(ParseException e){
                                getAlert(0).setHeaderText( "" + e);
                                getAlert(0).showAndWait();
                            }
                        }
                    }
                }
            }
        }   
    }
    
    public boolean VerifyUserInput(String user_input, String field_evaluated){
        if (user_input.charAt(0) == ' ' || user_input.isEmpty()) {
            getAlert(0).setHeaderText("The " + field_evaluated + " is invalid");
            getAlert(0).showAndWait();
            return false;
        }
        else{
            rfc_verification_aux += user_input.charAt(0);
            return true;
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Parent getParent(int parent_position) {
        return parents.get(parent_position);
    }

    public void setParent(int parent_position, Parent parent) {
        this.parents.add(parent_position, parent);
    }    
    
    public TextFormatter getTextFormatter(int text_formatter_position){
        return text_formatters.get(text_formatter_position);
    }
    
    public void setTextFormatter(int text_formatter_position, TextFormatter text_formatter){
        this.text_formatters.add(text_formatter_position, text_formatter);
    }
    
    public SimpleDateFormat getDateFormatter(int date_formatter_position){
        return date_formatters.get(date_formatter_position);
    }
    
    public void setDateFormatter(int date_formatter_position, SimpleDateFormat date_formatter){
        this.date_formatters.add(date_formatter_position, date_formatter);
    }
    
    public Alert getAlert(int alert_position) {
        return alerts.get(alert_position);
    }

    public void setAlert(int alert_position, Alert alert) {
        this.alerts.add(alert_position, alert);
    }
    
    public String getFirstName(){
        return user_input.get(0);
    }
    
    public void setFirstName(String first_name){
        user_input.add(0, first_name);
    }
    
    public String getMiddleName(){
        return user_input.get(1);
    }
    
    public void setMiddleName(String middle_name){
        user_input.add(1, middle_name);
    }
    
    public String getLastName(){
        return user_input.get(2);
    }
    
    public void setLastName(String last_name){
        user_input.add(2, last_name);
    }
    
    public String getBirthDate(){
        return user_input.get(3);
    }
    
    public void setBirthDate(String aa_mm_dd){
        user_input.add(3, aa_mm_dd);
    }
    
    public String getResultRfc(){
        return app_output.get(0);
    }
    
    public void setResultRfc(String result_rfc){
        app_output.add(0, result_rfc);
    }
}