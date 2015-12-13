package directory.business.dao;

import java.util.List;

import directory.model.Group;
import directory.model.Person;

public interface IDao {
	/**
	 * Prepares the use of this class.
	 */
	public void init();
	
	/**
	 * Terminates properly to the use of this class.
	 */
	public void close();
	
	/**
	 * Find all persons in database
	 * @return List<Person>
	 */
	public List<Person> findAllPersons();

	/**
	 * Find all persons in Group with groupId in database
	 * @param groupId
	 * @return List<Person>
	 */
	public List<Person> findAllPersonsInGroup(long groupId);
	
	/**
	 * Find person with id in database
	 * @param id
	 * @return Person
	 */
	public Person findPerson(long id);
	
	/**
	 * Add the given parameter person in the database
	 * @param p
	 */
	public void savePerson(Person p);
	
	/**
	 * Removes person with id of database
	 * @param id
	 */
	public void removePerson(long id);
	
	/**
	 * Find all Group in database
	 * @return List<Group>
	 */
	public List<Group> findAllGroups();
	
	/**
	 * Fin group with id in database
	 * @param id
	 * @return Group
	 */
	public Group findGroup(long id);
	
	/**
	 * Add the given parameter group in the database
	 * @param g
	 */
	public void saveGroup(Group g);
	
	/**
	 * Removes group with id of database
	 * @param id
	 */
	public void removeGroup(long id);
	
	/**
	 * Removes person with idPerson from group with idGroup in the database
	 * Care Person and Group must be in database
	 * @param idPerson
	 * @param idGroup
	 */
	public void removePersonFromGroup(long idPerson, long idGroup);
	
	/**
	 * Add person with idPerson in group with idGroup in database
	 * Care Person and Group must be in database
	 * @param idPerson
	 * @param idGroup
	 */
	public void addPersonInGroup(long idPerson, long idGroup);
}