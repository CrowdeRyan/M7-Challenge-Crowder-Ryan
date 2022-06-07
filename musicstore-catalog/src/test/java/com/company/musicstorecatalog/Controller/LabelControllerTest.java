package com.company.musicstorecatalog.Controller;

import com.company.musicstorecatalog.Model.Label;
import com.company.musicstorecatalog.Repository.LabelRepository;
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
@WebMvcTest(LabelController.class)
public class LabelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LabelRepository repo;

    private ObjectMapper mapper = new ObjectMapper();


    private Label sampleLabel;
    private String labelJson;
    private final List<Label> allLabel = new ArrayList<>();
    private String allLabelJson;

    @Before
    public void setup() throws Exception {
        sampleLabel = new Label();
        sampleLabel.setId(1);
        sampleLabel.setName("InterScope");
        sampleLabel.setWebsite("SeeFarther.com");

        labelJson = mapper.writeValueAsString(sampleLabel);


        Label label1 = new Label();
        label1.setId(7);
        label1.setName("RoadRunner");
        label1.setWebsite("RunFaster.com");

        allLabel.add(label1);
        allLabel.add(sampleLabel);

        allLabelJson = mapper.writeValueAsString(allLabel);

    }

    @Test
    public void shouldCreateNewLabelOnPostRequest() throws Exception {
        Label inputLabel = new Label();
        inputLabel.setId(1);
        inputLabel.setName("InterScope");
        inputLabel.setWebsite("SeeFarther.com");

        String inputJson = mapper.writeValueAsString(inputLabel);

        doReturn(sampleLabel).when(repo).save(inputLabel);

        mockMvc.perform(
                        post("/label")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(labelJson));

    }

    @Test
    public void shouldReturnLabelById() throws Exception {
        doReturn(Optional.of(sampleLabel)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/label/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(labelJson))
                );
    }


    @Test
    public void shouldReturnAllLabel() throws Exception {
        doReturn(allLabel).when(repo).findAll();

        mockMvc.perform(
                        get("/label"))
                .andExpect(status().isOk())
                .andExpect(content().json(allLabelJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(
                        put("/label/1")
                                .content(labelJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/label/2")).andExpect(status().isOk());
    }


}