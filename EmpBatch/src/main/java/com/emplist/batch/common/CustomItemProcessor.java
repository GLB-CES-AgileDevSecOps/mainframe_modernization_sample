package com.emplist.batch.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import com.emplist.batch.copybooks.TestBatchInfilerecord;
import com.emplist.batch.copybooks.TestBatchInfilerecord.TestBatchoutrecord;
import com.emplist.batch.copybooks.TestBatcoutrecord;
import com.emplist.batch.util.LowCodeUtility;

public class CustomItemProcessor implements ItemProcessor<TestBatchInfilerecord, TestBatchoutrecord>, JobExecutionListener { // StepExecutionListener

	private static final Logger log = LogManager.getLogger(CustomItemProcessor.class);
	int addValue = 0;

//	private StepExecution stepExecution;

	private JobExecution jobExecution;

	
	@Override
	public TestBatchoutrecord process(TestBatchInfilerecord item) throws Exception {

		try {
			processData(item);
		} catch (Exception e) {
			e.printStackTrace();
			this.jobExecution.stop();
			throw new RuntimeException("CustomItemProcessor process() Job Execution Exception :: " + e.getMessage(), e);
		}
		log.info("Exiting from process()  --  TestpgmInfilerecord  output is :: "+item.testBatchPojo.testBatchoutrecord);
		return item.testBatchPojo.testBatchoutrecord;
	}

	
	
	
	
	
	
	
	
/**
	 * @param item
	 */
	private void processData(TestBatchInfilerecord item) {
		// TODO Auto-generated method stub
		
	}









	//	@Override
//	public TestpgmWsoutrecord process(TestpgmInfilerecord item) throws Exception {
//
//		log.info("Entering into process()  -- input record - TestpgmInfilerecord :: " + item);
//		//Need to invoke business logic to process each recoed
//		try {
//			
//			processData(item);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			this.jobExecution.stop();
//			// throw new JobExecutionException("666666666666666666666666666666666666666666666666 :: CustomItemProcessor Job Execution Exception :: "+e.getMessage(), e);
//			throw new RuntimeException("CustomItemProcessor process() Job Execution Exception :: " + e.getMessage(), e);
//		}
//		log.info("Exiting from process()  --  TestpgmInfilerecord  output is :: "+item.getTestpgmPojo().testpgmwsoutrecord);
//		return item.getTestpgmPojo().testpgmwsoutrecord;
//	}
//
	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		this.jobExecution = jobExecution;

	}
	 



 

}