package springBootTest2.service.board2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.mapper.Board2Mapper;

@Component
@Service
public class Board2DeleteService {
	@Autowired
	Board2Mapper board2Mapper;
	public void execute (String boardNum) {
		board2Mapper.Delete(boardNum);
		
	}
}
