package springBootTest2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.LibCommand;
import springBootTest2.service.lib.FileDownloadService;
import springBootTest2.service.lib.LibDeleteService;
import springBootTest2.service.lib.LibInfoService;
import springBootTest2.service.lib.LibListService;
import springBootTest2.service.lib.LibModifyService;
import springBootTest2.service.lib.LibUpdateService;
import springBootTest2.service.lib.LibWriteService;

@Controller
@RequestMapping("lib")
public class LibraryController {
	@Autowired
	LibWriteService libWriteService;
	@Autowired
	LibListService libListService;
	@Autowired
	LibInfoService libInfoService;
	@Autowired
	LibModifyService libModifyService;
	@Autowired
	LibUpdateService libUpdateService;
	@Autowired
	LibDeleteService libDeleteService;
	@Autowired
	FileDownloadService fileDownloadService;
	

	// 파일 다운로드 libinfo 에 추가 
	@RequestMapping("fileDown")
	public void fileDown(@RequestParam(value="sfile") String sfileName, 
			@RequestParam(value="ofile") String ofileName, 
			HttpServletRequest request, HttpServletResponse response ) {
		// 파일이 있는 경로를 갖고오기 위해 request필요, 전달을 위한 response
		// 반환값 없으므로 void
		fileDownloadService.fileDownload(sfileName, ofileName, request, response);
	}
	// 파일도 삭제해야해! 
	@RequestMapping("libDelete")
	public String libDelete( HttpSession session, LibCommand libCommand, Model model) {
		model.addAttribute("newLineChar", '\n');
		String path = libDeleteService.execute(session, libCommand,  model);
		return path;
	}
	@RequestMapping(value="libModifyOk", method = RequestMethod.POST)
	public String libModifyOk(LibCommand libCommand, HttpSession session,  Model model) {	
		String path= libUpdateService.execute( libCommand, session, model);
		return path;
	}
	@RequestMapping("libModify")
	public String libModify(@RequestParam(value="num") String libNum,
			@RequestParam(value="libPw") String libPw, 
			Model model, HttpSession session) {
		model.addAttribute("newLineChar", '\n');
		String path = libModifyService.execute(libNum, libPw, model, session);
		return path;
	}
	@RequestMapping("libInfo")
	public String libInfo(@RequestParam(value="num") String libNum, 
			Model model, HttpSession session) {
		model.addAttribute("newLineChar", '\n');
		libInfoService.execute(model, libNum, session);
		return "thymeleaf/lib/libInfo";
	}
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
