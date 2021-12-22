package telran.b7a.forum.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Post {
	@Id
	String id;
	@Setter
	String title;
	@Setter
	String content;
	@Setter
	String author;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime dateCreated = LocalDateTime.now();
	@Setter
	@Singular
	Set<String> tags;
	int likes;
	@Singular
	List<Comment> comments = new ArrayList<Comment>();

	public Post(String title, String content, String author, Set<String> tags) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.tags = tags;

	}

	public void addLike() {
		likes++;
	}
	
	

}
