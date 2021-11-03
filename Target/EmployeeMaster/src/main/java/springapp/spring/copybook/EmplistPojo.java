package springapp.spring.copybook;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import springapp.spring.util.ObjInfo;
import springapp.spring.util.ObjInfo.DataType;




@Component
public class EmplistPojo{

	public EmplistPojo(){
		outputRecord = " ";
		userName = " ";
		password = " ";
		wsDesgcd = " ";
		wsDeptcode = " ";
		wsRepoDept = " ";
		wsEmpId = " ";
		wsEmpName = " ";
		wsEmpLoc = " ";
		wsEmpDept = " ";
		wsEmpDeptdesc = " ";
		wsEmpDesg = " ";
		wsEmpDesgdesc = " ";
		wsEmpBasic = "";
		wsEmpHra = "";
		wsEmpGrosspay ="";
		responseCode=0;
		actiono="";

	}
	
@ObjInfo(length = 250, dataType = DataType.INT)
	private int responseCode;
@ObjInfo(length = 250, dataType = DataType.STRING)
	private String actiono;
@ObjInfo(length = 250, dataType = DataType.STRING)
	private String outputRecord;

@ObjInfo(length = 80, dataType = DataType.STRING)
	private String userName = "syntel";
@ObjInfo(length = 80, dataType = DataType.STRING)
	private String password = "syntel";
@ObjInfo(length = 5, dataType = DataType.STRING)
	private String wsDesgcd = "\\s+";
@ObjInfo(length = 5, dataType = DataType.STRING)
	private String wsDeptcode;

@ObjInfo(length = 5, dataType = DataType.STRING)
	private String wsRepoDept;

@ObjInfo(length = 5, dataType = DataType.STRING)
	private String wsEmpId;

@ObjInfo(length = 30, dataType = DataType.STRING)
	private String wsEmpName;

@ObjInfo(length = 30, dataType = DataType.STRING)
	private String wsEmpLoc;

@ObjInfo(length = 5, dataType = DataType.STRING)
	private String wsEmpDept;

@ObjInfo(length = 50, dataType = DataType.STRING)
	private String wsEmpDeptdesc;

@ObjInfo(length = 5, dataType = DataType.STRING)
	private String wsEmpDesg;

@ObjInfo(length = 50, dataType = DataType.STRING)
	private String wsEmpDesgdesc;

@ObjInfo(length = 5, dataType = DataType.INT)
	private String wsEmpBasic;

@ObjInfo(length = 5, dataType = DataType.INT)
	private String wsEmpHra;

@ObjInfo(length = 7, dataType = DataType.INT)
	private String wsEmpGrosspay;

	public PR_EmplistWsSqlcodeAnalysis emplistwssqlcodeanalysis = new PR_EmplistWsSqlcodeAnalysis();

	public PR_EmplistWsTemp emplistwstemp = new PR_EmplistWsTemp();

	public PR_EmplistOutFileRecord emplistoutfilerecord = new PR_EmplistOutFileRecord();

	public PR_EmplistWsCounters emplistwscounters = new PR_EmplistWsCounters();



	public void initialize(){
		outputRecord = " ";
		userName = " ";
		password = " ";
		wsDesgcd = " ";
		wsDeptcode = " ";
		wsRepoDept = " ";
		wsEmpId = " ";
		wsEmpName = " ";
		wsEmpLoc = " ";
		wsEmpDept = " ";
		wsEmpDeptdesc = " ";
		wsEmpDesg = " ";
		wsEmpDesgdesc = " ";
		wsEmpBasic = "";
		wsEmpHra = "";
		wsEmpGrosspay = "";
		responseCode=0;
		actiono="";
		emplistwssqlcodeanalysis.initialize();
		emplistwstemp.initialize();
		emplistoutfilerecord.initialize();
		emplistwscounters.initialize();

	
}
public class PR_EmplistWsSqlcodeAnalysis{

	public PR_EmplistWsSqlcodeAnalysis(){


	}
	public PR_EmplistWsSqlcode emplistwssqlcode = new PR_EmplistWsSqlcode();



	public void initialize(){
		emplistwssqlcode.initialize();

	
}
	public EmplistPojo getemplistPojo() {
	    return EmplistPojo.this;
	}
		
}
public class PR_EmplistWsSqlcode{

@ObjInfo(length = 9, dataType = DataType.INT)
	private int wsSqlcode;

	public boolean wsSqlReturnNormal;

	public boolean wsSqlReturnNoEntry;

	public boolean wsSqlReturnInvaldDatetime;

	public boolean wsSqlReturnDupkey;

	public boolean wsSqlReturnMultResult;

	public boolean wsSqlReturnTimeout;

	public boolean wsSqlTimeoutNoRollback;

	public boolean wsSqlReturnError;

	public  int value;

	public ArrayList<Integer> wsSqlReturnNormalArrayList = new ArrayList<Integer>(Arrays.asList(0));

	public ArrayList<Integer> wsSqlReturnNoEntryArrayList = new ArrayList<Integer>(Arrays.asList(100));

	public ArrayList<Integer> wsSqlReturnInvaldDatetimeArrayList = new ArrayList<Integer>(Arrays.asList(-181));

	public ArrayList<Integer> wsSqlReturnDupkeyArrayList = new ArrayList<Integer>(Arrays.asList(-803));

	public ArrayList<Integer> wsSqlReturnMultResultArrayList = new ArrayList<Integer>(Arrays.asList(-811));

	public ArrayList<Integer> wsSqlReturnTimeoutArrayList = new ArrayList<Integer>(Arrays.asList(-911-913));

	public ArrayList<Integer> wsSqlTimeoutNoRollbackArrayList = new ArrayList<Integer>(Arrays.asList(-913));

	public  ArrayList<Integer> wsSqlReturnErrorArrayList = new ArrayList<Integer>();


	public void checkValue(Integer variableValue){
	wsSqlReturnNormal = wsSqlReturnNormalArrayList.contains(variableValue);
	wsSqlReturnNoEntry = wsSqlReturnNoEntryArrayList.contains(variableValue);
	wsSqlReturnInvaldDatetime = wsSqlReturnInvaldDatetimeArrayList.contains(variableValue);
	wsSqlReturnDupkey = wsSqlReturnDupkeyArrayList.contains(variableValue);
	wsSqlReturnMultResult = wsSqlReturnMultResultArrayList.contains(variableValue);
	wsSqlReturnTimeout = wsSqlReturnTimeoutArrayList.contains(variableValue);
	wsSqlTimeoutNoRollback = wsSqlTimeoutNoRollbackArrayList.contains(variableValue);
	wsSqlReturnError = wsSqlReturnErrorArrayList.contains(variableValue);
	}
	public void initialize(){
		wsSqlcode = 0;
		wsSqlReturnNormal = false;
		wsSqlReturnNoEntry = false;
		wsSqlReturnInvaldDatetime = false;
		wsSqlReturnDupkey = false;
		wsSqlReturnMultResult = false;
		wsSqlReturnTimeout = false;
		wsSqlTimeoutNoRollback = false;
		wsSqlReturnError = false;

			}
	public void initBooleanValue(int variableValue){
		if( wsSqlReturnNormalArrayList.contains(variableValue)){
		wsSqlReturnNormal = true;
		}
		if( wsSqlReturnNoEntryArrayList.contains(variableValue)){
		wsSqlReturnNoEntry = true;
		}
		if( wsSqlReturnInvaldDatetimeArrayList.contains(variableValue)){
		wsSqlReturnInvaldDatetime = true;
		}
		if( wsSqlReturnDupkeyArrayList.contains(variableValue)){
		wsSqlReturnDupkey = true;
		}
		if( wsSqlReturnMultResultArrayList.contains(variableValue)){
		wsSqlReturnMultResult = true;
		}
		if( wsSqlReturnTimeoutArrayList.contains(variableValue)){
		wsSqlReturnTimeout = true;
		}
		if( wsSqlTimeoutNoRollbackArrayList.contains(variableValue)){
		wsSqlTimeoutNoRollback = true;
		}
		if((variableValue >= -910 &&  variableValue<=-1) ||(variableValue >= 9999 &&  variableValue<=-914) || wsSqlReturnErrorArrayList.contains(variableValue)){
		wsSqlReturnError = true;
		}

	}
		}
public class PR_EmplistWsTemp{

	public PR_EmplistWsTemp(){


	}
	public PR_EmplistWsEmpcurval emplistwsempcurval = new PR_EmplistWsEmpcurval();

	public PR_EmplistWsDesgval pr_emplistwsdesgval = new PR_EmplistWsDesgval();

	public PR_EmplistWsDeptval pr_emplistwsdeptval = new PR_EmplistWsDeptval();



	public void initialize(){
		emplistwsempcurval.initialize();
		pr_emplistwsdesgval.initialize();
		pr_emplistwsdeptval.initialize();

	
}
		
}
public class PR_EmplistWsEmpcurval{

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String wsEmpcurval;

	public boolean empcExist;

	public boolean empcNotfnd;

	public boolean empcDberr;

	public  int value;

	public ArrayList<String> empcExistArrayList = new ArrayList<String>(Arrays.asList("F"));

	public ArrayList<String> empcNotfndArrayList = new ArrayList<String>(Arrays.asList("N"));

	public ArrayList<String> empcDberrArrayList = new ArrayList<String>(Arrays.asList("E"));


	public void setValue(String variableValue){
	empcExist = empcExistArrayList.contains(variableValue);
	empcNotfnd = empcNotfndArrayList.contains(variableValue);
	empcDberr = empcDberrArrayList.contains(variableValue);
	}
	public void initialize(){
		wsEmpcurval = " ";
		empcExist = false;
		empcNotfnd = false;
		empcDberr = false;

			}
	public void initBooleanValue(String variableValue){
		empcExist = empcExistArrayList.contains(variableValue);
		empcNotfnd = empcNotfndArrayList.contains(variableValue);
		empcDberr = empcDberrArrayList.contains(variableValue);

	}
		}
public class PR_EmplistWsDesgval{

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String wsDesgval;

	public boolean desgExist;

	public boolean desgNotfnd;

	public boolean desgDberr;

	public  int value;

	public ArrayList<String> desgExistArrayList = new ArrayList<String>(Arrays.asList("F"));

	public ArrayList<String> desgNotfndArrayList = new ArrayList<String>(Arrays.asList("N"));

	public ArrayList<String> desgDberrArrayList = new ArrayList<String>(Arrays.asList("E"));


	public void setValue(String variableValue){
	desgExist = desgExistArrayList.contains(variableValue);
	desgNotfnd = desgNotfndArrayList.contains(variableValue);
	desgDberr = desgDberrArrayList.contains(variableValue);
	}
	public void initialize(){
		wsDesgval = " ";
		desgExist = false;
		desgNotfnd = false;
		desgDberr = false;

			}
	public void initBooleanValue(String variableValue){
		desgExist = desgExistArrayList.contains(variableValue);
		desgNotfnd = desgNotfndArrayList.contains(variableValue);
		desgDberr = desgDberrArrayList.contains(variableValue);

	}
		}
public class PR_EmplistWsDeptval{

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String wsDeptval;

	public boolean deptExist;

	public boolean deptNotfnd;

	public boolean deptDberr;

	public  int value;

	public ArrayList<String> deptExistArrayList = new ArrayList<String>(Arrays.asList("F"));

	public ArrayList<String> deptNotfndArrayList = new ArrayList<String>(Arrays.asList("N"));

	public ArrayList<String> deptDberrArrayList = new ArrayList<String>(Arrays.asList("E"));


	public void setValue(String variableValue){
	deptExist = deptExistArrayList.contains(variableValue);
	deptNotfnd = deptNotfndArrayList.contains(variableValue);
	deptDberr = deptDberrArrayList.contains(variableValue);
	}
	public void initialize(){
		wsDeptval = " ";
		deptExist = false;
		deptNotfnd = false;
		deptDberr = false;

			}
	public void initBooleanValue(String variableValue){
		deptExist = deptExistArrayList.contains(variableValue);
		deptNotfnd = deptNotfndArrayList.contains(variableValue);
		deptDberr = deptDberrArrayList.contains(variableValue);

	}
		}
public class PR_EmplistOutFileRecord{

	public PR_EmplistOutFileRecord(){
		wsOutEmpId = " ";
		filler1 = " ";
		wsOutEmpName = " ";
		filler2 = " ";
		wsOutEmpLoc = " ";
		filler3 = " ";
		wsOutEmpDept = " ";
		filler4 = " ";
		wsOutEmpDeptdesc = " ";
		filler5 = " ";
		wsOutEmpDesg = " ";
		filler6 = " ";
		wsOutEmpDesgdesc = " ";
		filler7 = " ";
		wsOutEmpBasic = 0;
		filler8 = " ";
		wsOutEmpHra = 0;
		filler9 = " ";
		wsOutEmpGrosspay = 0;


	}
@ObjInfo(length = 5, dataType = DataType.STRING)
	private String wsOutEmpId;

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String filler1 = ",";
@ObjInfo(length = 30, dataType = DataType.STRING)
	private String wsOutEmpName;

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String filler2 = ",";
@ObjInfo(length = 30, dataType = DataType.STRING)
	private String wsOutEmpLoc;

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String filler3 = ",";
@ObjInfo(length = 5, dataType = DataType.STRING)
	private String wsOutEmpDept;

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String filler4 = ",";
@ObjInfo(length = 50, dataType = DataType.STRING)
	private String wsOutEmpDeptdesc;

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String filler5 = ",";
@ObjInfo(length = 5, dataType = DataType.STRING)
	private String wsOutEmpDesg;

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String filler6 = ",";
@ObjInfo(length = 50, dataType = DataType.STRING)
	private String wsOutEmpDesgdesc;

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String filler7 = "\\s+";
@ObjInfo(length = 5, dataType = DataType.INT)
	private int wsOutEmpBasic;

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String filler8 = "\\s+";
@ObjInfo(length = 5, dataType = DataType.INT)
	private int wsOutEmpHra;

@ObjInfo(length = 1, dataType = DataType.STRING)
	private String filler9 = "\\s+";
@ObjInfo(length = 7, dataType = DataType.INT)
	private int wsOutEmpGrosspay;



	public void initialize(){
		wsOutEmpId = " ";
		filler1 = " ";
		wsOutEmpName = " ";
		filler2 = " ";
		wsOutEmpLoc = " ";
		filler3 = " ";
		wsOutEmpDept = " ";
		filler4 = " ";
		wsOutEmpDeptdesc = " ";
		filler5 = " ";
		wsOutEmpDesg = " ";
		filler6 = " ";
		wsOutEmpDesgdesc = " ";
		filler7 = " ";
		wsOutEmpBasic = 0;
		filler8 = " ";
		wsOutEmpHra = 0;
		filler9 = " ";
		wsOutEmpGrosspay = 0;

	
}
		
}
public class PR_EmplistWsCounters{

	public PR_EmplistWsCounters(){
		empCount = 0;
		desgCount = 0;


	}
@ObjInfo(length = 10, dataType = DataType.INT)
	private int empCount = 0;
@ObjInfo(length = 10, dataType = DataType.INT)
	private int desgCount = 0;


	public void initialize(){
		empCount = 0;
		desgCount = 0;

	
}
		
}
public EmplistPojo getemplistPojo() {
	// TODO Auto-generated method stub
	return this.getemplistPojo();
}
}