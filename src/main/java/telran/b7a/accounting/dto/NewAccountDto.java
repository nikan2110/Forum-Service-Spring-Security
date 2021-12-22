package telran.b7a.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewAccountDto {
	
	String login;
	String password;
	String firstName;
	String lastName;

	
}
