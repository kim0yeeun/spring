package springBootTest2.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.mapper.GoodsMapper;

@Component
@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum) {
		Integer i = goodsMapper.goodsDelete(goodsNum);
		System.out.println(i + "개의 행이(가) 삭제되었습니다.");
	}
}
