package springapp.spring.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


import springapp.spring.util.ObjRelationship.DataHandling;  



/**
 * @author SD5045243 v5
 *
 */


public class LowCodeUtility {

	private int globalIndex = 0;	// instance variable

	private static LowCodeUtility locaInstance = new LowCodeUtility();

	// group to group move for ALL including array , Level 88 
	public static void copyObjectValues(Object source, Object target) throws Exception {
		LowCodeUtility copyValues = new LowCodeUtility();
		setMethod(copyValues, "globalIndex", 0);	
		try {
			/** get soruce group/ elementory values returns Striing */
			//				getObjValuesAsString(source.getClass(), source);
			
			createAndFillTargetValue(target.getClass(), target, getObjValuesAsString(source.getClass(), source), 0,copyValues);

		} catch (Exception e) {
			System.out.println("Exception :"+e);
			e.printStackTrace();
		}
	}


	public static void copyElementaryToGroup(String sourceValue, Object target) throws Exception {
		LowCodeUtility copyValues = new LowCodeUtility();
		setMethod(copyValues, "globalIndex", 0);	
		try {

			createAndFillTargetValue(target.getClass(), target, sourceValue, 0,copyValues);		
		} catch (Exception e) {
			System.out.println("Exception :"+e);
			e.printStackTrace();
		}
	}

	public static void copyGroupToElementary(Object sourceObject , Object targetObject ,String targetVariable ) throws Exception {
		LowCodeUtility copyValues = new LowCodeUtility();
		setMethod(copyValues, "globalIndex", 0);

		try {				
			setMethod(targetObject, targetVariable, getObjValuesAsString(sourceObject.getClass(), sourceObject));

		} catch (Exception e) {
			System.out.println("Exception :"+e);
		}
	}

	/** Positional group move */	
	public static void copyObjectValuesOnPosition(Object source, Object target , String sourcePosition , String targetPosition) throws Exception {
		LowCodeUtility copyValues = new LowCodeUtility();
		setMethod(copyValues, "globalIndex", 0);


		String sourcePos[] = sourcePosition.split(":");
		String targetPos[] = targetPosition.split(":");

		try {
			// get original source String
			String sourceString = getObjValuesAsString(source.getClass(), source);

			// get positional String
			sourceString = sourceString.substring((Integer.valueOf(sourcePos[0])-1), ((Integer.valueOf(sourcePos[0])-1)+(Integer.valueOf(sourcePos[1]))));


			// get original target String
			StringBuffer targetString = new StringBuffer();

			if(!targetPosition.isEmpty()) {
				targetString.append(getObjValuesAsString(target.getClass(), target));

				targetString = targetString.replace(Integer.valueOf(targetPos[0])-1, (Integer.valueOf(targetPos[0])-1+Integer.valueOf(targetPos[1])), sourceString);				

				createAndFillTargetValue(target.getClass(), target, targetString.toString(), 0,copyValues);
			}else {
				createAndFillTargetValue(target.getClass(), target, sourceString, 0,copyValues);
			}			

		} catch (Exception e) {
			throw e;
		}
	}


	public static <T> String getObjValuesAsString(Class<T> clazz, Object sourceObj) throws NullPointerException, Exception {	
		StringBuilder result = new StringBuilder();
		DataHandling objHandling = null;
		String redefinedType = null, redefinedName = null;
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			objHandling = null; redefinedType = null; redefinedName = null;
			if (field.isAnnotationPresent(ObjRelationship.class)) {
				objHandling = field.getAnnotation(ObjRelationship.class).objHandling();
				redefinedType = field.getAnnotation(ObjRelationship.class).RedefinedType();
				redefinedName = field.getAnnotation(ObjRelationship.class).RedefinedName();
			}
			if(DataHandling.REDEFINES.equals(objHandling)) {
				continue;
			}
			if (!field.getName().equals("this$0")) {
				Object value = getValueForField(field, sourceObj);

				try {

					result.append(value.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return result.toString();
	}


	/** get formatted type data base on field datatype*/

	public static Object getintTypeFieldValue(Object fieldValue, int fieldLength, int precision)  {
		try {
			return String.format("%0" + fieldLength + "d", Integer.valueOf(fieldValue.toString())).substring(0,fieldLength);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

	public static Object getlongTypeFieldValue(Object fieldValue, int fieldLength, int precision)  {
		try {
			return Long.parseLong(String.format("%0" + fieldLength + "d", Long.parseLong((fieldValue.toString()).substring(0,fieldLength))));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}

	/*
	 * public static Object getbooleanTypeFieldValue(Object fieldValue) { try {
	 * 
	 * }catch (Exception e) { e.printStackTrace(); } return null; }
	 */

	public static Object getdoubleTypeFieldValue(Object fieldValue, int fieldLength, int precision)  {
		try {
			if(precision>0) {
				if(fieldValue.toString().substring(0,(fieldLength+precision)).contains(".")){
					String dblSpltVal[]= fieldValue.toString().split("\\.");

					if(dblSpltVal[1].length()<=1) {
						return String.format("%." + precision + "f", Double.valueOf(fieldValue.toString().substring(0,(fieldLength+precision))));
					}else {
						return String.format("%." + precision + "f", Double.valueOf(fieldValue.toString().substring(0,(fieldLength+precision+1))));
					}
				}

			}else {
				return String.format("%0" + fieldLength + "d", (fieldValue.toString())).substring(0,fieldLength);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

	public static Object getfloatTypeFieldValue(Object fieldValue, int fieldLength, int precision) {
		try { 
			if(precision>0) {
				if(fieldValue.toString().substring(0,(fieldLength+precision)).contains(".")){
					String dblSpltVal[]= fieldValue.toString().split("\\.");

					if(dblSpltVal[1].length()<=1)
						return String.format("%." + precision + "f", Float.valueOf(fieldValue.toString().substring(0,(fieldLength+precision))));
					else
						return String.format("%." + precision + "f", Float.valueOf(fieldValue.toString().substring(0,(fieldLength+precision+1))));					
				}

			}else {
				return String.format("%0" + fieldLength + "d", (fieldValue.toString())).substring(0,fieldLength);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}			
		return null;			
	}


	public static Object getStringTypeFieldValue(Object fieldValue, int fieldLength, int precision) {

		try {
			//return String.format("%" + fieldLength + "." + fieldLength + "s", fieldValue);
			if(fieldValue==null) {
				fieldValue=" ";
			}
			return StringUtils.rightPad(fieldValue.toString(), fieldLength);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	

	}

	public static Object getcharTypeFieldValue(Object fieldValue, int fieldLength, int precision) {
		try {
			return String.format("%" + fieldLength + "." + fieldLength + "s", fieldValue);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}

	public static Object getBigIntegerTypeFieldValue(Object fieldValue, int fieldLength, int precision) {
		try {
			return String.format("%0" + fieldLength + "d", (fieldValue.toString())).substring(0,fieldLength);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}

	public static Object getListTypeFieldValue(Field field,int fieldLength,Object sourceObj,int precision) {
		try {
			Class<?> type = field.getType();
			return getLsitFieldValusOfClass(type,field,field.get(sourceObj));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;		

	}


	/** End */

	private static Object getValueForField(Field field,Object sourceObj) {
		try {

			Class<?> type = field.getType();
			int fieldLength = 0; int precision = 0; int fieldSize = 0;
			if (field.isAnnotationPresent(ObjInfo.class)){
				fieldLength = field.getAnnotation(ObjInfo.class).length();
				precision = field.getAnnotation(ObjInfo.class).precision();
				fieldSize = field.getAnnotation(ObjInfo.class).size();

			}	

			if(type.toString().contains("class com")) { /** For inner class extract*/
				return getObjValuesAsString(type,field.get(sourceObj));

			}else if (field.isAnnotationPresent(ObjInfo.class)){	


				field.setAccessible(true);		

				String valueMethodName = "get"+type.toString()+"TypeFieldValue";

				if(type.isArray()) 
				{	/** For inner array extract*/				
					//	//System.out.println("Array Length: " + Array.getLength(field.get(sourceObj)));
					StringBuffer arrayBuffer = new StringBuffer("");
					for(int j=0; j<Array.getLength(field.get(sourceObj)); j++) {				    	
						try {
							valueMethodName = valueMethodName.contains("class [Ljava.lang.") ? valueMethodName.replaceAll("class \\[Ljava.lang.", "").replaceAll("[;$]", "") : valueMethodName;

							Method callValueMethod = LowCodeUtility.class.getMethod(valueMethodName, Object.class,int.class,int.class);
							Object obj =  callValueMethod.invoke(locaInstance, Array.get(field.get(sourceObj), j),fieldLength,precision);	
							arrayBuffer.append(obj);							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return arrayBuffer.toString();

				}else if (Collection.class.isAssignableFrom(field.getType())) { /** For inner Array as group extract */

					valueMethodName = valueMethodName.contains("interface java.util.") ? valueMethodName.replaceAll("interface java.util.", "") : valueMethodName;

					Method callValueMethod = LowCodeUtility.class.getMethod(valueMethodName, Field.class,int.class,Object.class, int.class);						
					Object obj =  callValueMethod.invoke(locaInstance, field, fieldLength, sourceObj ,precision);
					return obj;
				} else {
					valueMethodName = valueMethodName.contains("class java.lang.") ? valueMethodName.replaceAll("class java.lang.", "").replaceAll("[;$]", "") : valueMethodName;
					Method callValueMethod = LowCodeUtility.class.getMethod(valueMethodName, Object.class,int.class,int.class);						
					Object obj =  callValueMethod.invoke(locaInstance, field.get(sourceObj),fieldLength,precision);		
					return obj;
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}

		return "";	

	}	

	/** Get List/Array type data */
	private static <T> Object getLsitFieldValusOfClass(Class<?> type, Field field, Object obj) throws IllegalArgumentException, IllegalAccessException, Exception {
		StringBuilder result = new StringBuilder();
		try {	
			if (obj == null) {
				return obj;
			}

			// Deal with collections...
			if (obj instanceof Collection) {
				for (Object o : ((Collection<Object>) obj)) {

					for (Field field1 : o.getClass().getDeclaredFields()) {
						field1.setAccessible(true);
						Object value = getValueForField(field1, o);
						result.append(value.toString());
					}	  		

				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}   
		return result.toString();
	}


	private static <T> Object createAndFillLsitFieldValus(Class<?> clazz, Object target,String targetValues, int startIndex,LowCodeUtility LowCodeUtility) {
		try {
			if (target == null) {
				return target;
			}

			// Deal with collections...
			if (target instanceof Collection) {
				for (Object obj : ((Collection<Object>) target)) {

					if(null == obj)
						obj = clazz.newInstance();

					int fieldLength = 0;

					for (Field field1 : obj.getClass().getDeclaredFields()) {
						field1.setAccessible(true);

						if (field1.isAnnotationPresent(ObjInfo.class))
							fieldLength = field1.getAnnotation(ObjInfo.class).length();
						else
							fieldLength = 0;		  			

						Object value = getValueForField(field1, obj,targetValues, (Integer)getMethod(LowCodeUtility,"globalIndex"),LowCodeUtility,0);
						field1.set(obj, value);

						setMethod(LowCodeUtility,"globalIndex" ,(((Integer)getMethod(LowCodeUtility,"globalIndex") + (fieldLength))));	
					}	  		

				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}  
		return target;
	}



	public static void setintTypeFieldFillValue(Field field, Object target, Object value) {
		try {
			field.set(target, Integer.parseInt(value.toString()));
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setlongTypeFieldFillValue(Field field, Object target, Object value)  {
		try {
			field.set(target, Long.parseLong(value.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static void setdoubleTypeFieldFillValue(Field field, Object target, Object value)  {
		try {
			if(null!=value && !"".equals(value)) {
				field.set(target, Double.parseDouble(value.toString()));
			}
			else {
				value="0";
				field.set(target, Double.parseDouble(value.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public static void setfloatTypeFieldFillValue(Field field, Object target, Object value)  {
		try {
			field.set(target, Float.parseFloat(value.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static void setStringTypeFieldFillValue(Field field, Object target, Object value) {
		try {
			field.set(target, String.valueOf(value.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static void setcharTypeFieldFillValue(Field field, Object target, Object value) {
		try {

			field.set(target, value.toString().charAt(0));
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	
	public static <T> Object createAndFillTargetValue(Class<T> clazz, Object target, String targetValues,
			int startIndex , LowCodeUtility LowCodeUtility) throws Exception {

		if(null == target)
			target = clazz.newInstance();
		int fieldLength = 0 , precision = 0 , fieldSize = 0;
		DataHandling objHandling = null;
		String redefinedType = null, redefinedName = null;
		for (Field field : clazz.getDeclaredFields()) {
			
			if(field.getName()!="this$0") {
				field.setAccessible(true);
				objHandling = null; redefinedType = null; redefinedName = null;
				if (field.isAnnotationPresent(ObjInfo.class)) {
					fieldLength = field.getAnnotation(ObjInfo.class).length();
					precision = field.getAnnotation(ObjInfo.class).precision();
					fieldSize = field.getAnnotation(ObjInfo.class).size();
				}	
				else                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
					fieldLength = 0;
				
				if (field.isAnnotationPresent(ObjRelationship.class)) {
					objHandling = field.getAnnotation(ObjRelationship.class).objHandling();
					redefinedType = field.getAnnotation(ObjRelationship.class).RedefinedType();
					redefinedName = field.getAnnotation(ObjRelationship.class).RedefinedName();
				}
				
				if(DataHandling.REDEFINES.equals(objHandling)) {
					Field redefinedField = clazz.getDeclaredField(redefinedName);
					redefinedField.setAccessible(true);
					LowCodeUtility copyValues1 = new LowCodeUtility();
					setMethod(copyValues1, "globalIndex", 0);
					Object redefinedobj = redefinedField.get(target);
					String strng = getObjValuesAsString(redefinedobj.getClass(), redefinedobj);
					//System.out.println(" 4444 checking redfne :: ValuesAsString "+strng);
					createAndFillRedefinesObj(field.getType(), field.get(target), strng);
					continue;
				}
				Object value = getValueForField(field, target,targetValues, (Integer)getMethod(LowCodeUtility,"globalIndex"),LowCodeUtility,0);

				Class<?> type = field.getType();				

				String valueMethodName = "set"+type.toString()+"TypeFieldFillValue";

				if (Collection.class.isAssignableFrom(field.getType())) {
					//	//System.out.println("List type target fill");
				}else if(type.isArray()) {
					for(int j=0; j<Array.getLength(field.get(target)); j++) {				    	
						try {
							Array.set(field.get(target), j,value);

						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}else if (field.isAnnotationPresent(ObjInfo.class))  {			

					valueMethodName = valueMethodName.contains("class java.lang.") ? valueMethodName.replace("class java.lang.", "") : valueMethodName;

					Method callValueMethod = LowCodeUtility.class.getMethod(valueMethodName, Field.class,Object.class,Object.class);

					callValueMethod.invoke(locaInstance, field,target,value);	
				}

				startIndex = (Integer)getMethod(LowCodeUtility,"globalIndex");
				if(precision>0) {
					if(null!=targetValues && !"".equalsIgnoreCase(targetValues)) {
						if(targetValues.substring(startIndex,(startIndex+fieldLength+precision)).contains(".")){
							String dblSpltVal[]= targetValues.replaceAll("[+-]", "").substring(startIndex,(startIndex+fieldLength+precision+1)).split("\\.");
							setMethod(LowCodeUtility, "globalIndex" , (startIndex+fieldLength+precision+1));
						}
					}else {
						setMethod(LowCodeUtility, "globalIndex" , (startIndex+fieldLength+precision+1));
					}
					
				}else {
					setMethod(LowCodeUtility, "globalIndex" , (startIndex+fieldLength));
				}

			}		
		}
		return target;

	}



	public static Object getintFieldValue(String objValue, int startIndex, int fieldLength, int precision ) {	
		String object="0";
		try {
			if(null!=objValue && !"".equalsIgnoreCase(objValue)) {
				object=objValue.substring(startIndex, (startIndex + fieldLength));
				if(!NumberUtils.isDigits(objValue.substring(startIndex, (startIndex + fieldLength)).trim().replaceAll("[+-]", "").toString())) {
					object="0";
				}	
			}
			return (String.format("%0" + fieldLength + "d", Integer.valueOf(object)));	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;			

	}

	public static Object getlongFieldValue( String objValue, int startIndex, int fieldLength, int precision ) {
		try {
			return (String.format("%0" + fieldLength + "d",Long.parseLong(objValue.substring(startIndex, (startIndex + fieldLength)))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getdoubleFieldValue(String objValue, int startIndex, int fieldLength, int precision ) {	
		
		try {
			if(null!=objValue && !"".equalsIgnoreCase(objValue)) {
				if(precision>0) {
					if(objValue.substring(startIndex,(startIndex+fieldLength+precision)).contains(".")){
						String dblSpltVal[]= objValue.replaceAll("[+-]", "").substring(startIndex,(startIndex+fieldLength+precision)).split("\\.");

						if(dblSpltVal[1].length()<=1) 
							return String.format("%." + precision + "f", Double.valueOf(objValue.substring(startIndex,(startIndex+fieldLength+precision))));
						else
							return String.format("%." + precision + "f", Double.valueOf(objValue.substring(startIndex,(startIndex+fieldLength+precision+1))));	
					}
				}else {
					return String.format("%0" + fieldLength + "d",Integer.valueOf(objValue.substring(startIndex, (startIndex + fieldLength))));
				}
			}else {
				objValue="0";
				return String.format("%0" + fieldLength + "d",Integer.valueOf(objValue));

			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}


	public static Object getStringFieldValue( String objValue, int startIndex, int fieldLength, int precision ) {
		try {
			if(objValue.length()<(startIndex + fieldLength)){
				objValue=StringUtils.rightPad(objValue, startIndex + fieldLength);
			}
			return String.format("%" + fieldLength + "." + fieldLength +  "s", objValue.substring(startIndex, (startIndex + fieldLength)));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;

	}

	public static Object getcharFieldValue(String objValue, int startIndex, int fieldLength, int precision ) {		
		try {
			return String.format("%" + fieldLength + "." + fieldLength +  "s", objValue.substring(startIndex, (startIndex + fieldLength)));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}

	public static Object getBigIntegerFieldValue(String objValue, int startIndex, int fieldLength, int precision ) {		
		try {
			return String.format("%0" + fieldLength + "d",Integer.valueOf(objValue.substring(startIndex, (startIndex + fieldLength))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}


	public static Object getListFieldValue(LowCodeUtility LowCodeUtility, Object targetObj, Field field, String objValue, int startIndex) {	
		try {
			Class<?> type = field.getType();
			return createAndFillLsitFieldValus(type, field.get(targetObj), objValue, startIndex, LowCodeUtility);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;		
	}

	public static Object getfloatFieldValue(String objValue, int startIndex, int fieldLength, int precision ) {		
		try {	
			if(precision>0) {
				if(objValue.substring(startIndex,(startIndex+fieldLength+precision)).contains(".")){
					String dblSpltVal[]= objValue.replaceAll("[+-]", "").substring(startIndex,(startIndex+fieldLength+precision+1)).split("\\.");

					if(dblSpltVal[1].length()<=1) 
						return String.format("%." + precision + "f", Float.valueOf(objValue.substring(startIndex,(startIndex+fieldLength+precision))));
					else
						return String.format("%." + precision + "f", Float.valueOf(objValue.substring(startIndex,(startIndex+fieldLength+precision+1))));	
				}
			}else {
				return String.format("%0" + fieldLength + "d",Float.valueOf(objValue.substring(startIndex, (startIndex + fieldLength))));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return null;

	}



	private static Object getValueForField(Field field,Object targetObj,Object completeValue,int startIndex , LowCodeUtility lowCodeUtility,
			int targetStartPos) throws Exception {		
		Class<?> type = field.getType();
		String objValue = (String) completeValue;
		int fieldLength = 0, precision = 0 , fieldSize = 0;

		if (field.isAnnotationPresent(ObjInfo.class)) {
			fieldLength = field.getAnnotation(ObjInfo.class).length()-targetStartPos;
			precision = field.getAnnotation(ObjInfo.class).precision();
			fieldSize = field.getAnnotation(ObjInfo.class).size();
		}			

		if(null == field.get(targetObj))
			field.set(targetObj, type.newInstance());	

		if(type.toString().contains("class com")) {
			return createAndFillTargetValue(type,field.get(targetObj),objValue,startIndex,lowCodeUtility);

		}else {	

			field.setAccessible(true);		

			String valueMethodName = "get"+type.toString()+"FieldValue";


			if(type.isArray())
			{					
				//		//System.out.println("Array Length: " + Array.getLength(field.get(targetObj)));
				StringBuffer arrayBuffer = new StringBuffer("");
				for(int j=0; j<Array.getLength(field.get(targetObj)); j++) {				    	
					try {				    			
						valueMethodName = valueMethodName.contains("class [Ljava.lang.") ? valueMethodName.replaceAll("class \\[Ljava.lang.", "").replaceAll("[;$]", "") : valueMethodName;
						Method callValueMethod = LowCodeUtility.class.getMethod(valueMethodName,String.class,int.class,int.class,int.class);			
						Object obj =  callValueMethod.invoke(locaInstance, objValue,startIndex,fieldLength,precision);						
						return obj;

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return arrayBuffer.toString();

			}else if (Collection.class.isAssignableFrom(field.getType()) && field.isAnnotationPresent(ObjInfo.class)) {

				valueMethodName = valueMethodName.contains("interface java.util.") ? valueMethodName.replaceAll("interface java.util.", "") : valueMethodName;

				Method callValueMethod = LowCodeUtility.class.getMethod(valueMethodName, LowCodeUtility.class, Object.class, Field.class, String.class, int.class);						
				Object obj =  callValueMethod.invoke(locaInstance, lowCodeUtility, targetObj , field, objValue ,precision);								
				return obj;
			}

			else if (field.isAnnotationPresent(ObjInfo.class)) {
				valueMethodName = valueMethodName.contains("class java.lang.") ? valueMethodName.replaceAll("class java.lang.", "").replaceAll("[;$]", "") : valueMethodName;
				Method callValueMethod = LowCodeUtility.class.getMethod(valueMethodName,String.class,int.class,int.class,int.class);			
				Object obj =  callValueMethod.invoke(locaInstance, objValue,startIndex,fieldLength,precision);						
				return obj;
			}

		}
		return "";

	}

	// -------------------------------------------------------------- Generic Utility methods for getter & setter calls ----------------------------

	public static <T> T getMethod(Object obj, String fieldName) {
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return (T) field.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** getValueForBoolean method is defined to access the 88 level COBOL Boolean Variable*/
	public static boolean getValueForBoolean(Object obj, String fieldName) {
		try {

			Field[] fields = obj.getClass().getDeclaredFields();
			Field field = obj.getClass().getDeclaredField(fieldName+"ArrayList");
			ArrayList<?> Al = (ArrayList<?>)field.get(obj);
			
			return Al.contains(fields[0].getType().getTypeName().contains("String")?getValueForField(fields[0], obj).toString().trim():getValueForField(fields[0], obj));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/** get value from array type variable */
	public static <T> T getMethod(Object obj, String fieldName,int index) {
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return (T) Array.get(field.get(obj), index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setMethod(Object obj, String variable, double varValue) {
		try {

			Field field = obj.getClass().getDeclaredField(variable);
			field.setAccessible(true);
			field.set(obj, varValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setMethod(Object obj, String variable, long varValue) {
		try {

			Field field = obj.getClass().getDeclaredField(variable);
			field.setAccessible(true);
			field.set(obj, varValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setMethod(Object obj, String variable, char varValue) {
		try {
			Field field = obj.getClass().getDeclaredField(variable);
			field.setAccessible(true);
			field.set(obj, varValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setMethod(Object obj, String variable, boolean varValue) {
		try {

			Field field = obj.getClass().getDeclaredField(variable);
			field.setAccessible(true);
			field.set(obj, varValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setMethod(Object obj, String variable, float varValue) {
		try {

			Field field = obj.getClass().getDeclaredField(variable);
			field.setAccessible(true);
			field.set(obj, varValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setMethod(Object obj, String variable, int varValue) {
		try {

			Field field = obj.getClass().getDeclaredField(variable);
			field.setAccessible(true);
			field.set(obj, varValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setMethod(Object obj, String variable, String varValue) {
		try {

			Field field = obj.getClass().getDeclaredField(variable);
			field.setAccessible(true);
			field.set(obj, varValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public static void copyObjectValuesForRedefine1(Object source, Object target) throws Exception {
		LowCodeUtility copyValues = new LowCodeUtility();
		setMethod(copyValues, "globalIndex", 0);	
		try {
			/** get soruce group/ elementory values returns Striing */
			//				getObjValuesAsString(source.getClass(), source);

			createAndFillTargetValue(target.getClass(), target, getObjValuesAsString(source.getClass(), source), 0,copyValues);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static <T> void createAndFillRedefinesObj(Class<T> redefinesObjClass, Object redefinesObject, String redefinedValue) throws Exception {
		
		try {
			LowCodeUtility redefineCopyValues = new LowCodeUtility();
			setMethod(redefineCopyValues, "globalIndex", 0);
			createAndFillTargetValue(redefinesObjClass, redefinesObject, redefinedValue, 0,redefineCopyValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	/** End */

}