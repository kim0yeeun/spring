package springBootTest2.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.GoodsCommand;
import springBootTest2.domain.GoodsDTO;
import springBootTest2.mapper.GoodsMapper;

@Component
@Service
public class GoodsUpdateService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand) {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsCompany(goodsCommand.getGoodsCompany());
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		dto.setGoodsDate(goodsCommand.getGoodsDate());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		dto.setGoodsQty(goodsCommand.getGoodsQty());
		dto.setGoodsNum(goodsCommand.getGoodsNum());

		Integer i = goodsMapper.goodsUpdate(dto);
		System.out.println(i+"개의 행이(가) 수정되었습니다.");
	}
}
