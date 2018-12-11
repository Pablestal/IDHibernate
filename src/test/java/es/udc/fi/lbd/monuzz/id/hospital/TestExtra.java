package es.udc.fi.lbd.monuzz.id.hospital;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
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

import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;
import es.udc.fi.lbd.monuzz.id.hospital.services.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml"})
public class TestExtra {
	
	private Logger log = Logger.getLogger("hospital");

	@Autowired
	private TestUtils testUtils;

	@Autowired
	private ExtraService extraService;
	
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
		
		Medico medicoX;
		Boolean error;
				
		
		//probando la consulta con COUNT
		assertEquals(2, extraService.findCountConsultasMedico(testUtils.medico_B).intValue());
		assertEquals(1, extraService.findCountConsultasMedico(testUtils.medico_A).intValue());

		try {
			error=false;
			medicoX  = new Medico (testUtils.medico_A.getNumColexiado(), "nada", "nada", true);
			extraService.findCountConsultasMedico(medicoX);
			
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		
		//probando la subconsulta, deberia devolver el medico con mas consultas, en este caso el medico B, que tiene 2
		assertEquals(extraService.findLastMedic(), testUtils.medico_B);
		
		
		
		//encontrar todos los pacientes que tienen citas
		List<Paciente> conjunto1 = extraService.findPacientesWithoutCitas();
		List<Paciente> conjunto2 = new ArrayList<Paciente>();
		
		conjunto2.add(testUtils.paciente_Y);
		conjunto2.add(testUtils.paciente_Z);

		
		Iterator<Paciente> it1 = conjunto1.iterator();
		Iterator<Paciente> it2 = conjunto2.iterator();
		
		
		assertEquals(conjunto1.size(), conjunto2.size());
		
		while(it1.hasNext()) {
			assertEquals(it1.next(), it2.next());
		}
		
		
		//comprobar que el paciente al no tener citas se muestra en la lista
		Paciente paciente_Prueba = new Paciente("Pac_Prueba", "Paciente_Prueba", LocalDate.of(1970, Month.JANUARY, 1));
		pacienteService.altaNovoPacienteBD(paciente_Prueba);
		
		conjunto1 = extraService.findPacientesWithoutCitas();
		conjunto2.add(paciente_Prueba);
		
		
		assertEquals(conjunto1.size(), conjunto2.size());
		
		while(it1.hasNext()) {
			assertEquals(it1.next(), it2.next());
		}
		
		pacienteService.borradoPacienteBD(paciente_Prueba);
		
		}
	

}
