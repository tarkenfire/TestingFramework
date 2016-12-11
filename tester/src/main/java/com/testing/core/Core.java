package com.testing.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.reflect.ClassPath;
import com.google.common.collect.ImmutableSet;

public class Core {

	public static void main(String[] args) {	
		
		//TODO: hack code
		
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		try {
			ClassPath path = ClassPath.from(loader);
			
			ImmutableSet<ClassPath.ClassInfo> classes = path.getTopLevelClasses("com.testing.tests");
			
			for (ClassPath.ClassInfo info : classes){
				
				Class<?> clazz = info.load();
				
				if (Testable.class.isAssignableFrom(clazz)){
					Object instance = clazz.newInstance();
					Method testMethod = clazz.getMethod("runTest", null);
					
					Object returnedValue = testMethod.invoke(instance, null);
					System.out.println(returnedValue.toString());	
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
