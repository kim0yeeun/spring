package springBootTest2.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.Board2DTO;
import springBootTest2.domain.BoardDTO;

@Component
@Repository(value="springBootTest2.mapper.Board2Mapper")
public interface Board2Mapper {
	public Integer Insert(Board2DTO dto);
	public List<Board2DTO> selectAll();
	public Board2DTO selectOne(String boardNum);
	public Integer Update(Board2DTO dto);
	public Integer Delete(String boardNum);
}
