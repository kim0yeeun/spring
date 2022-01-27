package kosaShoppingMall.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(EmployeeCommand employeeCommand,BindingResult result, Model model) {
		String path="redirect:empInfo?id="+employeeCommand.getEmpId();
		
		EmployeeDTO dto = employeeMapper.selectOne(employeeCommand.getEmpId());
		if(passwordEncoder.matches(employeeCommand.getEmpPw(), dto.getEmpPw())) {
			dto.setEmpAddr(employeeCommand.getEmpAddr());
			dto.setEmpName(employeeCommand.getEmpName());
			dto.setEmpPhone(employeeCommand.getEmpPhone());
			dto.setEmpId(employeeCommand.getEmpId());
			employeeMapper.empUpdate(dto);
			//model.addAttribute("employeeCommand",dto);
			//contoller로부터 받은 command값은 model로 보내지 않아도 자동으로 html에 전달이 된다. 

			// db로부터 받은 값을 command로 html 에 출력해야 나중에 오류 발생시 command값을 그대로 받기 위해서 
			// 귀찮아도 command라는 이름으로 받는 게 좋다.
		}else {
			//(command에있는 멤버필드명, object(command.필드명), 화면에출력하기 위한 메세지)
			result.rejectValue("empPw", "employeeCommand.empPw" ,"비밀번호가 틀립니다.");
			path="thymeleaf/employee/empUpdate";
		}
		return path;
	}

}
