package telran.b7a.forum.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class NewCommentDto {

	String user;
	String message;
	LocalDateTime dateCreated;
	int likes;
	
	
}
