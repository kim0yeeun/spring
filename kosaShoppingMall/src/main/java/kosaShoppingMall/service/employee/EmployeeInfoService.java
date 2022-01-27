package kosaShoppingMall.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeInfoService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(String empId, Model model) {
		EmployeeDTO dto = employeeMapper.selectOne(empId);
		model.addAttribute("employeeCommand", dto);
		
		
	}

}
