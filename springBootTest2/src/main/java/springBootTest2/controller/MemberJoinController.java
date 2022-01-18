package springBootTest2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.MemberCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.service.membership.MemberDropService;
import springBootTest2.service.membership.MemberInfoService;
import springBootTest2.service.membership.MemberJoinService;
import springBootTest2.service.membership.MemberPasswordService;
import springBootTest2.service.membership.MemberUpdateService;

@Controller
@RequestMapping("membership")
public class MemberJoinController {
	@Autowired
	MemberJoinService memberJoinService ;
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberUpdateService memberUpdateService; 
	@Autowired
	MemberPasswordService memberPasswordService;
	@Autowired
	MemberDropService memberDropService;
	
	@RequestMapping(value="memberDropOk", method=RequestMethod.POST)
	public String memberDropOk(@RequestParam(value="memPw") String memPw,
			HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if (authInfo.getUserPw().equals(memPw)) {
			memberDropService.execute(authInfo.getUserId());
			//아이디만 있으면 drop가능 
			return "redirect:/logout";
		}else {
			model.addAttribute("err_pw", "비밀번호가 일치하지 않습니다.");
			return "thymeleaf/membership/memberDrop";
		}
	}
	@RequestMapping("memberDrop")
	public String memberDrop() {
		return "thymeleaf/membership/memberDrop";
	}
	
	@RequestMapping(value="memberPassModify", method = RequestMethod.POST)
	public String memberPassModify(@RequestParam(value="memPw") String oldPw, 
			@RequestParam(value="newMemPw") String newPw, 
			@RequestParam(value="newMemPwCon") String newPwCon, 
			HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(authInfo.getUserPw().equals(oldPw)) {
			if (newPw.equals(newPwCon)) {
			// 아이디랑 패스워드만 전달 
			memberPasswordService.execute(authInfo.getUserId(), newPw); 
			// authInfo 를 바꾸면 session에 있는애가 바뀜 
			authInfo.setUserPw(newPw);
			// 비밀번호 바꿨으니까 새로 로그인 하도록 로그아웃시키자 
			// session.invalidate(); 로그아웃하지마고 new패스워드 적용 시키고 memInfo로 돌아가기
			return "redirect:memInfo"; 
		}else {
			model.addAttribute("err_pwCon", "비밀번호 확인이 다릅니다.");
			return "thymeleaf/membership/memberPassCon"; 
			}
		}else {
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			return "thymeleaf/membership/memberPassCon"; 
		}
	}
	@RequestMapping(value="memberPasswordPro", method = RequestMethod.POST)
	public String memberPasswordPro(@RequestParam(value="memPw") String pw,
			HttpSession session, Model model) {
		// session이 내 비번 갖고익서 
		// 서비스 가지 않고 여기서 화긴할개
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(authInfo.getUserPw().equals(pw)) { // 비밀번호가 맞으면 수정페이지
			return "thymeleaf/membership/memberPassCon";
		}else {
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			return "thymeleaf/membership/memberPass"; //  다르면 다시 비번 입력하도록 
			}
		}
	@RequestMapping("memberPass")
	public String memberPass() {
		return "thymeleaf/membership/memberPass";
	}
	@RequestMapping(value="memberInfoUpdate", method=RequestMethod.POST)
	public String memberInfoUpdate(MemberCommand memberCommand, HttpSession session) {
		// html 의 값을 갖고오려면 command맹글어서 쓰자
		// 러그인된 상태에서는 session이 필요하다.
		String path = memberUpdateService.execute(memberCommand,session);
		return path;
	}
	@RequestMapping("memberInfoModify")
	public String memberInfoModify(Model model, HttpSession session) {
		memberInfoService.execute(model, session); // info 꺼 그대로 
		return "thymeleaf/membership/memberModify";
	}
	@RequestMapping("memInfo")
	public String memInfo(Model model, HttpSession session) {
		memberInfoService.execute(model, session);
		// html 에서 DB로 값 넘겨주려면 model필요(redirect하면 안됨), 정보 넘겨야함 session 
		return "thymeleaf/membership/memInfo";
	}
	@RequestMapping("memberAgree")
	public String memberAgree() {
		return "thymeleaf/membership/agree";
	}
	@RequestMapping("memberJoin")
	public String memberJoin() {
		return "thymeleaf/membership/membeForm";
	}
	@RequestMapping("memberWrite")
	public String memberWrite(MemberCommand memberCommand) {
		memberJoinService.execute(memberCommand);
		return "thymeleaf/membership/welcome";
	}
}





