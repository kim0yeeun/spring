package springBootTest2.service.emplib;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
@Component
@Service
public class FileDownService {
	public void execute(String sfile, String ofile, 
			HttpServletRequest request, HttpServletResponse response) {
		String filePath = "/view/emplib";
		String fileDir = 
				request.getServletContext().getRealPath(filePath);
		String originalFileName = null;
		try {
			// 한글 안 깨지도록 방지방지 
			originalFileName = URLEncoder.encode(ofile, "utf-8");
		}catch (Exception e) {e.printStackTrace();}
		// text/html 
		response.setContentType("application/octet-stream;charset=utf-8");
		// 파일 이름 설정
		response.setHeader("Content-Disposition", "attachment;filename="+originalFileName+";");
		// 지금 전송하는 데이터는 binary 형태의 data다
		response.setHeader("Content-Transfer-Encoding", "binary");
		// 파일 불러오기
		File file = new File (fileDir+"/" +sfile);
		ServletOutputStream out1 = null;
		FileInputStream fis = null;
		try {
			// response를 이용해서 데이터를 전송하기 위해 output객체를 만들어줌
			out1 = response.getOutputStream();
			fis = new FileInputStream(file);
			// 파일을 outputstram을 통해 전송
			FileCopyUtils.copy(fis, out1); // 웹 브라우저로 전송 
			
			
			
			// 닫고 지웡 
			response.flushBuffer();
			out1.flush();
			out1.close();	
		}catch (Exception e) {e.printStackTrace();}
		finally {if(fis!=null) {try{fis.close();}catch(Exception e) {}}}
	}
}
