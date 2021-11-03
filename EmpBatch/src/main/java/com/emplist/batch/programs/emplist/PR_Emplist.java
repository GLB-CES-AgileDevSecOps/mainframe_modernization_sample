package com.emplist.batch.programs.emplist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.emplist.batch.constants.AppConstants;
import com.emplist.batch.copybooks.SqlcaPojo;
import com.emplist.batch.copybooks.emplistPojo;
import com.emplist.batch.dao.EmplistDAO;
import com.emplist.batch.dao.EmplistDAOImpl;
import com.emplist.batch.util.LowCodeUtility;

@Service
@Component
public class PR_Emplist {
	final static Logger logger = LogManager.getLogger(PR_Emplist.class);
	double addValue = 0;
	@Autowired
	emplistPojo emplistPojo;
	@Autowired
	PR_Emplistp1 emplistp1;
	@Autowired
	EmplistDAO emplistdao;
	@Autowired
	SqlcaPojo sqlcaPojo;

	public void initialize() {
		emplistPojo = new emplistPojo();
		emplistp1 = new PR_Emplistp1();
		emplistp1.initialize();
		emplistdao = new EmplistDAOImpl();
		sqlcaPojo = new SqlcaPojo();
	}

	public void execute(PR_Emplist emplist) {
		try {
			prInitPara(emplist);

			LowCodeUtility.setMethod(this.emplistPojo, "wsRepoDept", "BNFSC");
			prProcessData(emplist);
			emplistp1.prProgramExit(emplist);

			logger.info("END OF EMPLOYEE REPORT");

		} catch (Exception e) {
		}
	}

	public void prInitPara(PR_Emplist emplist) {
		try {
			// DISPLAY '1000-INIT - start'.
			logger.info("1000-INIT - start");

			// PERFORM CONNECT-TO-DATABASE.
			prConnectToDatabase(emplist);

			// PERFORM EMPLOYEE-CURSOR-DECLARE.
			prEmployeeCursorDeclare(emplist);

			// PERFORM EMPLOYEE-CURSOR-OPEN.
			prEmployeeCursorOpen(emplist);

			// OPEN OUTPUT OUTPUT-FILE.

			// DISPLAY '1000-INIT - END'.
			logger.info("1000-INIT - END");

		} catch (Exception e) {
		}
	}

	public void prProcessData(PR_Emplist emplist) {
		try {
			// DISPLAY 'PROCESS-DATA - start'.
			logger.info("PROCESS-DATA - start");

			// PERFORM EMPLOYEE-CURSOR-FETCH
			prEmployeeCursorFetch(emplist);

			// PERFORM DEPTCODE-RETRIEVAL.
			emplistp1.prDeptcodeRetrieval(emplist);

			// PERFORM DESGCODE-RETRIEVAL.
			emplistp1.prDesgcodeRetrieval(emplist);

			// PERFORM WRITE-OUTFILE.
			emplistp1.prWriteOutfile(emplist);

		} catch (Exception e) {
			logger.error("Error executing Process para :", e);

		}
	}

	public void prConnectToDatabase(PR_Emplist emplist) {
		try {
			// DISPLAY 'CONNECT-TO-DATABASE - START'.
			logger.info("CONNECT-TO-DATABASE - START");

			// TODO:EXEC Keyword Not Handled: EXEC SQL CONNECT TO :DATABASE-NAME USER

			logger.info("CONNECT-TO-DATABASE - END");

		} catch (Exception e) {
		}
	}

	public void prEmployeeCursorDeclare(PR_Emplist emplist) {
		try {
			// DISPLAY 'EMPLOYEE-CURSOR-DECLARE - START'.
			logger.info("EMPLOYEE-CURSOR-DECLARE - START");

			// TODO:EXEC Keyword Not Handled: EXEC SQL declare cur_employee cursor for
			// select empid, empname, deptcode, desgcode, location from employee END-EXEC.
			String sqlQuery = AppConstants.SELECT_CURSOR_EMPLOYEE_CURSOR_DECLARE;
			List<Object> parameterList = new ArrayList<Object>();

			// parameterList.add(LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpDept"));
			List<Map<String, Object>> dbRows = emplistdao.prEmployeeCursorDeclare(sqlQuery, parameterList);
			// MOVE SQLCODE TO WS-SQLCODE.
			LowCodeUtility.copyElementaryToGroup(
					LowCodeUtility.getMethod(this.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,
							"emplistwssqlcode"),
					LowCodeUtility.getMethod(this.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,
							"emplistwssqlcode"));
			// IF (WS-SQL-RETURN-NORMAL){
			if (((boolean) LowCodeUtility.getValueForBoolean(this.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,
					"wsSqlReturnNormal"))) {
				// MOVE 'F' TO WS-EMPCURVAL
				LowCodeUtility.copyElementaryToGroup("F",
						LowCodeUtility.getMethod(this.emplistPojo.emplistwstemp, "emplistwsempcurval"));
				// ELSE IF (WS-SQL-RETURN-NO-ENTRY)
			} else if (((boolean) LowCodeUtility.getValueForBoolean(
					this.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode, "wsSqlReturnNoEntry"))) {
				// MOVE 'N' TO WS-EMPCURVAL
				LowCodeUtility.copyElementaryToGroup("N",
						LowCodeUtility.getMethod(this.emplistPojo.emplistwstemp, "emplistwsempcurval"));
				// DISPLAY 'NOT FOUND - EMPLOYEE CURSOR DECLARE - ' WS-REPO-DEPT
				logger.info("NOT FOUND - EMPLOYEE CURSOR DECLARE - "
						+ LowCodeUtility.getMethod(this.emplistPojo, "wsRepoDept"));

				// ELSE
			} else {
				// DISPLAY 'DB2 ERROR - EMPLOYEE CURSOR DECLARE - ' WS-REPO-DEPT
				logger.info("DB2 ERROR - EMPLOYEE CURSOR DECLARE - "
						+ LowCodeUtility.getMethod(this.emplistPojo, "wsRepoDept"));

				// MOVE 'E' TO WS-EMPCURVAL
				LowCodeUtility.copyElementaryToGroup("E",
						LowCodeUtility.getMethod(this.emplistPojo.emplistwstemp, "emplistwsempcurval"));
			}
			// DISPLAY 'EMPLOYEE-CURSOR-DECLARE - END'.
			logger.info("EMPLOYEE-CURSOR-DECLARE - END");

		} catch (Exception e) {
		}
	}

	public void prEmployeeCursorOpen(PR_Emplist emplist) {
		try {
			// DISPLAY 'EMPLOYEE-CURSOR-OPEN - START'.
			logger.info("EMPLOYEE-CURSOR-OPEN - START");

			// TODO:EXEC Keyword Not Handled: EXEC SQL open cur_employee; END-EXEC.
			// MOVE SQLCODE TO WS-SQLCODE.

			// IF (WS-SQL-RETURN-NORMAL){
			if (((boolean) LowCodeUtility.getValueForBoolean(this.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,
					"wsSqlReturnNormal"))) {
				// MOVE 'F' TO WS-EMPCURVAL
				LowCodeUtility.copyElementaryToGroup("F",
						LowCodeUtility.getMethod(this.emplistPojo.emplistwstemp, "emplistwsempcurval"));
				// ELSE IF (WS-SQL-RETURN-NO-ENTRY)
			} else if (((boolean) LowCodeUtility.getValueForBoolean(
					this.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode, "wsSqlReturnNoEntry"))) {
				// MOVE 'N' TO WS-EMPCURVAL
				LowCodeUtility.copyElementaryToGroup("N",
						LowCodeUtility.getMethod(this.emplistPojo.emplistwstemp, "emplistwsempcurval"));
				// DISPLAY 'NOT FOUND - EMPLOYEE CURSOR OPEN - ' WS-REPO-DEPT
				logger.info("NOT FOUND - EMPLOYEE CURSOR OPEN - "
						+ LowCodeUtility.getMethod(this.emplistPojo, "wsRepoDept"));

				// ELSE
			} else {
				// DISPLAY 'DB2 ERROR - EMPLOYEE CURSOR OPEN - ' WS-REPO-DEPT
				logger.info("DB2 ERROR - EMPLOYEE CURSOR OPEN - "
						+ LowCodeUtility.getMethod(this.emplistPojo, "wsRepoDept"));

				// MOVE 'E' TO WS-EMPCURVAL
				LowCodeUtility.copyElementaryToGroup("E",
						LowCodeUtility.getMethod(this.emplistPojo.emplistwstemp, "emplistwsempcurval"));
			}
			// DISPLAY 'EMPLOYEE-CURSOR-OPEN - END'.
			logger.info("EMPLOYEE-CURSOR-OPEN - END");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prEmployeeCursorFetch(PR_Emplist emplist) {
		try {
			// DISPLAY 'EMPLOYEE-CURSOR-FETCH - START'.
			logger.info("EMPLOYEE-CURSOR-FETCH - START");

			// TODO:EXEC Keyword Not Handled: EXEC SQL fetch next cur_employee into
			// :WS-EMP-ID , :WS-EMP-NAME, :WS-EMP-DEPT, :WS-EMP-DESG, :WS-EMP-LOC; END-EXEC.
			String sqlQuery = AppConstants.SELECT_CURSOR_EMPLOYEE_CURSOR_DECLARE;
			List<Object> parameterList = new ArrayList<Object>();

			// parameterList.add(LowCodeUtility.getMethod(emplist.emplistPojo,"wsEmpDept"));
			List<Map<String, Object>> dbRows = emplistdao.prEmployeeCursorDeclare(sqlQuery, parameterList);
			if (!dbRows.isEmpty()) {
				for (Map resultSet : dbRows) {
					/*
					 * LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsEmpId",
					 * resultSet.get("empid"));
					 * LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsEmpName"
					 * ,resultSet.get("empname"));
					 * LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsEmpDept"
					 * ,resultSet.get("deptcode"));
					 * LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsEmpDesg"
					 * ,resultSet.get("desgcode"));
					 * LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord,"wsEmpLoc",
					 * resultSet.get("location"));
					 */
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord, "wsOutEmpId",
							resultSet.get("empid"));
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord, "wsOutEmpName",
							resultSet.get("empname"));
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord, "wsOutEmpDept",
							resultSet.get("deptcode"));
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord, "wsOutEmpDesg",
							resultSet.get("desgcode"));
					LowCodeUtility.setMethod(emplist.emplistPojo.emplistoutfilerecord, "wsOutEmpLoc",
							resultSet.get("location"));

				}

			} else {
				// LowCodeUtility.setValueForBoolean(emplist.emplistPojo.emplistwstemp.emplistwsempcurval,"empcNotfnd",true);
			}

			// MOVE SQLCODE TO WS-SQLCODE.

			// IF (WS-SQL-RETURN-NORMAL){
			if (((boolean) LowCodeUtility.getValueForBoolean(this.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode,
					"wsSqlReturnNormal"))) {
				// ADD 1 TO EMP-COUNT
				addValue = (int) LowCodeUtility.getMethod(this.emplistPojo.emplistwscounters, "empCount") + (int) 1;
				LowCodeUtility.setMethod(this.emplistPojo.emplistwscounters, "empCount", addValue);
				// MOVE 'F' TO WS-EMPCURVAL
				LowCodeUtility.copyElementaryToGroup("F",
						LowCodeUtility.getMethod(this.emplistPojo.emplistwstemp, "emplistwsempcurval"));
				// ELSE IF (WS-SQL-RETURN-NO-ENTRY)
			} else if (((boolean) LowCodeUtility.getValueForBoolean(
					this.emplistPojo.emplistwssqlcodeanalysis.emplistwssqlcode, "wsSqlReturnNoEntry"))) {
				// MOVE 'N' TO WS-EMPCURVAL
				LowCodeUtility.copyElementaryToGroup("N",
						LowCodeUtility.getMethod(this.emplistPojo.emplistwstemp, "emplistwsempcurval"));
				// DISPLAY 'NOT FOUND - EMPLOYEE CURSOR FETCH - ' WS-REPO-DEPT
				logger.info("NOT FOUND - EMPLOYEE CURSOR FETCH - "
						+ LowCodeUtility.getMethod(this.emplistPojo, "wsRepoDept"));

				// ELSE
			} else {
				// DISPLAY 'DB2 ERROR - EMPLOYEE CURSOR FETCH - ' WS-REPO-DEPT
				logger.info("DB2 ERROR - EMPLOYEE CURSOR FETCH - "
						+ LowCodeUtility.getMethod(this.emplistPojo, "wsRepoDept"));

				// MOVE 'E' TO WS-EMPCURVAL
				LowCodeUtility.copyElementaryToGroup("E",
						LowCodeUtility.getMethod(this.emplistPojo.emplistwstemp, "emplistwsempcurval"));
			}
			// DISPLAY 'EMPLOYEE-CURSOR-FETCH - END'.
			logger.info("EMPLOYEE-CURSOR-FETCH - END");

		} catch (Exception e) {
		}
	}
}
