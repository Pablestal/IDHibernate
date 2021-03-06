/**
 * 
 */
package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
		Cita cita = (Cita) sessionFactory.getCurrentSession().createQuery("from Cita where codigo = :codigoCita").setParameter("codigoCita", codigoCita).uniqueResult();
		return cita;
	}

	@Override
	public List<Consulta> findAllConsultasMedicoData(Medico meuMedico, LocalDate minhaData) {
		LocalDateTime minhaData1 = minhaData.atStartOfDay();
		LocalDateTime minhaData2 = minhaData.atTime(23,59);
		
		List<Consulta> consultas = (List<Consulta>) sessionFactory.getCurrentSession().createQuery
		("from Consulta a "
		+ "where a.medico = :meuMedico  AND (a.dataHora >= :minhaData1 AND a.dataHora <= :minhaData2)  "
		+ "order by a.dataHora").setParameter("meuMedico", meuMedico).setParameter("minhaData1", minhaData1).setParameter("minhaData2", minhaData2).list();
		return consultas;
	}

	@Override
	public List<Proba> findAllProbasData(LocalDate minhaData) {
		LocalDateTime minhaData1 = minhaData.atStartOfDay();
		LocalDateTime minhaData2 = minhaData.atTime(23,59);
		
		List<Proba> probas = (List<Proba>) sessionFactory.getCurrentSession().createQuery
		("from Proba p "
		+ "where (p.dataHora >= :minhaData1 AND p.dataHora <= :minhaData2) "
		+ "order by p.dataHora").setParameter("minhaData1", minhaData1).setParameter("minhaData2", minhaData2).list();
		return probas;
	}

	@Override
	public List<Consulta> findAllConsultasPaciente(Paciente meuPaciente) {
		List<Consulta> consultas = (List<Consulta>) sessionFactory.getCurrentSession().createQuery
		("from Consulta a "
		+ "where a.paciente = :meuPaciente "
		+ "order by a.dataHora desc").setParameter("meuPaciente", meuPaciente).list();
		return consultas;
	}

	@Override
	public List<Proba> findAllProbasPaciente(Paciente meuPaciente) {
		List<Proba> probas = (List<Proba>) sessionFactory.getCurrentSession().createQuery
		("from Proba a "
		+ "where a.paciente = :meuPaciente "
		+ "order by a.dataHora desc").setParameter("meuPaciente", meuPaciente).list();
		return probas;
	}

	@Override
	public SortedSet<Cita> findAllCitasPaciente(Paciente meuPaciente) {
		//problema de conversion 
		List<Cita> citas = (List<Cita>) sessionFactory.getCurrentSession().createQuery
		("from Cita a "
		+ "where a.paciente = :meuPaciente "
		+ "order by a.dataHora desc").setParameter("meuPaciente", meuPaciente).list();
		TreeSet<Cita> cit = new TreeSet<Cita>();
		cit.addAll(citas);
		meuPaciente.setCitas(cit);
		return cit;
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
	public Set<TipoDoenza> findAllDoenzasConsulta(Consulta minhaConsulta) {
		return minhaConsulta.getDoenzas();
		
	}

	@Override
	public TipoProba findTipoProba(Proba minhaCita) {
		return minhaCita.getTipoProba();
	}

}
