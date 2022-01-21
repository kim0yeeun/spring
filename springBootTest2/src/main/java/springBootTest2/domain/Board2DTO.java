package springBootTest2.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board2DTO {
	Integer boardNum;
	String boardWriter;
	String boardSubject;
	String boardContent;
	String writerIp;
	Date boardDate;
	
}
