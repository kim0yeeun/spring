package springBootTest2.service.emplib;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

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
			
			String [] storeFileNames = null;
			
			// 파일 
			
			if (dto.getStoreFileName() != null) {
				storeFileNames = dto.getStoreFileName().split("`");
				}
			
			String originalTotal = null;
			String storeTotal = null;
			String fileSizeTotal = null;
			
			String filePath = "/view/emplib";
			String fileDir = session.getServletContext().getRealPath(filePath);
			
		
			// 파일 업데이트 (새로운 파일 저장 후 원래 파일 삭제)
			// 파일 저장
			for(MultipartFile mf : emplibCommand.getReport()) {
				String ofile = mf.getOriginalFilename();
				String extension = ofile.substring(ofile.lastIndexOf("."));
				String sfile = UUID.randomUUID().toString().replace("-","");
				String storeFileName = sfile+extension;
				String fileSize = Long.toString(mf.getSize());
				File file = new File (fileDir+"/"+storeFileName);
				try {
					mf.transferTo(file);
				} catch (Exception e) {e.printStackTrace();	}
				originalTotal += ofile+"`";
				storeTotal += storeFileName+"`";
				fileSizeTotal = fileSize+"`";
			}
			dto.setFileSize(fileSizeTotal);
			dto.setOriginalFileName(originalTotal);
			dto.setStoreFileName(storeTotal);
			
			Integer i = emplibMapper.emplibUpdate(dto);
			
			if(i >0 ) {
				for(String fileName : storeFileNames) {
					File file = new File(fileDir + "/" + fileName);
					if(file.exists())file.delete();
				}
			}
		System.out.println("삭제");
				
			
			model.addAttribute("dto",dto);	
		}
		
		return path;
	}
}
