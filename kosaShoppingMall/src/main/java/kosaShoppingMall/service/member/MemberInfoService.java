package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberInfoService {
	@Autowired
	MemberMapper memberMapper;
	public void execute (String memNum, Model model) {
		MemberDTO dto = memberMapper.selectOne(memNum);
		model.addAttribute("memberCommand", dto);
	}

}
