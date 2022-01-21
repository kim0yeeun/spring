package springBootTest2.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class LibCommand { // html 꺼 
	String libWriter;
	String libSubject;
	String libContent;
	String libPw;
	String libNum;
	MultipartFile [] report;
	// libform 에서 report라는 이름으로 multiple로 넘기니까 배열로 받는다. 
}
