package com.company.musicstorerecommendations.Controller;

import com.company.musicstorerecommendations.Model.LabelRecommendations;
import com.company.musicstorerecommendations.Repository.LabelRecommendationsRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelRecommendationsController.class)
public class LabelRecommendationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LabelRecommendationsRepository repo;

    private ObjectMapper mapper = new ObjectMapper();


    private LabelRecommendations sampleLabelRecommendations;
    private String labelRecommendationsJson;
    private final List<LabelRecommendations> allLabelRecommendations = new ArrayList<>();
    private String allLabelRecommendationsJson;

    @Before
    public void setup() throws Exception {
        sampleLabelRecommendations = new LabelRecommendations();
        sampleLabelRecommendations.setId(1);
        sampleLabelRecommendations.setLabel_id(7);
        sampleLabelRecommendations.setUser_id(17);
        sampleLabelRecommendations.setLiked(true);

        labelRecommendationsJson = mapper.writeValueAsString(sampleLabelRecommendations);


        LabelRecommendations labelRecommendations1 = new LabelRecommendations();
        labelRecommendations1.setId(2);
        labelRecommendations1.setLabel_id(6);
        labelRecommendations1.setUser_id(3);
        labelRecommendations1.setLiked(false);

        allLabelRecommendations.add(labelRecommendations1);
        allLabelRecommendations.add(sampleLabelRecommendations);

        allLabelRecommendationsJson = mapper.writeValueAsString(allLabelRecommendations);

    }

    @Test
    public void shouldCreateNewLabelRecommendationsOnPostRequest() throws Exception {
        LabelRecommendations inputLabelRecommendations = new LabelRecommendations();
        inputLabelRecommendations.setId(3);
        inputLabelRecommendations.setLabel_id(7);
        inputLabelRecommendations.setUser_id(17);
        inputLabelRecommendations.setLiked(true);

        String inputJson = mapper.writeValueAsString(inputLabelRecommendations);

        doReturn(sampleLabelRecommendations).when(repo).save(inputLabelRecommendations);

        mockMvc.perform(
                        post("/label")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(labelRecommendationsJson));

    }

    @Test
    public void shouldReturnLabelRecommendationsById() throws Exception {
        doReturn(Optional.of(sampleLabelRecommendations)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/label/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(labelRecommendationsJson))
                );
    }


    @Test
    public void shouldReturnAllLabelRecommendations() throws Exception {
        doReturn(allLabelRecommendations).when(repo).findAll();

        mockMvc.perform(
                        get("/label"))
                .andExpect(status().isOk())
                .andExpect(content().json(allLabelRecommendationsJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(
                        put("/label/1")
                                .content(labelRecommendationsJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/label/2")).andExpect(status().isOk());
    }


}