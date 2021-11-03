/**
 * 
 */
package com.emplist.batch.util;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * @author A719637
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {
	

	@Override
    @Autowired
    public void setDataSource(@Qualifier("batchDataSource") DataSource batchDataSource) {
        super.setDataSource(batchDataSource);
    }

//    @Bean
//    public BatchDataSourceInitializer batchDataSourceInitializer(@Qualifier("batchDataSource") DataSource batchDataSource,
//            ResourceLoader resourceLoader) {
//        return new BatchDataSourceInitializer(batchDataSource, resourceLoader, new BatchProperties());
//    }
	
	
//	
//	
//	
//	
//
//    @Override
//    @Autowired
//    public void setDataSource(@Qualifier("batchDataSource") DataSource batchDataSource) {
//        super.setDataSource(batchDataSource);
//    }
//    
//    
//    @Bean(name = "batchDataSource", initMethod = "init", destroyMethod = "close")
//    public DataSource batchDataSource() {
//    	System.out.println("4444444444444444 ........................................");
//        final String user = "root";//this.env.getProperty("db.batch.username");
//        final String password = "Syntel123";//this.env.getProperty("db.batch.password");
//        final String url = "jdbc:mysql://localhost:9096/democustomerbatch";//this.env.getProperty("db.batch.url");
//
////        return this.getAtomikosDataSource("metaDataSource", this.getMysqlXADataSource(url, user, password));
//        return this.getMysqlXADataSource(url, user, password);
//    }
//    
//    
//    
//    private MysqlXADataSource getMysqlXADataSource(final String url, final String user, final String password) {
//
//        final MysqlXADataSource mysql = new MysqlXADataSource();
//        mysql.setUser(user);
//        mysql.setPassword(password);
//        mysql.setUrl(url);
//        mysql.setPinGlobalTxToPhysicalConnection(true);
//
//        return mysql;
//    }
}