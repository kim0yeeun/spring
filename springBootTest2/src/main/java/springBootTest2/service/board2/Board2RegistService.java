package springBootTest2.service.board2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.Board2Command;
import springBootTest2.domain.Board2DTO;
import springBootTest2.mapper.Board2Mapper;

@Component
@Service
public class Board2RegistService {
	@Autowired
	Board2Mapper board2Mapper;
	public void execute(Board2Command board2Command, HttpServletRequest request) {
		Board2DTO dto = new Board2DTO();
		dto.setBoardContent(board2Command.getBoardContent());
		dto.setWriterIp(request.getRemoteAddr());
		dto.setBoardSubject(board2Command.getBoardSubject());
		dto.setBoardWriter(board2Command.getBoardWriter());
		Integer i = board2Mapper.Insert(dto);
	}
}
