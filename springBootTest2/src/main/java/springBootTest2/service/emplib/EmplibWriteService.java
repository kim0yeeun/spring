package springBootTest2.service.emplib;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.EmplibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmplibDTO;
import springBootTest2.mapper.EmplibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmplibWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	EmplibMapper emplibMapper;
	public void execute(HttpSession session, EmplibCommand emplibCommand, 
			HttpServletRequest request) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		EmplibDTO dto = new EmplibDTO();
		Integer empNum = employeeMapper.selectId(authInfo.getUserId());
		dto.setEmpNum(empNum);
		dto.setLibContent(emplibCommand.getLibContent());
		dto.setLibPw(emplibCommand.getLibPw());
		dto.setLibSubject(emplibCommand.getLibSubject());
		dto.setLibWriter(emplibCommand.getLibWriter());
		dto.setIpAddr(request.getRemoteAddr());
		
		// 파일 정보를 입력하기 위한 변수 
		String originalTotal="";
		String storeTotal="";
		String filesizeTotal="";
		String filePath="/view/emplib";
		// dir의 경로를 갖고오기위해 request를 사용
		// C:\Users\eclips-workspace\workspace3\springBootTest2\src\main\webapp\view\emplib
		String fileDir = request.getServletContext().getRealPath(filePath);
		System.out.println(fileDir);
		
		if (!emplibCommand.getReport()[0].getOriginalFilename().isEmpty()) {
			for(MultipartFile mf : emplibCommand.getReport()) {
				String oFile = mf.getOriginalFilename();
				String extension = oFile.substring(oFile.lastIndexOf("."));
				// 중복 파일이 있을 때를 대비하여 유일한 이름의 파일명을 생성
				// 죽었다 깨어나도 겹치는 문자가 업대오 - 안되고 공백도 안된대요 
				String store = UUID.randomUUID().toString().replace("-", "");
				String sFile = store + extension;
				System.out.println(sFile);
				File file = new File (fileDir + "/" + sFile);
				String fileSize = Long.toString(mf.getSize());
				try {
					mf.transferTo(file); // originalFileName전송
				}catch (Exception e) {e.printStackTrace();}
				// 여기 까지 저장 
				// 구분자 `추가 
				originalTotal += oFile +"`";
				storeTotal += sFile +"`";
				filesizeTotal += fileSize+"`";
			}
		}
		dto.setOriginalFileName(originalTotal);
		dto.setStoreFileName(storeTotal);
		dto.setFileSize(filesizeTotal);
		// 이제 mapper로 가서 insert 되도록 만들자 
		Integer i = emplibMapper.emplibInsert(dto);
		System.out.println(i + "개의 행이(가) 삽입되었습니다.");
		
	}
}











