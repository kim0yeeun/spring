package springBootTest2.service.lib;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.LibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.LibDTO;
import springBootTest2.mapper.LibMapper;

@Component
@Service
public class LibWriteService {
	@Autowired
	LibMapper libMapper;
	public void execute(LibCommand libCommand, HttpSession session, HttpServletRequest request) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String memId = authInfo.getUserId();
		LibDTO dto = new LibDTO();
		dto.setLibWriter(libCommand.getLibWriter());
		dto.setLibSubject(libCommand.getLibSubject());
		dto.setLibContent(libCommand.getLibContent());
		dto.setMemId(memId);
		dto.setLibPw(libCommand.getLibPw());
		dto.setIpAddr(request.getRemoteAddr());
		// 파일 컬럼 3개 추가하기 -> 자료실 완성띠
		String originalTotal ="";
		String storeTotal ="";
		String fileSizeTotal ="";
		String fileDir ="/view/lib"; // 파일이 저장된 경로 
		String filePath = request.getServletContext().getRealPath(fileDir);
		
		// 파일이 들어왔다면~! 
		if (!libCommand.getReport()[0].getOriginalFilename().isEmpty()) {
		for (MultipartFile mf : libCommand.getReport()) {
			String originalFile = mf.getOriginalFilename();
			// original에서 확장자만 갖고오겠다. 예) login.html에서 html만!
			// 뒤에 있는 . 위치에서부터 substring해라 -> 확장자만 갖고옴 
			String extension = originalFile.substring(originalFile.lastIndexOf("."));
			// 얘 대신 사용할 이름
			String storeName = UUID.randomUUID().toString().replace("-", "");
							// uniqueid						// 문자열에서 - 사용 불가 
			// uuid로 이름만 만들었으니까 + extension을 해줘야 확장자가 붙는다. 
			String storeFileName = storeName+extension;
			String fileSize = Long.toString(mf.getSize());
			
			originalTotal += originalFile + "`";
			storeTotal += storeFileName + "`";
			fileSizeTotal += fileSize + "`";
			// 여기까지 db에 저장하기위해 파일정보를 가져옴! 이제 저장해야해! 
			// 파일을 webapp/view/lib 에 저장 
			File file = new File (filePath + "/" + storeFileName);
			try {
				mf.transferTo(file); // mf (현재 가고온 파일) 을 file이라는 이름으로 ㅈㅓ장 ㄴ ★ㄱ 
			}catch (Exception e){e.printStackTrace();}
			// 이러케 하믄 파일 저장된거레오 
			// 파일 저장했으니까 이제 db에 저장 ->  mapper	
			}
		}// 여기가지 과로과로 
		// 파일 없이 들어온 경우 그냥 null로 들어가고 오류가 생기지 않는당!  -> 파일 없이 내용만 작성 가능 
		dto.setFileSize(fileSizeTotal);
		dto.setOriginalFileName(originalTotal);
		dto.setStoreFileName(storeTotal);
		// Info 에서 파일 다운받도록 맹글자 
		Integer i = libMapper.LibInsert(dto);
		System.out.println(i+"개의 행이(가) 삽입되었습니다.");
	}
}