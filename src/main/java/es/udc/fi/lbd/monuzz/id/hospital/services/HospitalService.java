package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Consulta;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;
import es.udc.fi.lbd.monuzz.id.hospital.model.Proba;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoDoenza;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoProba;


public interface HospitalService {
	
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
	

	// 1) Metodos básicos ============================================================================================================
	
	// MEDICOS ==============================================

	public void altaNovoMedicoBD (Medico meuMedico);
		// Rexistra na BD un novo medico. 
		// De se produciren un problema, propagar excepcion
	public void borradoMedicoBD (Medico meuMedico);
		// Elimina da BD os datos dun medico. 
		// De se produciren un problema, propagar excepcion	
	public void modificacionMedicoBD (Medico meuMedico);
		// Actualiza na BD os datos dun medico. 
		// De se produciren un problema, propagar excepcion

	// -------------------------------------------------------------------------------

	public Medico recuperarMedicoBDPorId(Long id);
		// Carga un medico desde a BD usando o seu id persistente
	public Medico recuperarMedicoBDPorNumColexiado(String numColexiado);
		// Carga un medico desde a BD usando o seu numero de colexiado (único)
	public List<Medico> recuperarTodosMedicosBD();
		// Obten unha listaxe con TODOS os medicos rexistrados ba BD (na orde definida no DAO)
	
	
	// TIPOS DE DOENZAS ======================================
	
	public void altaNovoTipoDoenzaBD (TipoDoenza minhaDoenza);
		// Rexistra na BD unha nova doenza
	public void borradoTipoDoenzaBD (TipoDoenza minhaDoenza);
		// Elimina da BD os datos dunha doenza 
	public void modificacionTipoDoenzaBD (TipoDoenza minhaDoenza);
		// Modifica na BD os datos dunha doenza
	
	// -----------------------------------------------------------------------------
	
	public TipoDoenza recuperarTipoDoenzaBDPorId(Long id);
		//Recupera os datos dunha doenza desde a BD usando o seu id persistente
	public TipoDoenza recuperarTipoDoenzaBDPorCodigo (String codigo);	
		//Recupera os datos dunha doenza desde a BD usando o seu codigo 
	public List<TipoDoenza> recuperarTodosTiposDoenzasBD();
		// Recupera da BD os datos de TODAS as Doenzas
		// (na orde definida no DAO) 	

	
	// TIPOS DE PROBAS =======================================

	public void altaNovoTipoProbaBD(TipoProba minhaProba);
		// Rexistra na BD un novo tipo de proba
	public void borradoTipoProbaBD (TipoProba minhaProba);
		// Elimina da BD os datos dun tipo de proba 
	public void modificacionTipoProbaBD (TipoProba minhaProba);
		// Modifica na BD os datos dun tipo de proba
	
	// -----------------------------------------------------------------------------
	
	public TipoProba recuperarTipoProbaBDPorId(Long id);
		//Recupera os datos dun tipo de proba desde a BD usando o seu id persistente
	public TipoProba recuperarTipoProbaBDPorCodigo (String codigo);	
		//Recupera os datos dun tipo de proba desde a BD usando o seu codigo 
	public List<TipoProba> recuperarTodosTiposProbasBD();
		// Recupera da BD os datos de TODOS os tipos de proba
		// (na orde definida no DAO) 	
	
	
	// CITAS =================================================

	public void altaNovaCitaBD(Cita minhaCita);
		// Rexistra na BD unha nova cita
	public void borradoCitaBD (Cita minhaCita);
		// Elimina da BD os datos dunha cita 
	public void modificacionCitaBD (Cita minhaCita);
		// Modifica na BD os datos dunha cita
	
	// -----------------------------------------------------------------------------
	
	public Cita recuperarCitaBDPorId(Long id);
		//Recupera os datos dunha cita desde a BD usando o seu id persistente
	public Cita recuperarCitaBDPorCodigo (String codigo);	
		//Recupera os datos dunha cita desde a BD usando o seu codigo 
	
	
	
	// 2) Metodos de consulta sobre BD sen asignación de valor =====================================================================================
	
	public List<Consulta> recuperarTodasConsultasMedicoData (Medico meuMedico, LocalDate minhaData); 
		// Recupera TODAS as consultas que debe atender un médico nunha data, recuperadas da BD (a mais temperá primeiro) 

	public List<Proba> recuperarTodasProbasData (LocalDate minhaData); 
		// Recupera TODAS as probas que deben atenderse nunha data, recuperadas da BD (a mais temperá primeiro) 

	// -----------------------------------------------------------------------------

	public List<Consulta> recuperarTodasConsultasPaciente(Paciente meuPaciente); 
		// Recupera TODAS as consultas rexistradas na BD dun paciente, ordenadas por data (a mais recente primeiro) 

	public List<Proba> recuperarTodasProbasPaciente(Paciente meuPaciente);
		// Recupera TODAS as citas rexistradas na BD dun paciente, ordenadas por data (a mais recente primeiro) 


	
	// 3) Metodos de consulta sobre BD CON ASIGNACIÓN DE VALOR  =====================================================================================

	/* Todos estos métodos devolven un valor que debe ser asignado a unha propiedade de clase que representa a unha asociación
	 * 
	 * As propiedades involucradas son:
	 * 
	 * 	    Paciente.citas
	 * 		Cita.paciente
	 * 		Consulta.medico
	 * 		Consulta.doenzas
	 * 		Proba.tipoProba
	 * 
	 *  Cada método debe:
	 *  	-Invocar o DAO para un obxecto subministrado como argumento, e recuperar o valor devolto
	 *  	-Actualizar a propiedade do obxecto co valor recuperado.
	 *  
	 */
	
	public SortedSet<Cita> recuperarTodasCitasPaciente(Paciente meuPaciente);
		// Recupera da BD TODAS as citas dun paciente, na orde definida no DAO 	
		// Actualiza o valor de meuPaciente.citas

	public Paciente recuperarPacienteCita(Cita minhaCita);
		// Recupera da BD o paciente correspondente a unha cita
		// Actualiza o valor de minhaCita.paciente
	
	public Medico recuperarMedicoConsulta(Consulta minhaConsulta);
		// Recupera da BD o medico correspondente a unha consulta
		// Actualiza o valor de minhaConsulta.medico
	
	public Set<TipoDoenza> recuperarDoenzasConsulta (Consulta minhaConsulta);
		// Recupera da BD TODAS as doenzas diagnosticadas nuha consulta, na orde definida no DAO
		// Actualiza o valor de minhaConsulta.doenzas

	public TipoProba recuperarTipoProba (Proba minhaProba);
		// Recupera da BD o tipo dunha proba determinada
		// Actualiza o valor de minhaProba.tipoProba

	
}
