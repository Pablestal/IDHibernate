/**
 * 
 */
package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Consulta;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;
import es.udc.fi.lbd.monuzz.id.hospital.model.Proba;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoDoenza;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoProba;


public class CitaDAOImpl implements CitaDAO {

	@Override
	public Long create(Cita minhaCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cita minhaCita) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Cita minhaCita) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cita findCitaById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cita findCitaByCodigo(String codigoCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> findAllConsultasMedicoData(Medico meuMedico, LocalDate minhaData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proba> findAllProbasData(LocalDate minhaData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> findAllConsultasPaciente(Paciente meuPaciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proba> findAllProbasPaciente(Paciente meuPaciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Cita> findAllCitasPaciente(Paciente meuPaciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paciente findPacienteCita(Cita minhaCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medico findMedicoConsulta(Consulta minhaConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TipoDoenza> findAllDoenzasConsulta(Consulta minhaCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoProba findTipoProba(Proba minhaCita) {
		// TODO Auto-generated method stub
		return null;
	}

}
