/**
 * 
 */
package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoDoenza;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoProba;

@Repository
public class HospitalDAOImpl implements HospitalDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long create(Medico meuMedico) {
		if (meuMedico.getIdMedico()!= null) {
			throw new RuntimeException("Este medico ya existe:" + meuMedico.toString());
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(meuMedico              );
		sessionFactory.getCurrentSession().flush(); 
		return id;
	}

	@Override
	public void update(Medico meuMedico) {
		if (meuMedico.getIdMedico() == null) {
			throw new RuntimeException("Este medico no existe.");
		}
		sessionFactory.getCurrentSession().update(meuMedico);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public void remove(Medico meuMedico) {
		if (meuMedico.getIdMedico() == null) {
			throw new RuntimeException("Este paciente no existe.");
		}
		sessionFactory.getCurrentSession().delete(meuMedico);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public Medico findMedicoById(Long id) {
		Medico medico = (Medico) sessionFactory.getCurrentSession().get(Medico.class, id);
		return medico;
	}

	@Override
	public Medico findMedicoByNumColexiado(String numColexiado) {
		Medico medico = (Medico) sessionFactory.getCurrentSession().createQuery("from Medico where numColexiado = :numColexiado").setParameter("numColexiado", numColexiado).uniqueResult();
		return medico;
	}

	@Override
	public List<Medico> findAllMedicos() {
		List<Medico> medicos = (List<Medico>) sessionFactory.getCurrentSession().createQuery("from Medico order by especialidade desc, numColexiado").list();
		return medicos;
	}

	@Override
	public Long create(TipoDoenza minhaDoenza) {
		if (minhaDoenza.getIdTipoDoenza()!= null) {
			throw new RuntimeException("Esta dolencia ya existe:" + minhaDoenza.toString());
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(minhaDoenza              );
		sessionFactory.getCurrentSession().flush(); 
		return id;
	}

	@Override
	public void update(TipoDoenza minhaDoenza) {
		if (minhaDoenza.getIdTipoDoenza() == null) {
			throw new RuntimeException("Esta dolencia no existe.");
		}
		sessionFactory.getCurrentSession().update(minhaDoenza);
		sessionFactory.getCurrentSession().flush();		
	}

	@Override
	public void remove(TipoDoenza minhaDoenza) {
		if (minhaDoenza.getIdTipoDoenza() == null) {
			throw new RuntimeException("Esta dolencia no existe.");
		}
		sessionFactory.getCurrentSession().delete(minhaDoenza);
		sessionFactory.getCurrentSession().flush();		
	}

	@Override
	public TipoDoenza findTipoDoenzaById(Long id) {
		TipoDoenza doenza = (TipoDoenza) sessionFactory.getCurrentSession().get(TipoDoenza.class, id);
		return doenza;
	}

	@Override
	public TipoDoenza findTipoDoenzaByCodigo(String codigo) {
		TipoDoenza doenza = (TipoDoenza) sessionFactory.getCurrentSession().createQuery("from TipoDoenza where codigo = :codigo").setParameter("codigo", codigo).uniqueResult();
		return doenza;
	}

	@Override
	public List<TipoDoenza> findAllTiposDoenzas() {
		List<TipoDoenza> doenzas = (List<TipoDoenza>) sessionFactory.getCurrentSession().createQuery("from TipoDoenza order by nombre").list(); 
		return doenzas;
	}

	@Override
	public Long create(TipoProba minhaProba) {
		if (minhaProba.getIdTipoProba()!= null) {
			throw new RuntimeException("Esta prueba ya existe:" + minhaProba.toString());
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(minhaProba              );
		sessionFactory.getCurrentSession().flush(); 
		return id;
	}

	@Override
	public void update(TipoProba minhaProba) {
		if (minhaProba.getIdTipoProba() == null) {
			throw new RuntimeException("Esta prueba no existe.");
		}
		sessionFactory.getCurrentSession().update(minhaProba);
		sessionFactory.getCurrentSession().flush();			
	}

	@Override
	public void remove(TipoProba minhaProba) {
		if (minhaProba.getIdTipoProba() == null) {
			throw new RuntimeException("Esta prueba no existe.");
		}
		sessionFactory.getCurrentSession().delete(minhaProba);
		sessionFactory.getCurrentSession().flush();		
	}

	@Override
	public TipoProba findTipoProbaById(Long id) {
		TipoProba proba = (TipoProba) sessionFactory.getCurrentSession().get(TipoProba.class, id);
		return proba;
	}

	@Override
	public TipoProba findTipoProbaByCodigo(String codigo) {
		TipoProba proba = (TipoProba) sessionFactory.getCurrentSession().createQuery("from TipoProba where codigo = :codigo").setParameter("codigo", codigo).uniqueResult(); 
		return proba;
	}

	@Override
	public List<TipoProba> findAllTiposProbas() {
		List<TipoProba> probas = (List<TipoProba>) sessionFactory.getCurrentSession().createQuery("from TipoProba order by nome").list();
		return probas;
	}

}
