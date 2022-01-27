package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberautoNumService {
	@Autowired
	MemberMapper memberMapper;
	public void execute (Model model) {
		String autoNum = memberMapper.autoNum();
		model.addAttribute("memNum",autoNum);
	}
}
