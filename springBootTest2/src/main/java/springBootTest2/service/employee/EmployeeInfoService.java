package springBootTest2.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeInfoService {
	// 서비스의 의존객체는 mapper
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute (String empNum, Model model) {
		EmployeeDTO dto = employeeMapper.selectOne(empNum);
		model.addAttribute("dto", dto);
	}
}
