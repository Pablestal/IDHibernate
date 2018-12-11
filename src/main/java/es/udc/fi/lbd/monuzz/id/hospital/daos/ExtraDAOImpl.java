package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

@Repository
public class ExtraDAOImpl implements ExtraDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public Integer findCountConsultasMedico(Medico meuMedico) {
		Long l = (Long) sessionFactory.getCurrentSession().createQuery
		("select count(a) from Consulta a "
		+ "where a.medico = :meuMedico").setParameter("meuMedico", meuMedico).uniqueResult();
		return l.intValue() ;
	}


	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public Medico findLastMedic() {
		Medico medico = (Medico) sessionFactory.getCurrentSession().createQuery
				("select m.medico from Consulta m "
						+ "where m = (select max(a) from Consulta a)").uniqueResult();
		return medico;
	}


	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public List<Paciente> findPacientesWithoutCitas() {
		List<Paciente> lista = (List<Paciente>) sessionFactory.getCurrentSession().createQuery(
				"select p from Paciente p "
				+ "left join p.citas c where c is null "
				+ "order by p.idPaciente").list();
		return lista;
	}

}
