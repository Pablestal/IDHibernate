package es.udc.fi.lbd.monuzz.id.hospital;



import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.fi.lbd.monuzz.id.hospital.services.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml"})
public class TestExtra {
	
	private Logger log = Logger.getLogger("hospital");

	@Autowired
	private TestUtils testUtils;

	@Autowired
	private ExtraService extraService;

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
		
		//testUtils.eliminaSetCitasProba();
		testUtils.eliminaSetPacientesProba();
		testUtils.eliminaSetTiposProbasProba();
		testUtils.eliminaSetDoenzasProba(); 
		testUtils.eliminaSetMedicosProba(); 
		log.info ("Foron eliminados con éxito os datos iniciais para o caso de proba: " + this.getClass().getName() + " ==========");
	}
	
	@Test
	public void testCompleto() {

		log.info ("INICIANDO [Test_Extra] en: " + this.getClass().getName() + " ===========================================================");
		a_Test();


	}
	
	
	
	private void a_Test() {
		

	}
	

}
