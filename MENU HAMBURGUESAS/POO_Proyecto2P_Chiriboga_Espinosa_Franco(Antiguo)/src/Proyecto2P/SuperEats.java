/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto2P;

import Ventanas.VentanaLogin;
import Ventanas.VentanaSalir;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class SuperEats extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VentanaLogin ingreso = new VentanaLogin(stage);
        Scene scene = new Scene(ingreso.getRoot(),650,450);
        
        stage.setTitle("SuperEats");
        stage.setScene(scene);
        stage.show();
    }
    
}
