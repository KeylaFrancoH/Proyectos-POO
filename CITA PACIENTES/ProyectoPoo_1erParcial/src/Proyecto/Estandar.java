
package Proyecto;

import static Proyecto.SistemaMedico2.doctores;
import static Proyecto.SistemaMedico2.ingresoSistemaPaciente;
import static Proyecto.SistemaMedico2.especialidades;
import static Proyecto.SistemaMedico2.horarios;
import java.util.ArrayList;
import java.util.Scanner;

public class Estandar extends Paciente {
    
    private String cuentaBanco;

    public Estandar(String nombre, String apellido, String usuario, String contrasenia, char tipo, String cedula, int edad, double peso, double estatura, String fechaNacimiento, char genero, String cuentaBanco) {
        super(nombre, apellido, usuario, contrasenia, tipo, cedula, edad, peso, estatura, fechaNacimiento, genero);
        this.cuentaBanco = cuentaBanco;
    }

    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }
    
    @Override
    public void solicitarCita(){
        Paciente paciente = this;
        ArrayList<String> citas = Archivo.LeerArchivo("citas_medicas.txt");
        ArrayList<String> espNuevo =  especialidades;
        ArrayList<String> docNuevo =  doctores;
        ArrayList<String> horaNuevo =  horarios;
        
        //ACTUALIZAR LISTA DE HORARIOS DISPONIBLES
        if (citas.size()!=0){
            for (String c:citas){
                String[] campos = c.split(",");
                String fecha = campos[4];
                String hora = campos[5];
                String horario = fecha+","+hora;
                if(horaNuevo.contains(horario)){
                    horaNuevo.remove(horario);
                }
            }
        }
        //ESCOGE LA CITA
            Scanner sc = new Scanner(System.in);
            System.out.println("");
            System.out.println("****ESPECIALIDADES DISPONIBLES PARA CITAS*****");
            System.out.println("*                                            *");
            System.out.println("**********************************************");
            int i=0;
            for(String e:especialidades){
                i+=1;
                System.out.println(i+". "+e);
            }
            System.out.print("Escoja una especialidad: ");
            String seleccion = sc.next();
            
            //valida que lo ingresado sea correcto
            
            while((Integer.parseInt(seleccion)>especialidades.size())){
                System.out.println("");
                System.out.println("Ingrese una opción válida");
                
                System.out.println("");
                System.out.println("****ESPECIALIDADES DISPONIBLES PARA CITAS*****");
                System.out.println("*                                            *");
                System.out.println("**********************************************");
                i=0;
                for(String e:especialidades){
                    i+=1;
                    System.out.println(i+". "+e);
                }
                System.out.print("Escoja una especialidad: ");
                seleccion = sc.next();
            }
            
            String esp = especialidades.get(Integer.parseInt(seleccion)-1);
            
            //Muestra los doctores disponibles

            System.out.println("*******DOCTORES DISPONIBLES PARA CITAS********");
            System.out.println("*                                            *");
            System.out.println("**********************************************");
            i=0;
            for(String e:doctores){
                i+=1;
                System.out.println(i+". "+e);
            }
            System.out.print("Escoja un doctor: ");
            seleccion = sc.next();
            
            // valida el ingreso del usuario
            
            while(Integer.parseInt(seleccion)>doctores.size()){
                System.out.println("\n"+ "Ingrese una opción valida"+"\n");
                
                System.out.println("*******DOCTORES DISPONIBLES PARA CITAS********");
                System.out.println("*                                            *");
                System.out.println("**********************************************");
                i=0;
                for(String e:doctores){
                    i+=1;
                    System.out.println(i+". "+e);
                }
                System.out.print("Escoja un doctor: ");
                seleccion = sc.next();
                
            }
            
            String doc = doctores.get(Integer.parseInt(seleccion)-1);

            System.out.println("*******HORARIOS DISPONIBLES PARA CITAS********");
            System.out.println("*                                            *");
            System.out.println("**********************************************");
            i=0;
            for(String e:horaNuevo){
                i+=1;
                System.out.println(i+". "+e);
            }
            System.out.print("Escoja un horario: ");
            seleccion = sc.next();
            
            //Valida que lo ingresado sea correcto
            
            while (Integer.parseInt(seleccion)>horaNuevo.size()){
                System.out.println("\n"+ "Ingrese una opción valida"+"\n");
                System.out.println("*******HORARIOS DISPONIBLES PARA CITAS********");
                System.out.println("*                                            *");
                System.out.println("**********************************************");
                i=0;
                for(String e:horaNuevo){
                    i+=1;
                    System.out.println(i+". "+e);
                }
                System.out.print("Escoja un horario: ");
                seleccion = sc.next();
            }
            
            String horario = horaNuevo.get(Integer.parseInt(seleccion)-1);

            System.out.println("");
            System.out.println("Usted escogio:");
            System.out.println("Especialidad: "+esp);
            System.out.println("Doctor: "+doc);
            System.out.println("Horario: "+horario);
            System.out.print("Desea solicitar la cita (S/N): ");
            String confirma = sc.next();
            System.out.println("");

            if(confirma.equals("s")){
                CitaMedica cita = new CitaMedica(0,paciente,esp,doc,horario,25.00);
                int code = cita.generarCodigo();
                cita.setCodigo(code);
                String linea = code+","+esp+","+doc+","+nombre+" "+apellido+","+horario;
                Archivo.EscribirArchivo("citas_medicas.txt", linea);
                System.out.println("Su cita se ha reservado con el código: "+code);
            }
            else ingresoSistemaPaciente(paciente);
    }
        
}
