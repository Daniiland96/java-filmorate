package ru.yandex.practicum.filmorate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mvc;
//    private Gson gson = new GsonBuilder().create();
    private Film film;
    private final String URL = "/films";
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        film = new Film(1L, "IT", "Children play with a red balloon",
                LocalDate.of(2017, 9, 7), 135);
//        String filmJson = gson.toJson(film);
//        System.out.println(filmJson);
    }

//        @Test
//    void postTest() throws Exception {
//            this.mvc.perform(post(URL)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(film))
//                    )
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.name").value(user.getName()))
//                    .andReturn().getResponse().getContentAsString();
//    }
//
//    @Test
//    void shouldReturnEmptyList() throws Exception {
//        this.mvc.perform(get(URL))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", 'hasSize(0)));
//    }
}
