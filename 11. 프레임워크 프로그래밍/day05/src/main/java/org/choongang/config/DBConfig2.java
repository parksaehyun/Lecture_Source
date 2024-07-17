package org.choongang.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
//@Configuration // 설정을 2개로 할때는 내부클래스 형태로 많이함 // 프로필에 따라서 빈을 달리 생성
public class DBConfig2 {
    // 개발할 때의 설정
    @Profile("!prod") // prod 프로파일이 아닌 경우
    //  환경변수가 이거이면 여기 있는 빈을 생성
    // Profile : 환경변수에 따라서 빈을 달리 생성해주는 기술
    // spring.profiles.active라는 환경변수에 따라서 설정파일을 분리해서 인식( = 빈을 분리해서 생성)
    // 왜 분리? 우리개발할때는 윈도우 배포할때는 리눅스
    // 그리고 커넥션풀 설정만 보아도 개발할 때랑 배포할때의 설정이 다름
    @EnableTransactionManagement
    @MapperScan("org.choongang")
    @EnableJdbcRepositories("org.choongang")
    //@Configuration
    static class DBConfigDev extends AbstractJdbcConfiguration {

        @Bean(destroyMethod = "close")
        public DataSource dataSource() {

            log.info("dev 프로파일!");

            DataSource ds = new DataSource();

            /* 연결 설정 S */
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
            ds.setUsername("SPRING");
            ds.setPassword("oracle");
            /* 연결 설정 E */

            /* 커넥션 풀 설정 S */
            ds.setInitialSize(2);
            ds.setMaxActive(10); // 배포할때 이런설정이면 안되지
            ds.setTestWhileIdle(true);
            ds.setMinEvictableIdleTimeMillis(1000 * 60);
            ds.setTimeBetweenEvictionRunsMillis(1000 * 5);
            /* 커넥션 풀 설정 E */

            return ds;
        }

        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

        @Bean
        public SqlSessionFactory sqlSessionFactory() throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource());

            return factoryBean.getObject();
        }

        @Bean
        public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
            return new NamedParameterJdbcTemplate(dataSource);
        }
    }

    @Profile("prod") // 배포할때 프로필? 설정 //  환경변수가 이거이면 여기 있는 빈을 생성
    @EnableTransactionManagement
    @MapperScan("org.choongang")
    @EnableJdbcRepositories("org.choongang")
    //@Configuration
    static class DBConfigProd extends AbstractJdbcConfiguration {

        @Bean(destroyMethod = "close")
        public DataSource dataSource() {

            log.info("prod 프로파일!");

            DataSource ds = new DataSource();

            /* 연결 설정 S */
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
            ds.setUsername("SPRING");
            ds.setPassword("oracle");
            /* 연결 설정 E */

            /* 커넥션 풀 설정 S */
            ds.setInitialSize(2);
            ds.setMaxActive(10);
            ds.setTestWhileIdle(true);
            ds.setMinEvictableIdleTimeMillis(1000 * 60);
            ds.setTimeBetweenEvictionRunsMillis(1000 * 5);
            /* 커넥션 풀 설정 E */

            return ds;
        }

        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

        @Bean
        public SqlSessionFactory sqlSessionFactory() throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource());

            return factoryBean.getObject();
        }

        @Bean
        public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
            return new NamedParameterJdbcTemplate(dataSource);
        }
    }
}