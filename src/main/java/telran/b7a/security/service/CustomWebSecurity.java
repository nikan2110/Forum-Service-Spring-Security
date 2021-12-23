package telran.b7a.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.forum.dao.ForumMongoRepository;
import telran.b7a.forum.models.Post;

@Service("customSecurity")
public class CustomWebSecurity {

	ForumMongoRepository forumMongoRepository;

	@Autowired
	public CustomWebSecurity(ForumMongoRepository forumMongoRepository) {
		this.forumMongoRepository = forumMongoRepository;
	}

	
	public boolean checkPostAuthority(String postId, String userName) {
		Post post = forumMongoRepository.findById(postId).orElse(null);
		return post != null && userName.equals(post.getAuthor());

	}

}
