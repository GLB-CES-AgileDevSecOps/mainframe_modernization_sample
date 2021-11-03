package com.syntel.amazon;
import org.openqa.selenium.Keys;

import syntel.hwdriver.ExecutionInstance;
import syntel.hwdriver.Sessions;
import syntel.log.LogEntry;
import syntel.log.LogEntry.loggerLevel;

public class Amazon {
	String SessionID="";
	private Sessions ses;

	public Amazon(String sessionID) {
		super();
		SessionID = sessionID;
		ses = ExecutionInstance.getSession(SessionID);
	}



	public void testMethod() throws Exception
	{
	System.out.println("Welcome to xBRiD Framework");
	}
	
	// Login Method
	public void login() throws Exception{
		ses.getDriver().manage().window().maximize();
		ses.perform().sendKeys("userName","userName");
		ses.perform().sendKeys("password","password");
		ses.perform().click("signin");
	}
	
	//Create Department
	public void department() throws Exception{
		ses.perform().sendKeys("actionNo","actionNo");
		ses.perform().click("submit");
		ses.perform().sendKeys("action","action");
		ses.perform().sendKeys("deptcode","deptcode");
		ses.perform().sendKeys("deptDesc","deptDesc");
		ses.perform().click("proceed");
		ses.perform().jsGetValue("message");
		ses.perform().click("home");
		
	}
	
	//Delete Department
	public void departmentDelete() throws Exception{
		ses.perform().sendKeys("actionNo","actionNo");
		ses.perform().click("submit");
		ses.perform().sendKeys("action","action");
		ses.perform().sendKeys("deptcode","deptcode");
		ses.perform().click("proceed");
		ses.perform().jsGetValue("message");
		
	}
	
	//Create Designation
	public void designation() throws Exception{
		ses.perform().sendKeys("actionNo","actionNo");
		ses.perform().click("submit");
		ses.perform().sendKeys("action","action");
		ses.perform().sendKeys("desgncode","desgncode");
		ses.perform().click("submit");
		ses.perform().click("basicpay");
		ses.perform().jsKeyPress(Keys.BACK_SPACE, 5);
		ses.perform().sendKeys("basicpay","basicpay");
		ses.perform().click("proceed");
		ses.perform().jsGetValue("message");
		
		
	}
	
	//Add Employee
	public void addEmployee() throws Exception{
		ses.perform().sendKeys("actionNo","actionNo");
		ses.perform().click("submit");
		ses.perform().sendKeys("action","action");
		ses.perform().sendKeys("empcode","empcode");
		ses.perform().sendKeys("empname","empname");
		ses.perform().sendKeys("deptcode","deptcode");
		ses.perform().sendKeys("desgncode","desgncode");
		ses.perform().sendKeys("location","location");
		ses.perform().click("proceed");
		ses.perform().jsGetValue("message");
		ses.perform().click("home");
		
		
	}

	//Delete Employee 
	public void delete() throws Exception{
		ses.perform().sendKeys("actionNo","actionNo");
		ses.perform().click("submit");
		ses.perform().sendKeys("action","action");
		ses.perform().sendKeys("empcode","empcode");
		ses.perform().click("proceed");
		ses.perform().jsGetValue("message");
	}

}