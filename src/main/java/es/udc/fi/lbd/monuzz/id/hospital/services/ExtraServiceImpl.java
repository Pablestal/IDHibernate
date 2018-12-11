package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import es.udc.fi.lbd.monuzz.id.hospital.daos.ExtraDAO;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

@Service
public class ExtraServiceImpl implements ExtraService {

	static Logger log = Logger.getLogger("hospital");
	
	@Autowired
	ExtraDAO extraDAO;

	@Override
	public Integer findCountConsultasMedico(Medico meuMedico) {
		Integer l;
		try {
			l = extraDAO.findCountConsultasMedico(meuMedico);
			log.info("Encontrados : "+ l.toString() + " consultas del medico: " + meuMedico.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al encontrar datos de : " + meuMedico.toString());
			throw e;
		}
		return l;
	}

	@Override
	public Medico findLastMedic() {
		Medico medico;
		try {
			medico = extraDAO.findLastMedic();
			if (medico !=null ) log.info("El mejor medico es : " + medico.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al encontrar medicos expertos");
			throw e;
		}
		return medico;
	}

	@Override
	public List<Paciente> findPacientesWithoutCitas() {
		List<Paciente> lista;
		try {
			lista = extraDAO.findPacientesWithoutCitas();
			log.info("Encontrados estos pacientes: " + lista.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al encontrar medicos expertos");
			throw e;
		}
		return lista;
	}
	
	
	
	
}
