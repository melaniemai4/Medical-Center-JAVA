package centroMedico;

public class ConsultorioExterno extends Atencion {
	private Medico medico;
	private Fecha fechaAtencion;
	private boolean pagado;

	public ConsultorioExterno(Paciente paciente, Fecha fechaDeAtencion, Medico medico) {
		super();
		this.paciente = paciente;
		this.medico = medico;
		this.fechaAtencion = fechaDeAtencion;
		this.setPagado(false);
	}

	public Medico getMedico() {
		return medico;
	}

	public Fecha getFechaAtencion() {
		return fechaAtencion;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

}