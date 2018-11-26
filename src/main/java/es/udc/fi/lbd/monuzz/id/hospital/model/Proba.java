package es.udc.fi.lbd.monuzz.id.hospital.model;

import java.time.LocalDateTime;

public class Proba extends Cita {
	
	private String especificacions;
	private TipoProba tipoProba;
	private String resultado;

	// Atributos obrigatorios: especificacions, tipoproba
	// Atributos únicos: ---
		
	protected Proba() {	
	};
	
	public Proba(String codigo, LocalDateTime data, Paciente paciente, String especificacions, TipoProba tipoProba) {
		super (codigo, data, paciente);
		this.especificacions=especificacions;
		this.tipoProba=tipoProba;
		this.resultado=null;;
	}

	// GETTERS =============================================================================

	public String getEspecificacions() { return especificacions; }
	
	public TipoProba getTipoProba() { return this.tipoProba;	}

	public String getResultado() { return resultado; }
	
	
	// SETTERS =============================================================================
	

	public void setEspecificacions(String motivo) {	this.especificacions = motivo; }

	public void setTipoProba(TipoProba tipoProba) { this.tipoProba = tipoProba; }

	public void setResultado(String resultado) { this.resultado = resultado; }

		
	// OUTRAS =============================================================================
	
	@Override
	public String toString() {
		
		return "Cita (Proba) [idCita=" + idCita + ", codigo=" + codigo + ", data=" + dataHora + ", paciente=" + paciente.getNumPaciente() + 
				", especificacions=" + especificacions + ", tipoProba=" + (tipoProba!=null?tipoProba.getNome():"null") + ", resultado=" + resultado + "]";
	}
	
}
