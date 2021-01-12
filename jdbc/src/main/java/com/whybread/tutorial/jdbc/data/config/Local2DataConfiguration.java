package com.whybread.tutorial.jdbc.data.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.whybread.tutorial.jdbc.data.dao.foreignuser", sqlSessionFactoryRef = "local2SqlSessionFactory")
public class Local2DataConfiguration {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.db-local-2")
  public DataSourceProperties local2DataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.db-local-2.configuration")
  public HikariDataSource local2DataSource() {
    return local2DataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
  }

  @Bean
  public SqlSessionFactory local2SqlSessionFactory(@Qualifier("local2DataSource") DataSource local2DataSource,
      ApplicationContext applicationContext) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(local2DataSource);
    sqlSessionFactoryBean
        .setTypeAliasesPackage("com.whybread.tutorial.jdbc.data.vo, com.whybread.tutorial.jdbc.data.dto");
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/local2/**/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public SqlSessionTemplate local2SqlSessionTemplate(
      @Qualifier("local2SqlSessionFactory") SqlSessionFactory local1SqlSessionFactory) {
    return new SqlSessionTemplate(local1SqlSessionFactory);
  }
}