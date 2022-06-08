/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;

/**
 *
 * @author user
 */
public class VentanaSalir {
    
    private VBox root;
    private Scene escenaFinal;
    private Stage stage3;
    private ImageView imv;
    private Label temporizador = new Label("Cerrando..");
    private Thread tempo;
   

    public VentanaSalir() {
        root = new VBox();
//        temporizador = new Label("Cerrando en 3...");
    }
    
    public Scene setScene(){
        escenaFinal = new Scene(getRoot(),650,450);
        return escenaFinal;
    }
    public VBox getRoot(){
        //Label
        Label salida = new Label("¡Muchas Gracias!");
        salida.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:2.5em;" );
        salida.setPadding(new Insets(60,0,20,0));
        
        Label msj1 = new Label("Su pedido ha sido pagado y ahora empezaremos a prepararlo.");
        Label msj2 = new Label("En aproximadamente 30 minutos llegará a su destino.");
        Label msj3 = new Label("Gracias por preferirnos.");
        
        //Imagenes
        Image i1 = new Image(getClass().getResource("/Imagenes/entrega.gif").toString());
        imv = new ImageView(i1);
        imv.setFitHeight(200);
        imv.setFitWidth(260);
        
        
        //Temporizador
        startTempo();
        temporizador.setPadding(new Insets(0,0,0,450));
        
        
        //Añadir al root
        root.setStyle("-fx-background-color:white; ");
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(5);
        root.getChildren().addAll(salida,msj1,msj2,msj3,imv,temporizador);
        
        //Cerrar ventana
        return root;
    
    }
    
    public void startTempo(){
        Runnable tiempo = () -> runTempo();
        Thread cronometro = new Thread(tiempo);
        cronometro.setDaemon(true);
        cronometro.start();
    }
    
    public void runTempo(){
        
        for (int i = 5; i >= 1; i--){
            String tiempo = "La ventana se cerrará en " + i + " ...";
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    temporizador.setText(tiempo);
                }
            });
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        
        System.exit(0);
        
        
    }
    
}
