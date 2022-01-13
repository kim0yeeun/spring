package springBootTest2.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.EmployeeDTO;

// interface로 맹글맹글ㅎㅔ야헤!
@Component
@Repository(value="springBootTest2.mapper.EmployeeMapper")
public interface EmployeeMapper {
	public Integer empInsert (EmployeeDTO dto);
	public List<EmployeeDTO> selectAll();
	public EmployeeDTO selectOne(String empNum);
	public Integer empDelete(String empNum);
	public Integer empUpdate(EmployeeDTO dto);
}
// interface와 같은 이름의 xml파일을 맹글맹글 
