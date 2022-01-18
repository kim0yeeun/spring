package springBootTest2.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("LibDTO")
public class LibDTO {
	Integer libNum;
	String libWriter;
	String libSubject;
	String libContent;
	String memId;
	String ipAddr;
	String libPw;
}
