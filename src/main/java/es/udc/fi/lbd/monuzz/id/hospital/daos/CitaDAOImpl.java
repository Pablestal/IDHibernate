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
		List<Consulta> consultas = (List<Consulta>) sessionFactory.getCurrentSession().createQuery
		("from consulta a join cita b "
		+ "on a.id_cita = b.id_cita "
		+ "where a.medico = meuMedico and b.dataHora = minhaData "
		+ "order by b.dataHora").setParameter("meuMedico", meuMedico).setParameter("minhaData", minhaData).list();
		return consultas;
	}

	@Override
	public List<Proba> findAllProbasData(LocalDate minhaData) {
		List<Proba> probas = (List<Proba>) sessionFactory.getCurrentSession().createQuery
		("from prueba a join cita b "
		+ "on a.id_cita = b.id_cita "
		+ "where b.dataHota = minhaData "
		+ "order by b.dataHota").setParameter("minhaData", minhaData).list();
		return probas;
	}

	@Override
	public List<Consulta> findAllConsultasPaciente(Paciente meuPaciente) {
		List<Consulta> consultas = (List<Consulta>) sessionFactory.getCurrentSession().createQuery
		("from cita a join consulta b"
		+ "on a.id_cita = b.id_cita"
		+ "where a.id_paciente = meuPaciente"
		+ "orderby a.dataHora desc").setParameter("meuPaciente", meuPaciente).list();
		return consultas;
	}

	@Override
	public List<Proba> findAllProbasPaciente(Paciente meuPaciente) {
		List<Proba> probas = (List<Proba>) sessionFactory.getCurrentSession().createQuery
		("from cita a join prueba b"
		+ "on a.id_cita = b.id_cita"
		+ "where a.id_paciente = meuPaciente"
		+ "order by a.dataHora desc").setParameter("meuPaciente", meuPaciente).list();
		return probas;
	}

	@Override
	public SortedSet<Cita> findAllCitasPaciente(Paciente meuPaciente) {
		SortedSet<Cita> citas = (SortedSet<Cita>) sessionFactory.getCurrentSession().createQuery
		("from citas "
		+ "where id_paciente = meuPaciente"
		+ "order by dataHora desc").setParameter("meuPaciente", meuPaciente).list();
		return citas;
	}

	@Override
	public Paciente findPacienteCita(Cita minhaCita) {
		return minhaCita.getPaciente();
	}

	@Override
	public Medico findMedicoConsulta(Consulta minhaConsulta) {
		return minhaConsulta.getMedico();
	}

	@Override
	public Set<TipoDoenza> findAllDoenzasConsulta(Consulta minhaCita) {
		Set<TipoDoenza> doenzas = (Set<TipoDoenza>) sessionFactory.getCurrentSession().createQuery
		("from consulta_dolencias"
		+ "where consulta_id = minhaCita").setParameter("minhaCita", minhaCita).list();
		return doenzas;
	}

	@Override
	public TipoProba findTipoProba(Proba minhaCita) {
		return minhaCita.getTipoProba();
	}

}
