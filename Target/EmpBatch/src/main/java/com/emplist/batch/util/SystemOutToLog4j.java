package com.emplist.batch.util;

import java.io.PrintStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author AP5044237
 *
 */
public class SystemOutToLog4j extends PrintStream {
	
	private static final Log localLog = LogFactory.getLog(SystemOutToLog4j.class);

	private static final PrintStream originalSystemOut = System.out;
	
	private static SystemOutToLog4j systemOutToLogger;	

	private String packageOrClassToLog;
	
	
	
	private SystemOutToLog4j(PrintStream original, String packageOrClassToLog) {
		super(original);
		this.packageOrClassToLog = packageOrClassToLog;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public static void enableForClass(Class className) {
		systemOutToLogger = new SystemOutToLog4j(originalSystemOut, className.getName());
		System.setOut(systemOutToLogger);
	}

	
	public static void enableForPackage(String packageToLog) {
		systemOutToLogger = new SystemOutToLog4j(originalSystemOut, packageToLog);
		System.setOut(systemOutToLogger);
	}

	
	public static void disable() {
		System.setOut(originalSystemOut);
		systemOutToLogger = null;
	}

	
	
	@Override	
	public void println(String line) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement caller = findCallerToLog(stack);
		if (caller == null) {
			super.println(line);
			return;
		}
//		org.apache.commons.logging.Log
//		Log log = LogFactory.getLog(SystemOutToLog4j.class);
//		org.apache.commons.logging.LogFactory
		
//		Log sysoutlog = LogFactory.getLog(SystemOutToLog4j.class);
//		org.apache.log4j.Logger logger = org.apache.log4j.LogManager.getLogger(caller.getClass());
		Log sysoutlog = LogFactory.getLog(caller.getClass());
//		logger.debug("Code Line No.: " + stack[2].getLineNumber() + ", Class Name: " + caller.getClassName() + ", Text: " + line);
//		sysoutlog.debug("Code Line No.: " + stack[2].getLineNumber() + ", Class Name: " + caller.getClassName() + ", Text: " + line);
		sysoutlog.debug(line);
	}

	
	
	public StackTraceElement findCallerToLog(StackTraceElement[] stack) {
		for (StackTraceElement element : stack) {
			if (element.getClassName().startsWith(packageOrClassToLog)) {
				return element;
			}			
		}
		return null;
	}
	
	
	
	
}
