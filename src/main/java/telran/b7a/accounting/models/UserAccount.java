package telran.b7a.accounting.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Getter
@EqualsAndHashCode(of = "login")
public class UserAccount {
	private static final long NUMBER_OF_DAYS = 30;
	@Id
	String login;
	@Setter
	String password;
	@Setter
	String firstName;
	@Setter
	String lastName;
	@Setter
	@Singular
	Set<String> roles;
	@Setter
	LocalDate expPassworDate;
	
	public UserAccount() {
		roles = new HashSet<>(Arrays.asList("USER"));
		roles.add("USER");
		expPassworDate = LocalDate.now().plusDays(NUMBER_OF_DAYS);
	}

	public UserAccount(String login, String password, String firstName, String lastName) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		roles = new HashSet<String>();
		roles.add("USER");
	}

	public void changePassword(String encodePassword) {
		this.password = encodePassword;
		expPassworDate = LocalDate.now().plusDays(NUMBER_OF_DAYS);
		
	}

}
