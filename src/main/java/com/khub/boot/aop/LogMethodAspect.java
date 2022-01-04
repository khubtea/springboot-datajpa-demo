/**
 * @author NagiReddy
 */

package com.khub.boot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMethodAspect {
	
	private static final Logger log = LoggerFactory.getLogger(LogMethodAspect.class);
	
	/*
	 * Before method call will execute
	 */
	@Before("execution(public * com.khub.boot.service.CustomerServiceImpl.getCustomers())")
	public void beforeMethodCall() {
		log.info("@Before: AOP method being called. Hurray!");
	}
	
	/*
	 * After successfully returning from method without any exception only it will execute
	 */
	@AfterReturning("execution(public * com.khub.boot.service.CustomerServiceImpl.getCustomers())")
	public void afterMethodReturning() {
		log.info("@AfterReturning: AOP method being called. Hurray!");
	}

	/*
	 * if method call throws an exception then only this will execute
	 */
	@AfterThrowing("execution(public * com.khub.boot.service.CustomerServiceImpl.getCustomers())")
	public void afterMethodThrowing() {
		log.info("@AfterThrowing: AOP method being called. Hurray!");
	}

	
	/*
	 * Irrespective of result it will execute like finally method
	 */
	@After("execution(public * com.khub.boot.service.CustomerServiceImpl.getCustomers())")
	public void afterMethodCall() {
		log.info("@After: AOP method being called. Hurray!");
	}

	@Pointcut("execution(* com.khub.boot..*(..))")
	protected void bootPackage() {
		
	}
	
	@Pointcut("execution(public * * (..))")
	protected void publicMethod() {
		
	}	
	
	//Declares the around advice that is applied before and after the method matching with a pointcut expression  
	@Around(value= "bootPackage() && publicMethod()")  
	public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable   
	{  
		Object output;
		log.info("The method aroundAdvice() before invokation of the method " + jp.getSignature() + " method");  
		try   
		{  
			output = jp.proceed();  
		}   
		finally   
		{  
		}  
		log.info("The method aroundAdvice() after invokation of the method " + jp.getSignature().getName() + " method"); 
		return output;
	}
	
	@AfterThrowing(pointcut = "bootPackage() && publicMethod()", throwing = "ex")
	public void afterThrowingException(Throwable ex) {
		log.info("@AfterThrowing: Error:"+ex);
	}
	
}
