package telran.b7a.security.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.AccountMongoRepository;
import telran.b7a.accounting.models.UserAccount;
import telran.b7a.forum.dao.ForumMongoRepository;
import telran.b7a.forum.models.Post;

@Service("customSecurity")
public class CustomWebSecurity {

	ForumMongoRepository forumMongoRepository;
	AccountMongoRepository accountMongoRepository;

	@Autowired
	public CustomWebSecurity(ForumMongoRepository forumMongoRepository, AccountMongoRepository accountMongoRepository) {
		this.forumMongoRepository = forumMongoRepository;
		this.accountMongoRepository = accountMongoRepository;
	}

	
	public boolean checkPostAuthority(String postId, String userName) {
		Post post = forumMongoRepository.findById(postId).orElse(null);
		return post != null && userName.equals(post.getAuthor());

	}
	
	public boolean checkDate(String login) {
		UserAccount user = accountMongoRepository.findById(login).orElse(null);
		return user != null && LocalDate.now().isBefore(user.getExpPassworDate());
		
	}

}
