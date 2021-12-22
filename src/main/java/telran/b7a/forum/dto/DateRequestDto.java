package telran.b7a.forum.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DateRequestDto {
	@JsonFormat(pattern = "[yyyy-MM-dd][dd-MM-yyyy]")
	LocalDate dateFrom;
	@JsonFormat(pattern = "[yyyy-MM-dd][dd-MM-yyyy]")
	LocalDate dateTo;

}
