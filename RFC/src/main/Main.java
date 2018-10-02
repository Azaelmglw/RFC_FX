package main;

import models.*;
import controllers.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;

/**
 *
 * @author Azaelmglw
 */

public class Main extends Application{
    
    public static void main(String oamg[]) {  
        Application.launch(oamg);
    }
    
    @Override
    public void start(Stage primaryStage) {
        try {
            ArrayList<FXMLLoader> loaders_list = new ArrayList<>(5);
            ArrayList<Object> models_list = new ArrayList<>(5);
            ArrayList<Object> controllers_list = new ArrayList<>(5);
            
            // FXML Loaders Declaration.
            FXMLLoader loader_main = new FXMLLoader(getClass().getResource("ViewMain.fxml"));
            
            loaders_list.add(0, loader_main);
                        
            /*  To maintain an MVC structure and avoid performance issues due to infinite instantiation, 
            each one of the application Models, Controllers, Parents and Scenes have been instanced in 
            this exact class and method.*/
            
            /*  In order to be able to get the <<model_main>> which contains the <<primaryStage>> in the Controllers
            and allow the modification of it's <<root>> (switching views), the Controller of each respective 
            <<.fxml>> file needs to be declared outside of itself and before the <<load()>> method is called 
            or it's build will fail.*/
            
            /*  Models array list position:
            [0] -> model_main   |
            */
            
            /*  Controllers array list position:
            [0] -> controller_main  |
            */
            
            ModelMain model_main = new ModelMain(primaryStage);
            models_list.add(0, model_main);
            ControllerMain controller_main = new ControllerMain(model_main);
            loader_main.setController(controller_main);
            controllers_list.add(0, controller_main);
           
            //  Parents Declaration.
            Parent main = (Parent)loader_main.load();
            
            /*  Once all the Parent variables are declared, the <<model_main>> needs to receive the reference 
            for each one trough it's <<set()>> method, making available all the Parents to the Controllers.*/
            
            //  Parents Assignation.
            model_main.setParent(0, main);
            
            //  Scenes Declaration.
            Scene main_scene = new Scene(main, 800, 768);
            
            //  Unary Operators Declaration.
            UnaryOperator<Change> text_filter = c -> {
                
                if(!c.getText().matches("[A-Z]") && !c.getText().matches(" ") || c.isDeleted()){
                    c.setText("");
                    return c;
                }
                else if(c.getText().isEmpty()){
                    return c;
                }
                return c;
            };
            
            //  Text Formatters Declaration.
            TextFormatter<String> first_name_formatter = new TextFormatter<>(text_filter);
            TextFormatter<String> middle_name_formatter = new TextFormatter<>(text_filter);
            TextFormatter<String> last_name_formatter = new TextFormatter<>(text_filter);
            
            //  Text Formatters Assignation.
            model_main.setTextFormatter(0, first_name_formatter);
            model_main.setTextFormatter(1, middle_name_formatter);
            model_main.setTextFormatter(2, last_name_formatter);
            
            //  Date Formatters Declaration.
            SimpleDateFormat date_formatter_mx = new SimpleDateFormat("dd-mm-yyyy");
            SimpleDateFormat date_formatter_rfc = new SimpleDateFormat("yy-mm-dd");
            
            //  Date Formatters Assignation.
            model_main.setDateFormatter(0, date_formatter_mx);
            model_main.setDateFormatter(1, date_formatter_rfc);
            
            //  Alerts Declaration.
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Something went wrong");
            
            //  Alerts Assignation.
            model_main.setAlert(0, error_alert);
            
            /*  Only the <<main>> UI is set in this method, all of the other manipulations of <<primaryStage>>
            need to be declared in it's respective Controllers.*/
            primaryStage.setTitle("RFC Generator");
            primaryStage.setScene(main_scene);
            primaryStage.show();
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}