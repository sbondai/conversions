package com.playsafe.conversions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.playsafe.conversions.utils.Kelvin;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class ConversionsControllerTest {

	private static final String API_URL = "/conversions";

	public static final String KTOC = "/ktoc";

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	public void postUserRequest_whenUserRequestIsValidKelvin_receiveOK() {
		Kelvin kelvin = createValidKelvinRequest();
		ResponseEntity<Object> response = postUserRequest(kelvin, Object.class, KTOC);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	public <T> ResponseEntity<T> postUserRequest(Object request, Class<T> response, String endPoint) {
		return testRestTemplate.postForEntity(API_URL + endPoint, request, response);
	}

	private Kelvin createValidKelvinRequest() {
		Kelvin kelvin = new Kelvin();
		kelvin.setEnteredValue(33);
		return kelvin;
	}

}
