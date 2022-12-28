package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.ActuatorInfoApiClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ActuatorInfoApiIntTest extends AbstractDefaultProfileIntegrationTest {

	@Test
	void verify_actuator_info_api() {
		Response response = new ActuatorInfoApiClient(createRequestSpec()).get();

		assertThat(response.statusCode(), equalTo(200));
		assertThat(response.contentType(), containsString("application/json"));
		JsonPath jsonPath = response.jsonPath();
		String gitCommitId = jsonPath.get("git.commit.id");
		String gitCommitTime = jsonPath.get("git.commit.time");
		assertThat(gitCommitId, not(emptyOrNullString()));
		assertThat(gitCommitTime, not(emptyOrNullString()));
	}
}
