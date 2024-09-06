package centroMedico;

public class Privado extends Paciente {
	public Privado(String nombre, Fecha nac) {
		super();
		this.nombre = nombre;
		this.edad = (int) Fecha.diferenciaEntre(nac, Fecha.hoy());
		this.deuda = 0;
	}

}
