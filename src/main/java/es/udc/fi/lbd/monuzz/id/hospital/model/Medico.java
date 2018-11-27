package es.udc.fi.lbd.monuzz.id.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MEDICO")
public class Medico {
	
	@Id
	@SequenceGenerator(name="medicoId", sequenceName="id_medico_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="medicoId")
	@Column(name="ID_MEDICO")
	private Long idMedico;	
	
	@Column(name = "NUM_COLEGIADO", nullable = false, unique = true)
	private String numColexiado;
	
	@Column(name = "NOMBRE_APELLIDOS", nullable = false)
	private String nomeCompleto;
	
	@Column(name = "ESPECIALIDAD", nullable = false)
	private String especialidade;
	
	@Column(name = "ACTIVO", nullable = false)
	private Boolean activo;
	
	// Clave surrogada: idMedico
	// Clave natural: numColexiado
	// Atributos obrigatorios: numColexiado, nomeCompleto, especialidade, activo
	// Atributos unicos: numColexiado
	
	
	protected Medico() {}

	public Medico(String numColexiado, String nomeCompleto, String especialidade, Boolean activo) {
		this.idMedico=null;
		this.numColexiado = numColexiado;
		this.nomeCompleto = nomeCompleto;
		this.especialidade = especialidade;
		this.activo = activo;
	}


	// GETTERS =============================================================================
	
	public Long getIdMedico() {return this.idMedico;}

	public String getNumColexiado() {return this.numColexiado;}

	public String getNomeCompleto() {return this.nomeCompleto;}

	public String getEspecialidade() {return this.especialidade;}

	public Boolean getActivo() {return this.activo;}
	
	// SETTERS =============================================================================


	public void setIdMedico(Long id) {this.idMedico = id;}

	public void setNumColexiado(String num) {this.numColexiado = num;}

	public void setNomeCompleto(String nome) {this.nomeCompleto = nome;}

	public void setEspecialidade(String especialidade) {this.especialidade = especialidade;	}

	public void setActivo(Boolean activo) {this.activo = activo;}

	
	
	// OUTROS =============================================================================
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numColexiado == null) ? 0 : numColexiado.hashCode());
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
		Medico other = (Medico) obj;
		if (numColexiado == null) {
			if (other.numColexiado != null)
				return false;
		} else if (!numColexiado.equals(other.numColexiado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medico [idMedico=" + idMedico + ", numColexiado=" + numColexiado + ", nomeCompleto=" + nomeCompleto
				+ ", especialidade=" + especialidade + ", activo=" + activo + "]";
	}


	
}