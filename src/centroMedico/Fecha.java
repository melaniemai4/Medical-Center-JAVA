package centroMedico;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {
	int dia, mes, anio;
	Calendar fechaReal;

	public Fecha() {
		fechaReal = new GregorianCalendar();
		this.dia = fechaReal.get(Calendar.DATE);
		this.mes = fechaReal.get(Calendar.MONTH) + 1;
		this.anio = fechaReal.get(Calendar.YEAR);
	}

	public Fecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		fechaReal = new GregorianCalendar(anio, mes - 1, dia);
	}

	public static long diferenciaEntre(Fecha primera, Fecha segunda) {
		Date startDate = primera.fechaReal.getTime();
		Date endDate = segunda.fechaReal.getTime();
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		return diffDays;
	}

	public static Fecha hoy() {
		Fecha hoy = new Fecha();
		return hoy;
	}

	@Override
	public String toString() {
		return "[" + dia + "/" + mes + "/" + anio + "]";
	}

}
