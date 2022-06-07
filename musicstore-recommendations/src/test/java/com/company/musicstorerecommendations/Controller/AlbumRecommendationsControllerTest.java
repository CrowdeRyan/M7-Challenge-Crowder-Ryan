package com.company.musicstorerecommendations.Controller;

import com.company.musicstorerecommendations.Model.AlbumRecommendations;
import com.company.musicstorerecommendations.Repository.AlbumRecommendationsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationsController.class)
public class AlbumRecommendationsControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private AlbumRecommendationsRepository repo;

        private ObjectMapper mapper = new ObjectMapper();


        private AlbumRecommendations sampleAlbumRecommendations;
        private String albumRecommendationsJson;
        private final List<AlbumRecommendations> allAlbumRecommendations = new ArrayList<>();
        private String allAlbumRecommendationsJson;

        @Before
        public void setup() throws Exception {
            sampleAlbumRecommendations = new AlbumRecommendations();
            sampleAlbumRecommendations.setId(1);
            sampleAlbumRecommendations.setAlbum_id(7);
            sampleAlbumRecommendations.setUser_id(17);
            sampleAlbumRecommendations.setLiked(true);

            albumRecommendationsJson = mapper.writeValueAsString(sampleAlbumRecommendations);


            AlbumRecommendations albumRecommendations1 = new AlbumRecommendations();
            albumRecommendations1.setId(2);
            albumRecommendations1.setAlbum_id(6);
            albumRecommendations1.setUser_id(3);
            albumRecommendations1.setLiked(false);

            allAlbumRecommendations.add(albumRecommendations1);
            allAlbumRecommendations.add(sampleAlbumRecommendations);

            allAlbumRecommendationsJson = mapper.writeValueAsString(allAlbumRecommendations);

        }

        @Test
        public void shouldCreateNewAlbumRecommendationsOnPostRequest() throws Exception {
            AlbumRecommendations inputAlbumRecommendations = new AlbumRecommendations();
            inputAlbumRecommendations.setId(3);
            inputAlbumRecommendations.setAlbum_id(7);
            inputAlbumRecommendations.setUser_id(17);
            inputAlbumRecommendations.setLiked(true);

            String inputJson = mapper.writeValueAsString(inputAlbumRecommendations);

            doReturn(sampleAlbumRecommendations).when(repo).save(inputAlbumRecommendations);

            mockMvc.perform(
                            post("/album")
                                    .content(inputJson)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(albumRecommendationsJson));

        }

        @Test
        public void shouldReturnAlbumRecommendationsById() throws Exception {
            doReturn(Optional.of(sampleAlbumRecommendations)).when(repo).findById(1);

            ResultActions result = mockMvc.perform(
                            get("/album/1"))
                    .andExpect(status().isOk())
                    .andExpect((content().json(albumRecommendationsJson))
                    );
        }


        @Test
        public void shouldReturnAllAlbumRecommendations() throws Exception {
            doReturn(allAlbumRecommendations).when(repo).findAll();

            mockMvc.perform(
                            get("/album"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(allAlbumRecommendationsJson)
                    );
        }

        @Test
        public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
            mockMvc.perform(
                            put("/album/1")
                                    .content(albumRecommendationsJson)
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }

        @Test
        public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
            mockMvc.perform(delete("/album/2")).andExpect(status().isOk());
        }


    }
