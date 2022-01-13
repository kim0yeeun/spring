package springBootTest2.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.EmployeeCommand;
import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmployeeMapper;


// 얘네가 있어야 controller쪽에서 autowired가 된다. 
@Component 
@Service
public class EmployeeWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmployeeCommand employeeCommand) {
		// request 값 즉, command로 받아온 걸 dto 에 전달
		// dto 맹글맹글 
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		dto.setEmpHireDate(employeeCommand.getEmpHireDate());
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpPw(employeeCommand.getEmpPw());
		dto.setEmpPwCon(employeeCommand.getEmpPwCon());
		dto.setEmpSalary(employeeCommand.getEmpSalary());
		
		System.out.println(employeeCommand.getEmpSalary());
		Integer i = employeeMapper.empInsert(dto);
	    System.out.println(i + "개 행이(가) 삽입되었습니다.");
		// jsp에서는 dao에게 dto를 줬잖아!
		// springBoot에서는 mybatis를 사용하니까 dao대신에 mapper사용 
		// 의존객체이므로 autowired

	}
}
