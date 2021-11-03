package com.emplist.batch.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;


public class StepExecutionListner implements StepExecutionListener {
	
	private static final Logger log = LogManager.getLogger(StepExecutionListner.class);

	
	@Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("==== Before starting the Step ========================================== :: "+stepExecution.getStepName());
        log.info("==== StepExecution Step Name	  = :: "+stepExecution.getStepName());
        
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        /*
    	log.info("------------------------------------------------------------------------------------");
        log.info("StepExecutionListener - afterStep:getCommitCount=" +  stepExecution.getCommitCount());
        log.info("StepExecutionListener - afterStep:getFilterCount=" +  stepExecution.getFilterCount());
        log.info("StepExecutionListener - afterStep:getProcessSkipCount=" +  stepExecution.getProcessSkipCount());
        log.info("StepExecutionListener - afterStep:getReadCount=" +  stepExecution.getReadCount());
        log.info("StepExecutionListener - afterStep:getReadSkipCount=" +  stepExecution.getReadSkipCount());
        log.info("StepExecutionListener - afterStep:getRollbackCount=" +  stepExecution.getRollbackCount());
        log.info("StepExecutionListener - afterStep:getWriteCount=" +  stepExecution.getWriteCount());
        log.info("StepExecutionListener - afterStep:getWriteSkipCount=" +  stepExecution.getWriteSkipCount());
        log.info("StepExecutionListener - afterStep:getStepName=" +  stepExecution.getStepName());
        log.info("StepExecutionListener - afterStep:getSummary=" +  stepExecution.getSummary());
        log.info("StepExecutionListener - afterStep:getStartTime=" +  stepExecution.getStartTime());
        log.info("StepExecutionListener - afterStep:getStartTime=" +  stepExecution.getEndTime());
        log.info("StepExecutionListener - afterStep:getLastUpdated=" +  stepExecution.getLastUpdated());
        log.info("StepExecutionListener - afterStep:getExitStatus=" +  stepExecution.getExitStatus());
        log.info("StepExecutionListener - afterStep:getFailureExceptions=" +  stepExecution.getFailureExceptions());
        log.info("------------------------------------------------------------------------------------");
        */
    	
    	
//        log.info("===== StepExecution Commit Count= :: "+stepExecution.getCommitCount());
//        log.info("===== StepExecution Read Count  = :: "+stepExecution.getReadCount());
//        log.info("===== StepExecution Write Count = :: "+stepExecution.getWriteCount());
//        log.info("===== StepExecution Step Summary= :: "+stepExecution.getSummary());
//        log.info("===== StepExecution Start Time  = :: "+stepExecution.getStartTime());
//        log.info("===== StepExecution LastUpdated = :: "+stepExecution.getLastUpdated());
////        log.info("===== StepExecution End Time 	  = :: "+stepExecution.getEndTime());
//        log.info("===== StepExecution Exit Status = :: "+stepExecution.getExitStatus());
//        log.info("===== StepExecution FailureExceptions = :: "+stepExecution.getFailureExceptions());
        
        log.info("===== After Step completed ============================================= :: "+stepExecution.getStepName());

        return null;
    }
    
}
