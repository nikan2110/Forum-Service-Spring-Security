package telran.b7a.accounting.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class AccountNotFoundException extends RuntimeException {/**
	 * 
	 */
	private static final long serialVersionUID = 5635370307230854544L;
	public AccountNotFoundException(String login) {
		super("Account " + login + " not exist");
	}


}
