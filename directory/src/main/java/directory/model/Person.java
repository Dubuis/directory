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
	
	/**
	 * Empty constructor
	 */
	public Person() {}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param webSite the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @param password the password to set
	 */
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
