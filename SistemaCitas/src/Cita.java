public class Cita {
    private int idCita;
    private String fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(int idCita, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "ID Cita: " + idCita + "\nFecha: " + fechaHora + "\nMotivo: " + motivo +
                "\nDoctor: " + doctor.getNombre() + " (Especialidad: " + doctor.getEspecialidad() + ")" +
                "\nPaciente: " + paciente.getNombre();
    }
}
