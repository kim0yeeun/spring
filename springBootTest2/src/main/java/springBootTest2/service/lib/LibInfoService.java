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
public class LibInfoService {
	@Autowired
	LibMapper libMapper;
	public void execute(Model model, String libNum, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		LibDTO dto = libMapper.selectOne(libNum);
		model.addAttribute("memId",authInfo.getUserId());
		model.addAttribute("dto", dto);
	}
}
