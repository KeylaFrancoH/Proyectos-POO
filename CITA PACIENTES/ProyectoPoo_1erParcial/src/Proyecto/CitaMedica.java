
package Proyecto;

import java.util.Random;

public class CitaMedica {
    private int codigo;
    private Paciente paciente;
    private String especialidad;
    private String doctor;
    private String horario;
    double costo;
    
    public CitaMedica(){}

    public CitaMedica(int codigo, Paciente paciente, String especialidad, String doctor, String horario, double costo){
        this.codigo = codigo;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.doctor = doctor;
        this.horario = horario;
        this.costo = costo;
    }
    
    public int generarCodigo(){
        Random r = new Random();
        int aleatorio = r.nextInt(5000);
        return aleatorio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    


}
