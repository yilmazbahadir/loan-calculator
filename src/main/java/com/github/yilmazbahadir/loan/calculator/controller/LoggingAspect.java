package com.github.yilmazbahadir.loan.calculator.controller;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("execution(* com.github.yilmazbahadir.loan.calculator.*.*(..))")
	public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (Throwable e) {
			logger.error("Exception", e);
			throw e;
		} finally {
			logger.debug("Method {} is invoked with the parameters[{}] and returned[{}]",
					joinPoint.getSignature().toString(), Arrays.deepToString(joinPoint.getArgs()), value);
		}
		return value;
	}
}
