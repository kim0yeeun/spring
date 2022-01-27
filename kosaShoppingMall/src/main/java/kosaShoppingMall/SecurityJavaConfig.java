package kosaShoppingMall;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityJavaConfig 
	extends WebSecurityConfigurerAdapter{
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.cors().disable() //cors방지
			.csrf().disable() //csrf방지
			.formLogin().disable()
			.headers().frameOptions().disable();
	}
	@Bean
	// 암호화 모듈 : 객체를 생성시켜서 리턴받음 
	// 이 암호화 모듈을 사용하기 위해서 pom.xml 에서 dependency 보면 암호화 있쒀 그게 있어야함 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}