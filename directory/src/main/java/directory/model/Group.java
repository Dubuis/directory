package directory.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name=Group.TABLE)
@NamedQueries({
	@NamedQuery(
			name="findAllGroups",
			query="SELECT g FROM Group g"
			),
	@NamedQuery(
			name="findAllPersonsInGroup",
			query="SELECT g FROM Group g WHERE g.id = :idGroup"
			)
})
public class Group {
	// Table Group
	public static final String TABLE = "GROUP_TABLE";
	public static final String ID = "ID_GROUP";
	public static final String NAME = "NAME_GROUP";
	// Table Link
	public static final String LINK_TABLE = "LINK_TABLE";
	public static final String ID_GROUP = Group.ID;
	public static final String ID_PERSON = Person.ID;
	
	@Id
	@Column(name=ID)
	private long id;
	@Column(name=NAME)
	private String name;
	
	@ManyToMany
	@JoinTable(name=LINK_TABLE,
			joinColumns=
				@JoinColumn(name=ID_GROUP),
			inverseJoinColumns=
				@JoinColumn(name=ID_PERSON)
			)
	private List<Person> list = new ArrayList<Person>();
	
	/**
	 * Empty constructor
	 */
	public Group() {}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the list
	 */
	public List<Person> getList() {
		return list;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Person> list) {
		this.list = list;
	}
}
