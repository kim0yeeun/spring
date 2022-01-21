package springBootTest2.service.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
