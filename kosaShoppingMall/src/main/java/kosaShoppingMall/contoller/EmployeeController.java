package kosaShoppingMall.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.service.employee.EmployeeDeleteService;
import kosaShoppingMall.service.employee.EmployeeInfoService;
import kosaShoppingMall.service.employee.EmployeeListService;
import kosaShoppingMall.service.employee.EmployeeModifyService;
import kosaShoppingMall.service.employee.EmployeeUpdateService;
import kosaShoppingMall.service.employee.EmployeeWriteService;

@Controller
@RequestMapping("emp")
public class EmployeeController {
	@Autowired
	EmployeeWriteService employeeWriteService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeInfoService employeeInfoService;
	@Autowired
	EmployeeModifyService employeeModifyService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	@ModelAttribute
	EmployeeCommand setEmployeeCommand() {
		return new EmployeeCommand();
	}
	// empjoin 에 command 적어주지 않아도 된다. 
	// public String empJoin(Model model){
	// model.addAttribute("employeeCommand", new EmployeeCommand());  이거나
	// EmployeeCommad employcommad 라고 하는거나 같다! 
	
	
	@RequestMapping("empDelete")
	public String empDelete(@RequestParam(value="empId") String empId, 
			@RequestParam(value="empPw") String empPw, Model model) {
		String path = employeeDeleteService.execute(empId, empPw, model);
		return path;
	}
	@RequestMapping(value="empUpdate", method = RequestMethod.POST)
	public String empUpdate(EmployeeCommand employeeCommand,BindingResult result, Model model) {
		// BindindResult : 에러를 확인하고 에러 메세지 전달@ 항상  command뒤에 써야하며 command에 에러가 저장되어 전달
		String path = employeeUpdateService.execute(employeeCommand,result, model);
		return path;
	}
	@RequestMapping("empModify")
	public String empModify(@RequestParam(value="empId") String empId,
			@RequestParam(value="empPw") String empPw, Model model) {
		String path = employeeModifyService.execute(empId, empPw, model);
		return path;
	}
	@RequestMapping("empInfo")
	public String empInfo(@RequestParam(value="id") String empId, Model model) {
		employeeInfoService.execute(empId, model);
		return "thymeleaf/employee/empInfo";
	}
	@RequestMapping("empList")
	public String empList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/empList";
	}
	@RequestMapping("empForm")
	public String empJoin() {
		return "thymeleaf/employee/empForm";
	}
	// get방식 : 폼으로 redirect 
	@RequestMapping(value="empWrite", method = RequestMethod.GET)
	public String empWrite1() {
		return "redirect:empForm";
	}
	// 로그인페이지
	@RequestMapping(value="empWrite", method = RequestMethod.POST)
	public String empWrite(@Validated EmployeeCommand employeeCommand, 
			BindingResult result) { // 유효성 검사 
		if(result.hasErrors()) {
			return "thymeleaf/employee/empForm";
		}
		if (!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			result.rejectValue("empPwCon", "employeeCommand.empPwCon", "비밀번호 확인이 다릅니다.");
			return "thymeleaf/employee/empForm";
		}
		employeeWriteService.execute(employeeCommand);
		return "redirect:empList";
	}
}
