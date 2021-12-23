package telran.b7a.accounting.service;

import telran.b7a.accounting.dto.NewAccountDto;
import telran.b7a.accounting.dto.RoleDto;
import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserDto;

public interface AccountingService {
	
	UserDto addAccount(NewAccountDto newAccount);
	
	UserDto loginAccount (String login);
	
	UserDto removeAccount (String login);
	
	UserDto updateAccount (String login, UpdateUserDto newCredential);
	
	RoleDto addRole (String login, String role);
	
	RoleDto removeRole (String login, String role);

	void changePassword(String login, String newPassword);
	

}
