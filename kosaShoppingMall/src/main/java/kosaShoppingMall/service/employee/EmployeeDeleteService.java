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
	public String execute(String empId, String empPw, Model model) {
		String path ="redirect:empList";
		EmployeeDTO dto = employeeMapper.selectOne(empId);
		model.addAttribute("employeeCommand", dto);
		if(passwordEncoder.matches(empPw, dto.getEmpPw())) {
			model.addAttribute("employeeCommand", dto);
			employeeMapper.empDelete(empId);
		}else {
			model.addAttribute("employeeCommand", dto);
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			path= "thymeleaf/employee/empInfo";
		}
		
	
		return path;
	}

}
