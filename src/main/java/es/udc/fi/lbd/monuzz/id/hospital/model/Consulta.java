package es.udc.fi.lbd.monuzz.id.hospital.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class Consulta extends Cita {
	
	private String motivo;
	private Medico medico;
	private String informe;
	private Set<TipoDoenza> doenzas = new HashSet<TipoDoenza>();

	// Atributos obrigatorios: motivo, medico
	// Atributos únicos: ----
		
	protected Consulta() {	
	};
	
	public Consulta(String codigo, LocalDateTime data, Paciente paciente, String motivo, Medico medico) {
		super (codigo, data, paciente);
		this.motivo=motivo;
		this.medico=medico;
		this.informe=null;
		this.doenzas = new HashSet<TipoDoenza>();
	}

	// GETTERS =============================================================================

	public String getMotivo() { return motivo; }
	
	public Medico getMedico() { return medico;	}

	public String getInforme() { return informe; }
		
	public Set<TipoDoenza> getDoenzas() { return doenzas; }

	
	// SETTERS =============================================================================
	

	public void setMotivo(String motivo) {	this.motivo = motivo; }

	public void setMedico(Medico medico) {	this.medico = medico; }

	public void setInforme(String informe) { this.informe = informe; }

	public void setDoenzas(Set<TipoDoenza> doenzas) { this.doenzas = doenzas; }

		
	// OUTRAS =============================================================================
	
	@Override
	public String toString() {
		
		return "Cita (Consulta) [idCita=" + idCita + ", codigo=" + codigo + ", data=" + dataHora + ", paciente=" + paciente.getNumPaciente() + 
				", motivo=" + motivo + ", medico=" + medico + ", informe=" + informe + "]";
	}


}
