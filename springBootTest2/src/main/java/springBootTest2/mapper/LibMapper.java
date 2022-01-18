package springBootTest2.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.LibDTO;

@Component
@Repository(value="springBootTest2.mapper.LibMapper")
public interface LibMapper {
	public Integer LibInsert(LibDTO dto);
	public List<LibDTO> selectAll();

}
