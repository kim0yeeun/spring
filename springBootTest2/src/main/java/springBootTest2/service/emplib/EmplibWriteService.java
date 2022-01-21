package springBootTest2.service.emplib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.EmplibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmplibDTO;
import springBootTest2.mapper.EmplibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmplibWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	EmplibMapper emplibMapper;
	public void execute(HttpSession session, EmplibCommand emplibCommand, 
			HttpServletRequest request) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		EmplibDTO dto = new EmplibDTO();
		Integer empNum = employeeMapper.selectId(authInfo.getUserId());
		dto.setEmpNum(empNum);
		dto.setLibContent(emplibCommand.getLibContent());
		dto.setLibPw(emplibCommand.getLibPw());
		dto.setLibSubject(emplibCommand.getLibSubject());
		dto.setLibWriter(emplibCommand.getLibWriter());
		dto.setIpAddr(request.getRemoteAddr());
		
		Integer i = emplibMapper.emplibInsert(dto);
		System.out.println(i + "개의 행이(가) 삽입되었습니다.");
		
	}
}











