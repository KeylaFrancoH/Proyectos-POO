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
public class Cliente {
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasena;

    public Cliente(String nombre, String apellido, String usuario, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public static ArrayList<Cliente>cargarClientes() {
        ArrayList<Cliente> cl = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader("src/Recursos/cliente.txt"))){
            String linea;
            while((linea = bf.readLine())!=null){
                String[] campos = linea.split(";");
                
                cl.add(new Cliente(campos[0], campos[1], campos[2], campos[3]));
            } 
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        
        }
        return cl;
    }

    
}
