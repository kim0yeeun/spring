package springBootTest2.service.goods;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.GoodsCommand;
import springBootTest2.domain.GoodsDTO;
import springBootTest2.mapper.GoodsMapper;

@Component
@Service
public class GoodsUpdateService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand,
			HttpSession session) {
		GoodsDTO dto = goodsMapper.selectOne(goodsCommand.getGoodsNum());
		dto.setGoodsCompany(goodsCommand.getGoodsCompany());
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		dto.setGoodsDate(goodsCommand.getGoodsDate());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		dto.setGoodsQty(goodsCommand.getGoodsQty());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		
		// 파일
		String [] storeFileNames = null;
		if (dto.getGoodsImages() != null) {
			storeFileNames = dto.getGoodsImages().split("`");
			}

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
			
		
		Integer i = goodsMapper.goodsUpdate(dto);
		System.out.println(i+"개의 행이(가) 수정되었습니다.");
		
		if(i >0 ) {
			for(String fileName : storeFileNames) {
				File file = new File(fileDir + "/" + fileName);
				if(file.exists())file.delete();
			}
		}
	System.out.println("삭제");
			
		
	}
}
