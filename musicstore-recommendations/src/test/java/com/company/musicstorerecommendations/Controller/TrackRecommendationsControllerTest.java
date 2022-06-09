package com.company.musicstorerecommendations.Controller;

import com.company.musicstorerecommendations.Model.TrackRecommendations;
import com.company.musicstorerecommendations.Repository.TrackRecommendationsRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackRecommendationsController.class)
public class TrackRecommendationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackRecommendationsRepository repo;

    private ObjectMapper mapper = new ObjectMapper();


    private TrackRecommendations sampleTrackRecommendations;
    private String trackRecommendationsJson;
    private final List<TrackRecommendations> allTrackRecommendations = new ArrayList<>();
    private String allTrackRecommendationsJson;

    @Before
    public void setup() throws Exception {
        sampleTrackRecommendations = new TrackRecommendations();
        sampleTrackRecommendations.setId(1);
        sampleTrackRecommendations.setTrack_id(7);
        sampleTrackRecommendations.setUser_id(17);
        sampleTrackRecommendations.setLiked(true);

        trackRecommendationsJson = mapper.writeValueAsString(sampleTrackRecommendations);


        TrackRecommendations trackRecommendations1 = new TrackRecommendations();
        trackRecommendations1.setId(2);
        trackRecommendations1.setTrack_id(6);
        trackRecommendations1.setUser_id(3);
        trackRecommendations1.setLiked(false);

        allTrackRecommendations.add(trackRecommendations1);
        allTrackRecommendations.add(sampleTrackRecommendations);

        allTrackRecommendationsJson = mapper.writeValueAsString(allTrackRecommendations);

    }

    @Test
    public void shouldCreateNewTrackRecommendationsOnPostRequest() throws Exception {
        TrackRecommendations inputTrackRecommendations = new TrackRecommendations();
        inputTrackRecommendations.setId(3);
        inputTrackRecommendations.setTrack_id(7);
        inputTrackRecommendations.setUser_id(17);
        inputTrackRecommendations.setLiked(true);
        String inputJson = mapper.writeValueAsString(inputTrackRecommendations);

        doReturn(sampleTrackRecommendations).when(repo).save(inputTrackRecommendations);

        mockMvc.perform(
                        post("/track")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(trackRecommendationsJson));

    }

    @Test
    public void shouldReturnTrackRecommendationsById() throws Exception {
        doReturn(Optional.of(sampleTrackRecommendations)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/track/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(trackRecommendationsJson))
                );
    }


    @Test
    public void shouldReturnAllTrackRecommendations() throws Exception {
        doReturn(allTrackRecommendations).when(repo).findAll();

        mockMvc.perform(
                        get("/track"))
                .andExpect(status().isOk())
                .andExpect(content().json(allTrackRecommendationsJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(
                        put("/track/1")
                                .content(trackRecommendationsJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/track/2")).andExpect(status().isOk());
    }

    @Test
    public void shouldResponseWithStatus404IfIdIsNotFound() throws Exception {
        mockMvc.perform(get("/track/12"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldTryGetByIdAndReturn404StatusCode() throws Exception {
        mockMvc.perform(get("/track/time"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}