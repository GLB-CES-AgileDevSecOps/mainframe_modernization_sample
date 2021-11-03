package com.emplist.batch.tasklet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.emplist.batch.constants.AppConstants;
import com.emplist.batch.dao.EmplistDAO;
import com.emplist.batch.dao.EmplistDAOImpl;
import com.emplist.batch.programs.emplist.PR_Emplist;
 
public class Task1 implements Tasklet {

	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	
	@Autowired
    private EmplistDAO emplistdao;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("Tasklet Task1 start.......................................................");
//		ExecutionContext stepContext = this.stepExecution.getExecutionContext();
//	    stepContext.put("inputFile_step1", "c:\\temp\\testinput.txt");
		
		StepContext stepContext = chunkContext.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();

       jobContext.put("step1_paramer1", "its_param1");
       jobContext.put("step1_paramer2", "its_param2");
       jobContext.put("step1_paramer3", "its_param3");
       jobContext.put("step1_paramer4", "its_param4");
       
       stepContext.setAttribute("inputFile_step1", "c:\\temp\\testinput.txt");
       stepContext.getAttribute("inputFile_step1");
	
       //Gets the data here
       //String param5 = (String) jobContext.get("step1_paramer5");
       //String param6 = (String) jobContext.get("step1_paramer6");
       String inputFilePath = (String) stepContext.getAttribute("inputFile_step1");
       
//       System.out.println("========= jobContext : step1_paramer1 ============== :: "+jobContext.get("step1_paramer1"));
//       System.out.println("========= jobContext : step1_paramer2 ============== :: "+jobContext.get("step1_paramer2"));
//       System.out.println("========= jobContext : step1_paramer3 ============== :: "+jobContext.get("step1_paramer3"));
//       System.out.println("========= jobContext : step1_paramer4 ============== :: "+jobContext.get("step1_paramer4"));
//       
//       System.out.println("========= jobContext : step1_paramer5 ============== :: "+jobContext.get("step1_paramer5"));
//       System.out.println("========= jobContext : step1_paramer6 ============== :: "+jobContext.get("step1_paramer6"));
//       
//       System.out.println("========= stepContext : inputFile_step1 ============== :: "+stepContext.getAttribute("inputFile_step1"));
       
       
		// ... task code
       String sqlQuery =AppConstants.SELECT_CURSOR_EMPLOYEE_CURSOR_DECLARE;
       List<Object> parameterList = new ArrayList<Object>();
       List<Map<String, Object>> dbRows = null;
       emplistdao = new EmplistDAOImpl();
       PR_Emplist pr_emplist = new PR_Emplist();
		try {
			pr_emplist.initialize();
			pr_emplist.execute(pr_emplist);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Tasklet Task1 completed.......................................................");
		return RepeatStatus.FINISHED;
	}
	
	
	
	
	/**
	 * @return the param1
	 */
	public String getParam1() {
		return param1;
	}

	/**
	 * @param param1 the param1 to set
	 */
	public void setParam1(String param1) {
		this.param1 = param1;
	}

	/**
	 * @return the param2
	 */
	public String getParam2() {
		return param2;
	}

	/**
	 * @param param2 the param2 to set
	 */
	public void setParam2(String param2) {
		this.param2 = param2;
	}

	/**
	 * @return the param3
	 */
	public String getParam3() {
		return param3;
	}

	/**
	 * @param param3 the param3 to set
	 */
	public void setParam3(String param3) {
		this.param3 = param3;
	}

	/**
	 * @return the param4
	 */
	public String getParam4() {
		return param4;
	}

	/**
	 * @param param4 the param4 to set
	 */
	public void setParam4(String param4) {
		this.param4 = param4;
	}

	/**
	 * @return the param5
	 */
	public String getParam5() {
		return param5;
	}

	/**
	 * @param param5 the param5 to set
	 */
	public void setParam5(String param5) {
		this.param5 = param5;
	}

	/**
	 * @return the param6
	 */
	public String getParam6() {
		return param6;
	}

	/**
	 * @param param6 the param6 to set
	 */
	public void setParam6(String param6) {
		this.param6 = param6;
	}
}