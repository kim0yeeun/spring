package springBootTest2.service.emplib;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.command.EmplibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmplibDTO;
import springBootTest2.domain.EmployeeDTO;
import springBootTest2.mapper.EmplibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmplibUpdateService {
	@Autowired
	EmplibMapper emplibMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	
	public String execute (HttpSession session, EmplibCommand emplibCommand, 
			Model model) {
		String path ="redirect:emplibDetail?num="+emplibCommand.getLibNum();
		AuthInfo authInfo =(AuthInfo)session.getAttribute("authInfo");
		String libNum = emplibCommand.getLibNum();
		EmplibDTO dto = emplibMapper.selectOne(libNum);
		String empNum = dto.getEmpNum().toString();
		EmployeeDTO dto1 = employeeMapper.selectOne(empNum);	
		
		if (!dto.getLibPw().equals(emplibCommand.getLibPw())) {
			model.addAttribute("dto",dto);
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			path = "thymeleaf/emplib/libModify";
		}else if (!authInfo.getUserId().equals(dto1.getEmpId())){
			model.addAttribute("dto",dto);
			model.addAttribute("err_id","작성자가 아닙니다.");
			path = "thymeleaf/emplib/libModify";
		}else {
			dto.setLibWriter(emplibCommand.getLibWriter());
			dto.setLibSubject(emplibCommand.getLibSubject());
			dto.setLibContent(emplibCommand.getLibContent());
			dto.setLibNum(Integer.parseInt(emplibCommand.getLibNum()));
			Integer i = emplibMapper.emplibUpdate(dto);
			model.addAttribute("dto",dto);	
		}
		
		return path;
	}
}
