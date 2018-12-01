package es.udc.fi.lbd.monuzz.id.hospital.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fi.lbd.monuzz.id.hospital.converters.LocalDateAttributeConverter;

@Entity
@Table(name = "PACIENTE")
public class Paciente {
	
	@Id
	@SequenceGenerator(name="pacienteId", sequenceName="id_paciente_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pacienteId")
	@Column(name="ID_PACIENTE")
	private Long idPaciente;	
	
	@Column(name = "NUM_PACIENTES", nullable = false, unique = true)
	private String numPaciente;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nomeCompleto;
	
	@Column(name = "CODIGO", nullable = true)
	private LocalDate dataNacemento;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PACIENTE")
	//@CollectionTable(name="paciente_citas", joinColumns=@JoinColumn(name="ID_PACIENTE"))
	@OrderBy("dataHora")
	private SortedSet<Cita> citas = new TreeSet<Cita>();

	// Clave surrogada: idPaciente
	// Clave natural: numPaciente
	// Atributos obrigatorios: numPaciente, nomeCompleto
	// Atributos unicos: numPaciente
	
	// OUTRAS RESTRICCIONS (A DEFINIR NA BD) 
	// 		A listaxe de citas ESTA ORDENADA por data (a mais RECENTE primeiro)

		
	protected Paciente() {}

	public Paciente(String numPaciente, String nomeCompleto, LocalDate dataNacemento) {
		this.idPaciente=null;
		this.numPaciente = numPaciente;
		this.nomeCompleto = nomeCompleto;
		this.dataNacemento = dataNacemento;
	}


	// GETTERS =============================================================================
	
	public Long getIdPaciente() {return this.idPaciente;}

	public String getNumPaciente() {return this.numPaciente;}

	public String getNomeCompleto() {return this.nomeCompleto;}

	@Convert(converter = LocalDateAttributeConverter.class)
	public LocalDate getDataNacemento() {return this.dataNacemento;}

	public SortedSet<Cita> getCitas() {return this.citas;}
	
	// SETTERS =============================================================================

	public void setIdPaciente(Long id) {this.idPaciente = id;}

	public void setNumPaciente(String num) {this.numPaciente = num;}

	public void setNomeCompleto(String nome) {this.nomeCompleto = nome;}

	public void setDataNacemento(LocalDate data) {this.dataNacemento = data;}

	public void setCitas(SortedSet<Cita> citas) {this.citas = citas;}

	
	// MÉTODOS DE CONVENIENCIA ==============================================================


	public Boolean dataOcupada (LocalDateTime data) {
		Iterator<Cita> itr = this.getCitas().iterator();
		while (itr.hasNext()) {
			if (data.equals(itr.next().getDataHora()))
				return true;
		}
		return false;
	}
	
	// OUTROS =============================================================================
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numPaciente == null) ? 0 : numPaciente.hashCode());
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
		Paciente other = (Paciente) obj;
		if (numPaciente == null) {
			if (other.numPaciente != null)
				return false;
		} else if (!numPaciente.equals(other.numPaciente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", numPaciente=" + numPaciente + ", nomeCompleto=" + nomeCompleto
				+ ", dataNacemento=" + dataNacemento + "]";
	}


	
}