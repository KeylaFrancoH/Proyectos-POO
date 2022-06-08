
package Ventanas;

import Modelo.Cliente;
import Modelo.Pedido;
import Modelo.Producto;
import Modelo.ProductoSelec;
import Modelo.Tipo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class VentanaPedido {
    
    private VBox root;
    private ArrayList<Producto> productos;
    static ArrayList<ProductoSelec> escogido;
    private ComboBox<Tipo> tipo;
    private ComboBox ordenar;
    private TableView<ProductoSelec> datos;
    private VBox mostrar;
    private Label csubtotal;
    private Label civa;
    private Label ctotal;
    private Scene escena;
    
    public VentanaPedido(){
        root = new VBox();
        productos = new ArrayList<>();
        escogido = new ArrayList<>();
        tipo = new ComboBox<>();
        ordenar = new ComboBox();
        datos = new TableView<>();
        mostrar = new VBox();
        csubtotal = new Label();
        civa = new Label();
        ctotal = new Label();
    }
    
    public Scene setScene(){
        escena = new Scene(getRoot(),750,550);
        return escena; 
    }
    
    public Pane getRoot(){
        
        //Seccion Superior------------------------------------------------------
        Label pedido =  new Label("Realice su Pedido");
        pedido.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:2em;");
        pedido.setPadding(new Insets(20,0,0,30));
        
        //Seccion Combobox------------------------------------------------------
        HBox contenedor1 = new HBox();
        
        HBox h1 = new HBox();
        Label l1 = new Label("Tipo:");
        l1.setStyle("-fx-font-weight:bolder");
        tipo.setItems(FXCollections.observableArrayList(Tipo.cargarTipos()));
        tipo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                mostrar.getChildren().clear();
                Tipo tp = tipo.getValue();
                mostrarProductos(tp);
            }
        });
        h1.getChildren().addAll(l1,tipo);
        h1.setSpacing(5);
        //----------------------------------------------------------------------
        HBox h2 = new HBox();
        Label l2 = new Label("Ordenar por:");
        l2.setStyle("-fx-font-weight:bolder");
        ordenar.getItems().addAll(
                "Precio",
                "Orden Alfabetico");
        h2.getChildren().addAll(l2,ordenar);
        h2.setSpacing(5);
        
        contenedor1.getChildren().addAll(h1,h2);
        contenedor1.setPadding(new Insets(0,30,0,50));
        contenedor1.setSpacing(200);
        
        //Seccion titulos-------------------------------------------------------
        HBox contenedor2 = new HBox();
        
        Label t1 = new Label("Opciones");
        t1.setStyle("-fx-text-fill:orange; -fx-font-size:2em;");
        Label t2 = new Label("Pedido");
        t2.setStyle("-fx-text-fill:orange; -fx-font-size:2em;");
        
        contenedor2.getChildren().addAll(t1,t2);
        contenedor2.setPadding(new Insets(0,30,0,30));
        contenedor2.setSpacing(325);
        
        //Seccion Pedido---------------------------------------------------------
        HBox contenedor3 = new HBox();
        
        VBox subcontenedor = new VBox();
        
        HBox h3 = new HBox();
        Label descrip = new Label("Descripcion");
        Label precio = new Label("Precio");
        Label cant = new Label ("Cantidad");
        descrip.setStyle("-fx-font-weight:bolder");
        precio.setStyle("-fx-font-weight:bolder");
        cant.setStyle("-fx-font-weight:bolder");
        descrip.setPrefWidth(125);
        precio.setPrefWidth(60);
        cant.setPrefWidth(80);
        h3.getChildren().addAll(descrip,precio,cant);
        
        subcontenedor.getChildren().addAll(h3,mostrar);
        
        //Tabla-----------------------------------------------------------------
        VBox subcontenedor2 = new VBox();
        //columna descripcion
        TableColumn<ProductoSelec,String> descripcion = new TableColumn("Descripcion");
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        descripcion.setMinWidth(130);
        
        //columna cantidad
        TableColumn<ProductoSelec,Integer> cantidad = new TableColumn("Cantidad");
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cantidad.setMaxWidth(80);
        
        //columna valor
        TableColumn<ProductoSelec,Double> valor = new TableColumn("Precio");
        valor.setCellValueFactory(new PropertyValueFactory<>("precio"));
        valor.setMaxWidth(60);
        
        //anadir las columnas
        datos.getColumns().addAll(descripcion,cantidad,valor);
        datos.setPrefWidth(270);
        subcontenedor2.getChildren().add(datos);
        
        contenedor3.getChildren().addAll(subcontenedor,subcontenedor2);
        contenedor3.setPadding(new Insets(0,30,0,30));
        contenedor3.setSpacing(100);
        
        //Seccion Totales-------------------------------------------------------
        HBox contenedor4 = new HBox();
        
        GridPane gp = new GridPane();
        Label subtotal = new Label("Subtotal:");
        Label iva = new Label("IVA:");
        Label total = new Label("Total:");
        subtotal.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:1em;");
        iva.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:1em;");
        total.setStyle("-fx-text-fill:orange; -fx-font-weight:bolder; -fx-font-size:1em;");
        gp.add(subtotal, 0, 1);
        gp.add(iva, 0, 2);
        gp.add(total, 0, 3);
        
        csubtotal.setText("0.00");
        civa.setText("0.00");
        ctotal.setText("0.00");
        gp.add(csubtotal, 1, 1);
        gp.add(civa, 1, 2);
        gp.add(ctotal, 1, 3);
        gp.setHgap(15);
 
        contenedor4.getChildren().add(gp);
        contenedor4.setPadding(new Insets(0,0,0,450));
        
        //Secciones botones-----------------------------------------------------
        HBox contenedor5 = new HBox();
        
        Button continuar = new Button("Continuar");
        continuar.setStyle("-fx-font-weight:bolder; -fx-background-color:orange; -fx-font-size:1em; ");
        Button limpiar = new Button("Limpiar");
        limpiar.setStyle("-fx-font-weight:bolder; -fx-background-color:orange; -fx-font-size:1em; ");
        limpiar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                try{
                    limpiarTabla();
                }catch(NoSuchElementException ex){
                    System.out.println("No hay elementos en la tabla");
                }
                
            }
            
        });
        
        continuar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                generarPedido();
                //EMILIO AQUI VA TU STAGE TU METODO PARA QUE SALGA TU VENTANA 
            } 
        });
        
        contenedor5.getChildren().addAll(continuar,limpiar);
        contenedor5.setSpacing(10);
        contenedor5.setAlignment(Pos.CENTER);
        //Agregar al root-------------------------------------------------------
        root.setSpacing(40);
        root.getChildren().add(pedido);
        root.getChildren().add(contenedor1);
        root.getChildren().add(contenedor2);
        root.getChildren().add(contenedor3);
        root.getChildren().add(contenedor4);
        root.getChildren().add(contenedor5);
        
        return root;
    }
    
    public void mostrarProductos(Tipo tipo){
        productos = Producto.cargarProductos(tipo);
        for(Producto p:productos){
            HBox h = new HBox();
            Label descrip = new Label(p.getDescripcion());
            descrip.setPrefWidth(130);
            Label valor = new Label(Double.toString(p.getPrecio()));
            valor.setPrefWidth(60);
            TextField cant = new TextField();
            cant.setPrefWidth(40);
            Button agregar = new Button("Agregar");
            agregar.setPrefWidth(60);
            agregar.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    try{
                        escogido.add(new ProductoSelec(p.getDescripcion(),Integer.parseInt(cant.getText()),p.getPrecio()));
                        datos.setItems(FXCollections.observableArrayList(escogido));
                        calcularTotal();
                    }catch(NumberFormatException e){
                        System.out.println("no se agrego cant");
                    }
                    
                } 
            }); 
            h.getChildren().addAll(descrip,valor,cant,agregar);
            
            mostrar.getChildren().add(h);
            mostrar.setSpacing(20);   
        }
    }
    
    public void calcularTotal(){
        double subtotal = 0;
        double iva = 0;
        double total = 0;
        
        for(ProductoSelec p:escogido){
            subtotal += (p.getPrecio()*p.getCantidad());
            iva = (subtotal*0.12);
            total = subtotal + iva; 
        }
        csubtotal.setText(Double.toString(subtotal));
        civa.setText(Double.toString(iva));
        ctotal.setText(Double.toString(total));
        
    }
    
    public void limpiarTabla() throws NoSuchElementException {
        ObservableList<ProductoSelec> selectedRows,allProducts;
        
        allProducts = datos.getItems();
        
        selectedRows = datos.getSelectionModel().getSelectedItems();
        
        for(ProductoSelec p:selectedRows){
            allProducts.remove(p);
            escogido.remove(p);
            calcularTotal();
        }
    }
    
    public void generarPedido(){
        
        Cliente cl = VentanaLogin.generarCliente();
        
        Pedido pedido = new Pedido(cl,Double.parseDouble(csubtotal.getText()),Double.parseDouble(civa.getText()),Double.parseDouble(ctotal.getText()),Pedido.generarCodigo());
        String linea = pedido.getIdPedido()+","+pedido.getCliente().getNombre()+","+pedido.getTotal();
        Pedido.escribirPedido("pedidos.txt",linea);
        Pedido.escribirBin(pedido);
    }
    
}