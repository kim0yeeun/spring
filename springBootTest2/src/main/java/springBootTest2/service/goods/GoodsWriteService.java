package springBootTest2.service.goods;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.GoodsCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.GoodsDTO;
import springBootTest2.mapper.EmployeeMapper;
import springBootTest2.mapper.GoodsMapper;

@Component
@Service
public class GoodsWriteService {
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(HttpSession session, GoodsCommand goodsCommand, Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String empId = authInfo.getUserId();
		Integer empNum = employeeMapper.selectId(empId);
		GoodsDTO dto = new GoodsDTO();
		
		// 파일
		String imageTotal = "";
		String filepath = "/view/goods";
		String fileDir = session.getServletContext().getRealPath(filepath);

		for (MultipartFile mf : goodsCommand.getGoodsImages()) {
			String originalFileName= mf.getOriginalFilename();
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			String store = UUID.randomUUID().toString().replace("-", "");
			String storeFileName = store+extension;
			File file = new File (fileDir+"/" +storeFileName);
			try {
				mf.transferTo(file);
			}catch (Exception e) {e.printStackTrace();	}
			imageTotal += storeFileName+"`";
		}
		
			dto.setGoodsImages(imageTotal);
			
		dto.setEmpNum(empNum);
		dto.setGoodsCompany(goodsCommand.getGoodsCompany());
		dto.setGoodsDate(goodsCommand.getGoodsDate());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		dto.setGoodsQty(goodsCommand.getGoodsQty());
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		Integer i = goodsMapper.goodsinsert(dto);
		
		System.out.println(i + "개의 행이(가) 삽입되었습니다.");
		model.addAttribute("dto", dto);
	}
}
