package springBootTest2.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.Board2Command;
import springBootTest2.service.board2.Board2DeleteService;
import springBootTest2.service.board2.Board2DetailService;
import springBootTest2.service.board2.Board2ListService;
import springBootTest2.service.board2.Board2RegistService;
import springBootTest2.service.board2.Board2UpdateService;

@Controller
@RequestMapping(value="board2")
public class Board2Controller {
	@Autowired
	Board2RegistService board2RegistService;
	@Autowired
	Board2ListService board2ListService;
	@Autowired
	Board2DetailService board2DetailService;
	@Autowired
	Board2UpdateService board2UpdateService;
	@Autowired
	Board2DeleteService board2DeleteService;
	
	@RequestMapping("boardDelete")
	public String boardDelete(@RequestParam(value="num") String boardNum) {
		board2DeleteService.execute(boardNum);
		return "redirect:boardList";
	}
	@RequestMapping("boardModifyOk")
	public String boardUpdate(Board2Command board2Command, Model model) {
		board2UpdateService.execute(board2Command, model);
		return "redirect:boardDetail?num="+board2Command.getBoardNum();
	}
	@RequestMapping("boardModify")
	public String boardModify(@RequestParam(value="num") String boardNum , Model model) {
		board2DetailService.execute(boardNum, model);
		return "thymeleaf/board2/boardModify";
	}
	@RequestMapping("boardDetail")
	public String boardDetail(@RequestParam(value="num") String boardNum, 
			Model model) {
		board2DetailService.execute(boardNum, model);
		return "thymeleaf/board2/boardDetail";
	}
	@RequestMapping("boardRegist")
	public String boardRegist(Board2Command board2Command, HttpServletRequest request) {
		board2RegistService.execute(board2Command, request);
		return "redirect:boardList";
	}
	@RequestMapping("boardWrite")
	public String boardForm() {
		return "thymeleaf/board2/boardForm";
	}
	@RequestMapping("boardList")
	public String boardList(Board2Command board2Command, Model model) {
		board2ListService.execute(board2Command, model);
		return "thymeleaf/board2/boardList";
	}
	
}
