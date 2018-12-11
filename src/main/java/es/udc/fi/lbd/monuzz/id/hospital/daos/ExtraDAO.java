package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

public interface ExtraDAO {

	public Integer findCountConsultasMedico (Medico meuMedico);

	public Medico findLastMedic();
	
	public List<Paciente> findPacientesWithoutCitas ();
}
