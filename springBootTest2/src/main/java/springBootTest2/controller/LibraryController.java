package springBootTest2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootTest2.command.LibCommand;
import springBootTest2.service.lib.LibListService;
import springBootTest2.service.lib.LibWriteService;

@Controller
@RequestMapping("lib")
public class LibraryController {
	@Autowired
	LibWriteService libWriteService;
	@Autowired
	LibListService libListService;
	@RequestMapping("libWrite")
	public String libWrite(LibCommand libCommand, HttpSession session, HttpServletRequest request) {
		libWriteService.execute(libCommand, session, request);
		return "redirect:libList";
	}
	@RequestMapping("libForm")
	public String libForm() {
		return "thymeleaf/lib/libForm";
	}
	@RequestMapping("libList")
	public String libList(Model model) {
		libListService.execute(model);
		return "thymeleaf/lib/libList";
	}
	
}
