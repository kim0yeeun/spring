package springBootTest2.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EmplibCommand {
	String libWriter;					// input type="text"타입 받음 
	String libSubject;					// text타입 받음
	// date libEnterDate 				// input type="Date" 받음
	String libContent;
	String libPw;
	Integer empNum; 					// input type="number"
	String libNum;
	String ipAddr;
	// 파일 받기위한 multipartfile
	MultipartFile [] report;			// input type="file"
										// input type이 파일이니까 multipartfile로 받는것
	
}
