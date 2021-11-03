package com.emplist.batch.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
public @interface ObjRelationship {

	
	ObjectRelationshipHandling RelHandling() default ObjectRelationshipHandling.NONE;
	
	DataHandling objHandling() default DataHandling.NONE;
	
	
	
	String RedefinedType() default "NONE";
	
	String RedefinedName() default "NONE";
	
	public enum ObjectRelationshipHandling {
		NONE,REDEFINES,REDEFINED 
	}
	
	public enum DataHandling {
		NONE,REDEFINES,REDEFINED
	}
}
