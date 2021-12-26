package telran.b7a.security.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.AccountMongoRepository;
import telran.b7a.accounting.models.UserAccount;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	AccountMongoRepository accountMongoRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserAccount account = accountMongoRepository.findById(login).orElseThrow(() -> new UsernameNotFoundException(login));
		String[] roles = account.getRoles().stream()
				.map(r -> "ROLE_" + r.toUpperCase())
				.toArray(String[]::new);
	
		boolean passwordExpired = LocalDate.now().isBefore(account.getExpPassworDate());
		
		return new UserDetailsConfiguration(login, account.getPassword(), AuthorityUtils.createAuthorityList(roles), passwordExpired);
	}
	
}
