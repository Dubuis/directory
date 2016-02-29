package directory.business.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import directory.business.dao.IDao;
import directory.model.Group;
import directory.model.Person;

@SuppressWarnings("unchecked")
@Service
public class JpaDao implements IDao {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	/**
	 * Constructor
	 * @throws ClassNotFoundException 
	 */
	public JpaDao() {
		this("directory");
	}
	
	/**
	 * Constructor with persistenceUnit name
	 * @param persistenceUnitName
	 */
	public JpaDao(String persistenceUnitName) {
		try {
			emf = Persistence.createEntityManagerFactory(persistenceUnitName);
			em = emf.createEntityManager();
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		System.out.println("JpaDao initialization...");
	}
	
	public void close() {
		System.out.println("JpaDao closed !");
	}
	
	public List<Person> findAllPersons() {
		List<Person> persons =
				em.createNamedQuery("findAllPersons").getResultList();
		return (List<Person>) persons;
	}
	
	public List<Person> findAllPersonsInGroup(long groupId) {
		List<Group> g =
				em.createNamedQuery("findAllPersonsInGroup")
				.setParameter("idGroup", groupId).getResultList();
		if(g.isEmpty()) {
			return null;
		}
		List<Person> persons = g.get(0).getList();
		return persons;
	}
	
	public Person findPerson(long id) {
		return em.find(Person.class, id);
	}
	
	public void savePerson(Person p) {
		em.persist(p);
		try {
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}
	
	public void removePerson(long id) {
		Person p = em.find(Person.class, id);
		try {
			em.remove(p);
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public List<Group> findAllGroups() {
		List<Group> groups =
				em.createNamedQuery("findAllGroups").getResultList();
		return (List<Group>) groups;
	}

	public Group findGroup(long id) {
		return em.find(Group.class, id);
	}

	public void saveGroup(Group g) {
		em.persist(g);
		try {
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public void removeGroup(long id) {
		Group g = em.find(Group.class, id);
		try {
			em.remove(g);
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public void removePersonFromGroup(long idPerson, long idGroup) {
		Group g = em.find(Group.class, idGroup);
		Person p = em.find(Person.class, idPerson);
		if(g == null || p == null) {
			return;
		}
		g.getList().remove(p);
		try {
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	public void addPersonInGroup(long idPerson, long idGroup) {
		Person p = findPerson(idPerson);
		Group g = findGroup(idGroup);
		if(p == null || g == null) {
			return;
		}
		g.getList().add(p);
		try {
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}
}
