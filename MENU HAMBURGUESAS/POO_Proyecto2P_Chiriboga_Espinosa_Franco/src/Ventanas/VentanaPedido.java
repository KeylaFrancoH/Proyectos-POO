package Ventanas;

import Modelo.Producto;
import Modelo.Tipo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Toshiba
 */
public class VentanaPedido {
    private VBox root;
    private Scene escena;
    private Stage stage3;
    private ComboBox<Tipo> comboTipo;
    private ComboBox ordenar;
    private ArrayList<Producto> productos;
    
    public Scene setScene(){
        escena = new Scene(getRoot(),650,450);
        return escena; 
    }
    
    public VentanaPedido() {
        root = new VBox();
        comboTipo = new ComboBox<>();
        ordenar = new ComboBox();
        productos = new ArrayList<>();
    }
    
    public VBox getRoot(){
        
        GridPane gpinterno = new GridPane();
        Label l1 = new Label("Realice su pedido");
        l1.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:2em;");
        l1.setPadding(new Insets(20,0,0,30));
        
        
        //----------------------------------------------------------------------
        HBox h1 = new HBox();
        Label l = new Label("Tipo: ");
        l.setPadding(new Insets(0,0,0,30));
        l.setStyle("-fx-font-weight:bolder");
        comboTipo.setItems(FXCollections.observableArrayList(Tipo.cargarTipos()));
        comboTipo.setPadding(new Insets(0,0,0,30));
        comboTipo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                Tipo t1 = comboTipo.getValue();
                try {
                    mostrarProductos(t1);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        h1.getChildren().addAll(l,comboTipo);
        h1.setSpacing(55);
        //----------------------------------------------------------------------
        HBox h2 = new HBox();
        Label l2 = new Label("Ordenar por: ");
        l2.setPadding(new Insets(0,30,0,80));
        l2.setStyle("-fx-font-weight:bolder");
        ordenar.getItems().addAll(
                "Precio",
                "Orden Alfabetico");
        h2.getChildren().addAll(l2,ordenar);
        h2.setSpacing(5);
        
        
        //----------------------------------------------------------------------
        
        //Creación de botones
        
        HBox hboxBotones = new HBox();
        
// 1er boton
        
        Button bLimpiar = new Button("Limpiar");
        bLimpiar.setStyle("-fx-font-weight:bolder; -fx-background-color:orange; -fx-font-size:1em; -fx-text-fill:black; ");
        bLimpiar.setOnAction(e-> System.out.println("Aun no está lista la opción"));
        
//2do boton
        
        Button bContinuar = new Button("Continuar");
        bContinuar.setStyle("-fx-font-weight:bolder; -fx-background-color:orange; -fx-font-size:1em; -fx-text-fill:black; ");
        bContinuar.setOnAction((ActionEvent event) -> {
            VentanaPago pago = new VentanaPago();
            
            stage3 = new Stage();
            stage3.setScene(pago.setScene());
            stage3.setTitle("Pago");
            stage3.show();
            
            cerrarVentana(event);
           

            
            
        });

//Agregar los 2 botones al hbox

        hboxBotones.getChildren().addAll(bLimpiar, bContinuar);
        hboxBotones.setSpacing(30);
        hboxBotones.setPadding(new Insets(0,0,0,200));
        hboxBotones.setAlignment(Pos.BOTTOM_CENTER);
        // Añadir al gridInterno
        gpinterno.add(l1, 0, 0);
        gpinterno.add(h1, 0, 1);
        gpinterno.add(h2, 2, 1);
        gpinterno.setHgap(20);
        gpinterno.setVgap(25);
        
        //Añadir al root
        
        root.getChildren().add(gpinterno);
        root.setStyle("-fx-background-color:white;");
        return root;
    }
    
    
    //Metodo para mostrar los productos de una lista de obejtos por su tipo
    public void mostrarProductos(Tipo tipo) throws IOException{
        
        productos = Producto.cargarProductos(tipo);
        System.out.println(productos);

    }
    
    
    public void ordenarPedido(){

    }
    private void cerrarVentana(ActionEvent event) {
 
    Button source = (Button) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}
}