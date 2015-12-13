package directory.web;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import directory.model.Person;

/**
 * This class is Validator of Person class
 * @author Eldoran
 *
 */
@Service
public class PersonValidator implements Validator {

	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String WEBSITE_PATTERN = 
		"^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*"
		+ "[-a-zA-Z0-9+&@#/%=~_|]";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Person person = (Person) target;
		
		if(person.getId() <= 0L) {
			errors.rejectValue("id", "person.id.invalidID");
		}
		
		if(person.getFamilyName() == null || person.getFamilyName().isEmpty()) {
			errors.rejectValue("familyName", "person.familyName");
		}

		if(person.getFirstName() == null || person.getFirstName().isEmpty()) {
			errors.rejectValue("firstName", "person.firstName");
		}
		
		if(person.getEmail() != null &&
				!person.getEmail().isEmpty() &&
				!person.getEmail().matches(EMAIL_PATTERN)) {
			errors.rejectValue("email", "person.email.invalid");
		}

		if(person.getWebSite() != null &&
				!person.getWebSite().isEmpty() &&
				!person.getWebSite().matches(WEBSITE_PATTERN)) {
			errors.rejectValue("webSite", "person.website.invalid");
		}
		
		if(person.getPassword().isEmpty()) {
			errors.rejectValue("password", "person.password");
		}
		
		for(ObjectError e : errors.getAllErrors()) {
			System.out.println(e.toString());
		}
	}

}
