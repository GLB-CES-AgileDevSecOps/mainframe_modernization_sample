package com.emplist.batch.copybooks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emplist.batch.util.ObjInfo;
import com.emplist.batch.util.ObjInfo.DataType;
@Component
public class SqlcaPojo{
    public SqlcaPojo(){


    }

    @Autowired
    public SqlcaSqlca sqlcasqlca;


    public void initialize(){
	sqlcasqlca.initialize();

    }
    @Component
    public class SqlcaSqlerrm{

	public SqlcaSqlerrm(){
	    sqlerrml = 0;
	    sqlerrmc = " ";


	}
	@ObjInfo(length = 4, dataType = DataType.INT)
	private int sqlerrml;
	@ObjInfo(length = 70, dataType = DataType.STRING)
	private String sqlerrmc;


	public void initialize(){
	    sqlerrml = 0;
	    sqlerrmc = " ";

	}

    } 
    @Component
    public class SqlcaSqlext{

	public SqlcaSqlext(){
	    sqlwarn8 = " ";
	    sqlwarn9 = " ";
	    sqlwarna = " ";
	    sqlstate = " ";


	}
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn8;
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn9;
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarna;
	@ObjInfo(length = 5, dataType = DataType.STRING)
	private String sqlstate;


	public void initialize(){
	    sqlwarn8 = " ";
	    sqlwarn9 = " ";
	    sqlwarna = " ";
	    sqlstate = " ";

	}

    } 
    @Component
    public class SqlcaSqlca{

	public SqlcaSqlca(){
	    sqlcaid = " ";
	    sqlcabc = 0;
	    sqlcode = 0;
	    sqlerrp = " ";


	}
	@ObjInfo(length = 8, dataType = DataType.STRING)
	private String sqlcaid;
	@ObjInfo(length = 9, dataType = DataType.INT)
	private int sqlcabc;
	@ObjInfo(length = 9, dataType = DataType.INT)
	private int sqlcode;
	@Autowired
	public SqlcaSqlerrm sqlcasqlerrm;

	@ObjInfo(length = 8, dataType = DataType.STRING)
	private String sqlerrp;
	@ObjInfo(size = 6, length = 9, dataType = DataType.INT)
	public int[] sqlerrd = new int[6];
	@Autowired
	public SqlcaSqlwarn sqlcasqlwarn;

	@Autowired
	public SqlcaSqlext sqlcasqlext;



	public void initialize(){
	    sqlcaid = " ";
	    sqlcabc = 0;
	    sqlcode = 0;
	    sqlcasqlerrm.initialize();
	    sqlerrp = " ";
	    sqlcasqlwarn.initialize();
	    sqlcasqlext.initialize();

	}

    } 
    @Component
    public class SqlcaSqlwarn{

	public SqlcaSqlwarn(){
	    sqlwarn0 = " ";
	    sqlwarn1 = " ";
	    sqlwarn2 = " ";
	    sqlwarn3 = " ";
	    sqlwarn4 = " ";
	    sqlwarn5 = " ";
	    sqlwarn6 = " ";
	    sqlwarn7 = " ";


	}
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn0;
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn1;
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn2;
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn3;
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn4;
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn5;
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn6;
	@ObjInfo(length = 1, dataType = DataType.STRING)
	private String sqlwarn7;


	public void initialize(){
	    sqlwarn0 = " ";
	    sqlwarn1 = " ";
	    sqlwarn2 = " ";
	    sqlwarn3 = " ";
	    sqlwarn4 = " ";
	    sqlwarn5 = " ";
	    sqlwarn6 = " ";
	    sqlwarn7 = " ";

	}

    }
}