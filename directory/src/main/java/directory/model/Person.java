package directory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name=Person.TABLE)
@NamedQueries({
	@NamedQuery(
			name="findAllPersons",
			query="select p from Person p"
	),
	@NamedQuery(
			name="findAllPersonsInGroup",
			query="select p from Person p, Group g where g.id = :idGroup"
	)
})
public class Person {
	public static final String TABLE = "PERSON_TABLE";
	public static final String ID = "ID_PERSON";
	public static final String FAMILYNAME = "FAMILY_NAME";
	public static final String FIRSTNAME = "FIRST_NAME";
	public static final String EMAIL = "EMAIL";
	public static final String WEBSITE = "WEBSITE";
	public static final String BIRTHDATE = "BIRTHDATE";
	public static final String PASSWORD = "PASSWORD";
	
	@Id
	@Column(name=ID)
	private long id;
	@Column(name=FAMILYNAME)
	private String familyName;
	@Column(name=FIRSTNAME)
	private String firstName;
	@Column(name=EMAIL)
	private String email;
	@Column(name=WEBSITE)
	private String webSite;
	@Column(name=BIRTHDATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	@Column(name=PASSWORD)
	private String password;
	
	public Person() {}

	public long getId() {
		return id;
	}
	
	public String getFamilyName() {
		return familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public String getWebSite() {
		return webSite;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getPassword() {
		return password;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object p) {
		if(!(p instanceof Person)) {
			return false;
		}
		Person person = (Person) p;
		if(person.getId() != this.id) {
			return false;
		}
		return true;
	}
}
