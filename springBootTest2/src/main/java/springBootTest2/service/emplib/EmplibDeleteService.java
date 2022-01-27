package springBootTest2.service.emplib;

import java.io.File;

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
public class EmplibDeleteService {
	@Autowired
	EmplibMapper emplibMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public String execute(@RequestParam(value="libNum") String libNum, 
			@RequestParam(value="libPw") String libPw,
			HttpSession session, Model model) {
		String path = "redirect:libList";
		AuthInfo authInfo= (AuthInfo)session.getAttribute("authInfo");
		EmplibDTO dto = emplibMapper.selectOne(libNum);
		String empNum = dto.getEmpNum().toString();
		EmployeeDTO dto1 = employeeMapper.selectOne(empNum);

		model.addAttribute("dto", dto);
		
		if(!dto.getLibPw().equals(libPw)) {
		model.addAttribute("err_pw", "비밀번호가 틀립니다.");
		path = "thymeleaf/emplib/libDetail";
		}else if (!authInfo.getUserId().equals(dto1.getEmpId())) {
			model.addAttribute("err_id", "작성자가 아닙니다.");
			path = "thymeleaf/emplib/libDetail";
		}else { 
			Integer i = emplibMapper.emplibDelete(libNum);
			// db삭제 후 파일도 삭제
			// 파일 삭제 : DB 에서만 삭제하는 게 아니라 view/emplib에 있는 파일도 삭제해줘야한다. 
			if (i>0 && dto.getStoreFileName() != null) {
				// 파일 이름 가져오기
				String [] storeFileNames = dto.getStoreFileName().split("`");
				// 위치 가져오기 dir이 절대경로 갖고오는 것
				String filePath = "/view/emplib";
				String fileDir = session.getServletContext().getRealPath(filePath);
				File file = null;
				try {
					for (String fileName : storeFileNames) {
						file= new File(fileDir+"/" +fileName);
						if(file.exists()) file.delete();
					}
				}catch (Exception e) {e.printStackTrace();	}
			}
			System.out.println(i+"개의 행이(가) 삭제되었습니다.");
		}
		return path;
		
	}

}







