package com.galvanize.tmo.paspringstarter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaSpringStarterApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void isHealthy() throws Exception {
		mockMvc.perform(get("/health"))
				.andExpect(status().isOk());
	}

	@Test
	void addBookTest() throws Exception {

		Book book= new Book();
		book.setId(null);
		book.setTitle("Huckleberry Finn");
		book.setYearPublished(1932);
		book.setAuthor("Mark Twain");
		mockMvc.perform(post("/api/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(book))).andExpect(status().isCreated());
	}

	@Test
	void checkLibraryTest() throws Exception {
		mockMvc.perform(get("/api/books")).andExpect(status().isOk());
	}

	@Test
	void deleteLibraryTest() throws Exception {
		mockMvc.perform (delete("/api/books")).andExpect(status().isNoContent());
	}
}
