package centroMedico;

public class Guardia extends Atencion {
	private Fecha fechaAtencion;

	public Guardia(Paciente paciente, Fecha fechaDeAtencion) {
		super();
		this.paciente = paciente;
		this.fechaAtencion = fechaDeAtencion;

	}
	
}
