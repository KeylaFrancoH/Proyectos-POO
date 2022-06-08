
package Proyecto;

import java.util.ArrayList;
import java.util.Scanner;


public class Paciente extends Usuario{
    
    //protected Usuario usuario;
    protected String cedula;
    protected int edad;
    protected double peso;
    protected double estatura;
    protected String fechaNacimiento;
    protected char genero;
    

    public Paciente(String nombre, String apellido, String usuario, String contrasenia, char tipo, String cedula, int edad, double peso, double estatura, String fechaNacimiento, char genero) {
        super(nombre, apellido, usuario, contrasenia, tipo);
        this.cedula = cedula;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }
    
    public void solicitarCita(){}
    
    public void consultarCita(){
        
        Paciente paciente =this;
        ArrayList<String> citas = Archivo.LeerArchivo("citas_medicas.txt");
        
        if(citas.size()!= 0){
            System.out.print("Ingrese un codigo: ");
            Scanner sc = new Scanner(System.in);
            String codigo = sc.next();
            for(String s:citas){
                String[] campos = s.split(",");
                String code = campos[0];
                if(codigo.equals(code)){
                    System.out.println("");
                    System.out.println("****************CITA PENDIENTE****************");
                    System.out.println("*                                            *");
                    System.out.println("**********************************************");
                    System.out.println("ESPECIALIDAD: "+campos[1]);
                    System.out.println("DOCTOS: Dr. "+campos[2]);
                    System.out.println("FECHA: "+campos[4]);
                    System.out.println("HORA: "+campos[5]);
                    if(paciente instanceof Estandar) System.out.println("Como usted es un paciente estandar,\nse han debitado $25.00 dolares de su cuenta");
                    else if(paciente instanceof Preferencial) System.out.println("Como usted es un paciente preferencial,\nse han debitado $25.00 dolares de su cuenta\ny se le han agregado 100 puntos");
            }
                else {
                    System.out.println("Codigo Inexistente");
                }
            }
        }
        
        else{
            System.out.println("No existen citas por consultar.");
        }
        
    }
    
    public void solicitudCancelarCita(){
        Scanner sc = new Scanner(System.in);
        Paciente paciente = this;
        ArrayList<String> citas = Archivo.LeerArchivo("citas_medicas.txt");
        
        if(citas.size()!= 0){
            System.out.println("");
            System.out.println("******SOLICITUD DE CANCELACION DE CITA********");
            System.out.println("*                                            *");
            System.out.println("**********************************************");                    
            System.out.print("Ingrese el codigo de la cita a cancelar: ");
            String codigo = sc.next();
            for (String c:citas){
                String[] campos = c.split(",");
                String code = campos[0];
                if(codigo.equals(code)){
                    int codigoCita = Integer.parseInt(codigo);
                    Solicitud solicitud = new Solicitud(codigoCita,paciente);
                    String linea = codigoCita+","+usuario;
                    Archivo.EscribirArchivo("solicitudCancelacion.txt", linea);
                    System.out.println("La solicitud ha sido enviado cuando el asistente lo revise");
                    System.out.println("se cancelara su cita");
                }
                else System.out.println("Codigo Inexistente");
            }
        }
        else{
            System.out.println("No puede cancelar citas debido a que no existen citas creadas, por favor cree una");
        }
        
    }
}

