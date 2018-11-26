package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoDoenza;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoProba;

public interface HospitalDAO {

	// Métodos de modificación
    // Usar Session.flush() para permitir que Spring detecte violacion de restriccións na BD

	// MEDICOS ============================================================================================================
	
	public Long create(Medico meuMedico);
		// Rexistra na BD un novo medico
		// Xenerar RuntimeException se o medico xa é persistente
	public void update (Medico meuMedico);
		// Modifica na BD os datos dun medico
		// Xenerar RuntimeException se o medico non é persistente ainda	
	public void remove (Medico meuMedico);
		// Elimina da BD os datos dun medico 
		// Xenerar RuntimeException se o medico non é persistente ainda

	// -----------------------------------------------------------------------------

	public Medico findMedicoById(Long id);
		//Recupera os datos dun medico desde a BD usando o seu id persistente
	public Medico findMedicoByNumColexiado (String numColexiado);	
		//Recupera os datos dun medico desde a BD usando o seu numero de colexiado 
	public List<Medico> findAllMedicos();
		// Recupera da BD os datos de TODOS os medicos
		// (por orde de especialidade (Z-A) e numero de colexiado (A-Z) 		
	
	// DOENZAS ============================================================================================================
	
	public Long create(TipoDoenza minhaDoenza);
		// Rexistra na BD unha nova doenza
		// Xenerar RuntimeException se a doenza xa é persistente
	public void update (TipoDoenza minhaDoenza);
		// Modifica na BD os datos dunha doenza
		// Xenerar RuntimeException se a doenza non é persistente ainda	
	public void remove (TipoDoenza minhaDoenza);
		// Elimina da BD os datos dunha doenza 
		// Xenerar RuntimeException se a doenza non é persistente ainda
	
	// -----------------------------------------------------------------------------
	
	public TipoDoenza findTipoDoenzaById(Long id);
		//Recupera os datos dunha doenza desde a BD usando o seu id persistente
	public TipoDoenza findTipoDoenzaByCodigo (String codigo);	
		//Recupera os datos dunha doenza desde a BD usando o seu codigo 
	public List<TipoDoenza> findAllTiposDoenzas();
		// Recupera da BD os datos de TODAS as Doenzas
		// (por orde de nome, A-Z) 	
		
	// PROBAS ============================================================================================================

	public Long create(TipoProba minhaProba);
		// Rexistra na BD un novo tipo de proba
		// Xenerar RuntimeException se o tipo de proba xa é persistente
	public void update (TipoProba minhaProba);
		// Modifica na BD os datos dun tipo de proba
		// Xenerar RuntimeException se o tipo de proba non é persistente ainda	
	public void remove (TipoProba minhaProba);
		// Elimina da BD os datos dun tipo de proba 
		// Xenerar RuntimeException se o tipo de proba non é persistente ainda
	
	// -----------------------------------------------------------------------------
	
	public TipoProba findTipoProbaById(Long id);
		//Recupera os datos dun tipo de proba desde a BD usando o seu id persistente
	public TipoProba findTipoProbaByCodigo (String codigo);	
		//Recupera os datos dun tipo de proba desde a BD usando o seu codigo 
	public List<TipoProba> findAllTiposProbas();
		// Recupera da BD os datos de TODOS os tipos de proba
		// (por orde de nome, A-Z) 	
	
	// -----------------------------------------------------------------------------

}
