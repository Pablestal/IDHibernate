package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.lbd.monuzz.id.hospital.daos.CitaDAO;
import es.udc.fi.lbd.monuzz.id.hospital.daos.HospitalDAO;
import es.udc.fi.lbd.monuzz.id.hospital.daos.PacienteDAO;
import es.udc.fi.lbd.monuzz.id.hospital.model.*;

public class HospitalServiceImpl implements HospitalService {
	
	static Logger log = Logger.getLogger("hospital");
	
	@Autowired
	private HospitalDAO hospitalDAO;
	
	@Autowired
	private CitaDAO citaDAO;
	
	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovoMedicoBD(Medico meuMedico) {
		try {
			hospitalDAO.create(meuMedico);
			log.info("Guardados los datos de: "+ meuMedico.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al guardar los datos de: " + meuMedico.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoMedicoBD(Medico meuMedico) {
		try {
			hospitalDAO.remove(meuMedico);
			log.info("Borrados los datos de: "+ meuMedico.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al borrar los datos de: " + meuMedico.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionMedicoBD(Medico meuMedico) {
		try {
			hospitalDAO.update(meuMedico);
			log.info("Modificados los datos de: "+ meuMedico.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al modificar los datos de: " + meuMedico.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public Medico recuperarMedicoBDPorId(Long id) {
		Medico medico;
		try {
			medico = hospitalDAO.findMedicoById(id);
			log.info("Medico encontrado: "+ medico.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar el médico");
			throw e;
		}
		return medico;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public Medico recuperarMedicoBDPorNumColexiado(String numColexiado) {
		Medico medico;
		try {
			medico = hospitalDAO.findMedicoByNumColexiado(numColexiado);
			log.info("Medico encontrado: "+ medico.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar el médico");
			throw e;
		}
		return medico;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public List<Medico> recuperarTodosMedicosBD() {
		List<Medico> medicos;
		try {
			medicos = hospitalDAO.findAllMedicos();
			log.info("Médicos encontrados con éxito.");
		}
		catch (DataAccessException e) {
			log.error("No se han podido encontrar los médicos.");
			throw e;
		}
		return medicos;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovoTipoDoenzaBD(TipoDoenza minhaDoenza) {
		try {
			hospitalDAO.create(minhaDoenza);
			log.info("Guardado nuevo tipo de dolencia: "+ minhaDoenza.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al guardar los datos de: " + minhaDoenza.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoTipoDoenzaBD(TipoDoenza minhaDoenza) {
		try {
			hospitalDAO.remove(minhaDoenza);
			log.info("Borrado el tipo de dolencia: "+ minhaDoenza.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al borrar los datos de: " + minhaDoenza.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionTipoDoenzaBD(TipoDoenza minhaDoenza) {
		try {
			hospitalDAO.update(minhaDoenza);
			log.info("Modificados los datos del tipo de dolencia: "+ minhaDoenza.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al modificar los datos de: " + minhaDoenza.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public TipoDoenza recuperarTipoDoenzaBDPorId(Long id) {
		TipoDoenza doenza;
		try {
			doenza = hospitalDAO.findTipoDoenzaById(id);
			log.info("Tipo de dolencia encontrado: "+ doenza.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar el tipo de dolencia");
			throw e;
		}
		return doenza;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public TipoDoenza recuperarTipoDoenzaBDPorCodigo(String codigo) {
		TipoDoenza doenza;
		try {
			doenza = hospitalDAO.findTipoDoenzaByCodigo(codigo);
			log.info("Tipo de dolencia encontrado: "+ doenza.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar el tipo de dolencia");
			throw e;
		}
		return doenza;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public List<TipoDoenza> recuperarTodosTiposDoenzasBD() {
		List<TipoDoenza> doenzas;
		try {
			doenzas = hospitalDAO.findAllTiposDoenzas();
			log.info("Tipos de dolencias encontrados con éxito.");
		}
		catch (DataAccessException e) {
			log.error("No se han podido encontrar los tipos de dolencia.");
			throw e;
		}
		return doenzas;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovoTipoProbaBD(TipoProba minhaProba) {
		try {
			hospitalDAO.create(minhaProba);
			log.info("Guardado nuevo tipo de prueba: "+ minhaProba.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al guardar los datos de: " + minhaProba.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoTipoProbaBD(TipoProba minhaProba) {
		try {
			hospitalDAO.update(minhaProba);
			log.info("Modificado el tipo de prueba: "+ minhaProba.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al modificar los datos de: " + minhaProba.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionTipoProbaBD(TipoProba minhaProba) {
		try {
			hospitalDAO.remove(minhaProba);
			log.info("Borrado el tipo de prueba: "+ minhaProba.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al guardar los datos de: " + minhaProba.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public TipoProba recuperarTipoProbaBDPorId(Long id) {
		TipoProba proba;
		try {
			proba = hospitalDAO.findTipoProbaById(id);
			log.info("Tipo de prueba encontrado: "+ proba.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar el tipo de prueba.");
			throw e;
		}
		return proba;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public TipoProba recuperarTipoProbaBDPorCodigo(String codigo) {
		TipoProba proba;
		try {
			proba = hospitalDAO.findTipoProbaByCodigo(codigo);
			log.info("Tipo de prueba encontrado: "+ proba.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar el tipo de prueba.");
			throw e;
		}
		return proba;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public List<TipoProba> recuperarTodosTiposProbasBD() {
		List<TipoProba> probas;
		try {
			probas = hospitalDAO.findAllTiposProbas();
			log.info("Tipos de prueba encontrados con éxito.");
		}
		catch (DataAccessException e) {
			log.error("No se han podido encontrar los tipos de prueba.");
			throw e;
		}
		return probas;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovaCitaBD(Cita minhaCita) {
		try {
			citaDAO.create(minhaCita);
			log.info("Guardada nueva cita: "+ minhaCita.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al guardar los datos de: " + minhaCita.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoCitaBD(Cita minhaCita) {
		try {
			citaDAO.remove(minhaCita);
			log.info("Borrados los datos de la cita: "+ minhaCita.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al borrar los datos de: " + minhaCita.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionCitaBD(Cita minhaCita) {
		try {
			citaDAO.update(minhaCita);
			log.info("Modificados los datos de la cita: "+ minhaCita.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al modificar los datos de: " + minhaCita.toString());
			throw e;
		}
		
	}

	@Override
	public Cita recuperarCitaBDPorId(Long id) {
		Cita cita;
		try {
			cita = citaDAO.findCitaById(id);
			log.info("Cita encontrada: "+ cita.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar la cita.");
			throw e;
		}
		return cita;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public Cita recuperarCitaBDPorCodigo(String codigo) {
		Cita cita;
		try {
			cita = citaDAO.findCitaByCodigo(codigo);
			log.info("Cita encontrada: "+ cita.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar la cita.");
			throw e;
		}
		return cita;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public List<Consulta> recuperarTodasConsultasMedicoData(Medico meuMedico, LocalDate minhaData) {
		List<Consulta> consultas;
		try {
			consultas = citaDAO.findAllConsultasMedicoData(meuMedico, minhaData);
			log.info("Consultas encontradas con éxito.");
		}
		catch (DataAccessException e) {
			log.error("No se han podido encontrar las consultas.");
			throw e;
		}
		return consultas;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public List<Proba> recuperarTodasProbasData(LocalDate minhaData) {
		List<Proba> probas;
		try {
			probas = citaDAO.findAllProbasData(minhaData);
			log.info("Pruebas encontradas con éxito.");
		}
		catch (DataAccessException e){
			log.error("No se han podido encontrar las pruebas.");
			throw e;
		}
		return probas;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public List<Consulta> recuperarTodasConsultasPaciente(Paciente meuPaciente) {
		List<Consulta> consultas;
		try {
			consultas = citaDAO.findAllConsultasPaciente(meuPaciente);
			log.info("Consultas encontradas con éxito.");
		}
		catch (DataAccessException e) {
			log.error("No se han podido encontrar las consultas.");
			throw e;
		}
		return consultas;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public List<Proba> recuperarTodasProbasPaciente(Paciente meuPaciente) {
		List<Proba> probas;
		try {
			probas = citaDAO.findAllProbasPaciente(meuPaciente);
			log.info("Pruebas encontradas con éxito.");
		}
		catch (DataAccessException e){
			log.error("No se han podido encontrar las pruebas.");
			throw e;
		}
		return probas;
	}

	@Override
	public SortedSet<Cita> recuperarTodasCitasPaciente(Paciente meuPaciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paciente recuperarPacienteCita(Cita minhaCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medico recuperarMedicoConsulta(Consulta minhaConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TipoDoenza> recuperarDoenzasConsulta(Consulta minhaConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoProba recuperarTipoProba(Proba minhaProba) {
		// TODO Auto-generated method stub
		return null;
	}


}
