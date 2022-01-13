package springBootTest1;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 이 클래스는 무슨 클래스다 라는걸 알려줘야함 
@Configuration
public class DataBaseInfo {
	@Bean	// 스프링에 필요한 객체를 생성. bean객체를 맹글겠다.
	// jsp에서 connection 쓰는데 Mybatis에서 connection 객체 를 사용해야 한다. 
	// Mybatis에서는 sqlSessionFactory 라는 걸 사용한다. 
	// sessionFactory 는 application.properties의 datasourc를 자동으로 받아오는 객체 
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
		
		// application.propertiest에 있는 datasource를 가져옴 
		// sessionFactory 는 MVCprject 에서의 Connection과 같다. 
		// datasource 는 DriverManagerConnection? 이랑 같음 
				
	}
}
