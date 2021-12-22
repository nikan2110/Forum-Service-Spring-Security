package telran.b7a.forum.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.forum.dao.ForumMongoRepository;
import telran.b7a.forum.dto.CommentsInfoDto;
import telran.b7a.forum.dto.DateRequestDto;
import telran.b7a.forum.dto.NewPostDto;
import telran.b7a.forum.dto.PostDto;
import telran.b7a.forum.dto.exceptions.PostNotFoundException;
import telran.b7a.forum.models.Comment;
import telran.b7a.forum.models.Post;
import telran.b7a.forum.service.logging.PostLogger;

@Service
public class ForumServiceImpl implements ForumService {

	static final Logger log = LoggerFactory.getLogger(ForumServiceImpl.class);
	ForumMongoRepository forumMongoRepository;
	ModelMapper modelMapper;

	@Autowired
	public ForumServiceImpl(ForumMongoRepository forumMongoRepository, ModelMapper modelMapper) {
		this.forumMongoRepository = forumMongoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PostDto addPost(NewPostDto postInfoDto, String author) {
		Post post = modelMapper.map(postInfoDto, Post.class);
		post.setAuthor(author);
		forumMongoRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPost(String id) {
		Post post = forumMongoRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = forumMongoRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		forumMongoRepository.delete(post        );
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	@PostLogger
	public PostDto updatePost(String id, NewPostDto postInfoDto) {
		Post post = forumMongoRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		if (postInfoDto.getTitle() != null) {
			post.setTitle(postInfoDto.getTitle());
		}
		if (postInfoDto.getContent() != null) {
			post.setContent(postInfoDto.getContent());
		}
		if (postInfoDto.getTags() != null) {
			post.getTags().addAll(postInfoDto.getTags());
		}
		forumMongoRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	@PostLogger
	public void addLikeToPost(String id) {
		Post post = forumMongoRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.addLike();
		forumMongoRepository.save(post);

	}

	@Override
	public PostDto addCommentToPost(String id, String author, CommentsInfoDto commentsInfoDto) {
		Post post = forumMongoRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		Comment comment = modelMapper.map(commentsInfoDto, Comment.class);
		comment.setUser(author);
		post.getComments().add(comment);
		forumMongoRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> findPostsByAuthor(String author) {
		return forumMongoRepository.findByAuthorIgnoreCase(author)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByTags(List<String> tags) {
		return forumMongoRepository.findByTagsContaining(tags)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByPeriod(DateRequestDto dateRequestDto) {
		LocalDate from = dateRequestDto.getDateFrom();
		LocalDate to = dateRequestDto.getDateTo();
		return forumMongoRepository.findByDateCreatedBetween(from, to)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

}
