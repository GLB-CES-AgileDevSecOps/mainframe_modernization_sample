/**
 * 
 */
package com.emplist.batch.util;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author A719637
 *
 */
public class DataSourceConfiguration {

//	@Bean
//    @Primary
//    @ConfigurationProperties("domain.datasource")
//    public DataSource domainDataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean("batchDataSource")
//    @ConfigurationProperties("batch.datasource")
//    public DataSource batchDataSource() {
//        return DataSourceBuilder.create().build();
//    }
	
	
	
//	@Value("classpath:schema-mysql.sql")
//    private Resource schemaScript;
//
//
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public DataSource mysqlDataSource() throws SQLException {
//        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setDriver(new com.mysql.jdbc.Driver());
//        dataSource.setUrl("jdbc:mysql://localhost/spring_batch_example");
//        dataSource.setUsername("test");
//        dataSource.setPassword("test");
//        DatabasePopulatorUtils.execute(databasePopulator(), dataSource);
//        return dataSource;
//    }
//
//    @Bean
//    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") final DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    private DatabasePopulator databasePopulator() {
//        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(schemaScript);
//        return populator;
//    }
}