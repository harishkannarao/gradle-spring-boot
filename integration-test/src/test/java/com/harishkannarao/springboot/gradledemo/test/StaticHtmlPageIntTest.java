package com.harishkannarao.springboot.gradledemo.test;

import com.harishkannarao.springboot.gradledemo.common.api.client.StaticFileClient;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class StaticHtmlPageIntTest extends AbstractDefaultProfileIntegrationTest {

	@Test
	void verify_static_html_page() {
		Response response = new StaticFileClient(createRequestSpec()).get("/html/hello-world.html");

		assertThat(response.statusCode(), equalTo(200));
		assertThat(response.contentType(), equalTo("text/html"));
		XmlPath xmlPath = response.xmlPath(HTML);
		String actualMessage = xmlPath.get("html.body.h1");
		assertThat(actualMessage, equalTo("My Sample Static Message"));
	}

}
