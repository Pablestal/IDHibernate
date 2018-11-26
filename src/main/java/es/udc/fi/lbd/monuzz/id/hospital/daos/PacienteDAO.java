package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

public interface PacienteDAO {

	// Métodos de modificación
    // Usar Session.flush() para permitir que Spring detecte violacion de restriccións na BD

	public Long create(Paciente meuPaciente);
		// Rexistra un novo paciente na BD
		// Xenerar RuntimeException se o paciente xa é persistente
	public void update (Paciente meuPaciente);
		// Modifica os datos dun paciente na BD
		// Xenerar RuntimeException se o paciente non é persistente ainda	
	public void remove (Paciente meuPaciente);
		// Elimina os datos dun paciente da BD
		// Xenerar RuntimeException se o paciente non é persistente ainda

	// -----------------------------------------------------------------------------

	public Paciente findPacienteById(Long id);
		// Recupera os datos dun paciente desde a BD usando o seu id persistente
	public Paciente findPacienteByNum (String numPaciente);	
		// Recupera os datos dun paciente desde a BD usando o seu núnmero de paciente 

	// -----------------------------------------------------------------------------

	public List<Paciente> findAllPacientes();
		// Recupera TODOS os pacientes rexistrados na BD, ordenados por numero de paciente (Z-A) 
}
