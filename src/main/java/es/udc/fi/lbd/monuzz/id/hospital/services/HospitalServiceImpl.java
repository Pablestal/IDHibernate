package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import es.udc.fi.lbd.monuzz.id.hospital.model.*;

public class HospitalServiceImpl implements HospitalService {

	@Override
	public void altaNovoMedicoBD(Medico meuMedico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borradoMedicoBD(Medico meuMedico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificacionMedicoBD(Medico meuMedico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medico recuperarMedicoBDPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medico recuperarMedicoBDPorNumColexiado(String numColexiado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medico> recuperarTodosMedicosBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void altaNovoTipoDoenzaBD(TipoDoenza minhaDoenza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borradoTipoDoenzaBD(TipoDoenza minhaDoenza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificacionTipoDoenzaBD(TipoDoenza minhaDoenza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoDoenza recuperarTipoDoenzaBDPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoDoenza recuperarTipoDoenzaBDPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoDoenza> recuperarTodosTiposDoenzasBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void altaNovoTipoProbaBD(TipoProba minhaProba) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borradoTipoProbaBD(TipoProba minhaProba) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificacionTipoProbaBD(TipoProba minhaProba) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoProba recuperarTipoProbaBDPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoProba recuperarTipoProbaBDPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoProba> recuperarTodosTiposProbasBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void altaNovaCitaBD(Cita minhaCita) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borradoCitaBD(Cita minhaCita) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificacionCitaBD(Cita minhaCita) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cita recuperarCitaBDPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cita recuperarCitaBDPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> recuperarTodasConsultasMedicoData(Medico meuMedico, LocalDate minhaData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proba> recuperarTodasProbasData(LocalDate minhaData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> recuperarTodasConsultasPaciente(Paciente meuPaciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proba> recuperarTodasProbasPaciente(Paciente meuPaciente) {
		// TODO Auto-generated method stub
		return null;
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
