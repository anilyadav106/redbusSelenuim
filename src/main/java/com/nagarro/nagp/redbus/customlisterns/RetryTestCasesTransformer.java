package com.nagarro.nagp.redbus.customlisterns;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.nagarro.nagp.redbus.utilities.RedBusRetryAnalyzer;

public class RetryTestCasesTransformer implements IAnnotationTransformer {

	/**
	 * Modifies the TestNG annotation to set the RetryAnalyzer.
	 * 
	 * @param annotation The annotation to be transformed.
	 */
	
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
 		  annotation.setRetryAnalyzer(RedBusRetryAnalyzer.class);
	}
	 
	 
	 
}