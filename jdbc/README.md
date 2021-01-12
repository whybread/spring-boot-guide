# Chapter 03: JDBC

In this chapter, we will study how to communicate with your **DBMS** with **Spring Boot**.

Focus on how to modify the configurations of [`DataSource`](https://docs.oracle.com/javase/7/docs/api/javax/sql/DataSource.html) and [`SqlSessionFactory`](https://mybatis.org/mybatis-3/apidocs/org/apache/ibatis/session/SqlSessionFactory.html).




## Using @ConfigurationProperties

from https://howtodoinjava.com/spring-boot2/datasource-configuration/
```
@Bean
public DataSource dataSource() 
{
	DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	dataSourceBuilder.driverClassName("org.h2.Driver");
	dataSourceBuilder.url("jdbc:h2:file:C:/temp/test");
	dataSourceBuilder.username("sa");
	dataSourceBuilder.password("");
	return dataSourceBuilder.build();
}
```
to https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-configure-a-datasource

```
@Bean
@ConfigurationProperties("app.datasource")
public DataSource dataSource() {
	return DataSourceBuilder.create().build();
}
```
with a fragment of application.yml
```
app:
	datasource:
		url: "jdbc:mysql://localhost/test"
		username: "dbuser"
		password: "dbpass"
		pool-size: 30
```



## What is mybatis?

traditionally https://www.jackrutorial.com/2018/08/multiple-datasource-in-spring-boot.html
using `query` method of `JdbcTemplate` class
```
RowMapperImpl rowMapperImpl = new RowMapperImpl();
List list = myJdbcTemplate.query(sql1, rowMapperImpl);
```
to https://sanghye.tistory.com/26
```
@Configuration
@MapperScan(value = "com.test.api.mapper.first", sqlSessionFactoryRef = "firstSqlSessionFactory")
public class FirstDataSourceConfiguration {
     @Primary
     @Bean(name = "firstDataSource")
     @ConfigurationProperties(prefix = "spring.first.datasource")
     public DataSource firstDataSource() {
         return DataSourceBuilder.create().build();
     }

     @Primary
     @Bean(name = "firstSqlSessionFactory")
     public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource firstDataSource,
                                 ApplicationContext applicationContext) throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(secondDataSource);
            sqlSessionFactoryBean.setTypeAliasesPackage("com.test.api.entity, com.test.api.vo");
            sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/first/**.xml"));
            return sqlSessionFactoryBean.getObject();
     }

     @Primary
     @Bean(name = "firstSessionTemplate")
     public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory firstSqlSessionFactory) {
         return new SqlSessionTemplate(firstSqlSessionFactory);
     }
}
```
Use `org.apache.ibatis.session.SqlSessionFactory` interface which makes its own template and does the mapped queries.



## What's more?
