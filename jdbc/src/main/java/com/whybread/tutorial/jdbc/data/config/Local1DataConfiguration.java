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
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(value = "com.whybread.tutorial.jdbc.data.dao.domesticuser", sqlSessionFactoryRef = "local1SqlSessionFactory")
public class Local1DataConfiguration {

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.db-local-1")
  public DataSourceProperties local1DataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.db-local-1.configuration")
  public HikariDataSource local1DataSource() {
    return local1DataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
  }

  @Bean
  @Primary
  public SqlSessionFactory local1SqlSessionFactory(@Qualifier("local1DataSource") DataSource local1DataSource,
      ApplicationContext applicationContext) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(local1DataSource);
    sqlSessionFactoryBean
        .setTypeAliasesPackage("com.whybread.tutorial.jdbc.data.vo, com.whybread.tutorial.jdbc.data.dto");
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/local1/**/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  @Primary
  public SqlSessionTemplate local1SqlSessionTemplate(
      @Qualifier("local1SqlSessionFactory") SqlSessionFactory local1SqlSessionFactory) {
    return new SqlSessionTemplate(local1SqlSessionFactory);
  }
}