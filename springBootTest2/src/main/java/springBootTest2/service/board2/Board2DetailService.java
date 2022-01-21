package springBootTest2.service.board2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.Board2DTO;
import springBootTest2.mapper.Board2Mapper;

@Component
@Service
public class Board2DetailService {
	@Autowired
	Board2Mapper board2Mapper;
	public void execute(String boardNum, Model model) {
		Board2DTO dto = board2Mapper.selectOne(boardNum);
		model.addAttribute("dto", dto);
		
	}
}
