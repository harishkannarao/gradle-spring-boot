package com.harishkannarao.springboot.gradledemo;

import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class GradleDemoApplicationAltIntTest extends AbstractBaseFeatureToggleIntTest {

	@Test
	void contextLoads() {
		restartWithProperties(
				List.of(
					"server.port=8084"
				)
		);

		Environment environment = getBean(Environment.class);
		Integer port = environment.getProperty("server.port", Integer.class);
		assertThat(port, equalTo(8084));
	}

}
