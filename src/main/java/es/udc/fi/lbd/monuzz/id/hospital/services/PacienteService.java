package es.udc.fi.lbd.monuzz.id.hospital.services;
import java.util.List;

import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

public interface PacienteService {
		
	/* NOTA: Todos os métodos DEBEN: 
	 * 		Se exito: 
	 * 			-Rexistrar mensaxe informativa no log
	 * 		Se error ou excepción:
	 * 			-Capturar excepción
	 * 			-Rexistrar mensaxe de erro no log
	 * 			-Propagar excepción
	 * 
	 * Todos os servizos deben utilizar exclusivamente métodos dos DAOS para acceder á BD
	 * 
	 * Cada método se corresponde con UNHA TRANSACCIÓN
	 * 
	 * Para cada método abriremos e pecharemos UNHA SESION
	 * 
	 */
	
	void altaNovoPacienteBD (Paciente meuPaciente);
		// Rexistra un novo paciente na BD. 
	void borradoPacienteBD (Paciente meuPaciente);
		// Elimina un paciente da BD. 		
		// Nota: Ao eliminar un paciente, todas as súas citas TAMEN SE BORRAN
	void modificacionPacienteBD (Paciente meuPaciente);
		// Actualiza os datos dun paciente rexistrado na BD.

	// -------------------------------------------------------------------------------

	Paciente recuperarPacienteBDPorId(Long id);
		// Carga a un paciente desde a BD usando o seu id persistente
	Paciente recuperarPacienteBDPorNumPaciente(String num);
		// Carga a un paciente desde a BD usando o seu numero de paciente (único)

	// -------------------------------------------------------------------------------

	List<Paciente> recuperarTodosPacientesBD();
		//Recupera a todos os pacientes rexistrados na BD, na orde definida no DAO.
	
	
}
