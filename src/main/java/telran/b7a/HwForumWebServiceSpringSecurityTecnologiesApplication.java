package telran.b7a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import telran.b7a.accounting.dao.AccountMongoRepository;
import telran.b7a.accounting.models.UserAccount;

@SpringBootApplication
public class HwForumWebServiceSpringSecurityTecnologiesApplication implements CommandLineRunner {

	@Autowired
	AccountMongoRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HwForumWebServiceSpringSecurityTecnologiesApplication.class, args);
	}
	
	@
	Override
	public void run(String... args) throws Exception {
		if (!accountRepository.existsById("admin")) {
			String password = passwordEncoder.encode("admin");
			UserAccount account = new UserAccount("admin", password, "", "");
			account.getRoles().add("Moderator".toUpperCase());
			account.getRoles().add("Administrator".toUpperCase());
			accountRepository.save(account);
		}
		
	}

}
