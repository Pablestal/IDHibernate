package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.util.List;

import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

public interface ExtraService  {

	//calcula el número de consultas que tiene un médico
	public Integer findCountConsultasMedico (Medico meuMedico);

	//devuelve al medico con la consulta mas reciente
	public Medico findLastMedic();
	
	//encuentra todos los pacientes que no tienen citas
	public List<Paciente> findPacientesWithoutCitas();
	
}
