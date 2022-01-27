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
public class EmployeeModifyService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute (String empId, String empPw, Model model) {
		EmployeeDTO dto = employeeMapper.selectOne(empId);
		// (갖고온 패스워드(암호화 되지 않은 것), 암호화된 패스워드)
		// 암호화된 패스워든느 matches를 이요해서 비교 
		// 갖고온 패스워드를 암호화하면 같ㅇ느걸 입력해도 달라진다 
		model.addAttribute("employeeCommand" , dto);
		String path="";
		if(passwordEncoder.matches(empPw, dto.getEmpPw())) {
			// validation 하려면 employeeCommand 로 사용해야한다. 
			path="thymeleaf/employee/empUpdate";
		}else {
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			path ="thymeleaf/employee/empInfo";
		}
		
		return path;
	}
}
