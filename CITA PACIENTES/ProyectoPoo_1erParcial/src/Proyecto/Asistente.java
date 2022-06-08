
package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class Asistente extends Usuario{

    public Asistente(String nombre, String apellido, String usuario, String contrasenia, char tipo) {
        super(nombre, apellido, usuario, contrasenia, tipo);
    }
    
    public static void llenarPerfilPaciente(){
        Scanner sc = new Scanner(System.in);
        System.out.println("***********LLENAR PERFIL PACIENTE*************");
        System.out.println("*                                            *");
        System.out.println("**********************************************");
        System.out.print("Ingrese el usuario: ");
        String user = sc.next();
        
        ArrayList<String> usuarios = Archivo.LeerArchivo("usuarios.txt");
        for (String u:usuarios){
            String[] campos = u.split(",");
            String name = campos[0];
            String lastname = campos[1];
            String username = campos[2];
            char type = campos[4].charAt(0);
            if (user.equals(username)){
                switch(type){
                    case 'P':
                        System.out.println("Actualice los datos para "+name+" "+lastname);
                        System.out.println("");
                        System.out.print("Cedula:");
                        String cedula = sc.next();
                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(sc.next());
                        System.out.print("Peso: ");
                        double peso = Double.parseDouble(sc.next());
                        System.out.print("Estatura: ");
                        double estatura = Double.parseDouble(sc.next());
                        System.out.print("Fecha de Nacimiento: ");
                        String fechaNacimiento = sc.next();
                        System.out.print("Genero: ");
                        String genero = sc.next();
                        
                        String linea = username+","+cedula+","+edad+","+peso+","+estatura+","+fechaNacimiento+","+genero;
                        Archivo.EscribirArchivo("perfilPaciente.txt", linea);
                        break;
                    case 'E':
                        System.out.println("Actualice los datos para "+name+" "+lastname);
                        System.out.println("");
                        System.out.print("Cedula: ");
                        cedula = sc.next();
                        System.out.print("Edad: ");
                        edad = Integer.parseInt(sc.next());
                        System.out.print("Peso: ");
                        peso = Double.parseDouble(sc.next());
                        System.out.print("Estatura: ");
                        estatura = Double.parseDouble(sc.next());
                        System.out.print("Fecha de Nacimiento: ");
                        fechaNacimiento = sc.next();
                        System.out.print("Genero: ");
                        genero = sc.next();
                        
                        linea = username+","+cedula+","+edad+","+peso+","+estatura+","+fechaNacimiento+","+genero;
                        Archivo.EscribirArchivo("perfilPaciente.txt", linea);
                        break;
                    default:
                        System.out.println("No es un paciente");
                }
            }
        }
    }
    
    public static void consultarPacientesCitas(){
        ArrayList<String> citas = Archivo.LeerArchivo("citas_medicas.txt");
        ArrayList<String> usuarios = Archivo.LeerArchivo("usuarios.txt");
        
        if(citas.size()!=0){
            System.out.println("********PACIENTEs CON CITAS PENDIENTE*********");
            System.out.println("*                                            *");
            System.out.println("**********************************************");
            
            for (String c:citas){
                String[]  campos = c.split(",");  //["codigo", "esp", "doc", "Nombre Apellido", "horario"]
                String name = campos[3]; //"Nombre Apellido"
                
                for(String k: usuarios){
                    String[] boxes = k.split(",");  //["nombre", "apellido", "user", "psswrd", "tipo", "CI", "age",...]
                    String nom = boxes[0];
                    String Lname = boxes[1];
                    String nombreCompleto = nom+ " " + Lname;
                    
                    if(name.equals(nombreCompleto)){
                        System.out.println(name+ ": "+Integer.parseInt(boxes[6])+" años.");
                        
                    }
                    
            
            
            }
            
            
        }
        
        
        
    }
    else{
            System.out.println("No hay pacientes con citas medicas para mostrar");
            
        }
            
        }
    
}
