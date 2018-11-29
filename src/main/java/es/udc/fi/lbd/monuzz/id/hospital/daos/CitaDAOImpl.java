/**
 * 
 */
package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Consulta;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;
import es.udc.fi.lbd.monuzz.id.hospital.model.Proba;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoDoenza;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoProba;

@Repository
public class CitaDAOImpl implements CitaDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long create(Cita minhaCita) {
		if (minhaCita.getIdCita()!= null) {
			throw new RuntimeException("Esta cita ya existe:" + minhaCita.toString());
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(minhaCita);
		sessionFactory.getCurrentSession().flush(); 
		return id;
	}

	@Override
	public void update(Cita minhaCita) {
		if (minhaCita.getIdCita() == null) {
			throw new RuntimeException("Esta cita no existe.");
		}
		sessionFactory.getCurrentSession().update(minhaCita);
		sessionFactory.getCurrentSession().flush();		
	}

	@Override
	public void remove(Cita minhaCita) {
		if (minhaCita.getIdCita() == null) {
			throw new RuntimeException("Esta cita no existe.");
		}
		sessionFactory.getCurrentSession().delete(minhaCita);
		sessionFactory.getCurrentSession().flush();		
	}

	@Override
	public Cita findCitaById(Long id) {
		Cita cita = (Cita) sessionFactory.getCurrentSession().get(Cita.class, id);
		return cita;
	}

	@Override
	public Cita findCitaByCodigo(String codigoCita) {
		Cita cita = (Cita) sessionFactory.getCurrentSession().get(Cita.class, codigoCita);
		return cita;
	}

	@Override
	public List<Consulta> findAllConsultasMedicoData(Medico meuMedico, LocalDate minhaData) {
		List<Consulta> consultas = (List<Consulta>) sessionFactory.getCurrentSession().createQuery("").list();
		return consultas;
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
