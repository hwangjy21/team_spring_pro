package com.myweb.www.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = { "com.myweb.www.repository" })
@ComponentScan(basePackages = {"com.myweb.www.service","com.myweb.www.handler","com.myweb.www.exception"}) //
@EnableAspectJAutoProxy  //231026트랜젝션을 위해 담
@EnableTransactionManagement  //231026트랜젝션을 위해 담
@EnableScheduling   //231026트랜젝션을 위해 담
public class RootConfig {
	// 매퍼, db라인에 대한 설정

	/* DB설정 부분 */

	// 전과 달라진 부분
	// log4jdbc-log4j2 사용
	// hikaricp 사용

	@Autowired
	ApplicationContext applicationContext;

//	@Bean
//	public DataSource dataSource() {
//		HikariConfig hikariConfig = new HikariConfig();
//		// log4jdbc-log4j2의 드라이버 클래스 url 사용
//		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
//		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springdb");
//		hikariConfig.setUsername("springUser");
//		hikariConfig.setPassword("mysql");
//		hikariConfig.setMaximumPoolSize(5); // 최대 커넥션 개수
//		hikariConfig.setMinimumIdle(5); // 최소 유휴 커넥션 개수(반드시 maximumPoolSize와 같은 값으로)
//
//		hikariConfig.setConnectionTestQuery("SELECT now()"); // 첫 연결 test 구문 입력
//		hikariConfig.setPoolName("springHikariCP");
//
//		// ▶config의 추가 설정
//		// cache 사용여부 true
//		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
//		// mysql 드라이버가 한 연결당 cache statement의 수에 관한 설정(보통 default 25(250~500사이 권장))
//		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
//		// mysql connection당 캐싱할 preparedStatement의 개수 지정 옵션(default 256)
//		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true"); // 기본값 256으로 설정
//		// mysql 서버에서 최신 이슈가 있을 경우 지원받는 설정 true
//		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
//
//		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
//		return hikariDataSource;
//	}
	
	@Bean
	public DataSource dataSource() {
	    HikariConfig hikariConfig = new HikariConfig();
	    // MariaDB 드라이버 클래스로 변경
	    hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
	    // MariaDB JDBC URL로 변경, 포트는 MariaDB 기본 포트인 3306 사용
	    hikariConfig.setJdbcUrl("jdbc:mariadb://aj2002.cafe24.com:3306/aj2002");//231120전경환

//	    hikariConfig.setJdbcUrl("jdbc:mariadb://localhost:3306/aj2002");//231120전경환 //카페24배포용webapps//
	    hikariConfig.setUsername("aj2002");
	    hikariConfig.setPassword("k2m16ak47");
	    hikariConfig.setMaximumPoolSize(5);
	    hikariConfig.setMinimumIdle(5);

	    hikariConfig.setConnectionTestQuery("SELECT 123412341234");
	    hikariConfig.setPoolName("springHikariCP");

	    // 여기서부터는 기본적인 HikariCP의 설정으로 마리아DB에 맞게 필요한 부분을 변경하거나 추가할 수 있습니다.
	    hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
	    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
	    // prepStmtCacheSqlLimit 값을 "true"에서 실제 제한 값으로 설정해야 합니다. (예: "2048")
	    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
	    hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

	    HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
	    return hikariDataSource;
	}
	
	
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		
		return (SqlSessionFactory)sqlFactoryBean.getObject();
	}
	
	//트렌젝션메니저 빈 설정23 10 26 //231026트랜젝션을 위해 담
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
    // RestTemplate 빈 추가 지도 위도경도 자동추출을 위해 담 jgh1206 
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
}
