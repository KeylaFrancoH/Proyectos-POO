
package Proyecto;

public class Solicitud {
    
    private int codigoCita;
    private Paciente paciente;

    public Solicitud(int codigoCita, Paciente paciente) {
        this.codigoCita = codigoCita;
        this.paciente = paciente;
    }

    public int getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(int codigoCita) {
        this.codigoCita = codigoCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
}
