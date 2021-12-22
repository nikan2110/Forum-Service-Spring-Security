package telran.b7a.accounting.models;

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
	
	public UserAccount() {
		roles = new HashSet<>(Arrays.asList("USER"));
		roles.add("USER");
	}

	public UserAccount(String login, String password, String firstName, String lastName) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		roles = new HashSet<String>();
		roles.add("USER");
	}

}
