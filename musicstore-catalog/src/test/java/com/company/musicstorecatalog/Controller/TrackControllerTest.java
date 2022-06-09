package com.company.musicstorecatalog.Controller;

import com.company.musicstorecatalog.Model.Track;
import com.company.musicstorecatalog.Repository.TrackRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackController.class)
public class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackRepository repo;

    private ObjectMapper mapper = new ObjectMapper();


    private Track sampleTrack;
    private String trackJson;
    private final List<Track> allTrack = new ArrayList<>();
    private String allTrackJson;

    @Before
    public void setup() throws Exception {
        sampleTrack = new Track();
        sampleTrack.setId(1);
        sampleTrack.setTitle("Machinehead");
        sampleTrack.setAlbum_id("32");
        sampleTrack.setRun_time("4:25");


        trackJson = mapper.writeValueAsString(sampleTrack);


        Track track1 = new Track();
        track1.setId(8);
        track1.setTitle("Overload");
        track1.setAlbum_id("12");
        track1.setRun_time("3:45");

        allTrack.add(track1);
        allTrack.add(sampleTrack);

        allTrackJson = mapper.writeValueAsString(allTrack);

    }

    @Test
    public void shouldCreateNewTrackOnPostRequest() throws Exception {
        Track inputTrack = new Track();
        inputTrack.setId(1);
        inputTrack.setTitle("Machinehead");
        inputTrack.setAlbum_id("32");
        inputTrack.setRun_time("4:25");

        String inputJson = mapper.writeValueAsString(inputTrack);

        doReturn(sampleTrack).when(repo).save(inputTrack);

        mockMvc.perform(
                        post("/track")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(trackJson));

    }

    @Test
    public void shouldReturnTrackById() throws Exception {
        doReturn(Optional.of(sampleTrack)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/track/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(trackJson))
                );
    }


    @Test
    public void shouldReturnAllTrack() throws Exception {
        doReturn(allTrack).when(repo).findAll();

        mockMvc.perform(
                        get("/track"))
                .andExpect(status().isOk())
                .andExpect(content().json(allTrackJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(
                        put("/track/1")
                                .content(trackJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        mockMvc.perform(delete("/track/8")).andExpect(status().isOk());
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