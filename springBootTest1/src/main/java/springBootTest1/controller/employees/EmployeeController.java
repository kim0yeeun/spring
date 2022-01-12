package springBootTest1.controller.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootTest1.command.EmployeeCommand;
import springBootTest1.service.employees.EmployeeWriteService;

@Controller
@RequestMapping("emp") // 앞 주소 고정 이므로 /emp/employeeList할 필요없음 
public class EmployeeController {
	@Autowired
	// 서비스는 컨트롤러의 의존객체다.
	EmployeeWriteService employeeWriteService;
	// 인덱스의 주소를 requestmapping 으로 찾음 
	// 아래에는 실행 될 함수를 써주면 된다. 
	@RequestMapping("employeeList")
	public String emplist() {
		return "thymeleaf/employees/empList";
	}
	@RequestMapping("employeeForm")
	public String empForm() {
		return "thymeleaf/employees/empForm";
	}
	@RequestMapping("employeeWrite")
	public String empWrite(EmployeeCommand employeeCommand, Model model) {
		// Controller->Service
		// servlet request로 받는 대신에 command 사용
		String path = employeeWriteService.execute(employeeCommand,model);
		return path;
	}
}
