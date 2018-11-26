package es.udc.fi.lbd.monuzz.id.hospital.model;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import es.udc.fi.lbd.monuzz.id.hospital.converters.LocalDateTimeAttributeConverter;



public abstract class Cita implements Comparable<Cita> {
	
	protected Long idCita;
	protected String codigo;
	protected LocalDateTime dataHora;
	protected Paciente paciente;

	// Clave surrogada: idCita
	// Clave natural: codigo
	// Atributos obrigatorios: codigo, dataHora, paciente
	// Atributos únicos: codigo
		
	protected Cita() {	
	};
	
	public Cita(String codigo, LocalDateTime data, Paciente paciente) {
		if (paciente.dataOcupada(data)) throw new RuntimeException("Cita duplicada");
		this.idCita=null;
		this.codigo = codigo;
		this.dataHora = data;
		this.paciente=paciente;
		paciente.getCitas().add(this);
	}



	// GETTERS =============================================================================

	public Long getIdCita() {return idCita;	}


	public String getCodigo() {return this.codigo;}

	@Convert(converter=LocalDateTimeAttributeConverter.class)
	public LocalDateTime getDataHora() {return this.dataHora;}

	public Paciente getPaciente() {return this.paciente;}

	// SETTERS =============================================================================

	public void setIdCita(Long idPrograma) {this.idCita = idPrograma;}

	public void setCodigo(String codigo) {this.codigo = codigo;}

	public void setDataHora(LocalDateTime data) {this.dataHora = data;}

	public void setPaciente(Paciente paciente) {this.paciente = paciente;}

	
	// OUTROS =============================================================================

	@Override
	public int compareTo(Cita cita) {
		if (this.dataHora==null) return 0;
		if (cita.getDataHora()==null) return 1;
		return this.dataHora.compareTo(cita.getDataHora());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		return true;
	}
	
}
