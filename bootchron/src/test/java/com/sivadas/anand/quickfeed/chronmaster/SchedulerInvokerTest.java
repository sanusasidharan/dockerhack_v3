/*
 * Copyright (c) 2017 Author(s). All rights reserved.
 * 
 * @Author	- Anand Sivadas
 * @Date	- 15 Jan, 2017
 * 
 * 
 */
package com.sivadas.anand.quickfeed.chronmaster;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SchedulerInvokerTest.
 */
public class SchedulerInvokerTest {
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = LoggerFactory.getLogger(SchedulerInvokerTest.class);
	
	/**
	 * Test prompt user.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SecurityException the security exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	@Test
	public void testPromptUser() throws ClassNotFoundException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String osName = System.getProperty( "os.name" ).toLowerCase();
		String className = null;

		LOGGER.info("OS Name is " + osName);
		if( osName.contains( "windows" ) ){
		    className = "com.sun.security.auth.module.NTSystem";
		} 		else if( osName.contains( "linux" ) ){
		    className = "com.sun.security.auth.module.UnixSystem";
		} 		else if( osName.contains( "solaris" ) || osName.contains( "sunos" ) ){
		    className = "com.sun.security.auth.module.SolarisSystem";
		} else if( osName.contains( "mac" ) || osName.contains( "os x" ) ){
		    className = "com.sun.security.auth.module.UnixSystem";
		}

		if( className != null ){
		    Class<?> c = Class.forName( className );
		    Method method = c.getDeclaredMethod( "getUsername" );
		    Object o = c.newInstance();
		    System.out.println( method.invoke( o ) );
		    LOGGER.info("User is " + method.invoke(o));
		}
	}
	

}
