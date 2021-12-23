package telran.b7a.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.accounting.dto.NewAccountDto;
import telran.b7a.accounting.dto.RoleDto;
import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserDto;
import telran.b7a.accounting.service.AccountingService;

@RestController
@RequestMapping("/account")
public class AccountingController {

	AccountingService accountingService;

	@Autowired
	public AccountingController(AccountingService accountingService) {
		this.accountingService = accountingService;
	}

	@PostMapping("/register")
	public UserDto addUser(@RequestBody NewAccountDto newAccount) {
		return accountingService.addAccount(newAccount);
	}

	@PostMapping("/login")
	public UserDto loginUser(Authentication authentication) {
		return accountingService.loginAccount(authentication.getName());
	}

	@DeleteMapping("/user/{user}")
//	@PreAuthorize("#user == authentication.name or hasRole('ADMINISTRATOR')")
	public UserDto removeUser(@PathVariable String user) {
		return accountingService.removeAccount(user);
	}

	@PutMapping("/user/{user}")
//	@PreAuthorize("#user == authentication.name")
	public UserDto updateUser(@PathVariable String user, @RequestBody UpdateUserDto newCredential) {
		return accountingService.updateAccount(user, newCredential);
	}

	@PutMapping("/user/{user}/role/{role}")
	public RoleDto addRole(@PathVariable String user, @PathVariable String role) {
		return accountingService.addRole(user, role);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public RoleDto deleteRole(@PathVariable String user, @PathVariable String role) {
		return accountingService.removeRole(user, role);
	}

	@PutMapping("/password")
	public void changePassword(Authentication authentication, @RequestHeader("X-Password") String newPassword) {
		accountingService.changePassword(authentication.getName(), newPassword);
	}

}
