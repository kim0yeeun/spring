package springBootTest2.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.EmplibDTO;

@Component
@Repository(value="springBootTest2.mapper.EmplibMapper")
public interface EmplibMapper {
	public Integer emplibInsert(EmplibDTO dto);
	public List<EmplibDTO> selectAll();
	public EmplibDTO selectOne(String libNum);
	public Integer emplibUpdate(EmplibDTO dto);
	public Integer emplibDelete(String libNum);
}
