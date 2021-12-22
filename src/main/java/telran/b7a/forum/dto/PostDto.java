package telran.b7a.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostDto {

	String id;
	String title;
	String content;
	String author;
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime dateCreated;
	@Singular
	Set<String> tags;
	int likes;
	@Singular
	List<NewCommentDto> comments;

}
