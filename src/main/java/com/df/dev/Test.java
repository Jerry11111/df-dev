package com.df.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static Logger log = LoggerFactory.getLogger(Test.class);

	public static void test() {
		log.debug("test");
	}

	public static void main(String[] args) {
		test();

	}

}
