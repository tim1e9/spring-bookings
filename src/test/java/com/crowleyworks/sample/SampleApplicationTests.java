package com.crowleyworks.sample;

import com.crowleyworks.main.BookingApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = BookingApplication.class)
@TestPropertySource(locations="classpath:application-test.properties")
class SampleApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Canary test");
	}

}
