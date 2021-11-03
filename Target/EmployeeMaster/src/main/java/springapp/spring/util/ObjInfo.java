package springapp.spring.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ObjInfo {

	DataType dataType() default DataType.STRING;
	
	int length() default 0; //For variable length
	
	int precision() default 0; //For decimal variable length
	
	int size() default 0; //For array variable size
	
	DataHandling objHandling() default DataHandling.NONE;
	
	String RedefinedType() default "NONE";
	
	String RedefinedName() default "NONE";
	
	public enum DataType {
		INT,STRING,FLOAT,CHAR,DOUBLE,BOOLEAN,DATE
	}

	public enum DataLength {
		INT,STRING,FLOAT,CHAR,DOUBLE,BOOLEAN,DATE
	}
	
	public enum DataHandling {
		NONE,REDEFINES,REDEFINED
	}
}
