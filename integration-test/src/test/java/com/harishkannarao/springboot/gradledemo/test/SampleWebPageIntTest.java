package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.HomePageClient;
import com.harishkannarao.springboot.gradledemo.common.api.client.SampleWebPageClient;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SampleWebPageIntTest extends AbstractDefaultProfileIntegrationTest {

	@Test
	void verify_sample_web_page() {
		Response response = new SampleWebPageClient(createRequestSpec()).get();

		assertThat(response.statusCode(), equalTo(200));
		assertThat(response.contentType(), containsString("text/html"));
		XmlPath xmlPath = response.xmlPath(HTML);
		String actualMessage = xmlPath.get("html.body.h2");
		assertThat(actualMessage, equalTo("My Sample Web Message"));
	}

	@Test
	void verify_home_page_redirection() {
		Response response = new HomePageClient(createRequestSpec(false)).get();

		assertThat(response.statusCode(), equalTo(302));
		assertThat(response.getHeader("Location"), endsWith("/web/sample"));
	}

}
