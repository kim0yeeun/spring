package kosaShoppingMall.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.EmployeeCommand;
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
	
	@ModelAttribute
	MemberCommand setmemberCommand() {
		return new MemberCommand();
	}
	
	
	@RequestMapping("memDelete")
	public String memDelete(@RequestParam(value="memNum") String memNum, Model model) {
		memberDeleteService.execute(memNum, model);
		return "thymeleaf/member/memDel";
		// ajax를 사용하기 위해 nextpage를 만든 것 @ 중간에 memDel 끼고 @@ memList로 보냄
		//		return "redirect:memList";
	}
	@RequestMapping(value="memModify", method = RequestMethod.POST)
	public String memUpdate(@Validated MemberCommand memberCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memModify";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:memDetail/"+memberCommand.getMemNum();
	}
	@RequestMapping(value="memModify", method = RequestMethod.GET)
	public String memModify(@RequestParam(value="memNum")String memNum, Model model) {
		memberModifyService.execute(memNum, model);
		return "thymeleaf/member/memModify";
	}
	@RequestMapping(value="memDetail/{num}")
	public String memDetail(@PathVariable(value="num") String memNum, Model model) {
		// @PathVariable 
		memberInfoService.execute(memNum, model);
		return "thymeleaf/member/memInfo";
	}
	// data를 전달하는 주소 
	@RequestMapping(value="memRegist", method = RequestMethod.POST)
	public String memWrite1(@Validated MemberCommand memberCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/member/memForm";
		}
		//memberautoNumService.execute( memberCommand);
		
		if(!memberCommand.memPwEqualsmemPwCon()) {
			result.rejectValue("memPwCon", "memberCommand.memPwCon", "비밀번호가 틀립니다.");
			return "thymeleaf/member/memForm";
		}else {
			memberWriteService.execute(memberCommand);
			
		}
		return "redirect:memList";
	}
	// form 을 여는 주소 
	// 위에 @@ModelAttribute 로 command 줘야함 
	// regist get방식에서는 command가 없으니까오류남! command를 주자
	@RequestMapping(value="memRegist", method = RequestMethod.GET)
	public String memForm(MemberCommand memberCommand) {
		memberautoNumService.execute(memberCommand);
		return "thymeleaf/member/memForm";
	}
	@RequestMapping("memList")
	public String memList(Model model) {
		memberListService.execute(model);
		return "thymeleaf/member/memList";
	}
}
