package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.util.List;

import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

public interface ExtraService  {

	//calcula el número de consultas que tiene un médico
	public Integer findCountConsultasMedico (Medico meuMedico);

	//devuelve al medico con mas consultas
	public Medico findBestMedic();
	
	//encuentra todos los pacientes que tienen citas
	public List<Paciente> findPacientesWithCitas();
	
}
