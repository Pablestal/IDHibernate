package es.udc.fi.lbd.monuzz.id.hospital;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

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
public class TestCitas {
	
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
		testUtils.creaSetCitasProba();
		log.info ("Foron creados con éxito os datos iniciais para o caso de proba: " + this.getClass().getName() + " =============");
	}

	@After
	public void tearDown() throws Exception {
								
		log.info ("Eliminando datos iniciais para caso de proba: " + this.getClass().getName() + " ========================================");
		
		/* Ao eliminar un paciente deben desaparecer as súas citas      */
		/* Descomentar a primeira liña só se non se consegue facer funcionar tal e como está */
		/* Descomentala reducirá a nota do traballo */
		
		//testUtils.eliminaSetCitasProba();
		testUtils.eliminaSetPacientesProba();
		testUtils.eliminaSetTiposProbasProba();
		testUtils.eliminaSetDoenzasProba(); 
		testUtils.eliminaSetMedicosProba(); 
		log.info ("Foron eliminados con éxito os datos iniciais para o caso de proba: " + this.getClass().getName() + " ==========");
	}
	
	@Test
	public void testCompleto() {
		log.info ("INICIANDO [Test_Consulta] en  " + this.getClass().getName() + "===========================================================");
		a_Test_Consulta();
		log.info ("INICIANDO [Test_Alta] en  " + this.getClass().getName() + "===============================================================");
		b_Test_Alta();
		log.info ("INICIANDO [Test_Actualizacion] en  " + this.getClass().getName() + "======================================================");
		c_Test_Actualizacion();
		log.info ("INICIANDO [Test_Borrado] en  " + this.getClass().getName() + "============================================================");
		d_Test_Borrado();
	}
	
	private void a_Test_Consulta() {

		Paciente meuPaciente;
		Medico meuMedico;
		Cita minhaCita;
		Consulta minhaConsulta;
		Proba minhaProba;
		TipoProba meuTipoProba;
		
		List<Consulta> listaxeConsultas;
		List<Proba> listaxeProbas;
		
		SortedSet<Cita> conxuntoOrdenado1, conxuntoOrdenado2;
		Iterator<Cita> it1, it2;

		Set<TipoDoenza> conxuntoDoenzas1, conxuntoDoenzas2;
		Iterator<TipoDoenza> itd;
		
		// Recuperacion de obxectos individuais ---------------------------------------------------------------------------
		
		assertEquals (testUtils.cita_W1, hospitalService.recuperarCitaBDPorId(testUtils.cita_W1.getIdCita()));
		assertEquals (testUtils.cita_W2, hospitalService.recuperarCitaBDPorId(testUtils.cita_W2.getIdCita()));

		assertEquals (testUtils.cita_W1, hospitalService.recuperarCitaBDPorCodigo(testUtils.cita_W1.getCodigo()));
		assertEquals (testUtils.cita_W2, hospitalService.recuperarCitaBDPorCodigo(testUtils.cita_W2.getCodigo()));
	
		
		// Recuperacion de listaxes  -------------------------------------------------------------------------------------

		listaxeConsultas = hospitalService.recuperarTodasConsultasMedicoData(testUtils.medico_A, testUtils.cita_X1.getDataHora().toLocalDate());
		assertEquals (0,listaxeConsultas.size());

		listaxeConsultas = hospitalService.recuperarTodasConsultasMedicoData(testUtils.medico_B, testUtils.cita_X1.getDataHora().toLocalDate());
		assertEquals (2,listaxeConsultas.size());
		assertEquals (testUtils.cita_X1, listaxeConsultas.get(0));
		assertEquals (testUtils.cita_W3, listaxeConsultas.get(1));
		
		
		listaxeProbas = hospitalService.recuperarTodasProbasData(testUtils.cita_X1.getDataHora().toLocalDate());
		assertEquals (0,listaxeProbas.size());

		listaxeProbas = hospitalService.recuperarTodasProbasData(testUtils.cita_X2.getDataHora().toLocalDate());
		assertEquals (2,listaxeProbas.size());
		assertEquals (testUtils.cita_X2, listaxeProbas.get(0));
		assertEquals (testUtils.cita_W4, listaxeProbas.get(1));
		
		listaxeConsultas = hospitalService.recuperarTodasConsultasPaciente(testUtils.paciente_Z);
		assertEquals (0,listaxeConsultas.size());
		
		listaxeConsultas = hospitalService.recuperarTodasConsultasPaciente(testUtils.paciente_W);
		assertEquals (2,listaxeConsultas.size());
		assertEquals (testUtils.cita_W3, listaxeConsultas.get(0));
		assertEquals (testUtils.cita_W1, listaxeConsultas.get(1));
		
		listaxeProbas = hospitalService.recuperarTodasProbasPaciente(testUtils.paciente_Z);
		assertEquals (0,listaxeProbas.size());
		
		listaxeProbas = hospitalService.recuperarTodasProbasPaciente(testUtils.paciente_W);
		assertEquals (2,listaxeProbas.size());
		assertEquals (testUtils.cita_W4, listaxeProbas.get(0));
		assertEquals (testUtils.cita_W2, listaxeProbas.get(1));
		
		
		// Recuperacións con actualizacion de propiedade)
		
		// Paciente.citas[]
		meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_W.getIdPaciente());
		conxuntoOrdenado1 = hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		conxuntoOrdenado2 = testUtils.paciente_W.getCitas();
		it1 = conxuntoOrdenado1.iterator();
		it2 = conxuntoOrdenado2.iterator();
		assertEquals(conxuntoOrdenado1.size(), conxuntoOrdenado2.size());
		while (it1.hasNext()) {
			assertEquals (it1.next(), it2.next());
		}
		conxuntoOrdenado1 = meuPaciente.getCitas();
		conxuntoOrdenado2 = testUtils.paciente_W.getCitas();
		it1 = conxuntoOrdenado1.iterator();
		it2 = conxuntoOrdenado2.iterator();
		assertEquals(conxuntoOrdenado1.size(), conxuntoOrdenado2.size());
		while (it1.hasNext()) {
			assertEquals (it1.next(), it2.next());
		}

		// Cita.paciente
		minhaCita = hospitalService.recuperarCitaBDPorId(testUtils.cita_W1.getIdCita());
		meuPaciente = hospitalService.recuperarPacienteCita(minhaCita);		
		assertEquals(testUtils.paciente_W, meuPaciente);
		assertEquals(testUtils.paciente_W, minhaCita.getPaciente());
		
		minhaCita = hospitalService.recuperarCitaBDPorId(testUtils.cita_W2.getIdCita());
		meuPaciente = hospitalService.recuperarPacienteCita(minhaCita);		
		assertEquals(testUtils.paciente_W, meuPaciente);
		assertEquals(testUtils.paciente_W, minhaCita.getPaciente());

		// Consulta.medico
		minhaConsulta = (Consulta) hospitalService.recuperarCitaBDPorId(testUtils.cita_W1.getIdCita());
		meuMedico = hospitalService.recuperarMedicoConsulta(minhaConsulta);
		assertEquals(testUtils.medico_A, meuMedico);
		assertEquals(testUtils.medico_A, minhaConsulta.getMedico());
		
		// Consulta.doenzas[]
		minhaConsulta = (Consulta) hospitalService.recuperarCitaBDPorId(testUtils.cita_W1.getIdCita());
		conxuntoDoenzas1 = hospitalService.recuperarDoenzasConsulta(minhaConsulta);
		conxuntoDoenzas2 = testUtils.cita_W1.getDoenzas();
		assertEquals(conxuntoDoenzas1.size(), conxuntoDoenzas2.size());
		itd = conxuntoDoenzas1.iterator();
		while (itd.hasNext()) {
			assertTrue (conxuntoDoenzas2.contains(itd.next()));
		}
		itd = conxuntoDoenzas2.iterator();
		while (itd.hasNext()) {
			assertTrue (conxuntoDoenzas1.contains(itd.next()));
		}
		conxuntoDoenzas1 = minhaConsulta.getDoenzas();
		conxuntoDoenzas2 = testUtils.cita_W1.getDoenzas();
		assertEquals(conxuntoDoenzas1.size(), conxuntoDoenzas2.size());
		itd = conxuntoDoenzas1.iterator();
		while (itd.hasNext()) {
			assertTrue (conxuntoDoenzas2.contains(itd.next()));
		}
		itd = conxuntoDoenzas2.iterator();
		while (itd.hasNext()) {
			assertTrue (conxuntoDoenzas1.contains(itd.next()));
		}
		
		// Proba.tipoProba
		minhaProba = (Proba) hospitalService.recuperarCitaBDPorId(testUtils.cita_W2.getIdCita());
		meuTipoProba = hospitalService.recuperarTipoProba(minhaProba);
		assertEquals (testUtils.cita_W2.getTipoProba(), meuTipoProba);
		assertEquals (testUtils.cita_W2.getTipoProba(), minhaProba.getTipoProba());
		
	}

	private void b_Test_Alta() {

		// As insercions con información correcta xa están probadas ao inicializar as variables de TestUtils
		// Aquí só pretendemos comprobar que as insercións con datos erróneos non se admiten

		Cita minhaCita;
		Boolean error;
		Paciente meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
		hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		Medico meuMedico = hospitalService.recuperarMedicoBDPorId(testUtils.medico_D.getIdMedico());
		TipoProba minhaProba = hospitalService.recuperarTipoProbaBDPorId(testUtils.tproba_4.getIdTipoProba());
				
		// Detección de duplicados: Cita.codigoCita   (Consulta e Proba)
				
		try {
			error=false;
			minhaCita = new Consulta(testUtils.cita_W3.getCodigo(), LocalDateTime.of(2018, Month.AUGUST,  1, 8, 30), meuPaciente, "Motivo motivoso", meuMedico);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);

		try {
			error=false;
			minhaCita = new Proba(testUtils.cita_W4.getCodigo(), LocalDateTime.of(2018, Month.AUGUST,  1, 8, 30), meuPaciente, "Especificacions especificas" , minhaProba);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);

		// Deteccion de codigo nulo (consulta e proba)
		
		try {
			error=false;
			minhaCita = new Consulta(null, LocalDateTime.of(2018, Month.AUGUST,  1, 8, 30), meuPaciente, "Motivo motivoso", meuMedico);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);

		try {
			error=false;
			minhaCita = new Proba(null, LocalDateTime.of(2018, Month.AUGUST,  1, 8, 30), meuPaciente, "Especificacions especificas" , minhaProba);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);
		
		// Deteccion de datahora nula (consulta e proba)
		
		try {
			error=false;
			minhaCita = new Consulta("C666", null, meuPaciente, "Motivo motivoso", meuMedico);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);

		try {
			error=false;
			minhaCita = new Proba("C666", null, meuPaciente, "Especificacions especificas" , minhaProba);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);

		// Deteccion de motivo nulo (consulta)
		
		try {
			error=false;
			minhaCita = new Consulta("C666", LocalDateTime.of(2018, Month.AUGUST,  1, 8, 30), meuPaciente, null, meuMedico);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);

		// Deteccion de medico nulo (consulta)
		
		try {
			error=false;
			minhaCita = new Consulta("C666", LocalDateTime.of(2018, Month.AUGUST,  1, 8, 30), meuPaciente, "Motivo motivado", null);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);
		
		// Detección de especificacions nulas (proba)
		try {
			error=false;
			minhaCita = new Proba("C666", LocalDateTime.of(2018, Month.AUGUST,  1, 8, 30), meuPaciente, null , minhaProba);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);

		// Detección de tipo de proba nula (proba)
		try {
			error=false;
			minhaCita = new Proba("C666", LocalDateTime.of(2018, Month.AUGUST,  1, 8, 30), meuPaciente, "Especificacions especificas" , null);
			hospitalService.altaNovaCitaBD(minhaCita);
		} catch (DataAccessException e){
			error=true;
			meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
			hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		}
		assertTrue(error);

		// Comprobamos que as insercions erroneas non funcionaron ----------------------------------------------------------
		
		assertEquals (testUtils.paciente_Z.getCitas().size(), meuPaciente.getCitas().size());
		
	}
	
	private void c_Test_Actualizacion() {

		// Xa detectamos duplicados e nulos. Aqui só imos probar que as actualizacions funcionan  

		Consulta minhaConsulta;
		Proba minhaProba;
		Cita minhaCita;

		Set<TipoDoenza> doenzas1, doenzas2;
		
		Paciente meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Z.getIdPaciente());
		hospitalService.recuperarTodasCitasPaciente(meuPaciente);
		Medico meuMedico = hospitalService.recuperarMedicoBDPorId(testUtils.medico_D.getIdMedico());
		TipoProba meuTipo = hospitalService.recuperarTipoProbaBDPorId(testUtils.tproba_4.getIdTipoProba());

		// Damos de alta novas citas para probar actualizacion

		minhaConsulta = new Consulta("C666", LocalDateTime.of(2018, Month.AUGUST,  1, 8, 30), meuPaciente, "Motivo motivado", meuMedico);
		minhaProba = new Proba("P666", LocalDateTime.of(2018, Month.AUGUST,  2, 8, 30), meuPaciente, "Especificacions especificas" , meuTipo);
		hospitalService.altaNovaCitaBD(minhaConsulta);
		hospitalService.altaNovaCitaBD(minhaProba);
		

		// Cita.codigoCita   (Consulta e Proba)
		minhaConsulta.setCodigo("C_actualizada");
		minhaProba.setCodigo("P_actualizada");
		hospitalService.modificacionCitaBD(minhaConsulta);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaConsulta.getCodigo()); 
		assertEquals (minhaConsulta, minhaCita);
		hospitalService.modificacionCitaBD(minhaProba);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaProba.getCodigo()); 
		assertEquals (minhaProba, minhaCita);
		
		// Cita.dataHora (consulta e proba)
		minhaConsulta.setDataHora(LocalDateTime.of(2018, Month.AUGUST,  10, 8, 30));
		minhaCita.setDataHora(LocalDateTime.of(2018, Month.AUGUST,  11, 8, 30));
		hospitalService.modificacionCitaBD(minhaConsulta);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaConsulta.getCodigo()); 
		assertEquals (minhaConsulta.getDataHora(), minhaCita.getDataHora());
		hospitalService.modificacionCitaBD(minhaProba);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaProba.getCodigo()); 
		assertEquals (minhaProba.getDataHora(), minhaCita.getDataHora());
		
		// Cita.paciente (consulta e proba)		
		meuPaciente = pacienteService.recuperarPacienteBDPorId(testUtils.paciente_Y.getIdPaciente());
		minhaConsulta.setPaciente(meuPaciente);
		minhaProba.setPaciente(meuPaciente);
		hospitalService.modificacionCitaBD(minhaConsulta);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaConsulta.getCodigo());
		hospitalService.recuperarPacienteCita(minhaCita);
		assertEquals (minhaConsulta.getPaciente(), minhaCita.getPaciente());
		hospitalService.modificacionCitaBD(minhaProba);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaProba.getCodigo()); 
		hospitalService.recuperarPacienteCita(minhaCita);
		assertEquals (minhaProba.getPaciente(), minhaCita.getPaciente());

		// Consulta.motivo
		minhaConsulta.setMotivo("Motivo motivado e actualizado");
		hospitalService.modificacionCitaBD(minhaConsulta);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaConsulta.getCodigo()); 
		assertEquals (minhaConsulta.getMotivo(), ((Consulta)minhaCita).getMotivo());
		
		// Consulta.medico
		meuMedico = hospitalService.recuperarMedicoBDPorId(testUtils.medico_C.getIdMedico());
		minhaConsulta.setMedico(meuMedico);
		hospitalService.modificacionCitaBD(minhaConsulta);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaConsulta.getCodigo()); 
		hospitalService.recuperarMedicoConsulta(((Consulta)minhaCita));
		assertEquals (minhaConsulta.getMedico(), ((Consulta)minhaCita).getMedico());
		
		// Consulta.informe, consulta.doenzas
		doenzas1 = new HashSet<TipoDoenza>();
		doenzas1.add(testUtils.doenza_3);
		doenzas1.add(testUtils.doenza_4);
		minhaConsulta.setInforme("Informe informado e actualizado");
		minhaConsulta.setDoenzas(doenzas1);
		hospitalService.modificacionCitaBD(minhaConsulta);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaConsulta.getCodigo()); 
		doenzas2 = hospitalService.recuperarDoenzasConsulta((Consulta)minhaCita);
		assertEquals (minhaConsulta.getInforme(), ((Consulta)minhaCita).getInforme());
		assertEquals (doenzas1.size(), doenzas2.size());
		assertEquals (minhaConsulta.getDoenzas().size(), ((Consulta)minhaCita).getDoenzas().size());
		
		// Proba.tipoProba
		minhaProba.setTipoProba(testUtils.tproba_3);
		hospitalService.modificacionCitaBD(minhaProba);
		minhaCita = hospitalService.recuperarCitaBDPorCodigo(minhaProba.getCodigo()); 
		hospitalService.recuperarTipoProba((Proba)minhaCita);
		assertEquals (minhaProba.getTipoProba(), ((Proba)minhaCita).getTipoProba());
	}


	
	private void d_Test_Borrado() {
		
		Consulta minhaConsulta;
		Proba minhaProba;
		
		Boolean error;

		// Eliminar as citas creadas no test de actualizacion

		minhaConsulta = (Consulta)hospitalService.recuperarCitaBDPorCodigo("C_actualizada");
		minhaProba = (Proba)hospitalService.recuperarCitaBDPorCodigo("P_actualizada");
		
		assertNotNull(minhaConsulta);
		assertNotNull(minhaProba);

		hospitalService.borradoCitaBD(minhaConsulta);
		hospitalService.borradoCitaBD(minhaProba);
		
		assertNull (hospitalService.recuperarCitaBDPorCodigo("C_Actualizada"));
		assertNull (hospitalService.recuperarCitaBDPorCodigo("P_Actualizada"));

		
		// Comprobamos eliminacións non permitidas
		
		
		// TipoDoenza con consultas vinculadas
		try {
			error=false;
			hospitalService.borradoTipoDoenzaBD(testUtils.doenza_1);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		// Medico con consultas vinculadas
		try {
			error=false;
			hospitalService.borradoMedicoBD(testUtils.medico_A);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		
		// TipoProba con probas vinculadas
		try {
			error=false;
			hospitalService.borradoTipoProbaBD(testUtils.tproba_1);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		
	}
}
