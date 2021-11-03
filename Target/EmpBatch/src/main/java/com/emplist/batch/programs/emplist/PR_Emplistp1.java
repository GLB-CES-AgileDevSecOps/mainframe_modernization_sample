package com.emplist.batch.programs.emplist;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/* */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.emplist.batch.constants.AppConstants;
import com.emplist.batch.copybooks.SqlcaPojo;
import com.emplist.batch.copybooks.emplistPojo;
import com.emplist.batch.dao.EmplistDAO;
import com.emplist.batch.dao.EmplistDAOImpl;
import com.emplist.batch.util.LowCodeUtility;
import com.emplist.batch.util.WriteUtil;

@Service
@Component
public class PR_Emplistp1 {
	
	final static Logger logger = LogManager.getLogger(PR_Emplistp1.class);
	double addValue = 0;
	String fillerString = "";
	String mergedString = "";
	
	int SQLCODE = 100;
	
	@Autowired
	EmplistDAO emplistdao;

	public void initialize() {
		emplistdao = new EmplistDAOImpl();
	}
	
	public void execute(PR_Emplist pr_emplist){
		try{
			//System.out.println("Emplist : EmplistP1");
		}catch(Exception e){
		}
	}
	public void prDeptcodeRetrieval(PR_Emplist emplist){
		try{
			//DISPLAY 'DEPTCODE-RETRIEVAL - START'.
			logger.info("DEPTCODE-RETRIEVAL - START");

			//EXEC SQL SELECT DEPTDESC INTO :WS-EMP-DEPTDESC FROM DEPARTMENT WHERE DEPTCODE = :WS-EMP-DEPT END-EXEC.
			String sqlQuery =AppConstants.SELECT_DEPTCODE_RETRIEVAL;
			List<Object> parameterList = new ArrayList<Object>();
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsEmpDept",resultSet.get("deptcode"));
//			parameterList.add(LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpDept"));
			parameterList.add(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDept"));
			
//			List<Map<String, Object>> dbRows = emplistdao.deptcodeRetrieval(sqlQuery,parameterList);
			List<Map<String, Object>> dbRows = emplist.emplistdao.deptcodeRetrieval(sqlQuery,parameterList);
			if(!dbRows.isEmpty()) {
				for (Map resultSet : dbRows) {
					SQLCODE = 0;
					//LowCodeUtility.setMethod(emplist.emplistPojo,"wsEmpDeptdesc",resultSet.get("DEPTDESC"));
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDeptdesc",resultSet.get("DEPTDESC"));
				}
			}else{
				SQLCODE = 100;
			}

			//MOVE SQLCODE TO WS-SQLCODE.
			LowCodeUtility.copyElementaryToGroup(""+SQLCODE,LowCodeUtility.getMethod(emplist.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,"emplistwssqlcode"));
			//IF (WS-SQL-RETURN-NORMAL){
			if(((boolean) LowCodeUtility.getValueForBoolean(emplist.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,"wsSqlReturnNormal") )){
				//MOVE 'F' TO WS-DEPTVAL
				LowCodeUtility.copyElementaryToGroup("F",LowCodeUtility.getMethod(emplist.emplistPojo.emplistwstemp,"emplistwsdeptval"));
				//ADD 1 TO DEPT-COUNT
				addValue = (int) LowCodeUtility.getMethod(emplist.emplistPojo.emplistwscounters,"deptCount") + (int)1;
				LowCodeUtility.setMethod(emplist.emplistPojo.emplistwscounters,"deptCount",addValue);
				//ELSE IF (WS-SQL-RETURN-NO-ENTRY)
			}
			else if(((boolean) LowCodeUtility.getValueForBoolean(emplist.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,"wsSqlReturnNoEntry") )){
				//MOVE 'N' TO WS-DEPTVAL
				LowCodeUtility.copyElementaryToGroup("N",LowCodeUtility.getMethod(emplist.emplistPojo.emplistwstemp,"emplistwsdeptval"));
				//DISPLAY 'NOT FOUND - DEPARTMENT SELECT - ' WS-DEPTCODE
				logger.info("NOT FOUND - DEPARTMENT SELECT - "+LowCodeUtility.getMethod(emplist.emplistPojo,"wsDeptcode"));

				//ELSE
			}
			else {
				//DISPLAY 'DB2 ERROR - DEPARTMENT SELECT - ' WS-DEPTCODE
				logger.info("DB2 ERROR - DEPARTMENT SELECT - "+LowCodeUtility.getMethod(emplist.emplistPojo,"wsDeptcode"));

				//MOVE 'E' TO WS-DEPTVAL
				LowCodeUtility.copyElementaryToGroup("E",LowCodeUtility.getMethod(emplist.emplistPojo.emplistwstemp,"emplistwsdeptval"));
			}
			//DISPLAY 'DEPTCODE-RETRIEVAL - END'.
			logger.info("DEPTCODE-RETRIEVAL - END");

		}
		catch(Exception e){
			e.printStackTrace();
		}
			}
	public void prDesgcodeRetrieval(PR_Emplist emplist){
		try{
			//DISPLAY 'DESGCODE-RETRIEVAL - START'.
			logger.info("DESGCODE-RETRIEVAL - START");

			//EXEC SQL SELECT DESGDESC, BASICPAY, HRA, GROSSPAY INTO :WS-EMP-DESGDESC, :WS-EMP-BASIC, :WS-EMP-HRA, :WS-EMP-GROSSPAY FROM DESIGNATION WHERE DESGCODE = :WS-EMP-DESG END-EXEC.
			String sqlQuery =AppConstants.SELECT_DESGCODE_RETRIEVAL;
			List<Object> parameterList = new ArrayList<Object>();

			//parameterList.add(LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpDesg"));
			parameterList.add(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDesg"));
			
			List<Map<String, Object>> dbRows = emplistdao.desgcodeRetrieval(sqlQuery,parameterList);
			if(!dbRows.isEmpty()) {
				for (Map resultSet : dbRows) {
					SQLCODE = 0;
					/*LowCodeUtility.setMethod(emplist.emplistPojo,"wsEmpDesgdesc",resultSet.get("DESGDESC"));
					LowCodeUtility.setMethod(emplist.emplistPojo,"wsEmpBasic",resultSet.get("BASICPAY"));
					LowCodeUtility.setMethod(emplist.emplistPojo,"wsEmpHra",resultSet.get("HRA"));
					LowCodeUtility.setMethod(emplist.emplistPojo,"wsEmpGrosspay",resultSet.get("GROSSPAY"));
					*/
					//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDeptdesc",resultSet.get("DEPTDESC"))
					
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDesgdesc",resultSet.get("DESGDESC"));
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpBasic",resultSet.get("BASICPAY"));
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpHra",resultSet.get("HRA"));
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpGrosspay",resultSet.get("GROSSPAY"));
				}
			}else{
				SQLCODE = 100;
			}

			//MOVE SQLCODE TO WS-SQLCODE.
			LowCodeUtility.copyElementaryToGroup(""+SQLCODE,LowCodeUtility.getMethod(emplist.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,"emplistwssqlcode"));
			//IF (WS-SQL-RETURN-NORMAL){
			if(((boolean) LowCodeUtility.getValueForBoolean(emplist.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,"wsSqlReturnNormal") )){
				//MOVE 'F' TO WS-DESGVAL
				LowCodeUtility.copyElementaryToGroup("F",LowCodeUtility.getMethod(emplist.emplistPojo.emplistwstemp,"emplistwsdesgval"));
				//ADD 1 TO DESG-COUNT
				addValue = (int) LowCodeUtility.getMethod(emplist.emplistPojo.emplistwscounters,"desgCount") + (int)1;
				LowCodeUtility.setMethod(emplist.emplistPojo.emplistwscounters,"desgCount",addValue);
				//ELSE IF (WS-SQL-RETURN-NO-ENTRY)
			}
			else if(((boolean) LowCodeUtility.getValueForBoolean(emplist.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,"wsSqlReturnNoEntry") )){
				//MOVE 'N' TO WS-DESGVAL
				LowCodeUtility.copyElementaryToGroup("N",LowCodeUtility.getMethod(emplist.emplistPojo.emplistwstemp,"emplistwsdesgval"));
				//DISPLAY 'NOT FOUND - DESIGNATION SELECT - ' WS-DESGCD
				logger.info("NOT FOUND - DESIGNATION SELECT - "+LowCodeUtility.getMethod(emplist.emplistPojo,"wsDesgcd"));

				//ELSE
			}
			else {
				//DISPLAY 'DB2 ERROR - DESIGNATION SELECT - ' WS-DESGCD
				logger.info("DB2 ERROR - DESIGNATION SELECT - "+LowCodeUtility.getMethod(emplist.emplistPojo,"wsDesgcd"));

				//MOVE 'E' TO WS-DESGVAL
				LowCodeUtility.copyElementaryToGroup("E",LowCodeUtility.getMethod(emplist.emplistPojo.emplistwstemp,"emplistwsdesgval"));
			}
			//DISPLAY 'DESGCODE-RETRIEVAL - end'.
			logger.info("DESGCODE-RETRIEVAL - end");

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void prWriteOutfile(PR_Emplist emplist){
		try{
			//DISPLAY 'WRITE-OUTFILE - START'.
			logger.info("WRITE-OUTFILE - START");
//			LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpId",resultSet.get("empid"));
			//MOVE WS-EMP-ID TO WS-OUT-EMP-ID
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpId",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpId"));
			//MOVE WS-EMP-NAME TO WS-OUT-EMP-NAME
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpName",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpName"));
			//MOVE WS-EMP-LOC TO WS-OUT-EMP-LOC
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpLoc",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpLoc"));
			//MOVE WS-EMP-DEPT TO WS-OUT-EMP-DEPT
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDept",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpDept"));
			//MOVE WS-EMP-DEPTDESC TO WS-OUT-EMP-DEPTDESC
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDeptdesc",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpDeptdesc"));
			//MOVE WS-EMP-DESG TO WS-OUT-EMP-DESG
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDesg",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpDesg"));
			//MOVE WS-EMP-DESGDESC TO WS-OUT-EMP-DESGDESC
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDesgdesc",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpDesgdesc"));
			//MOVE WS-EMP-BASIC TO WS-OUT-EMP-BASIC
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpBasic",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpBasic"));
			//MOVE WS-EMP-HRA TO WS-OUT-EMP-HRA
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpHra",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpHra"));
			//MOVE WS-EMP-GROSSPAY TO WS-OUT-EMP-GROSSPAY.
			//LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpGrosspay",LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpGrosspay"));
			//DISPLAY 'WS-EMP-ID ' WS-OUT-EMP-ID
			logger.info("WS-EMP-ID "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpId"));

			//DISPLAY 'WS-EMP-NAME ' WS-OUT-EMP-NAME
			logger.info("WS-EMP-NAME "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpName"));

			//DISPLAY 'WS-EMP-LOC ' WS-OUT-EMP-LOC
			logger.info("WS-EMP-LOC "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpLoc"));

			//DISPLAY 'WS-EMP-DEPT ' WS-OUT-EMP-DEPT
			logger.info("WS-EMP-DEPT "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDept"));

			//DISPLAY 'WS-EMP-DEPTDESC ' WS-OUT-EMP-DEPTDESC
			logger.info("WS-EMP-DEPTDESC "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDeptdesc"));

			//DISPLAY 'WS-EMP-DESG ' WS-OUT-EMP-DESG
			logger.info("WS-EMP-DESG "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDesg"));

			//DISPLAY 'WS-EMP-DESGDESC ' WS-OUT-EMP-DESGDESC
			logger.info("WS-EMP-DESGDESC "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDesgdesc"));

			//DISPLAY 'WS-EMP-BASIC ' WS-OUT-EMP-BASIC
			logger.info("WS-EMP-BASIC "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpBasic"));

			//DISPLAY 'WS-EMP-HRA ' WS-OUT-EMP-HRA
			logger.info("WS-EMP-HRA "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpHra"));

			//DISPLAY 'WS-EMP-GROSSPAY ' WS-OUT-EMP-GROSSPAY.
			logger.info("WS-EMP-GROSSPAY "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpGrosspay"));

			//WRITE OUTPUT-RECORD FROM OUT-FILE-RECORD.
			//TODO: Output folderpath to be entered manually.
			String folderPath="D:\\temp\\Output";
			String fillerString = ",";
			String mergedString = "";
			mergedString = mergedString +StringUtils.rightPad(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpId"),5);
			mergedString = mergedString +String.format("%1s",fillerString);
			mergedString = mergedString +StringUtils.rightPad(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpName"),30);
			mergedString = mergedString +String.format("%1s",fillerString);
			mergedString = mergedString +StringUtils.rightPad(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpLoc"),30);
			mergedString = mergedString +String.format("%1s",fillerString);
			mergedString = mergedString +StringUtils.rightPad(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDept"),5);
			mergedString = mergedString +String.format("%1s",fillerString);
			mergedString = mergedString +StringUtils.rightPad(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDeptdesc"),50);
			mergedString = mergedString +String.format("%1s",fillerString);
			mergedString = mergedString +StringUtils.rightPad(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDesg"),5);
			mergedString = mergedString +String.format("%1s",fillerString);
			mergedString = mergedString +StringUtils.rightPad(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpDesgdesc"),50);
			mergedString = mergedString +String.format("%1s",fillerString);
			mergedString = mergedString +String.format("%05d",(int)LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpBasic"));
			//mergedString = mergedString +(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpBasic"));
			
			mergedString = mergedString +String.format("%1s",fillerString);
			mergedString = mergedString +String.format("%05d",(int)LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpHra"));
			mergedString = mergedString +String.format("%1s",fillerString);
			mergedString = mergedString +String.format("%07d",(int)LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpGrosspay"));
			String outputFileName ="OUTPUT-RECORD";
			WriteUtil writeUtil = new WriteUtil();
			writeUtil.execute(folderPath,outputFileName,mergedString);

			//DISPLAY 'WRITE-OUTFILE - END'.
			logger.info("WRITE-OUTFILE - END");

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void prProgramExit(PR_Emplist emplist){
		try{
			//DISPLAY '9999-PROGRAM-EXIT - start'.
			logger.info("9999-PROGRAM-EXIT - start");

			//CLOSE OUTPUT-FILE.

			// TODO:EXEC Keyword Not Handled: EXEC SQL disconnect all; END-EXEC
			//DISPLAY 'DESIGNATION COUNT ' DESG-COUNT.
			logger.info("DESIGNATION COUNT "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistwscounters,"desgCount"));

			//DISPLAY 'DEPARTMENT COUNT ' DEPT-COUNT.
			logger.info("DEPARTMENT COUNT "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistwscounters,"deptCount"));

			//DISPLAY 'EMPLOYEE COUNT ' EMP-COUNT.
			logger.info("EMPLOYEE COUNT "+LowCodeUtility.getMethod(emplist.emplistPojo.emplistwscounters,"empCount"));

			//DISPLAY '9999-PROGRAM-EXIT - end'. STOP RUN.
			logger.info("9999-PROGRAM-EXIT - end");

		}
		catch(Exception e){}
	}
}
