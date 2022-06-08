
package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaMedico2 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> especialidades=  new ArrayList<String>();
    static ArrayList<String> doctores=  new ArrayList<String>();
    static ArrayList<String> horarios=  new ArrayList<String>();
    
    
    public static void main(String[] args){        
        especialidades.add("Alergia");
        especialidades.add("Anatomia");
        especialidades.add("Anestesiologia");
        especialidades.add("Cardiologia");
        especialidades.add("Cirugia");
        doctores.add("Carlos Cruz");
        doctores.add("Manuel Peña");
        doctores.add("Luz Lucero");
        horarios.add("17/09/2020,09:30");
        horarios.add("18/07/2020,10:30");
        horarios.add("12/07/2020,20:00");
        horarios.add("19/07/2020,07:30");
        horarios.add("20/08/2020,14:30");
        mostrarMenu();
    }
    
    
    public static void mostrarMenu(){
        String contrasena = "";
        
        while(!contrasena.equals("0")){
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("            BIENVENIDO AL SISTEMA             ");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("Usuario: ");
            String usuario = sc.next();
            System.out.print("Contraseña: ");
            contrasena = sc.next();
            
            ArrayList<String> lineas = Archivo.LeerArchivo("usuarios.txt");
            for (String s:lineas){
                String [] campos = s.split(","); //["nombre", "apellido", "user", "psswrd",...,"CI"]
                String nombre = campos[0];
                String apellido = campos[1];
                String user = campos[2];
                String clave = campos[3];
                char tipo = campos[4].charAt(0);
                String cedula,fechaNacimiento;
                int edad;
                double peso,estatura;
                char genero;
                
            
                if (user.equals(usuario)&& clave.equals(contrasena)){ 
                    switch (tipo){
                        case 'P':
                            cedula = campos[5];
                            edad = Integer.parseInt(campos[6]);
                            peso = Double.parseDouble(campos[7]);
                            estatura = Double.parseDouble(campos[8]);
                            fechaNacimiento = campos[9];
                            genero = campos[10].charAt(0);
                            String seguro = campos[11];
                            String tarjeta = campos[12];
                            Preferencial preferencial = new Preferencial(nombre,apellido,user,clave,tipo,cedula,edad,peso,estatura,fechaNacimiento,genero,seguro,tarjeta);
                            ingresoSistemaPaciente((Paciente)preferencial);
                            break;
                        case 'E':
                            cedula = campos[5];
                            edad = Integer.parseInt(campos[6]);
                            peso = Double.parseDouble(campos[7]);
                            estatura = Double.parseDouble(campos[8]);
                            fechaNacimiento = campos[9];
                            genero = campos[10].charAt(0);
                            String cuenta = campos[11];
                            Estandar estandar = new Estandar(nombre,apellido,user,clave,tipo,cedula,edad,peso,estatura,fechaNacimiento,genero,cuenta);
                            ingresoSistemaPaciente((Paciente)estandar);
                            break;
                        case 'A': 
                            Asistente asistente = new Asistente(nombre,apellido,usuario,clave,tipo);
                            ingresoSistemaAsistente();
                            
                            break;
                        default:
                            System.out.println("No contiene un acceso");
                    }
                
                    
                }
                
                
            }
        } 
    }
    
    public static void ingresoSistemaPaciente(Paciente paciente){
        String opcion="";
        while(!opcion.equals("0")){
            System.out.println(" ");
            System.out.println("********************MENU**********************");
            System.out.println("*                                            *");
            System.out.println("**********************************************");
            System.out.println("1. Solicitar una cita médica");
            System.out.println("2. Consultar cita pendiente");
            System.out.println("3. Solicitar una cancelacion de cita");
            System.out.println("4. Salir");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next();
            switch (opcion){
                case "1": 
                    if(paciente instanceof Estandar){
                        
                        ArrayList<String> citas = Archivo.LeerArchivo("citas_medicas.txt");
                        if(citas.size()!=0){
                            for(String c: citas){
                                String[] espacios = c.split(","); //["codigo", "esp.", "Dname Dlname", "name Lname", fecha, hora]
                            
                                String nombreCompleto = espacios[3];  //"Nombre Apellido"
                                String nombre= ((Estandar) paciente).nombre+" "+ ((Estandar)paciente).apellido;
                            
                                if(nombreCompleto.equals(nombre)){
                                    System.out.println("Al ser un paciente estandar, solo puede solicitar una cita. ");
                                    ingresoSistemaPaciente(paciente);
                                }
                            
                            }
                        }
                        else ((Estandar) paciente).solicitarCita();
                        
                        
                        
                    }
                    
                    else if(paciente instanceof Preferencial){
                        ((Preferencial)paciente).solicitarCita();
                    }
                    
                    break;
                case "2":
                    paciente.consultarCita();
                    break;
                case "3":
                    paciente.solicitudCancelarCita();
                    System.out.println("");
                    break;
                case "4":    
                    System.out.println("Gracias por tu visita!");
                    mostrarMenu();
                default:
                    System.out.println("Opcion no valida!");
                    ingresoSistemaPaciente(paciente);
            }
        }
    }
    
    public static void ingresoSistemaAsistente(){
        String opcion = "";
        while (!opcion.equals("0")){
            System.out.println(" ");
            System.out.println("***************MENU ASISTENTE*****************");
            System.out.println("*                                            *");
            System.out.println("**********************************************");
            System.out.println("1. Llenar perfil paciente");
            System.out.println("2. Consultar paciente con cita");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next();
            switch(opcion){
                case "1":
                    System.out.println("");
                    Asistente.llenarPerfilPaciente();
                    System.out.println("");
                    break;
                case "2":
                    System.out.println("");
                    Asistente.consultarPacientesCitas();
                    System.out.println("");
                    break;
                case "3":
                    System.out.println("gracias por su visita.");
                    mostrarMenu();
                default: 
                    System.out.println("Opcion ingresada invalido.");
                    ingresoSistemaAsistente();
            }
        }
    }
    
    
}