package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Toshiba
 */
public class Tipo {
    private String nombre;
    private String codigo;

    public Tipo(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public static ArrayList<Tipo>cargarTipos() {
        ArrayList<Tipo> tp = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader("src/Recursos/tipo.txt"))){
            String linea;
            while((linea = bf.readLine())!=null){
                String[] campos = linea.split(";");
                
                tp.add(new Tipo(campos[0], campos[1]));
            } 
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        
        }
        return tp;
    }

    @Override
    public String toString() {
        return nombre;
    }

}