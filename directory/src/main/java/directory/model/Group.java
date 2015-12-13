package directory.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name=Group.TABLE)
@NamedQueries({
	@NamedQuery(
			name="findAllGroups",
			query="SELECT g FROM Group g"
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
	private List<Person> list;
	
	public Group() {}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Person> getList() {
		return list;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setList(List<Person> list) {
		this.list = list;
	}
}
