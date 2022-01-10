package springTest.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller : html 파일을 서비스하기 위한 컨트롤러 <-> RestController : 문자열을 서비스 하기 위한 컨트롤러 
// @RestController 가 왜 필요한가? 안드로이드는 html 을 받을 수 없다. 그래서 문자열로 받아야하기 때문에 필요하다. -> 제이슨이요? 홍현희..
@Controller
public class TestController {
	@RequestMapping("/hello")
	public String test() {
		// 내가 서비스 하고자 하는 경로 : 그 경로를 알려주는게 application.properties
		// taglib 안써도 된다 여기서는 thymeleaf html 쓴다,,,,,? 눼?
		return "thymeleaf/test/hello";
		// 이렇게 적어도 파일이 어느 위치에 있는지 모르기 떄문에 view 파일을 뭐 어짼다고?
		// html 확장자 안적어줘도 됨.
	}

}
