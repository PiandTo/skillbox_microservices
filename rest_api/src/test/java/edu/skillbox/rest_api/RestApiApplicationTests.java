package edu.skillbox.rest_api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.skillbox.rest_api.entity.Gender;
import edu.skillbox.rest_api.entity.User;
import edu.skillbox.rest_api.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.core.Is;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
// @WebMvcTest
class RestApiApplicationTests {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	private UserService userService;

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
			.withDatabaseName("postgres").withUsername("snaomi").withPassword("12345").withCopyFileToContainer(
					MountableFile.forClasspathResource("/static/schema.sql"), "/docker-entrypoint-initdb.d/");

	// @BeforeAll
	// public void start() {
	// container.start();
	// }

	// @AfterAll
	// public void close() {
	// container.close();
	// }

	@Test
	@Order(1)
	void testSaveUser() throws Exception {

		String str = getUrl();

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

	@Test
	@Order(2)
	void deleteUser() throws Exception {
		this.mockMvc.perform(
				delete("/user" + "/a99d56c6-a13d-427a-a443-8c77de18dc14")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	void getAllUsers() throws Exception {
		this.mockMvc.perform(
				get("/user/all"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	public String getUrl() {
		return container.getJdbcUrl();
	}
}
