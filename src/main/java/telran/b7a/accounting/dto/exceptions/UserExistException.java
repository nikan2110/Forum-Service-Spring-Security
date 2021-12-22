package telran.b7a.accounting.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.CONFLICT)
@NoArgsConstructor
public class UserExistException extends RuntimeException {/**
	 * 
	 */
	private static final long serialVersionUID = -6878819084308767014L;
	public UserExistException(String login) {
		super("Account " + login + " already exist");
	}
}
