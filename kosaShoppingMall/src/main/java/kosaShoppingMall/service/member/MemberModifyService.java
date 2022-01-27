package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberModifyService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(@RequestParam(value="memNum") String memNum, Model model) {
		MemberDTO dto = memberMapper.selectOne(memNum);
		model.addAttribute("memberCommand", dto);
	}
}
