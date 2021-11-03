package springapp.spring.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import springapp.spring.constant.AppConstants;
import springapp.spring.copybook.EmplistPojo;
import springapp.spring.dao.EmpListMasterDAO;
import springapp.spring.dao.EmpListMasterDAOImpl;
import springapp.spring.util.LowCodeUtility;

public class EmpListMaster {
	final static Logger logger = LogManager.getLogger(EmpListMaster.class);
	@Autowired
	public EmplistPojo emplistPojo;
	@Autowired
	EmpListMasterDAO emplistdao;
	
	public void initialize() {
		emplistPojo = new EmplistPojo();
		emplistdao = new EmpListMasterDAOImpl();
	}
	
	public EmpListMaster() {
		this.initialize();
	}

	public EmpListMaster getDesignationRecord(EmpListMaster empListPgm) throws Exception {
		String sqlQuery =AppConstants.SELECT_DESIGNATION_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDesgcd"));
		int grossPay = 0;
			List<Map<String, Object>> dbRows = this.emplistdao.desgcodeRetrieval(sqlQuery,parameterList);
			if(!dbRows.isEmpty()) {
				for (Map resultSet : dbRows) {
					grossPay = Integer.parseInt(resultSet.get("BASICPAY").toString())
						+ Integer.parseInt(resultSet.get("HRA").toString());
				LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpDesgdesc",
						resultSet.get("DESGDESC").toString());
				LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpBasic", resultSet.get("BASICPAY").toString());
				LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpHra", resultSet.get("HRA").toString());
				LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpGrosspay", String.valueOf(grossPay));}
	
			} else {
				LowCodeUtility.setMethod(empListPgm.emplistPojo, "responseCode", 13);
			}
		
		
		
		return empListPgm;

	}

	public EmpListMaster getDepartmentRecord(EmpListMaster empListPgm) throws Exception {
		
		String sqlQuery =AppConstants.SELECT_DEPARTMENT_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDeptcode") );
			List<Map<String, Object>> dbRows = this.emplistdao.deptcodeRetrieval(sqlQuery,parameterList);
			if(!dbRows.isEmpty()) {
				for (Map resultSet : dbRows) {
					LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpDeptdesc", resultSet.get("DEPTDESC").toString());

					}
	
			} else {
				LowCodeUtility.setMethod(empListPgm.emplistPojo, "responseCode", 13);
			}
		
		

		return empListPgm;

	}


	public EmpListMaster getEmpRecord(EmpListMaster obj) throws Exception {
		String sqlQuery =AppConstants.SELECT_EMPLOYEE_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(LowCodeUtility.getMethod(obj.emplistPojo, "wsEmpcode"));
			List<Map<String, Object>> dbRows = this.emplistdao.empcodeRetrieval(sqlQuery,parameterList);
			if(!dbRows.isEmpty()) {
				for (Map resultSet : dbRows) {
					LowCodeUtility.setMethod(obj.emplistPojo, "wsEmpDeptdesc", resultSet.get("empname").toString());
					LowCodeUtility.setMethod(obj.emplistPojo, "wsEmpDeptdesc", resultSet.get("deptcode").toString());
					LowCodeUtility.setMethod(obj.emplistPojo, "wsEmpDeptdesc", resultSet.get("desgcode").toString());
					LowCodeUtility.setMethod(obj.emplistPojo, "wsEmpDeptdesc", resultSet.get("location").toString());

					}
	
			} else {
				LowCodeUtility.setMethod(obj.emplistPojo, "responseCode", 13);
			}
		
		
		return obj;

	}
	
	
	public EmpListMaster addDesignationRecord(EmpListMaster empListPgm) throws Exception {
		
		String basic = (LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpBasic")).toString();
		String hra = (LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpHra")).toString();
		
		String sqlQuery=AppConstants.INSERT_DESIGNATION_RECORD;
		List<Object> parameterList = new ArrayList<Object>();

		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDesgcd").toString());
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpDesgdesc").toString());
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpBasic").toString());
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpHra").toString());
		parameterList.add(String.valueOf(Integer.parseInt(basic) + Integer.parseInt(hra)));
		this.emplistdao.addDesgnDetails(sqlQuery,parameterList);

		
		
		return empListPgm;
	}

	public EmpListMaster updateDesignationRecord(EmpListMaster empListPgm) throws Exception {
		String basic = (LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpBasic")).toString();
		String hra = (LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpHra")).toString();
		int grossPay = Integer.parseInt(basic) + Integer.parseInt(hra);
		String sqlQuery = AppConstants.UPDATE_DESIGNATION_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpDesgdesc"));
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpBasic"));
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpHra") );
		parameterList.add(grossPay);
		parameterList.add( LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDesgcd"));

		this.emplistdao.updateDesgnDetails(sqlQuery,parameterList);

		
		
		return empListPgm;
	}

	public EmpListMaster deleteDesignationRecord(EmpListMaster empListPgm) throws Exception {
		
		String sqlQuery = AppConstants.DELETE_DESIGNATION_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add( LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDesgcd"));
		
		this.emplistdao.deleteDesgnDetails(sqlQuery,parameterList);
		
		
		return empListPgm;
	}

	public EmpListMaster addDepartmentRecord(EmpListMaster empListPgm) throws Exception {
		
		String sqlQuery=AppConstants.INSERT_DEPARTMENT_RECORD;
		List<Object> parameterList = new ArrayList<Object>();

		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDeptcode").toString());
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpDeptdesc").toString());
		
		this.emplistdao.addDeptDetails(sqlQuery,parameterList);

		return empListPgm;
	}

	public EmpListMaster updateDepartmentRecord(EmpListMaster empListPgm) throws Exception {
		
		String sqlQuery = AppConstants.UPDATE_DEPARTMENT_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpDeptdesc").toString());
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDeptcode").toString());
		

		this.emplistdao.updateDeptDetails(sqlQuery,parameterList);

		
		return empListPgm;
	}

	public EmpListMaster deleteDepartmentRecord(EmpListMaster empListPgm) throws Exception {
		String sqlQuery = AppConstants.DELETE_DEPARTMENT_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDeptcode").toString());
		
		this.emplistdao.deleteDeptDetails(sqlQuery,parameterList);
		
		
		return empListPgm;
	}

	public EmpListMaster getEmployeeRecord(EmpListMaster empListPgm) throws Exception {
		
		String sqlQuery =AppConstants.SELECT_EMPLOYEEDETAILS_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpId").toString());
			List<Map<String, Object>> dbRows = this.emplistdao.empDetailRetrieval(sqlQuery,parameterList);
			if(!dbRows.isEmpty()) {
				for (Map resultSet : dbRows) {
					LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpName",
							resultSet.get("empname").toString());
					LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsDeptcode", resultSet.get("deptcode").toString());
					LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsDesgcd", resultSet.get("desgcode").toString());
					LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpLoc", resultSet.get("location").toString());
					if(null==resultSet.get("DESGDESC")) {
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpDesgdesc", " ");
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpBasic", " ");
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpHra"," ");
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpGrosspay", " ");
						
					}else {
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpDesgdesc", resultSet.get("DESGDESC").toString());
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpBasic", resultSet.get("BASICPAY").toString());
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpHra", resultSet.get("HRA").toString());
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpGrosspay", resultSet.get("grosspay").toString());
					}
					if(null==resultSet.get("deptdesc")) {
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpDeptdesc", " ");
					}else
						LowCodeUtility.setMethod(empListPgm.emplistPojo, "wsEmpDeptdesc", resultSet.get("deptdesc").toString());
					

				}
	
			} else {
				LowCodeUtility.setMethod(empListPgm.emplistPojo, "responseCode", 13);
			}
		
	

		return empListPgm;

	}

	public EmpListMaster addEmployeeRecord(EmpListMaster empListPgm) throws Exception {
		
		String basic = (LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpBasic")).toString();
		String hra = (LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpHra")).toString();
		
		String sqlQuery=AppConstants.INSERT_EMPLOYEE_RECORD;
		List<Object> parameterList = new ArrayList<Object>();

		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpId").toString());
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpName").toString());
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDeptcode").toString());
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsDesgcd").toString());
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpLoc").toString());
		
		this.emplistdao.addEmployeeDetails(sqlQuery,parameterList);
		
		return empListPgm;
	}

	public EmpListMaster updateEmployeeRecord(EmpListMaster emplList) throws Exception {
		
		String sqlQuery = AppConstants.UPDATE_EMPLOYEE_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(LowCodeUtility.getMethod(emplList.emplistPojo, "wsEmpName").toString());
		parameterList.add(LowCodeUtility.getMethod(emplList.emplistPojo, "wsDeptcode").toString());
		parameterList.add(LowCodeUtility.getMethod(emplList.emplistPojo, "wsDesgcd").toString());
		parameterList.add(LowCodeUtility.getMethod(emplList.emplistPojo, "wsEmpLoc").toString() );
		parameterList.add( LowCodeUtility.getMethod(emplList.emplistPojo, "wsEmpId").toString());

		this.emplistdao.updateEmployeeDetails(sqlQuery,parameterList);

		return emplList;
	}

	public EmpListMaster deleteEmployeeRecord(EmpListMaster empListPgm) throws Exception {
		String sqlQuery = AppConstants.DELETE_EMPLOYEE_RECORD;
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(LowCodeUtility.getMethod(empListPgm.emplistPojo, "wsEmpId"));
		
		this.emplistdao.deleteEmpDetails(sqlQuery,parameterList);
		
		
		return empListPgm;
	}

}
