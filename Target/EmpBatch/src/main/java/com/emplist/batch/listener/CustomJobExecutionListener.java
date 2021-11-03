package com.emplist.batch.listener;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.OutputStreamAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;


public class CustomJobExecutionListener  implements JobExecutionListener {
	
	private static final Logger log = LogManager.getLogger(CustomJobExecutionListener.class);
	private String jobName = "";
	private String logfilePath = "C:/logs/javalog/";
	private String logfileName = "";//"democustomerbatchelLog.log";
	private String logAppenderName = "";//"democustomerbatchelLog.log";
	private String pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %msg%n";
	
	private DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss");
	private Date date = new Date();

	

	@Override
	public void beforeJob(JobExecution jobExecution) {
		
		// beginning of the job - add the FileAppender to the root logger with an unique name and will remove it after the job execution
		jobName = jobExecution.getJobInstance().getJobName();
		logfileName = logfilePath+jobName + "_" + dateFormat.format(date) + ".log";
		OutputStream out = null;
		try {
			out = new FileOutputStream(logfileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAppender(out, logfileName);
		
		log.info(" ==== Before starting the Job. Job name ================================= :: "+jobExecution.getJobInstance().getJobName());
		// whole job execution
        List<Throwable> exceptions = jobExecution.getAllFailureExceptions();
        if (exceptions.isEmpty()) {
            return;
        }
        
        exceptions.forEach(th -> log.error("exception has occurred in job.", th));
        
        jobExecution.getStepExecutions().forEach(stepExecution -> {
            Object errorItem = stepExecution.getExecutionContext()
                    .get("ERROR_ITEM"); 
            if (errorItem != null) {
            	log.error("detected error on this item processing. " +
                        "[step:{}] [item:{}]", stepExecution.getStepName(),
                        errorItem);
            }
        });
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// end of the job - remove the FileAppender from the root logger after the job execution
		LoggerContext context = LoggerContext.getContext(false);
		Configuration config = context.getConfiguration();
		jobName = jobExecution.getJobInstance().getJobName();
		logfileName = logfilePath+jobName + "_" + dateFormat.format(date) + ".log";
		removeAppender(logfileName, config);
		log.info("===== After Job completed Job name ===================================== :: "+jobExecution.getJobInstance().getJobName());
	}
	
	 
	
	private void addAppender(final OutputStream outputStream, final String outputStreamName) {
	    final LoggerContext context = LoggerContext.getContext(false);
	    final Configuration config = context.getConfiguration();
	    final PatternLayout layout = PatternLayout.createDefaultLayout(config);
	    final Appender appender = OutputStreamAppender.createAppender(layout, null, outputStream, outputStreamName, false, true);
	    appender.start();
	    config.addAppender(appender);
	    updateLoggers(appender, config);
	}
	
	private void updateLoggers(final Appender appender, final Configuration config) {
	    final Level level = null;
	    final Filter filter = null;
	    for (final LoggerConfig loggerConfig : config.getLoggers().values()) {
	        loggerConfig.addAppender(appender, level, filter);
	    }
	    config.getRootLogger().addAppender(appender, level, filter); 
	}
	
	
	private void removeAppender(final String appendername, final Configuration config) {
	    config.getRootLogger().removeAppender(appendername);
	}
	 
	
}
