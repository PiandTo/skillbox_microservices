package edu.skillbox.rest_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.skillbox.rest_api.entity.Gender;
import edu.skillbox.rest_api.entity.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.core.Is;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class RestApiApplicationTests {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");

	@Test
	void testSaveUser() throws Exception {

		User user = User.builder()
		.name("Mike")
		.surname("Mike")
		.patronymic("Mike")
		.gender(Gender.MALE)
		.phoneNumber("999")	
		.login("Mike")
		.build();

		this.mockMvc.perform(
			post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", Is.is(user.getName())));
	}
}
