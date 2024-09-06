package centroMedico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Centro {
	private String nombre;
	private String cuit;
	private double valorXDiaInternacion;
	private HashMap<String, Double> especialidades;
	private HashMap<Integer, Paciente> pacientes;
	private HashMap<Integer, Medico> medicos;
	private ArrayList<Atencion> atenciones;

	public Centro(String nombre, String cuit, double valorXDiaInt) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.valorXDiaInternacion = valorXDiaInt;
		especialidades = new HashMap<String, Double>();
		pacientes = new HashMap<Integer, Paciente>();
		medicos = new HashMap<Integer, Medico>();
		atenciones = new ArrayList<Atencion>();
	}

	public void agregarEspecialidad(String nombre, double valorConsulta) {
		especialidades.put(nombre, valorConsulta);
		// agregar al map y ya
	}

	public void agregarPacientePrivado(String nombre, int hC, Fecha nac) {
		pacientes.put(hC, new Privado(nombre, nac));
		// creamos y agregamos
	}

	public void agregarPacienteAmbulatorio(String nombre, int hC, Fecha nac) {
		pacientes.put(hC, new Ambulatorio(nombre, nac));
		// creamos y agregamos
	}

	public void agregarPacienteObraSocial(String nombre, int hC, Fecha nac, String obrasoc, double porcentaje) {
		pacientes.put(hC, new ObraSocial(nombre, nac, obrasoc, porcentaje));
		// creamos y agregamos
	}

	public void agregarMedico(String nombre, int matricula, String nomEspecialidad, double valorTratamiento) {
		medicos.put(matricula, new Medico(nombre, nomEspecialidad, valorTratamiento));
		// crea al medico
		// lo une al map a partir de nro matricula
	}

	public void agregarAtencion(int hC, Fecha fecha, int matricula) { // consultorio
		Medico m = medicos.get(matricula);
		Paciente p = pacientes.get(hC);
		atenciones.add(new ConsultorioExterno(p, fecha, m));
		String especialidad = m.getEspecialidad();
		double valorEspecialidad = especialidades.get(especialidad);
		p.sumarDeuda(valorEspecialidad);
		pacientes.put(hC, p); // quizas innecesaria!!!
		// llamamos constructor de consultorio y la creamos
		// buscamos en el map al medico con su matricula
		// obtenemos su especialidad y buscamos su valor en el map de especialidades
		// le sumamos deuda a partir del valor buscado anteriormente
	}

	public void agregarAtencion(int hC, Fecha fecha) { // guardia
		// llamamos constructor de guardia
		Paciente p = pacientes.get(hC);
		atenciones.add(new Guardia(p, fecha));
	}

	public void agregarInternacion(int hC, String area, Fecha fingreso) {
		// registra internacion
		Paciente p = pacientes.get(hC);
		ObraSocial os = (ObraSocial) pacientes.get(hC);
		if (os.isInternado() == false) { // solo puede internarse si actualmente no está internado
			atenciones.add(new Internacion(p, area, fingreso));
			os.setInternado(true);
		}
	}
	
	public void altaInternacion(int hC, Fecha fechaAlta) {
		ObraSocial os = (ObraSocial) pacientes.get(hC);
		double porcentaje = os.getPorcentaje();
		Paciente p = pacientes.get(hC);
		long cantDiasInternado = 0;
		for (Atencion a : atenciones) {
			if (a instanceof Internacion) {
				Internacion i = (Internacion) a;
				if (i.paciente.equals(p)) {
					cantDiasInternado = Fecha.diferenciaEntre(i.getFechaInternacion(), fechaAlta);
					i.darAlta(fechaAlta);
					os.setInternado(false);
				}
			}
		}
		double montoASumar = ((double) cantDiasInternado) * valorXDiaInternacion * porcentaje;
		p.sumarDeuda(montoASumar);
		// busca al paciente, se queda con el porcentaje de obra social
		// calcula lacantidad de dias que estuvo internado, lo multiplica por el valor
		// del centro medico x dia
		// y el porcentaje de la obra social por ultimo, lo suma a la deuda del
		// paciente.
	}

	public void agregarTratamiento(int hC, int matricula, String atencion) {
		double valorTratamiento = medicos.get(matricula).getValorAtencionAmbulatoria();
		Paciente p = pacientes.get(hC);
		p.sumarDeuda(valorTratamiento);
		// a partir de la matricula obtener honorarios del medico
		// buscar a partir de la hc al paciente y sumarle deuda a partir de honorarios
		// del medico
	}

	public Map<Fecha, String> atencionesEnConsultorio(int hC) {
		Map<Fecha, String> atencionesDePaciente = new HashMap<Fecha, String>();
		Paciente p = pacientes.get(hC);
		for (Atencion a : atenciones) {
			if (a instanceof ConsultorioExterno && a.getPaciente().equals(p)) {
				ConsultorioExterno ce = (ConsultorioExterno) a;
				atencionesDePaciente.put(ce.getFechaAtencion(), ce.getMedico().getEspecialidad());
			}
		}
		return atencionesDePaciente;
	}

	public ArrayList<Integer> listaInternacion() {
		ArrayList<Integer> listaInternados = new ArrayList<Integer>();
		for (Atencion a : atenciones) {
			if (a instanceof Internacion) {
				Internacion i = (Internacion) a;
				if (i.getFechaAlta() == null) {
					for (Entry<Integer, Paciente> entry : pacientes.entrySet()) {
						if (entry.getValue().equals(i.getPaciente())) {
							listaInternados.add(entry.getKey());
						}
					}
				}
			}
		}
		return listaInternados;
	}

	public double getSaldo(int hC) {
		Paciente p = pacientes.get(hC);
		return p.getDeuda();
		// buscamos en el map al paciente, y llamamos a getDeuda
	}

	public void pagarSaldo(int hC) {
		Paciente p = pacientes.get(hC);
		for (Atencion a : atenciones) {
			if (a instanceof ConsultorioExterno && a.getPaciente().equals(p)) {
				ConsultorioExterno ce = (ConsultorioExterno) a;
				ce.setPagado(true);
			}
			if (a instanceof Internacion && a.getPaciente().equals(p)) {
				Internacion i = (Internacion) a;
				i.setPagado(true);
			}
		}
		p.cancelarDeuda();
	}

	@Override
	public String toString() {
		return "[" + nombre + ", " + cuit + "]";
	}

}
