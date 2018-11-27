package es.udc.fi.lbd.monuzz.id.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_PRUEBA")
public class TipoProba {
	
	@Id
	@SequenceGenerator(name="tipopruebaId", sequenceName="id_tipoprueba_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tipopruebaId")
	@Column(name="ID_TIPO_PRUEBA")
	private Long idTipoProba;	
	
	@Column(name = "CODIGO", nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "NOMBRE", nullable = false, unique = true)
	private String nome;
	
	@Column(name = "DESCRIPCION")
	private String descricion;
	
	// Clave surrogada: idTipoProba
	// Clave natural: codigo, nome
	// Atributos obrigatorios: codigo, nome
	// Atributos unicos: codigo, nome
	
		
	protected TipoProba() {}

	public TipoProba(String codigo, String nome, String descricion) {
		this.idTipoProba=null;
		this.codigo = codigo;
		this.nome = nome;
		this.descricion = descricion;
	}


	// GETTERS =============================================================================
	

	public Long getIdTipoProba() {return this.idTipoProba;}

	public String getCodigo() {return this.codigo;}

	public String getNome() {return this.nome;}

	public String getDescricion() {return this.descricion;}

	
	// SETTERS =============================================================================


	public void setIdTipoProba(Long id) {this.idTipoProba = id;}

	public void setCodigo(String num) {this.codigo = num;}

	public void setNome(String nome) {this.nome = nome;}

	public void setDescricion(String descricion) {this.descricion = descricion;	}

	
	// OUTROS =============================================================================
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		TipoProba other = (TipoProba) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoProba [idTipoProba=" + idTipoProba + ", codigo=" + codigo + ", nome=" + nome
				+ ", descricion=" + descricion + "]";
	}


	
}