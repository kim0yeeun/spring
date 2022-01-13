package springBootTest2.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmployeeMapper;

// MVC employeelistcontroller 랑 비교 jsp랑 똑같다 
@Component
@Service
public class EmployeeListService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(Model model) {
		List<EmployeeDTO> list = employeeMapper.selectAll();
		model.addAttribute("list", list);
	}
}
