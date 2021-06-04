package com.crowleyworks.sample;

import com.crowleyworks.main.BookingApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BookingApplication.class)
class SampleApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Canary test");
	}

}
