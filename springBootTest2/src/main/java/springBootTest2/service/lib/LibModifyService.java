package springBootTest2.service.lib;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.LibDTO;
import springBootTest2.mapper.LibMapper;

@Component
@Service
public class LibModifyService {
	@Autowired
	LibMapper libMapper;
	public String execute(String libNum, String libPw,
			Model model, HttpSession session) {
		String path = "thymeleaf/lib/libModify";
		// id 가져오기
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		// 자료번호의 글쓴이 id 가져오기
		LibDTO dto = libMapper.selectOne(libNum);
		// 수정 
		model.addAttribute("dto", dto);
		if (!dto.getLibPw().equals(libPw)) {
			model.addAttribute("a", dto.getLibPw()+"------"+libPw);
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			path = "thymeleaf/lib/libInfo";
		}else if (!dto.getMemId().equals(authInfo.getUserId())){
			model.addAttribute("err_id", "아이디가 틀립니다.");
			path = "thymeleaf/lib/libInfo";
		}
		return path;
	}
}
