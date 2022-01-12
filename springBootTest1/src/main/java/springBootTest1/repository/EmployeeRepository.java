package springBootTest1.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest1.domain.EmployeeDTO;


@Component
// Autowired 를 하기 위해서 추가함 
@Repository(value="springBootTest1.repository.EmployeeRepository")
public interface EmployeeRepository {
	public Integer employeeInsert(EmployeeDTO dto);
	// employeeInsert 메서드 이름은 xml에서의 insert id와 같아야 한다. 
}
