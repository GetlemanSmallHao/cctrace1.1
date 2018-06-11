package com.cctrace.testrevss;

import javax.management.RuntimeErrorException;

import org.junit.Test;

public class Testttes {
	@Test
	public void testCatch(){
		System.out.println("之前");
		try {
			throw new Exception();
		} catch (Exception e) {
			System.out.println("处理");
		}
		System.out.println("之后");
	}
}
