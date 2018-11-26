package es.udc.fi.lbd.monuzz.id.hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.fi.lbd.monuzz.id.hospital.model.*;
import es.udc.fi.lbd.monuzz.id.hospital.services.*;

public class TestUtils {


	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private PacienteService pacienteService;
	
	
	public final long timeout = 50;
	
	public Medico medico_A;
	public Medico medico_B;
	public Medico medico_C;
	public Medico medico_D;
	
	public TipoDoenza doenza_1;
	public TipoDoenza doenza_2;
	public TipoDoenza doenza_3;
	public TipoDoenza doenza_4;

	public TipoProba tproba_1;
	public TipoProba tproba_2;
	public TipoProba tproba_3;
	public TipoProba tproba_4;
	
	public Paciente paciente_W;
	public Paciente paciente_X;
	public Paciente paciente_Y;
	public Paciente paciente_Z;
	
	public Consulta cita_W1;
	public Proba    cita_W2;
	public Consulta cita_W3;
	public Proba    cita_W4;
	public Consulta cita_X1;
	public Proba    cita_X2;
	
	
	// Creacion

	public void creaSetMedicosProba() {

		// Medico (String numColexiado, String nomeCompleto, String especialidade, Boolean activo)

		medico_A = new Medico("Med_A", "Medico_A", "Especialidade_1", true);
		medico_B = new Medico("Med_B", "Medico_B", "Especialidade_1", true);
		medico_C = new Medico("Med_C", "Medico_C", "Especialidade_2", true);
		medico_D = new Medico("Med_D", "Medico_D", "Especialidade_2", true);		
		hospitalService.altaNovoMedicoBD(medico_A);
		hospitalService.altaNovoMedicoBD(medico_B);
		hospitalService.altaNovoMedicoBD(medico_C);
		hospitalService.altaNovoMedicoBD(medico_D);		
	}
	
	public void creaSetDoenzasProba() {

		// TipoDoenza(String codigo, String nome, String descricion) 

		doenza_1 = new TipoDoenza("Dz_1", "doenza_1", "Doenza numero 1");
		doenza_2 = new TipoDoenza("Dz_2", "doenza_2", "Doenza numero 2");
		doenza_3 = new TipoDoenza("Dz_3", "doenza_3", "Doenza numero 3");
		doenza_4 = new TipoDoenza("Dz_4", "doenza_4", "Doenza numero 4");		
		hospitalService.altaNovoTipoDoenzaBD(doenza_1);
		hospitalService.altaNovoTipoDoenzaBD(doenza_2);
		hospitalService.altaNovoTipoDoenzaBD(doenza_3);
		hospitalService.altaNovoTipoDoenzaBD(doenza_4);	
	}
	
	public void creaSetTiposProbasProba() {
		// TipoProba(String codigo, String nome, String descricion)

		tproba_1 = new TipoProba("TP_1", "tproba_1", "Tipo proba numero 1");
		tproba_2 = new TipoProba("TP_2", "tproba_2", "Tipo proba numero 2");
		tproba_3 = new TipoProba("TP_3", "tproba_3", "Tipo proba numero 3");
		tproba_4 = new TipoProba("TP_4", "tproba_4", "Tipo proba numero 4");		
		hospitalService.altaNovoTipoProbaBD(tproba_1);
		hospitalService.altaNovoTipoProbaBD(tproba_2);
		hospitalService.altaNovoTipoProbaBD(tproba_3);
		hospitalService.altaNovoTipoProbaBD(tproba_4);	
	}
	
	
	public void creaSetPacientesProba() {

		// Paciente(String numPaciente, String nomeCompleto, Date dataNacemento)


		paciente_W = new Paciente("Pac_W", "Paciente_W", LocalDate.of(1950, Month.JANUARY, 1));
		paciente_X = new Paciente("Pac_X", "Paciente_X", LocalDate.of(1960, Month.JANUARY, 1));
		paciente_Y = new Paciente("Pac_Y", "Paciente_Y", LocalDate.of(1970, Month.JANUARY, 1));
		paciente_Z = new Paciente("Pac_Z", "Paciente_Z", LocalDate.of(1980, Month.JANUARY, 1));		

		pacienteService.altaNovoPacienteBD(paciente_W);
		pacienteService.altaNovoPacienteBD(paciente_X);
		pacienteService.altaNovoPacienteBD(paciente_Y);
		pacienteService.altaNovoPacienteBD(paciente_Z);		
	}

	public void creaSetCitasProba() {
		
		cita_W1 = new Consulta("Cita_W1", LocalDateTime.of(2018, Month.JANUARY,  1, 9, 45), paciente_W, "Motivo_W1", medico_A);
		cita_W1.getDoenzas().add(doenza_1);
		cita_W1.getDoenzas().add(doenza_2);
		cita_W2 = new Proba   ("Cita_W2", LocalDateTime.of(2018, Month.JANUARY,  2, 9, 45), paciente_W, "Especificacions_W2", tproba_1);
		cita_W3 = new Consulta("Cita_W3", LocalDateTime.of(2018, Month.FEBRUARY, 1, 9, 45), paciente_W, "Motivo_W3", medico_B);
		cita_W4 = new Proba   ("Cita_W4", LocalDateTime.of(2018, Month.FEBRUARY, 2, 9, 45), paciente_W, "Especificacions_W4", tproba_2);
		cita_X1 = new Consulta("Cita_X1", LocalDateTime.of(2018, Month.FEBRUARY,  1, 9, 15), paciente_X, "Motivo_X1", medico_B);
		cita_X2 = new Proba   ("Cita_X2", LocalDateTime.of(2018, Month.FEBRUARY,  2, 9, 15), paciente_X, "Especificacions_X1", tproba_2);
	
		hospitalService.altaNovaCitaBD(cita_W1);
		hospitalService.altaNovaCitaBD(cita_W2);
		hospitalService.altaNovaCitaBD(cita_W3);
		hospitalService.altaNovaCitaBD(cita_W4);
		hospitalService.altaNovaCitaBD(cita_X1);
		hospitalService.altaNovaCitaBD(cita_X2);
	}
	
	// Eliminacion =============================================================================================================
	
	
	public void eliminaSetMedicosProba() {
		hospitalService.borradoMedicoBD(medico_A);
		hospitalService.borradoMedicoBD(medico_B);
		hospitalService.borradoMedicoBD(medico_C);
		hospitalService.borradoMedicoBD(medico_D);	
	}

	public void eliminaSetDoenzasProba() {
		hospitalService.borradoTipoDoenzaBD(doenza_1);
		hospitalService.borradoTipoDoenzaBD(doenza_2);
		hospitalService.borradoTipoDoenzaBD(doenza_3);
		hospitalService.borradoTipoDoenzaBD(doenza_4);
	}

	public void eliminaSetTiposProbasProba() {
		hospitalService.borradoTipoProbaBD(tproba_1);
		hospitalService.borradoTipoProbaBD(tproba_2);
		hospitalService.borradoTipoProbaBD(tproba_3);
		hospitalService.borradoTipoProbaBD(tproba_4);
	}
	
	public void eliminaSetPacientesProba() {
		pacienteService.borradoPacienteBD(paciente_W);
		pacienteService.borradoPacienteBD(paciente_X);
		pacienteService.borradoPacienteBD(paciente_Y);
		pacienteService.borradoPacienteBD(paciente_Z);	
	}
	
	public void eliminaSetCitasProba() {
		hospitalService.borradoCitaBD(cita_W1);
		hospitalService.borradoCitaBD(cita_W2);
		hospitalService.borradoCitaBD(cita_W3);
		hospitalService.borradoCitaBD(cita_W4);
		hospitalService.borradoCitaBD(cita_X1);
		hospitalService.borradoCitaBD(cita_X2);		
	}
	
	
	
}
