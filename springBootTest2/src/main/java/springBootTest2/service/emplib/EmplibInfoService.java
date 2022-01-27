package springBootTest2.service.emplib;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmplibDTO;
import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmplibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmplibInfoService {
	@Autowired
	EmplibMapper emplibMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(String libNum, Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		EmplibDTO dto = emplibMapper.selectOne(libNum);
		String num = Integer.toString(dto.getEmpNum());
		EmployeeDTO dto1 =  employeeMapper.selectOne(num);
		
		model.addAttribute("userId",authInfo.getUserId());
		model.addAttribute("empId",dto1.getEmpId());
		model.addAttribute("dto", dto);
	}
}
