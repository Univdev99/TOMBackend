package com.tom.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class AopLogDuration {
	static final Logger LOGGER = LoggerFactory.getLogger(AopLogDuration.class);

	@Around("execution(@LogDuration * *(..)) && @annotation(logDurationAnnotation)")
	public Object logDuration(ProceedingJoinPoint joinPoint, LogDuration logDurationAnnotation) throws Throwable {

		// long startTime = System.currentTimeMillis();
		// long endTime = System.currentTimeMillis();
		// long duration = endTime - startTime;
		StopWatch watch = new StopWatch();
		watch.start();
		// execute the method and get the result
		Object result = joinPoint.proceed();
		watch.stop();
		LOGGER.trace(logDurationAnnotation.value() + ": " + watch.getTotalTimeMillis() + " millis");
		// System.out.println(logDurationAnnotation.value() + ": " + watch.getTotalTimeMillis() + "ms");
		// return the result to the caller
		return result;
	}

}
