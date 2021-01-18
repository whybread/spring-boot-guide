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

/*
 * Indicates that a class declares one or more {@link Bean @Bean} methods
 * and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime
 * For more information: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html
 */

@Configuration

/*
 * Use this annotation to register MyBatis mapper interfaces when using Java Config.
 * For more information: https://mybatis.org/spring/apidocs/org/mybatis/spring/annotation/MapperScan.html
 */
@MapperScan(value = "com.whybread.tutorial.jdbc.data.dao.domesticuser", sqlSessionFactoryRef = "local1SqlSessionFactory")
public class Local1DataConfiguration {

  @Bean
  
  /*
   * Indicates that a bean should be given preference when multiple candidates
   * are qualified to autowire a single-valued dependency. If exactly one
   * 'primary' bean exists among the candidates, it will be the autowired value.
  */
  @Primary
  
  /*
   * Annotation for externalized configuration.
   * The prefix of the properties that are valid to bind to this object.
   */
  @ConfigurationProperties(prefix = "spring.datasource.db-local-1")

  // Make a new DataSourceProperties Bean with your 'spring.datasource.db-local-1.*' properties.
  public DataSourceProperties local1DataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.db-local-1.configuration")

  // Make a new HikariDataSource Bean with your local1DataSourceProperties Bean and 'spring.datasource.db-local-1.configuration.*' properties.
  // Seperating namespaces is to leverage what DataSourceProperties does for you.
  // e.g. DataSourceProperties is taking care of the url/jdbcUrl translation for you, so you can use either <custom-namespace>.url or <custom-namespace>.jdbc-url
  // For more information: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-configure-a-datasource
  public HikariDataSource local1DataSource() {
    return local1DataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
  }

  @Bean
  @Primary


  // Make a new SqlSessionFactory bean that is used by MyBatis.
  // As it is made with your local1DataSource Bean, the queries it has will be done in your db-local-1
  public SqlSessionFactory local1SqlSessionFactory(@Qualifier("local1DataSource") DataSource local1DataSource,
      ApplicationContext applicationContext) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

    // The facotry will use your custom DataSource object.
    sqlSessionFactoryBean.setDataSource(local1DataSource);

    // In your mapper XML, you can omit the package name.
    sqlSessionFactoryBean.setTypeAliasesPackage("com.whybread.tutorial.jdbc.data.vo, com.whybread.tutorial.jdbc.data.dto");
    
    // Your mapper XML location must be set.
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/db-local-1/**/*.xml"));
    
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  @Primary

  // Make a new SqlSessionTemplate Bean with your local1SqlSessionFactory Bean
  public SqlSessionTemplate local1SqlSessionTemplate(
      @Qualifier("local1SqlSessionFactory") SqlSessionFactory local1SqlSessionFactory) {
    return new SqlSessionTemplate(local1SqlSessionFactory);
  }
}