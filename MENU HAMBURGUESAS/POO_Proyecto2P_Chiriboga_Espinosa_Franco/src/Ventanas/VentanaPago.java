/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Hp
 */
public class VentanaPago {
    private Scene escena;
    private Stage stage3;
    private VBox root;
    private TextField direccion;
    private GridPane grid;
    private HBox hbox;
    Label etiquetaEfe;
    Label etiquetaCred; 
    VBox vBoxCred;
    TextField titular;
    TextField numTar;
    TextField caducidad;
    TextField cvv;
    GridPane gpCred;
    GridPane gPinterno;
    HBox hboxBotones;
    String UsDireccion, UsTitular, UsNumTar, UsCaducidad, UsCvv;
    Stage stage4;
    
    
    
    public Scene setScene(){
        escena = new Scene(getRoot(), 650, 450);
        return escena;
    }
    
    public VentanaPago(){
        gpCred = new GridPane();
        numTar = new TextField();
        caducidad = new TextField();
        cvv = new TextField();
        titular = new TextField();
        vBoxCred = new VBox();
        root = new VBox();
        grid = new GridPane();
        hbox = new HBox();
        direccion = new TextField();
        direccion.setPrefSize(450, 30);
        
                
    }
    
    public VBox getRoot(){
        
//Etiqueta de "direccion de entrega"
        Label etiqueta1 = new Label("Dirección de entrega");
        etiqueta1.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:2em;");
        etiqueta1.setPadding(new Insets(20, 0, 0, 30));
        
//Grid Interno 1
        gPinterno = new GridPane();
        gPinterno.add(new Label("Dirección: "), 1, 2);
        gPinterno.add(direccion, 2, 2); //Falta agregarle el getTextField
        gPinterno.setVgap(0);
        gPinterno.setHgap(30);
        //gPinterno.setLayoutX(30);
        //gPinterno.setLayoutY(60);
        
        //grid.add(gPinterno, 0, 0);
        
        root.getChildren().addAll(etiqueta1, gPinterno);
        root.setStyle("-fx-background-color:white;");
        
//Etiqueta de "Detalle de pago"
        Label etiqueta2 = new Label("Detalle de pago");
        etiqueta2.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:2em;");
        etiqueta2.setPadding(new Insets(40, 0, 0, 30));
        
//HBox Interno2 (De los Checkboxes)
        
        CheckBox efe = new CheckBox("Efectivo");
        CheckBox credito = new CheckBox("Tarjeta de crédito");
        hbox.getChildren().addAll(efe, credito);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10,0,0,30));
        hbox.setAlignment(Pos.BASELINE_LEFT);
        

        
//Creación de botones
        hboxBotones = new HBox();
//1er boton        
        Button bLimpiar = new Button("Limpiar");
        bLimpiar.setStyle("-fx-font-weight:bolder; -fx-background-color:orange; -fx-font-size:1em; ");
        bLimpiar.setOnAction(e-> {
            
            
            direccion.clear();
            titular.clear();
            numTar.clear();
            caducidad.clear();
            cvv.clear();
            System.out.println(txtIsEmpty());
            System.out.println(direccion.getText()+" "+titular.getText()+" "+numTar.getText()+" "+caducidad.getText()+" "+cvv.getText());
            
            });
        
//2do boton

        Button bContinuar = new Button("Continuar");
        bContinuar.setStyle("-fx-font-weight:bolder; -fx-background-color:orange; -fx-font-size:1em; ");
        
       
            bContinuar.setOnAction(e-> {
//Validar si casillas estan llenas ACTIONBUTTON             

//SI EFECTIVO ESTÁ SELECCIONADO -> (SOLO DIRECCION LLENAR)
              if(efe.isSelected()){
                 if(textoIngresado(direccion)){
                    System.out.println("Falta llenar");
                 }
                 
            }
//¿TODO BIEN? -> CONTINUE
                else{
                    System.out.println(txtIsEmpty()+" "+txtIsNULL());
                    System.out.println(UsDireccion+" "+UsTitular+" "+UsNumTar+" "+UsCaducidad+" "+UsCvv);
                    if(efe.isSelected()){
                        escribirPago(generarCodigo(), "09000", "Nombre cliente", "Total a pagar", "Fecha", "E");
                    }
                    if(credito.isSelected()){
                        escribirPago(generarCodigo(), "09000", "Nombre cliente", "Total a pagar", "Fecha", "C");
                    }
            
                    VentanaSalir salir = new VentanaSalir();
                    stage4 = new Stage();
                    stage4.setScene(salir.setScene());
                    stage4.setTitle("Salir");
                    stage4.show();
            
                    cerrarVentana(e);
                 }
                
//SI CREDITO ESTÁ LLENADO -> LLENAR TODO
            if(credito.isSelected()){
                if(txtIsEmpty()|| txtIsNULL()){
            
                System.out.println("Falta llenar");
            
                System.out.println(txtIsEmpty()+" "+txtIsNULL());


            

//¿TODO BIEN? -> CONTINUE       
                 }else{
                 System.out.println(txtIsEmpty()+" "+txtIsNULL());
                 System.out.println(UsDireccion+" "+UsTitular+" "+UsNumTar+" "+UsCaducidad+" "+UsCvv);
                if(efe.isSelected()){
                    escribirPago(generarCodigo(), "09000", "Nombre cliente", "Total a pagar", "Fecha", "E");
                }
                if(credito.isSelected()){
                    escribirPago(generarCodigo(), "09000", "Nombre cliente", "Total a pagar", "Fecha", "C");
                }
            
                VentanaSalir salir = new VentanaSalir();
                stage4 = new Stage();
                stage4.setScene(salir.setScene());
                stage4.setTitle("Salir");
                stage4.show();
            
                cerrarVentana(e);
            }
            }
             
            

            
             
            
            
            });
        
            
            
                
        
//agregar botones al HBox
        hboxBotones.getChildren().addAll(bLimpiar, bContinuar);
        hboxBotones.setSpacing(30);     
        
        hboxBotones.setAlignment(Pos.BASELINE_CENTER);

        
//Solo seleccionar uno
        
       efe.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
           credito.setSelected(!newValue);
           root.getChildren().remove(vBoxCred);
           gpCred.getChildren().clear();
           
           
           if(efe.isSelected()){
               etiquetaEfe = new Label("Tendrá que pagar un total de MUCHOS DOLARUCOS. "
                       + "\nAsegurese de tener el dinero completo por si el repartidor anda chirex. \nNo sea como la vrg y de propina ctm");
               etiquetaEfe.setPadding(new Insets(60, 0 ,0, 30));
               etiquetaEfe.setAlignment(Pos.TOP_LEFT);
               root.getChildren().addAll(etiquetaEfe, hboxBotones);
               
               
           } 
           
           
        }); 
        gpCred = new GridPane();

        credito.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> { 
            efe.setSelected(!newValue);
            root.getChildren().removeAll(etiquetaEfe, hboxBotones);
            
            if(credito.isSelected()){
                gpCred.getChildren().clear();
                vBoxCred.getChildren().clear();
                
                gpCred.add(new Label("Titular: "), 0, 0);
                gpCred.add(titular, 1, 0);
                gpCred.add(new Label("Número de tarjeta: "), 0, 1);
                gpCred.add(numTar, 1, 1);
                gpCred.add(new Label("Caducidad: "), 0, 2);
                gpCred.add(caducidad, 1, 2);
                gpCred.add(new Label("CVV: "), 0, 3);
                gpCred.add(cvv, 1, 3);
                
                gpCred.setHgap(12);
                gpCred.setVgap(6);
                etiquetaCred = new Label("Tendrá que pagar un total de MUCHOS BITCOINS ya que pagas con tarjeta reflechuchetumadre \n y"
                        + " te vamos a cobrar 5% más por usar tarjeta y no tener plata en efectivo aniñado hpta");
                
                etiquetaCred.setPadding(new Insets(10, 0, 0, 30));
                gpCred.setPadding(new Insets(10, 0, 0 ,30));
                gpCred.setAlignment(Pos.TOP_LEFT);
                vBoxCred.getChildren().addAll(gpCred, etiquetaCred);
                hboxBotones.setPadding(new Insets(10, 0, 0, 0));
                
                root.getChildren().addAll(vBoxCred, hboxBotones);


//Recoger valores ingresados por el usuario    
                
                UsTitular = titular.getText();
                UsNumTar = numTar.getText();
                UsCaducidad = numTar.getText();
                UsCvv = cvv.getText();

                
            
                
            } 
        });
        
        UsDireccion = direccion.getText();

    
//Agregar la segunda parte de la ventana al root principal
        root.getChildren().addAll(etiqueta2, hbox, hboxBotones);
        root.setStyle("-fx-background-color:white;");

        
        return root;
    }
    
    private void cerrarVentana(ActionEvent event){
        Button recurso = (Button) event.getSource();
        Stage stage = (Stage) recurso.getScene().getWindow();
        stage.close();
    }
    
    private static void escribirPago(String idPago, String idPedido, String nomCliente, String TotalPagar, String fecha, String tipo){
        
        FileWriter fw = null;
        BufferedWriter bw = null;
        String linea = idPago+","+idPedido+","+nomCliente+","+TotalPagar+","+fecha+","+tipo;
        
        try{
            fw = new FileWriter("pagos.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(linea+"\n");
            
        }catch(Exception e2){
            e2.printStackTrace();
        
        }finally{
            try{
                if(null != fw){
                    bw.close();
                }
            }catch (Exception e){
                System.out.println("!!!");
            }
        }
    }
    
    private String generarCodigo(){
        Random r = new Random();
        Integer aleatorio = r.nextInt(5000);
        String codigo = aleatorio.toString();
        return codigo;
    }
    
    private boolean textoIngresado(TextField txt){
        boolean b = txt.getText().isBlank();
        
        return b;
    }
    
    private boolean txtIsEmpty(){
        return (textoIngresado(direccion))||(textoIngresado(titular))||(textoIngresado(numTar))||(textoIngresado(caducidad))||(textoIngresado(cvv));
        
    }
    
    private boolean txtIsNULL(){
       
        return (direccion.getText()== null)||(titular.getText()==null)||(numTar.getText()==null)||(caducidad.getText()==null)||(cvv.getText()==null);
    }
    
    
    
    
    
}
