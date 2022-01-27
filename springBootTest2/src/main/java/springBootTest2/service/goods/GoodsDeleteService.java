package springBootTest2.service.goods;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.GoodsCommand;
import springBootTest2.domain.GoodsDTO;
import springBootTest2.mapper.GoodsMapper;

@Component
@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, HttpServletRequest request)
	{
		// 갖고어거
		GoodsDTO dto = goodsMapper.selectOne(goodsNum);
		// 삭제 
		Integer i = goodsMapper.goodsDelete(goodsNum);
		System.out.println(i + "개의 행이(가) 삭제되었습니다.");
		
		// 파일 삭제
		String [] storefileNames = dto.getGoodsImages().split("`");
		if(i>0 && dto.getGoodsImages() != null) {

			String filePath = "/view/goods";
			String fileDir = request.getServletContext().getRealPath(filePath);
			File file = null;
			try {
				for (String fileName : storefileNames) {
					file= new File(fileDir+"/" +fileName);
					if(file.exists()) file.delete();
				}
			}catch (Exception e) {e.printStackTrace();	}
		}
		System.out.println(i+"개의 행이(가) 삭제되었습니다.");
	}
}
