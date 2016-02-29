package business.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import directory.business.dao.IDao;
import directory.business.dao.jpa.JpaDao;
import directory.model.Group;
import directory.model.Person;

public class testDao {

	static IDao dao;
	Person P;
	Group G;
	
	@BeforeClass
	public static void beforeAll()  {
		System.out.println("Starting Test");
		dao = new JpaDao("test");
		dao.init();
	}

	@AfterClass
	public static void afterAll() {
		dao.close();
	}

	@Before
	public void setUp() throws ParseException {
		// une instance Person pour les tests
		P = new Person();
		P.setFamilyName("TRIELLI");
		P.setFirstName("Anthony");
		Date D = new SimpleDateFormat("yyyy-MM-dd").parse("1991-10-12");
		P.setBirthDate(D);
		P.setEmail("trielli.anthony@hotmail.fr");
		P.setWebSite("www.univ-amu.fr");
		
		// une instance Group pour les tests
		G = new Group();
		G.setName("Integration Systeme logicel");
	}

	@After
	public void tearDown() {
		dao.removeGroup(G.getId());
		dao.removePerson(P.getId());
	}
	
	/**
	 *	Test d'ajout d'une nouvelle personne
	 *	en base de données vierge
	 */
	@Test
	public void testSaveNewPerson() {
		List<Person> listBefore = dao.findAllPersons();
		P.setId(0);
		dao.savePerson(P);
		List<Person> listAfter = dao.findAllPersons();
		assertFalse(listBefore.equals(listAfter));
	}
	
	/**
	 *	Test d'ajout d'une même personne
	 *	en base de données vierge
	 */
	@Test
	public void testSaveExistantPerson() {
		// une nouvelle personne
		P.setId(1);
		dao.savePerson(P);
		List<Person> listBefore = dao.findAllPersons();
		// on reajoute cette memme personne
		dao.savePerson(P);
		List<Person> listAfter = dao.findAllPersons();
		assertEquals(listBefore, listAfter);
	}
	
	/**
	 *	Test de supression d'une personne
	 *	qui existe en base de données 
	 */
	@Test
	public void testRemoveExistantPerson() {
		List<Person> listBefore = dao.findAllPersons();
		// une nouvelle personne
		P.setId(2);
		dao.savePerson(P);
		// on efface cette personne
		dao.removePerson(2);
		List<Person> listAfter = dao.findAllPersons();
		assertTrue(listBefore.size() == listAfter.size());
	}
	
	/**
	 *	Test de supression d'une personne
	 *	qui n'existe pas en base de données 
	 */
	@Test
	public void testRemoveUnExistantPerson() {
		List<Person> listBefore = dao.findAllPersons();
		// on efface une personne dont l'id n'existe pas en BD
		dao.removePerson(999);
		List<Person> listAfter = dao.findAllPersons();
		assertTrue(listBefore.size() == listAfter.size());
	}
	
	
	/**
	 *	Test sur la recherche d'une personne qui existe en BD
	 */
	@Test
	public void testFindExistantPerson() {
		// on ajoute une nouvelle personne
		P.setId(4);
		dao.savePerson(P);
		// on recherche cette personne
		Person P1 = dao.findPerson(4);
		assertTrue(P1.getId() == 4);
	}
	
	/**
	 *	Test sur la recherche d'une personne qui n'existe pas en BD
	 */
	@Test
	public void testFindUnexistantPerson() {
		// on recherche cette personne
		Person P1 = dao.findPerson(999);
		assertNull(P1);
	}
	
	/**
	 *	Test sur la recherche de toutes les personnes dans un annuaire 
	 *  qui contient des personnes
	 */
	@Test
	public void testFindAllPersonWithData() {
		// on ajoute une personne
		P.setId(1);
		dao.savePerson(P);
		// on recherche toutes les personne
		List<Person> List = dao.findAllPersons();
		assertTrue(List.size() != 0);
	}
	
	/**
	 *	Test sur l'ajout d'un nouveau groupe
	 */
	@Test
	public void testSaveNewGroup() {
		List<Group> listBefore = dao.findAllGroups();
		G.setId(0);
		dao.saveGroup(G);
		List<Group> listAfter = dao.findAllGroups();
		assertTrue(listBefore.size() != listAfter.size());
	}
	
	/**
	 *	Test sur l'ajout d'un groupe qui existe déjà
	 */
	@Test
	public void testSaveExistantGroup() {
		G.setId(1);
		dao.saveGroup(G);
		List<Group> listBefore = dao.findAllGroups();
		dao.saveGroup(G);
		List<Group> listAfter = dao.findAllGroups();
		assertEquals(listBefore, listAfter);
	}
	
	/**
	 *	Test de supression d'une personne
	 *	qui existe en base de données 
	 */
	@Test
	public void testRemoveExistantGroup() {
		List<Group> listBefore = dao.findAllGroups();
		G.setId(2);
		dao.saveGroup(G);
		dao.removeGroup(2);
		List<Group> listAfter = dao.findAllGroups();
		assertEquals(listBefore, listAfter);
	}
	
	/**
	 *	Test de supression d'une personne
	 *	qui n'existe pas en base de données 
	 */
	@Test
	public void testRemoveUnExistantGroup() {
		List<Group> listBefore = dao.findAllGroups();
		dao.removeGroup(999);
		List<Group> listAfter = dao.findAllGroups();
		assertEquals(listBefore, listAfter);
	}
	
	/**
	 *	Test sur la recherche d'un groupe qui existe dans l'annuaire
	 */
	@Test
	public void testFindExistantGroup() {
		G.setId(4);
		dao.saveGroup(G);
		Group G1 = dao.findGroup(4);
		assertTrue(G1.getId() == 4);
	}
	
	/**
	 *	Test sur la recherche d'un groupe qui n'existe pas en BD
	 */
	@Test
	public void testFindUnexistantGroup() {
		Group G1 = dao.findGroup(999);
		assertNull(G1);
	}
	
	/**
	 *	Test sur la recherche de tous les groupes dans un annuaire 
	 *  qui contient des groupes
	 */
	@Test
	public void testFindAllGroupWithData() {
		G.setId(1);
		dao.saveGroup(G);
		assertTrue(dao.findAllGroups().contains(G));
	}
	
	/**
	 *	Test l'ajout d'une personnne existante dans un groupe 
	 *	qui existe
	 */
	@Test
	public void testAddExistantPersonInExistantGroup() {
		// on creer une personne dans l'annuaire
		P.setId(5);
		dao.savePerson(P);
		// on creer un groupe dans l'annuaire
		G.setId(5);
		dao.saveGroup(G);
		// on ajoute la personne au nouveau groupe
		dao.addPersonInGroup(5, 5);
		//
		Group G1 = dao.findGroup(5);
		assertTrue(G1.getList().contains(P));
	}
	
	/**
	 *	Test l'ajout d'une personnne qui n'existe pas dans un groupe 
	 *	qui existe
	 */
	@Test
	public void testAddUnExistantPersonInExistantGroup() {
		// on creer un groupe dans l'annuaire
		G.setId(6);
		dao.saveGroup(G);
		// on ajoute la personne au nouveau groupe
		dao.addPersonInGroup(999, 6);
		//
		Group G1 = dao.findGroup(6);
		assertTrue(G1.getList().isEmpty());
	}
	
	/**
	 *	Test l'ajout d'une personnne qui existe dans un groupe 
	 *	qui n'existe pas
	 */
	@Test
	public void testAddExistantPersonInUnexistantGroup() {
		// on creer une personne dans l'annuaire
		P.setId(7);
		dao.savePerson(P);
		dao.addPersonInGroup(7, 999);
		//
		List<Person> list = dao.findAllPersonsInGroup(999);
		assertNull(list);
	}
	
	/**
	 *	Test l'ajout d'une personnne qui n'existe pas dans un groupe 
	 *	qui n'existe pas
	 */
	@Test
	public void testAddUnexistantPersonInUnexistantGroup() {
		dao.addPersonInGroup(999, 999);
		Group G1 = dao.findGroup(999);
		assertNull(G1);
	}
	
	/**
	 *	Test suppression d'une personnne qui existe dans un groupe 
	 *	qui existe
	 */
	@Test
	public void testRemoveExistantPersonInExistantGroup() {
		P.setId(8);
		dao.savePerson(P);
		G.setId(8);
		dao.saveGroup(G);
		
		dao.addPersonInGroup(8, 8);
		assertTrue(dao.findAllPersonsInGroup(8).contains(P));
		
		dao.removePersonFromGroup(8, 8);
		for(Person p : dao.findAllPersonsInGroup(8)) {
			System.out.println(p.getId());
		}
		assertFalse(dao.findAllPersonsInGroup(8).contains(P));
	}
	
	/**
	 *	Test suppression d'une personnne qui existe dans un groupe 
	 *	qui n'existe pas
	 */
	@Test
	public void testRemoveExistantPersonInUnexistantGroup() {
		// on creer une personne dans l'annuaire
		P.setId(9);
		dao.savePerson(P);
		dao.addPersonInGroup(9, 999);
		dao.removePersonFromGroup(9, 999);
		
		List<Person> list = dao.findAllPersonsInGroup(999);
		assertNull(list);
	}
	
	/**
	 *	Test suppression d'une personnne qui n'existe pas dans un groupe 
	 *	qui existe
	 */
	@Test
	public void testRemoveUnexistantPersonInExistantGroup() {
		// on creer un groupe dans l'annuaire
		G.setId(9);
		dao.saveGroup(G);
		dao.addPersonInGroup(999, 9);
		List<Person> listBefore = dao.findAllPersonsInGroup(G.getId());
		dao.removePersonFromGroup(999, 9);
		List<Person> listAfter = dao.findAllPersonsInGroup(G.getId());
		
		assertEquals(listBefore, listAfter);
	}
	
	/**
	 *	Test suppression d'une personnne qui n'existe pas dans un groupe 
	 *	qui n'existe pas
	 */
	@Test
	public void testRemoveUnexistantPersonInUnExistantGroup() {
		dao.addPersonInGroup(999, 999);
		dao.removePersonFromGroup(999, 999);
		
		List<Person> list = dao.findAllPersonsInGroup(999);
		assertNull(list);
	}
	
	/**
	 *	Test recherche des personnes dans un groupe 
	 *	qui existe mais qui est vide
	 */
	@Test
	public void testFindPersonsGroupWithEmptyData() {
		// on creer un groupe dans l'annuaire
		G.setId(10);
		dao.saveGroup(G);
		List<Person> list = dao.findAllPersonsInGroup(10);
		assertTrue(list.isEmpty());
	}
	
	/**
	 *	Test recherche des personnes dans un groupe 
	 *	qui existe est qui n'est pas vide
	 */
	@Test
	public void testFindPersonsGroupWithData() {
		// on creer une personne dans l'annuaire
		P.setId(11);
		dao.savePerson(P);
		// on creer un groupe dans l'annuaire
		G.setId(11);
		dao.saveGroup(G);
		// on ajoute la personne au nouveau groupe
		dao.addPersonInGroup(11, 11);
		List<Person> List = dao.findAllPersonsInGroup(11);
		assertTrue(List.contains(P));
	}
	
	/**
	 *	Test recherche des personnes dans un groupe 
	 *	qui n'existe pas
	 */
	@Test
	public void testFindPersonsInUnexistantGroup() {
		List<Person> list = dao.findAllPersonsInGroup(999);
		assertNull(list);
	}
}