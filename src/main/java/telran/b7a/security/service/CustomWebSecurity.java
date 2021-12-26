package telran.b7a.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.AccountMongoRepository;
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
	
	public boolean checkDate(Authentication authentication) {
		UserDetailsConfiguration uds = (UserDetailsConfiguration)authentication.getPrincipal(); 
		return uds.isExpiredPassword(); 
		
	}

}
