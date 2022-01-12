package springBootTest1.service.employees;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest1.command.EmployeeCommand;
import springBootTest1.domain.EmployeeDTO;
import springBootTest1.repository.EmployeeRepository;

// autowired를 하기 위해서 추가함 
@Component
// 서비스는 꼭 서비스라고 적어주깅 
@Service
public class EmployeeWriteService {
	// 의존 객체 받아오깅 
	@Autowired
	EmployeeRepository employeeRepository;
	public String execute (EmployeeCommand employeeCommand, Model model) {
		// request 대신에 받을땐 command
		// 값을 html 에 전달할 때는 model사용
		// Model == request 
		// Model 자리에 HttpServletrequest 사용 가능 
		String path = null;
		if (!employeeCommand.isempPwEqualsempPwCon()
				|| employeeCommand.getEmpPw().isEmpty()) { // false일 때 
			// request.setAttribute와 같음
			model.addAttribute("err_pwcon","비밀번호가 일치하지 않습니다.");
			path ="thymeleaf/employees/empForm";
			return path;
		}
		
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		dto.setEmpHireDate(employeeCommand.getEmpHireDate());
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpPw(employeeCommand.getEmpPw());
		dto.setEmpSalary(employeeCommand.getEmpSalary());
		
		employeeRepository.employeeInsert(dto);
		
		path = "redirect:employeeList";
		return path;
	}
}
