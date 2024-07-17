package org.choongang.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // 트랜잭션관련된 설정 자동화
@MapperScan("org.choongang") // 정의하는데로 다 추가
@EnableJdbcRepositories("org.choongang") // 빈으로 만듬 + 구현체도 알아서 생성
public class DBConfig extends AbstractJdbcConfiguration {

   @Bean(destroyMethod = "close") // 자원 해제
    public DataSource dataSource() {
       DataSource ds = new DataSource();
       /* DB 연결 설정 S */
       ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
       ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
       //ds.setUsername("SPRING");
      ds.setUsername(System.getenv("db.username"));
      //getenv: 환경변수 조회
       //ds.setPassword("oracle");
      ds.setPassword(System.getenv("db.password"));
       /* DB 연결 설정 E */

       /* 커넥션 풀 설정 S */
       ds.setTestWhileIdle(true); // 유휴 객체 유효성 체크
       ds.setInitialSize(2);
       ds.setMaxActive(10);
       ds.setTimeBetweenEvictionRunsMillis(10 * 1000); // 10초에 한번씩 연결 상태 체크(기본값 5초)
       ds.setMinEvictableIdleTimeMillis(1000 * 60); // 유휴 객체(아무것도 하지 않고 있는 객체) 생존 시간 1분(기본값 1분, 안해도 되는데 걍 씀)
       /* 커넥션 풀 설정 E */

       return ds;
    }

   @Bean // 외부꺼라서 수동빈 등록해주어야함
   public JdbcTemplate jdbcTemplate() { // 마이바티스 슬꺼면 안해도 되긴 함
      return new JdbcTemplate(dataSource());
   }

   @Bean
   public PlatformTransactionManager transactionManager() {
      DataSourceTransactionManager tm = new DataSourceTransactionManager();
      tm.setDataSource(dataSource());

      return tm;
   }

   @Bean // 마이바티스 설정
   public SqlSessionFactory sqlSessionFactory() throws Exception {
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      sqlSessionFactoryBean.setDataSource(dataSource());

      SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
      return sqlSessionFactory;
   }

   @Bean
   public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
      return new NamedParameterJdbcTemplate(dataSource);
   }
}
