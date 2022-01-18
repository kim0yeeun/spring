package springBootTest2.service.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.LibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.LibDTO;
import springBootTest2.mapper.LibMapper;

@Component
@Service
public class LibWriteService {
	@Autowired
	LibMapper libMapper;
	public void execute(LibCommand libCommand, HttpSession session, HttpServletRequest request) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String memId = authInfo.getUserId();
		LibDTO dto = new LibDTO();
		dto.setLibWriter(libCommand.getLibWriter());
		dto.setLibSubject(libCommand.getLibSubject());
		dto.setLibContent(libCommand.getLibContent());
		dto.setMemId(memId);
		dto.setLibPw(libCommand.getLibPw());
		dto.setIpAddr(request.getRemoteAddr());
		
		Integer i = libMapper.LibInsert(dto);
		System.out.println(i+"개의 행이(가) 삽입되었습니다.");
	}
}