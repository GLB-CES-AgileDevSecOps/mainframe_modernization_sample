package com.emplist.batch.common;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.emplist.batch.copybooks.TestBatchInfilerecord;


public class ReportFieldSetMapper implements FieldSetMapper<TestBatchInfilerecord> {

	private static final Logger log = LogManager.getLogger(ReportFieldSetMapper.class);
	
	private int addValue = 0;

	@Override
	public TestBatchInfilerecord mapFieldSet(FieldSet fieldSet) throws BindException {
 
		String lineString = Arrays.toString(fieldSet.getValues());
//		TestpgmPojo testpgmPojo = new TestpgmPojo();
		
		try {
			//readData(lineString,testpgmPojo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error while mapping input to Pojo ",e);
		}
//		return testpgmPojo.testpgminfilerecord;
		return new TestBatchInfilerecord();
	}
	
	
//	public boolean readData(String ineString,TestBatchInfilerecord testpgmPojo){
//
//		try{
//			//DISPLAY '1200-READ-DATA - start'.
//			System.out.println("1200-READ-DATA - start");
//
//
//			//READ INPUT-FILE INTO INPUT-RECORD
////			String tmpRecord =bufferedReaderInputFile.readLine();
//			String tmpRecord = ineString;
//			if (null!=tmpRecord) {
//				LowCodeUtility.setMethod(testpgmPojo,"inputRecord",tmpRecord);
//			} else
//			{
//				//MOVE 'Y' TO END-OF-INPUT-IND
//				LowCodeUtility.setMethod(testpgmPojo.testpgmendofinputind, "endOfInputInd", "Y");
//				
//				//DISPLAY 'END OF FILE REACHED'
//				System.out.println("END OF FILE REACHED");
//				
//				return LowCodeUtility.getValueForBoolean(testpgmPojo.testpgmendofinputind, "endOfFile");// return type added instead of goto to exit para
//			}
//
//			//INITIALIZE IN-FILE-RECORD.
//			testpgmPojo.testpgminfilerecord.initialize();
//
//
//			//MOVE INPUT-RECORD TO IN-FILE-RECORD.
//			LowCodeUtility.copyElementaryToGroup((String)LowCodeUtility.getMethod(testpgmPojo,"inputRecord"),testpgmPojo.testpgminfilerecord);
//
//			//ADD +1 TO CAS-IN-COUNT.
//			addValue = (int) LowCodeUtility.getMethod(testpgmPojo.testpgmwscounters,"casInCount") + (int)1;
//			LowCodeUtility.setMethod(testpgmPojo.testpgmwscounters,"casInCount",addValue);
//
//			//PERFORM 1500-PROCESS-DATA THRU 1500-EXIT
//			//send for process the data in processor .......
//			////////////////////////////////processData();
//
//			//DISPLAY '1200-READ-DATA - end'.
//			System.out.println("1200-READ-DATA - end");
//		}
//
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return false;
//
//	}
}