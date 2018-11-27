package es.udc.fi.lbd.monuzz.id.hospital.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fi.lbd.monuzz.id.hospital.converters.LocalDateTimeAttributeConverter;

@Entity
@Inheritance
@Table(name = "CITA")
public abstract class Cita implements Comparable<Cita> {
	
	@Id
	@SequenceGenerator(name="citaId", sequenceName="id_cita_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="citaId")
	@Column(name="ID_CITA")
	protected Long idCita;
	
	@Column(name = "CODIGO", nullable = false, unique = true)
	protected String codigo;
	
	@Column(name = "FECHA_CITA", nullable = false)
	protected LocalDateTime dataHora;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
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
