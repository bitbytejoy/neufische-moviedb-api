package de.neuefische.movedbapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MovieControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void create_whenMovie_thenCreateMovie () throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "Karate Kid",
                        "year": 1984,
                        "posterUrl": "https://m.media-amazon.com/images/M/MV5BNTkzY2YzNmYtY2ViMS00MThiLWFlYTEtOWQ1OTBiOGEwMTdhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg"
                    }
                """)
        ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().json("""
                {
                    "id": "1",
                    "title": "Karate Kid",
                    "year": 1984,
                    "posterUrl": "https://m.media-amazon.com/images/M/MV5BNTkzY2YzNmYtY2ViMS00MThiLWFlYTEtOWQ1OTBiOGEwMTdhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg"
                }
            """,
                true
            )
        );
    }

    @Test
    void getAll_whenEmpty_thenReturnEmpty () throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/movies"))
            .andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().json("[]")
            );
    }

    @Test
    void getAll_whenOneMovie_thenReturnOneMovie () throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "Karate Kid",
                        "year": 1984,
                        "posterUrl": "https://m.media-amazon.com/images/M/MV5BNTkzY2YzNmYtY2ViMS00MThiLWFlYTEtOWQ1OTBiOGEwMTdhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg"
                    }
                """)
        );

        mvc.perform(MockMvcRequestBuilders.get("/api/movies"))
            .andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().json("""
                    [
                        {
                            "id": "1",
                            "title": "Karate Kid",
                            "year": 1984,
                            "posterUrl": "https://m.media-amazon.com/images/M/MV5BNTkzY2YzNmYtY2ViMS00MThiLWFlYTEtOWQ1OTBiOGEwMTdhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg"
                        }
                    ]
                """, true)
            );
    }

    @Test
    void getAll_whenTwoMovies_thenReturnTwoMovies () throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "Karate Kid",
                        "year": 1984,
                        "posterUrl": "https://m.media-amazon.com/images/M/MV5BNTkzY2YzNmYtY2ViMS00MThiLWFlYTEtOWQ1OTBiOGEwMTdhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg"
                    }
                """)
        );

        mvc.perform(
            MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "Back To The Future",
                        "year": 1985,
                        "posterUrl": "https://www.getdigital.de/images/products/__generated__resized/1100x1100/18736BTTF_poster_main.jpg"
                    }
                """)
        );

        mvc.perform(MockMvcRequestBuilders.get("/api/movies"))
            .andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().json("""
                    [
                        {
                            "id": "1",
                            "title": "Karate Kid",
                            "year": 1984,
                            "posterUrl": "https://m.media-amazon.com/images/M/MV5BNTkzY2YzNmYtY2ViMS00MThiLWFlYTEtOWQ1OTBiOGEwMTdhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg"
                        },
                        {
                            "id": "2",
                            "title": "Back To The Future",
                            "year": 1985,
                            "posterUrl": "https://www.getdigital.de/images/products/__generated__resized/1100x1100/18736BTTF_poster_main.jpg"
                        }
                    ]
                """, true)
            );
    }
}