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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The Class SchedulerInvoker.
 */
@SpringBootApplication
@EnableScheduling
public class SchedulerInvoker implements CommandLineRunner {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerInvoker.class);


	// Simple example shows how a command line spring application can execute an
	// injected bean service. Also demonstrates how you can use @Value to inject
	// command line args ('--name=whatever') or application properties

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
        SpringApplication.run(SchedulerInvoker.class);
    }
	
	 /* (non-Javadoc)
 	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
 	 */
 	@Override
	    public void run(String... strings) throws Exception {
		 promptUser();
		 LOGGER.info("Scheduler will kickoff every 30 seconds...");
	    } 
	 
	 /**
 	 * Prompt user.
 	 *
 	 * @throws ClassNotFoundException the class not found exception
 	 * @throws SecurityException the security exception
 	 * @throws NoSuchMethodException the no such method exception
 	 * @throws InstantiationException the instantiation exception
 	 * @throws IllegalAccessException the illegal access exception
 	 * @throws IllegalArgumentException the illegal argument exception
 	 * @throws InvocationTargetException the invocation target exception
 	 */
 	private void promptUser() throws ClassNotFoundException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			
			String osName = System.getProperty( "os.name" ).toLowerCase();
			String className = null;

			LOGGER.info("Your OS Name is " + osName);
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
			    LOGGER.info("Hello " + method.invoke(o));
			}
	 }
}
