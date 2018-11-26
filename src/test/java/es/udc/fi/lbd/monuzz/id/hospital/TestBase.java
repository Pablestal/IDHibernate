package es.udc.fi.lbd.monuzz.id.hospital;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.fi.lbd.monuzz.id.hospital.model.*;
import es.udc.fi.lbd.monuzz.id.hospital.services.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml"})
public class TestBase {
	
	private Logger log = Logger.getLogger("hospital");

	@Autowired
	private TestUtils testUtils;

	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private PacienteService pacienteService;

	@Before
	public void setUp() throws Exception {
		//Creamos datos de proba iniciais
		log.info ("Creando datos iniciais para caso de proba: " + this.getClass().getName() + " ===========================================");
		testUtils.creaSetMedicosProba(); 
		testUtils.creaSetDoenzasProba();
		testUtils.creaSetTiposProbasProba();
		testUtils.creaSetPacientesProba();
		log.info ("Foron creados con éxito os datos iniciais para o caso de proba: " + this.getClass().getName() + " =============");
	}

	@After
	public void tearDown() throws Exception {
		log.info ("Eliminando datos iniciais para caso de proba: " + this.getClass().getName() + " ========================================");
		testUtils.eliminaSetPacientesProba();
		testUtils.eliminaSetTiposProbasProba();
		testUtils.eliminaSetDoenzasProba(); 
		testUtils.eliminaSetMedicosProba(); 
		log.info ("Foron eliminados con éxito os datos iniciais para o caso de proba: " + this.getClass().getName() + " ==========");
	}
	
	@Test
	public void testCompleto() {

		log.info ("INICIANDO [Test_Consulta] en: " + this.getClass().getName() + " ===========================================================");
		a_Test_Consulta();
		log.info ("INICIANDO [Test_Alta] en: " + this.getClass().getName() + " ===============================================================");
		b_Test_Alta();
		log.info ("INICIANDO [Test_Actualizacion] en: " + this.getClass().getName() + " ======================================================");
		c_Test_Actualizacion();
		log.info ("INICIANDO [Test_Borrado] en: " + this.getClass().getName() + " ============================================================");
		d_Test_Borrado();

	}
	
	
	
	private void a_Test_Consulta() {
		
		
		// Recuperacion de obxectos individuais ---------------------------------------------------------------------------
				
		assertEquals (testUtils.doenza_1, hospitalService.recuperarTipoDoenzaBDPorId(testUtils.doenza_1.getIdTipoDoenza()));
		assertEquals (testUtils.doenza_1, hospitalService.recuperarTipoDoenzaBDPorCodigo(testUtils.doenza_1.getCodigo()));

		assertEquals (testUtils.tproba_1, hospitalService.recuperarTipoProbaBDPorId(testUtils.tproba_1.getIdTipoProba()));
		assertEquals (testUtils.tproba_1, hospitalService.recuperarTipoProbaBDPorCodigo(testUtils.tproba_1.getCodigo()));

		assertEquals (testUtils.medico_A, hospitalService.recuperarMedicoBDPorId(testUtils.medico_A.getIdMedico()));
		assertEquals (testUtils.medico_A, hospitalService.recuperarMedicoBDPorNumColexiado(testUtils.medico_A.getNumColexiado()));
		
		assertEquals (testUtils.paciente_W, pacienteService.recuperarPacienteBDPorId(testUtils.paciente_W.getIdPaciente()));
		assertEquals (testUtils.paciente_W, pacienteService.recuperarPacienteBDPorNumPaciente(testUtils.paciente_W.getNumPaciente()));

		// Recuperacion de listaxes  -------------------------------------------------------------------------------------

		List<TipoProba> listaxeProbas;
		List<TipoDoenza> listaxeDoenzas;
		List<Medico> listaxeMedicos;
		List<Paciente> listaxePacientes;
		
		listaxeDoenzas = hospitalService.recuperarTodosTiposDoenzasBD();
		assertEquals(4, listaxeDoenzas.size());
		assertEquals(listaxeDoenzas.get(0), testUtils.doenza_1);
		assertEquals(listaxeDoenzas.get(1), testUtils.doenza_2);
		assertEquals(listaxeDoenzas.get(2), testUtils.doenza_3);
		assertEquals(listaxeDoenzas.get(3), testUtils.doenza_4);
		
		listaxeProbas = hospitalService.recuperarTodosTiposProbasBD();
		assertEquals(4, listaxeProbas.size());
		assertEquals(listaxeProbas.get(0), testUtils.tproba_1);
		assertEquals(listaxeProbas.get(1), testUtils.tproba_2);
		assertEquals(listaxeProbas.get(2), testUtils.tproba_3);
		assertEquals(listaxeProbas.get(3), testUtils.tproba_4);
		
		listaxeMedicos = hospitalService.recuperarTodosMedicosBD();
		assertEquals(4, listaxeMedicos.size());
		assertEquals(listaxeMedicos.get(0), testUtils.medico_C);
		assertEquals(listaxeMedicos.get(1), testUtils.medico_D);
		assertEquals(listaxeMedicos.get(2), testUtils.medico_A);
		assertEquals(listaxeMedicos.get(3), testUtils.medico_B);

		listaxePacientes = pacienteService.recuperarTodosPacientesBD();
		assertEquals(4, listaxePacientes.size());
		assertEquals(listaxePacientes.get(0), testUtils.paciente_Z);
		assertEquals(listaxePacientes.get(1), testUtils.paciente_Y);
		assertEquals(listaxePacientes.get(2), testUtils.paciente_X);
		assertEquals(listaxePacientes.get(3), testUtils.paciente_W);
	}
	
	
	
	private void b_Test_Alta() {

		TipoProba tprobaX;
		TipoDoenza tdoenzaX;
		Medico medicoX;
		Paciente pacienteX;
		Boolean error;
		
		// Deteccion duplicados -------------------------------------------------------------------------------------

		try {
			error=false;
			tdoenzaX  = new TipoDoenza (testUtils.doenza_1.getCodigo(), "nada", "nada");
			hospitalService.altaNovoTipoDoenzaBD(tdoenzaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		try {
			error=false;
			tprobaX  = new TipoProba (testUtils.tproba_1.getCodigo(), "nada", "nada");
			hospitalService.altaNovoTipoProbaBD(tprobaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		try {
			error=false;
			medicoX  = new Medico (testUtils.medico_A.getNumColexiado(), "nada", "nada", true);
			hospitalService.altaNovoMedicoBD(medicoX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		try {
			error=false;
			pacienteX  = new Paciente (testUtils.paciente_W.getNumPaciente(), "nada", LocalDate.of(2018, Month.JANUARY,  1));
			pacienteService.altaNovoPacienteBD(pacienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		
		//Deteccion chave natural nula -------------------------------------------------------------------------------------

		try {
			error=false;
			tdoenzaX  = new TipoDoenza (null, "nada", "nada");
			hospitalService.altaNovoTipoDoenzaBD(tdoenzaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		try {
			error=false;
			tprobaX  = new TipoProba (null, "nada", "nada");
			hospitalService.altaNovoTipoProbaBD(tprobaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		try {
			error=false;
			medicoX  = new Medico (null, "nada", "nada", true);
			hospitalService.altaNovoMedicoBD(medicoX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		try {
			error=false;
			pacienteX  = new Paciente (null, "nada", LocalDate.of(2018, Month.JANUARY,  1));
			pacienteService.altaNovoPacienteBD(pacienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		//Deteccion nome de tipo de doenza nulo -------------------------------------------------------------------------------------

		try {
			error=false;
			tdoenzaX  = new TipoDoenza ("Doenza X", null, "nada");
			hospitalService.altaNovoTipoDoenzaBD(tdoenzaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		//Deteccion nome de tipo de proba nulo -------------------------------------------------------------------------------------
		
		try {
			error=false;
			tprobaX  = new TipoProba ("Proba X", null, "nada");
			hospitalService.altaNovoTipoProbaBD(tprobaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		//Deteccion nome de medico nulo -------------------------------------------------------------------------------------

		try {
			error=false;
			medicoX  = new Medico ("MX", null, "nada", true);
			hospitalService.altaNovoMedicoBD(medicoX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		//Deteccion nome de paciente nulo -------------------------------------------------------------------------------------

		try {
			error=false;
			pacienteX  = new Paciente ("PX", null, LocalDate.of(2018, Month.JANUARY,  1));
			pacienteService.altaNovoPacienteBD(pacienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		// Comprobamos que as insercions erroneas non funcionaron ----------------------------------------------------------
		
		assertEquals (4, ((List<TipoDoenza>) hospitalService.recuperarTodosTiposDoenzasBD()).size());
		assertEquals (4, ((List<TipoProba>)  hospitalService.recuperarTodosTiposProbasBD()).size());
		assertEquals (4, ((List<Medico>)     hospitalService.recuperarTodosMedicosBD()).size());
		assertEquals (4, ((List<Paciente>)   pacienteService.recuperarTodosPacientesBD()).size());
	
	}
	
	
	
	private void c_Test_Actualizacion() {

		TipoDoenza tdoenzaX, tdoenzaY;
		TipoProba tprobaX, tprobaY;
		Medico medicoX, medicoY;
		Paciente pacienteX, pacienteY;
		Boolean error;
		
		// Obxectos de proba
		
		tdoenzaX  = new TipoDoenza ("TDX", "nada", "nada");
		tprobaX  = new TipoProba ("TPX", "nada", "nada");
		medicoX  = new Medico ("MX", "nada", "nada", true);
		pacienteX  = new Paciente ("PX", "nada", LocalDate.of(2018, Month.JANUARY,  1));

		hospitalService.altaNovoTipoDoenzaBD(tdoenzaX);
		hospitalService.altaNovoTipoProbaBD(tprobaX);
		hospitalService.altaNovoMedicoBD(medicoX);
		pacienteService.altaNovoPacienteBD(pacienteX);
		
		// Actualización de chave natural duplicada
		
		try {
			error=false;
			tdoenzaX.setCodigo(testUtils.doenza_1.getCodigo());
			hospitalService.modificacionTipoDoenzaBD(tdoenzaX);
		} catch (DataAccessException e){
			error=true;
			tdoenzaX.setCodigo("TDX");			
		}
		assertTrue(error);
		
		try {
			error=false;
			tprobaX.setCodigo(testUtils.tproba_1.getCodigo());
			hospitalService.modificacionTipoProbaBD(tprobaX);
		} catch (DataAccessException e){
			tprobaX.setCodigo("TPX");
			error=true;
		}
		assertTrue(error);
		
		try {
			error=false;
			medicoX.setNumColexiado(testUtils.medico_A.getNumColexiado());
			hospitalService.modificacionMedicoBD(medicoX);
		} catch (DataAccessException e){
			error=true;
			medicoX.setNumColexiado("MX");
		}
		assertTrue(error);

		try {
			error=false;
			pacienteX.setNumPaciente(testUtils.paciente_W.getNumPaciente());
			pacienteService.modificacionPacienteBD(pacienteX);
		} catch (DataAccessException e){
			error=true;
			pacienteX.setNumPaciente("PX");
		}
		assertTrue(error);

		// Comprobar actualizacion chave natural non duplicada

		tdoenzaX.setCodigo("TD_X");
		tprobaX.setCodigo("TP_X");
		medicoX.setNumColexiado("M_X");
		pacienteX.setNumPaciente("P_X");

		hospitalService.modificacionTipoDoenzaBD(tdoenzaX);
		hospitalService.modificacionTipoProbaBD(tprobaX);
		hospitalService.modificacionMedicoBD(medicoX);
		pacienteService.modificacionPacienteBD(pacienteX);

		assertEquals (tdoenzaX, hospitalService.recuperarTipoDoenzaBDPorCodigo(tdoenzaX.getCodigo()));
		assertEquals (tprobaX, hospitalService.recuperarTipoProbaBDPorCodigo(tprobaX.getCodigo()));
		assertEquals (medicoX, hospitalService.recuperarMedicoBDPorNumColexiado(medicoX.getNumColexiado()));		
		assertEquals (pacienteX, pacienteService.recuperarPacienteBDPorNumPaciente(pacienteX.getNumPaciente()));
		
				
		// Comprobar actualizacion dos outros datos 
		
		tdoenzaX.setNome("todo");
		tdoenzaX.setDescricion("todo");
		
		tprobaX.setNome("todo");
		tprobaX.setDescricion("todo");

		medicoX.setNomeCompleto("todo");
		medicoX.setEspecialidade("todo");
		medicoX.setActivo(false);
		
		pacienteX.setNomeCompleto("todo");
		pacienteX.setDataNacemento(LocalDate.of(2018, Month.DECEMBER, 31));
		
		hospitalService.modificacionTipoDoenzaBD(tdoenzaX);
		hospitalService.modificacionTipoProbaBD(tprobaX);
		hospitalService.modificacionMedicoBD(medicoX);
		pacienteService.modificacionPacienteBD(pacienteX);

		tdoenzaY = hospitalService.recuperarTipoDoenzaBDPorCodigo(tdoenzaX.getCodigo());
		tprobaY =  hospitalService.recuperarTipoProbaBDPorCodigo(tprobaX.getCodigo());
		medicoY =  hospitalService.recuperarMedicoBDPorNumColexiado(medicoX.getNumColexiado());		
		pacienteY = pacienteService.recuperarPacienteBDPorNumPaciente(pacienteX.getNumPaciente());

		assertEquals (tdoenzaX.getNome(), tdoenzaY.getNome());
		assertEquals (tdoenzaX.getDescricion(), tdoenzaY.getDescricion());

		assertEquals (tprobaX.getNome(), tprobaY.getNome());
		assertEquals (tprobaX.getDescricion(), tprobaY.getDescricion());
		
		assertEquals (medicoY.getNomeCompleto(), medicoX.getNomeCompleto());
		assertEquals (medicoY.getEspecialidade(), medicoX.getEspecialidade());
		assertEquals (medicoY.getActivo(), medicoX.getActivo());
		
		assertEquals (pacienteY.getNomeCompleto(), pacienteX.getNomeCompleto());
		assertEquals (pacienteY.getDataNacemento(), pacienteX.getDataNacemento());
	}

	
	private void d_Test_Borrado() {

		// Os borrados con información correcta xa se proban ao eliminar as variables de TestUtils
		// Aquí só pretendemos comprobar que os borrados con datos erróneos non se admiten

		TipoDoenza tdoenzaX;
		TipoProba tprobaX;
		Medico medicoX;
		Paciente pacienteX;
		Boolean error;
		
		// Obxectos de proba
		
		tdoenzaX  = new TipoDoenza ("TDX", "nada", "nada");
		tprobaX  = new TipoProba ("TPX", "nada", "nada");
		medicoX  = new Medico ("MX", "nada", "nada", true);
		pacienteX  = new Paciente ("PX", "nada", LocalDate.of(2018, Month.JANUARY,  1));
		
		// Intento de borrado de obxectos que non existen na BD (id persistente nulo)

		try {
			error=false;
			hospitalService.borradoTipoDoenzaBD(tdoenzaX);
		} catch (Exception e){error=true;}
		assertTrue(error);

		try {
			error=false;
			hospitalService.borradoTipoProbaBD(tprobaX);
		} catch (Exception e){error=true;}
		assertTrue(error);
		try {
			error=false;
			hospitalService.borradoMedicoBD(medicoX);
		} catch (Exception e){error=true;}
		assertTrue(error);
		try {
			error=false;
			pacienteService.borradoPacienteBD(pacienteX);
		} catch (Exception e){error=true;}
		assertTrue(error);

		// Eliminamos obxectos de proba creados neste test

		
		assertEquals (5, hospitalService.recuperarTodosTiposDoenzasBD().size());
		assertEquals (5, hospitalService.recuperarTodosTiposProbasBD().size());
		assertEquals (5, hospitalService.recuperarTodosMedicosBD().size());
		assertEquals (5, pacienteService.recuperarTodosPacientesBD().size());
		
		tdoenzaX=hospitalService.recuperarTipoDoenzaBDPorCodigo("TD_X");
		tprobaX=hospitalService.recuperarTipoProbaBDPorCodigo("TP_X");
		medicoX=hospitalService.recuperarMedicoBDPorNumColexiado("M_X");
		pacienteX=pacienteService.recuperarPacienteBDPorNumPaciente("P_X");
		
		hospitalService.borradoTipoDoenzaBD(tdoenzaX);
		hospitalService.borradoTipoProbaBD(tprobaX);
		hospitalService.borradoMedicoBD(medicoX);
		pacienteService.borradoPacienteBD(pacienteX);
		
		assertNull(hospitalService.recuperarTipoDoenzaBDPorCodigo("TD_X"));
		assertNull(hospitalService.recuperarTipoProbaBDPorCodigo("TP_X"));
		assertNull(hospitalService.recuperarMedicoBDPorNumColexiado("M_X"));
		assertNull(pacienteService.recuperarPacienteBDPorNumPaciente("P_X"));

		assertEquals (4, hospitalService.recuperarTodosTiposDoenzasBD().size());
		assertEquals (4, hospitalService.recuperarTodosTiposProbasBD().size());
		assertEquals (4, hospitalService.recuperarTodosMedicosBD().size());
		assertEquals (4, pacienteService.recuperarTodosPacientesBD().size());
	}

}
