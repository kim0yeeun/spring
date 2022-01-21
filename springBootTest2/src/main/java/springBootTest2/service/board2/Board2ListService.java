package springBootTest2.service.board2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.command.Board2Command;
import springBootTest2.domain.Board2DTO;
import springBootTest2.mapper.Board2Mapper;

@Component
@Service
public class Board2ListService {
	@Autowired
	Board2Mapper board2Mapper;
	public void execute (Board2Command board2Command, Model model) {
		List<Board2DTO> list = board2Mapper.selectAll();
		model.addAttribute("list", list);
		
	}
}
