/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Modelo.Archivo;
import Modelo.Local;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class VentanaLocales1 {
    private Stage stage;
    private Scene Main;
    private StackPane root;
    private Pane AreaDeIcono;
    private ArrayList<ImageView> iconos = new ArrayList<ImageView>();
    private Label tiempo = new Label();
    Thread t1; 
    
    
    
    public VentanaLocales1() {        
        this.stage = stage;
    }
    
    public Scene getScene() throws FileNotFoundException{
        root=new StackPane();
        stage = new Stage();
        
                
        //SUBIR IMAGEN FONDO     
        Image mapa = new Image(getClass().getResource("/Imagenes/mapa.jpg").toString());
        ImageView imageView = new ImageView(mapa);
        imageView.setFitHeight(650);
        imageView.setFitWidth(1000);
                
           
        
        //LEER ARCHIVO ICONOS
        Archivo a = new Archivo();
        ArrayList<Local> locales = a.leerArchivoLocales();
        int numLocales = locales.size();
        Random r = new Random();
        
        
        
        //AREA DE LOS ICONOS
        AreaDeIcono = new Pane();
        AreaDeIcono.setMinSize(1000,650);
        AreaDeIcono.setMaxSize(1000,650);
        
        
        
        //TIEMPO Y LISTA DE ICONOS
        int random = r.nextInt(10)+1;
        for(int i=numLocales;i>0;i--){
            System.out.println(i);
            Image icono2 = new Image(getClass().getResource("/Imagenes/icono2.png").toString());
            ImageView iconoIMG = new ImageView(icono2);            
            iconos.add(iconoIMG);
            iconoIMG.setVisible(false);            
            Timeline ti = new Timeline(new KeyFrame(Duration.seconds(random), e -> iconoIMG.setVisible(true)));
            random = random+r.nextInt(10)+1;
            ti.play();
        }
        for(ImageView im :iconos){
            im.setLayoutX(locales.get(numLocales-1).getVextorX());
            im.setLayoutY(locales.get(numLocales-1).getVextorY());
            im.setFitHeight(60);
            im.setFitWidth(60); 
            String nombre_direccion_horario = locales.get(numLocales-1).getNombre()+","+locales.get(numLocales-1).getDireccion()+","+locales.get(numLocales-1).getHorario();
            im.setOnMouseClicked(event-> {
                try {
                    showPopUp(nombre_direccion_horario);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(VentanaLocales1.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            AreaDeIcono.getChildren().add(im);
            numLocales-=1;
        }
        
        
        
        //FINAL
        root.getChildren().addAll(imageView,AreaDeIcono);                
        Main = new Scene(root,1000,650);
        stage.sizeToScene();
        stage.setTitle("LOCALES");
        return Main;        
    }

    public void showPopUp(String nombre_direccion_horario) throws FileNotFoundException{
        System.out.println(nombre_direccion_horario);
        String arr[] = nombre_direccion_horario.split(",");
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.UNDECORATED);
        VBox vbox = new VBox();
        Label nombre = new Label(arr[0]);
        Label direccion = new Label(arr[1]);
        Label horario = new Label(arr[2]);
        Button aceptBtn = new Button("Aceptar");
        
        startTask();
        
        //DISEÑO
        nombre.setStyle("-fx-font-size: 3em; -fx-font: bold italic 15pt \"Lucida Console\"; -fx-font-weight: bolder; -fx-text-fill: #c24848;");
        direccion.setStyle("-fx-font-size: 3em; -fx-font: bold italic 10pt \"Lucida Console\"; -fx-font-weight: bolder; -fx-text-fill: black;");
        horario.setStyle("-fx-font-size: 3em; -fx-font: bold italic 10pt \"Lucida Console\"; -fx-font-weight: bolder; -fx-text-fill: black;");
        tiempo.setStyle("-fx-font-size: 3em; -fx-font: bold italic 10pt \"Lucida Console\"; -fx-font-weight: bolder; -fx-text-fill: black;");
        aceptBtn.setStyle("-fx-background-color: #c24848;-fx-font-size: 1.0em;-fx-font-weight:bolder; -fx-text-fill: white; -fx-font: bold italic 13pt \"Lucida Console\"");        
        
        
        
        //SUBIR IMAGEN FONDO 
        Image pin = new Image(getClass().getResource("/Imagenes/fondo1.jpg").toString());
        ImageView fondo = new ImageView(pin);
        fondo.setFitHeight(350);
        fondo.setFitWidth(350);
        
        
        
        //SCENE
        vbox.getChildren().addAll(nombre,direccion,horario,tiempo,aceptBtn);        
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.CENTER);
        StackPane root2 = new StackPane();
        root2.getChildren().addAll(fondo,vbox);
        Scene stageScene = new Scene(root2,300,300);
        newStage.setScene(stageScene);
        newStage.show();

        //ACCION BOTÓN
        aceptBtn.setOnMouseClicked((e)->{
            newStage.close();
        });     


    }
    
    private void runTaskThread(){
        
        for (int i = 5; i >= 1; i--) {
            String status = "Mostrando " + i +" segundos...";
            
            Platform.runLater(() -> { // GUI identifica que esto le e a un hilo
                tiempo.setText(status);
                
            });

            System.out.println(status);

            try {
                
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
        }
    }
    
    public void startTask() { //Thread (Continuidad)
        
        Runnable task = (() -> runTaskThread());
        t1= new Thread(task);
        t1.setDaemon(true); //Impide que afecte al hilo principal
        t1.start();

    }
  
    
 
}
