package springTest.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 파일이 아니라 특정한 문자값을 출력하는 컨트롤러 (직접 문자열을 forward하겠다는 뜻 )
@RestController
public class HelloController {
	// 찾는 주소를 넣으면 주소에 해당되는 메서드가 알아서 실행된다. 
	// 주소가 있는애들 : FrontController 를 jsp에서는 web.xml머시기에 맹글었는데 
	// 스프링부트 에서는 추가안해도 된다. 알아서 찾아오는애가 @SpringBootApplication객체이다. 
	// 알아서 찾아올 때 helloController가 컨트롤러인지를 알아야한다.
	// 알려주기 위해서 web.xml에 적었는데, 그 대신에 @RestController 든 @Controller 를 적어주어야 한다. 
	@RequestMapping("/")
	public String home() {
		// return 에 원하는 jsp파일 적어주면 된다. RequestDispatcher 어저고 dispatcher.forword이던고 안해도됨
		return "thymeleaf/test/hello";
	}
}
