package springBootTest2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.GoodsCommand;
import springBootTest2.service.board.GoodsDeleteService;
import springBootTest2.service.goods.GoodsAutoNumService;
import springBootTest2.service.goods.GoodsDetailService;
import springBootTest2.service.goods.GoodsListService;
import springBootTest2.service.goods.GoodsUpdateService;
import springBootTest2.service.goods.GoodsWriteService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsAutoNumService goodsAutoNumService;
	@Autowired
	GoodsWriteService goodsWriteService;
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	GoodsDetailService goodsDetailService;
	@Autowired
	GoodsUpdateService goodsUpdateService;
	@Autowired
	GoodsDeleteService goodsDeleteService;
	
	
	@RequestMapping("goodsDelete")
	public String goodsDelete(@RequestParam(value="num") String goodsNum) {
		goodsDeleteService.execute(goodsNum);
		return "redirect:goodsList";
	}
	@RequestMapping(value="goodsModifyOk", method = RequestMethod.POST)
	public String goodsUpdate(GoodsCommand goodsCommand) {
		goodsUpdateService.execute(goodsCommand);
		return "redirect:goodsDetail?num="+goodsCommand.getGoodsNum();
	}
	@RequestMapping("goodsModify")
	public String goodsModify(@RequestParam(value="num") String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsModify";
	}
	@RequestMapping("goodsDetail")
	public String goodsDetail(@RequestParam(value="num") String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsDetail";
	}
	@RequestMapping("goodsRegist")
	public String goodsRegist(HttpSession session, GoodsCommand goodsCommand, Model model) {
		goodsWriteService.execute(session, goodsCommand, model);
		return "redirect:goodsList";
	}
	@RequestMapping("goodsWrite")
	public String goodsWrite(Model model) {
		goodsAutoNumService.execute(model);
		return "thymeleaf/goods/goodsForm";
	}
	@RequestMapping("goodsList")
	public String goodsList(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/goods/goodsList";
	}
}
