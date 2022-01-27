package springBootTest2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import springBootTest2.service.emplib.FileDownService;

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
	@Autowired
	FileDownService fileDownService;
	
	// 파일 다운 
	@RequestMapping("fileDown")
	public void fileDown (@RequestParam(value="sfile") String sfile,
			@RequestParam(value="ofile") String ofile, 
			HttpServletRequest request, HttpServletResponse response) {
		// 파일객체를 갖고와야하니까 request로 파일이 있는 경로를 갖고옴 (request, session 필요)
		// 웹브라우저에서 파일을 다운로드 하기 위해서 response가 필요 response로 파일을 웹브라우저로 전달 
		fileDownService.execute(sfile, ofile, request, response);
	}
	
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
			Model model, HttpSession session) {
		emplibInfoService.execute(libNum, model, session);
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
