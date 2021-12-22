package telran.b7a.forum.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = { "user", "dateCreated" })
@ToString
public class Comment {
	@Setter
	String user;
	@Setter
	String message;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime dateCreated = LocalDateTime.now();
	int likes;

}
