package com.emplist.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class DemoCustomerBatchTest {
	
	private static final Logger log = LogManager.getLogger(DemoCustomerBatchTest.class);
	
	
	static {
		//SystemOutToLog4j.enableForPackage("com");
	}
	
	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		ClassPathXmlApplicationContext appContext = null;
		try {
			
			appContext = new ClassPathXmlApplicationContext("classpath:spring/batch/jobs/job-democustomer.xml");
			// get the launcher
			JobLauncher jobLauncher = (JobLauncher) appContext.getBean("jobLauncher");
			// get the job to run
//			Job dailyjob1 = (Job) appContext.getBean("dailyjob1");
			Job job1 = (Job) appContext.getBean("job1");
			// run
			//JobExecution dailyjob1Execution = jobLauncher.run(dailyjob1, new JobParameters());
			JobExecution dailyjob1Execution = jobLauncher.run(job1, new JobParameters());
			System.out.println("Job execution status is --- :: " + dailyjob1Execution.getStatus());
			log.info("Job dailyjob1 execution status is --- :: " + dailyjob1Execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in job execution launcher ::",e);
		} finally {
//			appContext.close();
		}

	}

}
