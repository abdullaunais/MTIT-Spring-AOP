package com.mtit.assignment2.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLogAspect {
	public static final Logger LOGGER = LogManager.getLogger(UserLogAspect.class.getName());


	@Before("execution(* com.mtit.assignment2.service.API.getUsers())")
	public void beforeGetUsers() {
		LOGGER.info("BEFORE GETTING USERS");
	}

	@After("execution(* com.mtit.assignment2.service.API.getUsers())")
	public void afterGetUsers() {
		LOGGER.info("AFTER GETTING USERS");
	}
	
	@AfterReturning(pointcut = "execution(* com.mtit.assignment2.service.API.getUsers())", returning = "result")
	public void afterReturnGetUsers(JoinPoint joinPoint, Object result) {
		LOGGER.info("RETURNED USERS -> " + result.toString());
	}

	@Before("execution(* com.mtit.assignment2.service.API.getAllUsers())")
	public void beforeGetAllUsers() {
		LOGGER.info("BEFORE GETTING ALL USERS");
	}

	@After("execution(* com.mtit.assignment2.service.API.getAllUsers())")
	public void afterGetAllUsers() {
		LOGGER.info("AFTER GETTING ALL USERS");
	}
	
	@AfterReturning(pointcut = "execution(* com.mtit.assignment2.service.API.getAllUsers())", returning = "result")
	public void afterReturnGetAllUsers(JoinPoint joinPoint, Object result) {
		LOGGER.info("RETURNED USER LIST -> " + result.toString());
	}


}
