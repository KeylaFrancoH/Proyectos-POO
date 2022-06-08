package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Toshiba
 */
public class Producto implements Comparable<Producto>{
    
    private Tipo tipo;
    private String descripcion;
    private double precio;

    public Producto(Tipo tipo, String descripcion, double precio) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public static ArrayList<Producto>cargarProductos(Tipo tipo){
        ArrayList<Producto> p = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader("src/Recursos/menu.txt"))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] campos = linea.split(",");
                if(campos[2].equals(tipo.getCodigo())){
                    p.add(new Producto(tipo,campos[0],Double.parseDouble(campos[1])));
                }
            }
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }        
        return p;
        
    }
    
    public int generarCodigo(){
        Random r = new Random();
        int aleatorio = r.nextInt(5000);
        return aleatorio;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    

    @Override
    public int compareTo(Producto p) {
        
        int num = 0;
        if (this.getPrecio()<p.getPrecio()){
            num = 1;  
        }
        else if(this.getPrecio()>p.getPrecio()){
            num = -1;
        }
        else{
            num = 0;
        }
        return num;

    }
    
    
}
