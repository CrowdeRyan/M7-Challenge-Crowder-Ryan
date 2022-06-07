package com.company.musicstorerecommendations.Controller;

import com.company.musicstorerecommendations.Model.ArtistRecommendations;
import com.company.musicstorerecommendations.Repository.ArtistRecommendationsRepository;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


    @RunWith(SpringRunner.class)
    @WebMvcTest(ArtistRecommendationsController.class)
    public class ArtistRecommendationsControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ArtistRecommendationsRepository repo;

        private ObjectMapper mapper = new ObjectMapper();


        private ArtistRecommendations sampleArtistRecommendations;
        private String artistRecommendationsJson;
        private final List<ArtistRecommendations> allArtistRecommendations = new ArrayList<>();
        private String allArtistRecommendationsJson;

        @Before
        public void setup() throws Exception {
            sampleArtistRecommendations = new ArtistRecommendations();
            sampleArtistRecommendations.setId(1);
            sampleArtistRecommendations.setArtist_id(7);
            sampleArtistRecommendations.setUser_id(17);
            sampleArtistRecommendations.setLiked(true);

            artistRecommendationsJson = mapper.writeValueAsString(sampleArtistRecommendations);


            ArtistRecommendations artistRecommendations1 = new ArtistRecommendations();
            artistRecommendations1.setId(2);
            artistRecommendations1.setArtist_id(6);
            artistRecommendations1.setUser_id(3);
            artistRecommendations1.setLiked(false);

            allArtistRecommendations.add(artistRecommendations1);
            allArtistRecommendations.add(sampleArtistRecommendations);

            allArtistRecommendationsJson = mapper.writeValueAsString(allArtistRecommendations);

        }

        @Test
        public void shouldCreateNewArtistRecommendationsOnPostRequest() throws Exception {
            ArtistRecommendations inputArtistRecommendations = new ArtistRecommendations();
            inputArtistRecommendations.setId(3);
            inputArtistRecommendations.setArtist_id(7);
            inputArtistRecommendations.setUser_id(17);
            inputArtistRecommendations.setLiked(true);

            String inputJson = mapper.writeValueAsString(inputArtistRecommendations);

            doReturn(sampleArtistRecommendations).when(repo).save(inputArtistRecommendations);

            mockMvc.perform(
                            post("/artist")
                                    .content(inputJson)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(artistRecommendationsJson));

        }

        @Test
        public void shouldReturnArtistRecommendationsById() throws Exception {
            doReturn(Optional.of(sampleArtistRecommendations)).when(repo).findById(1);

            ResultActions result = mockMvc.perform(
                            get("/artist/1"))
                    .andExpect(status().isOk())
                    .andExpect((content().json(artistRecommendationsJson))
                    );
        }


        @Test
        public void shouldReturnAllArtistRecommendations() throws Exception {
            doReturn(allArtistRecommendations).when(repo).findAll();

            mockMvc.perform(
                            get("/artist"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(allArtistRecommendationsJson)
                    );
        }

        @Test
        public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
            mockMvc.perform(
                            put("/artist/1")
                                    .content(artistRecommendationsJson)
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }

        @Test
        public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
            mockMvc.perform(delete("/artist/2")).andExpect(status().isOk());
        }


    }