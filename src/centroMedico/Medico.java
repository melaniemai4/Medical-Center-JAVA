package centroMedico;

public class Medico {
	private String nombre;
	private String especialidad;
	private double valorAtencionAmbulatoria; // o valorHonorarios

	public Medico(String nombre, String especialidad, double valorTratamiento) {
		this.nombre = nombre;
		this.especialidad = especialidad;
		this.valorAtencionAmbulatoria = valorTratamiento;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public double getValorAtencionAmbulatoria() {
		return valorAtencionAmbulatoria;
	}

}
