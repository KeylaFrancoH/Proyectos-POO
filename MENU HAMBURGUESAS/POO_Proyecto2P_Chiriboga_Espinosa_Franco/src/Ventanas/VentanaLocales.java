/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class VentanaLocales {
    private VBox root;
    private VBox elementos;
    private Scene escene;
    private Stage stage2;
    
     public VentanaLocales() {
        root = new VBox();
    }
    
    
    public Scene setScene(){
        escene = new Scene(getRoot(),650,450);
        return escene;
    }
    
    public VBox getRoot(){
        
        //Fondo
        root.setStyle("-fx-background-image: url(/Recursos/mapa.png);");
        return root;
    }
    
    public Pane Mapa(){
        Pane root = new Pane();
        return null;
}
}
