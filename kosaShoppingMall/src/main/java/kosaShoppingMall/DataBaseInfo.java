package kosaShoppingMall;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class DataBaseInfo {
   //인터페이스로 사용 시 필요
   @Bean
   public SqlSessionFactory sqlSessionFactory(DataSource dataSource, 
         ApplicationContext applicationContext)
         throws Exception {
      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
      sessionFactory.setDataSource(dataSource);
      sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
      sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/**/*Mapper.xml"));//xml 파일의 이름이 ~~~Mapper로 끝나아ㅑ함
                                                         //mappers에서 xml파일 관리할 것
      sessionFactory.setTypeAliasesPackage("kosaShoppingMall.domain");
      return sessionFactory.getObject();
   }
   
   //이거업스면 인터페이스로 사용하는거고, 클래스로 사용하겠다! 하면 이거필요
   @Bean
   public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {
      return new SqlSessionTemplate(sqlSessionFactory);
   }
   
}