
package Proyecto;

import static Proyecto.SistemaMedico2.doctores;
import static Proyecto.SistemaMedico2.especialidades;
import static Proyecto.SistemaMedico2.horarios;
import static Proyecto.SistemaMedico2.ingresoSistemaPaciente;
import java.util.ArrayList;
import java.util.Scanner;

public class Preferencial extends Paciente{
    
    private String codSeguro;
    private String tarjeta;
    private int puntos;

    public Preferencial(String nombre, String apellido, String usuario, String contrasenia, char tipo, String cedula, int edad, double peso, double estatura, String fechaNacimiento, char genero, String codSeguro, String tarjeta) {
        super(nombre, apellido, usuario, contrasenia, tipo, cedula, edad, peso, estatura, fechaNacimiento, genero);
        this.codSeguro = codSeguro;
        this.tarjeta = tarjeta;
    }

    public String getCodSeguro() {
        return codSeguro;
    }

    public void setCodSeguro(String codSeguro) {
        this.codSeguro = codSeguro;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
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
        
        //ACTUALIZAR LISTA DE DOCTORES Y ESPECIALIDADES
        if (citas.size()!=0){
            for(String c:citas){
                String[] campos = c.split(",");
                String especialidad = campos[1];
                String doctor = campos[2];
                if(docNuevo.contains(doctor)){
                    docNuevo.remove(doctor);
                }
                if(espNuevo.contains(especialidad)){
                    espNuevo.remove(especialidad);
                }
            }
        }
        
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
            for(String e:espNuevo){
                i+=1;
                System.out.println(i+". "+e);
            }
            System.out.print("Escoja una especialidad: ");
            String seleccion = sc.next();
            
            //valida que lo que haya ingresado sea correcto

            while((Integer.parseInt(seleccion)>espNuevo.size())){
                System.out.println("\n");
                System.out.println("Ingrese una opción válida");
                
                System.out.println("");
                System.out.println("****ESPECIALIDADES DISPONIBLES PARA CITAS*****");
                System.out.println("*                                            *");
                System.out.println("**********************************************");
                i=0;
                for(String e:espNuevo){
                    i+=1;
                    System.out.println(i+". "+e);
                }
                System.out.print("Escoja una especialidad: ");
                seleccion = sc.next();
            }
            
            String esp = espNuevo.get(Integer.parseInt(seleccion)-1);
            
            //Selecciona los doctores

            System.out.println("*******DOCTORES DISPONIBLES PARA CITAS********");
            System.out.println("*                                            *");
            System.out.println("**********************************************");
            i=0;
            for(String e:docNuevo){
                i+=1;
                System.out.println(i+". "+e);
            }
            System.out.print("Escoja un doctor: ");
            seleccion = sc.next();
            
            //valida que lo que haya ingresado sea correcto
            while(Integer.parseInt(seleccion)>docNuevo.size()){
                System.out.println("\n"+ "Ingrese una opción valida"+"\n");
                System.out.println("*******DOCTORES DISPONIBLES PARA CITAS********");
                System.out.println("*                                            *");
                System.out.println("**********************************************");
                i=0;
                for(String e:docNuevo){
                    i+=1;
                    System.out.println(i+". "+e);
                }
                System.out.print("Escoja un doctor: ");
                seleccion = sc.next();
                
            }
            
            String doc = docNuevo.get(Integer.parseInt(seleccion)-1);

            //Selecciona un horario disponible 
            
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
            
            //valida que la opción sea válida
            
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
    
    
