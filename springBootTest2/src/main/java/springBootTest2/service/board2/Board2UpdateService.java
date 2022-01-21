package springBootTest2.service.board2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.command.Board2Command;
import springBootTest2.domain.Board2DTO;
import springBootTest2.mapper.Board2Mapper;

@Component
@Service
public class Board2UpdateService {
	@Autowired
	Board2Mapper board2Mapper;
	public void execute(Board2Command board2Command, Model model) {
		Board2DTO dto = new Board2DTO();
		dto.setBoardContent(board2Command.getBoardContent());
		dto.setBoardSubject(board2Command.getBoardSubject());
		dto.setBoardWriter(board2Command.getBoardWriter());
		dto.setBoardNum(board2Command.getBoardNum());
		
		board2Mapper.Update(dto);
		model.addAttribute("dto", dto);
	}

}
