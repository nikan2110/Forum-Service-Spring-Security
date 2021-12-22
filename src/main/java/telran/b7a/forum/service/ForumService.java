package telran.b7a.forum.service;

import java.util.List;

import telran.b7a.forum.dto.CommentsInfoDto;
import telran.b7a.forum.dto.DateRequestDto;
import telran.b7a.forum.dto.NewPostDto;
import telran.b7a.forum.dto.PostDto;


public interface ForumService {
	
	PostDto addPost (NewPostDto postInfoDto, String author);
	
	PostDto findPost (String id);
	
	PostDto deletePost (String id);
	
	PostDto updatePost (String id, NewPostDto postInfoDto);
	
	void addLikeToPost(String id);
	
	PostDto addCommentToPost(String id, String author, CommentsInfoDto comment);
	
	List<PostDto> findPostsByAuthor(String author);
	
	List<PostDto> findPostsByTags(List<String> tags);
	
	List<PostDto> findPostsByPeriod(DateRequestDto dateRequestDto);
	
	

}
