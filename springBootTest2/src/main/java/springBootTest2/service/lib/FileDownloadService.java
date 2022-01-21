package springBootTest2.service.lib;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Configurable
@Service
public class FileDownloadService {
	public void fileDownload(String sfileName, String ofileName, 
			HttpServletRequest request, HttpServletResponse response) {
		String path = "/view/lib";
		String RealPath = request.getServletContext().getRealPath(path);
		// 오리지널 파일이름이 한글인 경우 깨짐방지
		String originalFileName = null;
		try {
			originalFileName = URLEncoder.encode(ofileName, "utf-8");
		} catch (Exception e) {	}
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+originalFileName+";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		// 파일을 갖고오자
		File file = new File(RealPath+"/"+sfileName);
		ServletOutputStream out1 = null;
		FileInputStream fis = null;
		try {
			out1 = response.getOutputStream();
			fis = new FileInputStream(file); // 객체 생성
			// file(fis)을 outputstream(out1) 한테 복붙 -> 웹브라우저로 파일 전송 
			// originalfilename이라는 이름으로 전송! 
			FileCopyUtils.copy(fis, out1);
			// flushBuffer: 버퍼에 저장되는 있는 내용 (out1 )을 클라이언트에 전송
			response.flushBuffer();
			// 버퍼 지우고 닫깅 
			out1.flush();
			out1.close();
		}catch(Exception e){e.printStackTrace();}
		finally {if(fis!=null) {try{fis.close();}catch(Exception e) {}}}
	}

}
