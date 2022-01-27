package kosaShoppingMall;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer{
	// html 이나 jsp 문서 에서 view밑에 있는 파일을 불러올 때 404 오류가 나는 것을 방지
	// /** view밑에 있는걸 의미 
	@Override
	public void addResourceHandlers(
			ResourceHandlerRegistry registry) {
		// static 밑이 아니라 view 밑에 있는 애들을 불러오기 위해서 필요한 코드 
		registry.addResourceHandler("/**")
        .addResourceLocations("/view/")
        .setCachePeriod(14400);
	}
}