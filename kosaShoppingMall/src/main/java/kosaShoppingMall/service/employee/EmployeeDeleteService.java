package kosaShoppingMall.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeDeleteService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(String empId, String empPw, Model model) {
		EmployeeDTO dto = employeeMapper.selectOne(empId);
		if(passwordEncoder.matches(empPw, dto.getEmpPw())) {
			Integer i = employeeMapper.empDelete(empId);
			model.addAttribute("id", i);
		}else {
			model.addAttribute("id", 0);
		}
	}

}
