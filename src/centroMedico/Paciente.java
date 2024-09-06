package centroMedico;

public class Paciente {
	protected String nombre;
	protected int edad;
	protected double deuda; // contiene el total de saldo que debe el paciente

	public Paciente() {

	}

	/*
	 * public Paciente(String nombre, Fecha nac) { this.nombre=nombre; this.edad=
	 * Fecha.fechaActual() - nac; this.deuda=0; }
	 */
	public void sumarDeuda(double monto) {
		this.deuda = deuda + monto;
	}

	public double getDeuda() {
		return deuda;
	}

	public void cancelarDeuda() {
		this.deuda = 0;
	}

}
