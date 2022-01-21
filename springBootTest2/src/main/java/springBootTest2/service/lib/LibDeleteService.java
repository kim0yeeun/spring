package springBootTest2.service.lib;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.command.LibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.LibDTO;
import springBootTest2.mapper.LibMapper;

@Component
@Service

public class LibDeleteService {
	@Autowired
	LibMapper libMapper;
	public String execute(HttpSession session, LibCommand libCommand, Model model) {
		String path=null;
		AuthInfo authInfo= (AuthInfo)session.getAttribute("authInfo");
		LibDTO dto = libMapper.selectOne(libCommand.getLibNum());
		model.addAttribute("dto", dto);
		if (!libCommand.getLibPw().equals(dto.getLibPw()) || 
				!dto.getMemId().equals(authInfo.getUserId())) {
			model.addAttribute("err_pw", "작성자가 아니거나 비밀번호가 틀렸습니다.");
			return "thymeleaf/lib/libInfo";
		}else {
			
			Integer i = libMapper.LibDelete(libCommand.getLibNum());
			// DB가 삭제가 됐다면 파일도 지우자!
			if (i>0 && dto.getStoreFileName() != null ) { // && 대신 파일이 없을경우는 ㄴㄴ 파일이null이 아니람녀!
				String [] fileNames = dto.getStoreFileName().split("`");
				String filepath = "/view/lib";
				String fileDir = session.getServletContext().getRealPath(filepath);
				// file경로에 있는 파일 불러오장
				File file = null;
				try {
					for(String fileName : fileNames) {
						// 파일 객체 맹글맹글
						file = new File(fileDir + "/" + fileName);
						if(file.exists()) file.delete(); // 파일이 존재하면 삭제삭제
					}
				}catch(Exception e) {}
			}
			path = "redirect:libList";
		}
		return path;
	}
}
