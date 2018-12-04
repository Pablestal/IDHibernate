package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

@Repository
public class PacienteDAOHibImpl implements PacienteDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long create(Paciente meuPaciente) {
		if (meuPaciente.getIdPaciente()!= null) {
			throw new RuntimeException("Este paciente ya existe:" + meuPaciente.toString());
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(meuPaciente);
		sessionFactory.getCurrentSession().flush(); 
		return id;
	}

	@Override
	public void update(Paciente meuPaciente) {
		if (meuPaciente.getIdPaciente() == null) {
			throw new RuntimeException("Este paciente no existe.");
		}
		sessionFactory.getCurrentSession().update(meuPaciente);
		sessionFactory.getCurrentSession().flush(); 
	}

	@Override
	public void remove(Paciente meuPaciente) {
		if (meuPaciente.getIdPaciente() == null) {
			throw new RuntimeException("Este paciente no existe.");
		}
		sessionFactory.getCurrentSession().delete(meuPaciente);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public Paciente findPacienteById(Long id) {
		Paciente paciente = (Paciente) sessionFactory.getCurrentSession().get(Paciente.class, id);
		return paciente;
	}

	@Override
	public Paciente findPacienteByNum(String numPaciente) {
		Paciente paciente = (Paciente) sessionFactory.getCurrentSession().createQuery("from Paciente where numPaciente = :numPaciente").setParameter("numPaciente", numPaciente).uniqueResult();
		return paciente;
	}

	@Override
	public List<Paciente> findAllPacientes() {
		List<Paciente> pacientes = (List<Paciente>) sessionFactory.getCurrentSession().createQuery("from Paciente p order by p.numPaciente desc").list();
		return pacientes;
	}

}
