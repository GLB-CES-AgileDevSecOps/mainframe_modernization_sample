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

			//DISPLAY 'DEPTCODE-RETRIEVAL - START'.




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









				LowCodeUtility.setMethod(emplist.emplistPojo.emplistwscounters,"deptCount",addValue);


			else if(((boolean) LowCodeUtility.getValueForBoolean(emplist.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,"wsSqlReturnNoEntry") )){







			else {











			e.printStackTrace();
		}
		


			//DISPLAY 'DESGCODE-RETRIEVAL - START'.




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









				LowCodeUtility.setMethod(emplist.emplistPojo.emplistwscounters,"desgCount",addValue);


			else if(((boolean) LowCodeUtility.getValueForBoolean(emplist.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,"wsSqlReturnNoEntry") )){







			else {










			e.printStackTrace();
		}



			//DISPLAY 'WRITE-OUTFILE - START'.

//			LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpId",resultSet.get("empid"));




















































			String folderPath="D:\\temp\\Output";
			String fillerString = ",";
			String mergedString = "";
			mergedString = mergedString +StringUtils.rightPad(LowCodeUtility.getMethod(emplist.emplistPojo.emplistoutfilerecord,"wsOutEmpId"),5);















			
			mergedString = mergedString +String.format("%1s",fillerString);




			WriteUtil writeUtil = new WriteUtil();
			writeUtil.execute(folderPath,outputFileName,mergedString);






			e.printStackTrace();
		}



			//DISPLAY '9999-PROGRAM-EXIT - start'.





			//DISPLAY 'DESIGNATION COUNT ' DESG-COUNT.














