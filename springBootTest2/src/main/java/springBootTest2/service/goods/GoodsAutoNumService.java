package springBootTest2.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.mapper.GoodsMapper;

@Component
@Service
public class GoodsAutoNumService {
	@Autowired
	GoodsMapper goodsMapper; 
	public void execute(Model model) {
		String num = goodsMapper.autoNum();
		System.out.println(num);
		model.addAttribute("goodsNum", num);
		
	}
}
