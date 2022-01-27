package springBootTest2.domain;

import lombok.Data;

@Data
public class EmplibDTO {
	String libWriter;
	String libSubject;
	String libContent;
	String libPw;
	Integer empNum; 
	Integer libNum;
	String ipAddr;
	// 파일 추가 
	String originalFileName;
	String storeFileName;
	String fileSize;
	
}
