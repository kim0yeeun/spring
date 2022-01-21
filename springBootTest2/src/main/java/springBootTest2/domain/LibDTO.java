package springBootTest2.domain;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

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
	String originalFileName;
	String storeFileName;
	String fileSize;
}
