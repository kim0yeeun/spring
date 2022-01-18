package springBootTest2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.service.login.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 로그아웃하려면 세션 날려야한다. 
		session.invalidate();
		return "redirect:/";
	}
	@RequestMapping(value="/loginPro", method=RequestMethod.GET)
	public String home() { // 주소를 치고들어오면 get방식
		// 아이디와 비번을 주소창에 치고 들어올 수 없음
		return "redirect:/";
	}
	@RequestMapping(value="/loginPro", method=RequestMethod.POST)
	// home에서 submit한게 post
	public String loginPro(@RequestParam(value="id") String id,
			@RequestParam(value="pw") String pw, HttpSession session, Model model) {
		// 로그인 되었을 때 path 사용 
		String path = loginService.execute(id, pw, session, model);
		// session을 만들어주면 login상태임 
		// String 을 return 
		// redirect는 다음페이지로 넘어가는 것이므로 request가 날라간당!  
		return path; 
	}
	
}
