package com.mtit.assignment2.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ItemLogAspect {
	public static final Logger LOGGER = LogManager.getLogger(ItemLogAspect.class.getName());


	@Before("execution(* com.mtit.assignment2.service.API.getItems())")
	public void beforeGetItems() {
		LOGGER.info("BEFORE GETTING ITEMS");
	}

	@After("execution(* com.mtit.assignment2.service.API.getItems())")
	public void afterGetItems() {
		LOGGER.info("AFTER GETTING ITEMS");
	}

	@AfterReturning(pointcut = "execution(* com.mtit.assignment2.service.API.getItems())", returning = "result")
	public void afterReturnGetUsers(JoinPoint joinPoint, Object result) {
		LOGGER.info("RETURNED ITEMS ---> " + result.toString());
	}
	
	@Before("execution(* com.mtit.assignment2.service.API.getAllItems())")
	public void beforeGetAllItems() {
		LOGGER.info("BEFORE GETTING ALL ITEMS");
	}

	@After("execution(* com.mtit.assignment2.service.API.getAllItems())")
	public void afterGetAllItems() {
		LOGGER.info("AFTER GETTING ALL ITEMS");
	}

	@AfterReturning(pointcut = "execution(* com.mtit.assignment2.service.API.getAllItems())", returning = "result")
	public void afterReturnGetAllUsers(JoinPoint joinPoint, Object result) {
		LOGGER.info("RETURNED ITEM LIST ---> " + result.toString());
	}
}
