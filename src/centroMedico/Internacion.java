package centroMedico;

public class Internacion extends Atencion {
	private Fecha fechaInternacion;
	private Fecha fechaAlta;
	private String area;
	private boolean pagado;
	
	public Internacion(Paciente paciente, String areaInternacion, Fecha fechaInternacion) {
		super();
		this.paciente = paciente;
		this.fechaInternacion = fechaInternacion;
		this.area = areaInternacion;
		this.fechaAlta = null;
		this.pagado = false;
	}

	public void darAlta(Fecha fechaAlta) {
		if (Fecha.diferenciaEntre(fechaInternacion, fechaAlta) >= 0) {
			this.fechaAlta = fechaAlta;
		} else {
			System.out.println("Se ingreso una fecha invalida");
		}
	}

	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	public Fecha getFechaInternacion() {
		return fechaInternacion;
	}
	

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

}
