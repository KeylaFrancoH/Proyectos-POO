/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Locales {
    
    private double ubicacionX;
    private double ubicacionY;
    private String lugar;

    public Locales() {
    }

    public Locales(double ubicacionX, double ubicacionY, String lugar) {
        this.ubicacionX = ubicacionX;
        this.ubicacionY = ubicacionY;
        this.lugar = lugar;
    }

    public double getUbicacionX() {
        return ubicacionX;
    }

    public void setUbicacionX(double ubicacionX) {
        this.ubicacionX = ubicacionX;
    }

    public double getUbicacionY() {
        return ubicacionY;
    }

    public void setUbicacionY(double ubicacionY) {
        this.ubicacionY = ubicacionY;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    public static ArrayList<Locales>LeerArchivo() {
        ArrayList<Locales> loc = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader("src/Recursos/locales.txt"))){
            String linea;
            while((linea = bf.readLine())!=null){
                String[] campos = linea.split(",");
                
                loc.add(new Locales(Double.parseDouble(campos[0]), Double.parseDouble(campos[1]), campos[2]));
            } 
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        
        }
        return loc;
    }

    @Override
    public String toString() {
        return lugar;
    }
    
    

}
