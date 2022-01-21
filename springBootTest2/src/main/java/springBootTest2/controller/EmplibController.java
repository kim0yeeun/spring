package springBootTest2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.EmplibCommand;
import springBootTest2.service.emplib.EmplibDeleteService;
import springBootTest2.service.emplib.EmplibInfoService;
import springBootTest2.service.emplib.EmplibListService;
import springBootTest2.service.emplib.EmplibModifyService;
import springBootTest2.service.emplib.EmplibUpdateService;
import springBootTest2.service.emplib.EmplibWriteService;

@Controller
@RequestMapping(value="emplib")
public class EmplibController {
	@Autowired
	EmplibWriteService emplibWriteService;
	@Autowired
	EmplibListService emplibListService;
	@Autowired
	EmplibInfoService emplibInfoService;
	@Autowired
	EmplibModifyService emplibModifyService;
	@Autowired
	EmplibUpdateService emplibUpdateService;
	@Autowired
	EmplibDeleteService emplibDeleteService;

	@RequestMapping("emplibDelete")
	public String emplibDelete(@RequestParam(value="libNum") String libNum, 
			@RequestParam(value="libPw") String libPw, HttpSession session, Model model) {
		String path = emplibDeleteService.execute(libNum, libPw, session, model);
		return path;
	}
	@RequestMapping("emplibUpdate")
	public String emplibUpdate(HttpSession session, EmplibCommand emplibCommand, 
			Model model) {
		String path = emplibUpdateService.execute(session, emplibCommand, model);
		return path;
	}
	@RequestMapping("emplibModify")
	public String emplibModify(@RequestParam(value="num") String libNum,
			@RequestParam(value="libPw") String libPw, Model model, 
			HttpSession session) {
		String path = emplibModifyService.execute(libNum, libPw, model, session);
		return path;
	}
	@RequestMapping("emplibDetail")
	public String emplibDetail(@RequestParam(value="num") String libNum, 
			Model model) {
		emplibInfoService.execute(libNum, model);
		return "thymeleaf/emplib/libDetail";
	}
	@RequestMapping("emplibRegist")
	public String emplibRegist(HttpSession session, EmplibCommand emplibCommand,
			HttpServletRequest request) {
		emplibWriteService.execute(session, emplibCommand, request);
		return "redirect:libList";
	}
	@RequestMapping("emplibWrite")
	public String emplibWrite() {
		return "thymeleaf/emplib/libForm";
	}
	@RequestMapping("libList")
	public String emplibList(Model model) {
		emplibListService.execute(model);
		return "thymeleaf/emplib/libList";
	}
	

}
