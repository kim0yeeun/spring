package kosaShoppingMall.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.service.member.MemberDeleteService;
import kosaShoppingMall.service.member.MemberInfoService;
import kosaShoppingMall.service.member.MemberListService;
import kosaShoppingMall.service.member.MemberModifyService;
import kosaShoppingMall.service.member.MemberUpdateService;
import kosaShoppingMall.service.member.MemberWriteService;
import kosaShoppingMall.service.member.MemberautoNumService;

@Component
@RequestMapping("mem")
public class MemberController {
	@Autowired
	MemberWriteService memberWriteService;
	@Autowired
	MemberautoNumService memberautoNumService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberModifyService memberModifyService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;

	@RequestMapping("memDelete")
	public String memDelete(@RequestParam(value="memNum") String memNum) {
		memberDeleteService.execute(memNum);
		return "redirect:memList";
	}
	@RequestMapping(value="memModify", method = RequestMethod.POST)
	public String memUpdate(MemberCommand memberCommand) {
		memberUpdateService.execute(memberCommand);
		return "thymeleaf/member/memInfo";
	}
	@RequestMapping(value="memModify", method = RequestMethod.GET)
	public String memModify(@RequestParam(value="memNum")String memNum, Model model) {
		memberModifyService.execute(memNum, model);
		return "thymeleaf/member/memModify";
	}
	@RequestMapping("memDetail")
	public String memDetail(@RequestParam(value="memNum") String memNum, Model model) {
		memberInfoService.execute(memNum, model);
		return "thymeleaf/member/memInfo";
	}
	// data를 전달하는 주소 
	@RequestMapping(value="memRegist", method = RequestMethod.POST)
	public String memWrite1(MemberCommand memberCommand, Model model) {
		memberautoNumService.execute(model);
		if(!memberCommand.getMemPw().equals(memberCommand.getMemPwCon())) {
			model.addAttribute("memPwCon", "비밀번호가 틀립니다.");
			return "thymeleaf/member/memForm";
		}else {
			memberWriteService.execute(memberCommand);
			
		}
		return "redirect:memList";
	}
	// form 을 여는 주소 
	@RequestMapping(value="memRegist", method = RequestMethod.GET)
	public String memForm(Model model) {
		memberautoNumService.execute(model);
		return "thymeleaf/member/memForm";
	}
	@RequestMapping("memList")
	public String memList(Model model) {
		memberListService.execute(model);
		return "thymeleaf/member/memList";
	}
}
