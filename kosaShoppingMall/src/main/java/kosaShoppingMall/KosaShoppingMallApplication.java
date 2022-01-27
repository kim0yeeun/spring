package kosaShoppingMall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// @ ser com con 등등 자동으로 생성 
//kosaShoppingMall 밑의 패키지 애들을 다 슼내하갯다
//kosaShoppingMall 안에 있는 매퍼를 사용하겠다. 
@SpringBootApplication
@ComponentScan(value = "kosaShoppingMall")
@MapperScan(value = {"kosaShoppingMall"})
@Controller
public class KosaShoppingMallApplication {
	@RequestMapping("/")
	public String test() {
		return "thymeleaf/index";
	}
	public static void main(String[] args) {
		SpringApplication.run(KosaShoppingMallApplication.class, args);
	}
}