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

import com.playsafe.conversions.error.ApiError;
import com.playsafe.conversions.utils.Degree;
import com.playsafe.conversions.utils.Distance;
import com.playsafe.conversions.utils.Kelvin;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class ConversionsControllerTest {

	private static final String API_URL = "/conversions";

	public static final String KTOC = "/ktoc";
	public static final String CTOK = "/ctok";
	public static final String MTOK = "/mtok";
	public static final String KTOM = "/ktom";

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	public void postUserRequest_whenUserRequestIsValidKelvin_receiveOK() {
		Kelvin kelvin = createValidKelvinRequest();
		ResponseEntity<Object> response = postUserRequest(kelvin, Object.class, KTOC);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void postUserRequest_whenUserRequestIsValid_ConvertKelvinsToDegreesCelcius() {
		Kelvin kelvin = createValidKelvinRequest();
		assertThat(kelvin.getEnteredValue()).isEqualTo(33);
	}

	@Test
	public void postUserRequest_whenUserEntersNegativeKelvinValue_receiveBadRequest() {
		Kelvin kelvin = createValidKelvinRequest();
		kelvin.setEnteredValue(-1);
		ResponseEntity<Object> response = postUserRequest(kelvin, Object.class, KTOC);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void postUserRequest_whenUserInputKelvinIsInvalid_receiveApiError() {
		Kelvin kelvin = new Kelvin();
		ResponseEntity<ApiError> response = postUserRequest(kelvin, ApiError.class, KTOC);
		assertThat(response.getBody().getUrl()).isEqualTo(API_URL + KTOC);

	}

	@Test
	public void postUserRequest_whenUserInputKelvinIsInvalid_receiveApiErrorWithValidationErrors() {
		Kelvin kelvin = new Kelvin();
		ResponseEntity<ApiError> response = postUserRequest(kelvin, ApiError.class, KTOC);
		assertThat(response.getBody().getValidationErrors().size()).isEqualTo(1);

	}

	@Test
	public void postUserRequest_whenUserRequestIsValidDegree_receiveOK() {
		Degree degree = createValidDegreeRequest();
		ResponseEntity<Object> response = postUserRequest(degree, Object.class, CTOK);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void postUserRequest_whenUserEntersValueDegreeWithinRange_receiveOk() {
		Degree degree = createValidDegreeRequest();
		degree.setEnteredValue(100);
		ResponseEntity<Object> response = postUserRequest(degree, Object.class, CTOK);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void postUserRequest_whenUserEntersDegreeBelowMinRange_receiveBadRequest() {
		Degree degree = createValidDegreeRequest();
		degree.setEnteredValue(-272.16);
		ResponseEntity<Object> response = postUserRequest(degree, Object.class, CTOK);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void postUserRequest_whenUserEntersDegreeAboveMaxRange_receiveBadRequest() {
		Degree degree = createValidDegreeRequest();
		degree.setEnteredValue(726.86);
		ResponseEntity<Object> response = postUserRequest(degree, Object.class, CTOK);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void postUserRequest_whenUserRequestIsValidDistanceMiles_receiveOK() {
		Distance miles = createValidDistance();
		ResponseEntity<Object> response = postUserRequest(miles, Object.class, MTOK);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void postUserRequest_whenUserRequestIsValidDistanceKM_receiveOK() {
		Distance km = createValidDistance();
		ResponseEntity<Object> response = postUserRequest(km, Object.class, MTOK);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	private Distance createValidDistance() {

		Distance miles = new Distance();
		miles.setEnteredValue(1);
		return miles;
	}

	public <T> ResponseEntity<T> postUserRequest(Object request, Class<T> response, String endPoint) {
		return testRestTemplate.postForEntity(API_URL + endPoint, request, response);
	}

	private Kelvin createValidKelvinRequest() {
		Kelvin kelvin = new Kelvin();
		kelvin.setEnteredValue(33);
		return kelvin;
	}

	private Degree createValidDegreeRequest() {

		Degree degree = new Degree();
		degree.setEnteredValue(12);
		return degree;
	}

}
