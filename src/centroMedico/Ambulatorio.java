package centroMedico;

public class Ambulatorio extends Paciente {
	public Ambulatorio(String nombre, Fecha nac) {
		super();
		this.nombre = nombre;
		this.edad = (int) Fecha.diferenciaEntre(nac, Fecha.hoy());
		this.deuda = 0;
	}
}