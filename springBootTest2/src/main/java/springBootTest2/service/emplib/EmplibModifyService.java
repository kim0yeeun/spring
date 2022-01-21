package springBootTest2.service.emplib;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmplibDTO;
import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmplibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmplibModifyService {
	@Autowired
	EmplibMapper emplibMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public String execute (@RequestParam(value="num") String libNum,
			@RequestParam(value="libPw") String libPw, Model model, 
			HttpSession session) {
		String path="thymeleaf/emplib/libModify";
		AuthInfo authInfo =(AuthInfo)session.getAttribute("authInfo");
		EmplibDTO dto = emplibMapper.selectOne(libNum);
		String empNum = dto.getEmpNum().toString();
		EmployeeDTO dto1 = employeeMapper.selectOne(empNum);
		
		model.addAttribute("dto",dto);		
		if (!dto.getLibPw().equals(libPw)) {
			model.addAttribute("dto",dto);
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			path = "thymeleaf/emplib/libDetail";
		}else if (!authInfo.getUserId().equals(dto1.getEmpId())){
			model.addAttribute("dto",dto);
			model.addAttribute("err_id","작성자가 아닙니다.");
			path = "thymeleaf/emplib/libDetail";
		}
		return path;
	}
}





