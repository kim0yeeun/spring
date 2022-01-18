package springBootTest2.service.membership;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.MemberCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.MemberDTO;
import springBootTest2.mapper.MemberShipMapper;

@Component
@Service
public class MemberUpdateService {
	@Autowired
	MemberShipMapper memberShipMapper;
	public String execute (MemberCommand memberCommand, HttpSession session) {
		// MemberDTO dto = new MemberDTO(); pw확인으 ㄹ위해 dto 받아오기 mapper
		// id없으니까 session으로 받아오깅 
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String path = null;
		if(authInfo.getUserPw().equals(memberCommand.getMemPw())) {
			// DB의 pw와 command의 pw가 일치한다면 dto에 담기
			MemberDTO dto = new MemberDTO();
			dto.setMemAddr(memberCommand.getMemAddr());
			dto.setMemBirth(memberCommand.getMemBirth());
			dto.setMemEmail(memberCommand.getMemEmail());
			dto.setMemGender(memberCommand.getMemGender());
			dto.setMemName(memberCommand.getMemName());
			dto.setMemNum(memberCommand.getMemNum());
			dto.setMemPhone1(memberCommand.getMemPhone1());
			dto.setMemPhone2(memberCommand.getMemPhone2());
			Integer i = memberShipMapper.memberUpdate(dto);
			System.out.println(i +"개의 행이(가) 수정되었습니다.");
			
			
			path= "redirect:memInfo";
			session.removeAttribute("err_pw");
			//remove의 이유 : 제대로 실행 됐다면 session지우기. session은 브라우저 사라질때까지 남아이스닉아
			}else { // 패스워드가 일치하지 않으면 모델 // 비번 틀리면 redirect하면 안됨 model 값 전달 모태! 
				// html 이나 jsp만 전달 되므로 String 반환값 바꾸고 path 맹글맹글 
				// 네?????????????? model 피료엄서졋다 session으로 하래오 
				session.setAttribute("err_pw", "비밀번호가 틀렸습니다.");
				path = "redirect:memberInfoModify";
			}
		return path;
	}
}
