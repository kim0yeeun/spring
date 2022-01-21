package springBootTest2.service.lib;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.LibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.LibDTO;
import springBootTest2.mapper.LibMapper;

@Component
@Service

public class LibUpdateService {
	@Autowired
	LibMapper libMapper; 
	public String execute ( LibCommand libCommand, HttpSession session,  Model model) {
		String path ="redirect:libInfo?num="+libCommand.getLibNum();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		LibDTO dto = libMapper.selectOne(libCommand.getLibNum());
		model.addAttribute("dto", dto);
		
		// 파일이 없을 경우는  xx
		String [] fileNames = null;
		// 업데이트 전 삭제할 파일 이름을 알아야함
		String originalTotal ="";
		String storeTotal ="";
		String fileSizeTotal ="";
		if (dto.getStoreFileName()!= null) {
			fileNames = dto.getStoreFileName().split("`");
			originalTotal = dto.getOriginalFileName();
			storeTotal = dto.getStoreFileName();
			fileSizeTotal = dto.getFileSize();
		}else { // 파일이 없을 경우null이면 안됨
			originalTotal ="";
			storeTotal ="";
			fileSizeTotal ="";
		}
		
		// 파일을 저장하기 위한 경로 확인 후 
		String filePath = "/view/lib";
		// 실제 파일이 있는 디렉토리 realPath
		String fileDir = session.getServletContext().getRealPath(filePath);
		
		
		
		if (!libCommand.getLibPw().equals(dto.getLibPw()) || 
				!dto.getMemId().equals(authInfo.getUserId())) {
			model.addAttribute("err_pw", "작성자가 아니거나 비밀번호가 틀렸습니다.");
			return "thymeleaf/lib/libModify";
		}else {
			dto.setLibContent(libCommand.getLibContent());
			dto.setLibSubject(libCommand.getLibSubject());
			dto.setLibWriter(libCommand.getLibWriter());
			dto.setLibNum(Integer.parseInt(libCommand.getLibNum()));
		

//////////////////////////////////////////////////
//////////////////////////////////////////////////
			
			// 파일을 선택하지 않았을 때는 있던 파일 지우면 앙대! 
			// 즉 비어있지 않을 경우에만 삭제! 
			if (!libCommand.getReport()[0].isEmpty()) {

				originalTotal = "";
				storeTotal = "";
				fileSizeTotal = "";
				
				for(MultipartFile mf : libCommand.getReport()) { //파일 하나씩 받아옴
					// 오리지날 파일 ㅇㅣ름 알아야하니까 불러왕
					String originalFileName = mf.getOriginalFilename();
					// 저장하기 위해서는 확장자가 필요하니까 substring으로 갖고오자
					// originalFileName 의 마지막 . 이 있는 index 이후로 갖고와라 -> 확장자
					String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
					// 파일이름에 들어가지못하는 특수문자 - 를 지웡
					String storeName = UUID.randomUUID().toString().replace("-", "");
					// storeName 이랑 확장자를 붙여서 실제 시스템에 저장하기 위한 이름 만들기
					String storeFileName = storeName + extension;
					String fileSize = Long.toString(mf.getSize());

					File file = new File(fileDir + "/" + storeFileName);
					
					// mf를 이용해서 저장
					try {
						mf.transferTo(file);
						// db에 저장해야하는데 파일이름이 여러개니까 ofile, sfile, size를 묶기 위한 변수가 필여하다 
						// dto 밑에 Total 세개 선언 
					} catch (IOException e) {}
					// 파일 여러개니까 `로 하나씩 묶자 
					originalTotal += originalFileName + "`";
					storeTotal += storeFileName + "`";
					fileSizeTotal += fileSize + "`";
				}
			}
//////////////////////////////////////////////////
//////////////////////////////////////////////////

			// dto에 저장 
			dto.setFileSize(fileSizeTotal);
			dto.setOriginalFileName(originalTotal);
			dto.setStoreFileName(storeTotal);
			// Mapper xml에 update 얘네 세개 추가 
			libMapper.LibUpdate(dto);
			
			Integer i = libMapper.LibUpdate(dto);
			// 파일이 정상적으로 업로드가 된 이후에 파일 삭제
			// 단, 파일이 삭제되지 않았을 경우에는 파일 삭제 안돼안돼
			if (!libCommand.getReport()[0].isEmpty()) {
				if(i>0) {
					File file = null;
					try {
						for(String fileName : fileNames) {
							file = new File(fileDir + "/" + fileName);
							if(file.exists())file.delete();
						}
					}catch(Exception e) {}
				}
			}
			
			System.out.println(i + "개의 행이(가) 수정되었습니다.");
		}
		return path;
	}
}
