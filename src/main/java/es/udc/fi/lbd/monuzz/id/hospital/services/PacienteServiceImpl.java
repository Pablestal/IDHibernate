package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.lbd.monuzz.id.hospital.daos.PacienteDAO;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

@Service
public class PacienteServiceImpl implements PacienteService {

	static Logger log = Logger.getLogger("hospital");
	
	@Autowired
	private PacienteDAO pacienteDAO;
	
	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovoPacienteBD(Paciente meuPaciente) {
		try {
			pacienteDAO.create(meuPaciente);
			log.info("Guardados los datos de: "+ meuPaciente.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al guardar los datos de: " + meuPaciente.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoPacienteBD(Paciente meuPaciente) {
		try {
			pacienteDAO.remove(meuPaciente);
			log.info("Borrados los datos de: "+ meuPaciente.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al borrar los datos de: " + meuPaciente.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionPacienteBD(Paciente meuPaciente) {
		try {
			pacienteDAO.update(meuPaciente);
			log.info("Modificados los datos de: "+ meuPaciente.toString());
		}
		catch (DataAccessException e) {
			log.error("Error al modificar los datos de: " + meuPaciente.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public Paciente recuperarPacienteBDPorId(Long id) {
		Paciente paciente;
		try {
			paciente = pacienteDAO.findPacienteById(id);
			if (paciente != null )log.info("Paciente econtrado: " + paciente.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar al paciente.");
			throw e;
		}
		return paciente;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public Paciente recuperarPacienteBDPorNumPaciente(String num) {
		Paciente paciente;
		try {
			paciente = pacienteDAO.findPacienteByNum(num);
			if (paciente != null) log.info("Paciente econtrado: " + paciente.toString());
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar al paciente.");
			throw e;
		}
		return paciente;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=true)
	public List<Paciente> recuperarTodosPacientesBD() {
		List<Paciente> pacientes;
		try {
			pacientes = pacienteDAO.findAllPacientes();
			log.info("Pacientes recuperados con exito.");
		}
		catch (DataAccessException e) {
			log.error("No se ha podido encontrar a los pacientes.");
			throw e;
		}
		return pacientes;
	}



}
