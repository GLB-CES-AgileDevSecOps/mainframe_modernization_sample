package com.emplist.batch.constants;
public interface AppConstants {
	/**
	*SQL Query Constant for para deptcodeRetrieval
	**/
	String SELECT_DEPTCODE_RETRIEVAL="SELECT DEPTDESC FROM DEPARTMENT WHERE DEPTCODE = ?";

	/**
	*SQL Query Constant for para desgcodeRetrieval
	**/
	String SELECT_DESGCODE_RETRIEVAL="SELECT DESGDESC, BASICPAY, HRA, GROSSPAY FROM DESIGNATION WHERE DESGCODE = ?";

	
	/**
	*SQL Query Constant for para prEmployeeCursorDeclare
	**/
	String SELECT_CURSOR_EMPLOYEE_CURSOR_DECLARE="select empid, empname, deptcode, desgcode, location from employee";
}