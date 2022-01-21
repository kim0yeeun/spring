package springBootTest2.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Board2Command {
	Integer boardNum;
	String boardWriter;
	String boardSubject;
	String boardContent;
	String writerIp;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date boardDate;
}
