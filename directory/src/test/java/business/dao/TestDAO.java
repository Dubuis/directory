package business.dao;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import directory.business.dao.IDao;
import directory.business.dao.jpa.JpaDao;
import directory.model.Group;
import directory.model.Person;

public class TestDAO {
	
	private static IDao dao = new JpaDao();
	private static Person P;
	private static Group G;
	
	@BeforeClass
	public static void beforeAll()  {
		dao.init();
	}

	@AfterClass
	public static void afterAll() {
		dao.removeGroup(G.getId());
		dao.removePerson(P.getId());
		dao.close();
	}

	@Before
	public void setUp() throws ParseException {
		P = new Person();
		P.setId(1002636L);
		P.setPassword("0202018687E");
		P.setFamilyName("TRIELLI");
		P.setFirstName("Anthony");
		Date D = new SimpleDateFormat("yyyy-MM-dd").parse("1991-10-12");
		P.setBirthDate(D);
		P.setEmail("trielli.anthony@hotmail.fr");
		P.setWebSite("www.univ-amu.fr");
		
		// une instance Groupe pour les tests
		G = new Group();
		G.setId(999);
		G.setName("Integration Systeme logicel 2015");
		
		List<Person> list = new ArrayList<Person>();
		list.add(P);
		G.setList(list);
		
		dao.savePerson(P);
		dao.saveGroup(G);
	}

	@After
	public void tearDown() {
	}

	/**
	 *	Test d'ajout d'une nouvelle personne
	 *	en base de données vierge
	 */
	@Test
	public void testAjoutPerson() {
		assertNotNull(dao.findPerson(P.getId()));
	}
	
	/**
	 *	Test d'ajout d'un nouveau groupe
	 *	en base de données vierge
	 */
	@Test
	public void testAjoutGroupe() {
		assertNotNull(dao.findGroup(G.getId()));
	}

	/**
	 *	Test sur le listing de personne issue de la BD sans paramètre
	 */
	@Test
	public void testListePerson() {
		List<Person> L = dao.findAllPersons();
		boolean bool = false;
		for(Person p : L) {
			bool |= (p.getId() == P.getId());
		}
		Assert.assertTrue(bool);
	}
	
	/**
	 *	Test sur le listing de personne en recherchant un étudiant portant un nom précis
	 */
	@Test
	public void testListePersonParametreNom() {
		Person p = dao.findPerson(1002636L);
		Assert.assertNotNull(p);
	}
	
	/**
	 *	Test sur le listing de personne en recherchant tous les étudiant appartenant à un groupe
	 */
	@Test
	public void testListePersonParametreGroupe() {
		List<Person> L = dao.findGroup(G.getId()).getList();
		boolean bool = false;
		for(Person p : L) {
			bool |= (p.getId() == P.getId());
		}
		Assert.assertTrue(bool);
	}
	
	/**
	 *	Test sur la liste des groupe
	 */
	@Test
	public void testListeGroupe() {
		List<Group> L = dao.findAllGroups();
		boolean bool = false;
		for(Group g : L){
			bool |= (g.getId() == G.getId());
		}
		Assert.assertTrue(bool);
	}
}