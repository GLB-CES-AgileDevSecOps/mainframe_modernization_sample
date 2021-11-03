package springapp.spring.constant;

import springapp.spring.util.LowCodeUtility;

public interface AppConstants {
	String SELECT_DESIGNATION_RECORD="SELECT DESGDESC, BASICPAY, HRA, GROSSPAY FROM DESIGNATION WHERE DESGCODE = ?";
	String SELECT_DEPARTMENT_RECORD="SELECT DEPTDESC FROM DEPARTMENT WHERE DEPTCODE =   ?";
	String SELECT_EMPLOYEE_RECORD="SELECT empname, deptcode, desgcode, location FROM employee WHERE empid =  ?";
	String INSERT_DESIGNATION_RECORD="INSERT INTO DESIGNATION(desgcode, desgdesc, basicpay, hra, grosspay)VALUES(?,?,?,?,?)";
	String UPDATE_DESIGNATION_RECORD="update DESIGNATION set desgdesc=?, basicpay=?, hra=?, grosspay=? where desgcode=?";
	String DELETE_DESIGNATION_RECORD="DELETE FROM DESIGNATION WHERE desgcode = ?";
	String INSERT_DEPARTMENT_RECORD="INSERT INTO department(deptcode,deptdesc)VALUES(?,?)";
	String UPDATE_DEPARTMENT_RECORD="update department set deptdesc=? where deptcode=?";
	String DELETE_DEPARTMENT_RECORD="DELETE FROM department WHERE deptcode =  ?";
	String SELECT_EMPLOYEEDETAILS_RECORD = "SELECT e.empname,e.deptcode, e.desgcode,e.location, dept.deptdesc,d.desgdesc,d.basicpay,"
			+ "d.hra,d.grosspay FROM employee as e  left join department as dept ON e.deptcode=dept.deptcode left join "
			+ "designation as d ON e.desgcode=d.desgcode WHERE e.empid=?";
	String INSERT_EMPLOYEE_RECORD = "INSERT INTO employee(empid, empname,deptcode,desgcode,location)VALUES(?,?,?,?,?)";
	String UPDATE_EMPLOYEE_RECORD = "update employee set empname=?, deptcode=?, desgcode=?,location=? where empid =?";
	String DELETE_EMPLOYEE_RECORD = "DELETE FROM employee where empid = ?";
}
