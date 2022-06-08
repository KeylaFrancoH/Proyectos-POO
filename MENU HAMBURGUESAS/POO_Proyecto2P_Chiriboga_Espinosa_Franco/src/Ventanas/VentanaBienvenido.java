/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Modelo.Cliente;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class VentanaBienvenido {
    private StackPane root;
    private VBox elementos;
    private Scene escene;
    private Stage stage2;

    public VentanaBienvenido() {
        root = new StackPane();
    }
    
    
    public Scene setScene(){
        escene = new Scene(getRoot(),650,450);
        return escene;
    }
    
    public StackPane getRoot(){
        VBox vboxInterno = new VBox();
        
        // clientes
        Cliente cliente = new Cliente("Veronica"); //Programar para que aparezca el nombre del usuario
        
        // Crear Labels
        Label label = new Label("Bienvenid@" + " "+ cliente.getNombre());
        label.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:2.5em;" );
        label.setPadding(new Insets(30,0,0,0));
        
        Label opc = new Label("Escoja la opción que prefiera");
        opc.setStyle("-fx-font-size:1em; -fx-text-fill:white;");
        opc.setPadding(new Insets(0,0,80,0));
        
        //Creación de botones
        Button bLocal = new Button("Encuentra el local más cercano");
        bLocal.setStyle("-fx-font-weight:bolder; -fx-background-color:orange; -fx-font-size:1em; -fx-text-fill:black; ");
        bLocal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0); //Borrar y agregar, crear metodo
            }
        });  
        Button bPedido = new Button("Haz tu pedido");
        bPedido.setStyle("-fx-font-weight:bolder; -fx-background-color:orange; -fx-font-size:1em; -fx-text-fill:black; ");
        bPedido.setPadding(new Insets(3,55,3,55));
        bPedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // nuevo Stage y scene
                VentanaPedido pedido = new VentanaPedido();
                stage2 = new Stage();
                stage2.setScene(pedido.setScene());
                stage2.setTitle("Pedido");
                stage2.show();

                
            }
        });
        
        //Fondo
        Image image = new Image(getClass().getResource("/Imagenes/fondoHamburguesa.jpg").toString());
        ImageView imv = new ImageView(image);
        imv.setFitHeight(450);
        imv.setFitWidth(650);
        
        
        // Añadir al vboxInterno
        vboxInterno.getChildren().addAll(label,opc, bLocal,bPedido);
        vboxInterno.setSpacing(30);
        vboxInterno.setPadding(new Insets(0,0,0,200));
        vboxInterno.setAlignment(Pos.TOP_CENTER);
        
        
        //Añadir al root
        root.getChildren().addAll(imv,vboxInterno);
        return root;
    }
    
    
}


