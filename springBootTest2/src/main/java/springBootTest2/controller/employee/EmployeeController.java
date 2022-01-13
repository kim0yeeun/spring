package springBootTest2.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.EmployeeCommand;
import springBootTest2.service.employee.EmployeeDeleteService;
import springBootTest2.service.employee.EmployeeInfoService;
import springBootTest2.service.employee.EmployeeListService;
import springBootTest2.service.employee.EmployeeUpdateService;
import springBootTest2.service.employee.EmployeeWriteService;

@Controller
@RequestMapping("emp")
public class EmployeeController {
	
	// 의존객체 
	@Autowired
	EmployeeWriteService employeeWriteService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeInfoService employeeInfoService;
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	
	@RequestMapping("employeeList")
	// emp/employeeList 라고 하면 -> emp 밑 emp 밑 employeeList 가 됨
	// /emp/employeeList 라고 하면 -> emp 밑 employeeList 가 됨 
	// = employeeList
	// springboot는 contextpath 가 엄더 ! 
	public String empList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/empList";
	}
	// 주소는 무조건 contextpath 다음부터의 주소이다.
	@RequestMapping("employeeRegist")
	public String empForm() {
		return "thymeleaf/employee/empForm";
	}
	@RequestMapping("employeeWrite")
	public String empWrite(EmployeeCommand employeeCommand) {
		employeeWriteService.execute(employeeCommand);
		return "redirect:employeeList";
		// jsp 에서 action.execute(request) request 넘겨줌
		// 의존객체니까 메서드 밖에 선언 부타캐 
	}
	@RequestMapping("employeeInfo")
	public String empInfo(@RequestParam(value="num") String empNum, Model model) {
		// @reparam의 내용이 String empNum = request.getPatameter("num") 가 된다.
		employeeInfoService.execute(empNum, model);
		return "thymeleaf/employee/empDetail";
	}
	@RequestMapping("employeeDelete")
	public String empDelete(@RequestParam(value="num") String empNum){
		employeeDeleteService.execute(empNum);
		return "redirect:employeeList";
	}
	@RequestMapping("employeeModify")
	public String empUpdate(@RequestParam(value="num") String empNum, Model model) {
		employeeInfoService.execute(empNum, model);
		return "thymeleaf/employee/empUpdate";
	}
	@RequestMapping("employeeUpdate")
	public String empUpdateOk(EmployeeCommand employeeCommand) { // command로받아옴
		employeeUpdateService.execute(employeeCommand); // 로 넘겨줌
		return "redirect:employeeInfo?num="+employeeCommand.getEmpNum();
	}
	
	
}
