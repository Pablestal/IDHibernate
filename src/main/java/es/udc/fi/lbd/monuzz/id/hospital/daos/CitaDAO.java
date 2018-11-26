package es.udc.fi.lbd.monuzz.id.hospital.daos;

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

public interface CitaDAO {
	
	// Métodos de modificación
    // Usar Session.flush() para permitir que Spring detecte violacion de restriccións na BD
		
	public Long create(Cita minhaCita);
		// Rexistra unha nova cita na BD
		// Xenerar RuntimeException se a cita xa é persistente
	public void update (Cita minhaCita);
		// Modifica os datos dunha cita na BD
		// Xenerar RuntimeException se a cita non é persistente ainda	
	public void remove (Cita minhaCita);
		// Elimina os datos dunha cita da BD
		// Xenerar RuntimeException se a cita non é persistente ainda

	// -----------------------------------------------------------------------------

	public Cita findCitaById(Long id);
		// Recupera os datos dunha cita desde a BD usando o seu id persistente
	public Cita findCitaByCodigo(String codigoCita);	
		// Recupera os datos dunha cita desde a BD usando o seu codigo 

	// -----------------------------------------------------------------------------


	public List<Consulta> findAllConsultasMedicoData (Medico meuMedico, LocalDate minhaData); 
		// Recupera TODAS as consultas que debe atender un médico nunha data, recuperadas da BD (ordenadas da mais antiga á mais recente) 

	public List<Proba> findAllProbasData (LocalDate minhaData); 
		// Recupera TODAS as probas que deben atenderse nunha data, recuperadas da BD (ordenadas da mais antiga á mais recente)

	
	// -----------------------------------------------------------------------------
	

	public List<Consulta> findAllConsultasPaciente(Paciente meuPaciente); 
		// Recupera TODAS as consultas rexistradas na BD dun paciente, ordenadas por data (ordenadas da mais recente á mais antiga) 
	
	public List<Proba> findAllProbasPaciente(Paciente meuPaciente);
		// Recupera TODAS as citas rexistradas na BD dun paciente, ordenadas por data (ordenadas da mais recente á mais antiga) 

	
	// -----------------------------------------------------------------------------

	/*  Os seguintes métodos deben devolver datos almacenados en propiedades das clases que representan asociacións: 
	 * 	
	 * 		Paciente.citas
	 * 		Cita.paciente
	 * 		Consulta.medico
	 * 		Consulta.doenzas
	 * 		Proba.tipoProba
	 *  
	 *  Con carga EAGER, devolver o valor da propiedade 
	 *  Con carga LAZY, facer unha consulta HQL sobre a BD para obter o resultado
	 *   
	 */
	
	public SortedSet<Cita> findAllCitasPaciente(Paciente meuPaciente);
		// Recupera TODAS as citas rexistradas na BD dun paciente, ordenadas por data (ordenadas da mais recente á mais antiga) 

	public Paciente findPacienteCita(Cita minhaCita);
		// Recupera o paciente rexistrado na BD para unha cita determinada (consulta ou proba, da igual)

	public Medico findMedicoConsulta(Consulta minhaConsulta);
		// Recupera o médico rexistrado na BD para unha consulta determinada
	
	public Set<TipoDoenza> findAllDoenzasConsulta (Consulta minhaCita);
		// Recupera TODAS as doenzas diagnosticadas nuha consulta

	public TipoProba findTipoProba (Proba minhaCita);
		// Recupera o tipo de proba rexistrado na BD para unha proba determinada

}
