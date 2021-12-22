package telran.b7a.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.CommentsInfoDto;
import telran.b7a.forum.dto.DateRequestDto;
import telran.b7a.forum.dto.PostDto;
import telran.b7a.forum.dto.NewPostDto;
import telran.b7a.forum.service.ForumService;

@RestController
public class ForumController {

	@Autowired
	ForumService forumService;

	@PostMapping("/forum/post/{author}")
	public PostDto addPost(@RequestBody NewPostDto postInfoDto, @PathVariable String author) {
		return forumService.addPost(postInfoDto, author);
	}

	@GetMapping("/forum/post/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return forumService.findPost(id);
	}

	@DeleteMapping("/forum/post/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return forumService.deletePost(id);
	}

	@PutMapping("/forum/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto post) {
		return forumService.updatePost(id, post);

	}
	
	@PutMapping("/forum/post/{id}/like")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addLikeToPost(@PathVariable String id) {
		forumService.addLikeToPost(id);
		
	}
	
	@PutMapping("/forum/post/{id}/comment/{author}")
	public PostDto addCommentToPost(@PathVariable String id, @PathVariable String author, @RequestBody CommentsInfoDto comment) {
		return forumService.addCommentToPost(id, author, comment);
		
	}
	
	@GetMapping("/forum/posts/author/{author}")
	public List<PostDto> findPostsByAuthor (@PathVariable String author) {
		return forumService.findPostsByAuthor(author);
		
	}
	
	@PostMapping("/forum/posts/tags")
	public List<PostDto> findPostsByTags (@RequestBody List<String> tags) {
		return forumService.findPostsByTags(tags);
		
	}
	
	@PostMapping("/forum/posts/period")
	public List<PostDto> findPostsByPeriod(@RequestBody DateRequestDto dateRequestDto) {
		return forumService.findPostsByPeriod(dateRequestDto);
	}

}
