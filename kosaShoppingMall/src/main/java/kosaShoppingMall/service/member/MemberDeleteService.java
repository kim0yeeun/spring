package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	public void execute (String memNum, Model model) {
		MemberDTO dto = memberMapper.selectOne(memNum);
		Integer i = memberMapper.memDelete(memNum);
		model.addAttribute("num", i);
		// model 에 값을 줬다는건 memDel 에 num을 날리겠다는것 
		}
}
