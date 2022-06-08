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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class VentanaLogin {
    Stage principal;
    static TextField user;
    static PasswordField contrasena;
    private VBox root;
    private ImageView imv;
    private ImageView ima;
    private GridPane grid;
    private Stage stage2;
    static Cliente cl;
    
    

    public VentanaLogin(Stage principal) {
        this.principal = principal;
        root = new VBox();
        grid = new GridPane();
        user = new TextField();
        contrasena = new PasswordField();
        imv = new ImageView();
    }
    
    public VBox getRoot(){
        
        GridPane gpInterno = new GridPane();
        BorderPane pane = new BorderPane();

        
        //Creación de Label
        
        Label titulo = new Label("The Good Burguer Restaurant");
        titulo.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:2.5em;");
        titulo.setPadding(new Insets(50, 50, 50, 130));
        
        //Creacion de Botones
        
        Button bIngreso = new Button("Ingresar");
        bIngreso.setStyle("-fx-font-weight:bolder; -fx-background-color:orange; -fx-font-size:1em; ");
        pane.setCenter(bIngreso);
        bIngreso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validarUsuario()){
                    VentanaBienvenido vb = new VentanaBienvenido();
                    principal.setScene(vb.setScene());
                }
                else{
                    user.clear();
                    contrasena.clear();
                    Label error = new Label("¡Ingreso incorrecto!");
                    error.setStyle("-fx-text-fill:red ; -fx-font-size:1em; -fx-font-weight:bolder");
                    gpInterno.add(error, 1, 2);
                    // PONER EL ERROR BIEN :(
                }
            }
        });  
        
        //Imagenes 
        
        Image i1 = new Image(getClass().getResource("/Imagenes/hamburguesa1.png").toString());
        imv = new ImageView(i1);
        imv.setFitHeight(200);
        imv.setFitWidth(200);
        
        
        Image i2 = new Image(getClass().getResource("/Imagenes/repartidor.png").toString());
        ima = new ImageView(i2);
        ima.setFitHeight(200);
        ima.setFitWidth(200);
        
        
        // Grid Interno
        
        gpInterno.add(new Label("Usuario: "),0,0);
        gpInterno.add(user,1,0);
        gpInterno.add(new Label("Contraseña: "), 0,1);
        gpInterno.add(contrasena,1,1);
        gpInterno.setHgap(30);
        gpInterno.setVgap(30);
        
        
        //Añadir al grid
        grid.add(gpInterno,1,0);
        grid.add(ima, 0, 2);
        grid.add(imv, 2,2);
        grid.add(pane,1,2);
        
        
        //Añadir al root
        root.getChildren().addAll(titulo, grid);
        root.setStyle("-fx-background-color:white;");
        
        //Retorno de variable
        return root;
    }
    
    public static boolean validarUsuario(){
        boolean v = false;
        ArrayList<Cliente> clientes = Cliente.cargarClientes();
        for (Cliente c:clientes){
            String usuario = c.getUsuario();
            String clave = c.getContrasena();
            if((user.getText().equals(usuario)) && (contrasena.getText().equals(clave))){
                v = true;
                cl = new Cliente(c.getNombre(),c.getApellido(),user.getText(),contrasena.getText());

            }
        }
        return v;
    }
    
    public static Cliente generarCliente(){
        return cl;
    }
}
