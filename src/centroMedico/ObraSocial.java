package centroMedico;

public class ObraSocial extends Paciente {
	private boolean internado;
	private String obrasocial;
	private double porcentaje;

	public ObraSocial(String nombre, Fecha nac, String obrasoc, double p) {
		super();
		this.nombre = nombre;
		this.edad = (int) Fecha.diferenciaEntre(nac, Fecha.hoy());
		this.internado = false;
		this.obrasocial = obrasoc;
		this.porcentaje = p;

	}

	public boolean isInternado() {
		return internado;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setInternado(boolean internado) {
		this.internado = internado;
	}

	
}
