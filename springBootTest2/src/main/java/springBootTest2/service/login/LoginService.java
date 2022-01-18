package springBootTest2.service.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.AuthInfo;
import springBootTest2.mapper.LoginMapper;

@Component
@Service
public class LoginService {
	@Autowired
	LoginMapper loginMapper;
	public String execute(String id, String pw, HttpSession session, Model model) {
		String path = "thymeleaf/index"; // 아이디가 존재하지 않을경우 index
		AuthInfo authInfo = loginMapper.loginSelect(id);
		if(authInfo != null) { // id가 존재할경우
			if(authInfo.getUserPw().equals(pw)) { //db로부터 갖고온 pw와 pw가 같으면
				session.setAttribute("authInfo", authInfo);
				path = "redirect:/"; // session이 있을 때만 바뀜 
			}else {
				model.addAttribute("err_pw", "비밀번호가 틀렸습니다.");
			}
		}else {
			model.addAttribute("err_id", "아이디가 존재하지 않습니다.");
		}
		return path;
	}
}
