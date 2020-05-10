package com.harishkannarao.springboot.gradledemo;

import org.junit.jupiter.api.Test;

import java.util.List;

class GradleDemoApplicationAltIntTest extends AbstractBaseIntTest {

	@Test
	void contextLoads() {
		testApplication.restartWithProperties(
				List.of(
					"server.port=8083"
				)
		);
	}

}
