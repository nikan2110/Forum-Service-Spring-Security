package telran.b7a.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import telran.b7a.accounting.dao.AccountMongoRepository;
import telran.b7a.accounting.dto.NewAccountDto;
import telran.b7a.accounting.dto.RoleDto;
import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserDto;
import telran.b7a.accounting.dto.exceptions.AccountNotFoundException;
import telran.b7a.accounting.dto.exceptions.UserExistException;
import telran.b7a.accounting.models.UserAccount;

@Component
public class AccountingServicImpl implements AccountingService {

	AccountMongoRepository accountMongoRepository;
	ModelMapper modelMapper;
	PasswordEncoder passwordEncoder;

	@Autowired
	public AccountingServicImpl(AccountMongoRepository accountMongoRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.accountMongoRepository = accountMongoRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDto addAccount(NewAccountDto newAccount) {
		if (accountMongoRepository.existsById(newAccount.getLogin())) {
			throw new UserExistException(newAccount.getLogin());
		}
		UserAccount user = modelMapper.map(newAccount, UserAccount.class);
		String password = passwordEncoder.encode(newAccount.getPassword());
		user.setPassword(password );
		accountMongoRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto loginAccount(String login) {
		UserAccount user = find(login);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto removeAccount(String login) {
		UserAccount user = find(login);
		accountMongoRepository.delete(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateAccount(String login, UpdateUserDto newCredential) {
		UserAccount user = find(login);
		if (newCredential.getFirstName() != null) {
			user.setFirstName(newCredential.getFirstName());
		}
		if (newCredential.getLastName() != null) {
			user.setLastName(newCredential.getLastName());
		}
		accountMongoRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public RoleDto addRole(String login, String role) {
		UserAccount user = find(login);
		user.getRoles().add(role.toUpperCase());
		accountMongoRepository.save(user);
		return modelMapper.map(user, RoleDto.class);
	}

	@Override
	public RoleDto removeRole(String login, String role) {
		UserAccount user = find(login);
		user.getRoles().remove(role.toUpperCase());
		accountMongoRepository.save(user);
		return modelMapper.map(user, RoleDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount user = find(login);
		user.setPassword(passwordEncoder.encode(newPassword));
		accountMongoRepository.save(user);
	}

	private UserAccount find(String login) {
		return accountMongoRepository.findById(login).orElseThrow(() -> new AccountNotFoundException(login));
	}

}
